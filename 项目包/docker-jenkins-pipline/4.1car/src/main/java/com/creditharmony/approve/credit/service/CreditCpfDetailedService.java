package com.creditharmony.approve.credit.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.credit.dao.CreditCpfDetailedDao;
import com.creditharmony.approve.credit.dao.CreditCycleRecordDao;
import com.creditharmony.approve.credit.dao.CreditReportDetailedDao;
import com.creditharmony.approve.credit.entity.CreditCpfDetailed;
import com.creditharmony.approve.credit.entity.CreditCycleRecord;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
import com.creditharmony.approve.credit.entity.ex.CreditCpfDetailedEx;
import com.creditharmony.approve.credit.entity.ex.CreditCycleRecordEx;

/**
 * 公积金操作
 * @Class Name CreditCpfDetailedService
 * @author 李文勇
 * @Create In 2016年2月24日
 */
@Service
public class CreditCpfDetailedService extends CoreManager<CreditCpfDetailedDao,CreditCpfDetailed> {

	@Autowired
	private CreditCpfDetailedDao creditCpfDetailedDao;
	@Autowired
	private CreditCycleRecordDao creditCycleRecordDao;
	@Autowired
	private CreditReportDetailedDao creditReportDetailedDao;
	
	/**
	 * 显示数据
	 * 2016年2月3日
	 * By 李文勇
	 * @param creditReportDetailed
	 * @return CreditCpfDetailedEx
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public CreditCpfDetailedEx showData(CreditReportDetailed creditReportDetailed) throws IllegalArgumentException,
			SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<CreditCpfDetailed> creditCpfDetailedList = creditCpfDetailedDao.getAllByParam(creditReportDetailed);
		List<CreditCycleRecord> cycleResult = creditCycleRecordDao.getAllByParam(creditReportDetailed);
		CreditCpfDetailedEx creditCpfDetailedEx = new CreditCpfDetailedEx();
		creditCpfDetailedEx.setCreditCpfDetailedList(creditCpfDetailedList);
		List<CreditCycleRecordEx> list = new ArrayList<CreditCycleRecordEx>();
		if(creditCpfDetailedList != null && creditCpfDetailedList.size() > 0){
			for(int w = 0; w < creditCpfDetailedList.size(); w++ ){
				if(creditCpfDetailedList.get(w) != null){
					CreditCycleRecordEx creditCycleRecordEx = getQsList(cycleResult,creditCpfDetailedList.get(w).getId());
					list.add(creditCycleRecordEx);
				}
			}
		}
		creditCpfDetailedEx.setCreditCycleRecordExList(list);
		return creditCpfDetailedEx;
	}
	
	/**
	 * 封装期数（展示用）
	 * 2016年2月3日
	 * By 李文勇
	 * @param cycleResult
	 * @param id
	 * @return CreditCycleRecordEx
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private CreditCycleRecordEx getQsList(List<CreditCycleRecord> cycleResult,String id) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		CreditCycleRecordEx creditCycleRecordExList = new CreditCycleRecordEx();
		if(cycleResult != null && cycleResult.size() > 0){
			for( int i = 0; i < cycleResult.size(); i++ ){
				if(StringUtils.isNotEmpty(cycleResult.get(i).getRelationId()) && id.equals(cycleResult.get(i).getRelationId())){
					Method[] methods = creditCycleRecordExList.getClass().getMethods();
					for( int w = 0; w < methods.length; w++){
						// 判断返回值是否为null,如果是null置为“”
						if (methods[w].getName().equals("getQs"+cycleResult.get(i).getCycleNo()) && methods[w].invoke(creditCycleRecordExList) == null){
							String outmethod = "s"+methods[w].getName().substring(1, methods[w].getName().length());
							creditCycleRecordExList.getClass().getMethod(outmethod, String.class).invoke(creditCycleRecordExList, cycleResult.get(i).getCycleValue());
							break;
						}
					}
				}
				creditCycleRecordExList.setRelationId(id);
			}
		}
		return creditCycleRecordExList;
	}
	
	/**
	 * 保存数据
	 * 2016年2月2日
	 * By 李文勇
	 * @param creditCpfDetailedEx
	 * @return boolean是否操作成功
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public boolean saveData(CreditCpfDetailedEx creditCpfDetailedEx) throws IllegalAccessException, InvocationTargetException, IllegalArgumentException, SecurityException, NoSuchMethodException{
		List<CreditCpfDetailed> creditCpfDetailedList = creditCpfDetailedEx.getCreditCpfDetailedList(); // 公积金信息
		List<CreditCycleRecordEx> creditCycleRecordExList = creditCpfDetailedEx.getCreditCycleRecordExList(); // 公积金期数
		CreditReportDetailed creditReportDetailed = new CreditReportDetailed();
		creditReportDetailed.setLoanCode(creditCpfDetailedEx.getLoanCode());
		creditReportDetailed.setDictCustomerType(creditCpfDetailedEx.getType());
		creditReportDetailed.setrCustomerCoborrowerId(creditCpfDetailedEx.getRelId());
		CreditReportDetailed detail = creditReportDetailedDao.getIdByParam(creditReportDetailed);
		if(detail != null && creditCpfDetailedList != null && creditCpfDetailedList.size() > 0){
			String detailId = detail.getId();
			for( int i = 0;i < creditCpfDetailedList.size(); i++ ){
				String id = creditCpfDetailedList.get(i).getId();
				// 如果有id 说明该数据在数据库已存在，则更新，否则，添加
				if(StringUtils.isNotEmpty(id)){
					CreditCpfDetailed creditCpfDetailed = new CreditCpfDetailed();
					BeanUtils.copyProperties(creditCpfDetailed,creditCpfDetailedList.get(i));
					creditCpfDetailed.preUpdate();
					creditCpfDetailedDao.updateData(creditCpfDetailed);
					// 	更新期数（先删除再保存）
					CreditCycleRecord creditCycleRecord = new CreditCycleRecord();
					creditCycleRecord.setRelationId(id);
					creditCycleRecordDao.deleteData(creditCycleRecord);
					// 保存期数
					if(creditCycleRecordExList != null && creditCycleRecordExList.get(i) != null){
						saveQs(creditCycleRecordExList.get(i),creditCpfDetailed.getId());
					}
				}else{// 添加数据
					CreditCpfDetailed creditCpfDetailed = creditCpfDetailedList.get(i);
					creditCpfDetailed.setIsNewRecord(false);
					creditCpfDetailed.setRelationId(detailId);
					creditCpfDetailed.preInsert();
					creditCpfDetailedDao.insertData(creditCpfDetailed);
					// 保存期数
					if(creditCycleRecordExList != null && creditCycleRecordExList.get(i) != null){
						saveQs(creditCycleRecordExList.get(i),creditCpfDetailed.getId());
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 插入数据（公积金期数）
	 * 2016年2月3日
	 * By 李文勇
	 * @param param
	 * @param paramId
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @return none
	 */
	private void saveQs(CreditCycleRecordEx param , String paramId) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		CreditCycleRecordEx creditCycleRecordEx =param;
		Method[] methods = creditCycleRecordEx.getClass().getMethods();
		for( int w = 0; w < methods.length; w++){
			// 判断返回值是否为null,如果是null置为“”
			if (methods[w].getName().startsWith("getQs") && methods[w].invoke(creditCycleRecordEx) == null){
				String outmethod = "s"+methods[w].getName().substring(1, methods[w].getName().length());
				creditCycleRecordEx.getClass().getMethod(outmethod, String.class).invoke(creditCycleRecordEx, "");
			}
			if (methods[w].getName().startsWith("getQs")){
				String num = methods[w].getName().substring(5, methods[w].getName().length());
				String methodsN = (String) methods[w].invoke(creditCycleRecordEx);
				CreditCycleRecord  toDb = new CreditCycleRecord();
				toDb.setRelationId(paramId);
				toDb.setRelationType(NumberConstants.THREE_STRING);
				toDb.setCycleNo(Integer.parseInt(num));
				toDb.setCycleValue(methodsN);
				toDb.preInsert();
				creditCycleRecordDao.insertData(toDb);
			}
		}
	}
	
	/**
	 * 删除数据
	 * 2016年2月2日
	 * By 李文勇
	 * @param record
	 * @return 返回操作成功数
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public int deleteData(CreditCpfDetailed record){
		int result = creditCpfDetailedDao.deleteData(record);
		CreditCycleRecord creditCycleRecord = new CreditCycleRecord();
		creditCycleRecord.setRelationId(record.getId());
		result = creditCycleRecordDao.deleteData(creditCycleRecord);
		return result;
	}
}

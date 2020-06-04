package com.creditharmony.approve.credit.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.credit.dao.CreditCycleRecordDao;
import com.creditharmony.approve.credit.dao.CreditLoanDetailedOneDao;
import com.creditharmony.approve.credit.dao.CreditLoanDetailedTwoDao;
import com.creditharmony.approve.credit.dao.CreditReportDetailedDao;
import com.creditharmony.approve.credit.entity.CreditCycleRecord;
import com.creditharmony.approve.credit.entity.CreditLoanDetailedOne;
import com.creditharmony.approve.credit.entity.CreditLoanDetailedTwo;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
import com.creditharmony.approve.credit.entity.ex.CreditCycleRecordEx;
import com.creditharmony.approve.credit.entity.ex.CreditLoanDetailedOneEx;
import com.creditharmony.core.cache.DictCache;

/**
 * 贷款明细一服务类
 * @Class Name CreditLoanDetailedOneService
 * @author 侯志斌
 * @Create In 2016年01月11日
 */
@Service
public class CreditLoanDetailedOneService {
	
	@Autowired
	CreditLoanDetailedOneDao creditLoanDetailedOneDao;
	
	@Autowired
	CreditCycleRecordDao creditCycleRecordDao;
	
	@Autowired
	CreditLoanDetailedTwoDao creditLoanDetailedTwoDao;
	
	@Autowired
	private CreditReportDetailedDao creditReportDetailedDao;
	
	/**
	 * 根据参数查询所有贷款明细
	 * 2016年02月02日
	 * By 侯志斌
	 * @param qureryMap 动态参数
	 * @return 数据列表
	 */
	public List<CreditLoanDetailedOne> query(Map<String, Object> qureryMap) {
		List<CreditLoanDetailedOne> dataList = creditLoanDetailedOneDao.findByParams(qureryMap);
		return dataList;
	}
	

	/**
	 * 保存贷款明细一
	 * 2016年02月02日
	 * By 侯志斌
	 * @param 贷款一实体类
	 * @return 是否保存成功
	 */
	public String save(CreditLoanDetailedOne model) {
		String id = model.getId();
		if(id == null || id.equals("")){
			model.preInsert();
			creditLoanDetailedOneDao.insert(model);
			id = model.getId();
		}else{
			
			creditLoanDetailedOneDao.updateData(model);
		}
		return id;
	}

	/**
	 * 展示数据
	 * 2016年2月3日
	 * By 侯志斌
	 * @param 征信报告实体
	 * @return 贷款扩展实体
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public CreditLoanDetailedOneEx showData(CreditReportDetailed creditReportDetailed) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		List<CreditLoanDetailedOne> creditLoanDetailedOneList = creditLoanDetailedOneDao.getAllByParam(creditReportDetailed);
		
		if(creditLoanDetailedOneList == null || creditLoanDetailedOneList.size() == 0 ||creditLoanDetailedOneList.get(0)==null){
			return null;
		}
		
		for (CreditLoanDetailedOne creditLoanDetailedOne : creditLoanDetailedOneList) {
			creditLoanDetailedOne.setLoanType(DictCache.getInstance().getDictLabel("jk_credit_loan_type_flag", creditLoanDetailedOne.getLoanType()));
			creditLoanDetailedOne.setGuaranteeType(DictCache.getInstance().getDictLabel("jk_guarantee_type", creditLoanDetailedOne.getGuaranteeType()));
			creditLoanDetailedOne.setCurrency(DictCache.getInstance().getDictLabel(DictionaryConstants.CREDIT_CARDINFO_CURRENCY, creditLoanDetailedOne.getCurrency()));
			creditLoanDetailedOne.setAccountStatu(DictCache.getInstance().getDictLabel(DictionaryConstants.CREDIT_LOANINFO_ACCOUNTSTATUS, creditLoanDetailedOne.getAccountStatu()));
			creditLoanDetailedOne.setRepayFrequency(DictCache.getInstance().getDictLabel("jk_repay_rate", creditLoanDetailedOne.getRepayFrequency()));
			creditLoanDetailedOne.setLevelClass(DictCache.getInstance().getDictLabel(DictionaryConstants.LOAN_LEVEL, creditLoanDetailedOne.getLevelClass()));
		}
		
		
		
		String relationId=creditLoanDetailedOneList.get(0).getRelationId(); 
		
		Map<String,Object> filter = new HashMap<String, Object>();
		filter.put("relationId", relationId);
		filter.put("relationType", "1");
		List<CreditCycleRecord> cycleResult = creditCycleRecordDao.getLoanAllByParam(filter);
		
		Map<String,Object> filter2 = new HashMap<String, Object>();
		filter2.put("relationId", relationId);
		List<CreditLoanDetailedTwo> creditLoanDetailedTwoList = creditLoanDetailedTwoDao.findByParams(filter2);
		
		CreditLoanDetailedOneEx creditLoanDetailedOneEx = new CreditLoanDetailedOneEx();
		creditLoanDetailedOneEx.setCreditLoanDetailedOneList(creditLoanDetailedOneList);
		creditLoanDetailedOneEx.setCreditLoanDetailedTwoList(creditLoanDetailedTwoList);
		
		List<CreditCycleRecordEx> list = new ArrayList<CreditCycleRecordEx>();
		if(creditLoanDetailedOneList != null && creditLoanDetailedOneList.size() > 0){
			for(int w = 0; w < creditLoanDetailedOneList.size(); w++ ){
				CreditCycleRecordEx creditCycleRecordEx = getQsList(cycleResult,creditLoanDetailedOneList.get(w).getId());
				list.add(creditCycleRecordEx);
				
			}
		}
		creditLoanDetailedOneEx.setCreditCycleRecordExList(list);
		return creditLoanDetailedOneEx;
	}




	/**
	 * 封装期数（展示用）
	 * 2016年2月3日
	 * By 侯志斌 
	 * @param cycleResult 期数列表
	 * @param id 关联ID
	 * @return 期数扩展实体
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
						//1：判断返回值是否为null,如果是null置为“”
						if (methods[w].getName().equals("getQs"+cycleResult.get(i).getCycleNo()) && methods[w].invoke(creditCycleRecordExList) == null){
							String outmethod = "s" + methods[w].getName().substring(1, methods[w].getName().length());
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
	 * 2016年2月3日
	 * By 侯志斌
	 * @param CreditLoanDetailedOneEx 征信报告扩展类
	 * @param relationType 贷款一的期数类型
	 * @param oneLoanRelationId 贷款一的关联ID
	 * @return 是否保存成功
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public boolean saveData(CreditLoanDetailedOneEx creditLoanDetailedOneEx,String relationType,String oneLoanRelationId) throws IllegalAccessException, InvocationTargetException, IllegalArgumentException, SecurityException, NoSuchMethodException{
		List<CreditLoanDetailedOne> creditLoanDetailedOneList = creditLoanDetailedOneEx.getCreditLoanDetailedOneList(); // 贷款1信息
		List<CreditLoanDetailedTwo> creditLoanDetailedTwoList = creditLoanDetailedOneEx.getCreditLoanDetailedTwoList(); // 贷款2信息
		List<CreditCycleRecordEx> creditCycleRecordExList = creditLoanDetailedOneEx.getCreditCycleRecordExList(); // 期数
		
		CreditReportDetailed creditReportDetailed = new CreditReportDetailed();
		creditReportDetailed.setLoanCode(creditLoanDetailedOneEx.getLoanCode());
		creditReportDetailed.setDictCustomerType(creditLoanDetailedOneEx.getType());
		creditReportDetailed.setrCustomerCoborrowerId(creditLoanDetailedOneEx.getRelId());
		CreditReportDetailed detail = creditReportDetailedDao.getIdByParam(creditReportDetailed);
		
		if(creditLoanDetailedOneList != null && creditLoanDetailedOneList.size() > 0 && detail != null) {
			String relationId= detail.getId();
			for( int i = 0;i < creditLoanDetailedOneList.size(); i++ ){
				String id = creditLoanDetailedOneList.get(i).getId();
				// 如果有id 说明该数据在数据库已存在，则更新，否则，添加
				if(StringUtils.isNotEmpty(id)){
					CreditLoanDetailedOne creditLoanDetailedOne = new CreditLoanDetailedOne();
					BeanUtils.copyProperties(creditLoanDetailedOne,creditLoanDetailedOneList.get(i));
					creditLoanDetailedOne.preUpdate();
					creditLoanDetailedOne.setRelationId(relationId);
					creditLoanDetailedOneDao.updateData(creditLoanDetailedOne);
					// 贷款2
					CreditLoanDetailedTwo creditLoanDetailedTwo = new CreditLoanDetailedTwo();
					BeanUtils.copyProperties(creditLoanDetailedTwo,creditLoanDetailedTwoList.get(i));
					creditLoanDetailedTwo.preUpdate();
					creditLoanDetailedTwo.setRelationId(id);
					creditLoanDetailedTwoDao.updateByPrimaryKeySelective(creditLoanDetailedTwo);
					
					
					// 	更新期数（先删除再保存）
					CreditCycleRecord creditCycleRecord = new CreditCycleRecord();
					creditCycleRecord.setRelationId(id);
					creditCycleRecord.setRelationType(relationType);
					creditCycleRecordDao.deleteData(creditCycleRecord);
					// 保存期数
					if(creditCycleRecordExList != null && creditCycleRecordExList.get(i) != null){
						saveQs(creditCycleRecordExList.get(i),creditLoanDetailedOne.getId(),relationType);
					}
				}else{// 添加数据
					CreditLoanDetailedOne creditLoanDetailedOne = creditLoanDetailedOneList.get(i);
					creditLoanDetailedOne.setIsNewRecord(false);
					creditLoanDetailedOne.setRelationId(relationId);
					creditLoanDetailedOne.preInsert();
					creditLoanDetailedOneDao.insert(creditLoanDetailedOne);
					
					
					// 贷款2
					CreditLoanDetailedTwo creditLoanDetailedTwo = creditLoanDetailedTwoList.get(i);
					creditLoanDetailedTwo.setIsNewRecord(false);
					creditLoanDetailedTwo.setRelationId(creditLoanDetailedOne.getId());
					creditLoanDetailedTwo.preInsert();
					creditLoanDetailedTwoDao.insert(creditLoanDetailedTwo);
					
					// 保存期数
					if(creditLoanDetailedOneEx != null && creditCycleRecordExList.get(i) != null){
						saveQs(creditCycleRecordExList.get(i),creditLoanDetailedOne.getId(),relationType);
					}
				}
			}
		}
		return true;
	}
	
	
	/**
	 * 插入数据（贷款期数）
	 * 2016年2月3日
	 * By 侯志斌
	 * @param param
	 * @param paramId
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private void saveQs(CreditCycleRecordEx param , String paramId,String relationType) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		CreditCycleRecordEx creditCycleRecordEx =param;
		Method[] methods = creditCycleRecordEx.getClass().getMethods();
		for( int w = 0; w < methods.length; w++){
			// 1：判断返回值是否为null,如果是null置为“”
			if (methods[w].getName().startsWith("getQs") && methods[w].invoke(creditCycleRecordEx) == null){
				String outmethod = "s" + methods[w].getName().substring(1, methods[w].getName().length());
				creditCycleRecordEx.getClass().getMethod(outmethod, String.class).invoke(creditCycleRecordEx, "");
			}
			if (methods[w].getName().startsWith("getQs")){
				String num = methods[w].getName().substring(5, methods[w].getName().length());
				String methodsN = (String) methods[w].invoke(creditCycleRecordEx);
				CreditCycleRecord  toDb = new CreditCycleRecord();
				toDb.setRelationId(paramId);
				toDb.setRelationType(relationType);  // 贷款：1，信用卡：2，公积金：3
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
	 * By 侯志斌
	 * @param record
	 * @return 是否保存成功的数值
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public int deleteData(CreditLoanDetailedOne record){
		String relationType="1";
		int result = creditLoanDetailedTwoDao.deleteByRelationId(record.getId());
		CreditCycleRecord creditCycleRecord = new CreditCycleRecord();
		creditCycleRecord.setRelationId(record.getId());
		creditCycleRecord.setRelationType(relationType);
		result = creditCycleRecordDao.deleteData(creditCycleRecord);
		result =creditLoanDetailedOneDao.deleteByPrimaryKey(record.getId());
		return result;
	}
	
	
}

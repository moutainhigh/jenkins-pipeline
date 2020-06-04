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
import com.creditharmony.approve.credit.dao.CreditCardDetailedOneDao;
import com.creditharmony.approve.credit.dao.CreditCardDetailedTwoDao;
import com.creditharmony.approve.credit.dao.CreditCycleRecordDao;
import com.creditharmony.approve.credit.dao.CreditReportDetailedDao;
import com.creditharmony.approve.credit.entity.CreditCardDetailedOne;
import com.creditharmony.approve.credit.entity.CreditCardDetailedTwo;
import com.creditharmony.approve.credit.entity.CreditCycleRecord;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
import com.creditharmony.approve.credit.entity.ex.CreditCardDetailedOneEx;
import com.creditharmony.approve.credit.entity.ex.CreditCycleRecordEx;
import com.creditharmony.core.cache.DictCache;


/**
 * 信用卡明细一服务类
 * @Class Name CreditCardDetailedOneService
 * @author 侯志斌
 * @Create In 2016年01月11日
 */
@Service
public class CreditCardDetailedOneService {
	
	@Autowired
	CreditCardDetailedOneDao creditCardDetailedOneDao;
	
	@Autowired
	CreditCycleRecordDao creditCycleRecordDao;
	
	@Autowired
	CreditCardDetailedTwoDao creditCardDetailedTwoDao;
	
	@Autowired
	private CreditReportDetailedDao creditReportDetailedDao;
	

	/**
	 * 保存信用卡明细一
	 * 2016年02月02日
	 * By 侯志斌
	 * @param CreditCardDetailedOne
	 * @return String
	 */
	public String save(CreditCardDetailedOne model) {
		String id = model.getId();
		if(id == null || id.equals("")){
			model.preInsert();
			creditCardDetailedOneDao.insert(model);
			id = model.getId();
		}else{
			
			creditCardDetailedOneDao.updateByPrimaryKeySelective(model);
		}
		return id;
	}

	/**
	 * 展示数据
	 * 2016年2月3日
	 * By 侯志斌
	 * @param creditReportDetailed
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public CreditCardDetailedOneEx showData(CreditReportDetailed creditReportDetailed) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		List<CreditCardDetailedOne> creditCardDetailedOneList = creditCardDetailedOneDao.getAllByParam(creditReportDetailed);
		
		if(creditCardDetailedOneList == null || creditCardDetailedOneList.size() == 0 ||creditCardDetailedOneList.get(0)==null){
			return null;
		}
		
		for (CreditCardDetailedOne creditCardDetailedOne : creditCardDetailedOneList) {
			creditCardDetailedOne.setCardType(DictCache.getInstance().getDictLabel("jk_card_type", creditCardDetailedOne.getCardType()));
			creditCardDetailedOne.setGuaranteeType(DictCache.getInstance().getDictLabel("jk_credit_loan_type_flag", creditCardDetailedOne.getGuaranteeType()));
			creditCardDetailedOne.setCurrency(DictCache.getInstance().getDictLabel(DictionaryConstants.CREDIT_CARDINFO_CURRENCY, creditCardDetailedOne.getCurrency()));
		}
		
		
		//String relationId=creditCardDetailedOneList.get(0).getRelationId(); 
		
		/*Map<String,Object> filter1 = new HashMap<String, Object>();
		filter1.put("relationId", creditReportDetailed);
		List<CreditCardDetailedOne> creditCardDetailedOneList = creditCardDetailedOneDao.findByParams(filter1);
		
		if(creditCardDetailedOneList == null || creditCardDetailedOneList.size() == 0 ||creditCardDetailedOneList.get(0)==null){
			return null;
		}
		*/
		
		String relationId=creditCardDetailedOneList.get(0).getRelationId(); //信用卡一ID是信用卡二和期数的relationId
		
		Map<String,Object> filter = new HashMap<String, Object>();
		filter.put("relationId", relationId);
		filter.put("relationType", "2");
		List<CreditCycleRecord> cycleResult = creditCycleRecordDao.getCardAllByParam(filter);
		
		Map<String,Object> filter2 = new HashMap<String, Object>();
		filter2.put("relationId", relationId);
		List<CreditCardDetailedTwo> creditCardDetailedTwoList = creditCardDetailedTwoDao.findByParams(filter2);
		
		for (CreditCardDetailedTwo creditCardDetailedTwo : creditCardDetailedTwoList) {
			creditCardDetailedTwo.setAccountStatus(DictCache.getInstance().getDictLabel(DictionaryConstants.CREDIT_CARDINFO_ACCOUNTSTATUS, creditCardDetailedTwo.getAccountStatus()));
		}
		
		CreditCardDetailedOneEx creditCardDetailedOneEx = new CreditCardDetailedOneEx();
		creditCardDetailedOneEx.setCreditCardDetailedOneList(creditCardDetailedOneList);
		creditCardDetailedOneEx.setCreditCardDetailedTwoList(creditCardDetailedTwoList);
		
		List<CreditCycleRecordEx> list = new ArrayList<CreditCycleRecordEx>();
		if(creditCardDetailedOneList != null && creditCardDetailedOneList.size() > 0){
			for(int w = 0; w < creditCardDetailedOneList.size(); w++ ){
				CreditCycleRecordEx creditCycleRecordEx = getQsList(cycleResult,creditCardDetailedOneList.get(w).getId());
				list.add(creditCycleRecordEx);
				
			}
		}
		creditCardDetailedOneEx.setCreditCycleRecordExList(list);
		return creditCardDetailedOneEx;
	}




	/**
	 * 封装期数（展示用）
	 * 2016年2月3日
	 * By 侯志斌
	 * @param cycleResult
	 * @return
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
	 * 2016年2月3日
	 * By 侯志斌
	 * @param creditCardDetailedOneEx
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	@Transactional(value = "transactionManager", readOnly = false)
	public boolean saveData(CreditCardDetailedOneEx creditCardDetailedOneEx) throws IllegalAccessException, InvocationTargetException, IllegalArgumentException, SecurityException, NoSuchMethodException{
		if(creditCardDetailedOneEx != null){
			
			List<CreditCardDetailedOne> creditCardDetailedOneList = creditCardDetailedOneEx.getCreditCardDetailedOneList(); // 信用卡1信息
			List<CreditCardDetailedTwo> creditCardDetailedTwoList = creditCardDetailedOneEx.getCreditCardDetailedTwoList(); // 信用卡2信息
			List<CreditCycleRecordEx> creditCycleRecordExList = creditCardDetailedOneEx.getCreditCycleRecordExList(); // 期数
			
			CreditReportDetailed creditReportDetailed = new CreditReportDetailed();
			creditReportDetailed.setLoanCode(creditCardDetailedOneEx.getLoanCode());
			creditReportDetailed.setDictCustomerType(creditCardDetailedOneEx.getType());
			creditReportDetailed.setrCustomerCoborrowerId(creditCardDetailedOneEx.getRelId());
			CreditReportDetailed detail = creditReportDetailedDao.getIdByParam(creditReportDetailed);
			
			if(creditCardDetailedOneList != null && creditCardDetailedOneList.size() > 0){
				String relationId= detail.getId();
				for( int i = 0;i < creditCardDetailedOneList.size(); i++ ){
					String id = creditCardDetailedOneList.get(i).getId();
					// 如果有id 说明该数据在数据库已存在，则更新，否则，添加
					String relationType="2";//信用卡
					if(StringUtils.isNotEmpty(id)){
						CreditCardDetailedOne creditCardDetailedOne = new CreditCardDetailedOne();
						BeanUtils.copyProperties(creditCardDetailedOne,creditCardDetailedOneList.get(i));
						creditCardDetailedOne.preUpdate();
						creditCardDetailedOneDao.updateByPrimaryKeySelective(creditCardDetailedOne);
						//信用卡2
						CreditCardDetailedTwo creditCardDetailedTwo = new CreditCardDetailedTwo();
						BeanUtils.copyProperties(creditCardDetailedTwo,creditCardDetailedTwoList.get(i));
						creditCardDetailedTwo.preUpdate();
						creditCardDetailedTwoDao.updateByPrimaryKeySelective(creditCardDetailedTwo);
						
						// 	更新期数（先删除再保存）
						CreditCycleRecord creditCycleRecord = new CreditCycleRecord();
						creditCycleRecord.setRelationId(id);
						creditCycleRecord.setRelationType(relationType);
						creditCycleRecordDao.deleteData(creditCycleRecord);
						// 保存期数
						if(creditCycleRecordExList != null && creditCycleRecordExList.get(i) != null){
							saveQs(creditCycleRecordExList.get(i),creditCardDetailedOne.getId(),relationType);
						}
					}else{// 添加数据
						CreditCardDetailedOne creditCardDetailedOne = creditCardDetailedOneList.get(i);
						creditCardDetailedOne.setIsNewRecord(false);
						creditCardDetailedOne.setRelationId(relationId);//这个ID是传输过来的
						creditCardDetailedOne.preInsert();
						creditCardDetailedOneDao.insert(creditCardDetailedOne);
						
						String relastionId=creditCardDetailedOne.getId();
						//信用卡2
						CreditCardDetailedTwo creditCardDetailedTwo = creditCardDetailedTwoList.get(i);
						creditCardDetailedTwo.setIsNewRecord(false);
						creditCardDetailedTwo.setRelationId(relastionId);
						creditCardDetailedTwo.preInsert();
						creditCardDetailedTwoDao.insert(creditCardDetailedTwo);
						
						// 保存期数
						if(creditCycleRecordExList.get(i) != null){
							saveQs(creditCycleRecordExList.get(i),relastionId,relationType);
						}
					}
				}
			}
		}
		return true;
	}
	
	
	/**
	 * 插入数据（信用卡期数）
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
			//1：判断返回值是否为null,如果是null置为“”
			if (methods[w].getName().startsWith("getQs") && methods[w].invoke(creditCycleRecordEx) == null){
				String outmethod = "s"+methods[w].getName().substring(1, methods[w].getName().length());
				creditCycleRecordEx.getClass().getMethod(outmethod, String.class).invoke(creditCycleRecordEx, "");
			}
			if (methods[w].getName().startsWith("getQs")){
				String num = methods[w].getName().substring(5, methods[w].getName().length());
				String methodsN = (String) methods[w].invoke(creditCycleRecordEx);
				CreditCycleRecord  toDb = new CreditCycleRecord();
				toDb.setRelationId(paramId);
				toDb.setRelationType(relationType);  //daikuan：1，信用卡：2，公积金：3
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
	 * @return
	 */
	@Transactional(value = "transactionManager", readOnly = false)
	public int deleteData(CreditCardDetailedOne record){
		String relationType="1";
		
		int result = creditCardDetailedOneDao.deleteByPrimaryKey(record.getId());
		result = creditCardDetailedTwoDao.deleteByRelationId(record.getId());
		CreditCycleRecord creditCycleRecord = new CreditCycleRecord();
		creditCycleRecord.setRelationId(record.getId());
		creditCycleRecord.setRelationType(relationType);
		result = creditCycleRecordDao.deleteData(creditCycleRecord);
		return result;
	}
	
	
   
}

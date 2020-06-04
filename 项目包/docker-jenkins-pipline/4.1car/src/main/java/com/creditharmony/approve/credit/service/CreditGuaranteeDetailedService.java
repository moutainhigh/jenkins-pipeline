package com.creditharmony.approve.credit.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.credit.dao.CreditGuaranteeDetailedDao;
import com.creditharmony.approve.credit.dao.CreditReportDetailedDao;
import com.creditharmony.approve.credit.entity.CreditGuaranteeDetailed;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
import com.creditharmony.approve.credit.entity.ex.CreditGuaranteeDetailedEx;



/**
 * 担保明细一服务类
 * @Class Name CreditLoanDetailedOneService
 * @author 侯志斌
 * @Create In 2016年01月11日
 */
@Service
public class CreditGuaranteeDetailedService {
	
	@Autowired
	CreditGuaranteeDetailedDao creditGuaranteeDetailedDao;
	
	@Autowired
	private CreditReportDetailedDao creditReportDetailedDao;
	
	/**
	 * 展示数据
	 * 2016年2月3日
	 * By 侯志斌
	 * @param creditReportDetailed 征信实体
	 * @return CreditGuaranteeDetailedEx 担保扩展实体
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public CreditGuaranteeDetailedEx showData(CreditReportDetailed creditReportDetailed) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		List<CreditGuaranteeDetailed> creditGuaranteeDetailedList = creditGuaranteeDetailedDao.getAllByParam(creditReportDetailed);
		
		if(creditGuaranteeDetailedList == null || creditGuaranteeDetailedList.size() == 0 ||creditGuaranteeDetailedList.get(0)==null){
			return null;
		}
		CreditGuaranteeDetailedEx creditGuaranteeDetailedEx=new CreditGuaranteeDetailedEx();
		creditGuaranteeDetailedEx.setCreditGuaranteeDetailedList(creditGuaranteeDetailedList);
		return creditGuaranteeDetailedEx;
	}

	/**
	 * 删除数据
	 * 2016年2月2日
	 * By 侯志斌
	 * @param record 担保实体
	 * @return 操作结果
	 */
	@Transactional(value = "transactionManager", readOnly = false)
	public int deleteData(CreditGuaranteeDetailed record){
		int result = creditGuaranteeDetailedDao.deleteByPrimaryKey(record.getId());
		return result;
	}


	

	/**
	 * 保存数据
	 * 2016年2月3日
	 * By 侯志斌
	 * @param creditLoanDetailedOneEx 贷款一扩展实体
	 * @param relationId 关联ID
	 * @return 操作结果
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	@Transactional(value = "transactionManager", readOnly = false)
	public boolean saveData(CreditGuaranteeDetailedEx creditGuaranteeDetailedEx) throws IllegalAccessException, InvocationTargetException, IllegalArgumentException, SecurityException, NoSuchMethodException{

		List<CreditGuaranteeDetailed> creditGuaranteeDetailedList = creditGuaranteeDetailedEx.getCreditGuaranteeDetailedList(); 
		
		CreditReportDetailed creditReportDetailed = new CreditReportDetailed();
		creditReportDetailed.setLoanCode(creditGuaranteeDetailedEx.getLoanCode());
		creditReportDetailed.setDictCustomerType(creditGuaranteeDetailedEx.getType());
		creditReportDetailed.setrCustomerCoborrowerId(creditGuaranteeDetailedEx.getRelId());
		CreditReportDetailed detail = creditReportDetailedDao.getIdByParam(creditReportDetailed);
		
		if(creditGuaranteeDetailedList != null && creditGuaranteeDetailedList.size() > 0){
			String relationId= detail.getId();
			for( int i = 0;i < creditGuaranteeDetailedList.size(); i++ ){
				String id = creditGuaranteeDetailedList.get(i).getId();
				// 如果有id 说明该数据在数据库已存在，则更新，否则，添加
				if(StringUtils.isNotEmpty(id)){
					CreditGuaranteeDetailed creditGuaranteeDetailed = new CreditGuaranteeDetailed();
					BeanUtils.copyProperties(creditGuaranteeDetailed,creditGuaranteeDetailedList.get(i));
					creditGuaranteeDetailed.preUpdate();
					creditGuaranteeDetailed.setCustomerCertNum(detail.getCertNo());
					creditGuaranteeDetailed.setGuaranteedName(detail.getName());
					creditGuaranteeDetailedDao.updateByPrimaryKeySelective(creditGuaranteeDetailed);
				}else{// 添加数据
					CreditGuaranteeDetailed creditGuaranteeDetailed = creditGuaranteeDetailedList.get(i);
					creditGuaranteeDetailed.setIsNewRecord(false);
					creditGuaranteeDetailed.setRelationId(relationId); 
					creditGuaranteeDetailed.setCustomerCertNum(detail.getCertNo());
					creditGuaranteeDetailed.setGuaranteedName(detail.getName());
					creditGuaranteeDetailed.preInsert();
					creditGuaranteeDetailedDao.insert(creditGuaranteeDetailed);
				}
			}
		}
		return true;
	}
	


}

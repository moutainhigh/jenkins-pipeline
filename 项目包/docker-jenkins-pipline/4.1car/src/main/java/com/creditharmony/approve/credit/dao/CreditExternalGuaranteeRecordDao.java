package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditExternalGuaranteeRecord;

/**
 * @Class Name CreditExternalGuaranteeRecordDao
 * @author 王浩
 * @Create In 2016年2月23日
 */
@LoanBatisDao
public interface CreditExternalGuaranteeRecordDao extends CrudDao<CreditExternalGuaranteeRecord>{
    
	/**
	 * 2016年2月23日
	 * By 王浩
	 * @param id
	 * @return 
	 */
	public int deleteByPrimaryKey(String id);	
	
	/**
	 * 2016年2月23日
	 * By 王浩
	 * @param guarantee
	 * @return 
	 */
	public List<CreditExternalGuaranteeRecord> selectByGuaranteeInfo(CreditExternalGuaranteeRecord guarantee);
	
	/**
	 * 2016年2月23日
	 * By 王浩
	 * @param id
	 * @return 
	 */
	public int deleteByLoanCode(String loanCode);
	
    /**
     * 2016年2月23日
     * By 王浩
     * @param record
     * @return 
     */
    public int insertExternalGuarantee(CreditExternalGuaranteeRecord record);

    int insertSelective(CreditExternalGuaranteeRecord record);

    CreditExternalGuaranteeRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditExternalGuaranteeRecord record);

    int updateByPrimaryKey(CreditExternalGuaranteeRecord record);
    
}
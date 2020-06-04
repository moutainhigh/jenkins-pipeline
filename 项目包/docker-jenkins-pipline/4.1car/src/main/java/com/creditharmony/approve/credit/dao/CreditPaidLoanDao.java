package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditPaidLoan;

/**
 * @Class Name CreditPaidLoanDao
 * @author 王浩
 * @Create In 2016年2月24日
 */
@LoanBatisDao
public interface CreditPaidLoanDao extends CrudDao<CreditPaidLoan>{
	
    /**
     * 2016年2月24日
     * By 王浩
     * @param id
     * @return 
     */
	public int deleteByPrimaryKey(String id);
    
    /**
     * 2016年2月24日
     * By 王浩
     * @param id
     * @return 
     */
    public int deleteByPaidLoanInfo(CreditPaidLoan record);
    
    /**
     * 2016年2月24日
     * By 王浩
     * @param record
     * @return 
     */
    public List<CreditPaidLoan> selectByPaidLoanInfo(CreditPaidLoan record);
    
    /**
     * 2016年2月24日
     * By 王浩
     * @param record
     * @return 
     */
    public int insertPaidLoan(CreditPaidLoan record);

    public int insertSelective(CreditPaidLoan record);
    
    CreditPaidLoan selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditPaidLoan record);

    int updateByPrimaryKey(CreditPaidLoan record);
}
package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditLoanCard;

/**
 * 企业征信-贷款卡Dao
 * @Class Name CreditLoanCardDao
 * @author 王浩
 * @Create In 2016年2月23日
 */
@LoanBatisDao
public interface CreditLoanCardDao extends CrudDao<CreditLoanCard>{
	
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
     * @param record
     * @return 
     */
    public int insertCreditLoanCard(CreditLoanCard record);

    int insertSelective(CreditLoanCard record);

    CreditLoanCard selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditLoanCard record);

    int updateByPrimaryKey(CreditLoanCard record);
    
    /**
     * 2016年2月23日
     * By 王浩
     * @param creditLoanCard
     * @return 
     */
    public List<CreditLoanCard> selectByLoanCardInfo(CreditLoanCard creditLoanCard);
    
    /**
     * 2016年2月23日
     * By 王浩
     * @param loanCode
     * @return 
     */
    public int deleteByLoanCode(String loanCode);
    
}
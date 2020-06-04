package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditUnclearedFactoring;

/**
 * 企业征信_未结清保理
 * @Class Name CreditUnclearedLoanDao
 * @author zhanghu
 * @Create In 2016年2月19日
 */
@LoanBatisDao
public interface CreditUnclearedFactoringDao extends CrudDao<CreditUnclearedFactoring>{
    
    /**
     * 根据借款编号查询
     * 2016年2月23日
     * By 李文勇
     * @param loanCode
     * @return 返回负债历史变化list
     */
    public List<CreditUnclearedFactoring> selectByLoanCode(String loanCode);
    
	/**
	 * 根据id删除信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param id 
	 * @return 执行条数
	 */
	public int deleteByPrimaryKey(String id);
	
	/**
	 * 新增信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 
	 * @return 执行条数
	 */
	public int insertCreditUnclearedFactoring(CreditUnclearedFactoring record);
    
	/**
	 * 根据借款编码删除信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param loanCode 借款编码
	 * @return 执行条数
	 */
	public int deleteByLoanCode(String loanCode);
	
}
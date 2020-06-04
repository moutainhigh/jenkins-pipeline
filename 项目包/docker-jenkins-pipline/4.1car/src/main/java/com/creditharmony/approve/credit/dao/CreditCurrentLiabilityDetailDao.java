package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditCurrentLiabilityDetail;

/**
 * 企业征信_当前负债信息明细
 * @Class Name CreditCurrentLiabilityDetailDao
 * @author 李文勇
 * @Create In 2016年2月24日
 */
@LoanBatisDao
public interface CreditCurrentLiabilityDetailDao extends CrudDao<CreditCurrentLiabilityDetail>{

	/**
	 * 根据借款编号查询
	 * 2016年2月24日
	 * By 李文勇
	 * @param LoanCode
	 * @return 结果集list
	 */
	public List<CreditCurrentLiabilityDetail> getByLoanCode(String loanCode);
	
	/**
	 * 更新信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 
	 * @return 执行条数
	 */
    public int updateByPrimaryKeySelective(CreditCurrentLiabilityDetail record);
    
	/**
	 * 新增信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 
	 * @return 执行条数
	 */
    public int insertCreditCurrentLiabilityDetail(CreditCurrentLiabilityDetail record);
    
}
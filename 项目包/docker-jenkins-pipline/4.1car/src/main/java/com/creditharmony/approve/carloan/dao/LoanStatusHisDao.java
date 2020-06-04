package com.creditharmony.approve.carloan.dao;

import com.creditharmony.approve.carloan.entity.LoanStatusHis;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 获取审批历史
 * @Class Name LoanStatusHisDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface LoanStatusHisDao extends CrudDao<LoanStatusHis> {
	
	/**
	 * 根据loanStatusHis获取审批历史
	 * 2016年1月25日
	 * By 申诗阔
	 * @param loanStatusHis
	 * @return 审批历史
	 */
	public PageList<LoanStatusHis> findByLoanCode(LoanStatusHis loanStatusHis);
	
	/**
	 * 插入借款状态变更历史表
	 * 2016年1月28日
	 * By 申诗阔
	 * @param loanStatusHis
	 * @return 
	 */
	public int insertLoanStatusHis(LoanStatusHis loanStatusHis);
}
package com.creditharmony.approve.newCar.dao;

import com.creditharmony.approve.newCar.entity.NewLoanStatusHis;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 获取审批历史
 * @Class Name LoanStatusHisDao
 * @author 李高远
 * @Create In 2017年4月12日
 */
@LoanBatisDao
public interface NewLoanStatusHisDao extends CrudDao<NewLoanStatusHis> {
	
	/**
	 * 根据loanStatusHis获取审批历史
	 * 2017年4月12日
	 * By 李高远
	 * @param loanStatusHis
	 * @return 审批历史
	 */
	public PageList<NewLoanStatusHis> findByLoanCode(NewLoanStatusHis loanStatusHis);
	
	/**
	 * 插入借款状态变更历史表
	 * 2016年1月28日
	 * By 申诗阔
	 * @param loanStatusHis
	 * @return 
	 */
	public int insertLoanStatusHis(NewLoanStatusHis loanStatusHis);
}
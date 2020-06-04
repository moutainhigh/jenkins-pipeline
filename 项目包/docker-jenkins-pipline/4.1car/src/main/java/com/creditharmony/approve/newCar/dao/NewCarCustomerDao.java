package com.creditharmony.approve.newCar.dao;



import com.creditharmony.approve.newCar.entity.NewLoanCustomerEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 主借人dao
 * @Class Name LoanCustomerDao
 * @author 刘燕军
 * @Create In 2015年12月5日
 */
@LoanBatisDao
public interface NewCarCustomerDao extends CrudDao<NewLoanCustomerEx>{
	/**
	 * 根据loanCode获取客户信息（车借）
	 * 2016年4月8日
	 * By 申诗阔
	 * @param loanCode
	 * @return
	 */
	public NewLoanCustomerEx findCustomerInfo(String loanCode);
    
}
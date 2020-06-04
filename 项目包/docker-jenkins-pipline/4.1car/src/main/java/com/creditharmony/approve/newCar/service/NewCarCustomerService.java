package com.creditharmony.approve.newCar.service;

import com.creditharmony.approve.newCar.entity.NewLoanCustomerEx;

public interface NewCarCustomerService{

	/**
	 * 根据loanCode获取客户信息（车借）
	 * 2016年4月8日
	 * By 申诗阔
	 * @param loanCode
	 * @return
	 */
	public NewLoanCustomerEx findCustomerInfo(String loanCode);
}

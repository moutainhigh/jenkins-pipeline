package com.creditharmony.approve.newCar.service;

import com.creditharmony.approve.newCar.entity.NewVehicleInfo;
public interface NewVehicleInfoService{
	
	/**
	 * 根据借款编号loanCode获取车辆信息
	 * 2016年1月26日
	 * By 申诗阔
	 * @param loanCode
	 * @return 车辆信息
	 */
    public NewVehicleInfo findByLoanCode(String loanCode);
}
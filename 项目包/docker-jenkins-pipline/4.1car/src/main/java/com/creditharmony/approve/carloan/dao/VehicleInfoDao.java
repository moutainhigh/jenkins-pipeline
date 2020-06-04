package com.creditharmony.approve.carloan.dao;

import com.creditharmony.approve.carloan.entity.VehicleInfo;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 获取车辆信息
 * @Class Name VehicleInfoDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface VehicleInfoDao {
	
	/**
	 * 根据借款编号loanCode获取车辆信息
	 * 2016年1月26日
	 * By 申诗阔
	 * @param loanCode
	 * @return 车辆信息
	 */
    public VehicleInfo findByLoanCode(String loanCode);
}
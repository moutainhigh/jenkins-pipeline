package com.creditharmony.approve.newCar.service;

import java.util.List;

import com.creditharmony.approve.newCar.entity.NewCarLoanInfo;
import com.creditharmony.core.persistence.Page;

public interface NewCarInfoService{
	
	
	/**
	 * 通过借款编码loanCode获取借款信息
	 * 2017年3月30日
	 * By 李高远
	 * @param loanCode
	 * @return
	 */
	public NewCarLoanInfo findByLoanCode(String loanCode);

    /**
     * 根据applyId获取借款信息
     * 2016年1月25日
     * By 申诗阔
     * @param applyId
     * @return 借款信息
     */
	public NewCarLoanInfo findByApplyId(String applyId);

	/**
	 * 复审 --更新借款状态 
	 * By 李高远
	 * 2017年3月29日
	 * @param carLoanInfo
	 */
	public void updateCarLoanStatus(NewCarLoanInfo carLoanInfo);

	public Page<NewCarLoanInfo> getOrderByLoanStatus(
			Page<NewCarLoanInfo> page, List<String> statusList);

	/**
	 * 取单后更新借款标识
	 * By 李高远
	 * 2017年4月5日
	 * @param loanCode
	 */
	public void updateLoanFlag(NewCarLoanInfo carLoanInfo);

	public void updateQueueName(NewCarLoanInfo carInfo);

}

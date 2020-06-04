package com.creditharmony.approve.newCar.service;

import com.creditharmony.approve.newCar.entity.NewCarContract;
import com.creditharmony.approve.newCar.entity.NewCarExamineEntity;
import com.creditharmony.approve.newCar.entity.NewCarLoanInfo;
import com.creditharmony.core.persistence.Page;

public interface NewCarContractService{

	/**
	 * 根据展期记录的loanCode获取其紧邻上一次的记录相应对的合同信息，借以获取审批金额
	 * 2016年3月26日
	 * By 申诗阔
	 * @param loanCode
	 * @return
	 */
	public NewCarContract getLastByLoanCodeOfExtend(String loanCode);
}

package com.creditharmony.approve.carloan.dao;

import com.creditharmony.approve.carloan.entity.CarContract;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

@LoanBatisDao
public interface CarContractDao extends CrudDao<CarContract> {
	/**
	 * 根据借款编号获取合同
	 * 2016年3月26日
	 * By 申诗阔
	 * @param loanCode
	 * @return 合同
	 */
	public CarContract selectByLoanCode(String loanCode);

	/**
	 * 根据展期记录的loanCode获取其紧邻上一次的记录相应对的合同信息，借以获取审批金额
	 * 2016年3月26日
	 * By 申诗阔
	 * @param loanCode
	 * @return
	 */
	public CarContract getLastByLoanCodeOfExtend(String loanCode);
}
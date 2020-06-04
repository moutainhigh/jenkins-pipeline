package com.creditharmony.approve.verify.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.verify.entity.LoanMate;

@LoanBatisDao
public interface LoanMateDao extends CrudDao<LoanMate>{

	/**
	 * 根据借款编号查询配偶数据
	 * 2016年2月23日
	 * By 李文勇
	 * @param param
	 * @return 返回结果集
	 */
	public List<LoanMate> getByLoanCode(LoanMate param);
	
	/**
	 * 根据借款编号和
	 * 2016年3月1日
	 * By 李文勇
	 * @param param
	 * @return 返回结果
	 */
	public LoanMate viewGetByLoanCode(LoanMate param);
}

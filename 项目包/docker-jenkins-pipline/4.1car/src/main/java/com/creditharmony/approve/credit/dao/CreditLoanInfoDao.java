package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditLoanInfo;

/**
 * 简版贷款信息
 * @Class Name CreditLoanInfoDao
 * @author 李文勇
 * @Create In 2015年12月31日
 */
@LoanBatisDao
public interface CreditLoanInfoDao extends CrudDao<CreditLoanInfo> {
	
	/**
	 * 根据简版贷款信息id删除简版贷款信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param id 简版贷款信息id
	 * @return 执行条数
	 */
    int deleteByPrimaryKey(String id);
	/**
	 * 新增简版贷款信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 简版贷款信息
	 * @return 执行条数
	 */
    int insertCreditLoanInfo(CreditLoanInfo record);

	/**
     * 根据贷款信息检索贷款信息List
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 贷款信息List
     */
    List<CreditLoanInfo> selectByCreditLoanInfo(CreditLoanInfo record);

	/**
	 * 根据个人征信简版id删除贷款信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param relationId 个人征信简版id
	 * @return 执行条数
	 */
	int deleteByRelationId(String relationId);
	
}

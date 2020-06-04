package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditExternalSecurityInfo;

/**
 * 企业征信_对外担保信息概要
 * @Class Name CreditExternalSecurityInfoDao
 * @author zhanghu
 * @Create In 2016年2月19日
 */
@LoanBatisDao
public interface CreditExternalSecurityInfoDao extends CrudDao<CreditExternalSecurityInfo> {
 	
	
	/**
	 * 根据借款编号查询
	 * 2016年2月24日
	 * By zhanghu
	 * @param LoanCode
	 * @return 结果集list
	 */
	public List<CreditExternalSecurityInfo> selectByLoanCode(String loanCode);
	
	/**
	 * 更新信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 
	 * @return 执行条数
	 */
    public int updateByPrimaryKeySelective(CreditExternalSecurityInfo record);
    
	/**
	 * 新增信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 
	 * @return 执行条数
	 */
    public int insertCreditExternalSecurityInfo(CreditExternalSecurityInfo record);
    
}
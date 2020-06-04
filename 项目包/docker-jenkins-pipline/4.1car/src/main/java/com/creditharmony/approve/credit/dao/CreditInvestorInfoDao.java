package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditInvestorInfo;

/**
 * 企业征信_出资人信息
 * @Class Name CreditInvestorInfoDao
 * @author zhanghu
 * @Create In 2016年2月19日
 */
@LoanBatisDao
public interface CreditInvestorInfoDao extends CrudDao<CreditInvestorInfo> {
    
	/**
	 * 根据出资人信息id删除出资人信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param id 出资人信息id
	 * @return 执行条数
	 */
	public int deleteByPrimaryKey(String id);
	
	/**
	 * 新增出资人信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 出资人信息
	 * @return 执行条数
	 */
	public int insertCreditInvestorInfo(CreditInvestorInfo record);
    
    /**
     * 根据借款编码检索出资人信息List
     * 2016年2月2日
     * By zhanghu
     * @param loanCode 借款编码
     * @return 出资人信息List
     */
	public List<CreditInvestorInfo> selectByLoanCode(String loanCode);

	/**
	 * 根据借款编码删除出资人信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param loanCode 借款编码
	 * @return 执行条数
	 */
	public int deleteByLoanCode(String loanCode);
	
}
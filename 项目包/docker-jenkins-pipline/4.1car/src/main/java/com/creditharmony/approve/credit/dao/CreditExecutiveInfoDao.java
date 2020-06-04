package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditExecutiveInfo;

/**
 * 企业征信_高管人员信息
 * @Class Name CreditExecutiveInfoDao
 * @author zhanghu
 * @Create In 2016年2月19日
 */
@LoanBatisDao
public interface CreditExecutiveInfoDao extends CrudDao<CreditExecutiveInfo> {
    
	/**
	 * 根据高管人员信息id删除高管人员信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param id 高管人员信息id
	 * @return 执行条数
	 */
	public int deleteByPrimaryKey(String id);
	
	/**
	 * 新增高管人员信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 高管人员信息
	 * @return 执行条数
	 */
	public int insertCreditExecutiveInfo(CreditExecutiveInfo record);
    
    /**
     * 根据借款编码检索高管人员信息List
     * 2016年2月2日
     * By zhanghu
     * @param loanCode 借款编码
     * @return 高管人员信息List
     */
	public List<CreditExecutiveInfo> selectByLoanCode(String loanCode);

	/**
	 * 根据借款编码删除高管人员信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param loanCode 借款编码
	 * @return 执行条数
	 */
	public int deleteByLoanCode(String loanCode);
	
}
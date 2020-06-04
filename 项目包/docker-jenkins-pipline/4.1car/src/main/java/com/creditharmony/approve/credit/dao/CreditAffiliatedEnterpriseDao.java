package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditAffiliatedEnterprise;

/**
 * 企业征信_直接关联企业
 * @Class Name CreditAffiliatedEnterpriseDao
 * @author zhanghu
 * @Create In 2016年2月19日
 */
@LoanBatisDao
public interface CreditAffiliatedEnterpriseDao extends CrudDao<CreditAffiliatedEnterprise> {
    
	/**
	 * 根据直接关联企业信息id删除直接关联企业信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param id 直接关联企业信息id
	 * @return 执行条数
	 */
	public int deleteByPrimaryKey(String id);
	
	/**
	 * 新增直接关联企业信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 直接关联企业信息
	 * @return 执行条数
	 */
	public int insertCreditAffiliatedEnterprise(CreditAffiliatedEnterprise record);
    
    /**
     * 根据借款编码检索直接关联企业信息List
     * 2016年2月2日
     * By zhanghu
     * @param loanCode 借款编码
     * @return 直接关联企业信息List
     */
	public List<CreditAffiliatedEnterprise> selectByLoanCode(String loanCode);

	/**
	 * 根据借款编码删除直接关联企业信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param loanCode 借款编码
	 * @return 执行条数
	 */
	public int deleteByLoanCode(String loanCode);
	
}
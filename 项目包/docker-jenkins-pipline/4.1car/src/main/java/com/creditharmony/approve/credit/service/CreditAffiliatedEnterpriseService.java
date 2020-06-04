package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.CreditAffiliatedEnterpriseDao;
import com.creditharmony.approve.credit.entity.CreditAffiliatedEnterprise;

/**
 * 直接关联企业信息Service
 * @Class Name CreditAffiliatedEnterpriseService
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Service
public class CreditAffiliatedEnterpriseService extends  CoreManager<CreditAffiliatedEnterpriseDao,CreditAffiliatedEnterprise> {

	/**
	 * 根据直接关联企业信息id删除直接关联企业信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param id 直接关联企业信息id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteByPrimaryKey(String id) {
		return this.dao.deleteByPrimaryKey(id);
	}
	
	/**
	 * 新增直接关联企业信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 直接关联企业信息
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int insertCreditAffiliatedEnterprise(CreditAffiliatedEnterprise record) {
		// 初始化默认数据
		record.preInsert();
		return this.dao.insertCreditAffiliatedEnterprise(record);
	}
    
    /**
     * 根据借款编码检索直接关联企业信息List
     * 2016年2月2日
     * By zhanghu
     * @param loanCode 借款编码
     * @return 直接关联企业信息List
     */
	public List<CreditAffiliatedEnterprise> selectByLoanCode(String loanCode) {
		return this.dao.selectByLoanCode(loanCode);
	}

	/**
	 * 根据借款编码删除直接关联企业信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param loanCode 借款编码
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteByLoanCode(String loanCode) {
		return this.dao.deleteByLoanCode(loanCode);
	}
	
}

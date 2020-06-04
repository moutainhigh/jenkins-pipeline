package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.CreditUnclearedLoanDao;
import com.creditharmony.approve.credit.entity.CreditUnclearedLoan;

/**
 * 企业征信_未结清贷款Service
 * @Class Name CreditUnclearedLoanService
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Service
public class CreditUnclearedLoanService extends  CoreManager<CreditUnclearedLoanDao,CreditUnclearedLoan> {

	/**
	 * 根据id删除信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param id 
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteByPrimaryKey(String id) {
		return this.dao.deleteByPrimaryKey(id);
	}
	
	/**
	 * 新增信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int insertCreditUnclearedLoan(CreditUnclearedLoan record) {
		// 初始化默认数据
		record.preInsert();
		return this.dao.insertCreditUnclearedLoan(record);
	}
    
    /**
     * 根据借款编码检索信息List
     * 2016年2月2日
     * By zhanghu
     * @param loanCode 借款编码
     * @return 信息List
     */
	public List<CreditUnclearedLoan> selectByLoanCode(String loanCode) {
		return this.dao.selectByLoanCode(loanCode);
	}

	/**
	 * 根据借款编码删除信息
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

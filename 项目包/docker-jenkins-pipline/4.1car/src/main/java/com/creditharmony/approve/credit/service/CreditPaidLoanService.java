package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.CreditPaidLoanDao;
import com.creditharmony.approve.credit.entity.CreditPaidLoan;

/**
 * 企业征信_已结清信贷Service
 * @Class Name CreditPunishService
 * @author 王浩
 * @Create In 2016年2月22日
 */
@Service
public class CreditPaidLoanService extends  CoreManager<CreditPaidLoanDao,CreditPaidLoan> {
	
	/**
	 * 根据关联loanCode查询已结清信贷信息
	 * 2016年2月22日
	 * By 王浩
	 * @param loanCode
	 * @return 
	 */
	public List<CreditPaidLoan> selectByPaidLoanInfo(CreditPaidLoan paidLoan) {
		return this.dao.selectByPaidLoanInfo(paidLoan);
	}

	/**
	 * 保存已结清信贷信息
	 * 2016年2月22日
	 * By 王浩
	 * @param creditPunish
	 * @return 保存记录条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int savePaidLoan(CreditPaidLoan paidLoan) {
		paidLoan.preInsert();
		return this.dao.insertPaidLoan(paidLoan);
	}

	/**
	 * 根据id删除已结清信贷信息
	 * 2016年2月22日
	 * By 王浩
	 * @param id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deletePaidLoanById(String id) {
		return this.dao.deleteByPrimaryKey(id);
	}	
	
	/**
	 * 根据loanCode删除相应已结清信贷信息
	 * 2016年2月22日
	 * By 王浩
	 * @param loanCode
	 * @return 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public int deleteByPaidLoanInfo(CreditPaidLoan paidLoan) {
		return this.dao.deleteByPaidLoanInfo(paidLoan);
	}	

}

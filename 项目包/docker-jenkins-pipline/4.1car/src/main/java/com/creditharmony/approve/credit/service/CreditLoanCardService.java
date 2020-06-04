package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.CreditLoanCardDao;
import com.creditharmony.approve.credit.entity.CreditLoanCard;

/**
 * 企业征信_贷款卡Service
 * @Class Name CreditLoanCardService
 * @author 王浩
 * @Create In 2016年2月22日
 */
@Service
public class CreditLoanCardService extends  CoreManager<CreditLoanCardDao,CreditLoanCard> {
	
	/**
	 * 根据关联id查询相应记录
	 * 2016年2月22日
	 * By 王浩
	 * @param loanCode
	 * @return 
	 */
	public List<CreditLoanCard> selectByLoanCode(String loanCode) {
		CreditLoanCard loanCardInfo = new CreditLoanCard();
		loanCardInfo.setLoanCode(loanCode);
		return this.dao.selectByLoanCardInfo( loanCardInfo);
	}

	/**
	 * 保存贷款卡信息
	 * 2016年2月22日
	 * By 王浩
	 * @param creditLoanCard
	 * @return 保存记录条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int saveCreditLoanCard(CreditLoanCard creditLoanCard) {
		creditLoanCard.preInsert();
		return this.dao.insertCreditLoanCard(creditLoanCard);
	}

	/**
	 * 根据id删除贷款卡信息
	 * 2016年2月22日
	 * By 王浩
	 * @param id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteLoanCardById(String id) {
		return this.dao.deleteByPrimaryKey(id);
	}	
	
	/**
	 * 根据loanCode删除贷款卡信息
	 * 2016年2月22日
	 * By 王浩
	 * @param loanCode
	 * @return 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public int deleteByRelationId(String loanCode) {
		return this.dao.deleteByLoanCode(loanCode);
	}	

}

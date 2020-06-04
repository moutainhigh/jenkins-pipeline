package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.CreditPunishDao;
import com.creditharmony.approve.credit.entity.CreditPunish;

/**
 * 企业征信_处罚Service
 * @Class Name CreditPunishService
 * @author 王浩
 * @Create In 2016年2月22日
 */
@Service
public class CreditPunishService extends  CoreManager<CreditPunishDao,CreditPunish> {
	
	/**
	 * 根据关联loanCode查询借款处罚信息
	 * 2016年2月22日
	 * By 王浩
	 * @param loanCode
	 * @return 
	 */
	public List<CreditPunish> selectByLoanCode(String loanCode) {
		CreditPunish punishInfo = new CreditPunish();
		punishInfo.setLoanCode(loanCode);
		return this.dao.selectByPunishInfo(punishInfo);
	}

	/**
	 * 保存处罚信息
	 * 2016年2月22日
	 * By 王浩
	 * @param creditPunish
	 * @return 保存记录条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int saveCreditPunish(CreditPunish creditPunish) {
		creditPunish.preInsert();
		return this.dao.insertCreditPunish(creditPunish);
	}

	/**
	 * 根据id删除处罚信息
	 * 2016年2月22日
	 * By 王浩
	 * @param id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deletePunishById(String id) {
		return this.dao.deleteByPrimaryKey(id);
	}	
	
	/**
	 * 根据loanCode删除相应处罚信息
	 * 2016年2月22日
	 * By 王浩
	 * @param loanCode
	 * @return 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public int deleteByRelationId(String loanCode) {
		return this.dao.deletePunishByLoanCode(loanCode);
	}	

}

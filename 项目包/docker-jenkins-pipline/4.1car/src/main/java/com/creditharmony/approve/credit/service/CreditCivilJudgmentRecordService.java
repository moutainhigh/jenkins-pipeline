package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.CreditCivilJudgmentRecordDao;
import com.creditharmony.approve.credit.entity.CreditCivilJudgmentRecord;

/**
 * 企业征信_法院判决Service
 * @Class Name CreditCivilJudgmentRecordService
 * @author 王浩
 * @Create In 2016年2月22日
 */
@Service
public class CreditCivilJudgmentRecordService extends  CoreManager<CreditCivilJudgmentRecordDao,CreditCivilJudgmentRecord> {
	
	/**
	 * 根据关联loanCode查询判决信息
	 * 2016年2月22日
	 * By 王浩
	 * @param loanCode
	 * @return 
	 */
	public List<CreditCivilJudgmentRecord> selectByLoanCode(String loanCode) {
		CreditCivilJudgmentRecord civilJudgment = new CreditCivilJudgmentRecord();
		civilJudgment.setLoanCode(loanCode);
		return this.dao.selectByJudgmentInfo(civilJudgment);
	}

	/**
	 * 保存法院判决信息
	 * 2016年2月22日
	 * By 王浩
	 * @param civilJudgment
	 * @return 保存记录条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int saveCivilJudgment(CreditCivilJudgmentRecord civilJudgment) {
		civilJudgment.preInsert();
		return this.dao.insertCivilJudgment(civilJudgment);
	}

	/**
	 * 根据id删除法院判决信息
	 * 2016年2月22日
	 * By 王浩
	 * @param id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteJudgeById(String id) {
		return this.dao.deleteByPrimaryKey(id);
	}	
	
	/**
	 * 根据loanCode删除法院判决记录
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

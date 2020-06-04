package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.CreditExternalGuaranteeRecordDao;
import com.creditharmony.approve.credit.entity.CreditExternalGuaranteeRecord;

/**
 * 企业征信_外部担保Service
 * @Class Name CreditExternalGuaranteeRecordService
 * @author 王浩
 * @Create In 2016年2月22日
 */
@Service
public class CreditExternalGuaranteeRecordService extends  CoreManager<CreditExternalGuaranteeRecordDao,CreditExternalGuaranteeRecord> {
	
	/**
	 * 根据
	 * 2016年2月22日
	 * By 王浩
	 * @param loanCode
	 * @return 
	 */
	public List<CreditExternalGuaranteeRecord> selectByLoanCode(String loanCode) {
		CreditExternalGuaranteeRecord externalGuarantee = new CreditExternalGuaranteeRecord();
		externalGuarantee.setLoanCode(loanCode);
		return this.dao.selectByGuaranteeInfo(externalGuarantee);
	}

	/**
	 * 保存外部担保信息
	 * 2016年2月22日
	 * By 王浩
	 * @param externalGuarantee
	 * @return 保存记录条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int saveExternalGuarantee(CreditExternalGuaranteeRecord externalGuarantee) {
		externalGuarantee.preInsert();
		return this.dao.insertExternalGuarantee(externalGuarantee);
	}

	/**
	 * 根据id删除外部担保信息
	 * 2016年2月22日
	 * By 王浩
	 * @param id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteGuaranteeById(String id) {
		return this.dao.deleteByPrimaryKey(id);
	}	
	
	/**
	 * 根据loanCode删除外部担保信息
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

package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.CreditGradeDao;
import com.creditharmony.approve.credit.entity.CreditGrade;

/**
 * 企业征信_信用评级Service
 * @Class Name CreditGradeService
 * @author 王浩
 * @Create In 2016年2月22日
 */
@Service
public class CreditGradeService extends  CoreManager<CreditGradeDao,CreditGrade> {
	
	/**
	 * 根据关联id查询相应记录
	 * 2016年2月22日
	 * By 王浩
	 * @param loanCode
	 * @return 
	 */
	public List<CreditGrade> selectByLoanCode(String loanCode) {
		CreditGrade gradeInfo = new CreditGrade();
		gradeInfo.setLoanCode(loanCode);
		return this.dao.selectByGradeInfo(gradeInfo);
	}

	/**
	 * 保存评级信息
	 * 2016年2月22日
	 * By 王浩
	 * @param creditGrade
	 * @return 保存记录条数
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public int saveCreditGrade(CreditGrade creditGrade) {
		creditGrade.preInsert();
		return this.dao.insertCreditGrade(creditGrade);
	}

	/**
	 * 根据id删除评级信息
	 * 2016年2月22日
	 * By 王浩
	 * @param id
	 * @return 执行条数
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public int deleteGradeById(String id) {
		return this.dao.deleteByPrimaryKey(id);
	}	
	
	/**
	 * 根据loanCode清除评级信息
	 * 2016年2月22日
	 * By 王浩
	 * @param loanCode
	 * @return 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public int deleteByRelationId(String loanCode) {
		return this.dao.deleteGradeByLoanCode(loanCode);
	}	

}

package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.CreditUnclearedImproperLoanDao;
import com.creditharmony.approve.credit.entity.CreditUnclearedImproperLoan;

/**
 * 企业征信_未结清业务:不良、关注类Service
 * @Class Name CreditUnclearedImproperLoanService
 * @author 侯志斌
 * @Create In 2016年2月25日
 */
@Service
public class CreditUnclearedImproperLoanService extends  CoreManager<CreditUnclearedImproperLoanDao,CreditUnclearedImproperLoan>{
	/**
	 * 根据关联loanCode查询未结清业务
	 * 2016年2月25日
	 * By 侯志斌
	 * @param unclearedImproperLoan
	 * @return 未结清业务列表
	 */
	public List<CreditUnclearedImproperLoan> selectByCreditUnclearedImproperLoan(CreditUnclearedImproperLoan unclearedImproperLoan) {
		return this.dao.selectByUnclearedImproperLoan(unclearedImproperLoan);
	}

	/**
	 * 保存未结清业务
	 * 2016年2月25日
	 * By 侯志斌
	 * @param unclearedImproperLoan
	 * @return 保存记录条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int saveUnclearedImproperLoan(CreditUnclearedImproperLoan unclearedImproperLoan) {
		unclearedImproperLoan.preInsert();
		return this.dao.insertUnclearedImproperLoan(unclearedImproperLoan);
	}

	/**
	 * 根据id删除未结清业务
	 * 2016年2月25日
	 * By 侯志斌
	 * @param id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteUnclearedImproperLoanById(String id) {
		return this.dao.deleteByPrimaryKey(id);
	}	
	
	/**
	 * 根据loanCode删除相应信息
	 * 2016年2月25日
	 * By 侯志斌
	 * @param unclearedImproperLoan
	 * @return 执行条数
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public int deleteByUnclearedImproperLoan(CreditUnclearedImproperLoan unclearedImproperLoan) {
		return this.dao.deleteByUnclearedImproperLoan(unclearedImproperLoan);
	}

}

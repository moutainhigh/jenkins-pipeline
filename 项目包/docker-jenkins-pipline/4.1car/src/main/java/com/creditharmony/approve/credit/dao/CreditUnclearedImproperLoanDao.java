package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditUnclearedImproperLoan;

/**
 * 企业征信_未结清非正常贷款
 * @Class Name CreditUnclearedImproperLoanDao
 * @author 李文勇
 * @Create In 2016年2月24日
 */
@LoanBatisDao
public interface CreditUnclearedImproperLoanDao extends CrudDao<CreditUnclearedImproperLoan> {
	
	/**
	 * 根据借款编号查询
	 * 2016年2月24日
	 * By 李文勇
	 * @param loanCode
	 * @return
	 */
	public List<CreditUnclearedImproperLoan> getByLoanCode(String loanCode);

	/**
	 * 根据关联loanCode查询未结清业务
	 * 2016年2月25日
	 * By 侯志斌
	 * @param unclearedImproperLoan
	 * @return 未结清业务列表
	 */
	public List<CreditUnclearedImproperLoan> selectByUnclearedImproperLoan(CreditUnclearedImproperLoan unclearedImproperLoan);

	/**
	 * 保存未结清业务
	 * 2016年2月25日
	 * By 侯志斌
	 * @param unclearedImproperLoan
	 * @return 保存记录条数
	 */
	public int insertUnclearedImproperLoan(CreditUnclearedImproperLoan unclearedImproperLoan);

	/**
	 * 根据id删除未结清业务
	 * 2016年2月25日
	 * By 侯志斌
	 * @param id
	 * @return 执行条数
	 */
	public int deleteByPrimaryKey(String id);

	/**
	 * 根据loanCode删除相应信息
	 * 2016年2月25日
	 * By 侯志斌
	 * @param unclearedImproperLoan
	 * @return 执行条数
	 */
	public int deleteByUnclearedImproperLoan(CreditUnclearedImproperLoan unclearedImproperLoan);
}
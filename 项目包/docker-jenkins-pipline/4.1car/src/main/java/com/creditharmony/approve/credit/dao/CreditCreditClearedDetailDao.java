package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditCreditClearedDetail;

/**
 * 企业征信_已结清信贷明细
 * @Class Name CreditCreditClearedDetailDao
 * @author 李文勇
 * @Create In 2016年2月24日
 */
@LoanBatisDao
public interface CreditCreditClearedDetailDao extends CrudDao<CreditCreditClearedDetail>{
	
	/**
	 * 根据借款编号查询
	 * 2016年2月24日
	 * By 李文勇
	 * @param loanCode
	 * @return 结果集list
	 */
	public List<CreditCreditClearedDetail> getByLoanCode(String loanCode);
	
	/**
	 * 更新信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 
	 * @return 执行条数
	 */
    public int updateByPrimaryKeySelective(CreditCreditClearedDetail record);
    
	/**
	 * 新增信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 
	 * @return 执行条数
	 */
    public int insertCreditCreditClearedDetail(CreditCreditClearedDetail record);

}
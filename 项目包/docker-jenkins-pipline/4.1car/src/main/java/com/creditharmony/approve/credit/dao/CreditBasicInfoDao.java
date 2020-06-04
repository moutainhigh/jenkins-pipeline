package com.creditharmony.approve.credit.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditBasicInfo;

/**
 * 企业征信_基础信息
 * @Class Name CreditBasicInfoDao
 * @author zhanghu
 * @Create In 2016年2月19日
 */
@LoanBatisDao
public interface CreditBasicInfoDao extends CrudDao<CreditBasicInfo> {
 	
	/**
	 * 新增基础信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 基础信息
	 * @return 执行条数
	 */
	public int insertCreditBasicInfo(CreditBasicInfo record);


    /**
     * 根据借款编码检索基础信息List
     * 2016年2月2日
     * By zhanghu
     * @param loanCode 借款编码
     * @return 基础信息
     */
	public CreditBasicInfo selectByLoanCode(String loanCode);
    
	/**
	 * 更新基础信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 基础信息
	 * @return 执行条数
	 */
	public int updateByPrimaryKeySelective(CreditBasicInfo record);

}
package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditCreditClearedInfo;

/**
 * 企业征信_已结清信贷信息
 * @Class Name CreditCreditClearedInfoDao
 * @author zhanghu
 * @Create In 2016年2月19日
 */
@LoanBatisDao
public interface CreditCreditClearedInfoDao extends CrudDao<CreditCreditClearedInfo> {
 	
	/**
	 * 更新信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 
	 * @return 执行条数
	 */
    public int updateByPrimaryKeySelective(CreditCreditClearedInfo record);
    
	/**
	 * 新增信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 
	 * @return 执行条数
	 */
    public int insertCreditCreditClearedInfo(CreditCreditClearedInfo record);

    /**
     * 通过借款款编号来获取数据
     * 2016年2月23日
     * By 李文勇
     * @return
     */
    public List<CreditCreditClearedInfo> getByLoanCode(String loanCode);
    
}
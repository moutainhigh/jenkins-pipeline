package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditCurrentLiabilityInfo;

/**
 * 企业征信_当前负债信息概要
 * @Class Name CreditCurrentLiabilityInfoDao
 * @author 李文勇
 * @Create In 2016年2月23日企业征信_当前负债信息概要
 */
@LoanBatisDao
public interface CreditCurrentLiabilityInfoDao extends CrudDao<CreditCurrentLiabilityInfo>{
    
    
    /**
     * 根据借款编号获取数据
     * 2016年2月23日
     * By 李文勇
     * @param loanCode
     * @return
     */
    public List<CreditCurrentLiabilityInfo> getByLoanCode(String loanCode);
    
	/**
	 * 更新信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 
	 * @return 执行条数
	 */
    public int updateByPrimaryKeySelective(CreditCurrentLiabilityInfo record);
    
	/**
	 * 新增信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 
	 * @return 执行条数
	 */
    public int insertCreditCurrentLiabilityInfo(CreditCurrentLiabilityInfo record);
    
    /**
     * 根据借款编码检索信息List
     * 2016年2月2日
     * By zhanghu
     * @param loanCode 借款编码
     * @return 信息List
     */
    public List<CreditCurrentLiabilityInfo> selectByLoanCode(String loanCode);
    
}
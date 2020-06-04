package com.creditharmony.approve.verify.dao;

import java.math.BigDecimal;
import java.util.List;

import com.creditharmony.approve.verify.entity.LoanBank;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 银行卡信息
 * @Class Name LoanBankDao
 * @author 李文勇
 * @Create In 2015年12月10日
 */
@LoanBatisDao
public interface LoanBankDao {
	
	/**
	 * 2016年1月18日
	 * By 李文勇
	 * @param id
	 * @return 返回操作成功数
	 */
	public int deleteByPrimaryKey(BigDecimal id);

    /**
     * 2016年1月18日
     * By 李文勇
     * @param record
     * @return 返回操作成功数
     */
	public int insertSelective(LoanBank record);

    /**
     * 2016年1月18日
     * By 李文勇
     * @param id
     * @return 返回银行卡信息对象
     */
	public LoanBank selectByPrimaryKey(BigDecimal id);

    /**
     * 2016年1月18日
     * By 李文勇
     * @param record
     * @return 返回操作成功数
     */
	public int updateByPrimaryKeySelective(LoanBank record);

    /**
     * 2016年1月18日
     * By 李文勇
     * @param record
     * @return 返回操作成功数
     */
	public int updateByPrimaryKey(LoanBank record);
    
    /**
     * 查看弹出画面用
     * 2015年12月10日
     * By 李文勇
     * @param loanCode
     * @return 返回银行卡对象list
     */
    public List<LoanBank> viewGetByLoanCode(String loanCode);
}
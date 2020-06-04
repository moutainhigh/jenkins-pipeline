package com.creditharmony.approve.verify.dao;

import java.math.BigDecimal;
import java.util.List;

import com.creditharmony.approve.verify.entity.LoanHouse;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 房产信息
 * @Class Name LoanHouseDao
 * @author 李文勇
 * @Create In 2015年12月10日
 */
@LoanBatisDao
public interface LoanHouseDao {
    
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
    public int insertSelective(LoanHouse record);

    /**
     * 
     * 2016年1月18日
     * By 李文勇
     * @param id
     * @return 返回房产对象
     */
    public LoanHouse selectByPrimaryKey(BigDecimal id);

    /**
     * 2016年1月18日
     * By 李文勇
     * @param record
     * @return 返回操作成功数
     */
    public int updateByPrimaryKeySelective(LoanHouse record);

    /**
     * 2016年1月18日
     * By 李文勇
     * @param record
     * @return 返回操作成功数
     */
    public int updateByPrimaryKey(LoanHouse record);

    /**
     * 查看弹出画面用
     * 2015年12月10日
     * By 李文勇
     * @param loanHouse
     * @return 返回房产对象list
     */
    public List<LoanHouse> viewGetByLoanCode(LoanHouse loanHouse);
    /**
     * 查询汇金录入的房产信息
     * 2016年9月14日
     * By 赵春香
     * @param loanHouse
     * @return 返回房产对象list
     */
    public List<LoanHouse> getListByLoanCode(String loanCode);

    
}
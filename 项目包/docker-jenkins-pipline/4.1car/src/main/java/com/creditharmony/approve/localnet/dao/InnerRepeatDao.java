package com.creditharmony.approve.localnet.dao;

import java.math.BigDecimal;
import java.util.List;

import com.creditharmony.approve.localnet.entity.InnerRepeat;
import com.creditharmony.approve.verify.entity.ex.PayBackMonthEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

@LoanBatisDao
public interface InnerRepeatDao extends  CrudDao<InnerRepeat>{
    
	public int deleteByPrimaryKey(BigDecimal id);

	public int insert(InnerRepeat record);

	public int insertSelective(InnerRepeat record);

	public InnerRepeat selectByPrimaryKey(BigDecimal id);
    
    /**
     * 更新查重信息
     * 2015年12月18日
     * By 刘燕军
     * @param record
     * @return
     */
	public int update(InnerRepeat record);

	public int updateByPrimaryKey(InnerRepeat record);
   
    /**
     * 查询指定查重信息
     * 2015年12月7日
     * By 刘燕军
     * @param type
     * @param loanCode
     * @return
     */
    public List<InnerRepeat> findListByLoanCode(VerifyParamEx param);
   
    /**
     * 
     * 2015年12月16日
     * By 刘燕军
     * @param param
     * @return
     */
    public  List<InnerRepeat> findExceptions(VerifyParamEx param);
   
    /**
     * 查询逾期次数
     * 2015年12月7日
     * By wanglidong
     * @param loanCode 借款编码
     * @return 逾期次数
     */
	public List<PayBackMonthEx> getLateTime(String loanCode);
	
	/**
	 * 查询逾期记录
	 * 2015年12月7日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 逾期记录
	 */
	public List<PayBackMonthEx> getLateTimeRecord(String loanCode);
	  
	/**
    * 获取单位名称查重信息 并插入到查重信息表中
    * 2016年1月6日
    * By 刘燕军
	 * @param loanCode
	 * @param resource
    * @return
    */
	 public int insertInnerRepeats(String loanCode,String resource);
		
	/**
	 * 把单位名称查重信息放入历史的借款信息中
	 * 2016年1月19日
	 * By 刘燕军
	 * @param loanCode
	 * @param resource
	 * @return
	 */
	public int insertRepeatHisName(String loanCode,String resource);
	
	/**
	  * 获取电话查重信息 并插入到查重信息表中
	  * 2016年1月6日
	  * By 刘燕军
	 * @param loanCode
	 * @param resource
	  * @return
	  */
	public int insertInnerRepeatsByTel(String loanCode,String resource);
	
	/**
	  * 获取电话查重信息 并插入到历史查重信息表中
	  * 2016年1月6日
	  * By 刘燕军
	 * @param loanCode
	 * @param resource
	  * @return
	  */
	public int insertInnerRepeatHisTel(String loanCode,String resource);
	
	/**
	 * 获取地址查重信息 并插入到查重信息表中
	 * 2016年1月6日
	 * By 刘燕军
	 * @param loanCode
	 * @param resource
	 * @return
	 */
	public int insertInnerRepeatsByAddress(String loanCode,String resource);
	
	/**
	 * 获取地址查重信息 并插入到历史查重信息表中
	 * 2016年1月6日
	 * By 刘燕军
	 * @param loanCode
	 * @param resource
	 * @return
	 */
	public int insertInnerRepeatHisAddress(String loanCode,String resource);
	
	/**
	 * 决策时判定查重是否都已经判定
	 * 2016年1月25日
	 * By 刘燕军
	 * @param param
	 * @return 查重对象集合
	 */
	public int checkException(VerifyParamEx param);
	
	/**
	 * 查询未判定的查重的数量
	 * 2016年3月28日
	 * By 刘燕军
	 * @param map
	 * @return 对应的数量
	 */
	public int selectRepeatNum(VerifyBusinessView res);
	
	/**
	 * 查询对应的114查询的电话数量
	 * 2016年3月28日
	 * By 刘燕军
	 * @param map
	 * @return 对应的数量
	 */
	public int selectTelNum(VerifyBusinessView res);
}
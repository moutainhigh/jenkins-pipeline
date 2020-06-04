package com.creditharmony.approve.document.dao;

import java.util.List;

import com.creditharmony.approve.document.entity.ZlshLoanAccount;
import com.creditharmony.approve.verify.entity.ex.MonthIncomeEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 个人卡流水/对公卡流水
 * @Class Name ZlshLoanAccountDao
 * @author 路志友
 * @Create In 2016年1月28日
 */
@LoanBatisDao
public interface ZlshLoanAccountDao {
	
	/**
	 * 保存银行卡流水
	 * 2015年12月7日
	 * By 黄维
	 * @param zlshLoanAccount
	 * @return none
	 */
	public void insertLoanAccount(ZlshLoanAccount zlshLoanAccount);

	/**
	 * 修改银行卡流水
	 * 2015年12月11日
	 * By 黄维
	 * @param zlshLoanAccount 
	 * @return none
	 */
	public void updateLoanAccount(ZlshLoanAccount zlshLoanAccount);
	
    /**
     * 
     * 2015年12月28日
     * By 路志友
     * @param record
     * @return int
     */
	public int insertSelective(ZlshLoanAccount record);
	
    /**
     * 月认定收入查询
     * 2016年1月4日
     * By 路志友
     * @param param
     * @return List
     */
    List<MonthIncomeEx> findSalaryMonth(VerifyParamEx param);
    
   /**
    * 通过借款编号获取所有的流水信息
    * 2016年1月4日
    * By 路志友
    * @param param
    * @return List
    */
    public List<ZlshLoanAccount> findZlshLoanAccounts(VerifyParamEx param);
    
    /**
     * 查找对公账户或对私账户
     * 2015年12月23日
     * By 路志友
     * @param record
     * @return List
     */
    public List<ZlshLoanAccount> getListByLoanCode(ZlshLoanAccount record);
    
   /**
    * 根据id删除对公或对私卡流水
    * 2015年12月24日
    * By 路志友
    * @param id
    * @return none
    */
	public void deleteBankCard(String id);
}
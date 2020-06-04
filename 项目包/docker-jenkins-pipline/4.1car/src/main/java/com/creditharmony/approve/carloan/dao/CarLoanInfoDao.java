package com.creditharmony.approve.carloan.dao;

import com.creditharmony.approve.carloan.entity.CarLoanInfo;
import com.creditharmony.approve.carloan.entity.FirstServiceCharge;
import com.creditharmony.approve.carloan.entity.ex.CarLoanExtendExtraEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 
 * @Class Name CarLoanInfoDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface CarLoanInfoDao extends CrudDao<CarLoanInfo> {
    /**
     * 根据applyId获取借款信息
     * 2016年1月25日
     * By 申诗阔
     * @param applyId
     * @return 借款信息
     */
    public CarLoanInfo findByApplyId(String applyId);
    
    /**
     * 通过借款编号loanCode获取借款信息
     * 2016年1月25日
     * By 申诗阔
     * @param loanCode
     * @return 借款信息
     */
    public CarLoanInfo findByLoanCode(String loanCode);
    
    /**
     * 通过借款编号更新借款信息
     * 2016年1月28日
     * By 申诗阔
     * @param loanInfo
     * @return 
     */
    public int updateByLoanCode(CarLoanInfo loanInfo);
    
    /**
     * 根据借款编码获得此借款编码的下的总借款期限等
     * 2016年3月7日
     * By 申诗阔
     * @param loanCode
     * @return 
     */
    public CarLoanExtendExtraEx getSumData(String loanCode);
	/**
	 * 查询借款信息
	 */
	public CarLoanInfo selectByLoanCode(String loanCode);
	
    FirstServiceCharge selectByPrimaryKey(String id);
}
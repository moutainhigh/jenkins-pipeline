package com.creditharmony.approve.newCar.dao;

import java.util.List;

import com.creditharmony.approve.carloan.entity.CarLoanInfo;
import com.creditharmony.approve.newCar.entity.NewCarLoanExtendExtraEx;
import com.creditharmony.approve.newCar.entity.NewCarLoanInfo;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 
 * @Class Name CarLoanInfoDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface NewCarLoanInfoDao extends CrudDao<NewCarLoanInfo> {
	
    /**
     * 根据applyId获取借款信息
     * 2016年1月25日
     * By 申诗阔
     * @param applyId
     * @return 借款信息
     */
    public NewCarLoanInfo findByApplyId(String applyId);
    /**
     * 通过借款编号loanCode获取借款信息
     * 2016年1月25日
     * By 申诗阔
     * @param loanCode
     * @return 借款信息
     */
    public NewCarLoanInfo findByLoanCodeNew(String loanCode);
    
    public NewCarLoanInfo findByLoanCode(String loanCode);
    
	public void updateCarLoanStatus(CarLoanInfo carLoanInfo);
	
	public void updateByLoanCode(CarLoanInfo info);
	
	public PageList<NewCarLoanInfo> getOrderByLoanStatus(
			PageBounds pageBounds, List<String> statusList);
	
	public void updateLoanFlag(NewCarLoanInfo carLoanInfo);
	
	public NewCarLoanExtendExtraEx getSumData(String sLoanCode);
	
	public void updateQueueName(NewCarLoanInfo carInfo);
}
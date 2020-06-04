package com.creditharmony.approve.localnet.dao;

import java.util.List;

import com.creditharmony.approve.localnet.entity.LoanRemark;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 借款备注
 * @Class Name LoanRemarkDao
 * @author 刘燕军
 * @Create In 2016年1月19日
 */
@LoanBatisDao
public interface LoanRemarkDao extends CrudDao<LoanRemark>{
   
    /**
     * 获取备注信息
     * 2016年1月19日
     * By 刘燕军
     * @param loanCode
     * @return 备注信息
     */
    public List<LoanRemark> findListByLoanCode(String loanCode);
   
}
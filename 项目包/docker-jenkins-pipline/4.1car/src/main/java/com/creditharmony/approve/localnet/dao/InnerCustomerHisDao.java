package com.creditharmony.approve.localnet.dao;

import java.util.List;

import com.creditharmony.approve.localnet.entity.InnerCustomerHis;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 历史归户信息查询
 * @Class Name InnerCustomerHisDao
 * @author 刘燕军
 * @Create In 2015年12月2日
 */
@LoanBatisDao
public interface InnerCustomerHisDao extends CrudDao<InnerCustomerHis>{
   
    /**
     * 通过借款编号获取历史归户信息
     * 2015年12月2日
     * By 刘燕军
     * @param param
     * @return 所有的归户信息
     */
	public List<InnerCustomerHis> findListByLoanCode(VerifyParamEx param);
}
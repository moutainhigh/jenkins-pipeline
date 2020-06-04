package com.creditharmony.approve.localnet.dao;

import java.util.List;

import com.creditharmony.approve.localnet.entity.InnerApplyContrast;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 历史归户信息
 * @Class Name InnerApplyContrastDao
 * @author 刘燕军
 * @Create In 2015年12月7日
 */
@LoanBatisDao
public interface InnerApplyContrastDao extends CrudDao<InnerApplyContrast>{

    /**
     * 获取所有符合条件的归户信息
     * 2015年12月7日
     * By 刘燕军
     * @param param 借款编号
     * @return 所有的归户信息
     */
	public List<InnerApplyContrast> findListByLoanCode(VerifyParamEx param);
}
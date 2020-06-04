package com.creditharmony.approve.carloan.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.carloan.entity.CarLoanCoborrower;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 
 * @Class Name CarLoanCoborrowerDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface CarLoanCoborrowerDao extends CrudDao<CarLoanCoborrower> {
    /**
     * 通过借款编号和借款人类型（共借人）获取客户共借人及其联系人
     * 2016年2月17日
     * By 申诗阔
     * @param map
     * @return 客户共借人及其联系人
     */
	public List<CarLoanCoborrower> selectByLoanCodeAndLoanType(Map<String, String> map);
}
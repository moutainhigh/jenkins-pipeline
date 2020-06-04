package com.creditharmony.approve.newCar.dao;

import java.util.List;
import com.creditharmony.approve.carloan.entity.GrossSpread;
import com.creditharmony.approve.newCar.entity.NewCarCustomerConsultation;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 
 * @Class Name GrossSpreadDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface NewGrossSpreadDao  extends CrudDao<GrossSpread>{
    /**
	 * 查询CarCustomerConsultation 
	 * 2016年3月11日
	 * By ganquan
	 * @param String
	 */
    public NewCarCustomerConsultation selectByLoanCode(String loanCode);

    /**
	 * 查询展期loanCode
	 * @param String
	 */
    public List<String> selectByLoanCodeList(String loanCode);
}
package com.creditharmony.approve.newCar.dao;

import com.creditharmony.approve.newCar.entity.NewApplicationInterviewInfo;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 车借_面审信息
 * @Class Name ApplicationInterviewInfoDao
 * @author 李高远
 * @Create In 2017年4月12日
 */
@LoanBatisDao
public interface NewApplicationInterviewInfoDao {

	NewApplicationInterviewInfo findByLoanCode(
			String loanCode);
}
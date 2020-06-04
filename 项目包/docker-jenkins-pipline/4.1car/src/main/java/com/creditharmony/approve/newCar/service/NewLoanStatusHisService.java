package com.creditharmony.approve.newCar.service;

import com.creditharmony.approve.newCar.entity.NewLoanStatusHis;
import com.creditharmony.core.persistence.Page;

public interface NewLoanStatusHisService {


	public Page<NewLoanStatusHis> findByLoanCode(Page<NewLoanStatusHis> page, NewLoanStatusHis loanStatusHis);
}

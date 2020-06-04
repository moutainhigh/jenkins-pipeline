package com.creditharmony.approve.newCar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.newCar.dao.NewLoanStatusHisDao;
import com.creditharmony.approve.newCar.entity.NewLoanStatusHis;
import com.creditharmony.approve.newCar.service.NewLoanStatusHisService;
import com.creditharmony.core.persistence.Page;

@Service
public class NewLoanStatusHisImpl implements NewLoanStatusHisService{

	@Autowired
	private NewLoanStatusHisDao loanStatusHisDao;
	@Override
	public Page<NewLoanStatusHis> findByLoanCode(Page<NewLoanStatusHis> page,
			NewLoanStatusHis loanStatusHis) {
		loanStatusHis.setPage(page);
		page.setList(loanStatusHisDao.findByLoanCode(loanStatusHis));
		return page;
	}

}

package com.creditharmony.approve.carloan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.carloan.dao.LoanStatusHisDao;
import com.creditharmony.approve.carloan.entity.LoanStatusHis;
import com.creditharmony.core.persistence.Page;

@Service
public class LoanStatusHisService {

	@Autowired
	private LoanStatusHisDao loanStatusHisDao;

	public Page<LoanStatusHis> findByLoanCode(Page<LoanStatusHis> page, LoanStatusHis loanStatusHis) {
		loanStatusHis.setPage(page);
		page.setList(loanStatusHisDao.findByLoanCode(loanStatusHis));
		return page;
	}
}

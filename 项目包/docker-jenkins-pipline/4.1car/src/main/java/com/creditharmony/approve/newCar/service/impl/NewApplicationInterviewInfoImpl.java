package com.creditharmony.approve.newCar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.newCar.dao.NewApplicationInterviewInfoDao;
import com.creditharmony.approve.newCar.entity.NewApplicationInterviewInfo;
import com.creditharmony.approve.newCar.service.NewApplicationInterviewInfoService;
@Service
public class NewApplicationInterviewInfoImpl implements NewApplicationInterviewInfoService {

	@Autowired
	private NewApplicationInterviewInfoDao applicationInterviewInfoDao;

	@Override
	public NewApplicationInterviewInfo getInfoByLoanCode(String loanCode) {
		return applicationInterviewInfoDao.findByLoanCode(loanCode);
	}



}

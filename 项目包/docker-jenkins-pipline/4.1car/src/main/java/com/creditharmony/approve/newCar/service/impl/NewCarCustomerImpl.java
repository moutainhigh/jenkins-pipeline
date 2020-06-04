package com.creditharmony.approve.newCar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.newCar.dao.NewCarCustomerDao;
import com.creditharmony.approve.newCar.dao.NewCarLoanInfoDao;
import com.creditharmony.approve.newCar.entity.NewCarLoanInfo;
import com.creditharmony.approve.newCar.entity.NewLoanCustomerEx;
import com.creditharmony.approve.newCar.service.NewCarCustomerService;
import com.creditharmony.approve.newCar.service.NewCarInfoService;

@Service
public class NewCarCustomerImpl implements NewCarCustomerService{

	@Autowired
	private NewCarCustomerDao carCustomerDao;
	
	@Override
	public NewLoanCustomerEx findCustomerInfo(String loanCode) {
		return carCustomerDao.findCustomerInfo(loanCode);
	}
}

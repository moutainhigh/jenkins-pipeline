package com.creditharmony.approve.newCar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.newCar.dao.NewCarContractDao;
import com.creditharmony.approve.newCar.entity.NewCarContract;
import com.creditharmony.approve.newCar.service.NewCarContractService;

@Service
public class NewCarContractImpl implements NewCarContractService{

	@Autowired
	private NewCarContractDao carContractDao;
	
	@Override
	public NewCarContract getLastByLoanCodeOfExtend(String loanCode) {
		return carContractDao.getLastByLoanCodeOfExtend(loanCode);
	}
}

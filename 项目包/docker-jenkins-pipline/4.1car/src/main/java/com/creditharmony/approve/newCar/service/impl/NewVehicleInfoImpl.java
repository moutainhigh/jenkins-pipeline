package com.creditharmony.approve.newCar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.carloan.entity.CarAuditResult;
import com.creditharmony.approve.newCar.dao.NewCarAuditResultDao;
import com.creditharmony.approve.newCar.dao.NewVehicleInfoDao;
import com.creditharmony.approve.newCar.entity.NewCarAuditResult;
import com.creditharmony.approve.newCar.entity.NewVehicleInfo;
import com.creditharmony.approve.newCar.service.NewCarAuditResultService;
import com.creditharmony.approve.newCar.service.NewVehicleInfoService;

@Service
public class NewVehicleInfoImpl implements NewVehicleInfoService {

	@Autowired
	private NewVehicleInfoDao vehicleInfoDao;

	@Override
	public NewVehicleInfo findByLoanCode(String loanCode) {
		return vehicleInfoDao.findByLoanCode(loanCode);
	}
}

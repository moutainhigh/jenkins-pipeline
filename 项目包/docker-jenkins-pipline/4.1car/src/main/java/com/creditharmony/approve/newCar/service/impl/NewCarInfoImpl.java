package com.creditharmony.approve.newCar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.newCar.dao.NewCarLoanInfoDao;
import com.creditharmony.approve.newCar.entity.NewCarLoanInfo;
import com.creditharmony.approve.newCar.service.NewCarInfoService;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;

@Service
public class NewCarInfoImpl implements NewCarInfoService{

	@Autowired
	private NewCarLoanInfoDao carLoanInfoDao;
	
	@Override
	public NewCarLoanInfo findByApplyId(String applyId) {
		return carLoanInfoDao.findByApplyId(applyId);
	}

	@Override
	public void updateCarLoanStatus(NewCarLoanInfo carLoanInfo) {
		 carLoanInfoDao.update(carLoanInfo);
	}

	@Override
	public NewCarLoanInfo findByLoanCode(String loanCode) {
		return carLoanInfoDao.findByLoanCodeNew(loanCode);
	}

	@Override
	public Page<NewCarLoanInfo> getOrderByLoanStatus(
			Page<NewCarLoanInfo> page, List<String> statusList) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		PageList<NewCarLoanInfo> pageList = (PageList<NewCarLoanInfo>) carLoanInfoDao
				.getOrderByLoanStatus(pageBounds, statusList);
		PageUtil.convertPage(pageList, page);		
		return page;
	}

	@Override
	public void updateLoanFlag(NewCarLoanInfo carLoanInfo) {
		carLoanInfoDao.updateLoanFlag(carLoanInfo);
	}

	@Override
	public void updateQueueName(NewCarLoanInfo carInfo) {
		carLoanInfoDao.updateQueueName(carInfo);
	}


	
}

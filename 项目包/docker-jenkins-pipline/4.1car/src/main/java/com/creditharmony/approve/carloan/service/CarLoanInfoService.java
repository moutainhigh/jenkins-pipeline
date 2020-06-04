package com.creditharmony.approve.carloan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.carloan.dao.CarLoanInfoDao;
import com.creditharmony.approve.carloan.entity.CarLoanInfo;
import com.creditharmony.approve.carloan.entity.FirstServiceCharge;

/**
 * 车借--借款信息service
 * @Class Name CarLoanInfoService
 * @author 申诗阔
 * @Create In 2016年3月31日
 */
@Service
public class CarLoanInfoService {

	@Autowired
	private CarLoanInfoDao carLoanInfoDao;
	
	/**
	 * 通过借款编码loanCode获取借款信息
	 * 2016年3月31日
	 * By 申诗阔
	 * @param loanCode
	 * @return
	 */
	public CarLoanInfo findByLoanCode(String loanCode) {
		return carLoanInfoDao.findByLoanCode(loanCode);
	}
	public FirstServiceCharge findFirstServiceChargeById(String id){
		return carLoanInfoDao.selectByPrimaryKey(id);
	}
}

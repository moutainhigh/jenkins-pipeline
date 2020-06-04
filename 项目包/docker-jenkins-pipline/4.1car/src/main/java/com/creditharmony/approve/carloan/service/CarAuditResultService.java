package com.creditharmony.approve.carloan.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.carloan.dao.CarAuditResultDao;
import com.creditharmony.approve.carloan.entity.CarAuditResult;

@Service
public class CarAuditResultService {

	@Autowired
	private CarAuditResultDao carAuditResultDao;
	
	/**
	 * 
	 * 2016年7月5日
	 * By 申诗阔
	 * @param loanCode
	 * @param checkType
	 * @return
	 */
	public CarAuditResult selectLastByLoanCodeAndCheckType(String loanCode, String checkType){
		Map<String, String> map = new HashMap<String, String>();
		map.put("loanCode", loanCode);
		map.put("checkType", checkType);
		return carAuditResultDao.selectLastByLoanCodeAndCheckType(map);
	}
}

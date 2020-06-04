package com.creditharmony.approve.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.antifraud.dao.AntifraudReportDao;
import com.creditharmony.approve.antifraud.entity.AntifraudReport;
import com.creditharmony.approve.management.dao.GlRefuseDao;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.verify.dao.AuditRecordDao;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.dao.StatusChangeRecordDao;

/**
 * 提报反欺诈页面
 * @Class Name submitAntifraudService
 * @author 刘燕军
 * @Create In 2015年12月4日
 */
@Service
public class SubmitAntifraudService {	
	@Autowired
	private GlRefuseDao glRefuseDao;
	@Autowired
	private AntifraudReportDao antifraudReportDao;
	@Autowired
	private StatusChangeRecordDao changeRecordDao;
	@Autowired
	private LoanInfoDao loanInfoDao;
	@Autowired
	private AuditRecordDao singleTastDao;
	
	/**
	 * 初始化反欺诈页面中的提报类别
	 * 2015年12月4日
	 * By 刘燕军
	 * @return
	 */
	public List<GlRefuse> initGlRefuse(){			
		return glRefuseDao.findGlRefuses();		
	}
	
	/**
	 * 提报反欺诈 查看信息
	 * 2016年1月27日
	 * By 刘燕军
	 * @param id
	 * @return
	 */
	public AntifraudReport getAntifraudReport(String id){
		return antifraudReportDao.findAntifraudReport(id);
	}

	/**
	 * 获取一级拒借码
	 * 2016年4月29日
	 * By wanglidong
	 */
	public List<GlRefuse> getOneLevel() {
		List<GlRefuse> oneLevel = antifraudReportDao.getOneLevel();
		return oneLevel;
		
	}

	/**
	 * 获取二级拒借码
	 * 2016年4月29日
	 * By wanglidong
	 * @param id
	 * @return
	 */
	public List<GlRefuse> getTwoLevel(String id) {
		List<GlRefuse> twoLevel = antifraudReportDao.getTwoLevel(id);
		return twoLevel;
	}
}

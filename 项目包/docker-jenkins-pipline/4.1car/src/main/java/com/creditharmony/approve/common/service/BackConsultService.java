package com.creditharmony.approve.common.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.common.dao.BackConsultDao;
import com.creditharmony.approve.common.entity.BackConsult;
import com.creditharmony.approve.management.dao.NegotiationDao;
import com.creditharmony.approve.management.entity.Negotiation;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.dao.StatusChangeRecordDao;
import com.creditharmony.core.service.CoreManager;

/**
 * 退回协商
 * @Class Name RollbackNegotiationService
 * @author wanglidong
 * @Create In 2015年12月8日
 */
@Service
public class BackConsultService extends CoreManager<BackConsultDao,BackConsult > {
	@Autowired
	private BackConsultDao backConsultDao;
	@Autowired
	private NegotiationDao negotiationDao;
	@Autowired
	private LoanInfoDao loanInfoDao;
	@Autowired
	private StatusChangeRecordDao statusChangeRecordDao;
	
	/**
	 * 获取退回协商码
	 * 2016年1月18日
	 * By wanglidong
	 * @param negLel 获取退回协商一级码
	 * @return 一级协商码集合
	 */
	public List<Negotiation> getNegotiationCodeOne(String negLel) {
		return negotiationDao.getNegotiationCodeOne(negLel);
	}
	
	/**
	 * 获取二级退回协商码
	 * 2016年1月18日
	 * By wanglidong
	 * @param id 二级协商码父id
	 * @return 二级协商码集合
	 */
	public List<Negotiation> getNegotiationCodeTwo(String id) {
		return negotiationDao.getNegotiationCodeTwo(id);
	}
	/**
	 * 查看退回协商
	 * 2015年12月30日
	 * By wanglidong
	 * @param id 关联id
	 * @return 退回协商内容
	 */
	public BackConsult getBackConsultView(String id) {
		return backConsultDao.getBackConsultView(id);
	}
	
}




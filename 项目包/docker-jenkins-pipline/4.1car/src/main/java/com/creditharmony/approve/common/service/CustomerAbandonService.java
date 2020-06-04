package com.creditharmony.approve.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.common.dao.CustomerAbandonDao;
import com.creditharmony.approve.common.entity.CustomerAbandon;
import com.creditharmony.approve.management.dao.GlGiveupDao;
import com.creditharmony.approve.management.entity.GlGiveup;
import com.creditharmony.approve.verify.dao.AuditRecordDao;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.dao.StatusChangeRecordDao;
/**
 * 客户放弃页面的service
 * @Class Name CustomerAbandonService
 * @author 刘燕军
 * @Create In 2015年12月5日
 */
@Service
public class CustomerAbandonService {
	@Autowired
	private StatusChangeRecordDao changeRecordDao;
	@Autowired
	private LoanInfoDao loanInfoDao;
	@Autowired
	private GlGiveupDao giveupDao;
	@Autowired
	private CustomerAbandonDao abandonDao;
	@Autowired
	private AuditRecordDao singleTastDao;
	/**
	 * 获取所有的一级码
	 * 2015年12月5日
	 * By 刘燕军
	 * @return
	 */
	public List<GlGiveup> findGlGiveups(){
		return giveupDao.findGlGiveups();
	}	
	/**
	 * 通过一级码的id获取所对应的所有的二级码
	 * 2015年12月5日
	 * By 刘燕军
	 * @param id
	 * @return
	 */
	public List<GlGiveup> findGlGiveupsByParentId(String id){
		return giveupDao.findGlGiveupsByParentId(id);
	}
	/**
	 * 获取客户放弃的初始化内容
	 * 2015年12月31日
	 * By 刘燕军
	 * @param param
	 * @return
	 */
	public CustomerAbandon getList(String id){
		return abandonDao.findCustomerAbandon(id);
	}
}

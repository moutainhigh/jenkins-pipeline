package com.creditharmony.approve.document.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.document.dao.ZlshSbgjjDao;
import com.creditharmony.approve.document.entity.ZlshSbgjj;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;

/**
 * 资料审核 社保公积金
 * @Class Name ZlshSbgjjService
 * @author 路志友
 * @Create In 2015年12月24日
 */
@Service
public class ZlshSbgjjService {

	@Autowired
	private ZlshSbgjjDao zlshSbgjjDao;
	
	/**
	 * 保存/修改社保公积金
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshSbgjj
	 * @param vex
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String saveSbgjj(ZlshSbgjj zlshSbgjj,VerifyParamEx vex) {
		zlshSbgjj.setLoanCode(vex.getLoanCode());
		zlshSbgjj.setDictCheckType(vex.getCheckType());
		zlshSbgjj.setDictCustomerType(vex.getType());
		zlshSbgjj.setrId(vex.getRelId());
			String id = zlshSbgjj.getId();
			if(StringUtils.isEmpty(id)){
				zlshSbgjj.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
				zlshSbgjj.preInsert();
				zlshSbgjjDao.insertSbgjj(zlshSbgjj);
			}else{
				zlshSbgjj.preUpdate();
				zlshSbgjjDao.updateSbgjj(zlshSbgjj);
			}
			id = zlshSbgjj.getId();
		return id;
	}
     
	/**
	 *  获取列表
	 * 2016年1月4日
	 * By 路志友
	 * @param loanCode
	 * @return List
	 */
	public List<ZlshSbgjj> getListByLoanCode(ZlshSbgjj zlshSbgjj) {
		return zlshSbgjjDao.getListByLoanCode(zlshSbgjj);
	}
	
	/**
	 * 删除社保公积金
	 * 2015年12月24日
	 * By 路志友
	 * @param zlshSbgjj
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String deleteSocialFund(ZlshSbgjj zlshSbgjj) {
		String id = "";
		if(zlshSbgjj.getId()!=null){
			zlshSbgjjDao.deleteById(zlshSbgjj.getId());
			id = zlshSbgjj.getId();
		}
		return id;
	}
	
}

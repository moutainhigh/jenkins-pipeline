package com.creditharmony.approve.document.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.document.dao.ZlshZczmDao;
import com.creditharmony.approve.document.entity.ZlshZczm;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;

/**
 * 资料审核 资产证明
 * @Class Name ZlshZczmService
 * @author 路志友
 * @Create In 2015年12月24日
 */
@Service
public class ZlshZczmService {

	@Autowired
	private ZlshZczmDao zlshZczmDao;
	
	/**
	 * 保存/修改资产证明
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshZczm
	 * @param vex
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String saveZczm(ZlshZczm zlshZczm,VerifyParamEx vex) {
		zlshZczm.setLoanCode(vex.getLoanCode());
		zlshZczm.setDictCheckType(vex.getCheckType());
		zlshZczm.setDictCustomerType(vex.getType());
		zlshZczm.setrCustomerCoborrowerId(vex.getRelId());
		String id = zlshZczm.getId();
		if( StringUtils.isEmpty(id) ){
			zlshZczm.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
			zlshZczm.preInsert();
			zlshZczmDao.insertZczm(zlshZczm);
			id = zlshZczm.getId();
		}else{
			zlshZczm.preUpdate();
			zlshZczmDao.updateZczm(zlshZczm);
		}
		return id;
	}
    
	/**
	 * 获取资产证明列表
	 * 2016年1月4日
	 * By 路志友
	 * @param loanCode
	 * @return List
	 */
	public List<ZlshZczm> getListByLoanCode(ZlshZczm zlshZczm) {
		return zlshZczmDao.getListByLoanCode(zlshZczm);
	}
	 
	/**
	 * 删除资产证明
	 * 2015年12月24日
	 * By 路志友
	 * @param zlshZczm
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String deletePropertyProve(ZlshZczm zlshZczm) {
		String id = "";
		if(!StringUtils.isEmpty(zlshZczm.getId())){
			 id = zlshZczm.getId();
			 zlshZczmDao.deleteByPrimaryKey(id);
		}
		return id;
	}
}

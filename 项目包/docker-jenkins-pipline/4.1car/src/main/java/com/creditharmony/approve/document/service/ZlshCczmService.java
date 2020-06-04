package com.creditharmony.approve.document.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.document.dao.ZlshCczmDao;
import com.creditharmony.approve.document.entity.ZlshCczm;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;

/**
 * 资料审核 车产证明
 * @Class Name ZlshCczmService
 * @author 路志友
 * @Create In 2015年12月24日
 */
@Service
public class ZlshCczmService {

	@Autowired
	private ZlshCczmDao zlshCczmDao;
	
	/**
	 * 保存/修改资产证明
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshCczm
	 * @param vex
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String saveCczm(ZlshCczm zlshCczm,VerifyParamEx vex) {
		zlshCczm.setDictCheckType(vex.getCheckType());
		zlshCczm.setLoanCode(vex.getLoanCode());
		zlshCczm.setDictCustomerType(vex.getType());
		zlshCczm.setrId(vex.getRelId());
		String id = zlshCczm.getId();
		if(StringUtils.isEmpty(id)){
			zlshCczm.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
			zlshCczm.preInsert();
			zlshCczmDao.insertSelective(zlshCczm);
			id = zlshCczm.getId();
		}else{
			zlshCczm.preUpdate();
			zlshCczmDao.updateByPrimaryKeySelective(zlshCczm);
		}
		return id;
	}
     
	/**
	 *  获取车产证明列表
	 * 2016年1月4日
	 * By 路志友
	 * @param loanCode
	 * @return List
	 */ 
	public List<ZlshCczm> getListByLoanCode(ZlshCczm zlshCczm) {
		return zlshCczmDao.getListByLoanCode(zlshCczm);
	}
	
	/**
	 * 删除车产证明
	 * 2015年12月24日
	 * By 路志友
	 * @param zlshCczm
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String deleteCarProve(ZlshCczm zlshCczm) {
		String id = "";
		if(!StringUtils.isEmpty(zlshCczm.getId())){
			 id = zlshCczm.getId();
			 zlshCczmDao.deleteById(id);
		}
		return id;
	}
	
}

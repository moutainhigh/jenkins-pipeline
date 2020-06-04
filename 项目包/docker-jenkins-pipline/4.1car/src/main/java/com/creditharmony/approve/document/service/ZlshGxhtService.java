package com.creditharmony.approve.document.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.document.dao.ZlshGxhtDao;
import com.creditharmony.approve.document.entity.ZlshGxht;
import com.creditharmony.approve.phone.dao.DhzhGxhtDhxxDao;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;

/**
 * 资料审核 购销合同
 * @Class Name ZlshGxhtService
 * @author 路志友
 * @Create In 2015年12月24日
 */
@Service
public class ZlshGxhtService {

	@Autowired
	private ZlshGxhtDao zlshGxhtDao;
	@Autowired
	private DhzhGxhtDhxxDao busiContractDao;
	@Autowired
	private DhzhGxhtDhxxDao dhzhGxhtDhxxDao;
	
	/**
	 * 保存/修改购销合同
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshGxht
	 * @param vex
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String saveGxht(ZlshGxht zlshGxht,VerifyParamEx vex) {
		zlshGxht.setLoanCode(vex.getLoanCode());
		zlshGxht.setDictCheckType(vex.getCheckType());
		zlshGxht.setrCustomerCoborrowerId(vex.getRelId());
		zlshGxht.setDictCustomerType(vex.getType());
		String id = zlshGxht.getId();
		if(StringUtils.isEmpty(id)){
			zlshGxht.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
			zlshGxht.preInsert();
			zlshGxhtDao.insertGxht(zlshGxht);			
			id = zlshGxht.getId();
		}else{
			zlshGxht.preUpdate();
			zlshGxhtDao.updateGxht(zlshGxht);
		}
		return id;
	}
	
    /**
     * 购销合同资料
     * 2015年12月23日
     * By 路志友
     * @param loanCode
     * @return List
     */
	public List<ZlshGxht> getListByLoanCode(ZlshGxht zlshGxht) {
		return zlshGxhtDao.getListByLoanCode(zlshGxht);
	}
	
	/**
	 * 删除购销合同编号
	 * 2015年12月24日
	 * By 路志友
	 * @param zlshGxht
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String deleteTradeContract(ZlshGxht zlshGxht) {
		String id = "";
		if(!StringUtils.isEmpty(zlshGxht.getId())){
			id = zlshGxht.getId();
			// 删除资料审核购销合同数据
			zlshGxhtDao.deleteById(id);
		}
		return id;
	}
	
}

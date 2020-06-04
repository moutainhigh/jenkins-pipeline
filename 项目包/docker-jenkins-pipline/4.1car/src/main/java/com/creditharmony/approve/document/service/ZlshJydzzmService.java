package com.creditharmony.approve.document.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.document.dao.ZlshJydzzmDao;
import com.creditharmony.approve.document.entity.ZlshJydzzm;
import com.creditharmony.approve.document.entity.ZlshJyzm;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;

/**
 * 资料审核 经营地址证明
 * @Class Name ZlshJydzzmService
 * @author 路志友
 * @Create In 2015年12月24日
 */
@Service
public class ZlshJydzzmService {

	@Autowired
	private ZlshJydzzmDao zlshJydzzmDao;
	
	/**
	 * 保存/修改经营地址证明
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshJydzzm
	 * @param vex
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public List<ZlshJydzzm> saveJydzzm(ZlshJyzm zlshJyzm,VerifyParamEx vex) {
		List<ZlshJydzzm> result = new ArrayList<ZlshJydzzm>();
		if(zlshJyzm != null && zlshJyzm.getJydzzmList() != null){
			for(ZlshJydzzm zlshJydzzm:zlshJyzm.getJydzzmList()){
				zlshJydzzm.setLoanCode(vex.getLoanCode());
				zlshJydzzm.setDictCheckType(vex.getCheckType());
				zlshJydzzm.setrJyzmId(vex.getRelId());
				zlshJydzzm.setDictCustomerType(vex.getType());
				String id = zlshJydzzm.getId();
				if(StringUtils.isEmpty(id)){
					zlshJydzzm.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
					zlshJydzzm.preInsert();
					zlshJydzzmDao.insertJydzzm(zlshJydzzm);
					id = zlshJydzzm.getId();
				}else{
					zlshJydzzm.preUpdate();
					zlshJydzzmDao.updateJydzzm(zlshJydzzm);
				}
				result.add(zlshJydzzm);
			}
		}
		return result;
	}

	/**
	 * 获取所有经营地址证明
	 * 2016年1月4日
	 * By 路志友
	 * @param zlshJydzzm
	 * @return List
	 */
	public List<ZlshJydzzm> getListByLoanCode(ZlshJydzzm zlshJydzzm) {
		return zlshJydzzmDao.getListByLoanCode(zlshJydzzm);
	}
	
	/**
	 * 删除经营地址证明
	 * 2015年12月28日
	 * By 路志友
	 * @param zlshJydzzm
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String deleteManageAddrProve(ZlshJydzzm zlshJydzzm) {
		String id = "";
		if(!StringUtils.isEmpty(zlshJydzzm.getId())){
			id = zlshJydzzm.getId();
			zlshJydzzmDao.deleteById(id);
		}
		return id;
	}
}

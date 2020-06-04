package com.creditharmony.approve.document.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.document.dao.ZlshGrzjxlDao;
import com.creditharmony.approve.document.entity.ZlshGrzj;
import com.creditharmony.approve.document.entity.ZlshGrzjxl;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;

/**
 * 资料审核 个人证件学历
 * @Class Name ZlshGrzjxlService
 * @author 赵春香
 * @Create In 2016年9月13日
 */
@Service
public class ZlshGrzjxlService {

	@Autowired
	private ZlshGrzjxlDao zlshGrzjxlDao;
	
	/**
	 * 保存/修改个人证件学历
	 * 2016年9月13日
	 * By 赵春香
	 * @param zlshGrzjxl
	 * @param vex
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public List<ZlshGrzjxl> saveZlshGrzjxl(ZlshGrzj zlshGrzj,VerifyParamEx vex) {
		List<ZlshGrzjxl> result = new ArrayList<ZlshGrzjxl>();
		if(zlshGrzj != null && zlshGrzj.getGrxlList() != null){
			for(ZlshGrzjxl zlshGrzjxl:zlshGrzj.getGrxlList()){
				zlshGrzjxl.setLoanCode(vex.getLoanCode());
				zlshGrzjxl.setDictCheckType(vex.getCheckType());
				zlshGrzjxl.setrGrzjId(vex.getRelId());
				zlshGrzjxl.setDictCustomerType(vex.getType());
				String id = zlshGrzjxl.getId();
				if(StringUtils.isEmpty(id)){
					zlshGrzjxl.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
					zlshGrzjxl.preInsert();
					zlshGrzjxlDao.insertGrzjxl(zlshGrzjxl);
					id = zlshGrzjxl.getId();
				}else{
					zlshGrzjxl.preUpdate();
					zlshGrzjxlDao.updateGrzjxl(zlshGrzjxl);
				}
				result.add(zlshGrzjxl);
			}
		}
	return result;
	}

	/**
	 * 获取所有个人证件学历
	 * 2016年9月13日
	 * By 赵春香
	 * @param zlshGrzjxl
	 * @return List
	 */
	public List<ZlshGrzjxl> getListByLoanCode(ZlshGrzjxl zlshGrzjxl) {
		return zlshGrzjxlDao.getListByLoanCode(zlshGrzjxl);
	}
	
	/**
	 * 删除个人证件学历
	 * 2016年9月19日
	 * By 赵春香
	 * @param zlshGrzjxl
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String deleteGrzjxl(ZlshGrzjxl zlsh) {
		String id = "";
		if(!StringUtils.isEmpty(zlsh.getId())){
			id = zlsh.getId();
			zlshGrzjxlDao.deleteById(id);
		}
		return id;
	}

}

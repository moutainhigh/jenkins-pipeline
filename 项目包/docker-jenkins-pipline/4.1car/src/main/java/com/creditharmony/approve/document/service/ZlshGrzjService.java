package com.creditharmony.approve.document.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.document.dao.ZlshGrzjDao;
import com.creditharmony.approve.document.dao.ZlshGrzjxlDao;
import com.creditharmony.approve.document.entity.ZlshGrzj;
import com.creditharmony.approve.document.entity.ZlshGrzjxl;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;

/**
 * 资料审核 个人证件
 * @Class Name ZlshGrzjService
 * @author 赵春香
 * @Create In 2016年09月13日
 */
@Service
public class ZlshGrzjService {

	@Autowired
	private ZlshGrzjDao zlshGrzjDao;	
	@Autowired
	private ZlshGrzjxlDao zlshGrzjxlDao;
	
	
	/**
	 * 保存/个人证件
	 * 2016年1月28日
	 * By 赵春香
	 * @param zlshGrzj
	 * @param vex
	 * @return String
	 * @throws ParseException 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public ZlshGrzj saveGrzj(ZlshGrzj zlshGrzj,VerifyParamEx vex) throws ParseException {
		
		ZlshGrzj result = new ZlshGrzj();
		zlshGrzj.setLoanCode(vex.getLoanCode());
		zlshGrzj.setDictCheckType(vex.getCheckType());
		zlshGrzj.setDictCustomerType(vex.getType());
		zlshGrzj.setrCustomerCoborrowerId(vex.getRelId());
		String id = zlshGrzj.getId();
		if(StringUtils.isEmpty(id)){
			zlshGrzj.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
			zlshGrzj.preInsert();
			zlshGrzjDao.insertGrzj(zlshGrzj);
		}else{
			zlshGrzj.preUpdate();
			zlshGrzjDao.updateGrzj(zlshGrzj);	
		}
		// 保存或修改学历信息
	    List<ZlshGrzjxl> grxlLis = saveGrxl(zlshGrzj,vex);
        result.setGrxlList(grxlLis);	
		result.setId(zlshGrzj.getId());
		return result;			
	}
 	
	 /**
	  * 学历信息
	  * 2016年9月21日
	  * By 赵春香
	  * @param zlshJyzm
	  * @return void
	 * @throws ParseException 
	  */
	public List<ZlshGrzjxl> saveGrxl(ZlshGrzj zlshGrzj,VerifyParamEx vex) throws ParseException{
		List<ZlshGrzjxl> result = new ArrayList<ZlshGrzjxl>();
		if(zlshGrzj != null && zlshGrzj.getGrxlList() != null){
			for(ZlshGrzjxl zlshGrzjxl:zlshGrzj.getGrxlList()){
				zlshGrzjxl.setLoanCode(vex.getLoanCode());
				zlshGrzjxl.setDictCheckType(vex.getCheckType());
				zlshGrzjxl.setrGrzjId(zlshGrzj.getId());
				zlshGrzjxl.setDictCustomerType(vex.getType());
				String id = zlshGrzjxl.getId();
				if(id == null || id.equals("")){
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
	 * 获取个人证件列表
	 * 2016年1月4日
	 * By 赵春香
	 * @param loanCode
	 * @return List
	 */
	public List<ZlshGrzj> getListByLoanCode(ZlshGrzj zlshGrzj) {
		return zlshGrzjDao.getListByLoanCode(zlshGrzj);
	}
	 
	/**
	 * 删除资产证明
	 * 2015年12月24日
	 * By 赵春香
	 * @param zlshGrzj
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String deletePropertyProve(ZlshGrzj zlshGrzj) {
		String id = "";
		if(!StringUtils.isEmpty(zlshGrzj.getId())){
			 id = zlshGrzj.getId();
			 zlshGrzjDao.deleteByPrimaryKey(id);
		}
		return id;
	}
}

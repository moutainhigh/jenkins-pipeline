package com.creditharmony.approve.document.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.document.dao.ZlshJydzzmDao;
import com.creditharmony.approve.document.dao.ZlshJyzmDao;
import com.creditharmony.approve.document.dao.ZlshJyzmGdxxDao;
import com.creditharmony.approve.document.entity.ZlshJydzzm;
import com.creditharmony.approve.document.entity.ZlshJyzm;
import com.creditharmony.approve.document.entity.ZlshJyzmGdxx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;

/**
 * 资料审核 经营证明
 * @Class Name ZlshJyzmService
 * @author 路志友
 * @Create In 2015年12月24日
 */
@Service
public class ZlshJyzmService {

	@Autowired
	private ZlshJyzmDao zlshJyzmDao;
	@Autowired
	private ZlshJyzmGdxxDao jyzmGdxxDao;
	@Autowired
	private ZlshJydzzmDao zlshJydzzmDao;
	
	
	/**
	 * 保存/修改经营证明
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshJyzm
	 * @param vex
	 * @return String
	 * @throws ParseException 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public ZlshJyzm saveJyzm(ZlshJyzm zlshJyzm,VerifyParamEx vex) throws ParseException {
		ZlshJyzm result = new ZlshJyzm();
		zlshJyzm.setLoanCode(vex.getLoanCode());
		zlshJyzm.setDictCheckType(vex.getCheckType());
		zlshJyzm.setDictCustomerType(vex.getType());
		zlshJyzm.setrCustomerCoborrowerId(vex.getRelId());
		String id = zlshJyzm.getId();
		if(StringUtils.isEmpty(id)){
			zlshJyzm.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
			zlshJyzm.setDictSysFlag(ApproveConstants.INFO_FROM_HC);
			zlshJyzm.preInsert();
			zlshJyzmDao.insertJyzm(zlshJyzm);
			List<ZlshJyzmGdxx> gdxxLis = saveGdxx(zlshJyzm);
			result.setJyzmGdxxList(gdxxLis);
		}else{
			zlshJyzm.preUpdate();
			zlshJyzmDao.updateJyzm(zlshJyzm);
			// 删除股东信息
			jyzmGdxxDao.deleteByRid(zlshJyzm.getId());
			// 保存股东信息
			List<ZlshJyzmGdxx> gdxxLis = saveGdxx(zlshJyzm);
			result.setJyzmGdxxList(gdxxLis);
		}		
		// 保存或修改经营地址
		List<ZlshJydzzm> dzzmLis = saveJydz(zlshJyzm,vex);
		result.setJydzzmList(dzzmLis);
		
		result.setId(zlshJyzm.getId());
		return result;
	}
	
	 /**
	  * 保存股东信息
	  * 2015年12月23日
	  * By 路志友
	  * @param zlshJyzm
	  * @return void
	  */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public List<ZlshJyzmGdxx> saveGdxx(ZlshJyzm zlshJyzm){
		List<ZlshJyzmGdxx> zlshJyzmGdxxlis = new ArrayList<ZlshJyzmGdxx>();
		if(zlshJyzm != null && !StringUtils.isEmpty(zlshJyzm.getJyzmGdxx().getGdxxGdname())&&
				!StringUtils.isEmpty(zlshJyzm.getJyzmGdxx().getGdxxRatio())&&
				!StringUtils.isEmpty(zlshJyzm.getJyzmGdxx().getGdxxRelation())){
			String[] name = zlshJyzm.getJyzmGdxx().getGdxxGdname().split(",");
			String[] ratio = zlshJyzm.getJyzmGdxx().getGdxxRatio().split(",");
			String[] relation = zlshJyzm.getJyzmGdxx().getGdxxRelation().split(",");
			
			for(int i = 0; i < name.length; i++){
				// 保存股东信息
				ZlshJyzmGdxx gdxx = new ZlshJyzmGdxx();
				gdxx.preInsert();
				gdxx.setrJyzmId(zlshJyzm.getId());
				gdxx.setGdxxGdname(name[i]);
				gdxx.setGdxxRatio(ratio[i]);
				gdxx.setGdxxRelation(relation[i]);
				gdxx.setDictCheckType(zlshJyzm.getDictCheckType());
				jyzmGdxxDao.insertJyzmGdxx(gdxx);
				zlshJyzmGdxxlis.add(i, gdxx);
			}
		}
		return zlshJyzmGdxxlis;
	}
	
	
	 /**
	  * 保存经营地址证明
	  * 2015年12月23日
	  * By 路志友
	  * @param zlshJyzm
	  * @return void
	 * @throws ParseException 
	  */
	public List<ZlshJydzzm> saveJydz(ZlshJyzm zlshJyzm,VerifyParamEx vex) throws ParseException{
		List<ZlshJydzzm> result = new ArrayList<ZlshJydzzm>();
		if(zlshJyzm != null && zlshJyzm.getJydzzmList() != null){
			for(ZlshJydzzm zlshJydzzm:zlshJyzm.getJydzzmList()){
				zlshJydzzm.setLoanCode(vex.getLoanCode());
				zlshJydzzm.setDictCheckType(vex.getCheckType());
				zlshJydzzm.setrJyzmId(zlshJyzm.getId());
				zlshJydzzm.setDictCustomerType(vex.getType());
				String id = zlshJydzzm.getId();
				if(id == null || id.equals("")){
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
	 * 获取经营证明列表
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshJyzm
	 * @return list
	 */
	public List<ZlshJyzm> getListByLoanCode(ZlshJyzm zlshJyzm) {
		return zlshJyzmDao.getListByLoanCode(zlshJyzm);
	}
    
	/**
	 * 获取经营证明股东列表
	 * 2016年1月4日
	 * By 路志友
	 * @param id
	 * @return List
	 */
	public List<ZlshJyzmGdxx> getListByRid(String id) {
		return jyzmGdxxDao.selectByRid(id);
	}
	 
	/**
	 * 
	 * 删除经营证明
	 * 2015年12月24日
	 * By 路志友
	 * @param zlshJyzm
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String deleteManageProve(ZlshJyzm zlshJyzm) {
		String id = "";
		if(!StringUtils.isEmpty(zlshJyzm.getId())){
			id = zlshJyzm.getId();
			zlshJyzm =  zlshJyzmDao.getEntityById(id);
			if(zlshJyzm.getLoanCode() != null){
				jyzmGdxxDao.deleteByRid(zlshJyzm.getLoanCode());
			}
			zlshJydzzmDao.deleteByRId(id);
			zlshJyzmDao.deleteById(id);
		}
		return id;
	}
    
	/**
	 * 删除股东信息
	 * 2015年12月25日
	 * By 路志友
	 * @param zlshJyzmGdxx
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String deleteGdxx(ZlshJyzmGdxx zlshJyzmGdxx) {
		String id = "";
		if(!StringUtils.isEmpty(zlshJyzmGdxx.getId())){
			id = zlshJyzmGdxx.getId();
			jyzmGdxxDao.deleteById(id);
		}
		return id;
	}
}

package com.creditharmony.approve.verify.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.common.constants.RefuseConstants;
import com.creditharmony.approve.management.dao.GlRefuseDao;
import com.creditharmony.approve.verify.dao.JkProductsDao;
import com.creditharmony.approve.verify.dao.ReconsiderApplyDao;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.verify.entity.JkProducts;
import com.creditharmony.approve.verify.entity.ReconsiderApply;
import com.creditharmony.approve.verify.entity.ex.ReconsiderFinalCheckEx;
import com.creditharmony.core.common.type.UseFlag;
import com.creditharmony.core.lend.type.LendConstants;

/**
 * 终审决策
 * @Class Name ReconsiderFinalExamineService
 * @author 李文勇
 * @Create In 2015年12月3日
 */
@Service
public class ReconsiderFinalExamineService {

	@Autowired
	private ReconsiderApplyDao reconsiderApplyDao;
	@Autowired
	private GlRefuseDao glRefuseDao;
	@Autowired
	private JkProductsDao jkProductsDao;
	
	/**
	 * 复议终审 决策页面显示
	 * 2015年12月9日
	 * By 李文勇
	 * @param loanCode
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public ReconsiderFinalCheckEx  getCheckInfo(String loanCode) throws IllegalAccessException, InvocationTargetException{
		ReconsiderFinalCheckEx  reconsiderFinalCheckEx = new ReconsiderFinalCheckEx();
		// 复议原因
		ReconsiderApply reconsiderApply = reconsiderApplyDao.getByLoanCode(loanCode);
		reconsiderFinalCheckEx.setReconsiderApply(reconsiderApply);
		// 一级拒绝码
		GlRefuse glRefuse = new GlRefuse();
		glRefuse.setParentIds(RefuseConstants.ROOT);
		List<GlRefuse> refuseCode = glRefuseDao.getDataByParentId(glRefuse);
		reconsiderFinalCheckEx.setGlRefuseList(refuseCode);
		// 产品
		List<JkProducts> products = jkProductsDao.getAllProducts(UseFlag.QY.value,LendConstants.PRODUCTS_TYPE_LOAN_CREDIT);
		reconsiderFinalCheckEx.setProductsList(products);
		
		return reconsiderFinalCheckEx;
	}
	
	/**
	 * 获取拒绝码
	 * 2015年12月16日
	 * By 李文勇
	 * @param parentId
	 * @return
	 */
	public List<GlRefuse> getRefuseCode(String parentId){
		GlRefuse glRefuse = new GlRefuse();
		glRefuse.setParentIds(parentId);
		glRefuse.setRefuseGrade(RefuseConstants.TYPE_APPROVE);
		return glRefuseDao.getDataByParentId( glRefuse );
	}
	
	/**
	 * 获取全部产品
	 * 2015年12月22日
	 * By 李文勇
	 * @return
	 */
	public List<JkProducts> getAllProducts(){
		return jkProductsDao.getAllProducts(UseFlag.QY.value,LendConstants.PRODUCTS_TYPE_LOAN_CREDIT);
	}
	
	/**
	 * 获取是否为复议
	 * @return
	 */
	public ReconsiderApply getByApplyId(String applyId){
		return reconsiderApplyDao.getByApplyId(applyId);
	}
	
}

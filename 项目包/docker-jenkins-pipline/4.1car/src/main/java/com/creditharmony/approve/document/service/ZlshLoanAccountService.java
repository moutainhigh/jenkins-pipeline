package com.creditharmony.approve.document.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.document.dao.ZlshLoanAccountDao;
import com.creditharmony.approve.document.entity.ZlshLoanAccount;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;

/**
 * 资料审核 账户信息
 * @Class Name ZlshLoanAccountService
 * @author 路志友
 * @Create In 2015年12月24日
 */
@Service
public class ZlshLoanAccountService {

	@Autowired
	private ZlshLoanAccountDao zlshLoanAccountDao;

	/**
	 * 保存/修改银行卡流水
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshLoanAccount
	 * @param vex
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String saveLoanAccount(ZlshLoanAccount zlshLoanAccount,VerifyParamEx vex) {
		String id = zlshLoanAccount.getId();
		zlshLoanAccount.setLoanCode(vex.getLoanCode());
		zlshLoanAccount.setRCustomerCoborrowerId(vex.getRelId());
		zlshLoanAccount.setDictCustomerType(vex.getType());
		zlshLoanAccount.setDictCheckType(vex.getCheckType());
		// 如果【同业借款】为“0”即无则清空【月划扣金额】和【月划扣数量】
		if(NumberConstants.ZERO_STRING.equals(zlshLoanAccount.getOtherLoanMark())){
			zlshLoanAccount.setMonthUseMoney(null);
			zlshLoanAccount.setMonthUseNum(null);
		}
		
		// 没有ID插入数据
		if(id == null || id.equals("")){
			zlshLoanAccount.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
			zlshLoanAccount.preInsert();
			zlshLoanAccountDao.insertLoanAccount(zlshLoanAccount);
			id = zlshLoanAccount.getId();
		}else{// 有ID更新数据
			zlshLoanAccount.preUpdate();
			zlshLoanAccountDao.updateLoanAccount(zlshLoanAccount);
		}
		return id;
	}
	
	/**
	 * 删除银行卡流水
	 * 2015年12月24日
	 * By 路志友
	 * @param zlshLoanAccount
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String deleteBankCard(ZlshLoanAccount zlshLoanAccount) {
		String id = "";
		if(!StringUtils.isEmpty(zlshLoanAccount.getId())){
			 id = zlshLoanAccount.getId();
			zlshLoanAccountDao.deleteBankCard(id);
		}
		return id;
	}
	
    /**
     * 查找对公账户或对私账户
     * 2015年12月23日
     * By 路志友
     * @param record
     * @return List
     */
	public List<ZlshLoanAccount> getListByLoanCode(ZlshLoanAccount record) {
		return zlshLoanAccountDao.getListByLoanCode(record);
	}

	
}

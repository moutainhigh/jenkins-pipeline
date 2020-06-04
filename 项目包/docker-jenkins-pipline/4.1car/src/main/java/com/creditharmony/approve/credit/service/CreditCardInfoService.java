package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.CreditCardInfoDao;
import com.creditharmony.approve.credit.entity.CreditCardInfo;

/**
 * 简版信用卡明细信息Service
 * @Class Name CreditCardInfoService
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Service
public class CreditCardInfoService extends  CoreManager<CreditCardInfoDao,CreditCardInfo> {
	
    /**
     * 根据个人征信简版id检索信用卡信息List
     * 2016年2月2日
     * By zhanghu
     * @param creditReportSimpleId
     * @return 信用卡信息List
     */
	public List<CreditCardInfo> selectByCreditCardInfo(String creditReportSimpleId) {
		CreditCardInfo creditCardInfo = new CreditCardInfo();
		creditCardInfo.setRelationId(creditReportSimpleId);
		return this.dao.selectByCreditCardInfo(creditCardInfo);
	}

	
	/**
	 * 根据个人征信简版id删除信用卡信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param relationId 个人征信简版id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteByRelationId(String relationId) {
		return this.dao.deleteByRelationId(relationId);
	}
	
	/**
	 * 根据个人征信简版id删除信用卡信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param relationId 个人征信简版id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int insertCreditCardInfo(CreditCardInfo creditCardInfo) {
		// 初始化默认数据
		creditCardInfo.preInsert();
		return this.dao.insertCreditCardInfo(creditCardInfo);
	}

	/**
	 * 根据id删除信用卡信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteCardInfoById(String id) {
		return this.dao.deleteByPrimaryKey(id);
	}

}

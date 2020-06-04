package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.constants.EnterpriseCreditConstants;
import com.creditharmony.approve.credit.dao.CreditCurrentLiabilityInfoDao;
import com.creditharmony.approve.credit.entity.CreditCurrentLiabilityInfo;
import com.creditharmony.approve.credit.entity.EnterpriseCredit;

/**
 * 基础信息Service
 * @Class Name CreditCurrentLiabilityInfo
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Service
public class CreditCurrentLiabilityInfoService extends  CoreManager<CreditCurrentLiabilityInfoDao,CreditCurrentLiabilityInfo> {
	
	/**
	 * 保存基础信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 基础信息
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int saveCreditCurrentLiabilityInfo(CreditCurrentLiabilityInfo record) {
		if (record.getIsNewRecord()){
			// 初始化默认数据
			record.preInsert();
			return this.dao.insertCreditCurrentLiabilityInfo(record);
		}else{
			record.preUpdate();
			return this.dao.updateByPrimaryKeySelective(record);
		}
	}
    
    /**
     * 根据借款编码检索基础信息
     * 2016年2月2日
     * By zhanghu
     * @param loanCode 借款编码
     * @return 信息List
     */
	public List<CreditCurrentLiabilityInfo> selectByLoanCode(String loanCode) {
		return this.dao.selectByLoanCode(loanCode);

	}
	
    /**
     * 初始化信息
     * 2016年2月2日
     * By zhanghu
     * @param enterpriseCredit
     */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public void initByEnterpriseCredit(EnterpriseCredit enterpriseCredit) {
		
		String loanCode = enterpriseCredit.getLoanCode();//借款编码
		
		CreditCurrentLiabilityInfo creditCurrentLiabilityInfo = new CreditCurrentLiabilityInfo();
		creditCurrentLiabilityInfo.setLoanCode(loanCode);
		
		creditCurrentLiabilityInfo.setInfoSummary(EnterpriseCreditConstants.CREDITCURRENTLIABILITYINFO_SUMMARY_1);//信息概要
		creditCurrentLiabilityInfo.setSort(Integer.valueOf(1));//排序
		this.saveCreditCurrentLiabilityInfo(creditCurrentLiabilityInfo);
		creditCurrentLiabilityInfo.setId("");//清空id
		
		creditCurrentLiabilityInfo.setInfoSummary(EnterpriseCreditConstants.CREDITCURRENTLIABILITYINFO_SUMMARY_2);//信息概要
		creditCurrentLiabilityInfo.setSort(Integer.valueOf(2));//排序
		this.saveCreditCurrentLiabilityInfo(creditCurrentLiabilityInfo);
		creditCurrentLiabilityInfo.setId("");//清空id
		
		creditCurrentLiabilityInfo.setInfoSummary(EnterpriseCreditConstants.CREDITCURRENTLIABILITYINFO_SUMMARY_3);//信息概要
		creditCurrentLiabilityInfo.setSort(Integer.valueOf(3));//排序
		this.saveCreditCurrentLiabilityInfo(creditCurrentLiabilityInfo);
		
	}

}

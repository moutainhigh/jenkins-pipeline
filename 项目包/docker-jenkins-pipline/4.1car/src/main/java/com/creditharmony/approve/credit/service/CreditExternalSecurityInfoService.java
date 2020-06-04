package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.constants.EnterpriseCreditConstants;
import com.creditharmony.approve.credit.dao.CreditExternalSecurityInfoDao;
import com.creditharmony.approve.credit.entity.CreditExternalSecurityInfo;
import com.creditharmony.approve.credit.entity.EnterpriseCredit;

/**
 * 企业征信_当前负债信息明细Service
 * @Class Name CreditExternalSecurityInfo
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Service
public class CreditExternalSecurityInfoService extends  CoreManager<CreditExternalSecurityInfoDao,CreditExternalSecurityInfo> {
	
	/**
	 * 保存信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int saveCreditExternalSecurityInfo(CreditExternalSecurityInfo record) {
		if (record.getIsNewRecord()){
			// 初始化默认数据
			record.preInsert();
			return this.dao.insertCreditExternalSecurityInfo(record);
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
	public List<CreditExternalSecurityInfo> selectByLoanCode(String loanCode) {
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
		
		CreditExternalSecurityInfo CreditExternalSecurityInfo = new CreditExternalSecurityInfo();
		CreditExternalSecurityInfo.setLoanCode(loanCode);
		
		CreditExternalSecurityInfo.setInfoSummary(EnterpriseCreditConstants.CREDITEXTERNALSECURITYINFO_SUMMARY_1);//信息概要
		CreditExternalSecurityInfo.setSort(Integer.valueOf(1));//排序
		this.saveCreditExternalSecurityInfo(CreditExternalSecurityInfo);
		CreditExternalSecurityInfo.setId("");//清空id
		
		CreditExternalSecurityInfo.setInfoSummary(EnterpriseCreditConstants.CREDITEXTERNALSECURITYINFO_SUMMARY_2);//信息概要
		CreditExternalSecurityInfo.setSort(Integer.valueOf(2));//排序
		this.saveCreditExternalSecurityInfo(CreditExternalSecurityInfo);
		CreditExternalSecurityInfo.setId("");//清空id
		
		CreditExternalSecurityInfo.setInfoSummary(EnterpriseCreditConstants.CREDITEXTERNALSECURITYINFO_SUMMARY_3);//信息概要
		CreditExternalSecurityInfo.setSort(Integer.valueOf(3));//排序
		this.saveCreditExternalSecurityInfo(CreditExternalSecurityInfo);
		
	}

}

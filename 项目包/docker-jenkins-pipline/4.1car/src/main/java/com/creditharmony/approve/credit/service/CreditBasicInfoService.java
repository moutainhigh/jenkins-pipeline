package com.creditharmony.approve.credit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.CreditBasicInfoDao;
import com.creditharmony.approve.credit.entity.CreditBasicInfo;

/**
 * 基础信息Service
 * @Class Name CreditBasicInfoService
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Service
public class CreditBasicInfoService extends  CoreManager<CreditBasicInfoDao,CreditBasicInfo> {
	
	/**
	 * 保存基础信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 基础信息
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int saveCreditBasicInfo(CreditBasicInfo record) {
		if (record.getIsNewRecord()){
			// 初始化默认数据
			record.preInsert();
			return this.dao.insertCreditBasicInfo(record);
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
     * @return 基础信息
     */
	public CreditBasicInfo selectByLoanCode(String loanCode) {
		return this.dao.selectByLoanCode(loanCode);

	}

}

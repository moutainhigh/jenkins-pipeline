package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.CreditInvestorInfoDao;
import com.creditharmony.approve.credit.entity.CreditInvestorInfo;

/**
 * 出资人信息Service
 * @Class Name CreditInvestorInfoService
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Service
public class CreditInvestorInfoService extends  CoreManager<CreditInvestorInfoDao,CreditInvestorInfo> {

	/**
	 * 根据出资人信息id删除出资人信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param id 出资人信息id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteByPrimaryKey(String id) {
		return this.dao.deleteByPrimaryKey(id);
	}
	
	/**
	 * 新增出资人信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 出资人信息
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int insertCreditInvestorInfo(CreditInvestorInfo record) {
		// 初始化默认数据
		record.preInsert();
		return this.dao.insertCreditInvestorInfo(record);
	}
    
    /**
     * 根据借款编码检索出资人信息List
     * 2016年2月2日
     * By zhanghu
     * @param loanCode 借款编码
     * @return 出资人信息List
     */
	public List<CreditInvestorInfo> selectByLoanCode(String loanCode) {
		return this.dao.selectByLoanCode(loanCode);
	}

	/**
	 * 根据借款编码删除出资人信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param loanCode 借款编码
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteByLoanCode(String loanCode) {
		return this.dao.deleteByLoanCode(loanCode);
	}
	
}

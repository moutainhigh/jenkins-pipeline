package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.CreditExecutiveInfoDao;
import com.creditharmony.approve.credit.entity.CreditExecutiveInfo;

/**
 * 高管人员信息Service
 * @Class Name CreditExecutiveInfoService
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Service
public class CreditExecutiveInfoService extends  CoreManager<CreditExecutiveInfoDao,CreditExecutiveInfo> {

	/**
	 * 根据高管人员信息id删除高管人员信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param id 高管人员信息id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteByPrimaryKey(String id) {
		return this.dao.deleteByPrimaryKey(id);
	}
	
	/**
	 * 新增高管人员信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 高管人员信息
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int insertCreditExecutiveInfo(CreditExecutiveInfo record) {
		// 初始化默认数据
		record.preInsert();
		return this.dao.insertCreditExecutiveInfo(record);
	}
    
    /**
     * 根据借款编码检索高管人员信息List
     * 2016年2月2日
     * By zhanghu
     * @param loanCode 借款编码
     * @return 高管人员信息List
     */
	public List<CreditExecutiveInfo> selectByLoanCode(String loanCode) {
		return this.dao.selectByLoanCode(loanCode);
	}

	/**
	 * 根据借款编码删除高管人员信息
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

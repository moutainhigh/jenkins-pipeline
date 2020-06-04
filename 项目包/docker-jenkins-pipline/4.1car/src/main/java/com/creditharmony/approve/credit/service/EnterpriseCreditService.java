package com.creditharmony.approve.credit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.credit.dao.EnterpriseCreditDao;
import com.creditharmony.approve.credit.entity.EnterpriseCredit;

/**
 * 出资人信息Service
 * @Class Name EnterpriseCreditService
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Service
public class EnterpriseCreditService extends  CoreManager<EnterpriseCreditDao,EnterpriseCredit> {
	
    /**
     * 新增所有列数据
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 返回条数
     */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int insertEnterpriseCredit(EnterpriseCredit record) {
		// 初始化默认数据
		record.preInsert();
		return this.dao.insertEnterpriseCredit(record);
	}
	
    /**
     * 更新所有列数据
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 返回条数
     */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int updateEnterpriseCredit(EnterpriseCredit record) {
		// 初始化默认数据
		record.preUpdate();
		return this.dao.updateByPrimaryKeySelective(record);
	}

    
    /**
     * 根据企业征信信息对象查询征信信息
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 企业征信信息对象
     */
	public EnterpriseCredit selectByEnterpriseCredit(EnterpriseCredit record) {
		return this.dao.selectByEnterpriseCredit(record);
	}
	
    /**
     * 根据企业征信信息对象查询征信信息
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 企业征信信息对象
     */
	public EnterpriseCredit selectEnterpriseCredit(EnterpriseCredit record) {
		return this.dao.selectEnterpriseCredit(record);
	}

}

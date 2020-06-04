package com.creditharmony.approve.verify.dao;

import com.creditharmony.approve.verify.entity.AuditRecord;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 审批记录表
 * @Class Name AuditRecordDao
 * @author 刘燕军
 * @Create In 2015年12月9日
 */
@LoanBatisDao
public interface AuditRecordDao extends CrudDao<AuditRecord>{
	
	/**
	 * 插入审批记录表
	 * 2015年12月28日
	 * By 刘燕军
	 * @param record
	 * @return 插入的行数
	 */
    public int insertAuditRecord(AuditRecord record);
    
    
    /**
     * 通过借款编号和创建人获取审核对应的关联id
     * 2016年2月24日
     * By 刘燕军
     * @param userId
     * @param loanCode
     * @return 关联id
     */
    public String findId(String userId,String loanCode);
}
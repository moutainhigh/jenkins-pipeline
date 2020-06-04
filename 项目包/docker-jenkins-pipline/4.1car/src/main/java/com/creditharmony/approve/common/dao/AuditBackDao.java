package com.creditharmony.approve.common.dao;

import com.creditharmony.approve.common.entity.AuditBack;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 审核回退
 * @Class Name AuditBackDao
 * @author 赖敏
 * @Create In 2015年12月23日
 */
@LoanBatisDao
public interface AuditBackDao extends CrudDao<AuditBack>{
	
	/**
	 * 插入回退清单
	 * 2015年12月28日
	 * By 赖敏
	 * @param record
	 * @return 更新的行数
	 */
    public int insertAuditBack(AuditBack record);
    
    /**
     * 根据历史ID获取回退清单
     * 2015年12月26日
     * By 赖敏
     * @param relationId 关联ID(变更历史表)
     * @return 历史回退清单
     */
    public AuditBack getById(String relationId);
    
}
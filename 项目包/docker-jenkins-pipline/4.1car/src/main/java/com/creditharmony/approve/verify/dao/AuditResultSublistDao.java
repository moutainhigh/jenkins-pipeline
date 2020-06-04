package com.creditharmony.approve.verify.dao;

import java.util.List;

import com.creditharmony.approve.verify.entity.AuditResultSublist;
import com.creditharmony.approve.verify.entity.ex.AuditResultSublistEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 初审决策，拒绝时，保存拒接码
 * @Class Name AuditResultSublistDao
 * @author 刘燕军
 * @Create In 2015年12月4日
 */
@LoanBatisDao
public interface AuditResultSublistDao extends CrudDao<AuditResultSublist>{
   
    /**
     * 保存 
     * 2015年12月23日
     * By 刘燕军
     * @param record
     * @return 插入行数
     */
	public int insert(AuditResultSublist record);
   
    /**
     * 保存决策结果数据 
     * 2015年12月23日
     * By 刘燕军
     * @param record
     * @return 插入行数
     */
    public int insertSelective(AuditResultSublist record);
    
    /**
     * 根据关联ID获取拒借码列表（审核结果表）
     * 2016年1月11日
     * By 赖敏
     * @param rId
     * @return 拒借码列表
     */
    public List<AuditResultSublistEx> getByRid(String rId);
}
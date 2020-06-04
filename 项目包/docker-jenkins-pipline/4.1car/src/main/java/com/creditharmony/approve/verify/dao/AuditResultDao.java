package com.creditharmony.approve.verify.dao;

import java.util.List;

import com.creditharmony.approve.verify.entity.AuditResult;
import com.creditharmony.approve.verify.entity.AuditResultSublist;
import com.creditharmony.approve.verify.entity.ex.AuditResultEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 保存决策结果
 * @Class Name AuditResultDao
 * @author 刘燕军
 * @Create In 2015年12月4日
 */
@LoanBatisDao
public interface AuditResultDao extends CrudDao<AuditResult>{
    
    /**
     * 决策页面中的审核历史
     * 2015年12月26日
     * By 刘燕军
     * @param param
     * @return 对应的结果集
     */
    public List<AuditResult> findAuditResults(VerifyParamEx param);
    
    /**
     * 通过借款编号，获取下载意见书中需要的对应的信息
     * 2015年12月8日
     * By 刘燕军
     * @param param
     * @return 对应的结果集
     */
    public AuditResult findResult(VerifyParamEx param);
   
    /**
     * 获取审批人的姓名和部门名称
     * 2015年12月17日
     * By 刘燕军
     * @param param
     * @return 对应的姓名部门集合
     */
    public List<AuditResult> findPeoples(VerifyParamEx param);
   
    /**
     * 通过扩展类 插入表中数据
     * 2015年12月23日
     * By 刘燕军
     * @param param
     * @return 插入的行数
     */
    public int insertByEx(AuditResultEx param);
   
    /**
     * 通过借款编号获取最新的审核结果
     * 2016年1月11日
     * By 刘燕军
     * @param param
     * @return 审核结果
     */
    public AuditResultEx getAuditResult(VerifyParamEx param);

    /**
     * 通过审核结果获取拒借信息
     * 2016年1月12日
     * By 赖敏
     * @param resultId 审批结果ID
     * @return 拒借信息
     */
    public AuditResultEx getRefuseInfo(String resultId);
    
    /**
     * 通过历史ID获取决策信息
     * 2016年1月19日
     * By 李文勇
     * @param param
     * @return 回显决策信息
     */
    public AuditResultEx getCheckInfo(AuditResult param);
    
    /**
     * 根据关联ID查询所有拒绝码
     * 2016年1月19日
     * By 李文勇
     * @param id
     * @return 结果集
     */
    public List<AuditResultSublist> getAllRefuseByRid(String id);

}
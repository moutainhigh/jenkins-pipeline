package com.creditharmony.approve.newCar.service;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.carloan.view.CarVerifyBusinessView;
import com.creditharmony.approve.newCar.entity.NewCarAuditResult;
public interface NewCarAuditResultService{
    
	
    public List<NewCarAuditResult> findCarAuditResultsByLoanCode(String loanCode);

	public void insertCarAuditResult(NewCarAuditResult auditResult);

	public NewCarAuditResult selectLastByLoanCodeAndCheckType(String loanCode,
			String code);
	/**
	 * 车借--审批保存以及退回保存：更新借款信息表、借款状态变更历史表、插入审核结果表
	 * 2016年1月28日
	 * By 申诗阔
	 * @param flowView 审批所需字段 实体
	 * @param stepName 审批步骤名称
	 * @return Map集合 用于后期相关处理逻辑
	 */
	public Map<String, String> auditHandle(CarVerifyBusinessView flowView, String stepName);
	
	public Map<String, String> auditHandleFinal(CarVerifyBusinessView flowView, String stepName);
}
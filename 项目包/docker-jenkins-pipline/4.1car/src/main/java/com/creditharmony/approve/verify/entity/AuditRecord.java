package com.creditharmony.approve.verify.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 审批记录
 * @Class Name AuditRecord
 * @author 刘燕军
 * @Create In 2015年12月9日
 */
public class AuditRecord extends DataEntity<AuditRecord>{

	private static final long serialVersionUID = 1L;

	private String loanCode; // 借款编号 
    private String dictCheckType; // 类型
    private String dictLoanStatus; // 借款状态
    private String transactorCode; // 办理人编号
    private Date transactorTime; // 办理时间
    private String orgCode; // 机构编码
    private String operateStep; // 操作步骤
    private String operateResult; // 操作结果
    private String refuseReason; // 拒绝原因
    
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getDictCheckType() {
		return dictCheckType;
	}
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
	public String getDictLoanStatus() {
		return dictLoanStatus;
	}
	public void setDictLoanStatus(String dictLoanStatus) {
		this.dictLoanStatus = dictLoanStatus;
	}
	public String getTransactorCode() {
		return transactorCode;
	}
	public void setTransactorCode(String transactorCode) {
		this.transactorCode = transactorCode;
	}

	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public Date getTransactorTime() {
		return transactorTime;
	}
	public void setTransactorTime(Date transactorTime) {
		this.transactorTime = transactorTime;
	}
	public String getOperateStep() {
		return operateStep;
	}
	public void setOperateStep(String operateStep) {
		this.operateStep = operateStep;
	}
	public String getOperateResult() {
		return operateResult;
	}
	public void setOperateResult(String operateResult) {
		this.operateResult = operateResult;
	}
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
  

}
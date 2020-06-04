package com.creditharmony.approve.carloan.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class LoanStatusHis extends DataEntity<LoanStatusHis>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 7218932348158522778L;

	private String loanCode;

    private String id;

    private String dictLoanStatus;

    private String operateStep;

    private String dictSysFlag;

    private String operateResult;

    private String operator;

    private String operatorRoleId;

    private String orgCode;

    private Date operateTime;

    private String remark;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDictLoanStatus() {
        return dictLoanStatus;
    }

    public void setDictLoanStatus(String dictLoanStatus) {
        this.dictLoanStatus = dictLoanStatus == null ? null : dictLoanStatus.trim();
    }

    public String getOperateStep() {
        return operateStep;
    }

    public void setOperateStep(String operateStep) {
        this.operateStep = operateStep == null ? null : operateStep.trim();
    }

    public String getDictSysFlag() {
        return dictSysFlag;
    }

    public void setDictSysFlag(String dictSysFlag) {
        this.dictSysFlag = dictSysFlag == null ? null : dictSysFlag.trim();
    }

    public String getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult == null ? null : operateResult.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getOperatorRoleId() {
        return operatorRoleId;
    }

    public void setOperatorRoleId(String operatorRoleId) {
        this.operatorRoleId = operatorRoleId == null ? null : operatorRoleId.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
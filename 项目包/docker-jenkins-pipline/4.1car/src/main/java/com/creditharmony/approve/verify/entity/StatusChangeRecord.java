package com.creditharmony.approve.verify.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class StatusChangeRecord extends DataEntity<StatusChangeRecord> {
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	
    private String applyId;

    private String loanCode;

    private String dictLoanStatus;

    private String operateStep;

    private String dictSysFlag;

    private String operateResult;

    private String operator;

    private String operaterRoleId;

    private String orgCode;

    private Date operateTime;

    private String remark;

	public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId == null ? null : applyId.trim();
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
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
		this.operator = operator;
	}


	public String getOperaterRoleId() {
		return operaterRoleId;
	}


	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public void setOperaterRoleId(String operaterRoleId) {
		this.operaterRoleId = operaterRoleId;
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

}
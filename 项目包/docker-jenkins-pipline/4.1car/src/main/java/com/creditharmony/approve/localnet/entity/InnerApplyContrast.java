package com.creditharmony.approve.localnet.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class InnerApplyContrast  extends DataEntity<InnerApplyContrast>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String loanCode;

    private String loanCustomterCode;

    private String dictCustomerType;

    private String contrastExceptionType;

    private String contrastExceptionMsg;

    private String contrastExceptionReason;

    private Date createTime;

    private String createBy;

    private String modifyBy;

    private Date modifyTime;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getLoanCustomterCode() {
        return loanCustomterCode;
    }

    public void setLoanCustomterCode(String loanCustomterCode) {
        this.loanCustomterCode = loanCustomterCode == null ? null : loanCustomterCode.trim();
    }

    public String getDictCustomerType() {
        return dictCustomerType;
    }

    public void setDictCustomerType(String dictCustomerType) {
        this.dictCustomerType = dictCustomerType == null ? null : dictCustomerType.trim();
    }

    public String getContrastExceptionType() {
        return contrastExceptionType;
    }

    public void setContrastExceptionType(String contrastExceptionType) {
        this.contrastExceptionType = contrastExceptionType == null ? null : contrastExceptionType.trim();
    }

    public String getContrastExceptionMsg() {
        return contrastExceptionMsg;
    }

    public void setContrastExceptionMsg(String contrastExceptionMsg) {
        this.contrastExceptionMsg = contrastExceptionMsg == null ? null : contrastExceptionMsg.trim();
    }

    public String getContrastExceptionReason() {
        return contrastExceptionReason;
    }

    public void setContrastExceptionReason(String contrastExceptionReason) {
        this.contrastExceptionReason = contrastExceptionReason == null ? null : contrastExceptionReason.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
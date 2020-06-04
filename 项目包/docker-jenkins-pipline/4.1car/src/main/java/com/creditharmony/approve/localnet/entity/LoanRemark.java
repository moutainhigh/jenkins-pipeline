package com.creditharmony.approve.localnet.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class LoanRemark extends DataEntity<LoanRemark> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loanCode;

	private String dictRemarkType;

	private String remark;

	private Date remarkTime;

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

	public String getDictRemarkType() {
		return dictRemarkType;
	}

	public void setDictRemarkType(String dictRemarkType) {
		this.dictRemarkType = dictRemarkType == null ? null : dictRemarkType
				.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Date getRemarkTime() {
		return remarkTime;
	}

	public void setRemarkTime(Date remarkTime) {
		this.remarkTime = remarkTime;
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
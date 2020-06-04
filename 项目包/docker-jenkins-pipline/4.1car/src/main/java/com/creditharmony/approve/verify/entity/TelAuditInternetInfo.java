package com.creditharmony.approve.verify.entity;

import com.creditharmony.core.persistence.DataEntity;


public class TelAuditInternetInfo extends DataEntity<TelAuditInternetInfo>{
	
	private static final long serialVersionUID = 8032263764768838865L;

	private String loanCode;

	private String dictCheckType;

	private String rId;

	private String dictCustomerType;

	private String checkType;

	private String checkJson;

	public String getRId() {
		return rId;
	}

	public void setRId(String rId) {
		this.rId = rId;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode == null ? null : loanCode.trim();
	}

	public String getDictCheckType() {
		return dictCheckType;
	}

	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType == null ? null : dictCheckType
				.trim();
	}

	public String getDictCustomerType() {
		return dictCustomerType;
	}

	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType == null ? null
				: dictCustomerType.trim();
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType == null ? null : checkType.trim();
	}

	public String getCheckJson() {
		return checkJson;
	}

	public void setCheckJson(String checkJson) {
		this.checkJson = checkJson;
	}



}
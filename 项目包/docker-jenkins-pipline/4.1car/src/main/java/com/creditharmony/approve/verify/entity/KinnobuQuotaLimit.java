package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;


public class KinnobuQuotaLimit extends  DataEntity<KinnobuQuotaLimit>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal kinnobuQuotaLimit;				// 金信上限额度
	private int version;								// 版本号
	private String kinnobuUsingFlag;					// 启用标志
	private BigDecimal useMoney;						// 审批额度
	
	public BigDecimal getKinnobuQuotaLimit() {
		return kinnobuQuotaLimit;
	}
	public void setKinnobuQuotaLimit(BigDecimal kinnobuQuotaLimit) {
		this.kinnobuQuotaLimit = kinnobuQuotaLimit;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getKinnobuUsingFlag() {
		return kinnobuUsingFlag;
	}
	public void setKinnobuUsingFlag(String kinnobuUsingFlag) {
		this.kinnobuUsingFlag = kinnobuUsingFlag;
	}
	public BigDecimal getUseMoney() {
		return useMoney;
	}
	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}
}

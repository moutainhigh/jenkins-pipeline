package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

public class TrusteeshipQuotaLimit extends DataEntity<TrusteeshipQuotaLimit>{

	
	private static final long serialVersionUID = 1L;	//
	private BigDecimal trusteeshipQuotaLimit;			//上限额度
	private Integer version;							//版本号
	private BigDecimal trusteeshipUsingFlag;			//启用标识
	private BigDecimal useMoney;						//审批额度
	public BigDecimal getTrusteeshipQuotaLimit() {
		return trusteeshipQuotaLimit;
	}
	public void setTrusteeshipQuotaLimit(BigDecimal trusteeshipQuotaLimit) {
		this.trusteeshipQuotaLimit = trusteeshipQuotaLimit;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public BigDecimal getTrusteeshipUsingFlag() {
		return trusteeshipUsingFlag;
	}
	public void setTrusteeshipUsingFlag(BigDecimal trusteeshipUsingFlag) {
		this.trusteeshipUsingFlag = trusteeshipUsingFlag;
	}
	public BigDecimal getUseMoney() {
		return useMoney;
	}
	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}
}

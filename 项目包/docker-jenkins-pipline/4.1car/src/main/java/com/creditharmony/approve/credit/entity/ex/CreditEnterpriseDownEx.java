package com.creditharmony.approve.credit.entity.ex;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.creditharmony.approve.credit.entity.CreditCurrentLiabilityDetail;

/**
 * 企业征信核查下载意见书扩展类
 * @Class Name CreditEnterpriseDownEx
 * @author 李文勇
 * @Create In 2016年2月25日
 */
public class CreditEnterpriseDownEx {

	private List<CreditCurrentLiabilityDetail> currentLiabilityDetailLis;		// 企业征信_当前负债信息明细
	private String version;						// 征信版本
	private String orgNum;						// 机构数量
	private Date firstTime;						// 首笔时间
	private Date nearCreditTime;				// 最近授信时间
	private BigDecimal nearCreditMoney;			// 最近授信金额
	private BigDecimal nearDueMoney;			// 最近到期金额
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getOrgNum() {
		return orgNum;
	}
	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}
	public List<CreditCurrentLiabilityDetail> getCurrentLiabilityDetailLis() {
		return currentLiabilityDetailLis;
	}
	public void setCurrentLiabilityDetailLis(
			List<CreditCurrentLiabilityDetail> currentLiabilityDetailLis) {
		this.currentLiabilityDetailLis = currentLiabilityDetailLis;
	}
	public Date getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}
	public Date getNearCreditTime() {
		return nearCreditTime;
	}
	public void setNearCreditTime(Date nearCreditTime) {
		this.nearCreditTime = nearCreditTime;
	}
	public BigDecimal getNearCreditMoney() {
		return nearCreditMoney;
	}
	public void setNearCreditMoney(BigDecimal nearCreditMoney) {
		this.nearCreditMoney = nearCreditMoney;
	}
	public BigDecimal getNearDueMoney() {
		return nearDueMoney;
	}
	public void setNearDueMoney(BigDecimal nearDueMoney) {
		this.nearDueMoney = nearDueMoney;
	}
}

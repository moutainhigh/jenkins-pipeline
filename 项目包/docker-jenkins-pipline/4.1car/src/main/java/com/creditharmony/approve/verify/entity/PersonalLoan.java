package com.creditharmony.approve.verify.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 个人征信报告实体
 * 
 * @Class Name PersonalLoan
 * @author 刘燕军
 * @Create In 2015年12月21日
 */
public class PersonalLoan extends DataEntity<PersonalLoan> {
	private static final long serialVersionUID = 1L;
	private String customerCode; // 客户编码
	private String loanCode; // 借款编码
	private String creditVersion; // 征信版本 0:详版 1:简版
	private String mechanismCount; // 贷款机构数量
	private String repaymentInfo; // 逾期情况说明
	private String createBy2; // 审核人员编号
	private String createOrg; // 审核人员机构
	private String createTime2; // 审核时间
	private String lastModifyBy; // 修改人员编号
	private String lastModifyOrg; // 修改人员机构
	private String lastModifyTime; // 修改时间
	private String firstLoanTime; // 首笔贷款发放时间
	private String recentlyLoanTime; // 最近一次授信时间
	private String recentlyLoanMoney; // 最近一次授信金额
	private String recentlyExpireLoan; // 最近一个月到期贷款笔数
	private String recentlyExpireMoney; // 最近一个月到期贷款金额

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getCreditVersion() {
		return creditVersion;
	}

	public void setCreditVersion(String creditVersion) {
		this.creditVersion = creditVersion;
	}

	public String getMechanismCount() {
		return mechanismCount;
	}

	public void setMechanismCount(String mechanismCount) {
		this.mechanismCount = mechanismCount;
	}

	public String getRepaymentInfo() {
		return repaymentInfo;
	}

	public void setRepaymentInfo(String repaymentInfo) {
		this.repaymentInfo = repaymentInfo;
	}

	public String getCreateBy2() {
		return createBy2;
	}

	public void setCreateBy2(String createBy2) {
		this.createBy2 = createBy2;
	}

	public String getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}

	public String getCreateTime2() {
		return createTime2;
	}

	public void setCreateTime2(String createTime2) {
		this.createTime2 = createTime2;
	}

	public String getLastModifyBy() {
		return lastModifyBy;
	}

	public void setLastModifyBy(String lastModifyBy) {
		this.lastModifyBy = lastModifyBy;
	}

	public String getLastModifyOrg() {
		return lastModifyOrg;
	}

	public void setLastModifyOrg(String lastModifyOrg) {
		this.lastModifyOrg = lastModifyOrg;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getFirstLoanTime() {
		return firstLoanTime;
	}

	public void setFirstLoanTime(String firstLoanTime) {
		this.firstLoanTime = firstLoanTime;
	}

	public String getRecentlyLoanTime() {
		return recentlyLoanTime;
	}

	public void setRecentlyLoanTime(String recentlyLoanTime) {
		this.recentlyLoanTime = recentlyLoanTime;
	}

	public String getRecentlyLoanMoney() {
		return recentlyLoanMoney;
	}

	public void setRecentlyLoanMoney(String recentlyLoanMoney) {
		this.recentlyLoanMoney = recentlyLoanMoney;
	}

	public String getRecentlyExpireLoan() {
		return recentlyExpireLoan;
	}

	public void setRecentlyExpireLoan(String recentlyExpireLoan) {
		this.recentlyExpireLoan = recentlyExpireLoan;
	}

	public String getRecentlyExpireMoney() {
		return recentlyExpireMoney;
	}

	public void setRecentlyExpireMoney(String recentlyExpireMoney) {
		this.recentlyExpireMoney = recentlyExpireMoney;
	}

}

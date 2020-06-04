package com.creditharmony.approve.verify.entity.ex;

import com.creditharmony.bpm.frame.view.BaseTaskItemView;

public class VerifyFlowTaskItemEx extends BaseTaskItemView{
	private String customerName; //客户姓名
	private String mateCertNum; //证件号码
	private String addrCity; //城市
	private String loanCode; //借款编号
	private String addrProvince; //省份
	private String contStoresId; //门店
	private String addrCityName; //
	private String addrProvinceName; //
	private String contStoresIdName; //门店名称
	private String borrowProduct; //产品
	private String borrowProductName; //
	private String loanIsUrgent;//加急标志
	private String loanIsUrgentName; //
	private Double loanApplyMoney;//申请金额
	private Integer loanMonths;//借款期数
	private String offendSalesName;//客户经理
	private String loanTeamEmpcode;//团队经理
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMateCertNum() {
		return mateCertNum;
	}
	public void setMateCertNum(String mateCertNum) {
		this.mateCertNum = mateCertNum;
	}
	public String getAddrCity() {
		return addrCity;
	}
	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getAddrProvince() {
		return addrProvince;
	}
	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}
	public String getContStoresId() {
		return contStoresId;
	}
	public void setContStoresId(String contStoresId) {
		this.contStoresId = contStoresId;
	}
	public String getAddrCityName() {
		return addrCityName;
	}
	public void setAddrCityName(String addrCityName) {
		this.addrCityName = addrCityName;
	}
	public String getAddrProvinceName() {
		return addrProvinceName;
	}
	public void setAddrProvinceName(String addrProvinceName) {
		this.addrProvinceName = addrProvinceName;
	}
	public String getContStoresIdName() {
		return contStoresIdName;
	}
	public void setContStoresIdName(String contStoresIdName) {
		this.contStoresIdName = contStoresIdName;
	}
	public String getBorrowProduct() {
		return borrowProduct;
	}
	public void setBorrowProduct(String borrowProduct) {
		this.borrowProduct = borrowProduct;
	}
	public String getBorrowProductName() {
		return borrowProductName;
	}
	public void setBorrowProductName(String borrowProductName) {
		this.borrowProductName = borrowProductName;
	}
	public String getLoanIsUrgent() {
		return loanIsUrgent;
	}
	public void setLoanIsUrgent(String loanIsUrgent) {
		this.loanIsUrgent = loanIsUrgent;
	}
	public String getLoanIsUrgentName() {
		return loanIsUrgentName;
	}
	public void setLoanIsUrgentName(String loanIsUrgentName) {
		this.loanIsUrgentName = loanIsUrgentName;
	}
	public Double getLoanApplyMoney() {
		return loanApplyMoney;
	}
	public void setLoanApplyMoney(Double loanApplyMoney) {
		this.loanApplyMoney = loanApplyMoney;
	}
	public Integer getLoanMonths() {
		return loanMonths;
	}
	public void setLoanMonths(Integer loanMonths) {
		this.loanMonths = loanMonths;
	}
	public String getOffendSalesName() {
		return offendSalesName;
	}
	public void setOffendSalesName(String offendSalesName) {
		this.offendSalesName = offendSalesName;
	}
	public String getLoanTeamEmpcode() {
		return loanTeamEmpcode;
	}
	public void setLoanTeamEmpcode(String loanTeamEmpcode) {
		this.loanTeamEmpcode = loanTeamEmpcode;
	}
	
}

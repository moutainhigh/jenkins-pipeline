package com.creditharmony.approve.antifraud.entity.ex;

import java.util.Date;

/**
 * 反欺诈已办列表 Ex
 * @Class Name AntifraudListEx
 * @author 赖敏
 * @Create In 2015年11月30日
 */
public class AntifraudListEx {
	
	private String loanCode; //借款编码
	private String customerName; //客户姓名
	private String mateCertNum; //证件号码
	private String addrProvince; //省份
	private String addrCity; //城市
	private String contStoresName; //门店
	private String borrowProduct; //产品
	private String loanIsUrgent; //是否加急
	private String loanApplyMoney;; //申请金额
	private String loanMonths; //分期
	private String loanTeamEmpcode; //团队经理
	private String offendSalesName; //客户经理
	private Date customerIntoTime; //进件时间
	private String dictStatus; // 状态
	private Date singleTime; //分单时间
	private String applyId; //流程ID
	
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
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
	public String getAddrProvince() {
		return addrProvince;
	}
	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}
	public String getAddrCity() {
		return addrCity;
	}
	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	public String getContStoresName() {
		return contStoresName;
	}
	public void setContStoresName(String contStoresName) {
		this.contStoresName = contStoresName;
	}
	public String getBorrowProduct() {
		return borrowProduct;
	}
	public void setBorrowProduct(String borrowProduct) {
		this.borrowProduct = borrowProduct;
	}
	public String getLoanIsUrgent() {
		return loanIsUrgent;
	}
	public void setLoanIsUrgent(String loanIsUrgent) {
		this.loanIsUrgent = loanIsUrgent;
	}
	public String getLoanApplyMoney() {
		return loanApplyMoney;
	}
	public void setLoanApplyMoney(String loanApplyMoney) {
		this.loanApplyMoney = loanApplyMoney;
	}
	public String getLoanMonths() {
		return loanMonths;
	}
	public void setLoanMonths(String loanMonths) {
		this.loanMonths = loanMonths;
	}
	public String getLoanTeamEmpcode() {
		return loanTeamEmpcode;
	}
	public void setLoanTeamEmpcode(String loanTeamEmpcode) {
		this.loanTeamEmpcode = loanTeamEmpcode;
	}
	public String getOffendSalesName() {
		return offendSalesName;
	}
	public void setOffendSalesName(String offendSalesName) {
		this.offendSalesName = offendSalesName;
	}
	public Date getCustomerIntoTime() {
		return customerIntoTime;
	}
	public void setCustomerIntoTime(Date customerIntoTime) {
		this.customerIntoTime = customerIntoTime;
	}
	public String getDictStatus() {
		return dictStatus;
	}
	public void setDictStatus(String dictStatus) {
		this.dictStatus = dictStatus;
	}
	public Date getSingleTime() {
		return singleTime;
	}
	public void setSingleTime(Date singleTime) {
		this.singleTime = singleTime;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
}

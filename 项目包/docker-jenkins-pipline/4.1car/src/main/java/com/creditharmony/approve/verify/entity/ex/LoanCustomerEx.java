package com.creditharmony.approve.verify.entity.ex;

import com.creditharmony.approve.verify.entity.LoanCustomer;

public class LoanCustomerEx extends LoanCustomer {

	private static final long serialVersionUID = 1L;
	private String compName;			// 单位名称
	private String compTel;				// 单位电话
	private String compAddress;			// 单位地址
	private String pCompName;			// 配偶单位名称
	private String mateTel;				// 配偶电话
	private String mateCertNum;			// 配偶证件号码
	private String mateName;			// 配偶名称
	private String livingAddr;		// 居住地址
	private String registAddr;		// 户籍地址
	private String dictDepartment;	// 所属部门
	private String dictPositionLevel;	// 职位级别
	private String monthlyPay;	// 月收入
	private String isOtherRevenue;	// 其他收入
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompTel() {
		return compTel;
	}
	public void setCompTel(String compTel) {
		this.compTel = compTel;
	}
	public String getCompAddress() {
		return compAddress;
	}
	public void setCompAddress(String compAddress) {
		this.compAddress = compAddress;
	}
	public String getpCompName() {
		return pCompName;
	}
	public void setpCompName(String pCompName) {
		this.pCompName = pCompName;
	}
	public String getMateTel() {
		return mateTel;
	}
	public void setMateTel(String mateTel) {
		this.mateTel = mateTel;
	}
	public String getMateCertNum() {
		return mateCertNum;
	}
	public void setMateCertNum(String mateCertNum) {
		this.mateCertNum = mateCertNum;
	}
	public String getMateName() {
		return mateName;
	}
	public void setMateName(String mateName) {
		this.mateName = mateName;
	}
	public String getLivingAddr() {
		return livingAddr;
	}
	public void setLivingAddr(String livingAddr) {
		this.livingAddr = livingAddr;
	}
	public String getRegistAddr() {
		return registAddr;
	}
	public void setRegistAddr(String registAddr) {
		this.registAddr = registAddr;
	}
	public String getDictDepartment() {
		return dictDepartment;
	}
	public void setDictDepartment(String dictDepartment) {
		this.dictDepartment = dictDepartment;
	}
	public String getDictPositionLevel() {
		return dictPositionLevel;
	}
	public void setDictPositionLevel(String dictPositionLevel) {
		this.dictPositionLevel = dictPositionLevel;
	}
	public String getMonthlyPay() {
		return monthlyPay;
	}
	public void setMonthlyPay(String monthlyPay) {
		this.monthlyPay = monthlyPay;
	}
	public String getIsOtherRevenue() {
		return isOtherRevenue;
	}
	public void setIsOtherRevenue(String isOtherRevenue) {
		this.isOtherRevenue = isOtherRevenue;
	}
	
}

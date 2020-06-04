package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CompManage extends DataEntity<CompManage> {
    private String id;
    private String loanCode;
    private String businessLicenceRegisterNum;//营业执照注册号
    private Date compCreateDate;//成立日期
    private String compType;//企业类型
    private BigDecimal averageMonthTurnover;//平均月营业额
    private String manageBusiness;//主营业务 
    private BigDecimal compRegisterCapital;//企业注册资本
    private BigDecimal customerRatioComp;// 申请人占股比例
    private String corporateRepresent;//法人代表
    private String certNum;//身份证号码
    private String manageAddressProvince;//省
    private String manageAddressCity;//市
    private String manageAddressArea;//区
    private String manageAddress;//经营地址
    private BigDecimal businessArea;//营业面积
    private String corporateRepresentMobile;//法定代表人手机号
    private String compEmail;//企业邮箱
    private String createBy;
    private Date createTime;
    private String modifyBy;
    private Date modifyTime;
    private BigDecimal monthRentMoney;//月租金
    private BigDecimal monthPayMoney;//月还款
    private String managePlace;       //
    private String creditCode;       //信用代码
    private String orgCode;       // 组织机构代码
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getBusinessLicenceRegisterNum() {
		return businessLicenceRegisterNum;
	}
	public void setBusinessLicenceRegisterNum(String businessLicenceRegisterNum) {
		this.businessLicenceRegisterNum = businessLicenceRegisterNum;
	}
	public Date getCompCreateDate() {
		return compCreateDate;
	}
	public void setCompCreateDate(Date compCreateDate) {
		this.compCreateDate = compCreateDate;
	}
	public String getCompType() {
		return compType;
	}
	public void setCompType(String compType) {
		this.compType = compType;
	}
	public BigDecimal getAverageMonthTurnover() {
		return averageMonthTurnover;
	}
	public void setAverageMonthTurnover(BigDecimal averageMonthTurnover) {
		this.averageMonthTurnover = averageMonthTurnover;
	}
	public BigDecimal getCompRegisterCapital() {
		return compRegisterCapital;
	}
	public void setCompRegisterCapital(BigDecimal compRegisterCapital) {
		this.compRegisterCapital = compRegisterCapital;
	}
	public BigDecimal getCustomerRatioComp() {
		return customerRatioComp;
	}
	public void setCustomerRatioComp(BigDecimal customerRatioComp) {
		this.customerRatioComp = customerRatioComp;
	}
	public String getCorporateRepresent() {
		return corporateRepresent;
	}
	public void setCorporateRepresent(String corporateRepresent) {
		this.corporateRepresent = corporateRepresent;
	}
	public String getCertNum() {
		return certNum;
	}
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
	public String getManageAddressProvince() {
		return manageAddressProvince;
	}
	public void setManageAddressProvince(String manageAddressProvince) {
		this.manageAddressProvince = manageAddressProvince;
	}
	public String getManageAddressCity() {
		return manageAddressCity;
	}
	public void setManageAddressCity(String manageAddressCity) {
		this.manageAddressCity = manageAddressCity;
	}
	public String getManageAddressArea() {
		return manageAddressArea;
	}
	public void setManageAddressArea(String manageAddressArea) {
		this.manageAddressArea = manageAddressArea;
	}
	public String getManageAddress() {
		return manageAddress;
	}
	public void setManageAddress(String manageAddress) {
		this.manageAddress = manageAddress;
	}
	public BigDecimal getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(BigDecimal businessArea) {
		this.businessArea = businessArea;
	}
	public String getCorporateRepresentMobile() {
		return corporateRepresentMobile;
	}
	public void setCorporateRepresentMobile(String corporateRepresentMobile) {
		this.corporateRepresentMobile = corporateRepresentMobile;
	}
	public String getCompEmail() {
		return compEmail;
	}
	public void setCompEmail(String compEmail) {
		this.compEmail = compEmail;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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
		this.modifyBy = modifyBy;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public BigDecimal getMonthRentMoney() {
		return monthRentMoney;
	}
	public void setMonthRentMoney(BigDecimal monthRentMoney) {
		this.monthRentMoney = monthRentMoney;
	}
	public BigDecimal getMonthPayMoney() {
		return monthPayMoney;
	}
	public void setMonthPayMoney(BigDecimal monthPayMoney) {
		this.monthPayMoney = monthPayMoney;
	}
	public String getManageBusiness() {
		return manageBusiness;
	}
	public void setManageBusiness(String manageBusiness) {
		this.manageBusiness = manageBusiness;
	}
	public String getManagePlace() {
		return managePlace;
	}
	public void setManagePlace(String managePlace) {
		this.managePlace = managePlace;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}
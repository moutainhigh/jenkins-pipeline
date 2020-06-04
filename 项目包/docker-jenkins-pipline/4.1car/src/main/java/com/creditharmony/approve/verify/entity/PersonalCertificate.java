package com.creditharmony.approve.verify.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class PersonalCertificate extends DataEntity<PersonalCertificate> {
    private String id;
    private String loanCode;
    private String customerRelMaster;//申请人与户主关系
    private String customerRelMasterRemark;//申请人与户主关系备注
    private String masterName;//户主姓名
    private String masterCertNum;//户主身份证号码
    private String masterAddressProvince;//户籍省
    private String masterAddressCity;//户籍市
    private String masterAddressArea;//户籍区
    private String masterAddress;//户籍区
    private String childrenName;//子女姓名
    private String childrenCertNum;//子女身份证号码
    private String educationalCertificateType;//学历证书类型
    private String educationalSchool;//学历证书类型
    private String educationalCertificateNum;//学历证书类型
    private Date educationalCertificateTime;//学历证书类型
    private Date weddingTime;//结婚日期 
    private String licenseIssuingAgency;//发证机构 

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

	public String getCustomerRelMaster() {
		return customerRelMaster;
	}

	public void setCustomerRelMaster(String customerRelMaster) {
		this.customerRelMaster = customerRelMaster;
	}

	public String getCustomerRelMasterRemark() {
		return customerRelMasterRemark;
	}

	public void setCustomerRelMasterRemark(String customerRelMasterRemark) {
		this.customerRelMasterRemark = customerRelMasterRemark;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getMasterCertNum() {
		return masterCertNum;
	}

	public void setMasterCertNum(String masterCertNum) {
		this.masterCertNum = masterCertNum;
	}

	public String getMasterAddressProvince() {
		return masterAddressProvince;
	}

	public void setMasterAddressProvince(String masterAddressProvince) {
		this.masterAddressProvince = masterAddressProvince;
	}

	public String getMasterAddressCity() {
		return masterAddressCity;
	}

	public void setMasterAddressCity(String masterAddressCity) {
		this.masterAddressCity = masterAddressCity;
	}

	public String getMasterAddressArea() {
		return masterAddressArea;
	}

	public void setMasterAddressArea(String masterAddressArea) {
		this.masterAddressArea = masterAddressArea;
	}

	public String getMasterAddress() {
		return masterAddress;
	}

	public void setMasterAddress(String masterAddress) {
		this.masterAddress = masterAddress;
	}

	public String getChildrenName() {
		return childrenName;
	}

	public void setChildrenName(String childrenName) {
		this.childrenName = childrenName;
	}

	public String getChildrenCertNum() {
		return childrenCertNum;
	}

	public void setChildrenCertNum(String childrenCertNum) {
		this.childrenCertNum = childrenCertNum;
	}

	public String getEducationalCertificateType() {
		return educationalCertificateType;
	}

	public void setEducationalCertificateType(String educationalCertificateType) {
		this.educationalCertificateType = educationalCertificateType;
	}

	public String getEducationalSchool() {
		return educationalSchool;
	}

	public void setEducationalSchool(String educationalSchool) {
		this.educationalSchool = educationalSchool;
	}

	public String getEducationalCertificateNum() {
		return educationalCertificateNum;
	}

	public void setEducationalCertificateNum(String educationalCertificateNum) {
		this.educationalCertificateNum = educationalCertificateNum;
	}

	public Date getEducationalCertificateTime() {
		return educationalCertificateTime;
	}

	public void setEducationalCertificateTime(Date educationalCertificateTime) {
		this.educationalCertificateTime = educationalCertificateTime;
	}

	public Date getWeddingTime() {
		return weddingTime;
	}

	public void setWeddingTime(Date weddingTime) {
		this.weddingTime = weddingTime;
	}

	public String getLicenseIssuingAgency() {
		return licenseIssuingAgency;
	}

	public void setLicenseIssuingAgency(String licenseIssuingAgency) {
		this.licenseIssuingAgency = licenseIssuingAgency;
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

}
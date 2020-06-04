package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 职业信息
 * 
 * @Class Name LoanCompany
 * @author 刘燕军
 * @Create In 2015年12月8日
 * @update in 2016-09-20
 */
public class LoanCompany extends DataEntity<LoanCompany> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String rId;

	private String loanCode;

	private String compName;

	private String dictCompType;

	private String compPostCode;

	private String compProvince;

	private String compCity;

	private String compArer;

	private String compAddress;

	private String compWebsite;

	private String compTel;

	private BigDecimal compUnitScale;

	private String compDepartment;

	private String compWorkExperience;

	private String dictCompIndustry;

	private String compPost;

	private Date compSalaryDay;

	private BigDecimal compOtherMoney;

	private Date compEntryTimestamp;

	private String dictSalaryPay;

	private String compCustomterType;

	private String createBy;

	private Date createTime;

	private String modifyBy;

	private Date modifyTime;

	private Date compEntryDate;

	public Date getCompEntryDate() {
		return compEntryDate;
	}

	public void setCompEntryDate(Date compEntryDate) {
		this.compEntryDate = compEntryDate;
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode == null ? null : loanCode.trim();
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName == null ? null : compName.trim();
	}

	public String getDictCompType() {
		return dictCompType;
	}

	public void setDictCompType(String dictCompType) {
		this.dictCompType = dictCompType == null ? null : dictCompType.trim();
	}

	public String getCompPostCode() {
		return compPostCode;
	}

	public void setCompPostCode(String compPostCode) {
		this.compPostCode = compPostCode == null ? null : compPostCode.trim();
	}

	public String getCompProvince() {
		return compProvince;
	}

	public void setCompProvince(String compProvince) {
		this.compProvince = compProvince == null ? null : compProvince.trim();
	}

	public String getCompCity() {
		return compCity;
	}

	public void setCompCity(String compCity) {
		this.compCity = compCity == null ? null : compCity.trim();
	}

	public String getCompArer() {
		return compArer;
	}

	public void setCompArer(String compArer) {
		this.compArer = compArer == null ? null : compArer.trim();
	}

	public String getCompAddress() {
		return compAddress;
	}

	public void setCompAddress(String compAddress) {
		this.compAddress = compAddress == null ? null : compAddress.trim();
	}

	public String getCompWebsite() {
		return compWebsite;
	}

	public void setCompWebsite(String compWebsite) {
		this.compWebsite = compWebsite == null ? null : compWebsite.trim();
	}

	public String getCompTel() {
		return compTel;
	}

	public void setCompTel(String compTel) {
		this.compTel = compTel == null ? null : compTel.trim();
	}

	public BigDecimal getCompUnitScale() {
		return compUnitScale;
	}

	public void setCompUnitScale(BigDecimal compUnitScale) {
		this.compUnitScale = compUnitScale;
	}

	public String getCompDepartment() {
		return compDepartment;
	}

	public void setCompDepartment(String compDepartment) {
		this.compDepartment = compDepartment == null ? null : compDepartment.trim();
	}

	public String getCompWorkExperience() {
		return compWorkExperience;
	}

	public void setCompWorkExperience(String compWorkExperience) {
		this.compWorkExperience = compWorkExperience == null ? null : compWorkExperience.trim();
	}

	public String getDictCompIndustry() {
		return dictCompIndustry;
	}

	public void setDictCompIndustry(String dictCompIndustry) {
		this.dictCompIndustry = dictCompIndustry == null ? null : dictCompIndustry.trim();
	}

	public String getCompPost() {
		return compPost;
	}

	public void setCompPost(String compPost) {
		this.compPost = compPost == null ? null : compPost.trim();
	}

	public Date getCompSalaryDay() {
		return compSalaryDay;
	}

	public void setCompSalaryDay(Date compSalaryDay) {
		this.compSalaryDay = compSalaryDay;
	}

	public BigDecimal getCompOtherMoney() {
		return compOtherMoney;
	}

	public void setCompOtherMoney(BigDecimal compOtherMoney) {
		this.compOtherMoney = compOtherMoney;
	}

	public Date getCompEntryTimestamp() {
		return compEntryTimestamp;
	}

	public void setCompEntryTimestamp(Date compEntryTimestamp) {
		this.compEntryTimestamp = compEntryTimestamp;
	}

	public String getDictSalaryPay() {
		return dictSalaryPay;
	}

	public void setDictSalaryPay(String dictSalaryPay) {
		this.dictSalaryPay = dictSalaryPay == null ? null : dictSalaryPay.trim();
	}

	public String getCompCustomterType() {
		return compCustomterType;
	}

	public void setCompCustomterType(String compCustomterType) {
		this.compCustomterType = compCustomterType == null ? null : compCustomterType.trim();
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
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
		this.modifyBy = modifyBy == null ? null : modifyBy.trim();
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 月税后工资(元)
	 */
	private BigDecimal compSalary;
	/**
	 * 前单位名称
	 */
	private String previousCompName;

	/**
	 * 月税后工资(元)
	 * 
	 * @return
	 */
	public BigDecimal getCompSalary() {
		return compSalary;
	}

	/**
	 * 月税后工资(元)
	 * 
	 * @param compSalary
	 */
	public void setCompSalary(BigDecimal compSalary) {
		this.compSalary = compSalary;
	}

	/**
	 * 前单位名称
	 * 
	 * @return
	 */
	public String getPreviousCompName() {
		return previousCompName;
	}

	/**
	 * 前单位名称
	 * 
	 * @param previousCompName
	 */
	public void setPreviousCompName(String previousCompName) {
		this.previousCompName = previousCompName;
	}

	/**
	 * 法人代表人姓名
	 */
	private String comLegalMan;
	/**
	 * 法人代表人身份证件号
	 */
	private String comLegalManNum;
	/**
	 * 法人代表人手机号
	 */
	private String comLegalManMoblie;
	/**
	 * 企业邮箱
	 */
	private String comEmail;

	/**
	 * 法人代表人姓名
	 */
	public String getComLegalMan() {
		return comLegalMan;
	}

	/**
	 * 法人代表人姓名
	 */
	public void setComLegalMan(String comLegalMan) {
		this.comLegalMan = comLegalMan;
	}

	/**
	 * 法人代表人身份证件号
	 */
	public String getComLegalManNum() {
		return comLegalManNum;
	}

	/**
	 * 法人代表人身份证件号
	 */
	public void setComLegalManNum(String comLegalManNum) {
		this.comLegalManNum = comLegalManNum;
	}

	/**
	 * 法人代表人手机号
	 */
	public String getComLegalManMoblie() {
		return comLegalManMoblie;
	}

	/**
	 * 法人代表人手机号
	 */
	public void setComLegalManMoblie(String comLegalManMoblie) {
		this.comLegalManMoblie = comLegalManMoblie;
	}

	/**
	 * 企业邮箱
	 */
	public String getComEmail() {
		return comEmail;
	}

	/**
	 * 企业邮箱
	 */
	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}

	/**
	 * 平均月营业额(万元)
	 */
	private BigDecimal averageMonthTurnover;

	/**
	 * 平均月营业额(万元)
	 */
	public BigDecimal getAverageMonthTurnover() {
		return averageMonthTurnover;
	}

	/**
	 * 平均月营业额(万元)
	 */
	public void setAverageMonthTurnover(BigDecimal averageMonthTurnover) {
		this.averageMonthTurnover = averageMonthTurnover;
	}

	/**
	 * 网查结果（法人代表人姓名）
	 */
	private String comLegalManResult;
	/**
	 * 网查备注（法人代表人姓名）
	 */
	private String comLegalManRemark;
	/**
	 * 网查结果（法人代表人身份证件号）
	 */
	private String comLegalManNumResult;
	/**
	 * 网查备注（法人代表人身份证件号）
	 */
	private String comLegalManNumRemark;
	/**
	 * 网查结果（法人代表人手机号）
	 */
	private String comLegalManMoblieResult;
	/**
	 * 网查备注（法人代表人手机号）
	 */
	private String comLegalManMoblieRemark;
	/**
	 * 网查结果（企业邮箱）
	 */
	private String comEmailNetResult;
	/**
	 * 网查备注（企业邮箱）
	 */
	private String comEmailNetRemark;

	/**
	 * 网查结果（法人代表人姓名）
	 */
	public String getComLegalManResult() {
		return comLegalManResult;
	}

	/**
	 * 网查结果（法人代表人姓名）
	 */
	public void setComLegalManResult(String comLegalManResult) {
		this.comLegalManResult = comLegalManResult;
	}

	/**
	 * 网查备注（法人代表人姓名）
	 */
	public String getComLegalManRemark() {
		return comLegalManRemark;
	}

	/**
	 * 网查备注（法人代表人姓名）
	 */
	public void setComLegalManRemark(String comLegalManRemark) {
		this.comLegalManRemark = comLegalManRemark;
	}

	/**
	 * 网查结果（法人代表人身份证件号）
	 */
	public String getComLegalManNumResult() {
		return comLegalManNumResult;
	}

	/**
	 * 网查结果（法人代表人身份证件号）
	 */
	public void setComLegalManNumResult(String comLegalManNumResult) {
		this.comLegalManNumResult = comLegalManNumResult;
	}

	/**
	 * 网查备注（法人代表人身份证件号）
	 */
	public String getComLegalManNumRemark() {
		return comLegalManNumRemark;
	}

	/**
	 * 网查备注（法人代表人身份证件号）
	 */
	public void setComLegalManNumRemark(String comLegalManNumRemark) {
		this.comLegalManNumRemark = comLegalManNumRemark;
	}

	/**
	 * 网查结果（法人代表人手机号）
	 */
	public String getComLegalManMoblieResult() {
		return comLegalManMoblieResult;
	}

	/**
	 * 网查结果（法人代表人手机号）
	 */
	public void setComLegalManMoblieResult(String comLegalManMoblieResult) {
		this.comLegalManMoblieResult = comLegalManMoblieResult;
	}

	/**
	 * 网查备注（法人代表人手机号）
	 */
	public String getComLegalManMoblieRemark() {
		return comLegalManMoblieRemark;
	}

	/**
	 * 网查备注（法人代表人手机号）
	 */
	public void setComLegalManMoblieRemark(String comLegalManMoblieRemark) {
		this.comLegalManMoblieRemark = comLegalManMoblieRemark;
	}

	/**
	 * 网查结果（企业邮箱）
	 */
	public String getComEmailNetResult() {
		return comEmailNetResult;
	}

	/**
	 * 网查结果（企业邮箱）
	 */
	public void setComEmailNetResult(String comEmailNetResult) {
		this.comEmailNetResult = comEmailNetResult;
	}

	/**
	 * 网查备注（企业邮箱）
	 */
	public String getComEmailNetRemark() {
		return comEmailNetRemark;
	}

	/**
	 * 网查备注（企业邮箱）
	 */
	public void setComEmailNetRemark(String comEmailNetRemark) {
		this.comEmailNetRemark = comEmailNetRemark;
	}

}
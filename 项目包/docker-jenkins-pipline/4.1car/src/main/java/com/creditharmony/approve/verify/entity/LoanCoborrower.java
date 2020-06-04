package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @author 罗俊平
 * @update in 2016-10-14
 */
public class LoanCoborrower extends DataEntity<LoanCoborrower> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String loanCode;

	private String coboName;

	private String coboAge;

	private String coboSex;

	private String dictCertType;

	private String coboCertNum;

	private String idStartDay;

	private String idEndDay;

	private Date coboBirthday;

	private String dictEducation;

	private String customerGraduationDay;

	private String coboHouseHoldHold;

	private String coboHouseholdProvince;

	private String coboHouseholdCity;

	private String coboHouseholdArea;

	private String coboHouseholdAddress;

	private String coboFamilyTel;

	private String coboMobile;

	private String coboMobile2;

	private String coboLiveingProvince;

	private String coboLiveingCity;

	private String coboLiveingArea;

	private String coboNowAddress;

	private String coboNowTel;

	private String coboEmail;

	private String dictMarryStatus;

	private String coboHaveChildFlag;

	private String coboContactIsKnow;

	private BigDecimal dictRelationType;

	private String dictRelationCustomer;

	private BigDecimal coboHouseOtTer;

	private BigDecimal coboSocialSecurity;

	private String customerFirtArriveYear;

	private String customerHouseHoldProperty;

	private String coboCompAddress;

	private String coboCompProvince;

	private String coboCompCity;

	private String coboCompArea;

	private String coboCompName;

	private String coboQq;

	private String coboWeibo;
	
    private String socialSecurityNumber;//社保卡号
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

	public String getCoboName() {
		return coboName;
	}

	public void setCoboName(String coboName) {
		this.coboName = coboName;
	}

	public String getCoboAge() {
		return coboAge;
	}

	public void setCoboAge(String coboAge) {
		this.coboAge = coboAge;
	}

	public String getCoboSex() {
		return coboSex;
	}

	public void setCoboSex(String coboSex) {
		this.coboSex = coboSex;
	}

	public String getDictCertType() {
		return dictCertType;
	}

	public void setDictCertType(String dictCertType) {
		this.dictCertType = dictCertType;
	}

	public String getCoboCertNum() {
		return coboCertNum;
	}

	public void setCoboCertNum(String coboCertNum) {
		this.coboCertNum = coboCertNum;
	}

	public String getIdStartDay() {
		return idStartDay;
	}

	public void setIdStartDay(String idStartDay) {
		this.idStartDay = idStartDay;
	}

	public String getIdEndDay() {
		return idEndDay;
	}

	public void setIdEndDay(String idEndDay) {
		this.idEndDay = idEndDay;
	}

	public Date getCoboBirthday() {
		return coboBirthday;
	}

	public void setCoboBirthday(Date coboBirthday) {
		this.coboBirthday = coboBirthday;
	}

	public String getDictEducation() {
		return dictEducation;
	}

	public void setDictEducation(String dictEducation) {
		this.dictEducation = dictEducation;
	}

	public String getCustomerGraduationDay() {
		return customerGraduationDay;
	}

	public void setCustomerGraduationDay(String customerGraduationDay) {
		this.customerGraduationDay = customerGraduationDay;
	}

	public String getCoboHouseHoldHold() {
		return coboHouseHoldHold;
	}

	public void setCoboHouseHoldHold(String coboHouseHoldHold) {
		this.coboHouseHoldHold = coboHouseHoldHold;
	}

	public String getCoboHouseholdProvince() {
		return coboHouseholdProvince;
	}

	public void setCoboHouseholdProvince(String coboHouseholdProvince) {
		this.coboHouseholdProvince = coboHouseholdProvince;
	}

	public String getCoboHouseholdCity() {
		return coboHouseholdCity;
	}

	public void setCoboHouseholdCity(String coboHouseholdCity) {
		this.coboHouseholdCity = coboHouseholdCity;
	}

	public String getCoboHouseholdArea() {
		return coboHouseholdArea;
	}

	public void setCoboHouseholdArea(String coboHouseholdArea) {
		this.coboHouseholdArea = coboHouseholdArea;
	}

	public String getCoboHouseholdAddress() {
		return coboHouseholdAddress;
	}

	public void setCoboHouseholdAddress(String coboHouseholdAddress) {
		this.coboHouseholdAddress = coboHouseholdAddress;
	}

	public String getCoboFamilyTel() {
		return coboFamilyTel;
	}

	public void setCoboFamilyTel(String coboFamilyTel) {
		this.coboFamilyTel = coboFamilyTel;
	}

	public String getCoboMobile() {
		return coboMobile;
	}

	public void setCoboMobile(String coboMobile) {
		this.coboMobile = coboMobile;
	}

	public String getCoboMobile2() {
		return coboMobile2;
	}

	public void setCoboMobile2(String coboMobile2) {
		this.coboMobile2 = coboMobile2;
	}

	public String getCoboLiveingProvince() {
		return coboLiveingProvince;
	}

	public void setCoboLiveingProvince(String coboLiveingProvince) {
		this.coboLiveingProvince = coboLiveingProvince;
	}

	public String getCoboLiveingCity() {
		return coboLiveingCity;
	}

	public void setCoboLiveingCity(String coboLiveingCity) {
		this.coboLiveingCity = coboLiveingCity;
	}

	public String getCoboLiveingArea() {
		return coboLiveingArea;
	}

	public void setCoboLiveingArea(String coboLiveingArea) {
		this.coboLiveingArea = coboLiveingArea;
	}

	public String getCoboNowAddress() {
		return coboNowAddress;
	}

	public void setCoboNowAddress(String coboNowAddress) {
		this.coboNowAddress = coboNowAddress;
	}

	public String getCoboNowTel() {
		return coboNowTel;
	}

	public void setCoboNowTel(String coboNowTel) {
		this.coboNowTel = coboNowTel;
	}

	public String getCoboEmail() {
		return coboEmail;
	}

	public void setCoboEmail(String coboEmail) {
		this.coboEmail = coboEmail;
	}

	public String getDictMarryStatus() {
		return dictMarryStatus;
	}

	public void setDictMarryStatus(String dictMarryStatus) {
		this.dictMarryStatus = dictMarryStatus;
	}

	public String getCoboHaveChildFlag() {
		return coboHaveChildFlag;
	}

	public void setCoboHaveChildFlag(String coboHaveChildFlag) {
		this.coboHaveChildFlag = coboHaveChildFlag;
	}

	public String getCoboContactIsKnow() {
		return coboContactIsKnow;
	}

	public void setCoboContactIsKnow(String coboContactIsKnow) {
		this.coboContactIsKnow = coboContactIsKnow;
	}

	public BigDecimal getDictRelationType() {
		return dictRelationType;
	}

	public void setDictRelationType(BigDecimal dictRelationType) {
		this.dictRelationType = dictRelationType;
	}

	public String getDictRelationCustomer() {
		return dictRelationCustomer;
	}

	public void setDictRelationCustomer(String dictRelationCustomer) {
		this.dictRelationCustomer = dictRelationCustomer;
	}

	public BigDecimal getCoboHouseOtTer() {
		return coboHouseOtTer;
	}

	public void setCoboHouseOtTer(BigDecimal coboHouseOtTer) {
		this.coboHouseOtTer = coboHouseOtTer;
	}

	public BigDecimal getCoboSocialSecurity() {
		return coboSocialSecurity;
	}

	public void setCoboSocialSecurity(BigDecimal coboSocialSecurity) {
		this.coboSocialSecurity = coboSocialSecurity;
	}

	public String getCustomerFirtArriveYear() {
		return customerFirtArriveYear;
	}

	public void setCustomerFirtArriveYear(String customerFirtArriveYear) {
		this.customerFirtArriveYear = customerFirtArriveYear;
	}

	public String getCustomerHouseHoldProperty() {
		return customerHouseHoldProperty;
	}

	public void setCustomerHouseHoldProperty(String customerHouseHoldProperty) {
		this.customerHouseHoldProperty = customerHouseHoldProperty;
	}

	public String getCoboCompAddress() {
		return coboCompAddress;
	}

	public void setCoboCompAddress(String coboCompAddress) {
		this.coboCompAddress = coboCompAddress;
	}

	public String getCoboCompProvince() {
		return coboCompProvince;
	}

	public void setCoboCompProvince(String coboCompProvince) {
		this.coboCompProvince = coboCompProvince;
	}

	public String getCoboCompCity() {
		return coboCompCity;
	}

	public void setCoboCompCity(String coboCompCity) {
		this.coboCompCity = coboCompCity;
	}

	public String getCoboCompArea() {
		return coboCompArea;
	}

	public void setCoboCompArea(String coboCompArea) {
		this.coboCompArea = coboCompArea;
	}

	public String getCoboCompName() {
		return coboCompName;
	}

	public void setCoboCompName(String coboCompName) {
		this.coboCompName = coboCompName;
	}

	public String getCoboQq() {
		return coboQq;
	}

	public void setCoboQq(String coboQq) {
		this.coboQq = coboQq;
	}

	public String getCoboWeibo() {
		return coboWeibo;
	}

	public void setCoboWeibo(String coboWeibo) {
		this.coboWeibo = coboWeibo;
	}

	/**
	 * 征信用户名
	 */
	private String creditUserName;
	/**
	 * 密码
	 */
	private String creditPassword;
	/**
	 * 授权码
	 */
	private String creditAuthCode;
	/**
	 * 子女人数(人)
	 */
	private int childrenNum;
	/**
	 * 供养人数(人)
	 */
	private int supportNum;
	/**
	 * 个人年收入(万元)
	 */
	private BigDecimal personalYearIncome;
	/**
	 * 家庭月收入(元)
	 */
	private BigDecimal homeMonthIncome;
	/**
	 * 家庭月支出(元)
	 */
	private BigDecimal homeMonthPay;
	/**
	 * 家庭总负债(万元)
	 */
	private BigDecimal homeTotalDebt;

	/**
	 * 征信用户名
	 */
	public String getCreditUserName() {
		return creditUserName;
	}

	/**
	 * 征信用户名
	 */
	public void setCreditUserName(String creditUserName) {
		this.creditUserName = creditUserName;
	}

	/**
	 * 密码
	 */
	public String getCreditPassword() {
		return creditPassword;
	}

	/**
	 * 密码
	 */
	public void setCreditPassword(String creditPassword) {
		this.creditPassword = creditPassword;
	}

	/**
	 * 授权码
	 */
	public String getCreditAuthCode() {
		return creditAuthCode;
	}

	/**
	 * 授权码
	 */
	public void setCreditAuthCode(String creditAuthCode) {
		this.creditAuthCode = creditAuthCode;
	}

	/**
	 * 子女人数(人)
	 */
	public int getChildrenNum() {
		return childrenNum;
	}

	/**
	 * 子女人数(人)
	 */
	public void setChildrenNum(int childrenNum) {
		this.childrenNum = childrenNum;
	}

	/**
	 * 供养人数(人)
	 */
	public int getSupportNum() {
		return supportNum;
	}

	/**
	 * 供养人数(人)
	 */
	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}

	/**
	 * 个人年收入(万元)
	 */
	public BigDecimal getPersonalYearIncome() {
		return personalYearIncome;
	}

	/**
	 * 个人年收入(万元)
	 */
	public void setPersonalYearIncome(BigDecimal personalYearIncome) {
		this.personalYearIncome = personalYearIncome;
	}

	/**
	 * 家庭月收入(元)
	 */
	public BigDecimal getHomeMonthIncome() {
		return homeMonthIncome;
	}

	/**
	 * 家庭月收入(元)
	 */
	public void setHomeMonthIncome(BigDecimal homeMonthIncome) {
		this.homeMonthIncome = homeMonthIncome;
	}

	/**
	 * 家庭月支出(元)
	 */
	public BigDecimal getHomeMonthPay() {
		return homeMonthPay;
	}

	/**
	 * 家庭月支出(元)
	 */
	public void setHomeMonthPay(BigDecimal homeMonthPay) {
		this.homeMonthPay = homeMonthPay;
	}

	/**
	 * 家庭总负债(万元)
	 */
	public BigDecimal getHomeTotalDebt() {
		return homeTotalDebt;
	}

	/**
	 * 家庭总负债(万元)
	 */
	public void setHomeTotalDebt(BigDecimal homeTotalDebt) {
		this.homeTotalDebt = homeTotalDebt;
	}

	/**
	 * 现住宅起始居住日期
	 */
	private Date customerFirstLivingDay;

	/**
	 * 现住宅起始居住日期
	 */
	public Date getCustomerFirstLivingDay() {
		return customerFirstLivingDay;
	}

	/**
	 * 现住宅起始居住日期
	 */
	public void setCustomerFirstLivingDay(Date customerFirstLivingDay) {
		this.customerFirstLivingDay = customerFirstLivingDay;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
    
}
package com.creditharmony.approve.newCar.entity;


import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
import static com.creditharmony.approve.common.util.CryptoUtils.decryptPhones;

/**
 * @author 罗俊平
 * @update in 2016-10-09
 */
public class NewCarCustomer extends DataEntity<NewCarCustomer> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String customerCode;

	private String applyId;

	private String loanCode;

	private String customerName;

	private String dictCertType;

	private String customerCertNum;

	private String customerCertOrg;

	private Date idStartDay;//

	private String idEndDay;//

	private String dictMarryStatus;//

	private String dictEducation;//

	private String customerPhoneFirst;

	private String customerPhoneSecond;

	private String registerProperty;// 户籍性质
	private String socialSecurityNumber;//社保卡号
	private String socialSecurityPassword;//社保卡密码

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

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

	public String getDictCertType() {
		return dictCertType;
	}

	public void setDictCertType(String dictCertType) {
		this.dictCertType = dictCertType;
	}

	public String getCustomerCertNum() {
		return customerCertNum;
	}

	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}

	public String getCustomerCertOrg() {
		return customerCertOrg;
	}

	public void setCustomerCertOrg(String customerCertOrg) {
		this.customerCertOrg = customerCertOrg;
	}

	public Date getIdStartDay() {
		return idStartDay;
	}

	public void setIdStartDay(Date idStartDay) {
		this.idStartDay = idStartDay;
	}

	public String getIdEndDay() {
		return idEndDay;
	}

	public void setIdEndDay(String idEndDay) {
		this.idEndDay = idEndDay;
	}

	public String getDictMarryStatus() {
		return dictMarryStatus;
	}

	public void setDictMarryStatus(String dictMarryStatus) {
		this.dictMarryStatus = dictMarryStatus;
	}

	public String getDictEducation() {
		return dictEducation;
	}

	public void setDictEducation(String dictEducation) {
		this.dictEducation = dictEducation;
	}

	public String getCustomerPhoneFirst() {
		return customerPhoneFirst;
	}

	public void setCustomerPhoneFirst(String customerPhoneFirst) {
		this.customerPhoneFirst = customerPhoneFirst;
	}

	public String getCustomerPhoneSecond() {
		return customerPhoneSecond;
	}

	public void setCustomerPhoneSecond(String customerPhoneSecond) {
		this.customerPhoneSecond = customerPhoneSecond;
	}

	public String getCustomerSex() {
		return customerSex;
	}

	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}

	public String getCustomerRegisterProvince() {
		return customerRegisterProvince;
	}

	public void setCustomerRegisterProvince(String customerRegisterProvince) {
		this.customerRegisterProvince = customerRegisterProvince;
	}

	public String getCustomerRegisterCity() {
		return customerRegisterCity;
	}

	public void setCustomerRegisterCity(String customerRegisterCity) {
		this.customerRegisterCity = customerRegisterCity;
	}

	public String getCustomerRegisterArea() {
		return customerRegisterArea;
	}

	public void setCustomerRegisterArea(String customerRegisterArea) {
		this.customerRegisterArea = customerRegisterArea;
	}

	public String getCustomerRegisterAddress() {
		return customerRegisterAddress;
	}

	public void setCustomerRegisterAddress(String customerRegisterAddress) {
		this.customerRegisterAddress = customerRegisterAddress;
	}

	public Date getCustomerBirthday() {
		return customerBirthday;
	}

	public void setCustomerBirthday(Date customerBirthday) {
		this.customerBirthday = customerBirthday;
	}

	public String getCustomerEname() {
		return customerEname;
	}

	public void setCustomerEname(String customerEname) {
		this.customerEname = customerEname;
	}

	public Date getCustomerGraduationTime() {
		return customerGraduationTime;
	}

	public void setCustomerGraduationTime(Date customerGraduationTime) {
		this.customerGraduationTime = customerGraduationTime;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerFax() {
		return customerFax;
	}

	public void setCustomerFax(String customerFax) {
		this.customerFax = customerFax;
	}

	public String getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	public String getCustomerHaveChildren() {
		return customerHaveChildren;
	}

	public void setCustomerHaveChildren(String customerHaveChildren) {
		this.customerHaveChildren = customerHaveChildren;
	}

	public String getCustomerLiveProvince() {
		return customerLiveProvince;
	}

	public void setCustomerLiveProvince(String customerLiveProvince) {
		this.customerLiveProvince = customerLiveProvince;
	}

	public String getCustomerLiveCity() {
		return customerLiveCity;
	}

	public void setCustomerLiveCity(String customerLiveCity) {
		this.customerLiveCity = customerLiveCity;
	}

	public String getCustomerLiveArea() {
		return customerLiveArea;
	}

	public void setCustomerLiveArea(String customerLiveArea) {
		this.customerLiveArea = customerLiveArea;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getCustomerOther() {
		return customerOther;
	}

	public void setCustomerOther(String customerOther) {
		this.customerOther = customerOther;
	}

	public String getDictCustomerIsDanger() {
		return dictCustomerIsDanger;
	}

	public void setDictCustomerIsDanger(String dictCustomerIsDanger) {
		this.dictCustomerIsDanger = dictCustomerIsDanger;
	}

	public String getDictCustomerDiff() {
		return dictCustomerDiff;
	}

	public void setDictCustomerDiff(String dictCustomerDiff) {
		this.dictCustomerDiff = dictCustomerDiff;
	}

	public String getDictCustomerStatus() {
		return dictCustomerStatus;
	}

	public void setDictCustomerStatus(String dictCustomerStatus) {
		this.dictCustomerStatus = dictCustomerStatus;
	}

	public String getDictCustomerSource() {
		return dictCustomerSource;
	}

	public void setDictCustomerSource(String dictCustomerSource) {
		this.dictCustomerSource = dictCustomerSource;
	}

	public String getCustomerContactIsKnow() {
		return customerContactIsKnow;
	}

	public void setCustomerContactIsKnow(String customerContactIsKnow) {
		this.customerContactIsKnow = customerContactIsKnow;
	}

	public String getDictRelationType() {
		return dictRelationType;
	}

	public void setDictRelationType(String dictRelationType) {
		this.dictRelationType = dictRelationType;
	}

	public String getCustomerGoldFlag() {
		return customerGoldFlag;
	}

	public void setCustomerGoldFlag(String customerGoldFlag) {
		this.customerGoldFlag = customerGoldFlag;
	}

	public String getDictSourceType() {
		return dictSourceType;
	}

	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}

	public String getCustomerTelesalesSource() {
		return customerTelesalesSource;
	}

	public void setCustomerTelesalesSource(String customerTelesalesSource) {
		this.customerTelesalesSource = customerTelesalesSource;
	}

	public String getCustomerTelesalesFlag() {
		return customerTelesalesFlag;
	}

	public void setCustomerTelesalesFlag(String customerTelesalesFlag) {
		this.customerTelesalesFlag = customerTelesalesFlag;
	}

	public String getCustomerTelesalesOrgcode() {
		return customerTelesalesOrgcode;
	}

	public void setCustomerTelesalesOrgcode(String customerTelesalesOrgcode) {
		this.customerTelesalesOrgcode = customerTelesalesOrgcode;
	}

	private String customerSex;

	private String customerRegisterProvince;

	private String customerRegisterCity;

	private String customerRegisterArea;

	private String customerRegisterAddress;

	private Date customerBirthday;

	private String customerEname;

	private Date customerGraduationTime;

	private String customerTel;

	private String customerEmail;

	private String customerFax;

	private String customerSource;

	private String customerHaveChildren;

	private String customerLiveProvince;

	private String customerLiveCity;

	private String customerLiveArea;

	private String customerAddress;

	private String customerStatus;

	private String customerOther;

	private String dictCustomerIsDanger; // 是否风险客户

	private String dictCustomerDiff;// 客户类型

	private String dictCustomerStatus;

	private String dictCustomerSource;

	private String customerContactIsKnow; // 家人是否知息此借款

	private String dictRelationType; // 知情人与本人关系

	private String customerGoldFlag; // 是否开通金账户（0：否，1：是）

	private String dictSourceType; // 来源版本

	private String customerTelesalesSource;

	private String customerTelesalesFlag;

	private String customerTelesalesOrgcode;

	private String coboHouseHoldHold;

	private String isLongTerm;

	private String dictCustomeSource2;

	private String customerTempPermit;

	private String customerHouseHoldProperty;

	private Date customerFirstLivingDay;

	private String cityPhone;

	private String customerFirtArriveYear;

	private BigDecimal creditLine;

	private Integer customerFamilySupport;

	public String getCoboHouseHoldHold() {
		return coboHouseHoldHold;
	}

	public void setCoboHouseHoldHold(String coboHouseHoldHold) {
		this.coboHouseHoldHold = coboHouseHoldHold;
	}

	public String getIsLongTerm() {
		return isLongTerm;
	}

	public void setIsLongTerm(String isLongTerm) {
		this.isLongTerm = isLongTerm;
	}

	public String getDictCustomeSource2() {
		return dictCustomeSource2;
	}

	public void setDictCustomeSource2(String dictCustomeSource2) {
		this.dictCustomeSource2 = dictCustomeSource2;
	}

	public String getCustomerTempPermit() {
		return customerTempPermit;
	}

	public void setCustomerTempPermit(String customerTempPermit) {
		this.customerTempPermit = customerTempPermit;
	}

	public String getCustomerHouseHoldProperty() {
		return customerHouseHoldProperty;
	}

	public void setCustomerHouseHoldProperty(String customerHouseHoldProperty) {
		this.customerHouseHoldProperty = customerHouseHoldProperty;
	}

	public Date getCustomerFirstLivingDay() {
		return customerFirstLivingDay;
	}

	public void setCustomerFirstLivingDay(Date customerFirstLivingDay) {
		this.customerFirstLivingDay = customerFirstLivingDay;
	}

	public String getCityPhone() {
		return cityPhone;
	}

	public void setCityPhone(String cityPhone) {
		this.cityPhone = cityPhone;
	}

	public String getCustomerFirtArriveYear() {
		return customerFirtArriveYear;
	}

	public void setCustomerFirtArriveYear(String customerFirtArriveYear) {
		this.customerFirtArriveYear = customerFirtArriveYear;
	}

	public BigDecimal getCreditLine() {
		return creditLine;
	}

	public void setCreditLine(BigDecimal creditLine) {
		this.creditLine = creditLine;
	}

	public Integer getCustomerFamilySupport() {
		return customerFamilySupport;
	}

	public void setCustomerFamilySupport(Integer customerFamilySupport) {
		this.customerFamilySupport = customerFamilySupport;
	}

	/**
	 * 子女人数(人)
	 */
	private String customerChildrenCount;
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
	 * 教育程度（新版申请表）
	 */
	private String dictEducationNew;

	/**
	 * 子女人数(人)
	 */
	public String getCustomerChildrenCount() {
		return customerChildrenCount;
	}

	/**
	 * 子女人数(人)
	 */
	public void setCustomerChildrenCount(String customerChildrenCount) {
		this.customerChildrenCount = customerChildrenCount;
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
	 * 教育程度（新版申请表）
	 */
	public String getDictEducationNew() {
		return dictEducationNew;
	}

	/**
	 * 教育程度（新版申请表）
	 */
	public void setDictEducationNew(String dictEducationNew) {
		this.dictEducationNew = dictEducationNew;
	}

	/**
	 * 征信用户名
	 */
	private String creditUsername;
	/**
	 * 密码
	 */
	private String creditPassword;
	/**
	 * 身份验证码
	 */
	private String creditAuthCode;

	/**
	 * 征信用户名
	 */
	public String getCreditUsername() {
		return creditUsername;
	}

	/**
	 * 征信用户名
	 */
	public void setCreditUsername(String creditUsername) {
		this.creditUsername = creditUsername;
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
	 * 身份验证码
	 */
	public String getCreditAuthCode() {
		return creditAuthCode;
	}

	/**
	 * 身份验证码
	 */
	public void setCreditAuthCode(String creditAuthCode) {
		this.creditAuthCode = creditAuthCode;
	}

	public String getRegisterProperty() {
		return registerProperty;
	}

	public void setRegisterProperty(String registerProperty) {
		this.registerProperty = registerProperty;
	}

	private String customerQq;
	private String customerWeibo;

	public String getCustomerQq() {
		return customerQq;
	}

	public void setCustomerQq(String customerQq) {
		this.customerQq = customerQq;
	}

	public String getCustomerWeibo() {
		return customerWeibo;
	}

	public void setCustomerWeibo(String customerWeibo) {
		this.customerWeibo = customerWeibo;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getSocialSecurityPassword() {
		return socialSecurityPassword;
	}

	public void setSocialSecurityPassword(String socialSecurityPassword) {
		this.socialSecurityPassword = socialSecurityPassword;
	}
    
}
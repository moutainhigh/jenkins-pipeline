package com.creditharmony.approve.verify.view;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @Class Name ViewDetailView
 * @author liwenyong
 * @Create In 2015年11月28日
 * @update in 2016-09-23
 */
public class ViewDetailView {

	// 申请信息老版（新版为进件信息和借款意愿）
	private Date customerIntoTime; // 进件时间
	private String productType; // 产品类型
	private String dictLoanUse; // 借款用途
	private BigDecimal loanApplyAmount; // 申请金额
	private BigDecimal loanMonths; // 申请分期
	private String loanManagercode; // 销售人员
	private String loanCustomerService; // 客服人员
	private String loanTeamOrgid; // 团队组织机构
	private String remark; // 备注
	private String loanStoreOrgid; // 所在组织机构（进件门店）
	/**
	 * 主要还款来源
	 */
	private String mainPaybackResource;

	/**
	 * 主要还款来源
	 */
	public String getMainPaybackResource() {
		return mainPaybackResource;
	}

	/**
	 * 主要还款来源
	 */
	public void setMainPaybackResource(String mainPaybackResource) {
		this.mainPaybackResource = mainPaybackResource;
	}

	// 客户个人信息
	private String customerName; // 客户名称
	private String dictCertType; // 证件类型
	private String customerCertNum; // 证件号码
	private String customerSex; // 客户性别
	private String customerAge; // 年龄（画面显示用）
	private String dictMarryStatus; // 婚姻状态
	private String customerHaveChildren; // 有无子女
	private String dictEducation; // 学历
	private String customerPhoneFirst; // 手机号
	private String customerPhoneSecond; // 手机号2
	private String customerTel; // 固定电话
	private String customerLiveProvince; // 居住省
	private String customerLiveCity; // 居住市
	private String customerLiveArea; // 居住区
	private String customerAddress; // 居住地址
	private String customerRegisterProvince; // 户籍省
	private String customerRegisterCity; // 户籍市
	private String customerRegisterArea; // 户籍区
	private String customerRegisterAddress; // 户籍地址
	private String tomerFirtArriveYear; // 初到本市时间
	private String customerEmail; // 电子邮箱
	private String customerQq; // qq
	private String customerWeibo; // weibo
	private Date customerFirstLivingDay;
	private String customerFirtArriveYear;
	private String socialSecurityNumber;//社保卡号
	private String socialSecurityPassword;//社保卡密码
	/**
	 * 供养人数
	 */
	private String customerFamilySupport;
	/**
	 * 子女个数
	 */
	private String customerChildrenCount;
	/**
	 * 个人年收入
	 */
	private BigDecimal personalYearIncome;
	/**
	 * 家庭月收入
	 */
	private BigDecimal homeMonthIncome;
	/**
	 * 家庭月支出
	 */
	private BigDecimal homeMonthPay;
	/**
	 * 家庭总负债
	 */
	private BigDecimal homeTotalDebt;
	/**
	 * 教育程度（新版）
	 */
	private String dictEducationNew;

	/**
	 * 教育程度（新版）
	 */
	public String getDictEducationNew() {
		return dictEducationNew;
	}

	/**
	 * 教育程度（新版）
	 */
	public void setDictEducationNew(String dictEducationNew) {
		this.dictEducationNew = dictEducationNew;
	}

	/**
	 * 家庭总负债
	 */
	public BigDecimal getHomeTotalDebt() {
		return homeTotalDebt;
	}

	/**
	 * 家庭总负债
	 */
	public void setHomeTotalDebt(BigDecimal homeTotalDebt) {
		this.homeTotalDebt = homeTotalDebt;
	}

	/**
	 * 家庭月支出
	 */
	public BigDecimal getHomeMonthPay() {
		return homeMonthPay;
	}

	/**
	 * 家庭月支出
	 */
	public void setHomeMonthPay(BigDecimal homeMonthPay) {
		this.homeMonthPay = homeMonthPay;
	}

	/**
	 * 家庭月收入
	 */
	public BigDecimal getHomeMonthIncome() {
		return homeMonthIncome;
	}

	/**
	 * 家庭月收入
	 */
	public void setHomeMonthIncome(BigDecimal homeMonthIncome) {
		this.homeMonthIncome = homeMonthIncome;
	}

	/**
	 * 个人年收入
	 */
	public BigDecimal getPersonalYearIncome() {
		return personalYearIncome;
	}

	/**
	 * 个人年收入
	 */
	public void setPersonalYearIncome(BigDecimal personalYearIncome) {
		this.personalYearIncome = personalYearIncome;
	}

	/**
	 * 子女个数
	 */
	public String getCustomerChildrenCount() {
		return customerChildrenCount;
	}

	/**
	 * 子女个数
	 */
	public void setCustomerChildrenCount(String customerChildrenCount) {
		this.customerChildrenCount = customerChildrenCount;
	}

	/**
	 * 供养人数
	 */
	public String getCustomerFamilySupport() {
		return customerFamilySupport;
	}

	/**
	 * 供养人数
	 */
	public void setCustomerFamilySupport(String customerFamilySupport) {
		this.customerFamilySupport = customerFamilySupport;
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
	 * 授权码
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

	public Date getCustomerFirstLivingDay() {
		return customerFirstLivingDay;
	}

	public void setCustomerFirstLivingDay(Date customerFirstLivingDay) {
		this.customerFirstLivingDay = customerFirstLivingDay;
	}

	public String getCustomerFirtArriveYear() {
		return customerFirtArriveYear;
	}

	public void setCustomerFirtArriveYear(String customerFirtArriveYear) {
		this.customerFirtArriveYear = customerFirtArriveYear;
	}

	// 单位信息
	private String compName; // 单位名称
	private String dictCompType; // 单位性质
	private Date compEntryDate; // 入职时间
	private String compDepartment; // 部门
	private String compPost; // 职务
	private String compTel; // 单位电话
	private String compProvince; // 单位省
	private String compCity; // 单位市
	private String compArer; // 单位区
	private String compAddress; // 单位详细地址
	private String compWorkExperience; // 工作年限
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

	// 居住信息
	private String customerResidential; // 居住地址
	private String customerHouseHoldProperty; // 住房性质
	private String customerHousingSituation; // 房产状况
	// 房产信息
	private String dictHouseType; // 房产类型
	private BigDecimal houseBuilingArea; // 房屋面积
	private Date propertyGetDay; // 产权取得时间
	private String houseAddress; // 房产地址
	private String HousePropertyRight; // 产权人
	private String HousePropertyRelation; // 与共有人关系
	private String HousePledgeMark; // 抵押情况
	private String houseProvince; // 房产所在省
	private String houseCity; // 房产所在市
	private String houseArea; // 房产所在区
	private Date houseBuyday; // 购房日期
	private String housePledgeFlag; // 抵押标志
	// 配偶信息
	private String mateName; // 配偶姓名
	private String mateCertType; // 证件类型
	private String mateCertNum; // 证件号码
	private String mateTel; // 手机
	private String comLegalMan; // 法人姓名
	private String comLegalManNum; // 法人身份证号
	private String comLegalManMoblie; // 法人手机号
	private String comEmail; // 法人企业邮
	/**
	 * 宅电
	 */
	private String homeTel;
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
	 * 宅电
	 * 
	 * @return
	 */
	public String getHomeTel() {
		return homeTel;
	}

	/**
	 * 宅电
	 * 
	 * @param homeTel
	 */
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getDictCertType() {
		return dictCertType;
	}

	public void setDictCertType(String dictCertType) {
		this.dictCertType = dictCertType;
	}

	public String getHousePropertyRight() {
		return HousePropertyRight;
	}

	public void setHousePropertyRight(String housePropertyRight) {
		HousePropertyRight = housePropertyRight;
	}

	public String getHousePropertyRelation() {
		return HousePropertyRelation;
	}

	public void setHousePropertyRelation(String housePropertyRelation) {
		HousePropertyRelation = housePropertyRelation;
	}

	public String getHousePledgeMark() {
		return HousePledgeMark;
	}

	public void setHousePledgeMark(String housePledgeMark) {
		HousePledgeMark = housePledgeMark;
	}

	public String getDictHouseType() {
		return dictHouseType;
	}

	public void setDictHouseType(String dictHouseType) {
		this.dictHouseType = dictHouseType;
	}

	public BigDecimal getHouseBuilingArea() {
		return houseBuilingArea;
	}

	public void setHouseBuilingArea(BigDecimal houseBuilingArea) {
		this.houseBuilingArea = houseBuilingArea;
	}

	public Date getPropertyGetDay() {
		return propertyGetDay;
	}

	public void setPropertyGetDay(Date propertyGetDay) {
		this.propertyGetDay = propertyGetDay;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getCustomerResidential() {
		return customerResidential;
	}

	public void setCustomerResidential(String customerResidential) {
		this.customerResidential = customerResidential;
	}

	public String getCustomerHouseHoldProperty() {
		return customerHouseHoldProperty;
	}

	public void setCustomerHouseHoldProperty(String customerHouseHoldProperty) {
		this.customerHouseHoldProperty = customerHouseHoldProperty;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLoanTeamOrgid() {
		return loanTeamOrgid;
	}

	public void setLoanTeamOrgid(String loanTeamOrgid) {
		this.loanTeamOrgid = loanTeamOrgid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCertNum() {
		return customerCertNum;
	}

	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}

	public String getCustomerSex() {
		return customerSex;
	}

	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}

	public String getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(String customerAge) {
		this.customerAge = customerAge;
	}

	public String getDictMarryStatus() {
		return dictMarryStatus;
	}

	public void setDictMarryStatus(String dictMarryStatus) {
		this.dictMarryStatus = dictMarryStatus;
	}

	public String getCustomerHaveChildren() {
		return customerHaveChildren;
	}

	public void setCustomerHaveChildren(String customerHaveChildren) {
		this.customerHaveChildren = customerHaveChildren;
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

	public String getCustomerRegisterAddress() {
		return customerRegisterAddress;
	}

	public void setCustomerRegisterAddress(String customerRegisterAddress) {
		this.customerRegisterAddress = customerRegisterAddress;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public Date getCustomerIntoTime() {
		return customerIntoTime;
	}

	public void setCustomerIntoTime(Date customerIntoTime) {
		this.customerIntoTime = customerIntoTime;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getDictLoanUse() {
		return dictLoanUse;
	}

	public void setDictLoanUse(String dictLoanUse) {
		this.dictLoanUse = dictLoanUse;
	}

	public BigDecimal getLoanApplyAmount() {
		return loanApplyAmount;
	}

	public void setLoanApplyAmount(BigDecimal loanApplyAmount) {
		this.loanApplyAmount = loanApplyAmount;
	}

	public BigDecimal getLoanMonths() {
		return loanMonths;
	}

	public void setLoanMonths(BigDecimal loanMonths) {
		this.loanMonths = loanMonths;
	}

	public String getLoanManagercode() {
		return loanManagercode;
	}

	public void setLoanManagercode(String loanManagercode) {
		this.loanManagercode = loanManagercode;
	}

	public String getLoanCustomerService() {
		return loanCustomerService;
	}

	public void setLoanCustomerService(String loanCustomerService) {
		this.loanCustomerService = loanCustomerService;
	}

	public String getDictCompType() {
		return dictCompType;
	}

	public void setDictCompType(String dictCompType) {
		this.dictCompType = dictCompType;
	}

	public Date getCompEntryDate() {
		return compEntryDate;
	}

	public void setCompEntryDate(Date compEntryDate) {
		this.compEntryDate = compEntryDate;
	}

	public String getCompDepartment() {
		return compDepartment;
	}

	public void setCompDepartment(String compDepartment) {
		this.compDepartment = compDepartment;
	}

	public String getCompPost() {
		return compPost;
	}

	public void setCompPost(String compPost) {
		this.compPost = compPost;
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

	public String getCustomerHousingSituation() {
		return customerHousingSituation;
	}

	public void setCustomerHousingSituation(String customerHousingSituation) {
		this.customerHousingSituation = customerHousingSituation;
	}

	public String getHouseProvince() {
		return houseProvince;
	}

	public void setHouseProvince(String houseProvince) {
		this.houseProvince = houseProvince;
	}

	public String getHouseCity() {
		return houseCity;
	}

	public void setHouseCity(String houseCity) {
		this.houseCity = houseCity;
	}

	public String getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
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

	public String getCompProvince() {
		return compProvince;
	}

	public void setCompProvince(String compProvince) {
		this.compProvince = compProvince;
	}

	public String getCompCity() {
		return compCity;
	}

	public void setCompCity(String compCity) {
		this.compCity = compCity;
	}

	public String getCompArer() {
		return compArer;
	}

	public void setCompArer(String compArer) {
		this.compArer = compArer;
	}

	public String getLoanStoreOrgid() {
		return loanStoreOrgid;
	}

	public void setLoanStoreOrgid(String loanStoreOrgid) {
		this.loanStoreOrgid = loanStoreOrgid;
	}

	public Date getHouseBuyday() {
		return houseBuyday;
	}

	public void setHouseBuyday(Date houseBuyday) {
		this.houseBuyday = houseBuyday;
	}

	public String getHousePledgeFlag() {
		return housePledgeFlag;
	}

	public void setHousePledgeFlag(String housePledgeFlag) {
		this.housePledgeFlag = housePledgeFlag;
	}

	public String getCompWorkExperience() {
		return compWorkExperience;
	}

	public void setCompWorkExperience(String compWorkExperience) {
		this.compWorkExperience = compWorkExperience;
	}

	public String getMateName() {
		return mateName;
	}

	public void setMateName(String mateName) {
		this.mateName = mateName;
	}

	public String getMateCertType() {
		return mateCertType;
	}

	public void setMateCertType(String mateCertType) {
		this.mateCertType = mateCertType;
	}

	public String getMateCertNum() {
		return mateCertNum;
	}

	public void setMateCertNum(String mateCertNum) {
		this.mateCertNum = mateCertNum;
	}

	public String getMateTel() {
		return mateTel;
	}

	public void setMateTel(String mateTel) {
		this.mateTel = mateTel;
	}

	public String getTomerFirtArriveYear() {
		return tomerFirtArriveYear;
	}

	public void setTomerFirtArriveYear(String tomerFirtArriveYear) {
		this.tomerFirtArriveYear = tomerFirtArriveYear;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

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

	public String getComLegalMan() {
		return comLegalMan;
	}

	public void setComLegalMan(String comLegalMan) {
		this.comLegalMan = comLegalMan;
	}

	public String getComLegalManNum() {
		return comLegalManNum;
	}

	public void setComLegalManNum(String comLegalManNum) {
		this.comLegalManNum = comLegalManNum;
	}

	public String getComLegalManMoblie() {
		return comLegalManMoblie;
	}

	public void setComLegalManMoblie(String comLegalManMoblie) {
		this.comLegalManMoblie = comLegalManMoblie;
	}

	public String getComEmail() {
		return comEmail;
	}

	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
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

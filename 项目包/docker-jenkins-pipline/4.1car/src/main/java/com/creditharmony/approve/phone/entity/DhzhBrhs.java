package com.creditharmony.approve.phone.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 电话照会_本人核实 及电话录音
 * 
 * @Class Name DhzhBrhs
 * @author 王浩
 * @Create In 2015年12月1日
 */
public class DhzhBrhs extends DataEntity<DhzhBrhs> {

	private static final long serialVersionUID = 429324584518696559L;
	private String loanCode;					// 借款编号
	private String rCustomerCoborrowerId;		// 主借人（或共借人）ID
	private String dictCustomerType;			// 借款人类型（主借人或共借人）
	private String brhsFamilyTel;				//
	private String dictProvince;
	private String dictCity;
	private String dictArer;
	private String brhsLiveAddress;
	private String brhsUnitname;
	private String brhsUnitTel;
	private String dictUnitProvince;
	private String dictUnitCity;
	private String dictUnitArer;
	private String brhsUnitAddress;
	private String applyProductType;
	private BigDecimal brhsLoanQuota;
	private BigDecimal brhsMyselfMonths;		// 自述分期数
	private BigDecimal brhsMonthRepayAmount;	// 月还款额
	private String dictLoanUse;					// 借款用途
	private String dictCustomerDiff;			// 客户类型(工薪,经营)
	private String brhsPhone;
	private String brhsPhoneTwo;
	private BigDecimal brhsUnitScale;
	private String brhsMainBusiness;
	private String otherLoanMark;
	private String dictCheckType;
	private String liveNetResult; 			// 居住地网查结果
	private String liveNetRemark; 			// 居住地网查备注
	private String familyNetAssessResult; 	// 家庭固话网查结果
	private String familyCheckRemark; 		// 家庭固话网查备注
	private String customerCertNum; 		// 身份证号
	private String certNetAssessResult; 	// 身份证网查结果
	private String certCheckRemark; 		// 身份证网查备注
	private String riskFlag;
	private String productMonths;	
	private String dictEducation; 			// 学历
	private String name; 					// 本人姓名
	private String nameNetResult; 			// 本人姓名网查结果
	private String nameNetRemark; 			// 本人姓名网查结果
	private Date hireDate;					// 入职时间
	private String dictCompIndustry;		// 行业分类
	private String dictCompIndustrySecond;	// *行业分类二
	private String dictCompIndustryThird;	// *行业分类三级
	private String brhsProfessionalCode;	// 职业分类
	private String professionRemark;		// *职业备注
	private String dictUnitProperties;		// 单位性质
	private String unitPropertiesRemark;	// *单位性质备注
	private String isReady;					// 是否初始化 是为1 否为0
	private String industryLabel;			// 行业label
	private String industryRemark;			// 行业备注
	private Integer hireMonth;				// 入职月数
	private BigDecimal otherMonthInput;		// 其他月收入
	private String otherMonthInputCk;		// 其他月收入复选框
	private BigDecimal familyMonthOutput;		// 家庭月支出
	private String dictMarryStatus;			// 婚姻状况
	private String customerHouseHoldProperty; // 住房性质
	
	// 新版申请表新增
	
	private String otherIncomeResource;			// 其他收入来源
	private String otherIncomeResourceRemark;	// 其他收入来源备注	
	private BigDecimal averageMonthTurnover;	// 平均月营业额(万元)	
	private BigDecimal monthPaybackTotalMoney;	// 月还款总额(元)	
	private Integer comPaybackCount;			// 同业在还借款总笔数
	private String customerHouseHoldPropertyRemark;			// 住宅类别备注
	private Integer dictLoanUseDict;			// 主要借款用途
	private String dictLoanUseRemark;			// 主要借款用途备注
	private BigDecimal brhsLoanQuotaNew;			// 申请额度
	private BigDecimal limitUpper;				// 大纲上限
	private BigDecimal limitLower;				// 大纲下线
	
	private String dataSources;				// 数据来源：汇金0，其他汇成
	
	private String dictSourceType;
	
	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getProductMonths() {
		return productMonths;
	}

	public void setProductMonths(String productMonths) {
		this.productMonths = productMonths;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode == null ? null : loanCode.trim();
	}

	public String getDictCustomerType() {
		return dictCustomerType;
	}

	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType == null ? null
				: dictCustomerType.trim();
	}

	public String getBrhsFamilyTel() {
		return brhsFamilyTel;
	}

	public void setBrhsFamilyTel(String brhsFamilyTel) {
		this.brhsFamilyTel = brhsFamilyTel == null ? null : brhsFamilyTel
				.trim();
	}

	public String getDictProvince() {
		return dictProvince;
	}

	public void setDictProvince(String dictProvince) {
		this.dictProvince = dictProvince == null ? null : dictProvince.trim();
	}

	public String getDictCity() {
		return dictCity;
	}

	public void setDictCity(String dictCity) {
		this.dictCity = dictCity == null ? null : dictCity.trim();
	}

	public String getDictArer() {
		return dictArer;
	}

	public void setDictArer(String dictArer) {
		this.dictArer = dictArer == null ? null : dictArer.trim();
	}

	public String getBrhsLiveAddress() {
		return brhsLiveAddress;
	}

	public void setBrhsLiveAddress(String brhsLiveAddress) {
		this.brhsLiveAddress = brhsLiveAddress == null ? null : brhsLiveAddress
				.trim();
	}

	public String getBrhsUnitname() {
		return brhsUnitname;
	}

	public void setBrhsUnitname(String brhsUnitname) {
		this.brhsUnitname = brhsUnitname == null ? null : brhsUnitname.trim();
	}

	public String getBrhsUnitTel() {
		return brhsUnitTel;
	}

	public void setBrhsUnitTel(String brhsUnitTel) {
		this.brhsUnitTel = brhsUnitTel == null ? null : brhsUnitTel.trim();
	}

	public String getDictUnitProvince() {
		return dictUnitProvince;
	}

	public void setDictUnitProvince(String dictUnitProvince) {
		this.dictUnitProvince = dictUnitProvince == null ? null
				: dictUnitProvince.trim();
	}

	public String getDictUnitCity() {
		return dictUnitCity;
	}

	public void setDictUnitCity(String dictUnitCity) {
		this.dictUnitCity = dictUnitCity == null ? null : dictUnitCity.trim();
	}

	public String getDictUnitArer() {
		return dictUnitArer;
	}

	public void setDictUnitArer(String dictUnitArer) {
		this.dictUnitArer = dictUnitArer == null ? null : dictUnitArer.trim();
	}

	public String getBrhsUnitAddress() {
		return brhsUnitAddress;
	}

	public void setBrhsUnitAddress(String brhsUnitAddress) {
		this.brhsUnitAddress = brhsUnitAddress == null ? null : brhsUnitAddress
				.trim();
	}

	public String getBrhsProfessionalCode() {
		return brhsProfessionalCode;
	}

	public void setBrhsProfessionalCode(String brhsProfessionalCode) {
		this.brhsProfessionalCode = brhsProfessionalCode == null ? null
				: brhsProfessionalCode.trim();
	}

	public String getDictCompIndustry() {
		return dictCompIndustry;
	}

	public void setDictCompIndustry(String dictCompIndustry) {
		this.dictCompIndustry = dictCompIndustry == null ? null
				: dictCompIndustry.trim();
	}

	public BigDecimal getBrhsLoanQuota() {
		return brhsLoanQuota;
	}

	public void setBrhsLoanQuota(BigDecimal brhsLoanQuota) {
		this.brhsLoanQuota = brhsLoanQuota;
	}

	public BigDecimal getBrhsMyselfMonths() {
		return brhsMyselfMonths;
	}

	public void setBrhsMyselfMonths(BigDecimal brhsMyselfMonths) {
		this.brhsMyselfMonths = brhsMyselfMonths;
	}

	public BigDecimal getBrhsMonthRepayAmount() {
		return brhsMonthRepayAmount;
	}

	public void setBrhsMonthRepayAmount(BigDecimal brhsMonthRepayAmount) {
		this.brhsMonthRepayAmount = brhsMonthRepayAmount;
	}

	public String getDictLoanUse() {
		return dictLoanUse;
	}

	public void setDictLoanUse(String dictLoanUse) {
		this.dictLoanUse = dictLoanUse == null ? null : dictLoanUse.trim();
	}

	public String getDictCustomerDiff() {
		return dictCustomerDiff;
	}

	public void setDictCustomerDiff(String dictCustomerDiff) {
		this.dictCustomerDiff = dictCustomerDiff == null ? null
				: dictCustomerDiff.trim();
	}

	public BigDecimal getBrhsUnitScale() {
		return brhsUnitScale;
	}

	public void setBrhsUnitScale(BigDecimal brhsUnitScale) {
		this.brhsUnitScale = brhsUnitScale;
	}

	public String getBrhsMainBusiness() {
		return brhsMainBusiness;
	}

	public void setBrhsMainBusiness(String brhsMainBusiness) {
		this.brhsMainBusiness = brhsMainBusiness == null ? null
				: brhsMainBusiness.trim();
	}

	public String getDictCheckType() {
		return dictCheckType;
	}

	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType == null ? null : dictCheckType
				.trim();
	}

	public String getBrhsPhone() {
		return brhsPhone;
	}

	public void setBrhsPhone(String brhsPhone) {
		this.brhsPhone = brhsPhone;
	}

	public String getBrhsPhoneTwo() {
		return brhsPhoneTwo;
	}

	public void setBrhsPhoneTwo(String brhsPhoneTwo) {
		this.brhsPhoneTwo = brhsPhoneTwo;
	}

	public String getOtherLoanMark() {
		return otherLoanMark;
	}

	public void setOtherLoanMark(String otherLoanMark) {
		this.otherLoanMark = otherLoanMark;
	}

	public String getrCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}

	public void setrCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
	}

	public String getFamilyNetAssessResult() {
		return familyNetAssessResult;
	}

	public void setFamilyNetAssessResult(String familyNetAssessResult) {
		this.familyNetAssessResult = familyNetAssessResult;
	}

	public String getFamilyCheckRemark() {
		return familyCheckRemark;
	}

	public void setFamilyCheckRemark(String familyCheckRemark) {
		this.familyCheckRemark = familyCheckRemark;
	}

	public String getCustomerCertNum() {
		return customerCertNum;
	}

	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}

	public String getCertNetAssessResult() {
		return certNetAssessResult;
	}

	public void setCertNetAssessResult(String certNetAssessResult) {
		this.certNetAssessResult = certNetAssessResult;
	}

	public String getCertCheckRemark() {
		return certCheckRemark;
	}

	public void setCertCheckRemark(String certCheckRemark) {
		this.certCheckRemark = certCheckRemark;
	}

	public String getLiveNetResult() {
		return liveNetResult;
	}

	public void setLiveNetResult(String liveNetResult) {
		this.liveNetResult = liveNetResult;
	}

	public String getLiveNetRemark() {
		return liveNetRemark;
	}

	public void setLiveNetRemark(String liveNetRemark) {
		this.liveNetRemark = liveNetRemark;
	}

	public String getRiskFlag() {
		return riskFlag;
	}

	public void setRiskFlag(String riskFlag) {
		this.riskFlag = riskFlag;
	}

	public String getApplyProductType() {
		return applyProductType;
	}

	public void setApplyProductType(String applyProductType) {
		this.applyProductType = applyProductType;
	}

	public String getDictEducation() {
		return dictEducation;
	}

	public void setDictEducation(String dictEducation) {
		this.dictEducation = dictEducation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameNetResult() {
		return nameNetResult;
	}

	public void setNameNetResult(String nameNetResult) {
		this.nameNetResult = nameNetResult;
	}

	public String getNameNetRemark() {
		return nameNetRemark;
	}

	public void setNameNetRemark(String nameNetRemark) {
		this.nameNetRemark = nameNetRemark;
	}
	public String getIsReady() {
		return isReady;
	}

	public void setIsReady(String isReady) {
		this.isReady = isReady;
	}
	public String getDictCompIndustrySecond() {
		return dictCompIndustrySecond;
	}

	public void setDictCompIndustrySecond(String dictCompIndustrySecond) {
		this.dictCompIndustrySecond = dictCompIndustrySecond;
	}

	public String getDictCompIndustryThird() {
		return dictCompIndustryThird;
	}

	public void setDictCompIndustryThird(String dictCompIndustryThird) {
		this.dictCompIndustryThird = dictCompIndustryThird;
	}

	public String getProfessionRemark() {
		return professionRemark;
	}

	public void setProfessionRemark(String professionRemark) {
		this.professionRemark = professionRemark;
	}

	public String getDictUnitProperties() {
		return dictUnitProperties;
	}

	public void setDictUnitProperties(String dictUnitProperties) {
		this.dictUnitProperties = dictUnitProperties;
	}

	public String getUnitPropertiesRemark() {
		return unitPropertiesRemark;
	}

	public void setUnitPropertiesRemark(String unitPropertiesRemark) {
		this.unitPropertiesRemark = unitPropertiesRemark;
	}

	public String getIndustryLabel() {
		return industryLabel;
	}

	public void setIndustryLabel(String industryLabel) {
		this.industryLabel = industryLabel;
	}

	public String getIndustryRemark() {
		return industryRemark;
	}

	public void setIndustryRemark(String industryRemark) {
		this.industryRemark = industryRemark;
	}

	public Integer getHireMonth() {
		return hireMonth;
	}

	public void setHireMonth(Integer hireMonth) {
		this.hireMonth = hireMonth;
	}

	public String getDictSourceType() {
		return dictSourceType;
	}

	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}

	public BigDecimal getOtherMonthInput() {
		return otherMonthInput;
	}

	public void setOtherMonthInput(BigDecimal otherMonthInput) {
		this.otherMonthInput = otherMonthInput;
	}

	public String getOtherMonthInputCk() {
		return otherMonthInputCk;
	}

	public void setOtherMonthInputCk(String otherMonthInputCk) {
		this.otherMonthInputCk = otherMonthInputCk;
	}

	public BigDecimal getFamilyMonthOutput() {
		return familyMonthOutput;
	}

	public void setFamilyMonthOutput(BigDecimal familyMonthOutput) {
		this.familyMonthOutput = familyMonthOutput;
	}

	public String getDictMarryStatus() {
		return dictMarryStatus;
	}

	public void setDictMarryStatus(String dictMarryStatus) {
		this.dictMarryStatus = dictMarryStatus;
	}

	public String getCustomerHouseHoldProperty() {
		return customerHouseHoldProperty;
	}

	public void setCustomerHouseHoldProperty(String customerHouseHoldProperty) {
		this.customerHouseHoldProperty = customerHouseHoldProperty;
	}

	public String getOtherIncomeResource() {
		return otherIncomeResource;
	}

	public void setOtherIncomeResource(String otherIncomeResource) {
		this.otherIncomeResource = otherIncomeResource;
	}

	public String getOtherIncomeResourceRemark() {
		return otherIncomeResourceRemark;
	}

	public void setOtherIncomeResourceRemark(String otherIncomeResourceRemark) {
		this.otherIncomeResourceRemark = otherIncomeResourceRemark;
	}

	public BigDecimal getAverageMonthTurnover() {
		return averageMonthTurnover;
	}

	public void setAverageMonthTurnover(BigDecimal averageMonthTurnover) {
		this.averageMonthTurnover = averageMonthTurnover;
	}

	public BigDecimal getMonthPaybackTotalMoney() {
		return monthPaybackTotalMoney;
	}

	public void setMonthPaybackTotalMoney(BigDecimal monthPaybackTotalMoney) {
		this.monthPaybackTotalMoney = monthPaybackTotalMoney;
	}

	public Integer getComPaybackCount() {
		return comPaybackCount;
	}

	public void setComPaybackCount(Integer comPaybackCount) {
		this.comPaybackCount = comPaybackCount;
	}

	public String getCustomerHouseHoldPropertyRemark() {
		return customerHouseHoldPropertyRemark;
	}

	public void setCustomerHouseHoldPropertyRemark(
			String customerHouseHoldPropertyRemark) {
		this.customerHouseHoldPropertyRemark = customerHouseHoldPropertyRemark;
	}

	public Integer getDictLoanUseDict() {
		return dictLoanUseDict;
	}

	public void setDictLoanUseDict(Integer dictLoanUseDict) {
		this.dictLoanUseDict = dictLoanUseDict;
	}

	public String getDictLoanUseRemark() {
		return dictLoanUseRemark;
	}

	public void setDictLoanUseRemark(String dictLoanUseRemark) {
		this.dictLoanUseRemark = dictLoanUseRemark;
	}

	public BigDecimal getBrhsLoanQuotaNew() {
		return brhsLoanQuotaNew;
	}

	public void setBrhsLoanQuotaNew(BigDecimal brhsLoanQuotaNew) {
		this.brhsLoanQuotaNew = brhsLoanQuotaNew;
	}

	public BigDecimal getLimitUpper() {
		return limitUpper;
	}

	public void setLimitUpper(BigDecimal limitUpper) {
		this.limitUpper = limitUpper;
	}

	public BigDecimal getLimitLower() {
		return limitLower;
	}

	public void setLimitLower(BigDecimal limitLower) {
		this.limitLower = limitLower;
	}

	public String getDataSources() {
		return dataSources;
	}

	public void setDataSources(String dataSources) {
		this.dataSources = dataSources;
	}
	
}
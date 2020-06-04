package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 推荐额度
 * @Class Name AdviseAmount
 * @author zhanghu
 * @Create In 2016年8月2日
 */
public class AdviseAmount extends DataEntity<AdviseAmount>{
    
	private static final long serialVersionUID = 1L;

	// 查询条件
	private String loanCode;					// 借款编号
	private String rCustomerCoborrowerId;		// 主借人（或共借人）ID
	private String dictCustomerType;			// 借款人类型（主借人或共借人）
	private String dictCheckType;               // 信审或者复议
	
	// 查询条件：资料审核_借款人账户流水
	private String dictAccountType;				// 账户类型(个人/公卡)
	private String accountFlowMark;				// 工资流水标记
	private String otherLoanMark;				// 同业借款标记
	
	// 查询条件：征信
	private String currency;					// 币种
	private Date actualDay;						// 截止年月*
	private Date issueDay;						// 发放日期*
	private BigDecimal conteactAmount;			// 贷款合同金额*
	
	// 查询条件：电话照会_本人核实_借款金额
	private String rBrhsId;						// 关联ID(核实主表)
	
    // 推荐额度
    private BigDecimal ratingAdviseAmount;
    
    // 查询结果:电话照会_本人核实
    private BigDecimal loanApplyAmount;			// 申请额度
    private BigDecimal brhsLoanQuota;			// 借款额度
    private BigDecimal brhsMonthRepayAmount;	// 月还款额
    private BigDecimal otherMonthInput;         // 其他月收入
    private BigDecimal familyMonthOutput;       // 家庭月支出
    private String dictCompIndustry;            // 行业类别一级
    private String dictCompIndustrySecond;      // 行业分类二
    private String dictUnitProvince;            // 单位地址省
    private String dictUnitCity;      			// 单位地址市

    // 查询结果:审核结果表
    private Integer auditMonths;				// 审批分期
    private String productCode;      			// 产品类型code
    private BigDecimal limitUpper;              // 大纲上限
    
    // 查询结果:各种求和结果
    private BigDecimal sumAccount;				// 求和结果
    
    // 查询结果:关联id
    private String relationId;					// 关联id
    
    // 查询结果:信审评分结果表
    private String verifyRiskLevel;				// 信审评分风险等级
    private String reconsiderRiskLevel;			// 复议评分风险等级
    private String applyRiskLevel;				// 进件评分风险等级
    
	// 查询条件：系数参照表（期数、费率、前期综合费）
	private String months;						// 期数
	private String riskLevel;					// 风险等级
	
    
    // 查询结果:系数参照表（期数、费率、前期综合费）
    private BigDecimal productUsableRate;		// 产品总费率（%）
	
    private BigDecimal payWater;						// 工资“月认定收入”之和
    private BigDecimal commonlyUsedWater;				// 常储“月认定收入”之和
    private BigDecimal publicWater;						// 对公“月认定收入”之和
    
    private BigDecimal cityIncomeCoefficient;			// 城市收入系数
    
	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getrCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}

	public void setrCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
	}

	public String getDictCustomerType() {
		return dictCustomerType;
	}

	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}

	public String getDictCheckType() {
		return dictCheckType;
	}

	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}

	public String getDictAccountType() {
		return dictAccountType;
	}

	public void setDictAccountType(String dictAccountType) {
		this.dictAccountType = dictAccountType;
	}

	public String getAccountFlowMark() {
		return accountFlowMark;
	}

	public void setAccountFlowMark(String accountFlowMark) {
		this.accountFlowMark = accountFlowMark;
	}

	public BigDecimal getRatingAdviseAmount() {
		return ratingAdviseAmount;
	}

	public void setRatingAdviseAmount(BigDecimal ratingAdviseAmount) {
		this.ratingAdviseAmount = ratingAdviseAmount;
	}

	public BigDecimal getBrhsLoanQuota() {
		return brhsLoanQuota;
	}

	public void setBrhsLoanQuota(BigDecimal brhsLoanQuota) {
		this.brhsLoanQuota = brhsLoanQuota;
	}

	public BigDecimal getBrhsMonthRepayAmount() {
		return brhsMonthRepayAmount;
	}

	public void setBrhsMonthRepayAmount(BigDecimal brhsMonthRepayAmount) {
		this.brhsMonthRepayAmount = brhsMonthRepayAmount;
	}

	public BigDecimal getOtherMonthInput() {
		return otherMonthInput;
	}

	public void setOtherMonthInput(BigDecimal otherMonthInput) {
		this.otherMonthInput = otherMonthInput;
	}

	public BigDecimal getFamilyMonthOutput() {
		return familyMonthOutput;
	}

	public void setFamilyMonthOutput(BigDecimal familyMonthOutput) {
		this.familyMonthOutput = familyMonthOutput;
	}

	public Integer getAuditMonths() {
		return auditMonths;
	}

	public void setAuditMonths(Integer auditMonths) {
		this.auditMonths = auditMonths;
	}

	public BigDecimal getSumAccount() {
		return sumAccount;
	}

	public void setSumAccount(BigDecimal sumAccount) {
		this.sumAccount = sumAccount;
	}

	public String getOtherLoanMark() {
		return otherLoanMark;
	}

	public void setOtherLoanMark(String otherLoanMark) {
		this.otherLoanMark = otherLoanMark;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getActualDay() {
		return actualDay;
	}

	public void setActualDay(Date actualDay) {
		this.actualDay = actualDay;
	}

	public Date getIssueDay() {
		return issueDay;
	}

	public void setIssueDay(Date issueDay) {
		this.issueDay = issueDay;
	}

	public BigDecimal getConteactAmount() {
		return conteactAmount;
	}

	public void setConteactAmount(BigDecimal conteactAmount) {
		this.conteactAmount = conteactAmount;
	}

	public String getrBrhsId() {
		return rBrhsId;
	}

	public void setrBrhsId(String rBrhsId) {
		this.rBrhsId = rBrhsId;
	}

	public String getDictCompIndustry() {
		return dictCompIndustry;
	}

	public void setDictCompIndustry(String dictCompIndustry) {
		this.dictCompIndustry = dictCompIndustry;
	}

	public String getDictCompIndustrySecond() {
		return dictCompIndustrySecond;
	}

	public void setDictCompIndustrySecond(String dictCompIndustrySecond) {
		this.dictCompIndustrySecond = dictCompIndustrySecond;
	}

	public String getVerifyRiskLevel() {
		return verifyRiskLevel;
	}

	public void setVerifyRiskLevel(String verifyRiskLevel) {
		this.verifyRiskLevel = verifyRiskLevel;
	}

	public String getReconsiderRiskLevel() {
		return reconsiderRiskLevel;
	}

	public void setReconsiderRiskLevel(String reconsiderRiskLevel) {
		this.reconsiderRiskLevel = reconsiderRiskLevel;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public BigDecimal getProductUsableRate() {
		return productUsableRate;
	}

	public void setProductUsableRate(BigDecimal productUsableRate) {
		this.productUsableRate = productUsableRate;
	}

	public BigDecimal getLimitUpper() {
		return limitUpper;
	}

	public void setLimitUpper(BigDecimal limitUpper) {
		this.limitUpper = limitUpper;
	}

	public String getDictUnitProvince() {
		return dictUnitProvince;
	}

	public void setDictUnitProvince(String dictUnitProvince) {
		this.dictUnitProvince = dictUnitProvince;
	}

	public String getDictUnitCity() {
		return dictUnitCity;
	}

	public void setDictUnitCity(String dictUnitCity) {
		this.dictUnitCity = dictUnitCity;
	}

	public BigDecimal getPayWater() {
		return payWater;
	}

	public void setPayWater(BigDecimal payWater) {
		this.payWater = payWater;
	}

	public BigDecimal getCommonlyUsedWater() {
		return commonlyUsedWater;
	}

	public void setCommonlyUsedWater(BigDecimal commonlyUsedWater) {
		this.commonlyUsedWater = commonlyUsedWater;
	}

	public BigDecimal getPublicWater() {
		return publicWater;
	}

	public void setPublicWater(BigDecimal publicWater) {
		this.publicWater = publicWater;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getCityIncomeCoefficient() {
		return cityIncomeCoefficient;
	}

	public void setCityIncomeCoefficient(BigDecimal cityIncomeCoefficient) {
		this.cityIncomeCoefficient = cityIncomeCoefficient;
	}

	public String getApplyRiskLevel() {
		return applyRiskLevel;
	}

	public void setApplyRiskLevel(String applyRiskLevel) {
		this.applyRiskLevel = applyRiskLevel;
	}

	public BigDecimal getLoanApplyAmount() {
		return loanApplyAmount;
	}

	public void setLoanApplyAmount(BigDecimal loanApplyAmount) {
		this.loanApplyAmount = loanApplyAmount;
	}
   
	
}
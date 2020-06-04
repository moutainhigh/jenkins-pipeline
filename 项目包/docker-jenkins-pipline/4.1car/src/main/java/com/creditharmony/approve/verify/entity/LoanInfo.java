package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @author 罗俊平
 * @update in 2016-09-13
 */
public class LoanInfo extends DataEntity<LoanInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loanCode;
	private String applyId;
	private String loanCustomerName;
	private Date loanApplyTime;
	private BigDecimal loanMonths;
	private BigDecimal loanApplyAmount;
	private String dictRealyUse;
	private String loanCommonRepayment;
	private String dictRepayMethod;
	private String dictLoanStatus;
	private String dictLoanType;
	private String productType;
	private String loanIsUrgent;
	private String loanDeptType;
	private String dictLoanUse;
	private String loanApplyTop;
	private String loanApplyTopStatus;
	private BigDecimal rId;
	private String dictIsCycle;
	private String dictIsAdditional;
	private Date customerIntoTime;
	private BigDecimal loanAuditMoney;
	private Date loanAuditTime;
	private Date loanSecondFinishtime;
	private BigDecimal loanAdditionalApplyid;
	private String loanDecisionmakingCode;
	private String loanManagercode;
	private String loanTeamManagercode;
	private String loanTeamOrgid;
	private String loanSurveyEmpId;
	private BigDecimal loanLastApplyId;
	private String dictLoanaType;
	private String loanIsRaise;
	private BigDecimal loanRaiseMoney;
	private BigDecimal loanNum;
	private String loanMarking;
	private String loanCustomerService;
	private String remark;
	private String outsideFlag;
	private String loanStoreOrgid; // 所在组织机构
	private String auditId;
	private String loanAuditProduct;
	private Integer loanAuditMonths;
	private String kinnobuQuotaFlag;
	private String approveStep;
	private String approveResult;
	private String resultId; // 每步的审核结果表id
	private String recordId; // 每步的审核记录表id
	private String model; // 模型
	private String loanFlag; // 出借标识
	private String loanFlagName;// 渠道name批单处理用
	private String usingFlag; // 历史再用标识
	private String dictSourceType;
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

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode == null ? null : loanCode.trim();
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId == null ? null : applyId.trim();
	}

	public String getLoanCustomerName() {
		return loanCustomerName;
	}

	public void setLoanCustomerName(String loanCustomerName) {
		this.loanCustomerName = loanCustomerName == null ? null : loanCustomerName.trim();
	}

	public Date getLoanApplyTime() {
		return loanApplyTime;
	}

	public void setLoanApplyTime(Date loanApplyTime) {
		this.loanApplyTime = loanApplyTime;
	}

	public BigDecimal getLoanMonths() {
		return loanMonths;
	}

	public void setLoanMonths(BigDecimal loanMonths) {
		this.loanMonths = loanMonths;
	}

	public BigDecimal getLoanApplyAmount() {
		return loanApplyAmount;
	}

	public void setLoanApplyAmount(BigDecimal loanApplyAmount) {
		this.loanApplyAmount = loanApplyAmount;
	}

	public String getDictRealyUse() {
		return dictRealyUse;
	}

	public void setDictRealyUse(String dictRealyUse) {
		this.dictRealyUse = dictRealyUse == null ? null : dictRealyUse.trim();
	}

	public String getLoanCommonRepayment() {
		return loanCommonRepayment;
	}

	public void setLoanCommonRepayment(String loanCommonRepayment) {
		this.loanCommonRepayment = loanCommonRepayment == null ? null : loanCommonRepayment.trim();
	}

	public String getDictRepayMethod() {
		return dictRepayMethod;
	}

	public void setDictRepayMethod(String dictRepayMethod) {
		this.dictRepayMethod = dictRepayMethod == null ? null : dictRepayMethod.trim();
	}

	public String getDictLoanStatus() {
		return dictLoanStatus;
	}

	public void setDictLoanStatus(String dictLoanStatus) {
		this.dictLoanStatus = dictLoanStatus == null ? null : dictLoanStatus.trim();
	}

	public String getDictLoanType() {
		return dictLoanType;
	}

	public void setDictLoanType(String dictLoanType) {
		this.dictLoanType = dictLoanType == null ? null : dictLoanType.trim();
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType == null ? null : productType.trim();
	}

	public String getLoanIsUrgent() {
		return loanIsUrgent;
	}

	public void setLoanIsUrgent(String loanIsUrgent) {
		this.loanIsUrgent = loanIsUrgent == null ? null : loanIsUrgent.trim();
	}

	public String getLoanDeptType() {
		return loanDeptType;
	}

	public void setLoanDeptType(String loanDeptType) {
		this.loanDeptType = loanDeptType == null ? null : loanDeptType.trim();
	}

	public String getDictLoanUse() {
		return dictLoanUse;
	}

	public void setDictLoanUse(String dictLoanUse) {
		this.dictLoanUse = dictLoanUse == null ? null : dictLoanUse.trim();
	}

	public String getLoanApplyTop() {
		return loanApplyTop;
	}

	public void setLoanApplyTop(String loanApplyTop) {
		this.loanApplyTop = loanApplyTop == null ? null : loanApplyTop.trim();
	}

	public String getLoanApplyTopStatus() {
		return loanApplyTopStatus;
	}

	public void setLoanApplyTopStatus(String loanApplyTopStatus) {
		this.loanApplyTopStatus = loanApplyTopStatus == null ? null : loanApplyTopStatus.trim();
	}

	public BigDecimal getrId() {
		return rId;
	}

	public void setrId(BigDecimal rId) {
		this.rId = rId;
	}

	public String getDictIsCycle() {
		return dictIsCycle;
	}

	public void setDictIsCycle(String dictIsCycle) {
		this.dictIsCycle = dictIsCycle == null ? null : dictIsCycle.trim();
	}

	public String getDictIsAdditional() {
		return dictIsAdditional;
	}

	public void setDictIsAdditional(String dictIsAdditional) {
		this.dictIsAdditional = dictIsAdditional == null ? null : dictIsAdditional.trim();
	}

	public Date getCustomerIntoTime() {
		return customerIntoTime;
	}

	public void setCustomerIntoTime(Date customerIntoTime) {
		this.customerIntoTime = customerIntoTime;
	}

	public BigDecimal getLoanAuditMoney() {
		return loanAuditMoney;
	}

	public void setLoanAuditMoney(BigDecimal loanAuditMoney) {
		this.loanAuditMoney = loanAuditMoney;
	}

	public Date getLoanAuditTime() {
		return loanAuditTime;
	}

	public void setLoanAuditTime(Date loanAuditTime) {
		this.loanAuditTime = loanAuditTime;
	}

	public Date getLoanSecondFinishtime() {
		return loanSecondFinishtime;
	}

	public void setLoanSecondFinishtime(Date loanSecondFinishtime) {
		this.loanSecondFinishtime = loanSecondFinishtime;
	}

	public BigDecimal getLoanAdditionalApplyid() {
		return loanAdditionalApplyid;
	}

	public void setLoanAdditionalApplyid(BigDecimal loanAdditionalApplyid) {
		this.loanAdditionalApplyid = loanAdditionalApplyid;
	}

	public String getLoanDecisionmakingCode() {
		return loanDecisionmakingCode;
	}

	public void setLoanDecisionmakingCode(String loanDecisionmakingCode) {
		this.loanDecisionmakingCode = loanDecisionmakingCode == null ? null : loanDecisionmakingCode.trim();
	}

	public String getLoanManagercode() {
		return loanManagercode;
	}

	public void setLoanManagercode(String loanManagercode) {
		this.loanManagercode = loanManagercode;
	}

	public String getLoanTeamManagercode() {
		return loanTeamManagercode;
	}

	public void setLoanTeamManagercode(String loanTeamManagercode) {
		this.loanTeamManagercode = loanTeamManagercode == null ? null : loanTeamManagercode.trim();
	}

	public String getLoanTeamOrgid() {
		return loanTeamOrgid;
	}

	public void setLoanTeamOrgid(String loanTeamOrgid) {
		this.loanTeamOrgid = loanTeamOrgid == null ? null : loanTeamOrgid.trim();
	}

	public String getLoanSurveyEmpId() {
		return loanSurveyEmpId;
	}

	public void setLoanSurveyEmpId(String loanSurveyEmpId) {
		this.loanSurveyEmpId = loanSurveyEmpId == null ? null : loanSurveyEmpId.trim();
	}

	public BigDecimal getLoanLastApplyId() {
		return loanLastApplyId;
	}

	public void setLoanLastApplyId(BigDecimal loanLastApplyId) {
		this.loanLastApplyId = loanLastApplyId;
	}

	public String getDictLoanaType() {
		return dictLoanaType;
	}

	public void setDictLoanaType(String dictLoanaType) {
		this.dictLoanaType = dictLoanaType == null ? null : dictLoanaType.trim();
	}

	public String getLoanIsRaise() {
		return loanIsRaise;
	}

	public void setLoanIsRaise(String loanIsRaise) {
		this.loanIsRaise = loanIsRaise == null ? null : loanIsRaise.trim();
	}

	public BigDecimal getLoanRaiseMoney() {
		return loanRaiseMoney;
	}

	public void setLoanRaiseMoney(BigDecimal loanRaiseMoney) {
		this.loanRaiseMoney = loanRaiseMoney;
	}

	public BigDecimal getLoanNum() {
		return loanNum;
	}

	public void setLoanNum(BigDecimal loanNum) {
		this.loanNum = loanNum;
	}

	public String getLoanMarking() {
		return loanMarking;
	}

	public void setLoanMarking(String loanMarking) {
		this.loanMarking = loanMarking == null ? null : loanMarking.trim();
	}

	public String getLoanCustomerService() {
		return loanCustomerService;
	}

	public void setLoanCustomerService(String loanCustomerService) {
		this.loanCustomerService = loanCustomerService == null ? null : loanCustomerService.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getOutsideFlag() {
		return outsideFlag;
	}

	public void setOutsideFlag(String outsideFlag) {
		this.outsideFlag = outsideFlag == null ? null : outsideFlag.trim();
	}

	public String getLoanStoreOrgid() {
		return loanStoreOrgid;
	}

	public void setLoanStoreOrgid(String loanStoreOrgid) {
		this.loanStoreOrgid = loanStoreOrgid;
	}

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getLoanAuditProduct() {
		return loanAuditProduct;
	}

	public void setLoanAuditProduct(String loanAuditProduct) {
		this.loanAuditProduct = loanAuditProduct;
	}

	public Integer getLoanAuditMonths() {
		return loanAuditMonths;
	}

	public void setLoanAuditMonths(Integer loanAuditMonths) {
		this.loanAuditMonths = loanAuditMonths;
	}

	public String getKinnobuQuotaFlag() {
		return kinnobuQuotaFlag;
	}

	public void setKinnobuQuotaFlag(String kinnobuQuotaFlag) {
		this.kinnobuQuotaFlag = kinnobuQuotaFlag;
	}

	public String getApproveStep() {
		return approveStep;
	}

	public void setApproveStep(String approveStep) {
		this.approveStep = approveStep;
	}

	public String getApproveResult() {
		return approveResult;
	}

	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLoanFlag() {
		return loanFlag;
	}

	public void setLoanFlag(String loanFlag) {
		this.loanFlag = loanFlag;
	}

	public String getLoanFlagName() {
		return loanFlagName;
	}

	public void setLoanFlagName(String loanFlagName) {
		this.loanFlagName = loanFlagName;
	}

	public String getUsingFlag() {
		return usingFlag;
	}

	public void setUsingFlag(String usingFlag) {
		this.usingFlag = usingFlag;
	}

	public String getDictSourceType() {
		return dictSourceType;
	}

	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}

}
package com.creditharmony.approve.newCar.entity;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 车借复审、终审列表实体类
 * @Class Name NewCarExamineEntity
 * @Create In 2017年3月27日
 */
public class NewCarExamineEntity  extends DataEntity<NewCarExamineEntity> implements Serializable{
	private static final long serialVersionUID = 74592908455640583L;

	private String	id             		 ;             	
	private String	loanCode             ;              //借款编号	
	private String	customerCode         ;              //客户code	
	private String	customerName         ;              //客户姓名	
	private String	borrowProductCode;              	//产品类型code
	private String	borrowProductName    ;              //产品类型（申请）	
	private String	certNum              ;              //身份证号	
	private String	loanTeamEmpCode  ;              	//团队经理code
	private String	loanTeamEmpName      ;              //团队经理	
	private String	offendSalesCode  ;              	//客户经理code
	private String	offendSalesName      ;              //客户经理	
	private String	storeCode            ;              //门店code	
	private String	storeName            ;              //门店  		
	private String	dictStatus           ;              //借款状态	
	private String	loanIsPhone          ;              //是否电销	
	private String	borrowTrusteeFlag    ;              //标识		
	private String	contractCode         ;              //合同编号	
	private Double	loanApplyAmount      ;              //申请金额	
	private Integer	loanMonths           ;              //借款期限（申请）
	private Double	storeAssessAmount    ;              //评估金额	
	private Double	auditAmount          ;              //批借金额	
	private Date	loanApplyTime        ;              //申请日期	
	private String	recheckNameCode  ;              	//复审人员code
	private String	recheckName          ;              //复审人员	
	private String	plateNumbers         ;              //车牌号码	
	private Date	customerIntoTime     ;              //进件时间	
	private Date	loanAuditTime        ;              //批复时间	
	private String 	firstCheckName;						// 初审姓名
	private String 	auditBorrowProductName;				//产品类型（批复
	private String 	auditBorrowProductCode;				//产品类型编码（批复
	private Integer auditLoanMonths;					//借款期限（批复）
	private Date auditTime;								//批借时间
	private String 	conditionalThroughFlag;				//附条件标识
	private String 	cycleBorrowingFlag;					//是否循环借
	private String 	extensionFlag;						//是否展期
	
	private String applyStatusCode;						// 展期--申请状态
	//排序字段
	private String orderField;
	private String createBy;							//创建人

	private String wobNum; 
	private String applyId;
	private String flowName; 
	private String token; 
	private String stepName; 
	private String applyOrgId; 
	private String applyOrgName; 
	private String applyOrgType; 
	private String trackState; 
	
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
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBorrowProductCode() {
		return borrowProductCode;
	}
	public void setBorrowProductCode(String borrowProductCode) {
		this.borrowProductCode = borrowProductCode;
	}
	public String getBorrowProductName() {
		return borrowProductName;
	}
	public void setBorrowProductName(String borrowProductName) {
		this.borrowProductName = borrowProductName;
	}
	public String getCertNum() {
		return certNum;
	}
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
	public String getLoanTeamEmpCode() {
		return loanTeamEmpCode;
	}
	public void setLoanTeamEmpCode(String loanTeamEmpCode) {
		this.loanTeamEmpCode = loanTeamEmpCode;
	}
	public String getLoanTeamEmpName() {
		return loanTeamEmpName;
	}
	public void setLoanTeamEmpName(String loanTeamEmpName) {
		this.loanTeamEmpName = loanTeamEmpName;
	}
	public String getOffendSalesCode() {
		return offendSalesCode;
	}
	public void setOffendSalesCode(String offendSalesCode) {
		this.offendSalesCode = offendSalesCode;
	}
	public String getOffendSalesName() {
		return offendSalesName;
	}
	public void setOffendSalesName(String offendSalesName) {
		this.offendSalesName = offendSalesName;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getDictStatus() {
		return dictStatus;
	}
	public void setDictStatus(String dictStatus) {
		this.dictStatus = dictStatus;
	}
	public String getLoanIsPhone() {
		return loanIsPhone;
	}
	public void setLoanIsPhone(String loanIsPhone) {
		this.loanIsPhone = loanIsPhone;
	}
	public String getBorrowTrusteeFlag() {
		return borrowTrusteeFlag;
	}
	public void setBorrowTrusteeFlag(String borrowTrusteeFlag) {
		this.borrowTrusteeFlag = borrowTrusteeFlag;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public Double getLoanApplyAmount() {
		return loanApplyAmount;
	}
	public void setLoanApplyAmount(Double loanApplyAmount) {
		this.loanApplyAmount = loanApplyAmount;
	}

	public Integer getLoanMonths() {
		return loanMonths;
	}
	public void setLoanMonths(Integer loanMonths) {
		this.loanMonths = loanMonths;
	}
	public Double getStoreAssessAmount() {
		return storeAssessAmount;
	}
	public void setStoreAssessAmount(Double storeAssessAmount) {
		this.storeAssessAmount = storeAssessAmount;
	}
	public Double getAuditAmount() {
		return auditAmount;
	}
	public void setAuditAmount(Double auditAmount) {
		this.auditAmount = auditAmount;
	}
	public Date getLoanApplyTime() {
		return loanApplyTime;
	}
	public void setLoanApplyTime(Date loanApplyTime) {
		this.loanApplyTime = loanApplyTime;
	}
	public String getRecheckNameCode() {
		return recheckNameCode;
	}
	public void setRecheckNameCode(String recheckNameCode) {
		this.recheckNameCode = recheckNameCode;
	}
	public String getRecheckName() {
		return recheckName;
	}
	public void setRecheckName(String recheckName) {
		this.recheckName = recheckName;
	}
	public String getPlateNumbers() {
		return plateNumbers;
	}
	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}
	public Date getCustomerIntoTime() {
		return customerIntoTime;
	}
	public void setCustomerIntoTime(Date customerIntoTime) {
		this.customerIntoTime = customerIntoTime;
	}
	public Date getLoanAuditTime() {
		return loanAuditTime;
	}
	public void setLoanAuditTime(Date loanAuditTime) {
		this.loanAuditTime = loanAuditTime;
	}
	public String getFirstCheckName() {
		return firstCheckName;
	}
	public void setFirstCheckName(String firstCheckName) {
		this.firstCheckName = firstCheckName;
	}
	public String getAuditBorrowProductName() {
		return auditBorrowProductName;
	}
	public void setAuditBorrowProductName(String auditBorrowProductName) {
		this.auditBorrowProductName = auditBorrowProductName;
	}
	public String getAuditBorrowProductCode() {
		return auditBorrowProductCode;
	}
	public void setAuditBorrowProductCode(String auditBorrowProductCode) {
		this.auditBorrowProductCode = auditBorrowProductCode;
	}
	public Integer getAuditLoanMonths() {
		return auditLoanMonths;
	}
	public void setAuditLoanMonths(Integer auditLoanMonths) {
		this.auditLoanMonths = auditLoanMonths;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getConditionalThroughFlag() {
		return conditionalThroughFlag;
	}
	public void setConditionalThroughFlag(String conditionalThroughFlag) {
		this.conditionalThroughFlag = conditionalThroughFlag;
	}
	public String getCycleBorrowingFlag() {
		return cycleBorrowingFlag;
	}
	public void setCycleBorrowingFlag(String cycleBorrowingFlag) {
		this.cycleBorrowingFlag = cycleBorrowingFlag;
	}
	public String getExtensionFlag() {
		return extensionFlag;
	}
	public void setExtensionFlag(String extensionFlag) {
		this.extensionFlag = extensionFlag;
	}
	public String getApplyStatusCode() {
		return applyStatusCode;
	}
	public void setApplyStatusCode(String applyStatusCode) {
		this.applyStatusCode = applyStatusCode;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getWobNum() {
		return wobNum;
	}
	public void setWobNum(String wobNum) {
		this.wobNum = wobNum;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getApplyOrgId() {
		return applyOrgId;
	}
	public void setApplyOrgId(String applyOrgId) {
		this.applyOrgId = applyOrgId;
	}
	public String getApplyOrgName() {
		return applyOrgName;
	}
	public void setApplyOrgName(String applyOrgName) {
		this.applyOrgName = applyOrgName;
	}
	public String getApplyOrgType() {
		return applyOrgType;
	}
	public void setApplyOrgType(String applyOrgType) {
		this.applyOrgType = applyOrgType;
	}
	public String getTrackState() {
		return trackState;
	}
	public void setTrackState(String trackState) {
		this.trackState = trackState;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
}

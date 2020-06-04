package com.creditharmony.approve.carloan.view;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.bpm.frame.view.BaseBusinessView;

public class CarVerifyBusinessView extends BaseBusinessView implements
		Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 7047874176750558854L;

	private String loanCode;				// 借款编号
	private String type;					// 借款人类型：主借人/共借人
	private Object json;					// 回退清单
	private String firstJson;				// 一级退回类型
	
	private Double firstServiceTariffing;		// 审批首期服务费率
	
	private String auditCheckExamine;			// 审批意见
	
	private String auditType;					// 审核类型：1 初审、2 复审、3 终审
	
	private String auditUserName;				// 审批人员姓名
	
	private String whetherLocal;				//是否本地
	
    private String ownEstate;					//有无房产
    
    private String workNature;					//工作性质
    
    private String industry;					//行业
    
    private String creditStatus;				//信用状况
    
    private String isWait;						// 判定是否待定
    
    /**
     * 原审核结果 ：auditStatus
     */
    private String auditResult;						// 审核结果		"1","通过"、"2","附条件通过"、"3","拒绝"、"4","退回"、"5","客户放弃"、"6","待定"
    
    private Double finalEvaluatedPrice;				// 终审评估金额
    /**
     * 原审批产品类型：private String auditProduct;	// 审批产品类型
     */
    private String auditBorrowProductCode;  		//产品类型编码（批复）
    /**
     * 原审批产品类型：private String auditProduct;	// 审批产品类型
     */
    private String auditBorrowProductName; 			//产品类型（批复）
    
    private Date auditTime;							//批借时间
    /**
     * 原审批期限：private String auditTerm;			// 审批期限
     */
    private String auditLoanMonths;				//借款期限（批复）
    
    private String conditionalThroughFlag;  		//附条件标识
    /**
     * 原审批总费率：private BigDecimal auditTotalRate;// 审批总费率
     */
    private Double grossRate;						//总费率
    /**
     * 原复审姓名：private String reCheckName;		// 复审姓名
     */
    private String recheckName;						//复审姓名
    
    /**
     * 原审批金额 ：private BigDecimal auditAmount;	// 审批金额
     */
    private Double auditAmount;						//批借金额   
    /**
     *  原借款状态 ：private String loanStatus;		// 借款状态
     */
    private String  dictStatus;						//借款状态（code） 
    
    private String operResultName;					// 操作结果，中文
    
    //排序字段
  	private String orderField;
  	//第一次退回的源节点名称--退回标红置顶业务所需
  	private String firstBackSourceStep;
  	
  	private String loanFlag;				// 渠道标识
  	private String timeOutFlag;				// 轮询
  	
  	private String contractVersion;
    private Double outVisitDistance;		//外访矩离
  	
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getJson() {
		return json;
	}
	public void setJson(Object json) {
		this.json = json;
	}
	
	public String getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	public Double getFirstServiceTariffing() {
		return firstServiceTariffing;
	}
	public void setFirstServiceTariffing(Double firstServiceTariffing) {
		this.firstServiceTariffing = firstServiceTariffing;
	}
	
	public String getAuditCheckExamine() {
		return auditCheckExamine;
	}
	public void setAuditCheckExamine(String auditCheckExamine) {
		this.auditCheckExamine = auditCheckExamine;
	}
	public Double getFinalEvaluatedPrice() {
		return finalEvaluatedPrice;
	}
	public void setFinalEvaluatedPrice(Double finalEvaluatedPrice) {
		this.finalEvaluatedPrice = finalEvaluatedPrice;
	}
	public String getAuditType() {
		return auditType;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	public String getAuditUserName() {
		return auditUserName;
	}
	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}
	public String getFirstJson() {
		return firstJson;
	}
	public void setFirstJson(String firstJson) {
		this.firstJson = firstJson;
	}
	public String getWhetherLocal() {
		return whetherLocal;
	}
	public void setWhetherLocal(String whetherLocal) {
		this.whetherLocal = whetherLocal;
	}
	public String getOwnEstate() {
		return ownEstate;
	}
	public void setOwnEstate(String ownEstate) {
		this.ownEstate = ownEstate;
	}
	public String getWorkNature() {
		return workNature;
	}
	public void setWorkNature(String workNature) {
		this.workNature = workNature;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getCreditStatus() {
		return creditStatus;
	}
	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}
	public String getIsWait() {
		return isWait;
	}
	public void setIsWait(String isWait) {
		this.isWait = isWait;
	}
	public String getAuditBorrowProductCode() {
		return auditBorrowProductCode;
	}
	public void setAuditBorrowProductCode(String auditBorrowProductCode) {
		this.auditBorrowProductCode = auditBorrowProductCode;
	}
	public String getAuditBorrowProductName() {
		return auditBorrowProductName;
	}
	public void setAuditBorrowProductName(String auditBorrowProductName) {
		this.auditBorrowProductName = auditBorrowProductName;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getAuditLoanMonths() {
		return auditLoanMonths;
	}
	public void setAuditLoanMonths(String auditLoanMonths) {
		this.auditLoanMonths = auditLoanMonths;
	}
	public String getConditionalThroughFlag() {
		return conditionalThroughFlag;
	}
	public void setConditionalThroughFlag(String conditionalThroughFlag) {
		this.conditionalThroughFlag = conditionalThroughFlag;
	}
	public Double getGrossRate() {
		return grossRate;
	}
	public void setGrossRate(Double grossRate) {
		this.grossRate = grossRate;
	}
	public String getRecheckName() {
		return recheckName;
	}
	public void setRecheckName(String recheckName) {
		this.recheckName = recheckName;
	}
	public Double getAuditAmount() {
		return auditAmount;
	}
	public void setAuditAmount(Double auditAmount) {
		this.auditAmount = auditAmount;
	}
	public String getDictStatus() {
		return dictStatus;
	}
	public void setDictStatus(String dictStatus) {
		this.dictStatus = dictStatus;
	}
	public String getOperResultName() {
		return operResultName;
	}
	public void setOperResultName(String operResultName) {
		this.operResultName = operResultName;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getFirstBackSourceStep() {
		return firstBackSourceStep;
	}
	public void setFirstBackSourceStep(String firstBackSourceStep) {
		this.firstBackSourceStep = firstBackSourceStep;
	}
	public String getLoanFlag() {
		return loanFlag;
	}
	public void setLoanFlag(String loanFlag) {
		this.loanFlag = loanFlag;
	}
	public String getTimeOutFlag() {
		return timeOutFlag;
	}
	public void setTimeOutFlag(String timeOutFlag) {
		this.timeOutFlag = timeOutFlag;
	}
	public String getContractVersion() {
		return contractVersion;
	}
	public void setContractVersion(String contractVersion) {
		this.contractVersion = contractVersion;
	}
	public Double getOutVisitDistance() {
		return outVisitDistance;
	}
	public void setOutVisitDistance(Double outVisitDistance) {
		this.outVisitDistance = outVisitDistance;
	}
	
	
}

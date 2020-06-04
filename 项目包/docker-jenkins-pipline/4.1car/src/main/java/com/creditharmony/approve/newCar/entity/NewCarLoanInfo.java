package com.creditharmony.approve.newCar.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class NewCarLoanInfo extends DataEntity<NewCarLoanInfo> {
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = -6914698305201165642L;
	// 借款编码
	private String loanCode;

	private String id;
	// APPLY_ID
	private String applyId;
	// 客户姓名
	private String loanCustomerName;
	// 客户编码
	private String customerCode;
	// 申请金额
	private BigDecimal loanApplyAmount;
	// 申请产品类型
	private String dictProductType;
	// 申请借款期限
	private Short loanMonths;
	// 申请时间
	private Date loanApplyTime;
	// 抵押权人
	private String mortgagee;
	// 授权人
	private String loanAuthorizer;
	// 停车费
	private Double parkingFee;
	// 设备费
	private Double facilityCharge;
	// 结清再借
	private String dictSettleRelend;
	// GPS是否拆除
	private String dictGpsRemaining;
	// 是否收取平台流量费
	private String dictIsGatherFlowFee;
	// 平台及流量费
	private Double flowFee;
	// 是否有共同还款人
	private String dictLoanCommonRepaymentFlag;
	// 借款用途
	private String dictLoanUse;
	// 退回时状态标识
	private String loanBackTopStatus;
	// 借款状态
	private String dictLoanStatus;
	// 还款方式
	private String dictRepayMethod;
	// 展期借款ID
	private String loanAdditionalApplyid;
	// 借款标识
	private String dictLoanFlag;
	// 外访标识
	private String outsideFlag;
	// 暗访标识
	private String visitFlag;
	// 客服编号
	private String loanCustomerService;
	// 客户经理CODE
	private String managerCode;
	// 团队经理编号
	private String consTeamManagercode;
	// 面审
	private String reviewMeet;
	// 团队编码
	private String storesName;
	// 退回原因类型
	private String dictBackMestype;
	// 备注
	private String remark;
	// 门店编码
	private String storeCode;
	// 门店名称
	private String storeName;
	//
	private String createBy;
	//
	private Date createTime;
	//
	private String modifyBy;
	//
	private Date modifyTime;
	// 车借_面审信息
	private NewInterviewInfo newInterviewInfo;
	// 进件时间
	private Date customerIntoTime;
	// 批复时间
	private Date loanAuditTime;
	// 附条件通过标识
	private String conditionalThroughFlag;
	// 是否展期
	private String isextension;
	// 展期原因
	private String extensionReason;
	// 原借款编码
	private String loanRawcode;
	// 初次进入汇诚时间
	private Date firstEntryApprove;
	// 出借标识
	private String loanFlag;
	// 结清时间
	private Date settledDate;
	
	private String queueName;
	
	private String dictsourcetype;// 来源版本(1:chp1.0 ;2:chp2. 0;3:chp3.0)
	
	private BigDecimal firstServiceTariffingRate;// 首期服务费率
	private String firstServiceChargeId;	//首期服务费率表主键
	
	private Double deviceUsedFee;	//设备作用费

	
	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode == null ? null : loanCode.trim();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
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
		this.loanCustomerName = loanCustomerName == null ? null
				: loanCustomerName.trim();
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode == null ? null : customerCode.trim();
	}

	public BigDecimal getLoanApplyAmount() {
		return loanApplyAmount;
	}

	public void setLoanApplyAmount(BigDecimal loanApplyAmount) {
		this.loanApplyAmount = loanApplyAmount;
	}

	public String getDictProductType() {
		return dictProductType;
	}

	public void setDictProductType(String dictProductType) {
		this.dictProductType = dictProductType == null ? null : dictProductType
				.trim();
	}

	public Short getLoanMonths() {
		return loanMonths;
	}

	public void setLoanMonths(Short loanMonths) {
		this.loanMonths = loanMonths;
	}

	public Date getLoanApplyTime() {
		return loanApplyTime;
	}

	public void setLoanApplyTime(Date loanApplyTime) {
		this.loanApplyTime = loanApplyTime;
	}

	public String getMortgagee() {
		return mortgagee;
	}

	public void setMortgagee(String mortgagee) {
		this.mortgagee = mortgagee == null ? null : mortgagee.trim();
	}

	public String getLoanAuthorizer() {
		return loanAuthorizer;
	}

	public void setLoanAuthorizer(String loanAuthorizer) {
		this.loanAuthorizer = loanAuthorizer == null ? null : loanAuthorizer
				.trim();
	}

	public Double getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(Double parkingFee) {
		this.parkingFee = parkingFee;
	}

	public Double getFacilityCharge() {
		return facilityCharge;
	}

	public void setFacilityCharge(Double facilityCharge) {
		this.facilityCharge = facilityCharge;
	}

	public String getDictSettleRelend() {
		return dictSettleRelend;
	}

	public void setDictSettleRelend(String dictSettleRelend) {
		this.dictSettleRelend = dictSettleRelend == null ? null
				: dictSettleRelend.trim();
	}

	public String getDictGpsRemaining() {
		return dictGpsRemaining;
	}

	public void setDictGpsRemaining(String dictGpsRemaining) {
		this.dictGpsRemaining = dictGpsRemaining == null ? null
				: dictGpsRemaining.trim();
	}

	public String getDictIsGatherFlowFee() {
		return dictIsGatherFlowFee;
	}

	public void setDictIsGatherFlowFee(String dictIsGatherFlowFee) {
		this.dictIsGatherFlowFee = dictIsGatherFlowFee == null ? null
				: dictIsGatherFlowFee.trim();
	}

	public Double getFlowFee() {
		return flowFee;
	}

	public void setFlowFee(Double flowFee) {
		this.flowFee = flowFee;
	}

	public String getDictLoanCommonRepaymentFlag() {
		return dictLoanCommonRepaymentFlag;
	}

	public void setDictLoanCommonRepaymentFlag(
			String dictLoanCommonRepaymentFlag) {
		this.dictLoanCommonRepaymentFlag = dictLoanCommonRepaymentFlag == null ? null
				: dictLoanCommonRepaymentFlag.trim();
	}

	public String getDictLoanUse() {
		return dictLoanUse;
	}

	public void setDictLoanUse(String dictLoanUse) {
		this.dictLoanUse = dictLoanUse == null ? null : dictLoanUse.trim();
	}

	public String getLoanBackTopStatus() {
		return loanBackTopStatus;
	}

	public void setLoanBackTopStatus(String loanBackTopStatus) {
		this.loanBackTopStatus = loanBackTopStatus == null ? null
				: loanBackTopStatus.trim();
	}

	public String getDictLoanStatus() {
		return dictLoanStatus;
	}

	public void setDictLoanStatus(String dictLoanStatus) {
		this.dictLoanStatus = dictLoanStatus == null ? null : dictLoanStatus
				.trim();
	}

	public String getDictRepayMethod() {
		return dictRepayMethod;
	}

	public void setDictRepayMethod(String dictRepayMethod) {
		this.dictRepayMethod = dictRepayMethod == null ? null : dictRepayMethod
				.trim();
	}

	public String getLoanAdditionalApplyid() {
		return loanAdditionalApplyid;
	}

	public void setLoanAdditionalApplyid(String loanAdditionalApplyid) {
		this.loanAdditionalApplyid = loanAdditionalApplyid == null ? null
				: loanAdditionalApplyid.trim();
	}

	public String getDictLoanFlag() {
		return dictLoanFlag;
	}

	public void setDictLoanFlag(String dictLoanFlag) {
		this.dictLoanFlag = dictLoanFlag == null ? null : dictLoanFlag.trim();
	}

	public String getOutsideFlag() {
		return outsideFlag;
	}

	public void setOutsideFlag(String outsideFlag) {
		this.outsideFlag = outsideFlag == null ? null : outsideFlag.trim();
	}

	public String getVisitFlag() {
		return visitFlag;
	}

	public void setVisitFlag(String visitFlag) {
		this.visitFlag = visitFlag == null ? null : visitFlag.trim();
	}

	public String getLoanCustomerService() {
		return loanCustomerService;
	}

	public void setLoanCustomerService(String loanCustomerService) {
		this.loanCustomerService = loanCustomerService == null ? null
				: loanCustomerService.trim();
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode == null ? null : managerCode.trim();
	}

	public String getConsTeamManagercode() {
		return consTeamManagercode;
	}

	public void setConsTeamManagercode(String consTeamManagercode) {
		this.consTeamManagercode = consTeamManagercode == null ? null
				: consTeamManagercode.trim();
	}

	public String getReviewMeet() {
		return reviewMeet;
	}

	public void setReviewMeet(String reviewMeet) {
		this.reviewMeet = reviewMeet == null ? null : reviewMeet.trim();
	}

	public String getStoresName() {
		return storesName;
	}

	public void setStoresName(String storesName) {
		this.storesName = storesName == null ? null : storesName.trim();
	}

	public String getDictBackMestype() {
		return dictBackMestype;
	}

	public void setDictBackMestype(String dictBackMestype) {
		this.dictBackMestype = dictBackMestype == null ? null : dictBackMestype
				.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode == null ? null : storeCode.trim();
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName == null ? null : storeName.trim();
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

	public Date getCustomerIntoTime() {
		return customerIntoTime;
	}

	public void setCustomerIntoTime(Date customerIntoTime) {
		this.customerIntoTime = customerIntoTime;
	}

	public NewInterviewInfo getNewInterviewInfo() {
		return newInterviewInfo;
	}

	public void setNewInterviewInfo(NewInterviewInfo newInterviewInfo) {
		this.newInterviewInfo = newInterviewInfo;
	}
	
	public Date getLoanAuditTime() {
		return loanAuditTime;
	}


	public void setLoanAuditTime(Date loanAuditTime) {
		this.loanAuditTime = loanAuditTime;
	}

	public String getConditionalThroughFlag() {
		return conditionalThroughFlag;
	}

	public void setConditionalThroughFlag(String conditionalThroughFlag) {
		this.conditionalThroughFlag = conditionalThroughFlag;
	}

	public String getIsextension() {
		return isextension;
	}

	public void setIsextension(String isextension) {
		this.isextension = isextension;
	}

	public String getExtensionReason() {
		return extensionReason;
	}

	public void setExtensionReason(String extensionReason) {
		this.extensionReason = extensionReason;
	}

	public String getLoanRawcode() {
		return loanRawcode;
	}

	public void setLoanRawcode(String loanRawcode) {
		this.loanRawcode = loanRawcode;
	}

	public Date getFirstEntryApprove() {
		return firstEntryApprove;
	}

	public void setFirstEntryApprove(Date firstEntryApprove) {
		this.firstEntryApprove = firstEntryApprove;
	}

	public String getLoanFlag() {
		return loanFlag;
	}

	public void setLoanFlag(String loanFlag) {
		this.loanFlag = loanFlag;
	}

	public Date getSettledDate() {
		return settledDate;
	}

	public void setSettledDate(Date settledDate) {
		this.settledDate = settledDate;
	}

	public String getDictsourcetype() {
		return dictsourcetype;
	}

	public void setDictsourcetype(String dictsourcetype) {
		this.dictsourcetype = dictsourcetype;
	}

	public BigDecimal getFirstServiceTariffingRate() {
		return firstServiceTariffingRate;
	}

	public void setFirstServiceTariffingRate(BigDecimal firstServiceTariffingRate) {
		this.firstServiceTariffingRate = firstServiceTariffingRate;
	}

	public String getFirstServiceChargeId() {
		return firstServiceChargeId;
	}

	public void setFirstServiceChargeId(String firstServiceChargeId) {
		this.firstServiceChargeId = firstServiceChargeId;
	}

	public Double getDeviceUsedFee() {
		return deviceUsedFee;
	}

	public void setDeviceUsedFee(Double deviceUsedFee) {
		this.deviceUsedFee = deviceUsedFee;
	}

}
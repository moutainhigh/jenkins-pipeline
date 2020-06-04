package com.creditharmony.approve.newCar.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 车借合同表实体
 * @Class Name CarContract
 * @author 申诗阔
 * @Create In 2016年3月26日
 */
public class NewCarContract extends DataEntity<NewCarContract> {

	private static final long serialVersionUID = 4594986245760027591L;
	// ID
	private String ID;
	// 借款编码
	private String loanCode;
	// 合同编号
	private String contractCode;
	// 预约签署日期
	private Date contractDueDay;
	// 实际签署日期
	private Date contractFactDay;
	// 首期还款日期
	private Date contractReplayDay;
	// 合同到期日期
	private Date contractEndDay;
	// 产品类型
	private String productType;
	// 产品类型名称
	private String productTypeName;
	// 批复金额
	private BigDecimal auditAmount;
	// 批借期限
	private Integer contractMonths;
	// 中间人主键ID
	private String midId;
	// 还款付息方式
	private String dictRepayMethod;
	// 合同金额
	private BigDecimal contractAmount;
	// 预计还款总额
	private BigDecimal contractExpectAmount;
	// 月还款额
	private BigDecimal contractMonthRepayAmount;
	// 审核状态
	private String dictCheckStatus;
	// 待放款确认退回原因
	private String contractBackResult;
	// 合同版本号
	private String contractVersion;
	// 创建人
	private String createBy;
	// 创建时间
	private Date createTime;
	// 修改人
	private String modifyBy;
	// 修改时间
	private Date modifyTime;
	// 总费率
	private BigDecimal grossRate;
	// 展期费用
	private BigDecimal extensionFee;
	// 降额
	private BigDecimal derate;
	// 合同编号后的数字
	private String numCount;
	// 中间人姓名
	private String midIdName;
	// 综合费用支付方式
	private String dictFeeMethod;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public Date getContractDueDay() {
		return contractDueDay;
	}

	public void setContractDueDay(Date contractDueDay) {
		this.contractDueDay = contractDueDay;
	}

	public Date getContractFactDay() {
		return contractFactDay;
	}

	public void setContractFactDay(Date contractFactDay) {
		this.contractFactDay = contractFactDay;
	}

	public Date getContractReplayDay() {
		return contractReplayDay;
	}

	public void setContractReplayDay(Date contractReplayDay) {
		this.contractReplayDay = contractReplayDay;
	}

	public Date getContractEndDay() {
		return contractEndDay;
	}

	public void setContractEndDay(Date contractEndDay) {
		this.contractEndDay = contractEndDay;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public BigDecimal getAuditAmount() {
		return auditAmount;
	}

	public void setAuditAmount(BigDecimal auditAmount) {
		this.auditAmount = auditAmount;
	}

	public Integer getContractMonths() {
		return contractMonths;
	}

	public void setContractMonths(Integer contractMonths) {
		this.contractMonths = contractMonths;
	}

	public String getMidId() {
		return midId;
	}

	public void setMidId(String midId) {
		this.midId = midId;
	}

	public String getDictRepayMethod() {
		return dictRepayMethod;
	}

	public void setDictRepayMethod(String dictRepayMethod) {
		this.dictRepayMethod = dictRepayMethod;
	}

	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	public BigDecimal getContractExpectAmount() {
		return contractExpectAmount;
	}

	public void setContractExpectAmount(BigDecimal contractExpectAmount) {
		this.contractExpectAmount = contractExpectAmount;
	}

	public BigDecimal getContractMonthRepayAmount() {
		return contractMonthRepayAmount;
	}

	public void setContractMonthRepayAmount(BigDecimal contractMonthRepayAmount) {
		this.contractMonthRepayAmount = contractMonthRepayAmount;
	}

	public String getDictCheckStatus() {
		return dictCheckStatus;
	}

	public void setDictCheckStatus(String dictCheckStatus) {
		this.dictCheckStatus = dictCheckStatus;
	}

	public String getContractBackResult() {
		return contractBackResult;
	}

	public void setContractBackResult(String contractBackResult) {
		this.contractBackResult = contractBackResult;
	}

	public String getContractVersion() {
		return contractVersion;
	}

	public void setContractVersion(String contractVersion) {
		this.contractVersion = contractVersion;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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
		this.modifyBy = modifyBy;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getGrossRate() {
		return grossRate;
	}

	public void setGrossRate(BigDecimal grossRate) {
		this.grossRate = grossRate;
	}

	public BigDecimal getExtensionFee() {
		return extensionFee;
	}

	public void setExtensionFee(BigDecimal extensionFee) {
		this.extensionFee = extensionFee;
	}

	public BigDecimal getDerate() {
		return derate;
	}

	public void setDerate(BigDecimal derate) {
		this.derate = derate;
	}

	public String getNumCount() {
		return numCount;
	}

	public void setNumCount(String numCount) {
		this.numCount = numCount;
	}

	public String getMidIdName() {
		return midIdName;
	}

	public void setMidIdName(String midIdName) {
		this.midIdName = midIdName;
	}

	public String getDictFeeMethod() {
		return dictFeeMethod;
	}

	public void setDictFeeMethod(String dictFeeMethod) {
		this.dictFeeMethod = dictFeeMethod;
	}

}

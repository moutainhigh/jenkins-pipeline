package com.creditharmony.approve.document.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class ZlshGxht extends DataEntity<ZlshGxht>{
    
	private static final long serialVersionUID = 1L;

	private String loanCode;				// 借款编码
	private String rCustomerCoborrowerId;	// 关联ID(主借人，共借人)
	private String dictCustomerType;		// 借款人类型(主借人/共借人)
	private String gxhtSignedType;			// 签订类型(上下游)
	private String gxhtCompanyName;			// 公司名称
	private String dictContractType;		// 合同类型
	private Double gxhtContractAmount;		// 合同金额
	private Date gxhtContractDeadline;		// 合同有效期
	private String dictSettlementType;		// 结算类型
	private String companyTel;				// 电话号码
	private String dictTelSource;			// 电话来源
	private String dictCheckType;			// 类型(初审，信审初审，复议初审)
	private String editRemark;				// 编辑标识
	private String dictSourceType;
	
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
	public String getGxhtSignedType() {
		return gxhtSignedType;
	}
	public void setGxhtSignedType(String gxhtSignedType) {
		this.gxhtSignedType = gxhtSignedType;
	}
	public String getGxhtCompanyName() {
		return gxhtCompanyName;
	}
	public void setGxhtCompanyName(String gxhtCompanyName) {
		this.gxhtCompanyName = gxhtCompanyName;
	}
	public String getDictContractType() {
		return dictContractType;
	}
	public void setDictContractType(String dictContractType) {
		this.dictContractType = dictContractType;
	}
	public Double getGxhtContractAmount() {
		return gxhtContractAmount;
	}
	public void setGxhtContractAmount(Double gxhtContractAmount) {
		this.gxhtContractAmount = gxhtContractAmount;
	}
	public Date getGxhtContractDeadline() {
		return gxhtContractDeadline;
	}
	public void setGxhtContractDeadline(Date gxhtContractDeadline) {
		this.gxhtContractDeadline = gxhtContractDeadline;
	}
	public String getDictSettlementType() {
		return dictSettlementType;
	}
	public void setDictSettlementType(String dictSettlementType) {
		this.dictSettlementType = dictSettlementType;
	}
	public String getCompanyTel() {
		return companyTel;
	}
	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	public String getDictTelSource() {
		return dictTelSource;
	}
	public void setDictTelSource(String dictTelSource) {
		this.dictTelSource = dictTelSource;
	}
	public String getDictCheckType() {
		return dictCheckType;
	}
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
	public String getEditRemark() {
		return editRemark;
	}
	public void setEditRemark(String editRemark) {
		this.editRemark = editRemark;
	}
	public String getDictSourceType() {
		return dictSourceType;
	}
	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}	
}
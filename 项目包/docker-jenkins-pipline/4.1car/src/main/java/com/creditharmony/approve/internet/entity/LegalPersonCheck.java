package com.creditharmony.approve.internet.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 法人保证人-网查
 * 
 * @author 罗俊平
 * @create in 2016-11-10
 */
public class LegalPersonCheck extends DataEntity<LegalPersonCheck> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1777653116138548872L;

	private String id;// 主键ID
	private String loanCode;// 借款编码
	private String rCustomerCoborrowerId;// 关联ID
	private String dictCustomerType;// 客户类型
	private String dictCheckType;// 审核类型
	private String corporateRepresent;// 法人保证人姓名
	private String certNum;// 法人保证人身份证号码
	private String corporateRepresentMobile;// 法人保证人电话号码
	private String compEmail;// 企业邮箱
	private String comLegalManResult;// 网查结果（法人代表人姓名）
	private String comLegalManRemark;// 网查备注（法人代表人姓名）
	private String comLegalManNumResult;// 网查结果（法人代表人身份证件号）
	private String comLegalManNumRemark;// 网查备注（法人代表人身份证件号）
	private String comLegalManMoblieResult;// 网查结果（法人代表人手机号）
	private String comLegalManMoblieRemark;// 网查备注（法人代表人手机号）
	private String comEmailNetResult;// 网查结果（企业邮箱）
	private String comEmailNetRemark;// 网查备注（企业邮箱）

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

	public String getCorporateRepresent() {
		return corporateRepresent;
	}

	public void setCorporateRepresent(String corporateRepresent) {
		this.corporateRepresent = corporateRepresent;
	}

	public String getCertNum() {
		return certNum;
	}

	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}

	public String getCorporateRepresentMobile() {
		return corporateRepresentMobile;
	}

	public void setCorporateRepresentMobile(String corporateRepresentMobile) {
		this.corporateRepresentMobile = corporateRepresentMobile;
	}

	public String getCompEmail() {
		return compEmail;
	}

	public void setCompEmail(String compEmail) {
		this.compEmail = compEmail;
	}

	public String getComLegalManResult() {
		return comLegalManResult;
	}

	public void setComLegalManResult(String comLegalManResult) {
		this.comLegalManResult = comLegalManResult;
	}

	public String getComLegalManRemark() {
		return comLegalManRemark;
	}

	public void setComLegalManRemark(String comLegalManRemark) {
		this.comLegalManRemark = comLegalManRemark;
	}

	public String getComLegalManNumResult() {
		return comLegalManNumResult;
	}

	public void setComLegalManNumResult(String comLegalManNumResult) {
		this.comLegalManNumResult = comLegalManNumResult;
	}

	public String getComLegalManNumRemark() {
		return comLegalManNumRemark;
	}

	public void setComLegalManNumRemark(String comLegalManNumRemark) {
		this.comLegalManNumRemark = comLegalManNumRemark;
	}

	public String getComLegalManMoblieResult() {
		return comLegalManMoblieResult;
	}

	public void setComLegalManMoblieResult(String comLegalManMoblieResult) {
		this.comLegalManMoblieResult = comLegalManMoblieResult;
	}

	public String getComLegalManMoblieRemark() {
		return comLegalManMoblieRemark;
	}

	public void setComLegalManMoblieRemark(String comLegalManMoblieRemark) {
		this.comLegalManMoblieRemark = comLegalManMoblieRemark;
	}

	public String getComEmailNetResult() {
		return comEmailNetResult;
	}

	public void setComEmailNetResult(String comEmailNetResult) {
		this.comEmailNetResult = comEmailNetResult;
	}

	public String getComEmailNetRemark() {
		return comEmailNetRemark;
	}

	public void setComEmailNetRemark(String comEmailNetRemark) {
		this.comEmailNetRemark = comEmailNetRemark;
	}

}

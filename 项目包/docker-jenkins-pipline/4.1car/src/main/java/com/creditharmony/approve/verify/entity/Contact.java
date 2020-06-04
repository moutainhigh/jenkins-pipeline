package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 罗俊平
 * @update in 2016-10-22
 */
public class Contact {

	private BigDecimal id;

	private String loanCode;

	private String rId;

	private String contactName;

	private String contactRelation;

	private String relationType;

	private String loanCustomterType;

	private BigDecimal contactSex;

	private String contactUnitTel;

	private String contactMobile;

	private String contactNowAddress;

	private String contactEmail;

	private String createBy;

	private Date createTime;

	private String modifyBy;

	private Date modifyTime;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode == null ? null : loanCode.trim();
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName == null ? null : contactName.trim();
	}

	public String getContactRelation() {
		return contactRelation;
	}

	public void setContactRelation(String contactRelation) {
		this.contactRelation = contactRelation == null ? null : contactRelation.trim();
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public String getLoanCustomterType() {
		return loanCustomterType;
	}

	public void setLoanCustomterType(String loanCustomterType) {
		this.loanCustomterType = loanCustomterType == null ? null : loanCustomterType.trim();
	}

	public BigDecimal getContactSex() {
		return contactSex;
	}

	public void setContactSex(BigDecimal contactSex) {
		this.contactSex = contactSex;
	}

	public String getContactUnitTel() {
		return contactUnitTel;
	}

	public void setContactUnitTel(String contactUnitTel) {
		this.contactUnitTel = contactUnitTel == null ? null : contactUnitTel.trim();
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile == null ? null : contactMobile.trim();
	}

	public String getContactNowAddress() {
		return contactNowAddress;
	}

	public void setContactNowAddress(String contactNowAddress) {
		this.contactNowAddress = contactNowAddress == null ? null : contactNowAddress.trim();
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail == null ? null : contactEmail.trim();
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

	/**
	 * 宅电
	 */
	private String homeTel;
	/**
	 * 其他联系人时的关系备注（工作证明人选择其他时的备注）
	 */
	private String remarks;

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

	/**
	 * 其他联系人时的关系备注（工作证明人选择其他时的备注）
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 其他联系人时的关系备注（工作证明人选择其他时的备注）
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
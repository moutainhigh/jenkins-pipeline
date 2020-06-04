package com.creditharmony.approve.internet.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 专网核查
 * 
 * @Class Name PrivateNetworkCheck
 * @author 刘燕军
 * @Create In 2016年2月4日
 */
public class PrivateNetworkCheck extends DataEntity<PrivateNetworkCheck> {

	private static final long serialVersionUID = 1L;

	private String loanCode;
	private String dictCheckType;
	private String relId;
	private String dictCustomerType;
	private String checkType;
	private String configId;
	private String result;
	private String remark;
	private String content;
	private String flag;
	private String name;
	private String workId;
	private String dictSourceType;
	private String relationship;

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getDictCheckType() {
		return dictCheckType;
	}

	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}

	public String getRelId() {
		return relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

	public String getDictCustomerType() {
		return dictCustomerType;
	}

	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getDictSourceType() {
		return dictSourceType;
	}

	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

}

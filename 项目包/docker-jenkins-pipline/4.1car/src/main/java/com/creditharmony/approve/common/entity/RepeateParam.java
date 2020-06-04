package com.creditharmony.approve.common.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 查重相关参数对应的实体
 * @Class Name RepeateParam
 * @author 刘燕军
 * @Create In 2016年5月21日
 */
public class RepeateParam extends DataEntity<RepeateParam>{

	private static final long serialVersionUID = 1L;
	
	private String loanCode;
	private String repeateContent;
	private String type;
	private String relId;
	private String repeateType;
	private String checkType;
	private String src;
	private String user;
	
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getRepeateContent() {
		return repeateContent;
	}
	public void setRepeateContent(String repeateContent) {
		this.repeateContent = repeateContent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRelId() {
		return relId;
	}
	public void setRelId(String relId) {
		this.relId = relId;
	}
	public String getRepeateType() {
		return repeateType;
	}
	public void setRepeateType(String repeateType) {
		this.repeateType = repeateType;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	
}

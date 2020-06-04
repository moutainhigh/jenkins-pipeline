package com.creditharmony.approve.common.view;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"orderField","loanStatusCode","loanStatusName"})
@XmlRootElement
public class ReconsiderInitView {
	private String loanStatusCode;  // 借款状态编码
	private String loanStatusName;  // 借款状态名称
	private String orderField;      // 工作流排序
	@XmlElement(name="loanStatusCode") 
	public String getLoanStatusCode() {
		return loanStatusCode;
	}
	public void setLoanStatusCode(String loanStatusCode) {
		this.loanStatusCode = loanStatusCode;
	}
	@XmlElement(name="loanStatusName")  
	public String getLoanStatusName() {
		return loanStatusName;
	}
	public void setLoanStatusName(String loanStatusName) {
		this.loanStatusName = loanStatusName;
	}
	@XmlElement(name="orderField")  
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
}

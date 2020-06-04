package com.creditharmony.approve.common.view;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 信审预处理返回值
 * @Class Name VerifyInitView
 * @author xiaoniu.hu
 * @Create In 2016年4月14日
 */
@XmlType(propOrder = {"orderField","loanStatusCode","loanStatusName"})
@XmlRootElement
public class VerifyInitView {
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

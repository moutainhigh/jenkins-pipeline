package com.creditharmony.approve.common.view;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 返回规则判定的结果，以及工作流中要更新的状态
 * @Class Name PolicyEngineView
 * @author 王浩
 * @Create In 2016年3月16日
 */
@XmlType(name = "", propOrder = {
		  "policyEngine",
		  "loanStatusCode",
		  "loanStatusName"
		})
@XmlRootElement
public class PolicyEngineView {
	
	private String policyEngine; // 决策引擎结果
	private String loanStatusCode;  // 借款状态编码
	private String loanStatusName;  // 借款状态名称
	
	public String getPolicyEngine() {
		return policyEngine;
	}
	public void setPolicyEngine(String policyEngine) {
		this.policyEngine = policyEngine;
	}
	public String getLoanStatusCode() {
		return loanStatusCode;
	}
	public void setLoanStatusCode(String loanStatusCode) {
		this.loanStatusCode = loanStatusCode;
	}
	public String getLoanStatusName() {
		return loanStatusName;
	}
	public void setLoanStatusName(String loanStatusName) {
		this.loanStatusName = loanStatusName;
	}
	
}

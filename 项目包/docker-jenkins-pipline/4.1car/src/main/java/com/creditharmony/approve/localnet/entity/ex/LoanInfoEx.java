package com.creditharmony.approve.localnet.entity.ex;

import com.creditharmony.approve.verify.entity.LoanInfo;

/**
 * 申请信息的实体Ex
 * 
 * @Class Name LoanInfoEx
 * @author 刘燕军
 * @Create In 2015年12月1日
 */
public class LoanInfoEx extends LoanInfo {
	private static final long serialVersionUID = 1L;

	private String productMonths;// 产品期数
	private String orgName;// 机构名字
	private String productName;// 产品名字
	private String result;     // 引擎结果

	public String getProductMonths() {
		return productMonths;
	}

	public void setProductMonths(String productMonths) {
		this.productMonths = productMonths;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}

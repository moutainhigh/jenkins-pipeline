package com.creditharmony.approve.verify.entity.ex;

import com.creditharmony.bpm.frame.view.BaseBusinessView;

/**
 * 查询参照通用实体
 * @Class Name VerifyParamEx
 * @author 刘燕军
 * @Create In 2015年12月11日
 */
public class VerifyParamEx extends BaseBusinessView{
	private String loanCode; // 借款编号
	private String type ; //借款人类型 主借人/共借人
	private String checkType; // 审批步骤 初审 复审等
	private String relId; // 关联id（客户id）
	private String customerName; // 客户姓名
	private String customerCertNum; // 身份证号
	private String customerSex; // 性别
	private String sunyardUrl;        // 信雅达地址信息
	private String outsideFlag;        // 外访标识
	
	public String getOutsideFlag() {
		return outsideFlag;
	}
	public void setOutsideFlag(String outsideFlag) {
		this.outsideFlag = outsideFlag;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getRelId() {
		return relId;
	}
	public void setRelId(String relId) {
		this.relId = relId;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCertNum() {
		return customerCertNum;
	}
	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}
	public String getCustomerSex() {
		return customerSex;
	}
	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}
	public String getSunyardUrl() {
		return sunyardUrl;
	}
	public void setSunyardUrl(String sunyardUrl) {
		this.sunyardUrl = sunyardUrl;
	}
}

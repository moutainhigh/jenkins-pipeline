package com.creditharmony.approve.common.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 待办查询实体
 * @Class Name SearchParam
 * @author 刘燕军
 * @Create In 2016年3月19日
 */
public class SearchParam implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String customerName; // 客户姓名
	private String identityCode; // 身份证号
	private String applyProductCode; // 申请产品
	private String provinceCode; // 省份
	private String cityCode; // 城市
	private String storeName; //门店
	private Date minIntoApproveTime; // 起始进件时间
	private Date maxIntoApproveTime; // 结束进件时间
	private Date minOutApproveTime; // 起始审批时间
	private Date maxOutApproveTime; // 结束审批时间
	private String loanStatusName;  // 借款状态
	private String dealUser;			//当前处理人
	private String loanCode;			// 借款编号
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getIdentityCode() {
		return identityCode;
	}
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	public String getApplyProductCode() {
		return applyProductCode;
	}
	public void setApplyProductCode(String applyProductCode) {
		this.applyProductCode = applyProductCode;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Date getMinIntoApproveTime() {
		return minIntoApproveTime;
	}
	public void setMinIntoApproveTime(Date minIntoApproveTime) {
		this.minIntoApproveTime = minIntoApproveTime;
	}
	public Date getMaxIntoApproveTime() {
		return maxIntoApproveTime;
	}
	public void setMaxIntoApproveTime(Date maxIntoApproveTime) {
		this.maxIntoApproveTime = maxIntoApproveTime;
	}
	public Date getMinOutApproveTime() {
		return minOutApproveTime;
	}
	public void setMinOutApproveTime(Date minOutApproveTime) {
		this.minOutApproveTime = minOutApproveTime;
	}
	public Date getMaxOutApproveTime() {
		return maxOutApproveTime;
	}
	public void setMaxOutApproveTime(Date maxOutApproveTime) {
		this.maxOutApproveTime = maxOutApproveTime;
	}
	public String getLoanStatusName() {
		return loanStatusName;
	}
	public void setLoanStatusName(String loanStatusName) {
		this.loanStatusName = loanStatusName;
	}
	public String getDealUser() {
		return dealUser;
	}
	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

}

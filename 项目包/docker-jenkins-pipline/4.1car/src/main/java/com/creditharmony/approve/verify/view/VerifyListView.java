package com.creditharmony.approve.verify.view;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name VerifyListView
 * @author 黄维
 * @Create In 2015年12月4日
 */
public class VerifyListView extends DataEntity<VerifyListView> {

	private static final long serialVersionUID = 6789397380910483794L;

	private String loanCode;//借款编码
	private String loanCustomerName;//客户姓名
	private String customerCertNum;//证件号码
	private String province;//门店省
	private String cityId;//门店市
	private String orgName;//门店ID
	private String productType;//产品类型
	private String loanUrgentFlag;//是否加急
	private Double loanApplyAmount;//申请金额
	private Integer loanMonths;//分期
	private Date customerIntoTime;//进件时间
	private Date assignmTaskTime;//分单时间
	private String finishStatus;//状态
	private String userCode;//当前所属员工编码
	
	private Date minCustomerIntoTime;//进件时间
	private Date maxCustomerIntoTime;//进件时间
	private Date minLoanAuditTime;//批复时间
	private Date maxLoanAuditTime;//批复时间
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getLoanCustomerName() {
		return loanCustomerName;
	}
	public void setLoanCustomerName(String loanCustomerName) {
		this.loanCustomerName = loanCustomerName;
	}
	public String getCustomerCertNum() {
		return customerCertNum;
	}
	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getLoanUrgentFlag() {
		return loanUrgentFlag;
	}
	public void setLoanUrgentFlag(String loanUrgentFlag) {
		this.loanUrgentFlag = loanUrgentFlag;
	}
	public Double getLoanApplyAmount() {
		return loanApplyAmount;
	}
	public void setLoanApplyAmount(Double loanApplyAmount) {
		this.loanApplyAmount = loanApplyAmount;
	}
	public Integer getLoanMonths() {
		return loanMonths;
	}
	public void setLoanMonths(Integer loanMonths) {
		this.loanMonths = loanMonths;
	}
	public Date getCustomerIntoTime() {
		return customerIntoTime;
	}
	public void setCustomerIntoTime(Date customerIntoTime) {
		this.customerIntoTime = customerIntoTime;
	}
	public Date getAssignmTaskTime() {
		return assignmTaskTime;
	}
	public void setAssignmTaskTime(Date assignmTaskTime) {
		this.assignmTaskTime = assignmTaskTime;
	}
	public String getFinishStatus() {
		return finishStatus;
	}
	public void setFinishStatus(String finishStatus) {
		this.finishStatus = finishStatus;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Date getMinCustomerIntoTime() {
		return minCustomerIntoTime;
	}
	public void setMinCustomerIntoTime(Date minCustomerIntoTime) {
		this.minCustomerIntoTime = minCustomerIntoTime;
	}
	public Date getMaxCustomerIntoTime() {
		return maxCustomerIntoTime;
	}
	public void setMaxCustomerIntoTime(Date maxCustomerIntoTime) {
		this.maxCustomerIntoTime = maxCustomerIntoTime;
	}
	public Date getMinLoanAuditTime() {
		return minLoanAuditTime;
	}
	public void setMinLoanAuditTime(Date minLoanAuditTime) {
		this.minLoanAuditTime = minLoanAuditTime;
	}
	public Date getMaxLoanAuditTime() {
		return maxLoanAuditTime;
	}
	public void setMaxLoanAuditTime(Date maxLoanAuditTime) {
		this.maxLoanAuditTime = maxLoanAuditTime;
	}
}

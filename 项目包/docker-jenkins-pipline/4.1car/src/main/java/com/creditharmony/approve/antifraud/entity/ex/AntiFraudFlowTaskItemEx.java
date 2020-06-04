package com.creditharmony.approve.antifraud.entity.ex;

import java.util.Date;

import com.creditharmony.bpm.frame.view.BaseTaskItemView;
/**
 * 反欺诈专员代办
 * @Class Name AntiFraudFlowTaskItemEx
 * @author wanglidong
 * @Create In 2015年12月16日
 */
public class AntiFraudFlowTaskItemEx extends BaseTaskItemView{

	private String loanCode; 			// 借款编号
	private String customerName; 		// 客户姓名
	private String mateCertNum; 		// 证件号码
	private String addrProvince; 		// 省份
	private String addrCity; 			// 城市
	private String contStoresName; 		// 门店名称
	private String borrowProduct; 		// 产品名称
	private String loanIsUrgent; 		// 是否加急
	private Double loanApplyMoney;		// 申请金额
	private Integer loanMonths;			// 借款期数
	private String dictStatus;			// 借款状态
	private Date customerIntoTime;		// 进件时间
	private Date singleTime;			// 分单时间
	private Double loanAuditAmount; 	// 批复金额
	private Date loanAuditTime; 		// 批复时间
	private String loanAuditProduct; 	// 批复产品
	private Integer loanAuditMonths; 	// 批借期限
	private String coborrowerName;		// 共借人
	private String loanTeamEmpcode; 	// 团队经理
	private String offendSalesName; 	// 客户经理
	
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMateCertNum() {
		return mateCertNum;
	}
	public void setMateCertNum(String mateCertNum) {
		this.mateCertNum = mateCertNum;
	}
	public String getAddrProvince() {
		return addrProvince;
	}
	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}
	public String getAddrCity() {
		return addrCity;
	}
	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	public String getContStoresName() {
		return contStoresName;
	}
	public void setContStoresName(String contStoresName) {
		this.contStoresName = contStoresName;
	}
	public String getBorrowProduct() {
		return borrowProduct;
	}
	public void setBorrowProduct(String borrowProduct) {
		this.borrowProduct = borrowProduct;
	}
	public String getLoanIsUrgent() {
		return loanIsUrgent;
	}
	public void setLoanIsUrgent(String loanIsUrgent) {
		this.loanIsUrgent = loanIsUrgent;
	}
	public Double getLoanApplyMoney() {
		return loanApplyMoney;
	}
	public void setLoanApplyMoney(Double loanApplyMoney) {
		this.loanApplyMoney = loanApplyMoney;
	}
	public Integer getLoanMonths() {
		return loanMonths;
	}
	public void setLoanMonths(Integer loanMonths) {
		this.loanMonths = loanMonths;
	}
	public String getDictStatus() {
		return dictStatus;
	}
	public void setDictStatus(String dictStatus) {
		this.dictStatus = dictStatus;
	}
	public Date getCustomerIntoTime() {
		return customerIntoTime;
	}
	public void setCustomerIntoTime(Date customerIntoTime) {
		this.customerIntoTime = customerIntoTime;
	}
	public Date getSingleTime() {
		return singleTime;
	}
	public void setSingleTime(Date singleTime) {
		this.singleTime = singleTime;
	}
	public Double getLoanAuditAmount() {
		return loanAuditAmount;
	}
	public void setLoanAuditAmount(Double loanAuditAmount) {
		this.loanAuditAmount = loanAuditAmount;
	}
	public Date getLoanAuditTime() {
		return loanAuditTime;
	}
	public void setLoanAuditTime(Date loanAuditTime) {
		this.loanAuditTime = loanAuditTime;
	}
	public String getLoanAuditProduct() {
		return loanAuditProduct;
	}
	public void setLoanAuditProduct(String loanAuditProduct) {
		this.loanAuditProduct = loanAuditProduct;
	}
	public Integer getLoanAuditMonths() {
		return loanAuditMonths;
	}
	public void setLoanAuditMonths(Integer loanAuditMonths) {
		this.loanAuditMonths = loanAuditMonths;
	}
	public String getCoborrowerName() {
		return coborrowerName;
	}
	public void setCoborrowerName(String coborrowerName) {
		this.coborrowerName = coborrowerName;
	}
	public String getLoanTeamEmpcode() {
		return loanTeamEmpcode;
	}
	public void setLoanTeamEmpcode(String loanTeamEmpcode) {
		this.loanTeamEmpcode = loanTeamEmpcode;
	}
	public String getOffendSalesName() {
		return offendSalesName;
	}
	public void setOffendSalesName(String offendSalesName) {
		this.offendSalesName = offendSalesName;
	}
}

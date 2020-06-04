package com.creditharmony.approve.credit.entity;

import java.util.Date;
import java.util.List;



import com.creditharmony.core.persistence.DataEntity;

/**
 * 简版信用报告
 * @Class Name CreditReportSimple
 * @author 李文勇
 * @Create In 2015年12月29日
 */
public class CreditReportSimple extends DataEntity<CreditReportSimple> {
	
	private static final long serialVersionUID = 1L;
	private String loanCode;				// 借款编号
	private String creditSource;			// 数据来源
	private String creditCode;				// 编号
	private String dictCustomerType;		// 借款人类型（主借人/共借人）
	private String rCustomerCoborrowerId;	// 关联ID（区分共借人）
	private List<CreditCardInfo> creditCardInfoList;	// 信用卡信息list
	private List<CreditLoanInfo> creditLoanInfoList;	// 贷款信息list
	private List<CreditQueryRecord> creditQueryRecordList;	// 查询记录信息list
	private List<CreditPaybackInfo> creditPaybackInfoList; //保证人代偿信息
	private Date queryTime;						// 查询时间
	private String customerName;
	private String name;						// 姓名
	private String certNo;						// 身份证号
	private String marryStatus;					// 婚姻状况
	private String highestEducation;			// 最该学历
	
	
	private String applyId;//
	private String customerId;//主借人编号	
	
	/** 爬虫HTML */
	private String htmlUrl;
	
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getCreditSource() {
		return creditSource;
	}
	public void setCreditSource(String creditSource) {
		this.creditSource = creditSource;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getDictCustomerType() {
		return dictCustomerType;
	}
	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}
	public String getrCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}
	public void setrCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
	}
	public List<CreditCardInfo> getCreditCardInfoList() {
		return creditCardInfoList;
	}
	public void setCreditCardInfoList(List<CreditCardInfo> creditCardInfoList) {
		this.creditCardInfoList = creditCardInfoList;
	}
	public List<CreditLoanInfo> getCreditLoanInfoList() {
		return creditLoanInfoList;
	}
	public void setCreditLoanInfoList(List<CreditLoanInfo> creditLoanInfoList) {
		this.creditLoanInfoList = creditLoanInfoList;
	}
	public List<CreditQueryRecord> getCreditQueryRecordList() {
		return creditQueryRecordList;
	}
	public void setCreditQueryRecordList(
			List<CreditQueryRecord> creditQueryRecordList) {
		this.creditQueryRecordList = creditQueryRecordList;
	}
	public Date getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(Date queryTime) {
		this.queryTime = queryTime;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getHtmlUrl() {
		return htmlUrl;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}
	public List<CreditPaybackInfo> getCreditPaybackInfoList() {
		return creditPaybackInfoList;
	}
	public void setCreditPaybackInfoList(
			List<CreditPaybackInfo> creditPaybackInfoList) {
		this.creditPaybackInfoList = creditPaybackInfoList;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getMarryStatus() {
		return marryStatus;
	}
	public void setMarryStatus(String marryStatus) {
		this.marryStatus = marryStatus;
	}
	public String getHighestEducation() {
		return highestEducation;
	}
	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}	
	
}

package com.creditharmony.approve.document.entity;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 资料审核 社保公积金
 * @Class Name ZlshSbgjj
 * @author 黄维
 * @Create In 2015年12月15日
 */
public class ZlshSbgjj extends DataEntity<ZlshSbgjj>{
    
	private static final long serialVersionUID = -6998917577854034580L;

	private String loanCode;// 借款编码
    private String rId;// 关联ID
    private String dictCustomerType;// 借款人类型(主借人/共借人)
    private String socialSecurityType;// 社保类型(社保,公积金)
    private Integer companyPayMonths;// 本公司缴纳月数
    private Double sbgjjPayMonth;// 月缴纳金额
    private Double sbgjjPayBase;// 缴纳基数
    private Double sbgjjFreeAmount;// 余额
    private String dictCheckType;// 类型(初审，信审初审，复议初审)
    private String dictSourceType;
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public String getDictCustomerType() {
		return dictCustomerType;
	}
	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}
	public String getSocialSecurityType() {
		return socialSecurityType;
	}
	public void setSocialSecurityType(String socialSecurityType) {
		this.socialSecurityType = socialSecurityType;
	}
	public Integer getCompanyPayMonths() {
		return companyPayMonths;
	}
	public void setCompanyPayMonths(Integer companyPayMonths) {
		this.companyPayMonths = companyPayMonths;
	}
	public Double getSbgjjPayMonth() {
		return sbgjjPayMonth;
	}
	public void setSbgjjPayMonth(Double sbgjjPayMonth) {
		this.sbgjjPayMonth = sbgjjPayMonth;
	}
	public Double getSbgjjPayBase() {
		return sbgjjPayBase;
	}
	public void setSbgjjPayBase(Double sbgjjPayBase) {
		this.sbgjjPayBase = sbgjjPayBase;
	}
	public Double getSbgjjFreeAmount() {
		return sbgjjFreeAmount;
	}
	public void setSbgjjFreeAmount(Double sbgjjFreeAmount) {
		this.sbgjjFreeAmount = sbgjjFreeAmount;
	}
	public String getDictCheckType() {
		return dictCheckType;
	}
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
	public String getDictSourceType() {
		return dictSourceType;
	}
	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}    
}
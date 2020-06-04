package com.creditharmony.approve.document.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 资料审核 经营地址证明
 * @Class Name ZlshJydzzm
 * @author 黄维
 * @Create In 2015年12月15日
 */
public class ZlshJydzzm extends DataEntity<ZlshJydzzm>{
	
	private static final long serialVersionUID = 1L;

	private String loanCode;// 借款编码
	private String rJyzmId;// 关联ID(经营证明)
	private String dictCustomerType;// 借款人类型(主借人/共借人)
	private Date jydzzmExpireDay;// 到期日期
	private Double jydzzmRentMonth;// 月租金
	private String dictCheckType;// 类型(初审，信审初审，复议初审)
	private String placeSituation; // 经营场所情况
	private String jydzzmExpireDays;// 到期日期 string
	private String jydzzmRentMonths;// 月租金 string
	private String dictSourceType;
	
	private Double monthPayMonth;// 月还款
	private Double businessArea;//营业面积	
	
	public String getPlaceSituation() {
		return placeSituation;
	}
	public void setPlaceSituation(String placeSituation) {
		this.placeSituation = placeSituation;
	}
	public String getJydzzmExpireDays() {
		return jydzzmExpireDays;
	}
	public void setJydzzmExpireDays(String jydzzmExpireDays) {
		this.jydzzmExpireDays = jydzzmExpireDays;
	}
	public String getJydzzmRentMonths() {
		return jydzzmRentMonths;
	}
	public void setJydzzmRentMonths(String jydzzmRentMonths) {
		this.jydzzmRentMonths = jydzzmRentMonths;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getrJyzmId() {
		return rJyzmId;
	}
	public void setrJyzmId(String rJyzmId) {
		this.rJyzmId = rJyzmId;
	}
	public String getDictCustomerType() {
		return dictCustomerType;
	}
	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}
	public Date getJydzzmExpireDay() {
		return jydzzmExpireDay;
	}
	public void setJydzzmExpireDay(Date jydzzmExpireDay) {
		this.jydzzmExpireDay = jydzzmExpireDay;
	}
	public Double getJydzzmRentMonth() {
		return jydzzmRentMonth;
	}
	public void setJydzzmRentMonth(Double jydzzmRentMonth) {
		this.jydzzmRentMonth = jydzzmRentMonth;
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
	public Double getMonthPayMonth() {
		return monthPayMonth;
	}
	public void setMonthPayMonth(Double monthPayMonth) {
		this.monthPayMonth = monthPayMonth;
	}
	public Double getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(Double businessArea) {
		this.businessArea = businessArea;
	}
}
package com.creditharmony.approve.document.entity;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 资料审核 车产证明
 * @Class Name ZlshCczm
 * @author 黄维
 * @Create In 2015年12月14日
 */
public class ZlshCczm extends  DataEntity<ZlshCczm>{

	private static final long serialVersionUID = 1L;

	private String loanCode;			// 借款编码
	private String rId;					// 关联ID
	private String dictCustomerType;	// 借款人类型(主借人/共借人)
	private Double carAssessValue;		// 车产估值
	private String pledgeFlag;			// 抵押标记
	private String dictCheckType;		// 类型(初审，信审初审，复议初审)
	private String vehicleBrand;		// 车产品牌
	private String dictSourceType;      

	public String getVehicleBrand() {
		return vehicleBrand;
	}
	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}
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
	public Double getCarAssessValue() {
		return carAssessValue;
	}
	public void setCarAssessValue(Double carAssessValue) {
		this.carAssessValue = carAssessValue;
	}
	public String getPledgeFlag() {
		return pledgeFlag;
	}
	public void setPledgeFlag(String pledgeFlag) {
		this.pledgeFlag = pledgeFlag;
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

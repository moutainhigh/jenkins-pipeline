package com.creditharmony.approve.newCar.entity;

import java.io.Serializable;
import java.util.List;

import com.creditharmony.approve.verify.entity.ex.LoanCustomerEx;

/**
 * 车借--汇诚--复审、终审查看页面实体类
 * 
 * @Class Name ApplyDetailInfokEx
 * @author 李高远
 * @Create In 2017年4月12日
 */
public class NewApplyDetailInfokEx implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -3803594581470157857L;
	private NewCarLoanInfo loanInfo; // 起始申请信息
	private LoanCustomerEx loanCustomerEx; // 客户个人信息
	private List<NewCustomerContactPerson> customerContactPersons; // 客户联系人信息
	private NewVehicleInfo vehicleInfo; // 车辆信息
	private List<NewCarLoanCoborrower> coborrowers; // 共借人
	private NewCarLoanInfo extendLoanInfo; // 展期的借款信息
	private NewCarLoanExtendExtraEx carLoanExtendExtraEx; // 展期额外所需的字段
	public NewCarLoanInfo getLoanInfo() {
		return loanInfo;
	}
	public void setLoanInfo(NewCarLoanInfo loanInfo) {
		this.loanInfo = loanInfo;
	}
	public List<NewCustomerContactPerson> getCustomerContactPersons() {
		return customerContactPersons;
	}
	public void setCustomerContactPersons(
			List<NewCustomerContactPerson> customerContactPersons) {
		this.customerContactPersons = customerContactPersons;
	}
	public NewVehicleInfo getVehicleInfo() {
		return vehicleInfo;
	}
	public void setVehicleInfo(NewVehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}
	public List<NewCarLoanCoborrower> getCoborrowers() {
		return coborrowers;
	}
	public void setCoborrowers(List<NewCarLoanCoborrower> coborrowers) {
		this.coborrowers = coborrowers;
	}
	public NewCarLoanInfo getExtendLoanInfo() {
		return extendLoanInfo;
	}
	public void setExtendLoanInfo(NewCarLoanInfo extendLoanInfo) {
		this.extendLoanInfo = extendLoanInfo;
	}
	public NewCarLoanExtendExtraEx getCarLoanExtendExtraEx() {
		return carLoanExtendExtraEx;
	}
	public void setCarLoanExtendExtraEx(NewCarLoanExtendExtraEx carLoanExtendExtraEx) {
		this.carLoanExtendExtraEx = carLoanExtendExtraEx;
	}
	public LoanCustomerEx getLoanCustomerEx() {
		return loanCustomerEx;
	}
	public void setLoanCustomerEx(LoanCustomerEx loanCustomerEx) {
		this.loanCustomerEx = loanCustomerEx;
	}
	
}

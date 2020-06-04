package com.creditharmony.approve.carloan.entity.ex;

import java.io.Serializable;
import java.util.List;

import com.creditharmony.approve.carloan.entity.CarLoanCoborrower;
import com.creditharmony.approve.carloan.entity.CustomerContactPerson;
import com.creditharmony.approve.carloan.entity.CarLoanInfo;
import com.creditharmony.approve.carloan.entity.VehicleInfo;
import com.creditharmony.approve.verify.entity.ex.LoanCustomerEx;

/**
 * 车借--汇诚--复审、终审查看页面实体类
 * 
 * @Class Name ApplyDetailInfokEx
 * @author 申诗阔
 * @Create In 2016年1月25日
 */
public class ApplyDetailInfokEx implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -3803594581470157857L;
	private CarLoanInfo loanInfo; // 起始申请信息
	private LoanCustomerEx loanCustomerEx; // 客户个人信息
	private List<CustomerContactPerson> customerContactPersons; // 客户联系人信息
	private VehicleInfo vehicleInfo; // 车辆信息
	private List<CarLoanCoborrower> coborrowers; // 共借人
	private CarLoanInfo extendLoanInfo; // 展期的借款信息
	private CarLoanExtendExtraEx carLoanExtendExtraEx; // 展期额外所需的字段
	
	public CarLoanInfo getLoanInfo() {
		return loanInfo;
	}

	public void setLoanInfo(CarLoanInfo loanInfo) {
		this.loanInfo = loanInfo;
	}

	public LoanCustomerEx getLoanCustomerEx() {
		return loanCustomerEx;
	}

	public void setLoanCustomerEx(LoanCustomerEx loanCustomerEx) {
		this.loanCustomerEx = loanCustomerEx;
	}

	public List<CustomerContactPerson> getCustomerContactPersons() {
		return customerContactPersons;
	}

	public void setCustomerContactPersons(
			List<CustomerContactPerson> customerContactPersons) {
		this.customerContactPersons = customerContactPersons;
	}

	public VehicleInfo getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(VehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public List<CarLoanCoborrower> getCoborrowers() {
		return coborrowers;
	}

	public void setCoborrowers(List<CarLoanCoborrower> coborrowers) {
		this.coborrowers = coborrowers;
	}

	public CarLoanInfo getExtendLoanInfo() {
		return extendLoanInfo;
	}

	public void setExtendLoanInfo(CarLoanInfo extendLoanInfo) {
		this.extendLoanInfo = extendLoanInfo;
	}

	public CarLoanExtendExtraEx getCarLoanExtendExtraEx() {
		return carLoanExtendExtraEx;
	}

	public void setCarLoanExtendExtraEx(CarLoanExtendExtraEx carLoanExtendExtraEx) {
		this.carLoanExtendExtraEx = carLoanExtendExtraEx;
	}
	
}

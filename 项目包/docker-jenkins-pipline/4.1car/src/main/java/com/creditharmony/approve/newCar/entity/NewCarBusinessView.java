package com.creditharmony.approve.newCar.entity;

import java.io.Serializable;
import java.util.List;

import com.creditharmony.bpm.frame.view.BaseBusinessView;

public class NewCarBusinessView extends BaseBusinessView implements
		Serializable {
	private static final long serialVersionUID = -5672321679467683678L;

	private String type; // 0 主借人、1 共借人、2 配偶

	private NewCarLoanInfo loanInfo; // 车借借款信息

	private NewLoanCustomerEx loanCustomerEx; // 客户信息

	private List<NewCoborrowerView> coborrowerList; // 共借人

	private List<NewCarAuditResult> carAuditResultList; // 审批结果列表
	
	private NewApplicationInterviewInfo applicationInterviewInfo ;

	private NewCarAuditResult carAuditResult;

	private NewVehicleInfo vehicleInfo; // 车辆信息

	private NewCarContract carContract; // 合同信息
	private String imageUrl;//影像路径
	private String contractVersion;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public NewCarLoanInfo getLoanInfo() {
		return loanInfo;
	}

	public void setLoanInfo(NewCarLoanInfo loanInfo) {
		this.loanInfo = loanInfo;
	}

	public NewLoanCustomerEx getLoanCustomerEx() {
		return loanCustomerEx;
	}

	public void setLoanCustomerEx(NewLoanCustomerEx loanCustomerEx) {
		this.loanCustomerEx = loanCustomerEx;
	}

	public List<NewCoborrowerView> getCoborrowerList() {
		return coborrowerList;
	}

	public void setCoborrowerList(List<NewCoborrowerView> coborrowerList) {
		this.coborrowerList = coborrowerList;
	}

	public List<NewCarAuditResult> getCarAuditResultList() {
		return carAuditResultList;
	}

	public void setCarAuditResultList(List<NewCarAuditResult> carAuditResultList) {
		this.carAuditResultList = carAuditResultList;
	}

	public NewCarAuditResult getCarAuditResult() {
		return carAuditResult;
	}

	public void setCarAuditResult(NewCarAuditResult carAuditResult) {
		this.carAuditResult = carAuditResult;
	}

	public NewVehicleInfo getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(NewVehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public NewCarContract getCarContract() {
		return carContract;
	}

	public void setCarContract(NewCarContract carContract) {
		this.carContract = carContract;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getContractVersion() {
		return contractVersion;
	}

	public void setContractVersion(String contractVersion) {
		this.contractVersion = contractVersion;
	}

	public NewApplicationInterviewInfo getApplicationInterviewInfo() {
		return applicationInterviewInfo;
	}

	public void setApplicationInterviewInfo(
			NewApplicationInterviewInfo applicationInterviewInfo) {
		this.applicationInterviewInfo = applicationInterviewInfo;
	}

}

package com.creditharmony.approve.workflow.view;

import java.io.Serializable;
import java.util.List;

import com.creditharmony.approve.carloan.entity.CarAuditResult;
import com.creditharmony.approve.carloan.entity.CarContract;
import com.creditharmony.approve.carloan.entity.CarLoanInfo;
import com.creditharmony.approve.carloan.entity.VehicleInfo;
import com.creditharmony.approve.verify.entity.ex.LoanCustomerEx;
import com.creditharmony.bpm.frame.view.BaseBusinessView;

public class CarLoanBusinessView extends BaseBusinessView implements
		Serializable {
	private static final long serialVersionUID = -5672321679467683678L;

	private String type; // 0 主借人、1 共借人、2 配偶

	private CarLoanInfo loanInfo; // 车借借款信息

	private LoanCustomerEx loanCustomerEx; // 客户信息

	private List<CoborrowerView> coborrowerList; // 共借人

	private List<CarAuditResult> carAuditResultList; // 审批结果列表

	private CarAuditResult carAuditResult;

	private VehicleInfo vehicleInfo; // 车辆信息

	private CarContract carContract; // 合同信息
	private String imageUrl;//影像路径
	private String contractVersion;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	public List<CoborrowerView> getCoborrowerList() {
		return coborrowerList;
	}

	public void setCoborrowerList(List<CoborrowerView> coborrowerList) {
		this.coborrowerList = coborrowerList;
	}

	public List<CarAuditResult> getCarAuditResultList() {
		return carAuditResultList;
	}

	public void setCarAuditResultList(List<CarAuditResult> carAuditResultList) {
		this.carAuditResultList = carAuditResultList;
	}

	public CarAuditResult getCarAuditResult() {
		return carAuditResult;
	}

	public void setCarAuditResult(CarAuditResult carAuditResult) {
		this.carAuditResult = carAuditResult;
	}

	public VehicleInfo getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(VehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public CarContract getCarContract() {
		return carContract;
	}

	public void setCarContract(CarContract carContract) {
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

}

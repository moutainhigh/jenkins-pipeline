package com.creditharmony.approve.newCar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class NewVehicleInfo implements Serializable{
	
	private static final long serialVersionUID = 3209183447141411577L;

	private String loanCode;

    private String id;

    private String plateNumbers;

    private BigDecimal suggestLoanAmount;

    private String appraiserName;

    private BigDecimal storeAssessAmount;

    private Date commercialMaturityDate;

    private BigDecimal similarMarketPrice;

    private Date factoryDate;

    private Date strongRiskMaturityDate;

    private Date annualCheckDate;

    private String frameNumber;

    private String vehiclePlantModel;

    private String vehicleBrandModel;

    private BigDecimal mileage;

    private Date firstRegistrationDate;

    private BigDecimal displacemint;

    private String carBodyColor;

    private String variator;

    private String engineNumber;

    private String changeNum;

    private String ownershipCertificateNumber;

    private String modifiedSituation;

    private String outerInspection;

    private String illegalAccident;

    private String vehicleAssessment;

    private String dictOperStatus;

    private String remark;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    private BigDecimal extensionAssessAmount;
    
    private BigDecimal extensionSuggestAmount;
    
    private String clivtaCompany;	//交强险保险公司
	private String clivtaNum;	//交强险单号
	private String commericialCompany; 	//商业险保险公司
	private String commericialNum;	//商业险单号
    
    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPlateNumbers() {
        return plateNumbers;
    }

    public void setPlateNumbers(String plateNumbers) {
        this.plateNumbers = plateNumbers == null ? null : plateNumbers.trim();
    }

    public BigDecimal getSuggestLoanAmount() {
        return suggestLoanAmount;
    }

    public void setSuggestLoanAmount(BigDecimal suggestLoanAmount) {
        this.suggestLoanAmount = suggestLoanAmount;
    }

    public String getAppraiserName() {
        return appraiserName;
    }

    public void setAppraiserName(String appraiserName) {
        this.appraiserName = appraiserName == null ? null : appraiserName.trim();
    }

    public BigDecimal getStoreAssessAmount() {
        return storeAssessAmount;
    }

    public void setStoreAssessAmount(BigDecimal storeAssessAmount) {
        this.storeAssessAmount = storeAssessAmount;
    }

    public Date getCommercialMaturityDate() {
        return commercialMaturityDate;
    }

    public void setCommercialMaturityDate(Date commercialMaturityDate) {
        this.commercialMaturityDate = commercialMaturityDate;
    }

    public BigDecimal getSimilarMarketPrice() {
        return similarMarketPrice;
    }

    public void setSimilarMarketPrice(BigDecimal similarMarketPrice) {
        this.similarMarketPrice = similarMarketPrice;
    }

    public Date getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(Date factoryDate) {
        this.factoryDate = factoryDate;
    }

    public Date getStrongRiskMaturityDate() {
        return strongRiskMaturityDate;
    }

    public void setStrongRiskMaturityDate(Date strongRiskMaturityDate) {
        this.strongRiskMaturityDate = strongRiskMaturityDate;
    }

    public Date getAnnualCheckDate() {
        return annualCheckDate;
    }

    public void setAnnualCheckDate(Date annualCheckDate) {
        this.annualCheckDate = annualCheckDate;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber == null ? null : frameNumber.trim();
    }

    public String getVehiclePlantModel() {
        return vehiclePlantModel;
    }

    public void setVehiclePlantModel(String vehiclePlantModel) {
        this.vehiclePlantModel = vehiclePlantModel == null ? null : vehiclePlantModel.trim();
    }

    public String getVehicleBrandModel() {
        return vehicleBrandModel;
    }

    public void setVehicleBrandModel(String vehicleBrandModel) {
        this.vehicleBrandModel = vehicleBrandModel == null ? null : vehicleBrandModel.trim();
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public Date getFirstRegistrationDate() {
        return firstRegistrationDate;
    }

    public void setFirstRegistrationDate(Date firstRegistrationDate) {
        this.firstRegistrationDate = firstRegistrationDate;
    }

    public BigDecimal getDisplacemint() {
        return displacemint;
    }

    public void setDisplacemint(BigDecimal displacemint) {
        this.displacemint = displacemint;
    }

    public String getCarBodyColor() {
        return carBodyColor;
    }

    public void setCarBodyColor(String carBodyColor) {
        this.carBodyColor = carBodyColor == null ? null : carBodyColor.trim();
    }

    public String getVariator() {
        return variator;
    }

    public void setVariator(String variator) {
        this.variator = variator == null ? null : variator.trim();
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber == null ? null : engineNumber.trim();
    }

    public String getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(String changeNum) {
        this.changeNum = changeNum == null ? null : changeNum.trim();
    }

    public String getOwnershipCertificateNumber() {
        return ownershipCertificateNumber;
    }

    public void setOwnershipCertificateNumber(String ownershipCertificateNumber) {
        this.ownershipCertificateNumber = ownershipCertificateNumber == null ? null : ownershipCertificateNumber.trim();
    }

    public String getModifiedSituation() {
        return modifiedSituation;
    }

    public void setModifiedSituation(String modifiedSituation) {
        this.modifiedSituation = modifiedSituation == null ? null : modifiedSituation.trim();
    }

    public String getOuterInspection() {
        return outerInspection;
    }

    public void setOuterInspection(String outerInspection) {
        this.outerInspection = outerInspection == null ? null : outerInspection.trim();
    }

    public String getIllegalAccident() {
        return illegalAccident;
    }

    public void setIllegalAccident(String illegalAccident) {
        this.illegalAccident = illegalAccident == null ? null : illegalAccident.trim();
    }

    public String getVehicleAssessment() {
        return vehicleAssessment;
    }

    public void setVehicleAssessment(String vehicleAssessment) {
        this.vehicleAssessment = vehicleAssessment == null ? null : vehicleAssessment.trim();
    }

    public String getDictOperStatus() {
        return dictOperStatus;
    }

    public void setDictOperStatus(String dictOperStatus) {
        this.dictOperStatus = dictOperStatus == null ? null : dictOperStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	public BigDecimal getExtensionAssessAmount() {
		return extensionAssessAmount;
	}

	public void setExtensionAssessAmount(BigDecimal extensionAssessAmount) {
		this.extensionAssessAmount = extensionAssessAmount;
	}

	public BigDecimal getExtensionSuggestAmount() {
		return extensionSuggestAmount;
	}

	public void setExtensionSuggestAmount(BigDecimal extensionSuggestAmount) {
		this.extensionSuggestAmount = extensionSuggestAmount;
	}

	public String getClivtaCompany() {
		return clivtaCompany;
	}

	public void setClivtaCompany(String clivtaCompany) {
		this.clivtaCompany = clivtaCompany;
	}

	public String getClivtaNum() {
		return clivtaNum;
	}

	public void setClivtaNum(String clivtaNum) {
		this.clivtaNum = clivtaNum;
	}

	public String getCommericialCompany() {
		return commericialCompany;
	}

	public void setCommericialCompany(String commericialCompany) {
		this.commericialCompany = commericialCompany;
	}

	public String getCommericialNum() {
		return commericialNum;
	}

	public void setCommericialNum(String commericialNum) {
		this.commericialNum = commericialNum;
	}
    
}
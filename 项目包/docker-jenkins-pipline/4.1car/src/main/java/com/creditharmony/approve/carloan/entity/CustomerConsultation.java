package com.creditharmony.approve.carloan.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerConsultation {
    private String id;

    private String customerCode;

    private String managerCode;

    private String consTeamManagerCode;

    private BigDecimal consLoanAmount;

    private String dictLoanUse;

    private String dictLoanType;

    private String consLoanRemarks;

    private Date consCommunicateDate;

    private Date planArrivalTime;

    private String consTelesalesFlag;

    private String consServiceUserCode;

    private String consTelesalesSource;

    private String createBy;

    private String modifyBy;

    private Date createTime;

    private Date modifyTime;

    private String consTelesalesOrgcode;

    private String dictOperStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode == null ? null : managerCode.trim();
    }

    public String getConsTeamManagerCode() {
        return consTeamManagerCode;
    }

    public void setConsTeamManagerCode(String consTeamManagerCode) {
        this.consTeamManagerCode = consTeamManagerCode == null ? null : consTeamManagerCode.trim();
    }

    public BigDecimal getConsLoanAmount() {
        return consLoanAmount;
    }

    public void setConsLoanAmount(BigDecimal consLoanAmount) {
        this.consLoanAmount = consLoanAmount;
    }

    public String getDictLoanUse() {
        return dictLoanUse;
    }

    public void setDictLoanUse(String dictLoanUse) {
        this.dictLoanUse = dictLoanUse == null ? null : dictLoanUse.trim();
    }

    public String getDictLoanType() {
        return dictLoanType;
    }

    public void setDictLoanType(String dictLoanType) {
        this.dictLoanType = dictLoanType == null ? null : dictLoanType.trim();
    }

    public String getConsLoanRemarks() {
        return consLoanRemarks;
    }

    public void setConsLoanRemarks(String consLoanRemarks) {
        this.consLoanRemarks = consLoanRemarks == null ? null : consLoanRemarks.trim();
    }

    public Date getConsCommunicateDate() {
        return consCommunicateDate;
    }

    public void setConsCommunicateDate(Date consCommunicateDate) {
        this.consCommunicateDate = consCommunicateDate;
    }

    public Date getPlanArrivalTime() {
        return planArrivalTime;
    }

    public void setPlanArrivalTime(Date planArrivalTime) {
        this.planArrivalTime = planArrivalTime;
    }

    public String getConsTelesalesFlag() {
        return consTelesalesFlag;
    }

    public void setConsTelesalesFlag(String consTelesalesFlag) {
        this.consTelesalesFlag = consTelesalesFlag == null ? null : consTelesalesFlag.trim();
    }

    public String getConsServiceUserCode() {
        return consServiceUserCode;
    }

    public void setConsServiceUserCode(String consServiceUserCode) {
        this.consServiceUserCode = consServiceUserCode == null ? null : consServiceUserCode.trim();
    }

    public String getConsTelesalesSource() {
        return consTelesalesSource;
    }

    public void setConsTelesalesSource(String consTelesalesSource) {
        this.consTelesalesSource = consTelesalesSource == null ? null : consTelesalesSource.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getConsTelesalesOrgcode() {
        return consTelesalesOrgcode;
    }

    public void setConsTelesalesOrgcode(String consTelesalesOrgcode) {
        this.consTelesalesOrgcode = consTelesalesOrgcode == null ? null : consTelesalesOrgcode.trim();
    }

    public String getDictOperStatus() {
        return dictOperStatus;
    }

    public void setDictOperStatus(String dictOperStatus) {
        this.dictOperStatus = dictOperStatus == null ? null : dictOperStatus.trim();
    }
}
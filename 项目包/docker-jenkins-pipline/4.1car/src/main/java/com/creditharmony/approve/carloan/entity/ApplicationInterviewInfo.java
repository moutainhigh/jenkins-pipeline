package com.creditharmony.approve.carloan.entity;

import java.util.Date;

public class ApplicationInterviewInfo {
    private String loanCode;

    private String customerRegisterAddress;

    private String dictIdIstrue;

    private String coboNowAddress;

    private String queryResult;

    private String queryResultPhone;

    private String customerJobReview;

    private String creditReport;

    private String createBy;

    private Date createTime;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getCustomerRegisterAddress() {
        return customerRegisterAddress;
    }

    public void setCustomerRegisterAddress(String customerRegisterAddress) {
        this.customerRegisterAddress = customerRegisterAddress == null ? null : customerRegisterAddress.trim();
    }

    public String getDictIdIstrue() {
        return dictIdIstrue;
    }

    public void setDictIdIstrue(String dictIdIstrue) {
        this.dictIdIstrue = dictIdIstrue == null ? null : dictIdIstrue.trim();
    }

    public String getCoboNowAddress() {
        return coboNowAddress;
    }

    public void setCoboNowAddress(String coboNowAddress) {
        this.coboNowAddress = coboNowAddress == null ? null : coboNowAddress.trim();
    }

    public String getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(String queryResult) {
        this.queryResult = queryResult == null ? null : queryResult.trim();
    }

    public String getQueryResultPhone() {
        return queryResultPhone;
    }

    public void setQueryResultPhone(String queryResultPhone) {
        this.queryResultPhone = queryResultPhone == null ? null : queryResultPhone.trim();
    }

    public String getCustomerJobReview() {
        return customerJobReview;
    }

    public void setCustomerJobReview(String customerJobReview) {
        this.customerJobReview = customerJobReview == null ? null : customerJobReview.trim();
    }

    public String getCreditReport() {
        return creditReport;
    }

    public void setCreditReport(String creditReport) {
        this.creditReport = creditReport == null ? null : creditReport.trim();
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
}
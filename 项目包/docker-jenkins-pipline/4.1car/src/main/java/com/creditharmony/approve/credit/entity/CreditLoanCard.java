package com.creditharmony.approve.credit.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CreditLoanCard extends DataEntity<CreditLoanCard>{

	private static final long serialVersionUID = 1L;

	private String loanCode;

    private String throughYear;

    private String orgName;

    private String operation;

    private Date addDay;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getThroughYear() {
        return throughYear;
    }

    public void setThroughYear(String throughYear) {
        this.throughYear = throughYear == null ? null : throughYear.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public Date getAddDay() {
        return addDay;
    }

    public void setAddDay(Date addDay) {
        this.addDay = addDay;
    }

}
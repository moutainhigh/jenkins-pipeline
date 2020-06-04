package com.creditharmony.approve.credit.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CreditGrade extends DataEntity<CreditGrade>{

	private static final long serialVersionUID = 1L;

	private String loanCode;

    private Date gradeTime;

    private String dictOrgName;

    private String dictRank;

    private Date addDay;

    private String operation;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public Date getGradeTime() {
        return gradeTime;
    }

    public void setGradeTime(Date gradeTime) {
        this.gradeTime = gradeTime;
    }

    public String getDictOrgName() {
        return dictOrgName;
    }

    public void setDictOrgName(String dictOrgName) {
        this.dictOrgName = dictOrgName == null ? null : dictOrgName.trim();
    }

    public String getDictRank() {
        return dictRank;
    }

    public void setDictRank(String dictRank) {
        this.dictRank = dictRank == null ? null : dictRank.trim();
    }

    public Date getAddDay() {
        return addDay;
    }

    public void setAddDay(Date addDay) {
        this.addDay = addDay;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }
    
}
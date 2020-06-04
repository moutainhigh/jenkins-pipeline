package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CreditPunish extends DataEntity<CreditPunish>{

	private static final long serialVersionUID = 1L;

	private String loanCode;

    private Date gradeTime;

    private String orgName;

    private String item;

    private BigDecimal amount;

    private Date addDay;

    private String operation;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

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

    public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

    public String getOrgName() {
		return orgName;
	}

	public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }
    
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
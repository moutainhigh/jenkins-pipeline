package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

public class CreditExternalGuaranteeRecord extends DataEntity<CreditExternalGuaranteeRecord>{

	private static final long serialVersionUID = 1L;

	private String loanCode;

    private String dictGuaranteeType;

    private String warrantee;

    private String dictCertType;

    private String customerCertNum;

    private String dictCurrency;

    private BigDecimal guaranteeAmount;

    private String dictGuaranteeForm;

    private String operation;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getDictGuaranteeType() {
        return dictGuaranteeType;
    }

    public void setDictGuaranteeType(String dictGuaranteeType) {
        this.dictGuaranteeType = dictGuaranteeType == null ? null : dictGuaranteeType.trim();
    }

    public String getWarrantee() {
        return warrantee;
    }

    public void setWarrantee(String warrantee) {
        this.warrantee = warrantee == null ? null : warrantee.trim();
    }

    public String getDictCertType() {
        return dictCertType;
    }

    public void setDictCertType(String dictCertType) {
        this.dictCertType = dictCertType == null ? null : dictCertType.trim();
    }

    public String getCustomerCertNum() {
        return customerCertNum;
    }

    public void setCustomerCertNum(String customerCertNum) {
        this.customerCertNum = customerCertNum == null ? null : customerCertNum.trim();
    }

    public String getDictCurrency() {
        return dictCurrency;
    }

    public void setDictCurrency(String dictCurrency) {
        this.dictCurrency = dictCurrency == null ? null : dictCurrency.trim();
    }

    public BigDecimal getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(BigDecimal guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public String getDictGuaranteeForm() {
        return dictGuaranteeForm;
    }

    public void setDictGuaranteeForm(String dictGuaranteeForm) {
        this.dictGuaranteeForm = dictGuaranteeForm == null ? null : dictGuaranteeForm.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }
    
}
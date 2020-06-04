package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 企业征信_未结清贷款
 * @Class Name CreditUnclearedLoan
 * @author zhanghu
 * @Create In 2015年12月31日
 */
public class CreditUnclearedLoan extends DataEntity<CreditUnclearedLoan> {

	private static final long serialVersionUID = 1L;

    private String loanCode;//借款编码

    private String loanOrg;//授信机构

    private String dictCurrency;//币种

    private BigDecimal iousAmount;//借据金额

    private BigDecimal iousBalance;//借据余额

    private Date lendingDay;//放款日期

    private Date actualDay;//到期日期

    private String dictLoanType;//贷款形式

    private String dictGuarantee;//担保

    private String dictExhibition;//展期

    private String operation;
    
    private String dictLevelClass;//五级分类

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

    public String getLoanOrg() {
        return loanOrg;
    }

    public void setLoanOrg(String loanOrg) {
        this.loanOrg = loanOrg == null ? null : loanOrg.trim();
    }

    public String getDictCurrency() {
        return dictCurrency;
    }

    public void setDictCurrency(String dictCurrency) {
        this.dictCurrency = dictCurrency == null ? null : dictCurrency.trim();
    }

    public BigDecimal getIousAmount() {
        return iousAmount;
    }

    public void setIousAmount(BigDecimal iousAmount) {
        this.iousAmount = iousAmount;
    }

    public BigDecimal getIousBalance() {
        return iousBalance;
    }

    public void setIousBalance(BigDecimal iousBalance) {
        this.iousBalance = iousBalance;
    }

    public Date getLendingDay() {
        return lendingDay;
    }

    public void setLendingDay(Date lendingDay) {
        this.lendingDay = lendingDay;
    }

    public Date getActualDay() {
        return actualDay;
    }

    public void setActualDay(Date actualDay) {
        this.actualDay = actualDay;
    }

    public String getDictLoanType() {
        return dictLoanType;
    }

    public void setDictLoanType(String dictLoanType) {
        this.dictLoanType = dictLoanType == null ? null : dictLoanType.trim();
    }

    public String getDictGuarantee() {
        return dictGuarantee;
    }

    public void setDictGuarantee(String dictGuarantee) {
        this.dictGuarantee = dictGuarantee == null ? null : dictGuarantee.trim();
    }

    public String getDictExhibition() {
        return dictExhibition;
    }

    public void setDictExhibition(String dictExhibition) {
        this.dictExhibition = dictExhibition == null ? null : dictExhibition.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
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

	public String getDictLevelClass() {
		return dictLevelClass;
	}

	public void setDictLevelClass(String dictLevelClass) {
		this.dictLevelClass = dictLevelClass;
	}
}
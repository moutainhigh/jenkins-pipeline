package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 企业征信_未结清贸易融资
 * @Class Name CreditUnclearedTradeFinancing
 * @author zhanghu
 * @Create In 2015年12月31日
 */
public class CreditUnclearedTradeFinancing extends DataEntity<CreditUnclearedTradeFinancing> {

	private static final long serialVersionUID = 1L;

    private String loanCode;//借款编码

    private String loanOrg;//授信机构

    private String dictCurrency;//币种

    private BigDecimal financingAmount;//融资金额

    private BigDecimal financingBalance;//融资余额

    private Date lendingDay;//放款日期

    private Date actualDay;//到期日期

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

    public BigDecimal getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(BigDecimal financingAmount) {
        this.financingAmount = financingAmount;
    }

    public BigDecimal getFinancingBalance() {
        return financingBalance;
    }

    public void setFinancingBalance(BigDecimal financingBalance) {
        this.financingBalance = financingBalance;
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
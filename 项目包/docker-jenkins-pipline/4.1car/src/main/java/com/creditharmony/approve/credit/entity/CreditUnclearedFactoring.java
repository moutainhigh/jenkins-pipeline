package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 企业征信_未结清保理
 * @Class Name CreditUnclearedFactoring
 * @author zhanghu
 * @Create In 2015年12月31日
 */
public class CreditUnclearedFactoring extends DataEntity<CreditUnclearedFactoring> {

	private static final long serialVersionUID = 1L;

    private String loanCode;//借款编码

    private String loanOrg;//授信机构

    private String dictCurrency;//币种

    private BigDecimal factoringAmount;//叙做金额

    private BigDecimal factoringBalance;//叙做余额

    private Date factoringDay;//叙做日期

    private String dictGuarantee;//担保

    private String makeAdvances;//垫款

    private String operation;
    
    private String dictLevelClass;//五级分类
    
    private Date lendingDay;//叙做日期//

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

    public BigDecimal getFactoringAmount() {
        return factoringAmount;
    }

    public void setFactoringAmount(BigDecimal factoringAmount) {
        this.factoringAmount = factoringAmount;
    }

    public BigDecimal getFactoringBalance() {
        return factoringBalance;
    }

    public void setFactoringBalance(BigDecimal factoringBalance) {
        this.factoringBalance = factoringBalance;
    }

    public Date getFactoringDay() {
        return factoringDay;
    }

    public void setFactoringDay(Date factoringDay) {
        this.factoringDay = factoringDay;
    }

    public String getDictGuarantee() {
        return dictGuarantee;
    }

    public void setDictGuarantee(String dictGuarantee) {
        this.dictGuarantee = dictGuarantee == null ? null : dictGuarantee.trim();
    }

    public String getMakeAdvances() {
        return makeAdvances;
    }

    public void setMakeAdvances(String makeAdvances) {
        this.makeAdvances = makeAdvances;
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

	public Date getLendingDay() {
		return lendingDay;
	}

	public void setLendingDay(Date lendingDay) {
		this.lendingDay = lendingDay;
	}
}
package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 企业征信_出资人信息
 * @Class Name CreditInvestorInfo
 * @author 张虎
 * @Create In 2015年12月31日
 */
public class CreditInvestorInfo extends DataEntity<CreditInvestorInfo> {

	private static final long serialVersionUID = 1L;

    private String loanCode;//借款编码

    private BigDecimal contributionAmount;//出资金额

    private String investorName;//出资方名称

    private String dictCertType;//证件类型

    private String customerCertNum;//证件号码

    private String dictCurrency;//币种

    private Integer contributionProportion;//出资占比

    private String operation;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public BigDecimal getContributionAmount() {
        return contributionAmount;
    }

    public void setContributionAmount(BigDecimal contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName == null ? null : investorName.trim();
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

    public Integer getContributionProportion() {
        return contributionProportion;
    }

    public void setContributionProportion(Integer contributionProportion) {
        this.contributionProportion = contributionProportion;
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
}
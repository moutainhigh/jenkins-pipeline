package com.creditharmony.approve.credit.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 企业征信_直接关联企业
 * @Class Name CreditAffiliatedEnterprise
 * @author zhanghu
 * @Create In 2015年12月31日
 */
public class CreditAffiliatedEnterprise extends DataEntity<CreditAffiliatedEnterprise> {

	private static final long serialVersionUID = 1L;

    private String loanCode;//借款编码

    private String name;//名称

    private String loanCardCode;//贷款卡编号

    private String dictRepeatRelation;//关系

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLoanCardCode() {
        return loanCardCode;
    }

    public void setLoanCardCode(String loanCardCode) {
        this.loanCardCode = loanCardCode == null ? null : loanCardCode.trim();
    }

    public String getDictRepeatRelation() {
        return dictRepeatRelation;
    }

    public void setDictRepeatRelation(String dictRepeatRelation) {
        this.dictRepeatRelation = dictRepeatRelation == null ? null : dictRepeatRelation.trim();
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
package com.creditharmony.approve.antifraud.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AntifraudRules {
    private String rulesCode;

    private BigDecimal id;

    private String rulesContent;

    private String rulesRemark;

    private String rulesSameColumn;

    private String rulesRelation;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    public String getRulesCode() {
        return rulesCode;
    }

    public void setRulesCode(String rulesCode) {
        this.rulesCode = rulesCode == null ? null : rulesCode.trim();
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRulesContent() {
        return rulesContent;
    }

    public void setRulesContent(String rulesContent) {
        this.rulesContent = rulesContent == null ? null : rulesContent.trim();
    }

    public String getRulesRemark() {
        return rulesRemark;
    }

    public void setRulesRemark(String rulesRemark) {
        this.rulesRemark = rulesRemark == null ? null : rulesRemark.trim();
    }

    public String getRulesSameColumn() {
        return rulesSameColumn;
    }

    public void setRulesSameColumn(String rulesSameColumn) {
        this.rulesSameColumn = rulesSameColumn == null ? null : rulesSameColumn.trim();
    }

    public String getRulesRelation() {
        return rulesRelation;
    }

    public void setRulesRelation(String rulesRelation) {
        this.rulesRelation = rulesRelation == null ? null : rulesRelation.trim();
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
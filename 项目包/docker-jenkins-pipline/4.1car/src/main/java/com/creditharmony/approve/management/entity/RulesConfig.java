package com.creditharmony.approve.management.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name RulesConfig
 * @author 侯志斌
 * @Create In 2016年01月19日
 * 规则实体类
 */
public class RulesConfig extends DataEntity<RulesConfig>{
	

	private static final long serialVersionUID = 1L;

	private String id;  //编号           

    private String rulesCode; //规则名称

    private String rulesContent; //规则内容

    private String rulesType;   //规则类型

    private String rulesRemark; //规则描述

    private String rulesSanmeColumn;  //规则字段

    private String rulesRelation;  //与本次借款人关系

    private Integer useState;  //启用标识

    private String rulesTables;  //规则对象

    private String whereFields;  //约束字段

    private String whereConstraints;//约束名称

    private String whereDatd;  //进件数据

    private String whereRelation;  //约束关系

    private String whereNumcheck;  //次数设置

    private String whereAll;     //约束总合

    private String whereNumlist; //约束总合

    private String tableRowid;   //表行序号

    private String whereRowid;   //条件行序号

    private String sqlAuto;      //自动生成sql语句

    private String sqlManual;    //手动编写sql语句

    private String createBy;     //创建人

    private Date createTime;     //创建时间

    private String modifyBy;     //修改人

    private Date modifyTime;     //修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRulesCode() {
        return rulesCode;
    }

    public void setRulesCode(String rulesCode) {
        this.rulesCode = rulesCode == null ? null : rulesCode.trim();
    }

    public String getRulesContent() {
        return rulesContent;
    }

    public void setRulesContent(String rulesContent) {
        this.rulesContent = rulesContent == null ? null : rulesContent.trim();
    }

    public String getRulesType() {
        return rulesType;
    }

    public void setRulesType(String rulesType) {
        this.rulesType = rulesType == null ? null : rulesType.trim();
    }

    public String getRulesRemark() {
        return rulesRemark;
    }

    public void setRulesRemark(String rulesRemark) {
        this.rulesRemark = rulesRemark == null ? null : rulesRemark.trim();
    }

    public String getRulesSanmeColumn() {
        return rulesSanmeColumn;
    }

    public void setRulesSanmeColumn(String rulesSanmeColumn) {
        this.rulesSanmeColumn = rulesSanmeColumn == null ? null : rulesSanmeColumn.trim();
    }

    public String getRulesRelation() {
        return rulesRelation;
    }

    public void setRulesRelation(String rulesRelation) {
        this.rulesRelation = rulesRelation == null ? null : rulesRelation.trim();
    }

    public Integer getUseState() {
        return useState;
    }

    public void setUseState(Integer useState) {
        this.useState = useState;
    }

    public String getRulesTables() {
        return rulesTables;
    }

    public void setRulesTables(String rulesTables) {
        this.rulesTables = rulesTables == null ? null : rulesTables.trim();
    }

    public String getWhereFields() {
        return whereFields;
    }

    public void setWhereFields(String whereFields) {
        this.whereFields = whereFields == null ? null : whereFields.trim();
    }

    public String getWhereConstraints() {
        return whereConstraints;
    }

    public void setWhereConstraints(String whereConstraints) {
        this.whereConstraints = whereConstraints == null ? null : whereConstraints.trim();
    }

    public String getWhereDatd() {
        return whereDatd;
    }

    public void setWhereDatd(String whereDatd) {
        this.whereDatd = whereDatd == null ? null : whereDatd.trim();
    }

    public String getWhereRelation() {
        return whereRelation;
    }

    public void setWhereRelation(String whereRelation) {
        this.whereRelation = whereRelation == null ? null : whereRelation.trim();
    }

    public String getWhereNumcheck() {
        return whereNumcheck;
    }

    public void setWhereNumcheck(String whereNumcheck) {
        this.whereNumcheck = whereNumcheck == null ? null : whereNumcheck.trim();
    }

    public String getWhereAll() {
        return whereAll;
    }

    public void setWhereAll(String whereAll) {
        this.whereAll = whereAll == null ? null : whereAll.trim();
    }

    public String getWhereNumlist() {
        return whereNumlist;
    }

    public void setWhereNumlist(String whereNumlist) {
        this.whereNumlist = whereNumlist == null ? null : whereNumlist.trim();
    }

    public String getTableRowid() {
        return tableRowid;
    }

    public void setTableRowid(String tableRowid) {
        this.tableRowid = tableRowid == null ? null : tableRowid.trim();
    }

    public String getWhereRowid() {
        return whereRowid;
    }

    public void setWhereRowid(String whereRowid) {
        this.whereRowid = whereRowid == null ? null : whereRowid.trim();
    }

    public String getSqlAuto() {
        return sqlAuto;
    }

    public void setSqlAuto(String sqlAuto) {
        this.sqlAuto = sqlAuto == null ? null : sqlAuto.trim();
    }

    public String getSqlManual() {
        return sqlManual;
    }

    public void setSqlManual(String sqlManual) {
        this.sqlManual = sqlManual == null ? null : sqlManual.trim();
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
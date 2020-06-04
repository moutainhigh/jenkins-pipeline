package com.creditharmony.approve.management.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name RulesData
 * @author 侯志斌
 * @Create In 2016年01月19日
 * 规则基础数据实体类
 */
public class RulesData extends DataEntity<RulesData>{
 
	private static final long serialVersionUID = 1L;

	private String id;              //编号

    private String fieldComment;    //字段说明

    private String fieldName;       //字段名称

    private String tableName;       //表名

    private String tableComment;    //表说明

    private String dbName;          //数据库名

    private String dbComment;       //数据库说明

    private String tableAlias;      //表别名

    private String remark;          //备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment == null ? null : fieldComment.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment == null ? null : tableComment.trim();
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName == null ? null : dbName.trim();
    }

    public String getDbComment() {
        return dbComment;
    }

    public void setDbComment(String dbComment) {
        this.dbComment = dbComment == null ? null : dbComment.trim();
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias == null ? null : tableAlias.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
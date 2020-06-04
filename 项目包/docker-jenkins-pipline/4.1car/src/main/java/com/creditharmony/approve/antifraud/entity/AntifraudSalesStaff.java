package com.creditharmony.approve.antifraud.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AntifraudSalesStaff {
    private BigDecimal id;

    private String salesCode;

    private String salesName;

    private String salesTel;

    private String isWork;

    private String salesRank;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getSalesCode() {
        return salesCode;
    }

    public void setSalesCode(String salesCode) {
        this.salesCode = salesCode == null ? null : salesCode.trim();
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName == null ? null : salesName.trim();
    }

    public String getSalesTel() {
        return salesTel;
    }

    public void setSalesTel(String salesTel) {
        this.salesTel = salesTel == null ? null : salesTel.trim();
    }

    public String getIsWork() {
        return isWork;
    }

    public void setIsWork(String isWork) {
        this.isWork = isWork == null ? null : isWork.trim();
    }

    public String getSalesRank() {
        return salesRank;
    }

    public void setSalesRank(String salesRank) {
        this.salesRank = salesRank == null ? null : salesRank.trim();
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
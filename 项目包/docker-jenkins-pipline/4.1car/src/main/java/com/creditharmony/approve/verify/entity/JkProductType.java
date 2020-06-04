package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

public class JkProductType {
    private BigDecimal id;

    private String classtype;

    private String productname;

    private String producttype;

    private String productstate;

    private Date starttimestamp;

    private Date closetimestamp;

    private String procTypeDesc;

    private String createBy;

    private Date createTime;

    private String lastmodifyBy;

    private Date modifyTime;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getClasstype() {
        return classtype;
    }

    public void setClasstype(String classtype) {
        this.classtype = classtype == null ? null : classtype.trim();
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype == null ? null : producttype.trim();
    }

    public String getProductstate() {
        return productstate;
    }

    public void setProductstate(String productstate) {
        this.productstate = productstate == null ? null : productstate.trim();
    }

    public Date getStarttimestamp() {
        return starttimestamp;
    }

    public void setStarttimestamp(Date starttimestamp) {
        this.starttimestamp = starttimestamp;
    }

    public Date getClosetimestamp() {
        return closetimestamp;
    }

    public void setClosetimestamp(Date closetimestamp) {
        this.closetimestamp = closetimestamp;
    }

    public String getProcTypeDesc() {
        return procTypeDesc;
    }

    public void setProcTypeDesc(String procTypeDesc) {
        this.procTypeDesc = procTypeDesc == null ? null : procTypeDesc.trim();
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

    public String getLastmodifyBy() {
        return lastmodifyBy;
    }

    public void setLastmodifyBy(String lastmodifyBy) {
        this.lastmodifyBy = lastmodifyBy == null ? null : lastmodifyBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
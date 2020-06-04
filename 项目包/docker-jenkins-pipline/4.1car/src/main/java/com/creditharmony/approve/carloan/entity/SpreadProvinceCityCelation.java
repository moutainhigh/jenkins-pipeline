package com.creditharmony.approve.carloan.entity;

import java.util.Date;

public class SpreadProvinceCityCelation {
    private String provinceCityId;

    private Short id;

    private Short rateId;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    public String getProvinceCityId() {
        return provinceCityId;
    }

    public void setProvinceCityId(String provinceCityId) {
        this.provinceCityId = provinceCityId == null ? null : provinceCityId.trim();
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getRateId() {
        return rateId;
    }

    public void setRateId(Short rateId) {
        this.rateId = rateId;
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
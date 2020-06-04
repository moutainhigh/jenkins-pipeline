package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 个人征信-保证人代偿信息表
 * @Class Name CreditPaybackInfo
 * @author 张虎
 * @Create In 2015年12月31日
 */
public class CreditPaybackInfo extends DataEntity<CreditPaybackInfo> {
	
	private static final long serialVersionUID = 1L;

    private String relationId;//

    private Date recentlyPaybackTime;//最近一次代偿时间

    private String paybackOrg;//代偿机构

    private BigDecimal totalPaybackAmount;//累计代偿金额

    private Date lastPaybackDate;//最后一次还款日期

    private BigDecimal residualAmount;//余额

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId == null ? null : relationId.trim();
    }

    public Date getRecentlyPaybackTime() {
        return recentlyPaybackTime;
    }

    public void setRecentlyPaybackTime(Date recentlyPaybackTime) {
        this.recentlyPaybackTime = recentlyPaybackTime;
    }

    public String getPaybackOrg() {
        return paybackOrg;
    }

    public void setPaybackOrg(String paybackOrg) {
        this.paybackOrg = paybackOrg == null ? null : paybackOrg.trim();
    }

    public BigDecimal getTotalPaybackAmount() {
        return totalPaybackAmount;
    }

    public void setTotalPaybackAmount(BigDecimal totalPaybackAmount) {
        this.totalPaybackAmount = totalPaybackAmount;
    }

    public Date getLastPaybackDate() {
        return lastPaybackDate;
    }

    public void setLastPaybackDate(Date lastPaybackDate) {
        this.lastPaybackDate = lastPaybackDate;
    }

    public BigDecimal getResidualAmount() {
        return residualAmount;
    }

    public void setResidualAmount(BigDecimal residualAmount) {
        this.residualAmount = residualAmount;
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
package com.creditharmony.approve.carloan.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class GrossSpread extends DataEntity<GrossSpread> {
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String rateId;

    private String dictProductType;

    private String dictDeadline;

    private BigDecimal grossRate;

    private String dictInitiate;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;
    
	private String rateType;//费率类型

    public String getRateId() {
		return rateId;
	}

	public void setRateId(String rateId) {
		this.rateId = rateId;
	}

	public String getDictProductType() {
        return dictProductType;
    }

    public void setDictProductType(String dictProductType) {
        this.dictProductType = dictProductType == null ? null : dictProductType.trim();
    }

    public String getDictDeadline() {
        return dictDeadline;
    }

    public void setDictDeadline(String dictDeadline) {
        this.dictDeadline = dictDeadline == null ? null : dictDeadline.trim();
    }

    public BigDecimal getGrossRate() {
        return grossRate;
    }

    public void setGrossRate(BigDecimal grossRate) {
        this.grossRate = grossRate;
    }

    public String getDictInitiate() {
        return dictInitiate;
    }

    public void setDictInitiate(String dictInitiate) {
        this.dictInitiate = dictInitiate == null ? null : dictInitiate.trim();
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

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
}
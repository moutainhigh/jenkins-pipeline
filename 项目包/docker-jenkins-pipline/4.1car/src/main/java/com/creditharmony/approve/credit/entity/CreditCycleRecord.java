package com.creditharmony.approve.credit.entity;

import com.creditharmony.core.persistence.DataEntity;

public class CreditCycleRecord extends DataEntity<CreditCycleRecord>{
	
	private static final long serialVersionUID = 1L;
	private String relationId;     //关联ID
    private String relationType;   //区分类型
    private Integer cycleNo;       //期数
    private String cycleValue;     //状态记录

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId == null ? null : relationId.trim();
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType == null ? null : relationType.trim();
    }

    public Integer getCycleNo() {
        return cycleNo;
    }

    public void setCycleNo(Integer cycleNo) {
        this.cycleNo = cycleNo;
    }

    public String getCycleValue() {
        return cycleValue;
    }

    public void setCycleValue(String cycleValue) {
        this.cycleValue = cycleValue == null ? null : cycleValue.trim();
    }
}
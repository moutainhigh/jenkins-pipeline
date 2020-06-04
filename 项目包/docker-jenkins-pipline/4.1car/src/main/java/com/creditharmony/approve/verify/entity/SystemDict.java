package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;

public class SystemDict {
    private BigDecimal id;

    private String dictId;

    private String dictValue;

    private String dictDescription;

    private String dictIndex;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    public String getDictDescription() {
        return dictDescription;
    }

    public void setDictDescription(String dictDescription) {
        this.dictDescription = dictDescription == null ? null : dictDescription.trim();
    }

    public String getDictIndex() {
        return dictIndex;
    }

    public void setDictIndex(String dictIndex) {
        this.dictIndex = dictIndex == null ? null : dictIndex.trim();
    }
}
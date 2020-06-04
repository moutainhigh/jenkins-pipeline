package com.creditharmony.approve.carloan.entity;

public class FirstServiceCharge {
    private String id;

    private String ninetyBelowRate;

    private String ninetyAboveRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNinetyBelowRate() {
        return ninetyBelowRate;
    }

    public void setNinetyBelowRate(String ninetyBelowRate) {
        this.ninetyBelowRate = ninetyBelowRate == null ? null : ninetyBelowRate.trim();
    }

    public String getNinetyAboveRate() {
        return ninetyAboveRate;
    }

    public void setNinetyAboveRate(String ninetyAboveRate) {
        this.ninetyAboveRate = ninetyAboveRate == null ? null : ninetyAboveRate.trim();
    }
}
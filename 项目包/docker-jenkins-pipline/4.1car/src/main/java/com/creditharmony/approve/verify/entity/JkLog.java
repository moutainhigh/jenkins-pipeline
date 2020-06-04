package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

public class JkLog {
    private BigDecimal id;

    private String logType;

    private String operatorName;

    private Date operatTime;

    private String operatInfo;

    private String loginName;

    private String loginIp;

    private String operatModular;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Date getOperatTime() {
        return operatTime;
    }

    public void setOperatTime(Date operatTime) {
        this.operatTime = operatTime;
    }

    public String getOperatInfo() {
        return operatInfo;
    }

    public void setOperatInfo(String operatInfo) {
        this.operatInfo = operatInfo == null ? null : operatInfo.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public String getOperatModular() {
        return operatModular;
    }

    public void setOperatModular(String operatModular) {
        this.operatModular = operatModular == null ? null : operatModular.trim();
    }
}
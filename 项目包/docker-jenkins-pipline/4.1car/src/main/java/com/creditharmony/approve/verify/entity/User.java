package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

public class User {
    private BigDecimal userCode;
    private String id;
    private String userName;
    private String loginName;
    private String password;
    private String serSex;
    private String empStatus;
    private BigDecimal userStatus;
    private String userMobilephone;
    private String userMail;
    private String createBy;
    private Date createTime;
    private String modifyBy;
    private Date modifyTime;

    public BigDecimal getUserCode() {
        return userCode;
    }

    public void setUserCode(BigDecimal userCode) {
        this.userCode = userCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSerSex() {
        return serSex;
    }

    public void setSerSex(String serSex) {
        this.serSex = serSex == null ? null : serSex.trim();
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus == null ? null : empStatus.trim();
    }

    public BigDecimal getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(BigDecimal userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserMobilephone() {
        return userMobilephone;
    }

    public void setUserMobilephone(String userMobilephone) {
        this.userMobilephone = userMobilephone == null ? null : userMobilephone.trim();
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail == null ? null : userMail.trim();
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
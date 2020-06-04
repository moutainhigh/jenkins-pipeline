package com.creditharmony.approve.credit.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CreditCpfDetailed extends DataEntity<CreditCpfDetailed>{
	
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String relationId;			//关联ID
	private String personAccount;		//个人账号
	private String unitName;			//单位名称
	private Date accountDay;			//开户日期
	private Date payDay;				//初缴年月
	private Date payToDay;				//缴至年月
	private Date payDayNear;			//最近一次缴纳日期
	private String unitRation;			//单位缴存比例
	private String personRation;		//个人缴存比例
	private String deposit;				//月缴存额
	private Date getinfoTime;			//信息获取时间


    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId == null ? null : relationId.trim();
    }

    public String getPersonAccount() {
        return personAccount;
    }

    public void setPersonAccount(String personAccount) {
        this.personAccount = personAccount == null ? null : personAccount.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public Date getAccountDay() {
        return accountDay;
    }

    public void setAccountDay(Date accountDay) {
        this.accountDay = accountDay;
    }

    public Date getPayDay() {
        return payDay;
    }

    public void setPayDay(Date payDay) {
        this.payDay = payDay;
    }

    public Date getPayToDay() {
        return payToDay;
    }

    public void setPayToDay(Date payToDay) {
        this.payToDay = payToDay;
    }

    public Date getPayDayNear() {
        return payDayNear;
    }

    public void setPayDayNear(Date payDayNear) {
        this.payDayNear = payDayNear;
    }

    public String getUnitRation() {
        return unitRation;
    }

    public void setUnitRation(String unitRation) {
        this.unitRation = unitRation == null ? null : unitRation.trim();
    }

    public String getPersonRation() {
        return personRation;
    }

    public void setPersonRation(String personRation) {
        this.personRation = personRation == null ? null : personRation.trim();
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit == null ? null : deposit.trim();
    }

    public Date getGetinfoTime() {
        return getinfoTime;
    }

    public void setGetinfoTime(Date getinfoTime) {
        this.getinfoTime = getinfoTime;
    }
    
}
package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CreditCardDetailedOne extends DataEntity<CreditCardDetailedOne>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String relationId;//关联ID

    private String cardType;//卡类型

    private String guaranteeType;//担保方式

    private String currency;//币种

    private Date accountDay;//开户日期

    private BigDecimal cerditLine;//信用额度

    private BigDecimal shareCreditLine;//共享授信额度

    private BigDecimal liabilitiesLine;//最大负债额

    private BigDecimal usedAmount;//透支余额/已使用额度

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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType == null ? null : guaranteeType.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Date getAccountDay()  {
    /*	Date tmpDate=CommonUtils.formatDate("yyyy-MM-dd",accountDay);
        return  tmpDate;*/
    	return accountDay;
    }

    public void setAccountDay(Date accountDay) {
        this.accountDay = accountDay;
    }

    public BigDecimal getCerditLine() {
        return cerditLine;
    }

    public void setCerditLine(BigDecimal cerditLine) {
        this.cerditLine = cerditLine;
    }

    public BigDecimal getShareCreditLine() {
        return shareCreditLine;
    }

    public void setShareCreditLine(BigDecimal shareCreditLine) {
        this.shareCreditLine = shareCreditLine;
    }

    public BigDecimal getLiabilitiesLine() {
        return liabilitiesLine;
    }

    public void setLiabilitiesLine(BigDecimal liabilitiesLine) {
        this.liabilitiesLine = liabilitiesLine;
    }

    public BigDecimal getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount = usedAmount;
    }
    
   
	/*private Date getDate(String date) throws ParseException {
    	date = date.replace("Z", " UTC");//注意是空格+UTC
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
    	Date d = format.parse(date );
    	return d;
	}*/
}
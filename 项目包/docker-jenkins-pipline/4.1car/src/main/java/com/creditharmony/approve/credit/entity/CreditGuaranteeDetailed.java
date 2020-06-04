package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CreditGuaranteeDetailed extends DataEntity<CreditGuaranteeDetailed>  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String relationId;//关联ID

    private BigDecimal otherGuaranteeAmount;//为他人贷款合同担保金额

    private BigDecimal realPrincipal;//被担保贷款实际本金余额

    private Date actualDay;//截至年月

    private String customerCertNum;//身份证号

    private String guaranteedName;//被担保人姓名

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

    public BigDecimal getOtherGuaranteeAmount() {
        return otherGuaranteeAmount;
    }

    public void setOtherGuaranteeAmount(BigDecimal otherGuaranteeAmount) {
        this.otherGuaranteeAmount = otherGuaranteeAmount;
    }

    public BigDecimal getRealPrincipal() {
        return realPrincipal;
    }

    public void setRealPrincipal(BigDecimal realPrincipal) {
        this.realPrincipal = realPrincipal;
    }

    public Date getActualDay() {
        return actualDay;
    }

    public void setActualDay(Date actualDay) {
        this.actualDay = actualDay;
    }

    public String getCustomerCertNum() {
        return customerCertNum;
    }

    public void setCustomerCertNum(String customerCertNum) {
        this.customerCertNum = customerCertNum == null ? null : customerCertNum.trim();
    }

    public String getGuaranteedName() {
        return guaranteedName;
    }

    public void setGuaranteedName(String guaranteedName) {
        this.guaranteedName = guaranteedName == null ? null : guaranteedName.trim();
    }
}
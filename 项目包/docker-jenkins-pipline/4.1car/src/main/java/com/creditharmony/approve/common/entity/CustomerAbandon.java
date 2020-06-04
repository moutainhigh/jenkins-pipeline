package com.creditharmony.approve.common.entity;

import com.creditharmony.core.persistence.DataEntity;

public class CustomerAbandon extends DataEntity<CustomerAbandon>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

    private String rStatusHisId;

    private String loanCode;

    private String abandonFirstCode;

    private String abandonSecondCode;

    private String abandonRemark;

    private String dictCheckType;
    
    private String abandonThirdCode;//三级拒借码

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

   

    public String getAbandonFirstCode() {
		return abandonFirstCode;
	}

	public void setAbandonFirstCode(String abandonFirstCode) {
		this.abandonFirstCode = abandonFirstCode;
	}

	public String getAbandonSecondCode() {
		return abandonSecondCode;
	}

	public void setAbandonSecondCode(String abandonSecondCode) {
		this.abandonSecondCode = abandonSecondCode;
	}

	public String getAbandonRemark() {
        return abandonRemark;
    }

    public void setAbandonRemark(String abandonRemark) {
        this.abandonRemark = abandonRemark == null ? null : abandonRemark.trim();
    }

    public String getDictCheckType() {
        return dictCheckType;
    }

    public void setDictCheckType(String dictCheckType) {
        this.dictCheckType = dictCheckType == null ? null : dictCheckType.trim();
    }

	public String getrStatusHisId() {
		return rStatusHisId;
	}

	public void setrStatusHisId(String rStatusHisId) {
		this.rStatusHisId = rStatusHisId;
	}

	public String getAbandonThirdCode() {
		return abandonThirdCode;
	}

	public void setAbandonThirdCode(String abandonThirdCode) {
		this.abandonThirdCode = abandonThirdCode;
	}
}
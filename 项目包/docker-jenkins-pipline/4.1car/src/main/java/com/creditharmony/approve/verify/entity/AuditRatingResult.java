package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;


public class AuditRatingResult extends DataEntity<AuditRatingResult>{
    
	private static final long serialVersionUID = 1L;

	private String ratingScoreDecision;
    private String loanCode;
    private BigDecimal ratingAdviseMonths;
    private BigDecimal ratingAdviseAmount;
    private Integer ratingScore;
    private Integer verifyRateScore;
    private Integer reconsiderRateScore;
    private String verifyRiskLevel;
    private String reconsiderRiskLevel;
    private String applyRiskLevel;

    public BigDecimal getRatingAdviseAmount() {
		return ratingAdviseAmount;
	}

	public void setRatingAdviseAmount(BigDecimal ratingAdviseAmount) {
		this.ratingAdviseAmount = ratingAdviseAmount;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }


    public BigDecimal getRatingAdviseMonths() {
        return ratingAdviseMonths;
    }

    public void setRatingAdviseMonths(BigDecimal ratingAdviseMonths) {
        this.ratingAdviseMonths = ratingAdviseMonths;
    }

    public String getRatingScoreDecision() {
        return ratingScoreDecision;
    }

    public void setRatingScoreDecision(String ratingScoreDecision) {
        this.ratingScoreDecision = ratingScoreDecision == null ? null : ratingScoreDecision.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

	public Integer getRatingScore() {
		return ratingScore;
	}

	public void setRatingScore(Integer ratingScore) {
		this.ratingScore = ratingScore;
	}

	public Integer getVerifyRateScore() {
		return verifyRateScore;
	}

	public void setVerifyRateScore(Integer verifyRateScore) {
		this.verifyRateScore = verifyRateScore;
	}

	public Integer getReconsiderRateScore() {
		return reconsiderRateScore;
	}

	public void setReconsiderRateScore(Integer reconsiderRateScore) {
		this.reconsiderRateScore = reconsiderRateScore;
	}

	public String getVerifyRiskLevel() {
		return verifyRiskLevel;
	}

	public void setVerifyRiskLevel(String verifyRiskLevel) {
		this.verifyRiskLevel = verifyRiskLevel;
	}

	public String getReconsiderRiskLevel() {
		return reconsiderRiskLevel;
	}

	public void setReconsiderRiskLevel(String reconsiderRiskLevel) {
		this.reconsiderRiskLevel = reconsiderRiskLevel;
	}

	public String getApplyRiskLevel() {
		return applyRiskLevel;
	}

	public void setApplyRiskLevel(String applyRiskLevel) {
		this.applyRiskLevel = applyRiskLevel;
	}
	
}
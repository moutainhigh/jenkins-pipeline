package com.creditharmony.approve.carloan.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 审核结果表
 * @Class Name AuditResult
 * @author 申诗阔
 * @Create In 2016年1月27日
 */
public class CarAuditResult extends DataEntity<CarAuditResult> {
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = -4315508552541448654L;

	private String id;

    private String loanCode;

    private String rStatusHisId;

    private String singleTastId;

    private String auditResult;

    private String dictProductType;
    
    private String productTypeName;

    private String auditResultName;
    
    private String dictAuditMonths;

    private Double finalEvaluatedPrice;

    private Double auditAmount;

    private Double grossRate;

    private Double firstServiceTariffing;

    private String auditCheckExamine;

    private String dictCheckType;

    private Date createTime;

    private String createBy;

    private Date modifyTime;

    private String modifyBy;

    private String returnType;

    private String auditJson;
    
    private String auditJsonName;

    private String whetherLocal;
    
    private String ownEstate;
    
    private String workNature;
    
    private String industry;
    
    private String creditStatus;
    
    private String whetherLocalName;
    
    private String ownEstateName;
    
    private String workNatureName;
    
    private String industryName;
    
    private String thisLoanStatus; // 审批结果表 对应 借款状态变更历史表 审批后的当时的状态，用于复审办理，历史审批回显时控制是否本地、信用状况等字段显示
    
    private Double outVisitDistance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getrStatusHisId() {
        return rStatusHisId;
    }

    public void setrStatusHisId(String rStatusHisId) {
        this.rStatusHisId = rStatusHisId == null ? null : rStatusHisId.trim();
    }

    public String getSingleTastId() {
        return singleTastId;
    }

    public void setSingleTastId(String singleTastId) {
        this.singleTastId = singleTastId == null ? null : singleTastId.trim();
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult == null ? null : auditResult.trim();
    }

    public String getDictProductType() {
        return dictProductType;
    }

    public void setDictProductType(String dictProductType) {
        this.dictProductType = dictProductType == null ? null : dictProductType.trim();
    }

    public String getDictAuditMonths() {
        return dictAuditMonths;
    }

    public void setDictAuditMonths(String dictAuditMonths) {
        this.dictAuditMonths = dictAuditMonths == null ? null : dictAuditMonths;
    }

    public Double getFinalEvaluatedPrice() {
        return finalEvaluatedPrice;
    }

    public void setFinalEvaluatedPrice(Double finalEvaluatedPrice) {
        this.finalEvaluatedPrice = finalEvaluatedPrice;
    }

    public Double getAuditAmount() {
        return auditAmount;
    }

    public void setAuditAmount(Double auditAmount) {
        this.auditAmount = auditAmount;
    }

    public Double getGrossRate() {
		return grossRate;
	}

	public void setGrossRate(Double grossRate) {
		this.grossRate = grossRate;
	}

	public Double getFirstServiceTariffing() {
		return firstServiceTariffing;
	}

	public void setFirstServiceTariffing(Double firstServiceTariffing) {
		this.firstServiceTariffing = firstServiceTariffing;
	}

	public String getAuditCheckExamine() {
        return auditCheckExamine;
    }

    public void setAuditCheckExamine(String auditCheckExamine) {
        this.auditCheckExamine = auditCheckExamine == null ? null : auditCheckExamine.trim();
    }

    public String getDictCheckType() {
        return dictCheckType;
    }

    public void setDictCheckType(String dictCheckType) {
        this.dictCheckType = dictCheckType == null ? null : dictCheckType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType == null ? null : returnType.trim();
    }

    public String getAuditJson() {
        return auditJson;
    }

    public void setAuditJson(String auditJson) {
        this.auditJson = auditJson == null ? null : auditJson.trim();
    }

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getAuditResultName() {
		return auditResultName;
	}

	public void setAuditResultName(String auditResultName) {
		this.auditResultName = auditResultName;
	}

	public String getAuditJsonName() {
		return auditJsonName;
	}

	public void setAuditJsonName(String auditJsonName) {
		this.auditJsonName = auditJsonName;
	}

	public String getWhetherLocal() {
		return whetherLocal;
	}

	public void setWhetherLocal(String whetherLocal) {
		this.whetherLocal = whetherLocal;
	}

	public String getOwnEstate() {
		return ownEstate;
	}

	public void setOwnEstate(String ownEstate) {
		this.ownEstate = ownEstate;
	}

	public String getWorkNature() {
		return workNature;
	}

	public void setWorkNature(String workNature) {
		this.workNature = workNature;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCreditStatus() {
		return creditStatus;
	}

	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}

	public String getWhetherLocalName() {
		return whetherLocalName;
	}

	public void setWhetherLocalName(String whetherLocalName) {
		this.whetherLocalName = whetherLocalName;
	}

	public String getOwnEstateName() {
		return ownEstateName;
	}

	public void setOwnEstateName(String ownEstateName) {
		this.ownEstateName = ownEstateName;
	}

	public String getWorkNatureName() {
		return workNatureName;
	}

	public void setWorkNatureName(String workNatureName) {
		this.workNatureName = workNatureName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getThisLoanStatus() {
		return thisLoanStatus;
	}

	public void setThisLoanStatus(String thisLoanStatus) {
		this.thisLoanStatus = thisLoanStatus;
	}

	public Double getOutVisitDistance() {
		return outVisitDistance;
	}

	public void setOutVisitDistance(Double outVisitDistance) {
		this.outVisitDistance = outVisitDistance;
	}
    
}
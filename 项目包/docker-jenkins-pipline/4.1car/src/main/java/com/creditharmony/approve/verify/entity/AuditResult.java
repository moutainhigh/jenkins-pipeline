package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 审批结果
 * @Class Name AuditResult
 * @author 赖敏
 * @Create In 2015年12月24日
 */
public class AuditResult extends DataEntity<AuditResult>{

	private static final long serialVersionUID = -3263112553656470415L;
	private String applyId;						// 流程ID
    private String loanCode;					// 借款编码
    private String rStatusHisId;				// 关联ID(变更历史表)
    private String singleTastId;				// 关联ID（分单表)
    private String auditResult;					// 审批结果
    private String productType;					// 审批产品code
    private Integer auditMonths;				// 审批分期
    private BigDecimal auditAmount;				// 批复金额
    private BigDecimal auditContractAmount;		// 合同额度
    private BigDecimal auditMonthRepayAmount;	// 月还款额
    private String auditRulesCode;				// 规则测试码
    private String auditCheckExamine;			// 审批意见
    private String auditEnsureName;				// 保证人姓名
    private String auditLegalMan;				// 法定代表人
    private String ensuremanBusinessPlace;		// 保证人实际经营场所
    private String businessCertNum;				//法人保证人身份证号
    private String dictCheckType;				// 审核类型(信审初审 等)
    private String attachmentPath;				// 附件路径
    private String stepName; 					// 步骤名
    private Double rate;						// 产品总费率
    private String businessProveId;				// 保证人公司id
    private String ensuremanBusinessProvince;   // 保证人实际经营场所-省 
    private String ensuremanBusinessCity;		// 保证人实际经营场所-市
    private String ensuremanBusinessArea;		// 保证人实际经营场所-区
    private String bestCoborrowerId;            //最优自然人保证人
    
    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId == null ? null : applyId.trim();
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getRStatusHisId() {
        return rStatusHisId;
    }

    public void setRStatusHisId(String rStatusHisId) {
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Integer getAuditMonths() {
        return auditMonths;
    }

    public void setAuditMonths(Integer auditMonths) {
        this.auditMonths = auditMonths;
    }

    public BigDecimal getAuditAmount() {
        return auditAmount;
    }

    public void setAuditAmount(BigDecimal auditAmount) {
        this.auditAmount = auditAmount;
    }

    public BigDecimal getAuditContractAmount() {
        return auditContractAmount;
    }

    public void setAuditContractAmount(BigDecimal auditContractAmount) {
        this.auditContractAmount = auditContractAmount;
    }

    public BigDecimal getAuditMonthRepayAmount() {
        return auditMonthRepayAmount;
    }

    public void setAuditMonthRepayAmount(BigDecimal auditMonthRepayAmount) {
        this.auditMonthRepayAmount = auditMonthRepayAmount;
    }

    public String getAuditRulesCode() {
        return auditRulesCode;
    }

    public void setAuditRulesCode(String auditRulesCode) {
        this.auditRulesCode = auditRulesCode == null ? null : auditRulesCode.trim();
    }

    public String getAuditCheckExamine() {
        return auditCheckExamine;
    }

    public void setAuditCheckExamine(String auditCheckExamine) {
        this.auditCheckExamine = auditCheckExamine == null ? null : auditCheckExamine.trim();
    }

    public String getAuditEnsureName() {
        return auditEnsureName;
    }

    public void setAuditEnsureName(String auditEnsureName) {
        this.auditEnsureName = auditEnsureName == null ? null : auditEnsureName.trim();
    }

    public String getAuditLegalMan() {
        return auditLegalMan;
    }

    public void setAuditLegalMan(String auditLegalMan) {
        this.auditLegalMan = auditLegalMan == null ? null : auditLegalMan.trim();
    }

    public String getEnsuremanBusinessPlace() {
        return ensuremanBusinessPlace;
    }

    public void setEnsuremanBusinessPlace(String ensuremanBusinessPlace) {
        this.ensuremanBusinessPlace = ensuremanBusinessPlace == null ? null : ensuremanBusinessPlace.trim();
    }

    public String getDictCheckType() {
        return dictCheckType;
    }

    public void setDictCheckType(String dictCheckType) {
        this.dictCheckType = dictCheckType == null ? null : dictCheckType.trim();
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath == null ? null : attachmentPath.trim();
    }

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getBusinessProveId() {
		return businessProveId;
	}

	public void setBusinessProveId(String businessProveId) {
		this.businessProveId = businessProveId;
	}

	public String getEnsuremanBusinessProvince() {
		return ensuremanBusinessProvince;
	}

	public void setEnsuremanBusinessProvince(String ensuremanBusinessProvince) {
		this.ensuremanBusinessProvince = ensuremanBusinessProvince;
	}

	public String getEnsuremanBusinessCity() {
		return ensuremanBusinessCity;
	}

	public void setEnsuremanBusinessCity(String ensuremanBusinessCity) {
		this.ensuremanBusinessCity = ensuremanBusinessCity;
	}

	public String getEnsuremanBusinessArea() {
		return ensuremanBusinessArea;
	}

	public void setEnsuremanBusinessArea(String ensuremanBusinessArea) {
		this.ensuremanBusinessArea = ensuremanBusinessArea;
	}

	public String getBestCoborrowerId() {
		return bestCoborrowerId;
	}

	public void setBestCoborrowerId(String bestCoborrowerId) {
		this.bestCoborrowerId = bestCoborrowerId;
	}

	public String getBusinessCertNum() {
		return businessCertNum;
	}

	public void setBusinessCertNum(String businessCertNum) {
		this.businessCertNum = businessCertNum;
	}
	
}
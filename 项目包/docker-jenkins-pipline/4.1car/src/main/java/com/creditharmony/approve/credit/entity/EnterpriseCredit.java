package com.creditharmony.approve.credit.entity;

import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 企业征信
 * @Class Name EnterpriseCredit
 * @author 张虎
 * @Create In 2015年12月31日
 */
public class EnterpriseCredit extends DataEntity<EnterpriseCredit> {
	
	private static final long serialVersionUID = 1L;
	
    private String loanCode;//借款编码

    private String creditVersion;//征信版本 0:详版 1:简版

    private String loanCardCode;//贷款卡编号

    private Date reportDate;//报告日期
    
    private List<CreditInvestorInfo> creditInvestorInfoList;//出资人信息List
    
    private List<CreditExecutiveInfo> creditExecutiveInfoList;//高管人员信息List
    
	private List<CreditAffiliatedEnterprise> creditAffiliatedEnterpriseList;// 企业征信_直接关联企业List
    
    private List<CreditExternalGuaranteeRecord> externalGuaranteeList;// 企业征信_对外担保记录List
    
    private List<CreditCivilJudgmentRecord> civilJudgmentList;// 民事判决记录
    
    private List<CreditLoanCard> creditLoanCardList;// 企业征信_贷款卡信息List
    
    private List<CreditGrade> creditGradeList;// 评级信息List
    
	private List<CreditPunish> creditPunishList;// 处罚信息List
	
	private CreditBasicInfo creditBasicInfo;//基础信息
	
	private List<CreditCurrentLiabilityInfo> creditCurrentLiabilityInfoList;//当前负债信息概要
	
	private List<CreditCurrentLiabilityDetail> creditCurrentLiabilityDetailList;//当前负债信息概要明细
	
	private List<CreditCreditClearedDetail> creditCreditClearedDetailList;//企业征信_已结清信贷明细
	
	private List<CreditExternalSecurityInfo> creditExternalSecurityInfoList;//企业征信_对外担保信息概要
	
	private List<CreditCreditClearedInfo> creditCreditClearedInfoList;//企业征信_已结清信贷信息
	
	private List<CreditPaidLoan> paidLoanList; //企业征信_已结清贷款明细
	
	private List<CreditLiabilityHis> creditLiabilityHisList;//企业征信_负债历史变化
	
	private List<CreditUnclearedLoan> creditUnclearedLoanList;//企业征信_未结清贷款
	
	private List<CreditUnclearedTradeFinancing> creditUnclearedTradeFinancingList;//企业征信_未结清贸易融资
	
	private List<CreditUnclearedFactoring> creditUnclearedFactoringList;// 企业征信_未结清保理
	
	private List<CreditUnclearedNotesDiscounted> creditUnclearedNotesDiscountedList;//企业征信_未结清票据贴现

	private List<CreditUnclearedBankAcceptance> creditUnclearedBankAcceptanceList;//企业征信_未结清银行承兑汇票

	private List<CreditUnclearedLetterCredit> creditUnclearedLetterCreditList;//企业征信_未结清信用证

	private List<CreditUnclearedLetterGuarantee> creditUnclearedLetterGuaranteeList;//企业征信_未结清保函
	
	private List<CreditUnclearedImproperLoan> creditUnclearedImproperLoanList;// 未结清业务:不良、关注类

	private String applyId;
	
	private String customerName;
	
    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getCreditVersion() {
        return creditVersion;
    }

    public void setCreditVersion(String creditVersion) {
        this.creditVersion = creditVersion == null ? null : creditVersion.trim();
    }

    public String getLoanCardCode() {
        return loanCardCode;
    }

    public void setLoanCardCode(String loanCardCode) {
        this.loanCardCode = loanCardCode == null ? null : loanCardCode.trim();
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
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

	public List<CreditInvestorInfo> getCreditInvestorInfoList() {
		return creditInvestorInfoList;
	}

	public void setCreditInvestorInfoList(
			List<CreditInvestorInfo> creditInvestorInfoList) {
		this.creditInvestorInfoList = creditInvestorInfoList;
	}

	public List<CreditPunish> getCreditPunishList() {
		return creditPunishList;
	}

	public void setCreditPunishList(List<CreditPunish> creditPunishList) {
		this.creditPunishList = creditPunishList;
	}
	
	public List<CreditExecutiveInfo> getCreditExecutiveInfoList() {
		return creditExecutiveInfoList;
	}

	public void setCreditExecutiveInfoList(
			List<CreditExecutiveInfo> creditExecutiveInfoList) {
		this.creditExecutiveInfoList = creditExecutiveInfoList;
	}

	public List<CreditExternalGuaranteeRecord> getExternalGuaranteeList() {
		return externalGuaranteeList;
	}

	public void setExternalGuaranteeList(
			List<CreditExternalGuaranteeRecord> externalGuaranteeList) {
		this.externalGuaranteeList = externalGuaranteeList;
	}

	public List<CreditCivilJudgmentRecord> getCivilJudgmentList() {
		return civilJudgmentList;
	}

	public void setCivilJudgmentList(
			List<CreditCivilJudgmentRecord> civilJudgmentList) {
		this.civilJudgmentList = civilJudgmentList;
	}

	public List<CreditLoanCard> getCreditLoanCardList() {
		return creditLoanCardList;
	}

	public void setCreditLoanCardList(List<CreditLoanCard> creditLoanCardList) {
		this.creditLoanCardList = creditLoanCardList;
	}

	public List<CreditGrade> getCreditGradeList() {
		return creditGradeList;
	}

	public void setCreditGradeList(List<CreditGrade> creditGradeList) {
		this.creditGradeList = creditGradeList;
	}

	public List<CreditAffiliatedEnterprise> getCreditAffiliatedEnterpriseList() {
		return creditAffiliatedEnterpriseList;
	}

	public void setCreditAffiliatedEnterpriseList(
			List<CreditAffiliatedEnterprise> creditAffiliatedEnterpriseList) {
		this.creditAffiliatedEnterpriseList = creditAffiliatedEnterpriseList;
	}

	public CreditBasicInfo getCreditBasicInfo() {
		return creditBasicInfo;
	}

	public void setCreditBasicInfo(CreditBasicInfo creditBasicInfo) {
		this.creditBasicInfo = creditBasicInfo;
	}

	public List<CreditCurrentLiabilityInfo> getCreditCurrentLiabilityInfoList() {
		return creditCurrentLiabilityInfoList;
	}

	public void setCreditCurrentLiabilityInfoList(
			List<CreditCurrentLiabilityInfo> creditCurrentLiabilityInfoList) {
		this.creditCurrentLiabilityInfoList = creditCurrentLiabilityInfoList;
	}

	public List<CreditCurrentLiabilityDetail> getCreditCurrentLiabilityDetailList() {
		return creditCurrentLiabilityDetailList;
	}

	public void setCreditCurrentLiabilityDetailList(
			List<CreditCurrentLiabilityDetail> creditCurrentLiabilityDetailList) {
		this.creditCurrentLiabilityDetailList = creditCurrentLiabilityDetailList;
	}

	public List<CreditExternalSecurityInfo> getCreditExternalSecurityInfoList() {
		return creditExternalSecurityInfoList;
	}

	public void setCreditExternalSecurityInfoList(
			List<CreditExternalSecurityInfo> creditExternalSecurityInfoList) {
		this.creditExternalSecurityInfoList = creditExternalSecurityInfoList;
	}

	public List<CreditCreditClearedInfo> getCreditCreditClearedInfoList() {
		return creditCreditClearedInfoList;
	}

	public void setCreditCreditClearedInfoList(
			List<CreditCreditClearedInfo> creditCreditClearedInfoList) {
		this.creditCreditClearedInfoList = creditCreditClearedInfoList;
	}

	public List<CreditCreditClearedDetail> getCreditCreditClearedDetailList() {
		return creditCreditClearedDetailList;
	}

	public void setCreditCreditClearedDetailList(
			List<CreditCreditClearedDetail> creditCreditClearedDetailList) {
		this.creditCreditClearedDetailList = creditCreditClearedDetailList;
	}

	public List<CreditPaidLoan> getPaidLoanList() {
		return paidLoanList;
	}

	public void setPaidLoanList(List<CreditPaidLoan> paidLoanList) {
		this.paidLoanList = paidLoanList;
	}	

	public List<CreditLiabilityHis> getCreditLiabilityHisList() {
		return creditLiabilityHisList;
	}

	public void setCreditLiabilityHisList(
			List<CreditLiabilityHis> creditLiabilityHisList) {
		this.creditLiabilityHisList = creditLiabilityHisList;
	}

	public List<CreditUnclearedLoan> getCreditUnclearedLoanList() {
		return creditUnclearedLoanList;
	}

	public void setCreditUnclearedLoanList(
			List<CreditUnclearedLoan> creditUnclearedLoanList) {
		this.creditUnclearedLoanList = creditUnclearedLoanList;
	}

	public List<CreditUnclearedTradeFinancing> getCreditUnclearedTradeFinancingList() {
		return creditUnclearedTradeFinancingList;
	}

	public void setCreditUnclearedTradeFinancingList(
			List<CreditUnclearedTradeFinancing> creditUnclearedTradeFinancingList) {
		this.creditUnclearedTradeFinancingList = creditUnclearedTradeFinancingList;
	}

	public List<CreditUnclearedFactoring> getCreditUnclearedFactoringList() {
		return creditUnclearedFactoringList;
	}

	public void setCreditUnclearedFactoringList(
			List<CreditUnclearedFactoring> creditUnclearedFactoringList) {
		this.creditUnclearedFactoringList = creditUnclearedFactoringList;
	}

	public List<CreditUnclearedNotesDiscounted> getCreditUnclearedNotesDiscountedList() {
		return creditUnclearedNotesDiscountedList;
	}

	public void setCreditUnclearedNotesDiscountedList(
			List<CreditUnclearedNotesDiscounted> creditUnclearedNotesDiscountedList) {
		this.creditUnclearedNotesDiscountedList = creditUnclearedNotesDiscountedList;
	}

	public List<CreditUnclearedBankAcceptance> getCreditUnclearedBankAcceptanceList() {
		return creditUnclearedBankAcceptanceList;
	}

	public void setCreditUnclearedBankAcceptanceList(
			List<CreditUnclearedBankAcceptance> creditUnclearedBankAcceptanceList) {
		this.creditUnclearedBankAcceptanceList = creditUnclearedBankAcceptanceList;
	}

	public List<CreditUnclearedLetterCredit> getCreditUnclearedLetterCreditList() {
		return creditUnclearedLetterCreditList;
	}

	public void setCreditUnclearedLetterCreditList(
			List<CreditUnclearedLetterCredit> creditUnclearedLetterCreditList) {
		this.creditUnclearedLetterCreditList = creditUnclearedLetterCreditList;
	}

	public List<CreditUnclearedLetterGuarantee> getCreditUnclearedLetterGuaranteeList() {
		return creditUnclearedLetterGuaranteeList;
	}

	public void setCreditUnclearedLetterGuaranteeList(
			List<CreditUnclearedLetterGuarantee> creditUnclearedLetterGuaranteeList) {
		this.creditUnclearedLetterGuaranteeList = creditUnclearedLetterGuaranteeList;
	}

	public List<CreditUnclearedImproperLoan> getCreditUnclearedImproperLoanList() {
		return creditUnclearedImproperLoanList;
	}

	public void setCreditUnclearedImproperLoanList(
			List<CreditUnclearedImproperLoan> creditUnclearedImproperLoanList) {
		this.creditUnclearedImproperLoanList = creditUnclearedImproperLoanList;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}	
	
}
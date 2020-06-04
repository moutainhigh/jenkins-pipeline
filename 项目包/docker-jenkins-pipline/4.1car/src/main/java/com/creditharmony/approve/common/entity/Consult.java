package com.creditharmony.approve.common.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 客户咨询信息
 * @Class Name Consult
 * @author zhangyongsheng
 * @Create In 2015年11月9日
 */
public class Consult extends DataEntity<Consult> {

	private static final long serialVersionUID = -4826593454690408310L;
	
	private CustomerBaseInfo customerBaseInfo;     //客户基本信息
	private ConsultRecord consultRecord;     //客户沟通日志表
	private String financingId;             //理财经理ID
	private String loanApplyMoney;          //借款金额
	private String dictLoanUse;             //借款用途
	private String dictLoanType;            //借款类型                           				
	private String loanTeamEmpcode;			//团队经理编号							
	private String loanTeamOrgId;			//团队组织机构ID							
	private String teleSalesOrgid;			//电销组织机构ID(电销用)							
	private String consPhoneSource;			//上级员工编码							
	private String loanIsPhone;				//是否电销(0:否；1:是)						
	private String consCustomerService;		//客服人员(电销用)
	private boolean longTerm;		        //长期(1:长期;0：非长期)	
	
	
	public CustomerBaseInfo getCustomerBaseInfo() {
		return customerBaseInfo;
	}
	public void setCustomerBaseInfo(CustomerBaseInfo customerBaseInfo) {
		this.customerBaseInfo = customerBaseInfo;
	}
	public ConsultRecord getConsultRecord() {
		return consultRecord;
	}
	public void setConsultRecord(ConsultRecord consultRecord) {
		this.consultRecord = consultRecord;
	}
	public String getFinancingId() {
		return financingId;
	}
	public void setFinancingId(String financingId) {
		this.financingId = financingId;
	}
	public String getLoanApplyMoney() {
		return loanApplyMoney;
	}
	public void setLoanApplyMoney(String loanApplyMoney) {
		this.loanApplyMoney = loanApplyMoney;
	}
	public String getDictLoanUse() {
		return dictLoanUse;
	}
	public void setDictLoanUse(String dictLoanUse) {
		this.dictLoanUse = dictLoanUse;
	}
	public String getDictLoanType() {
		return dictLoanType;
	}
	public void setDictLoanType(String dictLoanType) {
		this.dictLoanType = dictLoanType;
	}
	
	public String getLoanTeamEmpcode() {
		return loanTeamEmpcode;
	}
	public void setLoanTeamEmpcode(String loanTeamEmpcode) {
		this.loanTeamEmpcode = loanTeamEmpcode;
	}
	public String getLoanTeamOrgId() {
		return loanTeamOrgId;
	}
	public void setLoanTeamOrgId(String loanTeamOrgId) {
		this.loanTeamOrgId = loanTeamOrgId;
	}
	public String getTeleSalesOrgid() {
		return teleSalesOrgid;
	}
	public void setTeleSalesOrgid(String teleSalesOrgid) {
		this.teleSalesOrgid = teleSalesOrgid;
	}
	public String getConsPhoneSource() {
		return consPhoneSource;
	}
	public void setConsPhoneSource(String consPhoneSource) {
		this.consPhoneSource = consPhoneSource;
	}
	public String getLoanIsPhone() {
		return loanIsPhone;
	}
	public void setLoanIsPhone(String loanIsPhone) {
		this.loanIsPhone = loanIsPhone;
	}
	public String getConsCustomerService() {
		return consCustomerService;
	}
	public void setConsCustomerService(String consCustomerService) {
		this.consCustomerService = consCustomerService;
	}
	
	
	
	public boolean isLongTerm() {
		return longTerm;
	}
	public void setLongTerm(boolean longTerm) {
		this.longTerm = longTerm;
	}
	@Override
	public String toString() {
		return "Consult [customerCode=" +  ", financingId="
				+ financingId + ", loanApplyMoney=" + loanApplyMoney
				+ ", dictLoanUse=" + dictLoanUse + ", dictLoanType="
				
				+ ", loanTeamEmpcode=" + loanTeamEmpcode + ", loanTeamOrgId="
				+ loanTeamOrgId + ", teleSalesOrgid=" + teleSalesOrgid
				+ ", consPhoneSource=" + consPhoneSource + ", loanIsPhone="
				+ loanIsPhone + ", consCustomerService=" + consCustomerService
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result
				+ ((consPhoneSource == null) ? 0 : consPhoneSource.hashCode());
		
		result = prime * result
				+ ((dictLoanType == null) ? 0 : dictLoanType.hashCode());
		result = prime * result
				+ ((dictLoanUse == null) ? 0 : dictLoanUse.hashCode());
		result = prime * result
				+ ((financingId == null) ? 0 : financingId.hashCode());
		result = prime * result
				+ ((loanApplyMoney == null) ? 0 : loanApplyMoney.hashCode());
		result = prime * result
				+ ((loanIsPhone == null) ? 0 : loanIsPhone.hashCode());
		result = prime * result
				+ ((loanTeamEmpcode == null) ? 0 : loanTeamEmpcode.hashCode());
		result = prime * result
				+ ((loanTeamOrgId == null) ? 0 : loanTeamOrgId.hashCode());
		result = prime * result
				+ ((teleSalesOrgid == null) ? 0 : teleSalesOrgid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consult other = (Consult) obj;
		
		if (consCustomerService == null) {
			if (other.consCustomerService != null)
				return false;
		} else if (!consCustomerService.equals(other.consCustomerService))
			return false;
		
		if (consPhoneSource == null) {
			if (other.consPhoneSource != null)
				return false;
		} else if (!consPhoneSource.equals(other.consPhoneSource))
			return false;
		
		if (dictLoanType == null) {
			if (other.dictLoanType != null)
				return false;
		} else if (!dictLoanType.equals(other.dictLoanType))
			return false;
		if (dictLoanUse == null) {
			if (other.dictLoanUse != null)
				return false;
		} else if (!dictLoanUse.equals(other.dictLoanUse))
			return false;
		if (financingId == null) {
			if (other.financingId != null)
				return false;
		} else if (!financingId.equals(other.financingId))
			return false;
		if (loanApplyMoney == null) {
			if (other.loanApplyMoney != null)
				return false;
		} else if (!loanApplyMoney.equals(other.loanApplyMoney))
			return false;
		if (loanIsPhone == null) {
			if (other.loanIsPhone != null)
				return false;
		} else if (!loanIsPhone.equals(other.loanIsPhone))
			return false;
		if (loanTeamEmpcode == null) {
			if (other.loanTeamEmpcode != null)
				return false;
		} else if (!loanTeamEmpcode.equals(other.loanTeamEmpcode))
			return false;
		if (loanTeamOrgId == null) {
			if (other.loanTeamOrgId != null)
				return false;
		} else if (!loanTeamOrgId.equals(other.loanTeamOrgId))
			return false;
		if (teleSalesOrgid == null) {
			if (other.teleSalesOrgid != null)
				return false;
		} else if (!teleSalesOrgid.equals(other.teleSalesOrgid))
			return false;
		return true;
	}
	
	

}

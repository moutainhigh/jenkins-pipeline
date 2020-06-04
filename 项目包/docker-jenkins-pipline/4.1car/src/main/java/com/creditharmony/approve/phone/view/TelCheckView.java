package com.creditharmony.approve.phone.view;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.creditharmony.approve.common.entity.CreditReportRisk;
import com.creditharmony.approve.phone.entity.ex.TelCheckBorrowerInfoEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckBusiContractEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckCompanyEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckContactPersonEx;
import com.creditharmony.approve.verify.entity.ex.DictList;

/**
 * 电话照会页面View
 * 
 * @Class Name TeleCheckView
 * @author 王浩
 * @Create In 2015年12月4日
 */
public class TelCheckView implements Serializable {

	private static final long serialVersionUID = 1L;

	private String loanCode;
	
	private Map<String, Object> dictValues;
	
	private DictList dictList;	

	private List<TelCheckCompanyEx> companyList;

	private List<TelCheckContactPersonEx> contactWorkList;

	private List<TelCheckContactPersonEx> contactFamilyList;
	
	private List<TelCheckContactPersonEx> contactOtherList;

	private TelCheckBorrowerInfoEx borrowerInfo;

	private List<TelCheckBusiContractEx> busiContractList;

	private CreditReportRisk creditRisk;

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public Map<String, Object> getDictValues() {
		return dictValues;
	}

	public void setDictValues(Map<String, Object> dictValues) {
		this.dictValues = dictValues;
	}

	public List<TelCheckCompanyEx> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<TelCheckCompanyEx> companyList) {
		this.companyList = companyList;
	}

	public List<TelCheckContactPersonEx> getContactWorkList() {
		return contactWorkList;
	}

	public void setContactWorkList(List<TelCheckContactPersonEx> contactWorkList) {
		this.contactWorkList = contactWorkList;
	}

	public List<TelCheckContactPersonEx> getContactFamilyList() {
		return contactFamilyList;
	}

	public void setContactFamilyList(
			List<TelCheckContactPersonEx> contactFamilyList) {
		this.contactFamilyList = contactFamilyList;
	}

	public TelCheckBorrowerInfoEx getBorrowerInfo() {
		return borrowerInfo;
	}

	public void setBorrowerInfo(TelCheckBorrowerInfoEx borrowerInfo) {
		this.borrowerInfo = borrowerInfo;
	}

	public List<TelCheckBusiContractEx> getBusiContractList() {
		return busiContractList;
	}

	public void setBusiContractList(
			List<TelCheckBusiContractEx> busiContractList) {
		this.busiContractList = busiContractList;
	}

	public CreditReportRisk getCreditRisk() {
		return creditRisk;
	}

	public void setCreditRisk(CreditReportRisk creditRisk) {
		this.creditRisk = creditRisk;
	}

	public List<TelCheckContactPersonEx> getContactOtherList() {
		return contactOtherList;
	}

	public void setContactOtherList(List<TelCheckContactPersonEx> contactOtherList) {
		this.contactOtherList = contactOtherList;
	}

	public DictList getDictList() {
		return dictList;
	}

	public void setDictList(DictList dictList) {
		this.dictList = dictList;
	}	

}

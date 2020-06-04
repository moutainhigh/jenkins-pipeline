package com.creditharmony.approve.phone.entity.ex;

import java.util.List;

import com.creditharmony.approve.phone.entity.DhzhBrhs;
import com.creditharmony.approve.phone.entity.DhzhBrhsJkje;
import com.creditharmony.approve.phone.entity.DhzhDhgxbrshd;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;

/**
 * 本人核实及电话录音
 * 
 * @Class Name TelCheckBorrowerInfo
 * @author 王浩
 * @Create In 2015年12月1日
 */
public class TelCheckBorrowerInfoEx extends DhzhBrhs {

	private static final long serialVersionUID = 162878258348121845L;
	private List<DhzhBrhsJkje> otherLoanList;
	private List<TelCheckBorrowerNumEx> borrowerNumList;// 家庭固话
	private List<TelCheckBorrowerNumEx> phoneNumList;// 手机号
	private List<DhzhDhgxbrshd> brshdList;
	private List<DhzhDhlyxx> dhlyxx;
	public List<DhzhBrhsJkje> getOtherLoanList() {
		return otherLoanList;
	}
	public void setOtherLoanList(List<DhzhBrhsJkje> otherLoanList) {
		this.otherLoanList = otherLoanList;
	}
	public List<TelCheckBorrowerNumEx> getBorrowerNumList() {
		return borrowerNumList;
	}
	public void setBorrowerNumList(List<TelCheckBorrowerNumEx> borrowerNumList) {
		this.borrowerNumList = borrowerNumList;
	}

	public List<DhzhDhgxbrshd> getBrshdList() {
		return brshdList;
	}
	public void setBrshdList(List<DhzhDhgxbrshd> brshdList) {
		this.brshdList = brshdList;
	}
	public List<TelCheckBorrowerNumEx> getPhoneNumList() {
		return phoneNumList;
	}
	public void setPhoneNumList(List<TelCheckBorrowerNumEx> phoneNumList) {
		this.phoneNumList = phoneNumList;
	}
	public List<DhzhDhlyxx> getDhlyxx() {
		return dhlyxx;
	}
	public void setDhlyxx(List<DhzhDhlyxx> dhlyxx) {
		this.dhlyxx = dhlyxx;
	}
}

package com.creditharmony.approve.verify.view;

import java.io.Serializable;

public class DiskInfoView implements Serializable{
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 4564562237202873588L;
	private String loanIndex;
	private String verifyFilelevel;
	private String verifyIndex;
	private String reconsiderIndex;
	private String reconsiderFilelevel;
	public String getLoanIndex() {
		return loanIndex;
	}
	public void setLoanIndex(String loanIndex) {
		this.loanIndex = loanIndex;
	}
	public String getVerifyFilelevel() {
		return verifyFilelevel;
	}
	public void setVerifyFilelevel(String verifyFilelevel) {
		this.verifyFilelevel = verifyFilelevel;
	}
	public String getReconsiderFilelevel() {
		return reconsiderFilelevel;
	}
	public void setReconsiderFilelevel(String reconsiderFilelevel) {
		this.reconsiderFilelevel = reconsiderFilelevel;
	}
	public String getVerifyIndex() {
		return verifyIndex;
	}
	public void setVerifyIndex(String verifyIndex) {
		this.verifyIndex = verifyIndex;
	}
	public String getReconsiderIndex() {
		return reconsiderIndex;
	}
	public void setReconsiderIndex(String reconsiderIndex) {
		this.reconsiderIndex = reconsiderIndex;
	}
	
}

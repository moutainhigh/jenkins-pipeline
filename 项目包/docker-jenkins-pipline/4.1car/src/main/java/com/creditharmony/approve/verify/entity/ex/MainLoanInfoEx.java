package com.creditharmony.approve.verify.entity.ex;

import com.creditharmony.approve.verify.entity.LoanCustomer;

public class MainLoanInfoEx extends LoanCustomer{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mateName; // 姓名	
	private String mateCertNum; // 身份证号	
	private String dictRealyUse; // 借款用途

	public String getMateName() {
		return mateName;
	}

	public void setMateName(String mateName) {
		this.mateName = mateName;
	}

	public String getMateCertNum() {
		return mateCertNum;
	}

	public void setMateCertNum(String mateCertNum) {
		this.mateCertNum = mateCertNum;
	}

	public String getDictRealyUse() {
		return dictRealyUse;
	}

	public void setDictRealyUse(String dictRealyUse) {
		this.dictRealyUse = dictRealyUse;
	}
}

package com.creditharmony.approve.verify.entity.ex;

import com.creditharmony.approve.verify.entity.LoanCoborrower;
/**
 * 共借人信息实体
 * @Class Name JointlyLoanInfoEx
 * @author 刘燕军
 * @Create In 2015年12月5日
 */
public class JointlyLoanInfoEx extends LoanCoborrower{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String mateName; // 姓名
	
	private String mateCertNum; // 身份证号

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
}

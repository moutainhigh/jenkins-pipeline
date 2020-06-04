/**
 * @Probject Name: chp-loan
 * @Path: com.creditharmony.loan.common.utilsLoanStatus.java
 * @Create By 王彬彬
 * @Create In 2015年12月17日 下午2:31:17
 */
package com.creditharmony.approve.verify.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 累计折算房款期数
 * 借款期数	累计折算放款期数
 * @Class Name LoanNumberType
 * @author zhanghu
 * @Create In 2016年8月9日
 */
public enum LoanNumberType {
	
	Loan_Number_3("3", 3.0),
	Loan_Number_6("6", 6.0),
	Loan_Number_12("12", 12.0),
	Loan_Number_18("18", 15.0),
	Loan_Number_24("24", 18.0),
	Loan_Number_36("36", 21.6),
	Loan_Number_48("48", 24.0);
	
	private static Map<Double, LoanNumberType> nameMap = new HashMap<Double, LoanNumberType>(
			100);
	private static Map<String, LoanNumberType> codeMap = new HashMap<String, LoanNumberType>(
			100);

	static {
		LoanNumberType[] allValues = LoanNumberType.values();
		for (LoanNumberType obj : allValues) {
			nameMap.put(obj.getName(), obj);
			codeMap.put(obj.getCode(), obj);
		}
	}

	private Double name;
	private String code;

	private LoanNumberType(String code, Double name) {
		this.name = name;
		this.code = code;

	}

	public Double getName() {
		return name;
	}

	public void setName(Double name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static LoanNumberType parseByName(String name) {
		return nameMap.get(name);
	}

	public static LoanNumberType parseByCode(String code) {
		return codeMap.get(code);
	}

}

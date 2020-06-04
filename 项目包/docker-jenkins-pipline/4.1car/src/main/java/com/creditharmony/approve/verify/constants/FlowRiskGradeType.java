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
 * 风险等级流水等级分组 折算后系数（0.7-1.2）
 * @Class Name FlowRiskGradeType
 * @author zhanghu
 * @Create In 2016年8月9日
 */
public enum FlowRiskGradeType {
	
	Risk_F_Flow_E("风险_F(1-3)_流水_E", 0.7),
	Risk_F_Flow_D("风险_F(1-3)_流水_D", 0.7129),
	Risk_F_Flow_C("风险_F(1-3)_流水_C", 0.7268),
	Risk_F_Flow_B("风险_F(1-3)_流水_B", 0.741),
	Risk_F_Flow_A("风险_F(1-3)_流水_A", 0.7365),
	
	Risk_E_Flow_E("风险_E(1-3)_流水_E", 0.7256),
	Risk_E_Flow_D("风险_E(1-3)_流水_D", 0.7566),
	Risk_E_Flow_C("风险_E(1-3)_流水_C", 0.7557),
	Risk_E_Flow_B("风险_E(1-3)_流水_B", 0.7685),
	Risk_E_Flow_A("风险_E(1-3)_流水_A", 0.7378),
	
	Risk_D_Flow_E("风险_D(1-3)_流水_E", 0.7567),
	Risk_D_Flow_D("风险_D(1-3)_流水_D", 0.7814),
	Risk_D_Flow_C("风险_D(1-3)_流水_C", 0.8046),
	Risk_D_Flow_B("风险_D(1-3)_流水_B", 0.819),
	Risk_D_Flow_A("风险_D(1-3)_流水_A", 0.778),
	
	Risk_C_Flow_E("风险_C(1-3)_流水_E", 0.8302),
	Risk_C_Flow_D("风险_C(1-3)_流水_D", 0.8758),
	Risk_C_Flow_C("风险_C(1-3)_流水_C", 0.9023),
	Risk_C_Flow_B("风险_C(1-3)_流水_B", 0.8903),
	Risk_C_Flow_A("风险_C(1-3)_流水_A", 0.8385),
	
	Risk_B_Flow_E("风险_B(1-3)_流水_E", 0.9073),
	Risk_B_Flow_D("风险_B(1-3)_流水_D", 0.9456),
	Risk_B_Flow_C("风险_B(1-3)_流水_C", 1.0),
	Risk_B_Flow_B("风险_B(1-3)_流水_B", 1.0269),
	Risk_B_Flow_A("风险_B(1-3)_流水_A", 0.9073),
	
	Risk_A_Flow_E("风险_A(1-3)_流水_E", 0.9853),
	Risk_A_Flow_D("风险_A(1-3)_流水_D", 0.9758),
	Risk_A_Flow_C("风险_A(1-3)_流水_C", 1.1534),
	Risk_A_Flow_B("风险_A(1-3)_流水_B", 1.1022),
	Risk_A_Flow_A("风险_A(1-3)_流水_A", 1.2);
	
	private static Map<Double, FlowRiskGradeType> nameMap = new HashMap<Double, FlowRiskGradeType>(
			100);
	private static Map<String, FlowRiskGradeType> codeMap = new HashMap<String, FlowRiskGradeType>(
			100);

	static {
		FlowRiskGradeType[] allValues = FlowRiskGradeType.values();
		for (FlowRiskGradeType obj : allValues) {
			nameMap.put(obj.getName(), obj);
			codeMap.put(obj.getCode(), obj);
		}
	}

	private Double name;
	private String code;

	private FlowRiskGradeType(String code, Double name) {
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

	public static FlowRiskGradeType parseByName(String name) {
		return nameMap.get(name);
	}

	public static FlowRiskGradeType parseByCode(String code) {
		return codeMap.get(code);
	}

}

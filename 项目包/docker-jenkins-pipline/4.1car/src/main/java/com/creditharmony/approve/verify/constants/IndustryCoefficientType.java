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
 * 信合行业划分及系数（按信合申请表上行业顺序）
 * @Class Name LoanNumberType
 * @author zhanghu
 * @Create In 2016年8月9日
 */
public enum IndustryCoefficientType {
	
	Industry_Coefficient_1("农、林、牧、渔业", 1.12),
	Industry_Coefficient_2("制造业", 0.89),
	Industry_Coefficient_3("信息传输、软件和计算机", 1.20),
	Industry_Coefficient_4("批发和零售业", 1.01),
	Industry_Coefficient_5("交通运输、仓储和物流业", 0.92),
	
	Industry_Coefficient_6("住宿和餐饮业", 0.85),
	Industry_Coefficient_7("商务服务及居民服务业", 1.00),
	Industry_Coefficient_8("建筑业", 0.90),
	Industry_Coefficient_9("文化、体育和娱乐业", 0.90),
	Industry_Coefficient_10("采矿业", 0.69),
	Industry_Coefficient_11("政府或其他非盈利机构", 1.00),
	
	Industry_Coefficient_12("卫生和社会工作", 1.12),
	Industry_Coefficient_13("教育、科研和技术服务业", 0.92),
	Industry_Coefficient_14("房地产业", 0.84),
	Industry_Coefficient_15("供热、供电、供水等能源供应业", 0.95),
	Industry_Coefficient_16("金融业", 0.80),
	Industry_Coefficient_17("其他（请填写）", 0.94);
	
	private static Map<Double, IndustryCoefficientType> nameMap = new HashMap<Double, IndustryCoefficientType>(
			100);
	private static Map<String, IndustryCoefficientType> codeMap = new HashMap<String, IndustryCoefficientType>(
			100);

	static {
		IndustryCoefficientType[] allValues = IndustryCoefficientType.values();
		for (IndustryCoefficientType obj : allValues) {
			nameMap.put(obj.getName(), obj);
			codeMap.put(obj.getCode(), obj);
		}
	}

	private Double name;
	private String code;

	private IndustryCoefficientType(String code, Double name) {
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

	public static IndustryCoefficientType parseByName(String name) {
		return nameMap.get(name);
	}

	public static IndustryCoefficientType parseByCode(String code) {
		return codeMap.get(code);
	}

}

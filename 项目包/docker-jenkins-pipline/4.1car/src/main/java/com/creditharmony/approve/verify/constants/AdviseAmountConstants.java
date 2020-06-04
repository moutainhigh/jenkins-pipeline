package com.creditharmony.approve.verify.constants;

import java.math.BigDecimal;

/**
 * 额度卡
 * @Class Name AdviseAmountConstants
 * @author zhanghu
 * @Create In 2016年8月9日
 */
public class AdviseAmountConstants {

	// 流水分数断点1
	public static final BigDecimal FLOW_GRADE_SCORE_1 = new BigDecimal(3000);
	// 流水分数断点2
	public static final BigDecimal FLOW_GRADE_SCORE_2 = new BigDecimal(8000);
	// 流水分数断点3
	public static final BigDecimal FLOW_GRADE_SCORE_3 = new BigDecimal(20000);
	// 流水分数断点4
	public static final BigDecimal FLOW_GRADE_SCORE_4 = new BigDecimal(60000);
	
	// 流水_A
	public static final String FLOW_A = "A";
	// 流水_B
	public static final String FLOW_B = "B";
	// 流水_C
	public static final String FLOW_C = "C";
	// 流水_D
	public static final String FLOW_D = "D";
	// 流水_E
	public static final String FLOW_E = "E";
	
	// 风险_A
	public static final String Risk_A = "A";
	// 风险_B
	public static final String Risk_B = "B";
	// 风险_C
	public static final String Risk_C = "C";
	// 风险_D
	public static final String Risk_D = "D";
	// 风险_E
	public static final String Risk_E = "E";
	// 风险_F
	public static final String Risk_F = "F";
	
	
	// 行业-"其他不常见行业"
	public static final String INDUSTRY_H = "h";
	
	// 行业-dict type
	public static final String INDUSTRY_TYPE = "jk_industry_code";
	
	
}

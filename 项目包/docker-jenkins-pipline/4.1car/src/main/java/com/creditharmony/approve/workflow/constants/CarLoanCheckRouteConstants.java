package com.creditharmony.approve.workflow.constants;
/**
 * 
 * @Class Name CarLoanCheckRouteConstants  车借路由类
 * @author 李静辉
 * @Create In 2016年1月28日
 */
public class CarLoanCheckRouteConstants {
	
	/**
	 * 复审--通过到终审
	 */
	public static final String TO_FINAL_CHECK = "TO_FINAL_CHECK";
	/**
	 * 复审--附条件通过到终审
	 */
	public static final String TO_FINAL_CHECK_CONDICTION = "TO_FINAL_CHECK_CONDICTION";
	/**
	 * 复审--拒绝
	 */
	public static final String RECHECK_REFUSED = "RECHECK_REFUSED";
	/**
	 * 复审--客户放弃
	 */
	public static final String RECHECK_ABANDON = "RECHECK_ABANDON";
	/**
	 * 复审--退回到初审
	 */
	public static final String BACK_FIRST_CHECK = "BACK_FIRST_CHECK";
	/**
	 * 复审--退回到待签订合同
	 */
	public static final String BACK_CONTRACT_SIGN_UPLOAD = "BACK_CONTRACT_SIGN_UPLOAD";
	/**
	 * 复审--待定
	 */
	public static final String RECHECK_WAIT = "RECHECK_WAIT";
	/**
	 * 终审--通过到待审核费率
	 */
	public static final String FINAL_CHECK_PASS = "FINAL_CHECK_PASS";
	/**
	 * 终审--通过到合同审核
	 */
	public static final String TO_AUDIT_CONTRACT = "TO_AUDIT_CONTRACT";
	/**
	 * 终审--附条件通过到待审核费率
	 */
	public static final String FINAL_CHECK_PASS_CONDICTION = "FINAL_CHECK_PASS_CONDICTION";
	/**
	 * 终审--拒绝
	 */
	public static final String FINAL_CHECK_REFUSED = "FINAL_CHECK_REFUSED";
	/**
	 * 终审--客户放弃
	 */
	public static final String FINAL_CHECK_ABANDON = "FINAL_CHECK_ABANDON";
	/**
	 * 终审--退回到复审
	 */
	public static final String BACK_RECHECK = "BACK_RECHECK";
	/**
	 * 终审--待定
	 */
	public static final String FINAL_CHECK_WAIT = "FINAL_CHECK_WAIT";
}

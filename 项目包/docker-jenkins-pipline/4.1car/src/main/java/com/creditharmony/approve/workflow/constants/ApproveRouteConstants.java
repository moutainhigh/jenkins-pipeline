package com.creditharmony.approve.workflow.constants;

public interface ApproveRouteConstants {
	/**
	 * 门店提交至规则引擎
	 */
	public static final String STORE_TO_RULEENGIN = "TO_RULEENGIN";
	/**
	 * 初审提交到复审
	 */
	public static final String CHECK_TO_RECHECK = "TO_RECHECK";
	/**
	 * 初审提交到反欺诈
	 */
	public static final String CHECK_TO_ANTIFRAUD = "TO_ANTIFRAUD";
	/**
	 * 初审提交外访
	 */
	public static final String CHECK_TO_VISIT = "TO_VISIT";
	/**
	 * 初审回退门店
	 */
	public static final String CHECK_TO_STORE = "TO_STORE";
	/**
	 * 初审提交结束
	 */
	public static final String CHECK_TO_END = "TO_END";
	/**
	 * 初审客户放弃
	 */
	public static final String CHECK_TO_ABANDON = "TO_ABANDON";
	/**
	 * 初审拟拒借
	 */
	public static final String CHECK_PLAN_REFUSED = "PLAN_REFUSED";
	/**
	 * 复审提交到终审组(信审流程)
	 */
	public static final String RECHECK_TO_GROUPCHECK = "TO_GROUPCHECK";
	/**
	 * 复审提交到终审(复议流程)
	 */
	public static final String RECHECK_TO_FINALCHECK = "TO_FINALCHECK";
	/**
	 * 复审提交至审核利率
	 */
	public static final String RECHECK_TO_INTERESTRATE = "RECHECK_TO_INTERESTRATE";
	/**
	 * 复审提交结束
	 */
	public static final String RECHECK_TO_END = "TO_END";
	/**
	 * 终审组提交到终审
	 */
	public static final String GROUPCHECK_TO_FINALCHECK = "TO_FINALCHECK";
	/**
	 * 终审组提交至审核利率
	 */
	public static final String GROUPCHECK_TO_INTERESTRATE = "GROUPCHECK_TO_INTERESTRATE";
	/**
	 * 终审组提交结束
	 */
	public static final String GROUPCHECK_TO_END = "TO_END";
	/**
	 * 终审通过
	 */
	public static final String FINALCHECK_TO_PASS = "FINALCHECK_TO_PASS";
	/**
	 * 终审提交结束
	 */
	public static final String FINALCHECK_TO_END = "TO_END";
	/**
	 * 反欺诈回退初审
	 */
	public static final String ANTIFRAUD_BACK_CHECK = "ANTIFRAUD_BACK_CHECK";
	/**
	 * 反欺诈清白件
	 */
	public static final String ANTIFRAUD_TO_CHECK = "ANTIFRAUD_TO_CHECK";
	/**
	 * 反欺诈加黑加灰
	 */
	public static final String ANTIFRAUD_TO_END = "TO_END";
	/**
	 * 退回协商
	 */
	public static final String CHECK_TO_PREVIOUS = "TO_PREVIOUS";
}

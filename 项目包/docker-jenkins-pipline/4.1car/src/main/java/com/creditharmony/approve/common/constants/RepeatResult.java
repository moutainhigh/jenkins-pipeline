package com.creditharmony.approve.common.constants;
/**
 * 决策时，进行的查重对应的结果
 * @Class Name RepeatResult
 * @author 刘燕军
 * @Create In 2016年1月12日
 */
public interface RepeatResult {
	/**
	 * 风险客户，直接拒绝
	 */
	public static final String REFUSE="1";
	/**
	 * 没有异常，正常通过
	 */
	public static final String PASS="2";
	/**
	 * 内网需要重新去判定
	 */
	public static final String BACK_INSIDE="3";
	/**
	 * 外网需要重新去判定
	 */
	public static final String BACK_OUTSIDE="4";
	/**
	 * 电话照会需要重新去判定
	 */
	public static final String BACK_TEL_CHECK="5";
	/**
	 * 外访核查重新
	 */
	public static final String BACK_OUT_VISIT_CHECK="6";
}

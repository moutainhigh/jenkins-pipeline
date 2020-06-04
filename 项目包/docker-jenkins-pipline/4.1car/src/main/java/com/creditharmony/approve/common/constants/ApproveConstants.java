package com.creditharmony.approve.common.constants;

/**
 * 常量定义类
 * 
 * @Class Name ApproveConstants
 * @author 刘燕军
 * @Create In 2015年12月2日
 */
public interface ApproveConstants {
	/**
	 * 初审决策通过
	 */
	public static final String PASS = "0";
	/**
	 * 初审决策拒绝
	 */
	public static final String REFUSE = "1";
	/**
	 * 初审决策拟拒绝
	 */
	public static final String PSEUDO_REFUSE = "2";
	/**
	 * 车借--待定标识
	 */
	public static final String WAIT_FLAG = "1";
	/**
	 * 外呼固定前缀号码
	 */
	public static final String SYNROUTE_CALLNUMBER = "9";
	/**
	 * 复审中,通过时走向终审组还是审核利率的额度临界值
	 */
	public static final String RECHECK_MONEY = "0";
	/**
	 * 终审组审中,通过时走向终审还是审核利率的额度临界值
	 */
	public static final String GROUPCHECK_MONEY = "1";
	/**
	 * 在配置文件中的key
	 */
	public static final String UPLOAD_URL = "uploadUrl";
	/**
	 * 信雅达地址-前段 常量
	 */
	public static final String CHP3_0XYD = "chp3.0XYD";
	/**
	 * 信雅达地址-后段 常量
	 */
	public static final String CHP3_0XYD_INFO = "chp3.0XYD.info";
	
	/**
	 * 借款状态对应的字典type
	 */
	public static final String LOAN_APPLY_STATUS="jk_loan_apply_status";
	
	/**
	 * 复议待办状态信息
	 */
	public static final String RECONSIDER_STATUS="13,37,40,42,43,44,45,46,48,49";
	
	/**
	 * 信审待办状态信息
	 */
	public static final String VERIFY_STATUS="12,14,16,19,20,22,23,24,25,26,28,29,31";
	
	/**
	 * 未取单状态信息
	 */
	public static final String POOL_STATUS="14,24,25,28,31,37,45,46,49";
	
	/**
	 * 信审借款标志
	 */
	public static final String VERIFY_FLAG="HJ0001";
	
	
	/**
	 * 数据来自chp3.0还是2.0，3代表来自chp3.0
	 */
	public static final String DATA_SOURCE_CHP3_0 = "3";
	
	/**
	 * 信雅达插件索引系统标识，chp2.0信审 
	 */
	public static final String  XS_CHP2_0_SYS_FLAG = "4";
	
	/**
	 * 信雅达插件索引系统标识，chp2.0复议
	 */
	public static final String  FY_CHP2_0_SYS_FLAG = "5";
	/**
	 * 电话照会排除114
	 */
	public static final String TEL_SOURCE_FILTER_114_KEY = "02";
	/**
	 * 无效的ID
	 */
	public static final String INEFFICIENCY_ID = "999999999999";
	/**
	 * 家庭联系人
	 */
	public static final String RELATION_TYPE_FAMILY = "0";
	/**
	 * 工作证明人
	 */
	public static final String RELATION_TYPE_WORKMATE = "1";
	/**
	 * 其他联系人
	 */
	public static final String RELATION_TYPE_OTHER = "2";	
	/**
	 * 评分不低于拒借码
	 */
	public static final String SCORE_REFUSE_CODE = "N0903";	
	/**
	 * 汇金数据来源
	 */
	public static final String INFO_FROM_HJ = "1";
	/**
	 * 汇城数据来源
	 */
	public static final String INFO_FROM_HC = "0";
	/**
	 * 汇进同步股东信息股东关系字段（默认为本人）
	 */
	public static final String INFO_FROM_HJ_GDXX = "01";
	/**
	 * 资料审核变更记录默认为无
	 */
	public static final String ZLSH_CHANGE_FLAG = "1";	
	/**
	 * 汇进同步股东信息股东占股比例（如果汇金不填占股比例，默认为100%）
	 */
	public static final String INFO_FROM_HJ_RATIO = "100";
	
}

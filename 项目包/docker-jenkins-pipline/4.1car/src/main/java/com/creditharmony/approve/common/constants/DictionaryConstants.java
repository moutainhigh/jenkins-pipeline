package com.creditharmony.approve.common.constants;
/**
 * 字典关键字常量
 * @Class Name DictionaryKeyConstants
 * @author xiaoniu.hu
 * @Create In 2016年2月3日
 */
public class DictionaryConstants {
	
	/**
	 * 性别
	 */
	public static final String DICT_GENDER = "jk_sex";
	
	/**
	 * 币种
	 */
	public static final String CREDIT_CARDINFO_CURRENCY = "jk_credit_currency";
	
	/**
	 * 币种
	 */
	public static final String ENTERPRISE_CREDIT_CURRENCY = "jk_enterprise_currency";
	
	/**
	 * 信用账户状态
	 */
	public static final String CREDIT_CARDINFO_ACCOUNTSTATUS = "jk_credit_cardinfo_accountstatus";
	
	/**
	 * 贷款账户状态
	 */
	public static final String CREDIT_LOANINFO_ACCOUNTSTATUS = "jk_credit_loaninfo_accountstatus";
	
	/**
	 * 贷款类型
	 */
	public static final String ENTERPRISE_LOAN_TYPE = "jk_enterprise_loan_type";
	
	/**
	 * 五级分类
	 */
	public static final String LOAN_LEVEL_CLASS = "jk_enterprise_level_class";
	
	/**
	 * 已付清五级分类
	 */
	public static final String LOAN_LEVEL = "jk_enterprise_level";
	
	/**
	 * 征信查询原因
	 */
	public static final String CREDIT_QUERY_TYPE = "jk_credit_queryrecord_querytype";
	
	/**
	 * 征信查询原因
	 */
	public static final String CREDIT_ISOVERDUE = "jk_creait_isoverdue";
	
	/**
	 * 是否有担保、展期、垫款
	 */
	public static final String CREDIT_IS_GUARANTEE = "jk_enterprise_guarantee";
	
	/**
	 * 外部担保类型
	 */
	public static final String EX_GUARANTEE_TYPE = "jk_enterprise_guarantee_type";
	
	/**
	 * 贷款种类
	 */
	public static final String CREDIT_LOAN_TYPE = "jk_credit_loan_type_flag";
	
	/**
	 * 征信报告种类：简版、祥版、企业
	 */
	public static final String CREDIT_REPORT_TYPE = "jk_enterprise_risk_type";
	
	/**
	 * 证件类型
	 */
	public static final String CART_TYPE = "jk_enterprise_cart_type";
	
	/**
	 * 职务
	 */
	public static final String POSITION_TYPE = "jk_enterprise_comp_post";
	
	/**
	 * 对外担保证件类型
	 */
	public static final String GURANTEE_CARD_TYPE = "jk_enterprise_guarantee_card_type";
	
	/**
	 * 担保类型
	 */
	public static final String GURANTEE_FORM = "jk_enterprise_guarantee_form";
	
	/**
	 * 结案方式
	 */
	public static final String CLOSED_MANNER = "jk_enterprise_closed_manner";

	/**
	 * 原告或者被告
	 */
	public static final String LAWSUIT_POSITION = "jk_enterprise_lawsuit_position";
	
	/**
	 * 评级机构
	 */
	public static final String CREDIT_ORG_NAME = "jk_enterprise_org_name";

	/**
	 * 评级级别
	 */
	public static final String CREDIT_RANK = "jk_enterprise_rank";

	/**
	 * 
	 */
	public static final String ENTERPRISE_ITEM = "jk_enterprise_item";

	/**
	 * 关联企业关系
	 */
	public static final String CONNECT_RELATION = "jk_enterprise_repeat_relation";

	/**
	 * 贷款卡状态
	 */
	public static final String ENTERPRISE_CARED_STATUS = "jk_enterprise_card_status";
	
	/**
	 * 黑/灰名单黑名单
	 * 黑名单 0
	 * 灰名单 1
	 * 清白件 2
	 * 退回 3
	 * 反欺诈未处理 4
	 */
	public static final String BLACK_GREY_LIST_TYPE = "jk_black_grey_list";
	
	/**
	 * 其他联系人和本人关系
	 * "2";"同事"
	 * "1";"朋友"
	 * "0";"亲属"
	 */
	public static final String LOAN_OTHER_RELATION_TYPE = "jk_loan_other_relation";
	
	/**
	 * 工薪类外访家庭
	 */
	public static final String APPROVE_VISIT_WORK_FAMILY = "jk_approve_visit_Check_0201";
	
	/**
	 * 工薪类外访工作单位
	 */
	public static final String APPROVE_VISIT_WORK_UNIT = "jk_approve_visit_Check_0202";
	
	/**
	 * 经营类外访家庭
	 */
	public static final String APPROVE_VISIT_BUSINESS_FAMILY = "jk_approve_visit_Check_0101";
	
	/**
	 * 经营类外访企业
	 */
	public static final String APPROVE_VISIT_BUSINESS_UNIT = "jk_approve_visit_Check_0102";
	
	/**
	 * 客户类型
	 */
	public static final String CUSTOMER_TYPE = "jk_customer_diff";

	/************** 电话照会字典type整理--start *******************
	/**
	 * 行业代码
	 */
	public static final String INDUSTRY_CODE = "jk_industry_code";
	
	/**
	 * 职业代码
	 */
	public static final String OCCUPATIONAL_CODES = "jk_occupational_codes";
	
	/**
	 * 电话录音来源
	 */
	public static final String TEL_REC_SRC = "jk_tel_rec_src";
	
	/**
	 * 关系类型，家庭联系人/其他联系人/工作证明人
	 */
	public static final String RELATION_TYPE = "jk_relation_type";
	
	/**
	 * 单位名称来源
	 */
	public static final String WORK_UNIT_NAME_SRC = "jk_work_unit_name_src";
	
	/**
	 * 工作证明人与借款人关系
	 */
	public static final String LOAN_WORKMATE_RELATION = "jk_loan_workmate_relation";	
	
	/**
	 * 电话类型
	 */
	public static final String PHONE_TYPE = "phone_type";
	
	 /**
	  * 征信报告版本  字典中的type
	  */
	 public static final String CREDIT_VERSION = "jk_approve_credit_risk";
	 
	 /**
	  * 主借人、共借人  字典中的type
	  */
	 public static final String LOAN_MAN = "jk_loan_man_flag";
	 
	 /**
	  * 征信报告个人  字典中的type
	  */
	 public static final String PERSONAL = "jk_personal_risk_0";
	 
	 /**
	  * 征信报告企业  字典中的type
	  */
	 public static final String ENTERPRISE = "jk_enterprise_risk";
	 
	 /**
	  * 专网查询类型  字典中的type
	  */
	 public static final String PRIVATE_TYPE = "jk_network_type";
	 
	 /**
	  * 电话来源  字典中的type
	  */
	 public static final String TEL_SOURCE = "jk_tel_src";
	 
	 /**
	  * 婚姻状况  字典中的type
	  */
	 public static final String MARRIAGE = "jk_marriage";
	 
	 /**
	  * 借款用途  字典中的type
	  */
	 public static final String LOAN_USE = "jk_loan_use";
	 
	 /**
	  * 股东情况  字典中的type
	  */
	 public static final String SHAREHOLDER_RE = "jk_shareholder_re";
	 
	 /**
	  * 付款方式  字典中的type
	  */
	 public static final String SETTLEMENT_TYPE = "jk_settlement_type";
	 
	 /**
	  * 合同类型  字典中的type
	  */
	 public static final String UPDOWNCONTRACT = "jk_updowncontract_type";
	 
	 
	 /**
	  * 流水类型  字典中的type
	  */
	 public static final String SALARYTRACE = "jk_salary_trace_flag";
	 
	 /**
	  * 抵押标志  字典中的type
	  */
	 public static final String PLEDGE = "jk_pledge_flag";
	 
	 /**
	  * 规划用途  字典中的type
	  */
	 public static final String HOUSE = "jk_design_use";
	 
	 /**
	  * 产权人  字典中的type
	  */
	 public static final String PROPERTY = "jk_property_owner";
	 
	 /**
	  * 字典-回退原因
	  */
	 public static final String BACKLOAN_REASON = "jk_backloan_reason";
	 
	 /**
	  * 商路通电话拨打后缀，在字典中的type
	  */
	 public static final String SYNROUTE_NUMBER = "jk_synroute_number";  
	 
	 /**
	  * 商路通电话拨打后缀，在字典中的type
	  */
	 public static final String PHONE_STATUE = "jk_approve_phone_statue";  
	 
	 /**
	  * 证件类型 在字典中的type
	  */
	 public static  final String CERTIFICATE_TYPE = "jk_certificate_type";
	 
	 /**
	 * 学历
	 */
	public static  final String DEGREE = "jk_degree";
	
	/**
	 * 住房性质
	 */
	public static  final String HOUSE_NATURE = "jk_house_nature";
	
	/**
	 * 家人与本人关系
	 */
	public static  final String LOAN_FAMILY_RELATION = "jk_loan_family_relation";
	
	/**
	 * 复议原因
	 */
	public static final String RECONSIDE_REASON = "jk_reconside_reason";
}

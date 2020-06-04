package com.creditharmony.approve.credit.constants;

/**
 * 征信核查
 * @Class Name CreditReportConstants
 * @author 李文勇
 * @Create In 2016年1月14日
 */
public class CreditReportConstants {

	// 详版征信报告标志
	public static final String DETAILED = "1";
	// 简版征信报告标志
	public static final String SIMPLE = "2";
	// 企业征信报告标志
	public static final String ENTERPRISE = "3";
	// 选择状态(选择)
	public static final String CHOOSE = "1";
	// 选择状态(未选择)
	public static final String UNCHOOSE = "0";
	// 详版
	// 申请人身份证号与征信报告录入不一致
	public static final String DETAILED_BOX_ONE = "01";
	// 申请人手机号码与征信报告不一致
	public static final String DETAILED_BOX_TWO = "02";
	// 申请人工作单位与征信报告不一致
	public static final String DETAILED_BOX_THREE = "03";
	// 申请人家庭电话与征信报告不一致
	public static final String DETAILED_BOX_FOUR = "04";
	// 申请人单位电话与征信报告不一致
	public static final String DETAILED_BOX_FIVE = "05";
	// 申请人单位地址与征信报告不一致
	public static final String DETAILED_BOX_SIX = "06";
	// 申请人居住地址与征信报告不一致
	public static final String DETAILED_BOX_SEVEN = "07";
	// 申请人婚姻状况与征信报告不一致
	public static final String DETAILED_BOX_EIGHT = "08";
	// 配偶工作单位与征信报告不一致
	public static final String DETAILED_BOX_NINE = "09";
	// 配偶联系电话与征信报告不一致
	public static final String DETAILED_BOX_TEN = "10";
	// 配偶证件号码与征信报告不一致
	public static final String DETAILED_BOX_ELEVEN = "11";
	// 配偶信息与征信报告不一致
	public static final String DETAILED_BOX_TWELVE = "12";
	// 时间重叠时，进件时工作单位与征信报告不一致
	public static final String DETAILED_BOX_THIRTEEN = "13";
	// 征信报告显示贷款或信用卡当前逾期，需核查账单资料
	public static final String DETAILED_BOX_FOURTEEN = "14";
	// 征信报告显示贷款到期，但仍显示正常还款状态
	public static final String DETAILED_BOX_FIFTEEN = "15";
	// 非助业贷申请人存有止付状态的信用卡
	public static final String DETAILED_BOX_SIXTEEN = "16";
	// 征信报告显示有为他人担保记录
	public static final String DETAILED_BOX_SEVENTEEN = "17";
	// 近期出现较多贷款审批/信用卡审批
	public static final String DETAILED_BOX_EIGHTEEN = "18";
	// 申请人工作单位与公积金缴纳单位不一致
	public static final String DETAILED_BOX_NINETEEN = "19";
	
	// 简版
	// 征信报告显示贷款或信用卡当前逾期，需核查账单资料
	public static final String SIMPLE_BOX_ONE = "01";
	// 征信报告显示贷款到期，但仍显示正常还款状态
	public static final String SIMPLE_BOX_TWO = "02";
	// 非助业贷申请人存有止付状态的信用卡
	public static final String SIMPLE_BOX_THREE = "03";
	// 征信报告显示有为他人担保记录
	public static final String SIMPLE_BOX_FOUR = "04";
	// 近期出现较多贷款审批/信用卡审批
	public static final String SIMPLE_BOX_FIVE = "05";
	// 企业版
	// 征信报告显示自主查询版/银行版，报告日期距进件日期超过15天，建议退回重新提供
	public static final String ENTERPRISE_BOX_ONE = "01";
	// 征信报告显示网查版，报告日期距进件日期超过7天，建议退回重新提供
	public static final String ENTERPRISE_BOX_TWO = "02";
	// 征信报告显示贷款卡状态异常
	public static final String ENTERPRISE_BOX_THREE = "03";
	// 征信报告显示出资方身份证号码与申请表不一致
	public static final String ENTERPRISE_BOX_FOUR = "04";
	// 高管人员信息与申请信息不一致
	public static final String ENTERPRISE_BOX_FIVE = "05";
	// 征信报告显示空白
	public static final String ENTERPRISE_BOX_SIX = "06";
	// 有新增授信
	public static final String ENTERPRISE_BOX_SEVEN = "07";
	// 未结清授信出现当前欠息金额超过200元（不含），建议查看结清证明。
	public static final String ENTERPRISE_BOX_EIGHT = "08";
	// 未结清授信出现关注，建议查看银行出具的说明
	public static final String ENTERPRISE_BOX_NINE = "09";
	// 已结清授信出现关注、不良（次级、可疑、损失），建议查看有无新增授信
	public static final String ENTERPRISE_BOX_TEN = "10";
	// 已结清授信出现垫款、由资产管理公司处置等
	public static final String ENTERPRISE_BOX_ELEVEN = "11";
	// 对外担保信息出现关注
	public static final String ENTERPRISE_BOX_TWELVE = "12";
	// 距进件时间两周内有贷款到期或贷款目前已经到期，建议查看结清证明或续贷合同
	public static final String ENTERPRISE_BOX_THIRTEEN = "13";
	// 声明信息/公共信息明细
	public static final String ENTERPRISE_BOX_FOURTEEN = "14";
	
	// 详版页面贷款表格信息-抵押房贷
	public static final String DETAILED_PAGE_LOAN_HOUSE = "house";
	// 详版页面贷款表格信息-抵押车贷
	public static final String DETAILED_PAGE_LOAN_CAR = "car";
	// 详版页面贷款表格信息-抵押贷款
	public static final String DETAILED_PAGE_LOAN_MORTGAGE = "mortgage";
	// 详版页面贷款表格信息-非抵押贷款
	public static final String DETAILED_PAGE_LOAN_NOMORTGAGE = "noMortgage";
	
	// 简版页面贷款表格信息-抵押房贷
	public static final String SIMPLE_PAGE_LOAN_HOUSE = "house";
	// 简版页面贷款表格信息-抵押车贷
	public static final String SIMPLE_PAGE_LOAN_CAR = "car";
	// 简版页面贷款表格信息-抵押贷款
	public static final String SIMPLE_PAGE_LOAN_MORTGAGE = "mortgage";
	// 简版页面贷款表格信息-非抵押贷款
	public static final String SIMPLE_PAGE_LOAN_NOMORTGAGE = "noMortgage";
	
	// 企业征信版本-自主查询版
	public static final String ENTERPRISE_VERSION_SEARCHSELF = "1";
	// 企业征信版本-银行版
	public static final String ENTERPRISE_VERSION_BANK = "2";
	// 企业征信版本-代表网查版
	public static final String ENTERPRISE_VERSION_SEARCHNET = "3";
}

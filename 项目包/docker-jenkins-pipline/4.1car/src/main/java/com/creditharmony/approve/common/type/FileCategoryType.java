package com.creditharmony.approve.common.type;

import java.util.HashMap;
import java.util.Map;

/**
 * 信雅达插件文件目录的唯一标识
 * @Class Name FileCategory
 * @author 王浩
 * @Create In 2016年3月31日
 */
public enum FileCategoryType {	
	APPLY_MATERIAL("30911001", "申请证明"), 
	IDENTITY_DOC("30911002", "身份证明"),
	MARRIAGE_DOC("30911003", "婚姻证明"), 
	WORK_MANAGE("30911004", "工作证明及经营证明"),
	WORK_DOC("309110041", "工作证明"), 
	MANAGE_DOC("309110042", "经营证明"), 
	SALES_CONTRACT("309110043", "上下游合同资料"),
	SOCAIL_CPF("309110044", "社保公积金证明"), 
	LIVING_ASSETS("30911005", "居住证明及资产证明"), 
	LIVING_MATERIAL("309110051", "居住证明"),
	CAR_OWNERSHIP("309110052", "车产证明"), 
	REAL_EASTATE("309110053", "房产证明"), 
	BANK_ACCOUNT("30911006", "银行流水"),
	SALARY_DOC("309110061", "工资流水"), 
	BANK_SAVINGS("309110062", "常用储蓄"), 
	COMPANY_BANK_ACCOUNT("309110063", "对公流水"),
	CREDIT_REPORT("30911007", "征信报告"), 
	CREDIT_REPORT_DETAIL("309110071", "个人详版"), 
	CREDIT_REPORT_SIMPLE("309110072", "个人简版"),
	CREDIT_REPORT_ENTERPRISE("309110073", "企业征信"), 
	OTHER_DOC("30911008", "其他资料"), 
	OUTVISIT_DOC("30911009", "一次外访"),
	OUTVISIT_MAIN("309110091", "主借人"), 
	OUTVISIT_WORK("309110030911", "外访单位"), 
	OUTVISIT_FAMILY("3091100912", "外访家庭"),				
	OUTVISIT_ADDITIONAL("3091100913", "外访补充资料"), 
	OUTVISIT_OPINION("3091100914", "外访意见书"), 
	OUTVISIT_CO("309110092", "共借人"),
	OUTVISIT_CO_WORK("3091100921", "外访单位"), 
	OUTVISIT_CO_FAMILY("3091100922", "外访家庭"), 
	OUTVISIT_CO_ADDITIONAL("3091100923", "外访补充资料"),
	OUTVISIT_CO_OPINION("3091100924", "外访意见书"),	
	OUTVISIT_SECOND_DOC("30911010", "多次外访"),
	OUTVISIT_SECOND_MAIN("309110101", "主借人"), 
	OUTVISIT_SECOND_WORK("3091101011", "外访单位"), 
	OUTVISIT_SECOND_FAMILY("3091101012", "外访家庭"),				
	OUTVISIT_SECOND_ADDITIONAL("3091101013", "外访补充资料"), 
	OUTVISIT_SECOND_OPINION("3091101014", "外访意见书"), 
	OUTVISIT_SECOND_CO("309110102", "共借人"),
	OUTVISIT_SECOND_CO_WORK("3091101021", "外访单位"), 
	OUTVISIT_SECOND_CO_FAMILY("3091101022", "外访家庭"), 
	OUTVISIT_SECOND_CO_ADDITIONAL("3091101023", "外访补充资料"),
	OUTVISIT_SECOND_CO_OPINION("3091101024", "外访意见书"),	
	RECONSIDER_ADDITIONAL("30911011", "复议补充资料"),
	CREDIT_CONTRACT("30911012", "信用合同"),
	CREDIT_LOAN("309110121", "信用合同"),
	TRUSTS_LOAN("309110122", "委托划扣"),
	GOLDACCOUNT_LIB("309110123", "金账户协议"), 
	GOLDCREIT_CONTRACT("309110124", "金信协议"),
	OTHER_CONTRACT("309110125", "其他材料"),	
	COBORROW_DOC("30911013", "共同借款人"),
	LEGALPERSON_DOC("1302", "法人保证人人"),
	NATURALPERSON_DOC("1301", "自然人保证人人");
/*	VERIFY_CHECK("30911014", "信审初审"),
	VERIFY_RECHECK("30911015", "信审复审"), 
	VERIFY_FINAL_CHECK("30911016", "信审终审"),
	VERIFY_SENIOR_FINAL_CHECK("30911017", "信审高级终审"), 
	RECONSIER_CHECK("30911018", "复议初审"), 
	RECONSIDEK_RECHECK("30911019", "复议复审"),
	RECONSIER_FINAL_CHECK("30911020", "复议终审"), 
	LARGE_AMOUNT_AUDIT("30911021", "大额审批");	*/
	
		
	private static Map<String, FileCategoryType> nameMap = new HashMap<String, FileCategoryType>(
			100);
	private static Map<String, FileCategoryType> codeMap = new HashMap<String, FileCategoryType>(
			100);

	static {
		FileCategoryType[] allValues = FileCategoryType.values();
		for (FileCategoryType obj : allValues) {
			nameMap.put(obj.getName(), obj);
			codeMap.put(obj.getCode(), obj);
		}
	}

	private String name;
	private String code;

	private FileCategoryType(String code, String name) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static FileCategoryType parseByName(String name) {
		return nameMap.get(name);
	}

	public static FileCategoryType parseByCode(String code) {
		return codeMap.get(code);
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}
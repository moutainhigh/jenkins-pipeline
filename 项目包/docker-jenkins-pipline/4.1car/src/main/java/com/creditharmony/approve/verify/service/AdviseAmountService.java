package com.creditharmony.approve.verify.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.credit.constants.CreditReportConstants;
import com.creditharmony.approve.verify.constants.AdviseAmountConstants;
import com.creditharmony.approve.verify.constants.FlowRiskGradeType;
import com.creditharmony.approve.verify.constants.IndustryCoefficientType;
import com.creditharmony.approve.verify.constants.LoanNumberType;
import com.creditharmony.approve.verify.dao.AdviseAmountDao;
import com.creditharmony.approve.verify.dao.AuditRatingResultDao;
import com.creditharmony.approve.verify.entity.AdviseAmount;
import com.creditharmony.approve.verify.entity.AuditRatingResult;
import com.creditharmony.core.approve.type.AccountTraceType;
import com.creditharmony.core.approve.type.CurrencyFlag;
import com.creditharmony.core.approve.type.HoriLoanFlag;
import com.creditharmony.core.approve.type.SalaryTraceFlag;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.dict.util.DictUtils;

/**
 * 额度卡业务处理
 * @Class Name AdviseAmountService
 * @author zhanghu
 * @Create In 2016年8月3日
 */
@Service
public class AdviseAmountService {
	
	/**
	 * 日志对象
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AdviseAmountDao adviseAmountDao;
	
	@Autowired
	private AuditRatingResultDao ratingResultDao;
	
	/**
	 * 查询电话照会_本人核实
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 电话照会_本人核实
	 */
    public AdviseAmount selectDhzhBrhs(AdviseAmount record) {
		return adviseAmountDao.selectDhzhBrhs(record);
	}
    
	/**
	 * 查询资料审核-流水(工资流水、常储流水、对公流水)
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 流水
	 */
    public BigDecimal selectAccountMonthWage(AdviseAmount record) {
    	
    	AdviseAmount adviseAmount =  adviseAmountDao.selectAccountMonthWage(record);
    	if (null == adviseAmount || null == adviseAmount.getSumAccount()) {
    		return BigDecimal.valueOf(0);
    	}
    	return adviseAmount.getSumAccount();
    }
    
	/**
	 * 查询资料审核-个人或对公流水-同业月供
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 同业月供
	 */
    public BigDecimal selectMonthUseMoney(AdviseAmount record) {
    	
    	AdviseAmount adviseAmount =  adviseAmountDao.selectMonthUseMoney(record);
    	if (null == adviseAmount || null == adviseAmount.getSumAccount()) {
    		return BigDecimal.valueOf(0);
    	}
    	return adviseAmount.getSumAccount();
    }
    
	/**
	 * 查询资料审核-个人流水(工资流水)
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 个人流水(工资流水)
	 */
    public BigDecimal selectPayWater(AdviseAmount record) {
    	
    	AdviseAmount adviseAmount =  new AdviseAmount();
    	adviseAmount.setLoanCode(record.getLoanCode());
    	adviseAmount.setrCustomerCoborrowerId(record.getrCustomerCoborrowerId());
    	adviseAmount.setDictCheckType(record.getDictCheckType());
    	
    	// 个人流水
    	adviseAmount.setDictAccountType(AccountTraceType.PERSONAL_WATER_BILLS.getCode());
    	// 工资流水
    	adviseAmount.setAccountFlowMark(SalaryTraceFlag.PAY_WATER.getCode());
    	    	
    	return this.selectAccountMonthWage(adviseAmount);
    }
    
	/**
	 * 查询资料审核-个人流水(常储流水)
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 个人流水(常储流水)
	 */
    public BigDecimal selectCommonlyUsedWater(AdviseAmount record) {
    	
    	AdviseAmount adviseAmount =  new AdviseAmount();
    	adviseAmount.setLoanCode(record.getLoanCode());
    	adviseAmount.setrCustomerCoborrowerId(record.getrCustomerCoborrowerId());
    	adviseAmount.setDictCheckType(record.getDictCheckType());
    	// 个人流水
    	adviseAmount.setDictAccountType(AccountTraceType.PERSONAL_WATER_BILLS.getCode());
    	// 常储流水
    	adviseAmount.setAccountFlowMark(SalaryTraceFlag.COMMONLY_USED_SAVINGS.getCode());
    	    	
    	return this.selectAccountMonthWage(adviseAmount);
    }
    
	/**
	 * 查询资料审核-流水(对公流水)
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 对公流水
	 */
    public BigDecimal selectPublicWater(AdviseAmount record) {
    	
    	AdviseAmount adviseAmount =  new AdviseAmount();
    	adviseAmount.setLoanCode(record.getLoanCode());
    	adviseAmount.setrCustomerCoborrowerId(record.getrCustomerCoborrowerId());
    	adviseAmount.setDictCheckType(record.getDictCheckType());
    	// 对公流水
    	adviseAmount.setDictAccountType(AccountTraceType.PUBLIC_WATER_BILLS.getCode());
    	    	
    	return this.selectAccountMonthWage(adviseAmount);
    }
    
	/**
	 * 查询资料审核-个人-同业月供
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 个人-同业月供
	 */
    public BigDecimal selectUserMonthUse(AdviseAmount record) {
    	
    	AdviseAmount adviseAmount =  new AdviseAmount();
    	adviseAmount.setLoanCode(record.getLoanCode());
    	adviseAmount.setrCustomerCoborrowerId(record.getrCustomerCoborrowerId());
    	adviseAmount.setDictCheckType(record.getDictCheckType());
    	// 个人流水
    	adviseAmount.setDictAccountType(AccountTraceType.PERSONAL_WATER_BILLS.getCode());
    	// 同业借款点选“有”
    	adviseAmount.setOtherLoanMark(HoriLoanFlag.YES.getCode());
    	    	
    	return this.selectMonthUseMoney(adviseAmount);
    }
    
	/**
	 * 查询资料审核-对公-同业月供
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 对公-同业月供
	 */
    public BigDecimal selectPublicMonthUse(AdviseAmount record) {
    	
    	AdviseAmount adviseAmount =  new AdviseAmount();
    	adviseAmount.setLoanCode(record.getLoanCode());
    	adviseAmount.setrCustomerCoborrowerId(record.getrCustomerCoborrowerId());
    	adviseAmount.setDictCheckType(record.getDictCheckType());
    	// 对公流水
    	adviseAmount.setDictAccountType(AccountTraceType.PUBLIC_WATER_BILLS.getCode());
    	// 同业借款点选“有”
    	adviseAmount.setOtherLoanMark(HoriLoanFlag.YES.getCode());
    	    	
    	return this.selectMonthUseMoney(adviseAmount);
    }
    
	/**
	 * 查询个人征信-版本-简版、详版
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 标识
	 */
    public String selectCreditReportVersion(AdviseAmount record) {
    	
    	return this.adviseAmountDao.selectCreditReportVersion(record);
    }
    
	/**
	 * 查询个人征信-简版-id
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 简版-id
	 */
    public String selectCreditReportSimpleId(AdviseAmount record) {
    	
    	AdviseAmount adviseAmount = this.adviseAmountDao.selectCreditReportSimpleId(record);
    	if (null == adviseAmount || adviseAmount.getRelationId() == null) {
    		return "";
    	}
    	return adviseAmount.getRelationId();
    }

	/**
	 * 查询个人征信-详版-id
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 详版-id
	 */
    public String selectCreditReportDetailedId(AdviseAmount record) {
    	
    	AdviseAmount adviseAmount = this.adviseAmountDao.selectCreditReportDetailedId(record);
    	if (null == adviseAmount || adviseAmount.getRelationId() == null) {
    		return "";
    	}
    	return adviseAmount.getRelationId();
    }
    
	/**
	 * 查询-简版征信-贷款-贷款的所有贷款合同金额
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 贷款的所有贷款合同金额
	 */
    public BigDecimal selectConteactAmount(AdviseAmount record) {
    	
    	record.setCurrency(CurrencyFlag.YUAN.getCode());
    	AdviseAmount adviseAmount = this.adviseAmountDao.selectConteactAmount(record);
    	if (null == adviseAmount || adviseAmount.getSumAccount() == null) {
    		return BigDecimal.valueOf(0);
    	}
    	return adviseAmount.getSumAccount();
    }
    
	/**
	 * 查询-简版征信-信用卡-已使用额度之和
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 已使用额度之和
	 */
    public BigDecimal selectUsedLimit(AdviseAmount record) {
    	
    	record.setCurrency(CurrencyFlag.YUAN.getCode());
    	AdviseAmount adviseAmount = this.adviseAmountDao.selectUsedLimit(record);
    	if (null == adviseAmount || adviseAmount.getSumAccount() == null) {
    		return BigDecimal.valueOf(0);
    	}
    	return adviseAmount.getSumAccount();
    }
    
	/**
	 * 查询-详版征信-信用卡-所有“本月应还款金额”之和
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 本月应还款金额”之和
	 */
    public BigDecimal selectCardShouldRepay(AdviseAmount record) {
    	
    	record.setCurrency(CurrencyFlag.YUAN.getCode());
    	AdviseAmount adviseAmount = this.adviseAmountDao.selectCardShouldRepay(record);
    	if (null == adviseAmount || adviseAmount.getSumAccount() == null) {
    		return BigDecimal.valueOf(0);
    	}
    	return adviseAmount.getSumAccount();
    }
    
	/**
	 * 查询-详版征信-贷款-贷款的所有“本月应还款金额
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 所有贷款合同金额
	 */
    public BigDecimal selectLoanShouldRepay(AdviseAmount record) {
    	
    	record.setCurrency(CurrencyFlag.YUAN.getCode());
    	AdviseAmount adviseAmount = this.adviseAmountDao.selectLoanShouldRepay(record);
    	if (null == adviseAmount || adviseAmount.getSumAccount() == null) {
    		return BigDecimal.valueOf(0);
    	}
    	return adviseAmount.getSumAccount();
    }
    
	/**
	 * 查询-电话照会中本人的所有“借款金额”/“借款期限”之和
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 电话照会中本人的所有“借款金额”/“借款期限”之和
	 */
    public BigDecimal selectJkjeLoanAmount(AdviseAmount record) {
    	
    	record.setCurrency(CurrencyFlag.YUAN.getCode());
    	AdviseAmount adviseAmount = this.adviseAmountDao.selectJkjeLoanAmount(record);
    	if (null == adviseAmount || adviseAmount.getSumAccount() == null) {
    		return BigDecimal.valueOf(0);
    	}
    	return adviseAmount.getSumAccount();
    }
    
	/**
	 * 获取信合行业划分及系数
	 * 2016年8月2日
	 * By zhanghu
	 * @param dhzhBrhs
	 * @return 行业系数
	 */
    public Double getIndustryCoefficient(AdviseAmount dhzhBrhs) {
    	
    	// 行业系数
    	Double industryCoefficient = new Double(0);
    	// 行业名称
    	String industryLabel;
    	
    	// 其他不常见行业
    	if (null != dhzhBrhs.getDictCompIndustry() && 
    			AdviseAmountConstants.INDUSTRY_H.equals(dhzhBrhs.getDictCompIndustry())) {
    		// 用行业分类二级查询
    		industryLabel = DictUtils.getDictLabel(dhzhBrhs.getDictCompIndustrySecond(), AdviseAmountConstants.INDUSTRY_TYPE, "");
    	} else { 
    		// 用行业分类一级查询
    		industryLabel = DictUtils.getDictLabel(dhzhBrhs.getDictCompIndustry(), AdviseAmountConstants.INDUSTRY_TYPE, "");
    	}
    	if (!StringUtils.isNotEmpty(industryLabel)) {
    		return Double.valueOf(0);
    	}
    	//查询匹配系数
    	industryCoefficient = IndustryCoefficientType.parseByCode(industryLabel).getName();
    	
    	if (null == industryCoefficient) {
    		return Double.valueOf(0);
    	}
    	
    	return industryCoefficient;
    }
    
	/**
	 * 获取累计折算放款期数
	 * 2016年8月2日
	 * By zhanghu
	 * @param dhzhBrhs 借款期数
	 * @return 累计折算放款期数
	 */
    public Double getLoanNumber(String loanNumber) {
    	
    	// 累计折算放款期数
    	Double conversionLoanNumber = LoanNumberType.parseByCode(loanNumber).getName();
    	if (null == conversionLoanNumber) {
    		return Double.valueOf(0);
    	}
    	return conversionLoanNumber;
    }
    
	/**
	 * 获取流水等级
	 * 2016年8月2日
	 * By zhanghu
	 * @param dhzhBrhs 借款期数
	 * @return 流水等级
	 */
    public String getFlowGrade(BigDecimal flow) {
    	
    	if (null == flow) {
    		return "";
    	}
    	// <3000
    	if (flow.compareTo(AdviseAmountConstants.FLOW_GRADE_SCORE_1) < 0) {
    		return AdviseAmountConstants.FLOW_E;
    	}
    	// 3000<=,<8000
    	if (flow.compareTo(AdviseAmountConstants.FLOW_GRADE_SCORE_1) >= 0 &&
    			flow.compareTo(AdviseAmountConstants.FLOW_GRADE_SCORE_2) < 0) {
    		return AdviseAmountConstants.FLOW_D;
    	}
    	// 8000<=,<20000
    	if (flow.compareTo(AdviseAmountConstants.FLOW_GRADE_SCORE_2) >= 0 &&
    			flow.compareTo(AdviseAmountConstants.FLOW_GRADE_SCORE_3) < 0) {
    		return AdviseAmountConstants.FLOW_C;
    	}
    	// 20000<=,<60000
    	if (flow.compareTo(AdviseAmountConstants.FLOW_GRADE_SCORE_3) >= 0 &&
    			flow.compareTo(AdviseAmountConstants.FLOW_GRADE_SCORE_4) < 0) {
    		return AdviseAmountConstants.FLOW_B;
    	}
    	// >=60000
    	if (flow.compareTo(AdviseAmountConstants.FLOW_GRADE_SCORE_4) >= 0) {
    		return AdviseAmountConstants.FLOW_A;
    	}
    	return "";
    }
    
    /**
     * 查询-信审评分结果表-风险等级
     * 2016年8月16日
     * By zhanghu
     * @return
     */
    public String selectRiskLevel(AdviseAmount record) {
    	String riskLevel = "";
    	AdviseAmount adviseAmount = this.adviseAmountDao.selectJkAuditRating(record);
    	if (null == adviseAmount) {
    		return riskLevel;
    	}

    	// 复议评分风险等级
    	if (!StringUtils.isEmpty(adviseAmount.getReconsiderRiskLevel())) {
    		return adviseAmount.getReconsiderRiskLevel();
    	}
    	// 信审评分风险等级
    	if (!StringUtils.isEmpty(adviseAmount.getVerifyRiskLevel())) {
    		return adviseAmount.getVerifyRiskLevel();
    	}
    	// 进件评分风险等级 - 评分模型
    	if (!StringUtils.isEmpty(adviseAmount.getApplyRiskLevel())) {
    		return adviseAmount.getApplyRiskLevel();
    	}
    	return riskLevel;
    }
    
	/**
	 * 获取转换后风险等级
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 风险等级
	 */
    public String getRiskLevel(String riskLevel) {

    	// 风险等级转换 
    	if (riskLevel.contains(AdviseAmountConstants.Risk_A)) {
    		riskLevel = AdviseAmountConstants.Risk_A;
    	}
    	if (riskLevel.contains(AdviseAmountConstants.Risk_B)) {
    		riskLevel = AdviseAmountConstants.Risk_B;
    	}
    	if (riskLevel.contains(AdviseAmountConstants.Risk_C)) {
    		riskLevel = AdviseAmountConstants.Risk_C;
    	}
    	if (riskLevel.contains(AdviseAmountConstants.Risk_D)) {
    		riskLevel = AdviseAmountConstants.Risk_D;
    	}
    	if (riskLevel.contains(AdviseAmountConstants.Risk_E)) {
    		riskLevel = AdviseAmountConstants.Risk_E;
    	}
    	if (riskLevel.contains(AdviseAmountConstants.Risk_F)) {
    		riskLevel = AdviseAmountConstants.Risk_F;
    	}
    	return riskLevel;
    	
    }
    
    /**
     * 获取流水与风险等级系数
     * 2016年8月11日
     * By zhanghu
     * @param FlowLevel 流水等级
     * @param RiskLevel 风险等级
     * @return流水与风险等级系数
     */
    public Double getFlowRiskLevelCoefficient(BigDecimal flowGrade, String riskLevel) {
    	// 对应分组的流水分数=工资“月认定收入”之和+常储“月认定收入”之和+对公“月认定收入”之和
    	// A(1-3)代表A1、A2、A3三个风险等级
    	String flowLevel = getFlowGrade(flowGrade);
    	riskLevel = getRiskLevel(riskLevel);
    	if (!StringUtils.isNotEmpty(flowLevel) || 
    			!StringUtils.isNotEmpty(riskLevel)) {
    		return Double.valueOf(0);
    	}
    	// 风险_E_流水_E
    	StringBuffer codeSb = new StringBuffer();
    	codeSb.append("风险_");
    	codeSb.append(riskLevel);
    	codeSb.append("(1-3)_流水_");
    	codeSb.append(flowLevel);
    	//查询匹配系数
    	Double flowRiskLevelCoefficient = FlowRiskGradeType.parseByCode(codeSb.toString()).getName();
    	
    	if (null == flowRiskLevelCoefficient) {
    		return Double.valueOf(0);
    	}
    	return flowRiskLevelCoefficient;
    }
    
	/**
	 * 查询-月费率-产品总费率（%）
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 产品总费率（%）
	 */
    public BigDecimal selectProductUsableRate(AdviseAmount record){
    	
    	AdviseAmount adviseAmount = this.adviseAmountDao.selectProductUsableRate(record);
    	if (null == adviseAmount || adviseAmount.getProductUsableRate() == null) {
    		return BigDecimal.valueOf(0);
    	}
    	// %转换*0.01
    	return adviseAmount.getProductUsableRate().multiply(BigDecimal.valueOf(0.01));
    }
    
	/**
	 * 查询最终批借结果
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 最终批借期数、大纲上限
	 */
    public AdviseAmount selectAuditResult(AdviseAmount record) {
    	AdviseAmount adviseAmount = this.adviseAmountDao.selectAuditResult(record);
    	if (null == adviseAmount) {
    		adviseAmount = new AdviseAmount();
    	}
        // 审批分期
    	if (null == adviseAmount.getAuditMonths()) {
    		adviseAmount.setAuditMonths(Integer.valueOf(0));
    	}
        // 大纲上限
    	if (null == adviseAmount.getLimitUpper()) {
    		adviseAmount.setLimitUpper(BigDecimal.valueOf(0));
    	}
    	return adviseAmount;
    	
    }
    
    /**
     * 流水中的月认定收入 
     * 2016年8月15日
     * By zhanghu
     * @return
     */
	private AdviseAmount selectWaterMonthlyIncome(AdviseAmount record) {
		
		AdviseAmount waterMonthlyIncome = new AdviseAmount();
    	// 工资“月认定收入”之和
		BigDecimal payWater = this.selectPayWater(record);
		waterMonthlyIncome.setPayWater(payWater);
    	// 常储“月认定收入”之和
		BigDecimal commonlyUsedWater = this.selectCommonlyUsedWater(record);
		waterMonthlyIncome.setCommonlyUsedWater(commonlyUsedWater);
		// 对公“月认定收入”之和
		BigDecimal publicWater = this.selectPublicWater(record);
		waterMonthlyIncome.setPublicWater(publicWater);
		
		return waterMonthlyIncome;
	}
    
    /**
     * 流水中的月认定收入 
     * 2016年8月15日
     * By zhanghu
     * @return
     */
	private BigDecimal getWaterMonthlyIncome(AdviseAmount waterMonthlyIncome) {
    	
    	// 工资“月认定收入”之和
		BigDecimal payWater = waterMonthlyIncome.getPayWater();
    	// 常储“月认定收入”之和/20
		BigDecimal commonlyUsedWater = waterMonthlyIncome.getCommonlyUsedWater().divide(BigDecimal.valueOf(20));
    	// 对公“月认定收入”之和/20
		BigDecimal publicWater = waterMonthlyIncome.getPublicWater().divide(BigDecimal.valueOf(20));
		
		// 流水中的月认定收入=工资“月认定收入”之和+常储“月认定收入”之和/20+对公“月认定收入”之和/20
		return payWater.add(commonlyUsedWater).add(publicWater);
    }
	
    /**
     * 对应分组的流水分数 
     * 2016年8月15日
     * By zhanghu
     * @return
     */
	private BigDecimal getFlowFraction(AdviseAmount waterMonthlyIncome) {
    	
    	// 工资“月认定收入”之和
		BigDecimal payWater = waterMonthlyIncome.getPayWater();
    	// 常储“月认定收入”之和
		BigDecimal commonlyUsedWater = waterMonthlyIncome.getCommonlyUsedWater();
    	// 对公“月认定收入”之和
		BigDecimal publicWater = waterMonthlyIncome.getPublicWater();
		
		// 对应分组的流水分数=工资“月认定收入”之和+常储“月认定收入”之和+对公“月认定收入”之和
		return payWater.add(commonlyUsedWater).add(publicWater);
    }
	
	
    /**
     * 信用报告月负债 
     * 2016年8月15日
     * By zhanghu
     * @return
     */
	private BigDecimal getCreditReportMonthly(AdviseAmount record) {
		
		// 查询个人征信-版本-简版、详版
	    String creditReportVersion = selectCreditReportVersion(record);
		if (!StringUtils.isNotEmpty(creditReportVersion)) {
			return BigDecimal.valueOf(0);
		}
		// 简版
		if (creditReportVersion.equals(CreditReportConstants.SIMPLE)) {
			
			// 查询个人征信-简版-id
		    String creditReportSimpleId = this.selectCreditReportSimpleId(record);
		    // 关联id
		    record.setRelationId(creditReportSimpleId);
		    // 贷款的所有贷款合同金额
		    BigDecimal conteactAmount = this.selectConteactAmount(record);
		    // 信用卡的所有已使用额度/10之和
		    BigDecimal usedLimit = this.selectUsedLimit(record).divide(BigDecimal.valueOf(10));
		    // 简版月负债=信用卡的所有已使用额度/10之和+贷款的所有贷款合同金额/(到期日期-发放日期）之和
		    return conteactAmount.add(usedLimit);
		    
		}
		// 详版
		if (creditReportVersion.equals(CreditReportConstants.DETAILED)) {
			// 详版月负债=贷款的所有“本月应还款金额”之和+信用卡的所有“本月应还款金额”之和
			// 查询个人征信-详版-id
		    String creditReportDetailedId = this.selectCreditReportDetailedId(record);
		    // 关联id
		    record.setRelationId(creditReportDetailedId);
		    // 贷款的所有贷款合同金额
		    BigDecimal loanShouldRepay = this.selectLoanShouldRepay(record);
		    // 信用卡的所有已使用额度/10之和
		    BigDecimal cardShouldRepay = this.selectCardShouldRepay(record);
		    // 详版月负债=贷款的所有“本月应还款金额”之和+信用卡的所有“本月应还款金额”之和
		    return loanShouldRepay.add(cardShouldRepay);
		}
		
	    return BigDecimal.valueOf(0);
	}
	
    /**
     * 同业月供
     * 2016年8月15日
     * By zhanghu
     * @return 同业月供
     */
	private BigDecimal playMonthUseMoney(AdviseAmount record) {
		
		// 个人-同业月供
	    BigDecimal userMonthUse = this.selectUserMonthUse(record);
		// 对公-同业月供
	    BigDecimal publicMonthUse = this.selectPublicMonthUse(record);
	    // 电话照会中本人的所有“借款金额”/“借款期限”之和
		BigDecimal jkjeLoanAmount = this.selectJkjeLoanAmount(record);
		
		// 同业月供=资料审核中的“月划扣金额”之和+电话照会中本人的所有“借款金额”/“借款期限”之和
		return userMonthUse.add(publicMonthUse).add(jkjeLoanAmount);
	}
	
    /**
	 * 查询大纲上线
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 大纲上线
	 */
    public BigDecimal selectLimitUpper(AdviseAmount record) {
    	AdviseAmount limitUpperAdviseAmount = this.adviseAmountDao.selectLimitUpper(record);
    	if (null == limitUpperAdviseAmount || null == limitUpperAdviseAmount.getLimitUpper()) {
    		return BigDecimal.valueOf(0);
    	}
    	return limitUpperAdviseAmount.getLimitUpper();
    }
    
    /**
	 * 查询-城市月均收入系数
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 大纲上线
	 */
    public BigDecimal selectCityIncomeCoefficient(AdviseAmount record) {
    	
    	List<AdviseAmount> cityIncomeCoefficientAdviseAmountList = this.adviseAmountDao.selectCityIncomeCoefficient(record);
    	// 结果集为空
    	if (cityIncomeCoefficientAdviseAmountList == null ||
    			cityIncomeCoefficientAdviseAmountList.size() == 0) {
    		// 默认0.7
    		return BigDecimal.valueOf(0.7);
    	}
    	
    	AdviseAmount cityIncomeCoefficientAdviseAmount = cityIncomeCoefficientAdviseAmountList.get(0);
    	
    	if (null == cityIncomeCoefficientAdviseAmount ||
    			null == cityIncomeCoefficientAdviseAmount.getCityIncomeCoefficient()) {
    		// 默认0.7
    		return BigDecimal.valueOf(0.7);
    	}
    	return cityIncomeCoefficientAdviseAmount.getCityIncomeCoefficient();
    }
	
    /**
     * 计算推荐额度
     * 2016年8月15日
     * By zhanghu
     * @param record 
     * (必传条件)	借款编号
	 * (必传条件)	主借人（或共借人）ID
	 * (必传条件)	借款人类型（主借人或共借人）
	 * (必传条件)	信审或者复议
	 * 批借的期数、产品类型(页面下拉动态选择)
     * @return
     */
    public BigDecimal getAdviseAmount(AdviseAmount record) {

        Integer auditMonths = record.getAuditMonths();	// 审批分期
        String productCode = record.getProductCode(); 	// 产品类型code
        
        if ((null == auditMonths || auditMonths == 0) &&
        		!StringUtils.isNotEmpty(productCode)) {
        	// 从审批结果中获取
        	AdviseAmount auditResult = this.selectAuditResult(record);
        	if (null == auditResult) {
        		// 没有审批记录
        		return BigDecimal.valueOf(0);
        	}
        	
        	if ((null == auditResult.getAuditMonths() || auditResult.getAuditMonths() == 0) ||
        			(null == auditResult.getLimitUpper() || auditResult.getLimitUpper().
        			compareTo(BigDecimal.valueOf(0)) == 0)) {
        		// 没有审批记录
        		return BigDecimal.valueOf(0);
        	}
        	// 审批分期
        	record.setAuditMonths(auditResult.getAuditMonths());
        	// 大纲上限
        	record.setLimitUpper(auditResult.getLimitUpper());
        	
        }
    	
    	// 电话照会中本人
    	AdviseAmount dhzhBrhs = this.selectDhzhBrhs(record);
    	
    	// 设置电话照会中本人关联id
    	record.setrBrhsId(dhzhBrhs.getrBrhsId());
    	
    	if (null == dhzhBrhs) {
    		return BigDecimal.valueOf(0);
    	}
    	// TODO 申请额度 
        BigDecimal brhsLoanQuota = dhzhBrhs.getLoanApplyAmount();
        if (null == brhsLoanQuota) {
        	brhsLoanQuota = BigDecimal.valueOf(0);
        }
        // TODO 客户自述月还款额
        BigDecimal brhsMonthRepayAmount = dhzhBrhs.getBrhsMonthRepayAmount();
        if (null == brhsMonthRepayAmount) {
        	brhsMonthRepayAmount = BigDecimal.valueOf(0);
        }
        // TODO 其他月收入
        BigDecimal otherMonthInput = dhzhBrhs.getOtherMonthInput();
        if (null == otherMonthInput) {
        	otherMonthInput = BigDecimal.valueOf(0);
        }
        
        // TODO 家庭月支出
        BigDecimal familyMonthOutput = dhzhBrhs.getFamilyMonthOutput();
        if (null == familyMonthOutput) {
        	familyMonthOutput = BigDecimal.valueOf(0);
        }
        
        // TODO 行业系数 
        Double industryCoefficient = getIndustryCoefficient(dhzhBrhs);

        // TODO 城市月均收入系数
        BigDecimal cityIncomeCoefficient = this.selectCityIncomeCoefficient(record);
        
        // TODO 大纲上限
        BigDecimal limitUpper;
        if (null == record.getLimitUpper() || 
        		record.getLimitUpper().compareTo(BigDecimal.valueOf(0)) == 0) {
        	limitUpper = this.selectLimitUpper(record);
        } else {
        	limitUpper = record.getLimitUpper();
        }
        
    	// TODO 获取累计折算放款期数
        Double loanNumber = this.getLoanNumber(record.getAuditMonths().toString());
    	
    	// TODO 信用报告月负债
    	BigDecimal  creditReportMonthly = this.getCreditReportMonthly(record);
    	
    	// TODO 同业月供
    	BigDecimal monthUseMoney = this.playMonthUseMoney(record);
    	
    	// 月认定收入 
    	AdviseAmount waterMonthlyIncomeAdviseAmount = this.selectWaterMonthlyIncome(record);
    	// TODO 流水中的月认定收入 
    	BigDecimal waterMonthlyIncome = this.getWaterMonthlyIncome(waterMonthlyIncomeAdviseAmount);
    	
    	// 流水分数
    	BigDecimal flowFraction = this.getFlowFraction(waterMonthlyIncomeAdviseAmount);
    	// 查询-信审评分结果表-风险等级
    	String riskLevel = selectRiskLevel(record);
    	// TODO 流水和风险等级系数
    	Double flowRiskLevelCoefficient = this.getFlowRiskLevelCoefficient(flowFraction, riskLevel);
    	
    	// TODO 月费率 产品总费率（%）
    	record.setRiskLevel(riskLevel);
        BigDecimal productUsableRate = this.selectProductUsableRate(record);
    	
        // 信审认定月还=流水中的月认定收入+其他月收入-信用报告月负债-同业月供-家庭月支出
        BigDecimal letterMonthlyRepayment =  waterMonthlyIncome.add(otherMonthInput).
        		subtract(creditReportMonthly).subtract(monthUseMoney).
        		subtract(familyMonthOutput);	
        
        // 月还=MIN（客户自述月还、信审认定月还)
        BigDecimal monthlyRepayment =  letterMonthlyRepayment.min(brhsMonthRepayAmount);
        
        
        // 月费率*实际批借期数+1
        BigDecimal tempBigDecimal = productUsableRate.multiply(
        		BigDecimal.valueOf(record.getAuditMonths())).
        		    add(BigDecimal.valueOf(1));
        // 调整系数=城市月均收入系数*行业系数*流水和风险等级系数
        BigDecimal adjustmentCoefficient = cityIncomeCoefficient.multiply(
        		BigDecimal.valueOf(industryCoefficient)).multiply(
    				BigDecimal.valueOf(flowRiskLevelCoefficient));
        		
        // 估算批借额度=月还*调整系数*累计折算放款期数/(月费率*实际批借期数+1）
        BigDecimal estimateBorrowingAmount = monthlyRepayment.multiply(
        		adjustmentCoefficient).multiply(BigDecimal.valueOf(loanNumber)).
        			divide(tempBigDecimal, Integer.valueOf(6), BigDecimal.ROUND_DOWN);
        
        // 推荐额度=MIN(估算批借额度，申请额度、大纲上限）
    	BigDecimal adviseAmount = estimateBorrowingAmount.min(brhsLoanQuota).min(limitUpper);
    	// “推荐额度”字段值规范成5000的整数倍，且全部往下取5000的整数倍，例额度测算金额为109000，则测算额度为105000
		if (adviseAmount.compareTo(BigDecimal.valueOf(0)) < 0) {
			adviseAmount = adviseAmount.divide(BigDecimal.valueOf(5000), Integer.valueOf(0),
	    			BigDecimal.ROUND_UP).multiply(BigDecimal.valueOf(
	    					5000));
		} else {
			adviseAmount = adviseAmount.divide(BigDecimal.valueOf(5000), Integer.valueOf(0),
	    			BigDecimal.ROUND_DOWN).multiply(BigDecimal.valueOf(
	    					5000));
		}
    	
    	return adviseAmount;
    	
    }
    
    /**
     * 保存推荐额度到业务库中
     * 2016年8月23日
     * By zhanghu
     * @param auditRatingResult
     * LoanCode、ratingAdviseAmount必传
     */
    @Transactional(value="loanTransactionManager",readOnly=false)
	public String saveRatingAdviseAmount(AuditRatingResult auditRatingResult) {	
		
		// 查询历史记录
		AuditRatingResult prevRatingResultOld = ratingResultDao.findByLoanCode(auditRatingResult.getLoanCode());			
		if (prevRatingResultOld != null) {
			auditRatingResult.setId(prevRatingResultOld.getId());
		}
		
		// 如果数据库中已经有该条记录，那么就更新该条记录
		if (StringUtils.isNotEmpty(auditRatingResult.getId())) {
			auditRatingResult.preUpdate();
			ratingResultDao.updateSelective(auditRatingResult);
			logger.info("更新推荐额度，LoanCode：" + auditRatingResult.getLoanCode());
			return BooleanType.TRUE;
		} 
		
		auditRatingResult.preInsert();
		ratingResultDao.insertSelective(auditRatingResult);
		logger.info("更新推荐额度，LoanCode：" + auditRatingResult.getLoanCode());
		return BooleanType.TRUE;
		
	}


	
	
	
}

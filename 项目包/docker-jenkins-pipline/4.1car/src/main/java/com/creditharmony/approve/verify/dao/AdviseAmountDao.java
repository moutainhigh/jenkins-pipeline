package com.creditharmony.approve.verify.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.rule.applyengine.client.RatingParam;
import com.creditharmony.approve.rule.auditrating.client.AuditRatingParam;
import com.creditharmony.approve.verify.entity.AdviseAmount;
import com.creditharmony.approve.verify.entity.AuditRatingResult;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 额度卡数据库操作
 * @Class Name AdviseAmountDao
 * @author zhanghu
 * @Create In 2016年8月2日
 */
@LoanBatisDao
public interface AdviseAmountDao extends CrudDao<AdviseAmount> {
	
	/**
	 * 查询电话照会_本人核实
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 电话照会_本人核实
	 */
    public AdviseAmount selectDhzhBrhs(AdviseAmount record);
    
	/**
	 * 查询最终批借结果
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 最终批借期数、大纲上限
	 */
    public AdviseAmount selectAuditResult(AdviseAmount record);
    
	/**
	 * 查询资料审核-流水(工资流水、常储流水、对公流水)
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 最终批借期数
	 */
    public AdviseAmount selectAccountMonthWage(AdviseAmount record);
    
	/**
	 * 查询资料审核-个人或对公流水-同业月供
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 最终批借期数
	 */
    public AdviseAmount selectMonthUseMoney(AdviseAmount record);
    
	/**
	 * 查询个人征信-版本-简版、详版
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 标识
	 */
    public String selectCreditReportVersion(AdviseAmount record);
    
	/**
	 * 查询个人征信-简版-id
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 简版-id
	 */
    public AdviseAmount selectCreditReportSimpleId(AdviseAmount record);

	/**
	 * 查询个人征信-详版-id
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 详版-id
	 */
    public AdviseAmount selectCreditReportDetailedId(AdviseAmount record);

	/**
	 * 查询-简版征信-信用卡-贷款的所有贷款合同金额
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 贷款的所有贷款合同金额
	 */
    public AdviseAmount selectConteactAmount(AdviseAmount record);

	/**
	 * 查询-简版征信-信用卡-已使用额度之和
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 已使用额度之和
	 */
    public AdviseAmount selectUsedLimit(AdviseAmount record);

	/**
	 * 查询-详版征信-信用卡-所有“本月应还款金额”之和
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 本月应还款金额”之和
	 */
    public AdviseAmount selectCardShouldRepay(AdviseAmount record);
    
	/**
	 * 查询-详版征信-贷款-贷款的所有“本月应还款金额
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 所有贷款合同金额
	 */
    public AdviseAmount selectLoanShouldRepay(AdviseAmount record);
    
	/**
	 * 查询-电话照会中本人的所有“借款金额”/“借款期限”之和
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 电话照会中本人的所有“借款金额”/“借款期限”之和
	 */
    public AdviseAmount selectJkjeLoanAmount(AdviseAmount record);
    
	/**
	 * 查询-信审评分结果表-风险等级
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 风险等级
	 */
    public AdviseAmount selectJkAuditRating(AdviseAmount record);
    
	/**
	 * 查询-产品总费率（%）
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 产品总费率（%）
	 */
    public AdviseAmount selectProductUsableRate(AdviseAmount record);

    /**
	 * 查询大纲上线
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 大纲上线
	 */
    public AdviseAmount selectLimitUpper(AdviseAmount record);
    
    /**
	 * 查询-城市月均收入系数
	 * 2016年8月2日
	 * By zhanghu
	 * @param record
	 * @return 大纲上线
	 */
    public List<AdviseAmount> selectCityIncomeCoefficient(AdviseAmount record);
    
}
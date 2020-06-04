package com.creditharmony.approve.credit.dao;


import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.common.entity.CreditReportRisk;
import com.creditharmony.approve.credit.entity.CreditCardInfo;
import com.creditharmony.approve.credit.entity.CreditLoanInfo;
import com.creditharmony.approve.credit.entity.CreditReportSimple;
import com.creditharmony.approve.credit.entity.ex.CreditLoanInfoEx;

/**
 * 征信报告简版
 * @Class Name CreditReportSimpleDao
 * @author 李文勇
 * @Create In 2016年1月18日
 */
@LoanBatisDao
public interface  CreditReportSimpleDao extends CrudDao<CreditReportSimple>{

	/**
	 * 查询简版信用卡信息
	 * 2015年12月29日
	 * By 李文勇
	 * @param param
	 * @return 返回信用卡信息
	 */
	public CreditCardInfo getCardByLoanCode(CreditReportRisk param);
	
	/**
	 * 简版获取贷款信息
	 * 2015年12月31日
	 * By 李文勇
	 * @param param
	 * @return 返回贷款信息
	 */
	public CreditLoanInfo getLoanByLoanCode(CreditLoanInfoEx param);
	
	/**
	 * 简版根据借款编号获取全部的数据
	 * 2016年1月13日
	 * By 李文勇
	 * @param param
	 * @return 返回全部的数据
	 */
	public List<CreditReportSimple> getAllByLoanCode(CreditReportSimple param);
	
	/**
	 * 简版【信用卡明细信息】或【贷款明细信息】中【逾期金额】不为0，则打勾；均为0则打勾。
	 * 2016年1月13日
	 * By 李文勇
	 * @param param
	 * @return 返回是否为零
	 */
	public CreditCardInfo getMaxOVerdue(CreditReportSimple param);
	
	/**
	 * 简版【账户状态为正常】且【到期日期】已在此次判定时间之前，则打勾；否则不打。
	 * 2016年1月13日
	 * By 李文勇
	 * @param param
	 * @return 返回判定结果
	 */
	public CreditLoanInfoEx getSimpleStatus(CreditLoanInfoEx param);
	
	/**
	 * 简版进件产品为薪水借，精英借A/B，优卡借，优房借，楼易借，且【账户状态为止付】则打勾，否则不打
	 * 2016年1月13日
	 * By 李文勇
	 * @param param
	 * @return 返回判定结果
	 */
	public CreditLoanInfoEx getCheckProduct(CreditLoanInfoEx param);
	
	/**
	 * 查询信息中，近半年，查询原因中出现5次及以上【信用卡审批】或3次及以上【贷款审批】，或信用卡审批次数+贷款审批次数超过5次（含5次）则打勾，否则不打。
	 * 2016年1月13日
	 * By 李文勇
	 * @param param
	 * @return 返回条数
	 */
	public int getSimpleReason(CreditLoanInfoEx param);
	
	/**
	 * 下载页面用简版贷记卡
	 * 2016年1月21日
	 * By 李文勇
	 * @param param
	 * @return 返回list
	 */
	public List<CreditCardInfo> downloadUseSimpleCard(CreditReportSimple param);
	
	/**
	 * 下载页面用简版贷款
	 * 2016年1月22日
	 * By 李文勇
	 * @param param
	 * @return 返回list
	 */
	public List<CreditLoanInfo> downloadUseSimpleLoan(CreditReportSimple param);

    /**
     * 根据主键删除征信信息
     * 2016年2月2日
     * By zhanghu
     * @param id
     * @return 返回条数
     */
    int deleteByPrimaryKey(String id);

    /**
     * 新增所有列数据
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 返回条数
     */
    int insertCreditReportSimple(CreditReportSimple record);

    /**
     * 新增有数据的列数据
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 返回条数
     */
    int insertSelective(CreditReportSimple record);

    /**
     * 根据主键查询征信信息
     * 2016年2月2日
     * By zhanghu
     * @param id
     * @return 征信信息对象
     */
    CreditReportSimple selectByPrimaryKey(String id);

    /**
     * 根据主键更新有数据列数据
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 返回条数
     */
    int updateByPrimaryKeySelective(CreditReportSimple record);

    /**
     * 根据主键更新所有列数据
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 返回条数
     */
    int updateByPrimaryKey(CreditReportSimple record);

    /**
     * 根据征信信息对象查询征信信息
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 征信信息对象
     */
    CreditReportSimple selectByCreditReportSimple(CreditReportSimple record);
    
}

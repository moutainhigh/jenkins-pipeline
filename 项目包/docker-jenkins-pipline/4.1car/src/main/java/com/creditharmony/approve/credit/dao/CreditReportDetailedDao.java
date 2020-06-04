package com.creditharmony.approve.credit.dao;


import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
import com.creditharmony.approve.credit.entity.ex.CreditCardDetailedEx;
import com.creditharmony.approve.credit.entity.ex.CreditLoanDetailedEx;
import com.creditharmony.approve.credit.entity.ex.CreditReportDetailedEx;

/**
 * 详版征信报告
 * @Class Name CreditReportDetailedDao
 * @author 李文勇
 * @Create In 2016年1月5日
 */
@LoanBatisDao
public interface CreditReportDetailedDao extends CrudDao<CreditReportDetailed>{

	
	/**
	 * 保存详版征信数据
	 * 2016年2月18日
	 * By 李文勇
	 * @param param
	 * @return
	 */
	public int saveData(CreditReportDetailed param);
	
	/**
	 * 更新个人详版征信
	 * 2016年2月18日
	 * By 李文勇
	 * @param param
	 * @return
	 */
	public int updataById(CreditReportDetailed param);
	
	/**
	 * 根据条件获取数据
	 * 2016年2月15日
	 * By 李文勇
	 * @param param
	 * @return
	 */
	public CreditReportDetailed getIdByParam(CreditReportDetailed param);
	
	/**
	 * 获取征信核查基本信息（详版）
	 * 2016年1月7日
	 * By 李文勇
	 * @param param
	 * @return 返回对象
	 */
	public CreditReportDetailedEx getBaseInfo( CreditReportDetailed param );
	
	/**
	 * 获取征信核查贷记卡负债信息（详版）
	 * 2016年1月7日
	 * By 李文勇
	 * @param param
	 * @return 返回对象
	 */
	public CreditCardDetailedEx getDetailedCard( CreditCardDetailedEx param );
	
	/**
	 * 获取征信核查贷款负债信息（详版）
	 * 2016年1月8日
	 * By 李文勇
	 * @param param
	 * @return 返回对象
	 */
	public CreditLoanDetailedEx getDetailedLoan(CreditLoanDetailedEx param);

	/**
	 * 获取对比的基本信息
	 * 2016年1月11日
	 * By 李文勇
	 * @param param
	 * @return 返回对象
	 */
	public CreditReportDetailedEx getDetailInfo( CreditReportDetailed param );
	
	/**
	 * 根据借款编号获取全部征信数据
	 * 2016年1月12日
	 * By 李文勇
	 * @param param
	 * @return 返回对象集合
	 */
	public List<CreditReportDetailedEx> getAllByLoanCode( CreditReportDetailed param );
	
	/**
	 * 获取共借人信息
	 * 2016年1月12日
	 * By 李文勇
	 * @param param
	 * @return 返回对象集合
	 */
	public List<CreditReportDetailedEx> getDetailBorrow( CreditReportDetailed param );
	
	/**
	 * 获取申请表单位信息 [单位名称，入职时间]（时间重叠时，进件时工作单位与征信报告不一致）
	 * 2016年1月12日
	 * By 李文勇
	 * @param param
	 * @return 返回对象
	 */
	public CreditReportDetailedEx getApplyCompany( CreditReportDetailed param );
	
	/**
	 * 获取征信报告单位信息 [单位名称，入职时间]（时间重叠时，进件时工作单位与征信报告不一致）
	 * 2016年1月12日
	 * By 李文勇
	 * @param param
	 * @return 返回对象
	 */
	public CreditReportDetailedEx getReportCompany( CreditReportDetailed param );
	
	/**
	 * 获取详版贷款（二）和详版信用卡（二）里的【当前逾期期数*】
	 * 2016年1月12日
	 * By 李文勇
	 * @param param
	 * @return 返回对象
	 */
	public CreditCardDetailedEx getMaxOverdue( CreditReportDetailed param );
	
	/**
	 * 查询（征信报告显示贷款到期，但仍显示正常还款状态）
	 * 2016年1月12日
	 * By 李文勇
	 * @param param
	 * @return 返回对象
	 */
	public CreditLoanDetailedEx getNormalStatus( CreditLoanDetailedEx param );
	
	/**
	 * 查询（非经营类申请人存有止付状态的信用卡）
	 * 2016年1月12日
	 * By 李文勇
	 * @param param
	 * @return
	 */
	public CreditLoanDetailedEx getProductStatus( CreditLoanDetailedEx param );
	
	/**
	 * 为他人贷款担保明细信息中，有值则打勾，否则不打。
	 * 2016年1月12日
	 * By 李文勇
	 * @param param
	 * @return 返回操作成功数
	 */
	public int getForOther( CreditLoanDetailedEx param );
	
	/**
	 * 公积金（公积金信息中【单位名称】与【申请表中单位名称不一致】，则打勾，否则不打。若公积金信息中出现两个单位，取开户日期距今较近的单位名称进行比对。）
	 * 2016年1月12日
	 * By 李文勇
	 * @param param
	 * @return
	 */
	public CreditLoanDetailedEx getAccumulation(CreditLoanDetailedEx param);
	
	/**
	 * 查询信息中，近半年，查询原因中出现5次及以上【信用卡审批】或3次及以上【贷款审批】，或信用卡审批次数+贷款审批次数超过5次（含5次）则打勾，否则不打。
	 * 2016年1月13日
	 * By 李文勇
	 * @param param
	 * @return 返回操作成功数
	 */
	public int getDetailedReason(CreditLoanDetailedEx param);
	
	/**
	 * 下载意见书用（详版贷记卡信息）
	 * 2016年1月21日
	 * By 李文勇
	 * @param param
	 * @return 返回对象集合
	 */
	public List<CreditCardDetailedEx> downloadUseDetailCard(CreditReportDetailed param);
	
	/**
	 * 下载意见书用（详版贷款信息）
	 * 2016年1月21日
	 * By 李文勇
	 * @param param
	 * @return 返回对象集合
	 */
	public List<CreditLoanDetailedEx> downloadUseDetailLoan(CreditReportDetailed param);

}

package com.creditharmony.approve.verify.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.internet.entity.PrivateNetworkCheck;
import com.creditharmony.approve.localnet.entity.ex.LoanInfoEx;
import com.creditharmony.approve.rule.channelconfig.client.ChannelConfigParam;
import com.creditharmony.approve.verify.entity.LoanInfo;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

@LoanBatisDao
public interface LoanInfoDao extends CrudDao<LoanInfo> {
	public int deleteByPrimaryKey(String loanCode);

	public int insert(LoanInfo record);

	public int insertSelective(LoanInfo record);

	public LoanInfo selectByApplyId(Map<String, Object> map);

	/**
	 * 更新借款信息表中的状态 2015年12月4日 By 刘燕军
	 * 
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(LoanInfo record);

	public int updateByPrimaryKey(LoanInfo record);

	/**
	 * 
	 * 2015年12月9日 By 刘燕军
	 * 
	 * @param loanCode
	 * @return
	 */
	public LoanInfoEx findLoanInfoEx(VerifyParamEx param);

	/**
	 * 查看弹出页面用（申请信息） 2015年12月10日 By 李文勇
	 * 
	 * @param loanCode
	 * @return
	 */
	public LoanInfo viewGetByLoanCode(String loanCode);

	/**
	 * 查看页面获取汇金提交数据 2016年6月11日 xiaoniu.hu
	 * 
	 * @param loanCode
	 * @return
	 */
	public LoanInfo getLoanInfoByLoanCode(String loanCode);

	/**
	 * 查看页面获取汇金提交数据 2016年10月10日
	 * 
	 * @param loanCode
	 * @return
	 */
	public LoanInfo getLoanInfoByLoanCodeNew(String loanCode);

	/**
	 * 查看页面获取汇金提交数据（自然人保证人借款意愿和进件信息） 2016年11月1日
	 * 
	 * @param loanCode
	 * @return
	 */
	public LoanInfo getLoanInfoCobByLoanCodeNew(Map<String, String> mapp);

	/**
	 * 通过条件查询 单位名称和身份证号 2015年12月22日 By 刘燕军
	 * 
	 * @param loanCode
	 * @param checkType
	 * @return 对应的信息集合
	 */
	public List<PrivateNetworkCheck> findOutsideNetInfoParam(String loanCode, String checkType);

	/**
	 * 通过条件查询 单位名称和身份证号 2015年10月11日
	 * 
	 * @param loanCode
	 * @param checkType
	 * @return 对应的信息集合
	 */
	public List<PrivateNetworkCheck> findOutsideNetInfoParamNew(String loanCode, String checkType);

	/**
	 * 通过条件查询 单位名称和身份证号 2015年12月22日 By 刘燕军
	 * 
	 * @param loanCode
	 * @param coBo
	 * @return 对应的信息集合
	 */
	public List<PrivateNetworkCheck> findOutsideNetParam(String loanCode, String checkType);

	/**
	 * 通过条件查询 单位名称和身份证号 2015年12月22日 By 刘燕军
	 * 
	 * @param loanCode
	 * @param coBo
	 * @return 对应的信息集合
	 */
	public List<PrivateNetworkCheck> findOutsideNetParamNew(String loanCode, String checkType);

	/**
	 * 通过借款编号查询 2016年3月2日 By 李文勇
	 * 
	 * @param loanCode
	 * @return 实体
	 */
	public LoanInfo getByLoanCode(String loanCode);

	/**
	 * 通过借款编号获取对应的标志 2016年5月7日 By 刘燕军
	 * 
	 * @param loanCode
	 * @return 门店放弃、门店拒绝标志
	 */
	public String getVistFlag(String loanCode);

	/**
	 * 根据借款编号获取apply_id主借人id以及进件时间 2016年5月16日 By 王浩
	 * 
	 * @param loanCode
	 * @return
	 */
	public Map<String, String> getCustomerIntoTimeByLoanCode(String loanCode);

	/**
	 * 查询判定渠道所需的数据 2016年3月2日 By 王浩
	 * 
	 * @param param
	 * @return 数据封装list
	 */
	public ChannelConfigParam selectChannelParam(Map<String, Object> param);

	/**
	 * 通过借款编号获取跳转到新旧版申请表页面的标志 2016年9月8日 By 张虎
	 * 
	 * @param loanCode
	 * @return 跳转到新旧版申请表页面的标志
	 */
	public String selectOldornewFlag(String loanCode);

	/**
	 * 获取共借人借款信息 By 张虎
	 * 
	 * @param param
	 * @return 共借人借款信息
	 */
	public LoanInfoEx findCoborroweLoanInfoEx(VerifyParamEx param);

}
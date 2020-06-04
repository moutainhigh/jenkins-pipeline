package com.creditharmony.approve.antifraud.dao;

import java.util.List;

import com.creditharmony.approve.antifraud.entity.ex.AntifraudOffendInfoEx;
import com.creditharmony.approve.antifraud.entity.ex.RemarkEx;
import com.creditharmony.approve.antifraud.entity.ex.RepeatMsgEx;
import com.creditharmony.approve.verify.entity.ex.GreyListEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 反欺诈触发规则dao
 * @Class Name AntifraudOffendInfoDao
 * @author wanglidong
 * @Create In 2015年12月1日
 */
@LoanBatisDao
public interface AntifraudOffendInfoDao extends CrudDao<AntifraudOffendInfoEx>{
	
    /**
     * 内网审核灰黑/名单列表
     * 2015年12月2日
     * By luiyanjun
     * @param loanCode 借款编码
     * @param nameList 姓名集合
     * @return 灰名单稽核
     */
    List<GreyListEx>  selectByLoanCode(String loanCode,String nameList);
    
    /**
     * 获取查重信息
     * 2015年11月30日
     * By wanglidong
     * @param loanCode 借款编码
     * @return 查重信息集合
     */
    public List<AntifraudOffendInfoEx> getAntifraudOffendInfoCheckRepeat(String loanCode);
    
    /**
     * 获取销售人员匹配信息
     * 2015年11月30日
     * By wanglidong
     * @param loanCode 借款 编码
     * @return	销售人员匹配信息集合
     */
    public List<AntifraudOffendInfoEx> getAntifraudOffendInfoSaleInfo(String loanCode);
    
    /**
     * 获取黑名单匹配信息
     * 2015年11月30日
     * By wanglidong
     * @param loanCode 借款 编码
     * @return 黑名单匹配信息集合
     */
    public List<AntifraudOffendInfoEx> getAntifraudOffendInfoBlackList(String loanCode);
    
    /**
     * 获取欺诈案件匹配信息
     * 2015年11月30日
     * By wanglidong
     * @param loanCode 借款编码
     * @return 欺诈案件匹配信息集合
     */
    public List<AntifraudOffendInfoEx> getAntifraudOffendInfoJudgeCase(String loanCode);
    
    /**
     * 修改解除状态
     * 2015年12月2日
     * By wanglidong
     * @param id id
     * @param status 解除状态
     * @param remark 解除理由
     * @return int 
     */
	public int updateRrelieveStatus(String id, String status,String remark);
	
	/**
	 * 修改解除理由
	 * 2015年12月3日
	 * By wanglidong
	 * @param remark 解除理由
	 * @param id 关联id
	 * @return int
	 */
	public int updateRemark(String remark, String id);
	
	/**
	 * 修改全部解除理由
	 * 2016年1月4日
	 * By wanglidong
	 * @param list 修改对象集合
	 * @return int
	 */
	public int updateRemarkAll(List<RemarkEx> list);

	/**
	 * 
	 * 2016年2月18日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 被查重内容
	 */
	public String getApplyid(String loanCode);

	/**
	 * 被查重内容
	 * 2016年2月18日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 被查重内容
	 */
	public List<RepeatMsgEx> getRepeatMsg(String loanCode);

}





















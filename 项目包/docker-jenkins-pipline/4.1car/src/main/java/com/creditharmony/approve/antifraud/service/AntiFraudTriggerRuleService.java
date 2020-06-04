package com.creditharmony.approve.antifraud.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.antifraud.dao.AntifraudBlacklistDao;
import com.creditharmony.approve.antifraud.dao.AntifraudCaseDao;
import com.creditharmony.approve.antifraud.dao.AntifraudOffendInfoDao;
import com.creditharmony.approve.antifraud.dao.AntifraudOffendSalesDao;
import com.creditharmony.approve.antifraud.dao.AntifraudRepeatDao;
import com.creditharmony.approve.antifraud.entity.AntifraudCase;
import com.creditharmony.approve.antifraud.entity.AntifraudOffendSales;
import com.creditharmony.approve.antifraud.entity.AntifraudRepeat;
import com.creditharmony.approve.antifraud.entity.ex.AntifraudBlacklistEx;
import com.creditharmony.approve.antifraud.entity.ex.AntifraudOffendInfoEx;
import com.creditharmony.approve.antifraud.entity.ex.RemarkEx;

/**
 * 反欺诈触发规则service
 * @Class Name AntiFraudTriggerRuleService
 * @author wanglidong
 * @Create In 2015年11月30日
 */
@Service
public class AntiFraudTriggerRuleService {
	@Autowired
	private AntifraudOffendInfoDao antifraudOffendInfoDao;
	@Autowired
	private AntifraudRepeatDao antifraudRepeatDao;
	@Autowired
	private AntifraudOffendSalesDao antifraudOffendSalesDao;	
	@Autowired
	private AntifraudBlacklistDao antifraudBlacklistDao;	
	@Autowired
	private AntifraudCaseDao antifraudCaseDao;	
	
	/**
	 * 获取查重匹配规则内容
	 * 2015年11月30日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 查重匹配规则内容集合
	 */
    public List<AntifraudOffendInfoEx> getAntifraudOffendInfoCheckRepeat(String loanCode){
		return antifraudOffendInfoDao.getAntifraudOffendInfoCheckRepeat(loanCode);
    }
    
    /**
     * 获取销售人员匹配信息
     * 2015年11月30日
     * By wanglidong
     * @param loanCode 借款编码
     * @return 销售人员匹配信息集合
     */
	public List<AntifraudOffendInfoEx> getAntifraudOffendInfoSaleInfo(String loanCode) {
		return antifraudOffendInfoDao.getAntifraudOffendInfoSaleInfo(loanCode);
	}
	
	/**
	 * 获取黑名单匹配信息
	 * 2015年11月30日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 黑名单匹配信息集合
	 */
	public List<AntifraudOffendInfoEx> getAntifraudOffendInfoBlackList(String loanCode) {
		return antifraudOffendInfoDao.getAntifraudOffendInfoBlackList(loanCode);
	}
	
	/**
	 * 获取欺诈案件匹配信息
	 * 2015年11月30日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 欺诈案件匹配信息集合
	 */
	public List<AntifraudOffendInfoEx> getAntifraudOffendInfoJudgeCase(String loanCode) {
		return antifraudOffendInfoDao.getAntifraudOffendInfoJudgeCase(loanCode);
	}
	
	/**
	 * 根据关联id获取相同查重内容的列表
	 * 2015年12月1日
	 * By wanglidong
	 * @param rulesCode 关联规则编号
	 * @return 查重内容集合
	 */
	public List<AntifraudRepeat> getAntifraudRepeat(String rulesCode,String loanCode) {
		Map<String, String> map = new HashMap<String, String>();
		// 规则编号
		map.put("rulesCode", rulesCode);
		// 借款编码
		map.put("loanCode", loanCode);
		return antifraudRepeatDao.getAntifraudRepeat(map);
	}
	
	/**
	 * 根据关联id获取销售匹配信息
	 * 2015年12月2日
	 * By wanglidong
	 * @param id 关联id
	 * @return 销售匹配信息集合
	 */
	public List<AntifraudOffendSales> getSaleInfo(String id,String loanCode) {
		return antifraudOffendSalesDao.getAntifraudOffendSales(id,loanCode);
	}
	
	/**
	 * 根据关联id黑名单匹配信息
	 * 2015年12月2日
	 * By wanglidong
	 * @param id 关联id
	 * @return 黑名单匹配信息集合
	 */
	public List<AntifraudBlacklistEx> getBlackListInfo(String rulesCode,String loanCode) {
		Map<String, String> map = new HashMap<String, String>();
		// 规则编号
		map.put("rulesCode", rulesCode);
		// 借款编码
		map.put("loanCode", loanCode);
		return antifraudBlacklistDao.getAntifraudBlacklist(map);
	}	
	
	/**
	 * 根据关联id获取欺诈案件匹配信息
	 * 2015年12月2日
	 * By wanglidong
	 * @param id 关联id
	 * @return 欺诈案件匹配信息集合
	 */
	public List<AntifraudCase> getJudgeCaseInfo(String id,String loanCode) {
		return antifraudCaseDao.getAntifraudCase(id,loanCode);
	}
	
	/**
	 * 修改反欺诈判定解除状态
	 * 2015年12月2日
	 * By wanglidong
	 * @param id 关联id
	 * @param status 状态
	 * @param remark 解除理由
	 * @return int
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int updateRrelieveStatus(String id, String status,String remark) {
		return antifraudOffendInfoDao.updateRrelieveStatus(id,status,remark);
	}
	
	/**
	 * 修改解除原因
	 * 2015年12月3日
	 * By wanglidong
	 * @param remark 解除理由
	 * @param id 关联id
	 * @return int
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int updateRemark(String remark, String id) {
		return antifraudOffendInfoDao.updateRemark(remark,id);
	}

	/**
	 * 修改全部解除理由
	 * 2015年12月18日
	 * By wanglidong
	 * @param ridAll 关联id
	 * @param remark 解除理由
	 * @param reliveStatus 解除状态
	 * @return int
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int updateRemarkAll(String ridAll,String remark,String reliveStatus) {
		// 取出需要解除的id
		String allRid = ridAll.substring(0,ridAll.length()-1);
		String[] strings = allRid.split(",");
		List<RemarkEx> list = new ArrayList<RemarkEx>();
		RemarkEx remarkEx = null;
		// 将要修改解除理由的id放到集合中
		for (int i = 0; i < strings.length; i++) {
			remarkEx = new RemarkEx();
			remarkEx.setId(strings[i]);
			remarkEx.setRemark(remark);
			remarkEx.setOffendRelieveStatus(reliveStatus);
			list.add(remarkEx);
		}		
		return antifraudOffendInfoDao.updateRemarkAll(list);
	}
	
	/**
	 * 获取applyid
	 * 2016年1月12日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return  返回applyid
	 */
	public String getApplyid(String loanCode) {
		return antifraudOffendInfoDao.getApplyid(loanCode);
	}

}




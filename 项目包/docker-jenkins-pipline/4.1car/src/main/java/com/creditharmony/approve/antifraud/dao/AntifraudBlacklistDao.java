package com.creditharmony.approve.antifraud.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.antifraud.entity.ex.AntifraudBlacklistEx;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 反欺诈黑灰名匹配信息dao
 * @Class Name AntifraudBlacklistDao
 * @author wanglidong
 * @Create In 2015年12月1日
 */
@LoanBatisDao
public interface AntifraudBlacklistDao{
    /**
     * 获取反欺诈黑名单匹配信息
     * 2015年12月2日
     * By wanglidong
     * @param id 关联id
     * @return 黑名单匹配信息
     */
    public List<AntifraudBlacklistEx> getAntifraudBlacklist(Map<String, String> map);  
    
}
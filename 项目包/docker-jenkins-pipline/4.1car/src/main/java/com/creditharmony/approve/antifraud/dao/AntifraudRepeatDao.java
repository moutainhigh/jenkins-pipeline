package com.creditharmony.approve.antifraud.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.antifraud.entity.AntifraudRepeat;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 查重匹配内容dao
 * @Class Name AntifraudRepeatDao
 * @author wanglidong
 * @Create In 2015年12月1日
 */
@LoanBatisDao
public interface AntifraudRepeatDao {

    /**
     * 获取查重内容
     * 2015年12月1日
     * By wanglidong
     * @param id 关联id
     * @return 查重内容集合
     */
    public List<AntifraudRepeat> getAntifraudRepeat(Map<String, String> map);
}
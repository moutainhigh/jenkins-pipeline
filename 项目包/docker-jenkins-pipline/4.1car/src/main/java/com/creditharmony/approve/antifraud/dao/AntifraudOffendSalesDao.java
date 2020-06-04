package com.creditharmony.approve.antifraud.dao;

import java.util.List;

import com.creditharmony.approve.antifraud.entity.AntifraudOffendSales;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 反欺诈销售匹配内容
 * @Class Name AntifraudOffendSalesDao
 * @author wanglidong
 * @Create In 2015年12月2日
 */
@LoanBatisDao
public interface AntifraudOffendSalesDao {

    /**
     * 反欺诈获取销售匹配信息
     * 2015年12月2日
     * By wanglidong
     * @param id 关联id
     * @return 销售匹配信息稽核
     */
    public List<AntifraudOffendSales> getAntifraudOffendSales(String id,String loanCode);
}
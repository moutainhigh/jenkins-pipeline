package com.creditharmony.approve.verify.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.verify.entity.CreditRisk;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 征信核查
 * @Class Name CreditRiskDao
 * @author 李文勇
 * @Create In 2016年1月18日
 */
@LoanBatisDao
public interface CreditRiskDao extends CrudDao<CreditRisk> {

    /**
     * 2016年1月18日
     * By 李文勇
     * @param record
     * @return 返回操作成功数
     */
    public int insertSelective(CreditRisk record);
    
    /**
     * 获取全部征信核查数据
     * 2016年1月18日
     * By 李文勇
     * @param filter
     * @return 返回征信核查数据
     */
    public List<CreditRisk> getTeleCreditRisk(Map<String,Object> filter);

}
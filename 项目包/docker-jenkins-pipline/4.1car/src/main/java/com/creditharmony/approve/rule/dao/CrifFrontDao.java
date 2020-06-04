package com.creditharmony.approve.rule.dao;

import com.creditharmony.approve.rule.entity.CrifFront;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 策略接口输出数据的数据库操作dao
 * @Class Name CrifFrontDao
 * @author 安艳东
 * @Create In 2016年9月22日
 */
@LoanBatisDao
public interface CrifFrontDao {
    int deleteByPrimaryKey(String id);

    int insert(CrifFront record);

    int insertSelective(CrifFront record);

    CrifFront selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CrifFront record);

    int updateByPrimaryKey(CrifFront record);
}
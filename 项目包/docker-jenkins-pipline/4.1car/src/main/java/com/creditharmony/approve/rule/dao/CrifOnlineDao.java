package com.creditharmony.approve.rule.dao;

import com.creditharmony.approve.rule.entity.CrifOnline;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 联机接口输出数据的数据库操作dao
 * @Class Name CrifOnlineDao
 * @author 安艳东
 * @Create In 2016年9月22日
 */
@LoanBatisDao
public interface CrifOnlineDao {
    int deleteByPrimaryKey(String id);

    int insert(CrifOnline record);

    int insertSelective(CrifOnline record);

    CrifOnline selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CrifOnline record);

    int updateByPrimaryKey(CrifOnline record);
}
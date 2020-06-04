package com.creditharmony.approve.antifraud.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.antifraud.entity.ex.BlacklistAllEx;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 黑灰名单dao
 * @Class Name BacklistAllDao
 * @author wanglidong
 * @Create In 2015年12月2日
 */
@LoanBatisDao
public interface BacklistAllDao extends CrudDao<BlacklistAllEx>{

	
	/**
	 * 查询黑灰名单
	 * 2016年1月6日
	 * By wanglidong
	 * @param params
	 * @param pageBounds
	 * @return
	 */
	public List<BlacklistAllEx> findByParams(Map<String,Object> params, PageBounds pageBounds);

	/**
	 * 查询黑灰名单类型
	 * 2016年1月12日
	 * By wanglidong
	 */
	public List<BlacklistAllEx> getBlackListType();

	
	
}

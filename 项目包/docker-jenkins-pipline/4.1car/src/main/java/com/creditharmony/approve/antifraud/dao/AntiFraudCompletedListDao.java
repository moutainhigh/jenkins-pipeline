package com.creditharmony.approve.antifraud.dao;

import java.util.Map;

import com.creditharmony.approve.verify.entity.ex.VerifyListEx;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 反欺诈专员列表
 * @Class Name CommissionerDao
 * @author 赖敏
 * @Create In 2015年11月27日
 */
@LoanBatisDao
public interface AntiFraudCompletedListDao extends CrudDao<VerifyListEx>{
	
	/**
	 * 反欺诈专员已办分页列表
	 * 2015年12月15日
	 * By 赖敏
	 * @param map
	 * @param pageBounds
	 * @return 已办列表
	 */
	public PageList<VerifyListEx> queryList(Map<String, Object> map, PageBounds pageBounds);
	
	/**
	 * 反欺诈专员已办分页列表
	 * 2015年12月15日
	 * By 赖敏
	 * @param map
	 * @param pageBounds
	 * @return 已办列表
	 */
	public PageList<VerifyListEx> queryAllList(VerifyListEx verifyListEntity, PageBounds pageBounds);
}

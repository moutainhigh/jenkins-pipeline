package com.creditharmony.approve.carloan.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.carloan.entity.ex.CarVerifyListEx;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 已办列表
 * @Class Name CarCompletedListDao
 * @author 申诗阔
 * @Create In 2016年1月26日
 */
@LoanBatisDao
public interface CarCompletedListDao extends CrudDao<CarVerifyListEx> {

	/**
	 * 获取已办列表
	 * 2016年1月26日
	 * By 申诗阔
	 * @param map
	 * @param pageBounds
	 * @return 已办列表
	 */
	public PageList<CarVerifyListEx> getCompletedListDoneList(
			Map<String, Object> map, PageBounds pageBounds);
	
	public List<CarVerifyListEx> getCompletedListDoneList(
			Map<String, Object> map);

}

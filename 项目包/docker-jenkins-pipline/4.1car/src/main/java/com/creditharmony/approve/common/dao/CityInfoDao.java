package com.creditharmony.approve.common.dao;

import java.util.List;

import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 省市区管理
 * @Class Name CityInfoDao
 * @author 赖敏
 * @Create In 2015年12月31日
 */
@LoanBatisDao
public interface CityInfoDao extends CrudDao<CityInfo> {

	/**
	 * 根据父ID获取相应区域列表
	 * 2016年1月4日
	 * By 赖敏
	 * @param parentId 父ID
	 * @return 区域列表
	 */
	public List<CityInfo> findByParams(String parentId);
	
}

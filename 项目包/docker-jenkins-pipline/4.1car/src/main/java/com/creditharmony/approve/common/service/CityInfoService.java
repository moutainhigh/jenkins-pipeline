package com.creditharmony.approve.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.creditharmony.approve.common.dao.CityInfoDao;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.core.common.type.CityConstant;
import com.creditharmony.core.service.CoreManager;

/**
 * 省市区服务类
 * @Class Name CityInfoService
 * @author 赖敏
 * @Create In 2015年12月31日
 */
@Service
public class CityInfoService extends CoreManager<CityInfoDao, CityInfo> {
	
	/**
	 * 查询所有省
	 * 2015年12月31日
	 * By 赖敏
	 * @param
	 * @return 省列表
	 */
	public List<CityInfo> findProvince(){
		return dao.findByParams(CityConstant.ROOT_ID+"");
	}
	
	/**
	 * 查询省下级城市
	 * 2015年12月31日
	 * By 赖敏
	 * @param provinceId 省id
	 * @return 城市列表
	 */
	public List<CityInfo> findCity(String provinceId){
		return dao.findByParams(provinceId);
	}
	
	/**
	 * 查询市下级区
	 * 2015年12月31日
	 * By 赖敏
	 * @param cityId 市id
	 * @return 区列表
	 */
	public List<CityInfo> findDistrict(String cityId){
		return dao.findByParams(cityId);
	}
}

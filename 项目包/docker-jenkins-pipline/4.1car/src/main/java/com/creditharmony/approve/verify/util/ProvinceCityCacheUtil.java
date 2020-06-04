package com.creditharmony.approve.verify.util;

import javax.servlet.jsp.tagext.TagSupport;

import com.creditharmony.core.cache.ProvinceCityCache;

/**
 * 获取省市区的方法
 * @Class Name ProvinceCityCacheUtil
 * @author 刘燕军
 * @Create In 2016年2月22日
 */
public class ProvinceCityCacheUtil extends TagSupport{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 通过id获取对应的名字
	 * 2016年2月22日
	 * By 刘燕军
	 * @param id
	 * @return 省市区对应的名字
	 */
	public static String getProviceId(String id){
		String [] ids = id.split(",");
		if(ids != null && ids.length==3){
			return ProvinceCityCache.getInstance().getProvinceCity(ids[0], ids[1], ids[2]);
		}
		return "";
	}
	/**
	 * 通过id获取对应的名字
	 * 2016年2月22日
	 * By 刘燕军
	 * @param privice
	 * @param city
	 * @param area
	 * @return 省市区对应的名字
	 */
	public static String getProCityArea(String privice,String city,String area){
		return ProvinceCityCache.getInstance().getProvinceCity(privice, city, area);
	}
}

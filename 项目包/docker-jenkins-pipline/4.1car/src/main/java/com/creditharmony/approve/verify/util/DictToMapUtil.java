package com.creditharmony.approve.verify.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.dict.util.DictUtils;

/**
 * 根据type获取字典list并转换为map
 * @Class Name DictToMapUtil
 * @author 刘燕军
 * @Create In 2016年3月30日
 */
public class DictToMapUtil {
	
	/**
	 * 通过字典的type 获取对应的字典。并转换为map
	 * 2016年5月19日
	 * By 刘燕军
	 * @param type
	 * @return
	 */
	public static Map<String, String> getMap(String type){
		// 获取type对应的字典数据
		List<Dict> checkResults = DictUtils.getDictList(type);
		Map<String, String> map = new HashMap<String, String>();
		for(Dict dict : checkResults){// 遍历字典数据。转为map
			map.put(dict.getValue(), dict.getLabel());
		}
		return map;
	}
	
	/**
	 * 通过字典的type,value 值 获取对应的字典。并转换为map
	 * 2016年5月19日
	 * By 刘燕军
	 * @param param1
	 * @param type
	 * @return
	 */
	public static Map<String, String> getList(String param1,String type){
		// 获取type，value值对应的字典数据
		List<Dict> checkResults = DictUtils.getDictLists(param1,type);
		Map<String, String> map = new HashMap<String, String>();
		for(Dict dict : checkResults){// 遍历字典数据。转为map
			map.put(dict.getValue(), dict.getLabel());
		}
		return map;
	}
}

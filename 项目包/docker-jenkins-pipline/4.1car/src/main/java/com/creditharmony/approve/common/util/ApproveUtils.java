package com.creditharmony.approve.common.util;

import java.lang.reflect.Field;
import java.util.Date;

import com.creditharmony.approve.common.view.SearchParam;
import com.creditharmony.common.util.StringUtils;
import com.query.ProcessQueryBuilder;

/**
 * 把实体转换为工作流需要的查询类型
 * @Class Name GetQueryParamUtil
 * @author 刘燕军
 * @Create In 2016年3月19日
 */
public class ApproveUtils {

	/**
	 * 获取查询信息
	 * 2016年3月19日
	 * By 刘燕军
	 * @return 查询信息的实体
	 */
	@SuppressWarnings("unchecked")
	public static ProcessQueryBuilder getQuery(SearchParam entity){
		ProcessQueryBuilder query = new ProcessQueryBuilder();
		Class<SearchParam> cl = (Class<SearchParam>)entity.getClass();
		try {
			Field[] fields = cl.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String name = field.getName();
				Object obj = field.get(entity);
				if(!"serialVersionUID".equals(name) && StringUtils.isNotEmpty(name) && obj != null && !obj.equals("") ){ // 不是序列化id也名称不为空
					if(name.startsWith("max")){ // 结束时间 ，则小于
						String key = name.substring(3, 4).toLowerCase()+name.substring(4); // 获取查询的key
						query.put(key +"@<=",((Date)field.get(entity)).getTime()/1000+1);
					}else if(name.startsWith("min")){// 开始时间，则大于
						String key = name.substring(3, 4).toLowerCase()+name.substring(4); // 获取查询的key
						query.put(key +"@>=", ((Date)field.get(entity)).getTime()/1000-1);
					}else if(name.equals("customerName")){// 开始时间，则大于
						query.put(name +"@like","%"+field.get(entity)+"%");
					}else{ // 都不是，则直接放入
						query.put(name, (String)field.get(entity));
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return query;
	}
	
	public static boolean sqlFormat(String input) {
		String filterKeyword = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
		String filter[] = filterKeyword.split("\\|");
		for(String item : filter){
			if (input.toLowerCase().indexOf(item) >= 0) {
				return true;
			}
		}
		return false;
	}
}

package com.creditharmony.approve.verify.util;

import java.util.Date;

/**
 * 根据出生日期获取年龄
 * @Class Name GetAgeUtil
 * @author 李文勇
 * @Create In 2015年12月7日
 */
public class GetAgeUtil {

	/**
	 * 根据传过来的生日计算年龄
	 * 2015年12月7日
	 * By 李文勇
	 * @param birthDay
	 * @return
	 */
	public static String countAge(Date birthDay){
		if( birthDay != null ){
			Date now = new Date();
			long diff = ( now.getTime() - birthDay.getTime() )/( 24*60*60*1000 );
			long result = diff/365;
			return result+"";
		}else{
			return "";
		}
	}
}

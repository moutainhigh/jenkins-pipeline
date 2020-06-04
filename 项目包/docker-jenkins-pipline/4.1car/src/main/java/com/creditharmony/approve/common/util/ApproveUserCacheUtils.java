package com.creditharmony.approve.common.util;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.cache.UserCache;
import com.creditharmony.core.users.entity.User;

/**
 * 用户工具类
 * @Class Name UserUtils
 * @author 张永生
 * @Create In 2015年12月1日
 */
public class ApproveUserCacheUtils {
	
	/**
	 * 根据ID获取用户姓名
	 * @param id
	 * @return 取不到返回null
	 */
	public static String getUserName(String id){
		try{
			if(StringUtils.isNotEmpty(id)){
				User user = UserCache.getInstance().get(id);
				if (user == null){
					return "";
				}
				return user.getName();
			}else{
				return "";
			}
		}catch(Exception e){
			return "";
		}

	}
	
}

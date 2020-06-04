package com.creditharmony.approve.users.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.users.entity.UserInfo;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.core.users.entity.UserRoleOrg;

/**
 * 用户dao
 * 
 * @Class Name UserInfoDao
 * @author 张永生
 * @Create In 2015年12月8日
 */
@LoanBatisDao
public interface UserInfoDao extends CrudDao<UserInfo> {

	/**
	 * 建立用户与机构组的关系
	 * 
	 * @param user
	 * @return
	 */
	public void insertUserOrg(UserInfo userInfo);

	/**
	 * 建立用户、角色、组织间关系 2016年1月7日 By 陈伟东
	 * 
	 * @param userRoleOrg
	 */
	public void insertUserRoleOrg(UserRoleOrg userRoleOrg);

	/**
	 * 删除用户与机构组的关系
	 * 
	 * @param userId
	 * @return
	 */
	public void deleteUserOrg(String userId);

	/**
	 * 删除用户角色的关系
	 * 
	 * @param userId
	 * @return
	 */
	public void deleteUserRole(String userId);

	/**
	 * 删除用户角色组织的关系 2016年1月7日 By 陈伟东
	 * 
	 * @param userId
	 */
	public void deleteUserRoleOrg(String userId);

	/**
	 * 新增用户与角色的关系
	 * 
	 * @param userInfo
	 * @return
	 */
	public void insertUserRole(UserInfo userInfo);

	/**
	 * 分页查询User列表
	 * 
	 * @param user
	 * @return
	 */
	PageList<UserInfo> findUserInfoPage(UserInfo user);

	/**
	 * 查询User列表 不分页 2016年1月26日 By 张振强
	 * 
	 * @param user
	 * @return
	 */
	List<UserInfo> findUserInfo(UserInfo user);

	/**
	 * 获取固定角色用户 2016年1月20日 By 王彬彬
	 * 
	 * @param userMap
	 *            查询条件
	 * @return 用户列表
	 */
	public List<UserInfo> getRoleUser(Map<String, String> userMap,PageBounds pageBounds);
	
	/**
	 * 获取固定角色用户 
	 * 2016年1月20日 By 王彬彬
	 * 
	 * @param userMap
	 *            查询条件
	 * @return 用户列表
	 */
	public List<UserInfo> getRoleUser(Map<String, String> userMap);

	/**
	 * 分页查询User列表
	 * 
	 * @param userMap 查询条件（用户roleid和orgid）
	 * @param pageBounds 分页信息
	 * @return 用户信息
	 */
	public List<UserInfo> findUserInfoByRole(Map<String,String> userMap,PageBounds pageBounds);
}

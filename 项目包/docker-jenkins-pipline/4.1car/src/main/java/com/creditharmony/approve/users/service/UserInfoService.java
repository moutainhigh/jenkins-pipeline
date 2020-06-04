package com.creditharmony.approve.users.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.users.dao.UserInfoDao;
import com.creditharmony.approve.users.entity.UserInfo;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.UserRoleOrg;

/**
 * 用户信息service
 * @Class Name UserInfoService
 * @author 王彬彬
 * @Create In 2016年3月11日
 */
@Component
@Service
public class UserInfoService extends CoreManager<UserInfoDao, UserInfo> {
	
	/**
	 * 通过ID过去用户
	 * 2016年3月11日
	 * By 王彬彬
	 * @param id 用户id
	 * @return 用户信息
	 */
	public UserInfo getUser(String id){
		return dao.get(id);
	}
	
	/**
	 * 保存用户
	 * 2016年3月11日
	 * By 王彬彬
	 * @param userInfo 
	 */
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public void saveUser(UserInfo userInfo){
		dao.insert(userInfo);
	}
	
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public void update(UserInfo userInfo){
		dao.update(userInfo);
	}
	
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public void delete(String id){
		dao.delete(id);
	}
	
	/**
	 * 新增用户与组织机构的关系
	 * @param userInfo
	 * @return
	 */
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public void insertUserOrg(UserInfo userInfo){
		dao.insertUserOrg(userInfo);
	}
	
	/**
	 * 新增用户角色组织关系
	 * 2016年1月7日
	 * By 陈伟东
	 * @param userRoleOrg
	 */
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public void insertUserRoleOrg(UserRoleOrg userRoleOrg){
		dao.insertUserRoleOrg(userRoleOrg);
	}

	/**
	 * 删除用户与机构组的关系
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public void deleteUserOrg(String userId){
		dao.deleteUserOrg(userId);
	}
	

	/**
	 * 删除用户角色的关系
	 * @param userId 用户id
	 * @return 删除用户
	 */
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public void deleteUserRole(String userId){
		dao.deleteUserRole(userId);
	}
	
	/**
	 * 删除用户角色组织的关系
	 * 2016年1月7日
	 * By 陈伟东
	 * @param userId
	 */
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public void deleteUserRoleOrg(String userId){
		dao.deleteUserRoleOrg(userId);
	}
	
	/**
	 * 新增用户与角色的关系
	 * @param userInfo
	 * @return
	 */
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public void insertUserRole(UserInfo userInfo){
		dao.insertUserRole(userInfo);
	}
	
	
	/**
	 * 分页查询用户列表信息
	 * @param page 分页条件
	 * @param user 查询条件
	 * @return
	 */
	@Deprecated
	public Page<UserInfo> findUserInfoPage(Page<UserInfo> page,UserInfo user){
		user.setPage(page);
		page.setList(dao.findUserInfoPage(user));
		return page;
    }
	
	/**
	 * 查询用户列表信息
	 * 2016年1月29日
	 * By 张振强
	 * @param user 查询条件
	 * @return
	 */
	public List<UserInfo> findUserInfo(UserInfo user){
	
		return dao.findUserInfo(user);
    }
	
	
	/**
	 * 获取角色用户
	 * 2016年1月20日
	 * By 王彬彬
	 * @param userMap
	 * @return 用户列表
	 */
	public Page<UserInfo> getRoleUser(Page<UserInfo> page,Map<String,String> userMap){
		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize());
		 List<UserInfo> lstUser = dao.getRoleUser(userMap,pageBounds);
		PageList<UserInfo> pageList = (PageList<UserInfo>)lstUser;
		PageUtil.convertPage(pageList, page);
		
		return page;
    }
	
	/**
	 * 获取角色用户
	 * 2016年1月20日
	 * By 王彬彬
	 * @param userMap
	 * @return 用户列表
	 */
	public List<UserInfo> getRoleUser(Map<String,String> userMap){
		return dao.getRoleUser(userMap);
    }
	
	/**
	 * 
	 * 2016年2月23日
	 * By 王彬彬
	 * @param page
	 * @param userMap 查询条件(orgId：角色ID，roleId：角色ID（枚举BaseRole，枚举LoanRole）)
	 * @return 用户信息
	 */
	public Page<UserInfo> findUserInfoByRole(Page<UserInfo> page,Map<String,String> userMap){
		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize());
		
		List<UserInfo> userList = dao.findUserInfoByRole(userMap,pageBounds);
		PageList<UserInfo> pageList = (PageList<UserInfo>) userList;
		PageUtil.convertPage(pageList, page);
		
		return page;
    }
}

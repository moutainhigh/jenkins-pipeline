package com.creditharmony.approve.verify.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.core.users.entity.User;

/**
 * 用户服务
 * @Class Name ApproveUserDao
 * @author 赖敏
 * @Create In 2016年1月19日
 */
@LoanBatisDao
public interface ApproveUserDao extends CrudDao<User>{
    
	/**
	 * 获取终审角色列表
	 * 2016年1月19日
	 * By 赖敏
	 * @param roleId
	 * @return 角色列表
	 */
	public List<User> getFinalChecks(String roleId); 
	
}
package com.creditharmony.approve.users.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.users.dao.RoleInfoDao;
import com.creditharmony.approve.users.entity.RoleInfo;
import com.creditharmony.core.service.CoreManager;

/**
 * 角色管理Service
 * @Class Name RoleInfoService
 * @author 陈伟东
 * @Create In 2015年12月25日
 */
@Service
public class RoleInfoService extends CoreManager<RoleInfoDao, RoleInfo> {
	
	public RoleInfo getRole(String id){
		return dao.get(id);
	}

	@Transactional(readOnly = false,value = "loanTransactionManager")
	public void saveRole(RoleInfo roleInfo){
		dao.insert(roleInfo);
	}
	
	@Transactional(readOnly = false,value = "loanTransactionManager")
	public void update(RoleInfo roleInfo){
		dao.update(roleInfo);
	}
}

package com.creditharmony.approve.users.dao;

import com.creditharmony.approve.users.entity.RoleInfo;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 角色DAO接口
 * @Class Name RoleInfoDao
 * @author 陈伟东
 * @Create In 2015年11月27日
 */
@LoanBatisDao
public interface RoleInfoDao extends CrudDao<RoleInfo> {
	
}

package com.creditharmony.approve.users.dao;

import com.creditharmony.approve.users.entity.OrgInfo;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;


/**
 * 组织机构dao
 * @Class Name OrgInfoDao
 * @author 张永生
 * @Create In 2015年12月8日
 */
@LoanBatisDao
public interface OrgInfoDao extends CrudDao<OrgInfo> {

}

package com.creditharmony.approve.carloan.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.carloan.entity.CustomerContactPerson;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 借款编码和借款人类型（主借人或共借人）获取客户联系人
 * @Class Name CustomerContactPersonDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface CustomerContactPersonDao extends CrudDao<CustomerContactPerson> {
	
	/**
	 * 根据借款编码和借款人类型（主借人或共借人）获取客户联系人列表
	 * 2016年1月25日
	 * By 申诗阔
	 * @param map
	 * @return 客户联系人列表
	 */
	public List<CustomerContactPerson> findByLoanCodeAndType(Map<String, String> map);
}
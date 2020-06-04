package com.creditharmony.approve.newCar.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.newCar.entity.NewCustomerContactPerson;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 借款编码和借款人类型（主借人或共借人）获取客户联系人
 * @Class Name CustomerContactPersonDao
 * @author 李高远
 * @Create In 2017年4月12日
 */
@LoanBatisDao
public interface NewCustomerContactPersonDao extends CrudDao<NewCustomerContactPerson> {
	
	/**
	 * 根据借款编码和借款人类型（主借人或共借人）获取客户联系人列表
	 * 2016年1月25日
	 * By 申诗阔
	 * @param map
	 * @return 客户联系人列表
	 */
	public List<NewCustomerContactPerson> findByLoanCodeAndType(Map<String, String> map);
}
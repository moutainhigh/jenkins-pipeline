package com.creditharmony.approve.management.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.management.entity.SalesStaff;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
@LoanBatisDao
public interface SalesStaffDao extends CrudDao<SalesStaff>{
	
	/**
	 * 反欺诈-销售人员信息列表
	 */
	public List<SalesStaff> getSalesStaff() ;
	
	/**
	 * 分页
	 */
	public PageList<SalesStaff> findByParams(Map<String, Object> map,
			PageBounds pageBounds);
	/**
	 * 新增
	 * @param salesStaff
	 */
	public void addSalesStaff(SalesStaff salesStaff);
	
	/**
	 * 删除
	 * @param id
	 * @return 
	 */
	public int delSalesStaff(String id);

	/**
	 * 查询对象
	 * @param salesStaff
	 */
	public SalesStaff findSalesStaff(SalesStaff salesStaff);
	
	/**
	 * 修改
	 * @param salesStaff
	 */
	public void updateSalesStaff(SalesStaff salesStaff);

}

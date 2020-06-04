package com.creditharmony.approve.management.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.management.dao.SalesStaffDao;
import com.creditharmony.approve.management.entity.SalesStaff;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager; 
/**
 * Service
 * @Class Name SalesStaffService
 * @author 袁李杰
 * @Create In 2016年03月15日
 */
@Service
public class SalesStaffService extends CoreManager<SalesStaffDao,SalesStaff>{
	
	@Autowired
	private SalesStaffDao salesStaffDao;
	/**
	 * 查询反欺诈-销售人员信息
	 * @return
	 */
	public List<SalesStaff> getSalesStaff() {
		List<SalesStaff> salesStaff =  salesStaffDao.getSalesStaff();
		return salesStaff;
	}

	/**
	 * 获取分页列表
	 * @param page
	 * @param map
	 * @return
	 */
	public Page<SalesStaff> findByParams(Page<SalesStaff> page,
			Map<String, Object> map) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
        PageList<SalesStaff> pageList = (PageList<SalesStaff>)dao.findByParams(map, pageBounds);
        PageUtil.convertPage(pageList, page);
		return page;
	}

	/**
	 * 新增销售人员
	 * @param salesStaff
	 * @return
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public String addSalesStaff(SalesStaff salesStaff) {
		salesStaff.preInsert();
		salesStaffDao.addSalesStaff(salesStaff);
		return salesStaff.getId();
	}
	
	/**
	 * 返回对象查询
	 * @param salesStaff
	 * @return
	 */
	public SalesStaff findSalesStaff(SalesStaff salesStaff) {
		salesStaff = salesStaffDao.findSalesStaff(salesStaff);
		return salesStaff;
	}
	
	/**
	 * 修改
	 * @param salesStaff
	 * @return
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public String updateSalesStaff(SalesStaff salesStaff) {
		salesStaff.preUpdate();
		salesStaffDao.updateSalesStaff(salesStaff);
		return salesStaff.getId();
	}
	/**
	 * 删除
	 * @param id
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int delSalesStaff(String id) {
		int del = salesStaffDao.delSalesStaff(id);
		return del;
	}
}

package com.creditharmony.approve.management.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.antifraud.dao.AntiFraudCompletedListDao;
import com.creditharmony.approve.common.dao.CompletedListDao;
import com.creditharmony.approve.verify.entity.ex.VerifyListEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.mybatis.paginator.domain.Paginator;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;


/**
 * 获取所有的已办
 * @Class Name VerifyCompletedService
 * @author 刘燕军
 * @Create In 2016年3月3日
 */
@Service
public class VerifyAllService extends CoreManager<CompletedListDao, VerifyListEx>{
   

	@Autowired
	private CompletedListDao completedListDao;
	@Autowired
	private AntiFraudCompletedListDao antiFraudCompletedListDao;
	
	/**
	 * 获取指定某一个角色下的所有的已办
	 * 2016年3月17日
	 * By 刘燕军
	 * @param page
	 * @param verifyListEntity
	 * @return 已办列表
	 */
	public Page<VerifyListEx> findAll(Page<VerifyListEx> page,VerifyListEx verifyListEntity){
		//获取所有下级组织
		verifyListEntity.setOrgIds(completedListDao.getOrgIds(verifyListEntity.getOrgId()));
		PageBounds pageBounds = new PageBounds(0,page.getPageSize());
		pageBounds.setCountBy("LOAN_CODE");
		pageBounds.setFilterOrderBy(BooleanType.FALSE);
		verifyListEntity.setPageSize(page.getPageSize());
		verifyListEntity.setOrderBy(page.getOrderBy());
		verifyListEntity.setPageNo(page.getPageNo());
		PageList<VerifyListEx> pageList = completedListDao.getAllCompleted(verifyListEntity, pageBounds);
		Paginator temp=pageList.getPaginator();
		int count = completedListDao.getOrgCount(verifyListEntity, pageBounds);
		Paginator newPage=new Paginator(temp.getPage(),temp.getLimit(),count);
		pageList.setPaginator(newPage);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	/**
	 * 反欺诈专员已办分页列表
	 * 2015年12月15日
	 * By 赖敏
	 * @param page
	 * @param map 
	 * @return 已办列表
	 */
	public Page<VerifyListEx> findAntifraudAll(Page<VerifyListEx> page,VerifyListEx verifyListEntity){
		//获取所有下级组织
		verifyListEntity.setOrgIds(completedListDao.getOrgIds(verifyListEntity.getOrgId()));
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setCountBy("LOAN_CODE");
		verifyListEntity.setOrderBy(page.getOrderBy());
		PageList<VerifyListEx> pageList = antiFraudCompletedListDao.queryAllList(verifyListEntity, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
		
	}
	/**
	 * 获取所有的已办
	 * 2016年3月17日
	 * By 刘燕军
	 * @param page
	 * @param verifyListEntity
	 * @return 已办列表
	 */
	public Page<VerifyListEx> findAllCom(Page<VerifyListEx> page,VerifyListEx verifyListEntity){
		PageBounds pageBounds = new PageBounds(0,page.getPageSize());
		pageBounds.setCountBy("LOAN_CODE");
		pageBounds.setFilterOrderBy(BooleanType.FALSE);
		verifyListEntity.setPageSize(page.getPageSize());
		verifyListEntity.setOrderBy(page.getOrderBy());
		verifyListEntity.setPageNo(page.getPageNo());
		PageList<VerifyListEx> pageList = completedListDao.getCompleted(verifyListEntity, pageBounds);
		Paginator temp=pageList.getPaginator();
		int count = completedListDao.getCount(verifyListEntity, pageBounds);
		Paginator newPage=new Paginator(temp.getPage(),temp.getLimit(),count);
		pageList.setPaginator(newPage);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 通过指定的条件获取对应的已办集合
	 * 2016年5月4日
	 * By 刘燕军
	 * @param param
	 * @return 已办集合
	 */
	public List<VerifyListEx> findOneDone(VerifyParamEx param){
		List<VerifyListEx> list = completedListDao.getOneDone(param);
		return list;
	}
}

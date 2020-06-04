package com.creditharmony.approve.common.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.verify.entity.ex.VerifyListEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 已办列表
 * @Class Name CompletedListDao
 * @author 申诗阔
 * @Create In 2016年1月19日
 */
@LoanBatisDao
public interface CompletedListDao extends CrudDao<VerifyListEx> {

	/**
	 * 获取已办列表
	 * 2016年1月19日
	 * By 申诗阔
	 * @param map
	 * @param pageBounds
	 * @return 已办列表
	 */
	public PageList<VerifyListEx> getCompletedListDoneList(Map<String, Object> map, PageBounds pageBounds);
	
	/**
	 * 获取某角色下所有的已办
	 * 2016年3月3日
	 * By 刘燕军
	 * @param map
	 * @param pageBounds
	 * @return 已办列表
	 */
	public PageList<VerifyListEx> getAllCompleted(VerifyListEx verifyListEntity, PageBounds pageBounds);
	
	/**
	 * 获取某角色下所有的已办总数
	 * 2016年6月24日
	 * xiaoniu.hu
	 * @param verifyListEntity
	 * @param pageBounds
	 * @return
	 */
	public int getOrgCount(VerifyListEx verifyListEntity, PageBounds pageBounds);
	
	/**
	 * 获取所有的已办
	 * 2016年3月3日
	 * By 刘燕军
	 * @param map
	 * @param pageBounds
	 * @return 已办列表
	 */
	public PageList<VerifyListEx> getCompleted(VerifyListEx verifyListEntity, PageBounds pageBounds);
	
	
	/**
	 * 通过固定的参数获取对应的已办集合
	 * 2016年5月4日
	 * By 刘燕军
	 * @param param
	 * @return 已办集合
	 */
	public List<VerifyListEx> getOneDone(VerifyParamEx param);
	
	/**
	 * 获取所有已办时的总单数
	 * 2016年5月26日
	 * By 刘燕军
	 * @return 总单数
	 */
	public int getCount(VerifyListEx verifyListEntity, PageBounds pageBounds);
	
	/**
	 * 获取管辖的所有组织
	 * 2016年5月26日
	 * xiaoniu.hu
	 * @param orgId 
	 * @return
	 */
	public List<String> getOrgIds(String orgId);
	/**
	 * 获取汇成所有审批的机构id
	 * 2016年5月26日
	 * xiaoniu.hu
	 * @param orgId 
	 * @return
	 */
	public List<String> getCheckAfraudRecheckOrgIds();

	/**
	 * 获取汇成所有审批用户
	 * 2016年6月22日
	 * By wanglidong
	 * @param orgIds
	 */
	public List<Map<String, String>> getApproveUserList(List<String> orgIds);
}

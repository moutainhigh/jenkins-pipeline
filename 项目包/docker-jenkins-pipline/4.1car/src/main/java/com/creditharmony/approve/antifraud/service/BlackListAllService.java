package com.creditharmony.approve.antifraud.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.antifraud.dao.BacklistAllDao;
import com.creditharmony.approve.antifraud.entity.ex.BlacklistAllEx;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.Order;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;

/**
 * 黑灰名单Service
 * @Class Name BlackListService
 * @author wanglidong
 * @Create In 2015年11月23日
 */
@Service
public class BlackListAllService extends CoreManager<BacklistAllDao,BlacklistAllEx> {
	@Autowired
	private BacklistAllDao backlistAllDao;		
	
	/**
	 * 获取分页列表
	 * 2015年12月1日
	 * By wanglidong
	 * @param page
	 * @param filter
	 * @return
	 */
	public Page<BlacklistAllEx> findByParams(Page<BlacklistAllEx> page,Map<String,Object> filter) {
		// 设置分页字段
		String sortString = "judge_proc_date.desc";
		// 调用分页工具类型
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize(),Order.formString(sortString));
		// 获取分页数据
        PageList<BlacklistAllEx> pageList = (PageList<BlacklistAllEx>)dao.findByParams(filter, pageBounds);
        // 将pageList 转换为 page
        PageUtil.convertPage(pageList, page);
        return page;
	}

	/**
	 * 查黑灰名单类型
	 * 2016年1月12日
	 * By wanglidong
	 */
	public List<BlacklistAllEx> getBlackListType() {
		List<BlacklistAllEx> blackListType = backlistAllDao.getBlackListType();
		return blackListType;
	}	
	
}



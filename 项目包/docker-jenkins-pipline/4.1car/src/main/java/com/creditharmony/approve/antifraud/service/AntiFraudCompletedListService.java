package com.creditharmony.approve.antifraud.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.creditharmony.approve.antifraud.dao.AntiFraudCompletedListDao;
import com.creditharmony.approve.verify.entity.ex.VerifyListEx;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;

/**
 * 反欺诈专员列表
 * @Class Name CommissionerService
 * @author 赖敏
 * @Create In 2015年11月27日
 */
@Service
public class AntiFraudCompletedListService extends CoreManager<AntiFraudCompletedListDao,VerifyListEx>{
	
	@Autowired
	private AntiFraudCompletedListDao antiFraudCompletedListDao;
	
	/**
	 * 反欺诈专员已办分页列表
	 * 2015年12月15日
	 * By 赖敏
	 * @param page
	 * @param map 
	 * @return 已办列表
	 */
	public Page<VerifyListEx> findPage(Page<VerifyListEx> page,Map<String, Object> map){
		// 分页工具类
		PageBounds pageBounds=new PageBounds(page.getPageNo(), page.getPageSize());
		// 利用setPage进行分页查询和利用PageBounds进行分页查询时，默认是按照id进行count(), 默认是去掉sql语句最后的order by片段，如果查询sql中的子查询中有order by片段，不能在count语句中去掉末端的order by片段 则设置false
		map.put("orderBy", page.getOrderBy());
		// 获取分页列表
		PageList<VerifyListEx> pageList = antiFraudCompletedListDao.queryList(map, pageBounds);
		// 将PageList转换为Page
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
}

package com.creditharmony.approve.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.common.dao.CompletedListDao;
import com.creditharmony.approve.verify.dao.ApproveDictDao;
import com.creditharmony.approve.verify.entity.ex.VerifyListEx;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;

/**
 * 信审已办共通
 * @Class Name CompletedListService
 * @author 赖敏
 * @Create In 2016年1月20日
 */
@Service
public class CompletedListService extends CoreManager<CompletedListDao, VerifyListEx> {

	@Autowired
	private CompletedListDao completedListDao;
	
	@Autowired
	private ApproveDictDao dictDao;
	
	/**
	 * 获取已办，参数map中key及说明：
	 * <li>key：transactorCode，说明：当前登录人员的code值，只能查询自己办理的列表，不传则查询全部</li>
	 * <li>key：verifyNode，说明：审批节点，取值为反欺诈、初审、复审、复议等的code值，祥见枚举CheckType</li>
	 * <br><br>其他说明：目前页大小设置为1；其他参数可封入map
	 * 2015年12月28日
	 * By 申诗阔
	 * @param page
	 * @param map
	 * @return 已办列表
	 */
	public Page<VerifyListEx> findPage(Page<VerifyListEx> page, Map<String, Object> map) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		map.put("orderBy", page.getOrderBy());
		PageList<VerifyListEx> pageList = completedListDao.getCompletedListDoneList(map, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	/**
	 * 获取汇成所有审批用户
	 * 2016年6月22日
	 * By wanglidong
	 * @param orgIds
	 */
	public List<Map<String, String>> getApproveUserList(List<String> orgIds) {
		List<Map<String,String>> approveUserList = completedListDao.getApproveUserList(orgIds);
		return approveUserList;
	}

	/**
	 * 
	 * 2016年6月23日
	 * By wanglidong
	 * @param departmentId
	 * @return
	 */
	public List<String> getCheckAfraudRecheckOrgIds() {
		List<String> checkAfraudRecheckOrgIds = completedListDao.getCheckAfraudRecheckOrgIds();
		return checkAfraudRecheckOrgIds;
	}

}

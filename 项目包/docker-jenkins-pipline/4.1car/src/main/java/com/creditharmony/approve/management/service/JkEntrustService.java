package com.creditharmony.approve.management.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.StringHelper;
import com.creditharmony.core.common.dao.EntrustDao;
import com.creditharmony.core.common.entity.Entrust;
import com.creditharmony.core.common.entity.ex.EntrustEx;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.type.DeleteFlagType;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.User;


/**
 * 委托管理的service
 * @Class Name EntrustService
 * @author 王浩
 * @Create In 2015年12月8日
 */
@Service
public class JkEntrustService  extends CoreManager<EntrustDao, Entrust> {
	
	@Autowired
	private EntrustDao entrustDao;	
	
	/**
	 * 根据id获取委托详情
	 * 2015年12月9日
	 * By 王浩
	 * @param id
	 * @return 单条委托详情记录
	 */
	public EntrustEx getEntrust(String id) {
		EntrustEx entrustEx = entrustDao.getByPrimaryId(id);
		if (entrustEx == null){
			return null;
		}
		return entrustEx;
	}

	/**
	 * 列表查询分页
	 * 2015年12月9日
	 * By 王浩
	 * @param page 翻页组件
	 * @param filter 参数
	 * @return 翻页page对象
	 */
	public Page<EntrustEx> getEntrustList(Page<EntrustEx> page,
			Map<String, Object> filter) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());		       
		PageList<EntrustEx> entrustList = (PageList<EntrustEx>) entrustDao
				.findListByParams(filter, pageBounds);
        PageUtil.convertPage(entrustList, page);
		return page;
	}

	/**
	 * 根据id是否为空，判断是update或者insert
	 * 2015年12月8日
	 * By 王浩
	 * @param entrust
	 * @return 是否保存成功
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String saveEntrust(EntrustEx entrust) {
		if (StringHelper.isNotEmpty(entrust.getId())) {
			// id不为空则保存
			entrust.preUpdate();
			entrustDao.updateSelective(entrust);
			return BooleanType.TRUE;
		} else {
			entrust.setValidFlag(DeleteFlagType.NORMAL);
			entrust.preInsert();
			entrustDao.insertEntrust(entrust);
			return BooleanType.TRUE;			
		}
	}
	
	/**
	 * 根据id删除某一条记录
	 * 2015年12月10日
	 * By 王浩
	 * @param id 记录主键id
	 * @return none
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public void delEntrustById(String id) {
		entrustDao.deleteByPrimaryId(id);
	}		
	
	/**
	 * 更新是否可用
	 * 2016年2月23日
	 * By 王浩
	 * @param entrust 委托信息
	 * @retrun none
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public void updateValid(EntrustEx entrust) {
		entrustDao.updateValid(entrust);
	}		

	/**
	 * 下拉框选择同角色的其他用户
	 * 2016年1月22日
	 * By 王浩
	 * @param param
	 * @return 
	 */
	public List<User> findOtherByPrevUser(Map<String,Object> param){		
		List<User> userList = entrustDao.selectOtherByPrevUser(param);
		return userList;
	}
	
	/**
	 * 下拉框根据角色名字查出所有用户
	 * 2016年1月22日
	 * By 王浩
	 * @param param
	 * @return 
	 */
	public List<User> findUserByRole(Map<String,Object> param){		
		List<User> userList = entrustDao.selectUserByRole(param);
		return userList;
	}
		
	/**
	 * 根据登录用户名，查找某一个用户信息
	 * 2016年3月15日
	 * By 王浩
	 * @param param
	 * @return 
	 */
	public EntrustEx getCurrentUser(Map<String,Object> param){
		return entrustDao.getCurrenUserInfo(param);
	}

}

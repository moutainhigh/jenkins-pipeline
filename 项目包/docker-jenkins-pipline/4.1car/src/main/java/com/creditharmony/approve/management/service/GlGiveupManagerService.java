package com.creditharmony.approve.management.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.management.cache.GiveupCache;
import com.creditharmony.approve.management.dao.GlGiveupDao;
import com.creditharmony.approve.management.entity.GlGiveup;
import com.creditharmony.approve.management.util.GlGiveupComparator;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.service.CoreManager;

/**
 * 客户放弃码维护service
 * @Class Name GlGiveupService
 * @author 李文勇
 * @Create In 2015年12月17日
 */
@Service
public class GlGiveupManagerService extends CoreManager<GlGiveupDao, GlGiveup> {

	/**
	 * 获取全部数据
	 * 2016年1月13日
	 * By 李文勇
	 * @return 返回放弃码list
	 */
	public List<GlGiveup> findAll(){
		List<GlGiveup> refuseList = GiveupCache.getInstance().getList();
		if(ArrayHelper.isNotEmpty(refuseList)){
			// 根据码值排序
			Collections.sort(refuseList, new GlGiveupComparator());
		}
		return refuseList;
	}

	/**
	 * 保存客户放弃码数据
	 * 2015年12月16日
	 * By 李文勇
	 * @param glGiveup
	 * @return none
	 */
	@Transactional(value = "loanTransactionManager",readOnly = false)
	public void saveData(GlGiveup glGiveup) {
		if( glGiveup != null && glGiveup.getParent() != null 
				&& "".equals(glGiveup.getParent().getId()) ){
			glGiveup.getParent().setId(NumberConstants.ZERO_STRING);
		}
		super.save(glGiveup);
		GiveupCache.getInstance().save(glGiveup);
	}
	
	/**
	 * 删除数据
	 * 2015年12月16日
	 * By 李文勇
	 * @param glGiveup
	 * @return none
	 */
	@Transactional(value = "loanTransactionManager",readOnly = false)
	public void deleteData ( GlGiveup glGiveup ) {
		super.delete(glGiveup);
		GiveupCache.getInstance().remove(glGiveup.getId());
	}
	
	/**
	 * 跟据ID查询数据
	 * 2016年1月29日
	 * By 李文勇
	 * @param id
	 * @return
	 */
	public GlGiveup getById(String id){
		GlGiveup result = GiveupCache.getInstance().get(id);
		return result;
	}
	
	/**
	 * 根据ID获取数据
	 * 2015年12月17日
	 * By 李文勇
	 * @param glGiveup
	 * @return 返回放弃码对象
	 */
	public GlGiveup getById( GlGiveup glGiveup ){
		// 当前节点不为null的场合
		if( glGiveup != null ){ 
			// 当前节点的父节点不为null的场合
			if( glGiveup.getParent() != null ){
				// 当前节点的父节点的ID不为"0"的场合
				if( !NumberConstants.ZERO_STRING.equals(glGiveup.getParent().getId()) ){
					GlGiveup data = GiveupCache.getInstance().get( glGiveup.getParent().getId() );
					glGiveup.getParent().setGiveupName( data.getGiveupName() );
					return glGiveup;
				}else{// 当前节点的父节点的id为"0"的场合，则说明父节点为虚拟根节点
					GlGiveup data = new GlGiveup();
					data.setId(NumberConstants.ZERO_STRING);
					glGiveup.setParent(data);
					return glGiveup;
				}
			}else{// 
				GlGiveup data = new GlGiveup();
				glGiveup.setParent(data);
				return glGiveup;
			}
		}else{
			GlGiveup data = new GlGiveup();
			return data;
		}
	}
	
	/**
	 * 根据参数（放弃码名称  或  放弃码编码 ）查询数据
	 * 2016年1月27日
	 * By 李文勇
	 * @param glGiveup
	 * @return
	 */
	public List<GlGiveup> findByParam(GlGiveup glGiveup){
		List<GlGiveup> result = GiveupCache.getInstance().findByParam(glGiveup);
		return result;
	}
}

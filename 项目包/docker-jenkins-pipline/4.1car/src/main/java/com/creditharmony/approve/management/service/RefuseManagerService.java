package com.creditharmony.approve.management.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.management.cache.RefuseCache;
import com.creditharmony.approve.management.dao.GlRefuseDao;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.management.util.RefuseComparator;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.service.CoreManager;

/**
 * 拒借码维护service
 * @Class Name RefuseManagerService
 * @author 李文勇
 * @Create In 2015年12月22日
 */
@Service
public class RefuseManagerService extends CoreManager<GlRefuseDao, GlRefuse> {

	/**
	 * 查询所有数据
	 * 2015年12月16日
	 * By 李文勇
	 * @return 返回拒绝码list
	 */
	public List<GlRefuse> findAll(){
		List<GlRefuse> refuseList = RefuseCache.getInstance().getList();
		
		List<Dict> dicLis = DictCache.getInstance().getListByType("jk_refuse_type");
		if( ArrayHelper.isNotEmpty(refuseList) ){
			for(int i = 0; i < refuseList.size(); i++){
				String grade = refuseList.get(i).getRefuseGrade();
				for( int j = 0; j < dicLis.size(); j++ ){
					if(StringUtils.isNotEmpty(grade) && grade.equals(dicLis.get(j).getValue())){
						refuseList.get(i).setRefuseGrade(dicLis.get(j).getLabel());
					}
				}
			}
			// 根据码值排序
			Collections.sort(refuseList, new RefuseComparator());
		}
		return refuseList;
	}

	/**
	 * 保存数据
	 * 2016年1月13日
	 * By 李文勇
	 * @param refuse
	 * @return none
	 */
	@Transactional(value = "loanTransactionManager",readOnly = false)
	public void saveData(GlRefuse glRefuse) {
		if( glRefuse != null && glRefuse.getParent() != null 
				&& "".equals(glRefuse.getParent().getId())){
			glRefuse.getParent().setId(NumberConstants.ZERO_STRING);
		}
		super.save(glRefuse);
		RefuseCache.getInstance().save(glRefuse);
	}
	
	/**
	 * 删除数据
	 * 2016年1月13日
	 * By 李文勇
	 * @param refuse
	 * @return none
	 */
	@Transactional(value = "loanTransactionManager",readOnly = false)
	public void deleteData(GlRefuse glRefuse) {
		super.delete(glRefuse);
		RefuseCache.getInstance().remove(glRefuse.getId());
	}
	
	/**
	 * 根据id 获取数据
	 * 2016年1月13日
	 * By 李文勇
	 * @param refuse
	 * @return 返回拒绝码对象
	 */
	public GlRefuse getById(GlRefuse glRefuse){
		// refuse(当前拒绝码本身)不为空的场合
		if( glRefuse != null ){
			// refuse(当前拒绝码的父节点)不为空的场合
			if( glRefuse.getParent() != null ){
				// refuse(当前拒绝码的父节点)不为"0"的场合
				if( !NumberConstants.ZERO_STRING.equals( glRefuse.getParent().getId() ) ){
					GlRefuse data = RefuseCache.getInstance().get( glRefuse.getParent().getId() );
					glRefuse.getParent().setRefuseName( data.getRefuseName() );
					return glRefuse;
				}else{// refuse(当前拒绝码的父节点)为"0"的场合 ,说明父节点是虚拟根节点
					GlRefuse data = new GlRefuse();
					data.setId( NumberConstants.ZERO_STRING );
					glRefuse.setParent(data);
					return glRefuse;
				}
			}else{// 给当前节点的父节点new一个对象
				GlRefuse parent = new GlRefuse();
				glRefuse.setParent(parent);
				return glRefuse;
			}
		}else{ // new 一个当前节点的对象
			GlRefuse data = new GlRefuse();
			return data;
		}
	}
	
	/**
	 * 跟据ID查询数据
	 * 2016年1月29日
	 * By 李文勇
	 * @param id
	 * @return
	 */
	public GlRefuse getById(String id){
		GlRefuse result = RefuseCache.getInstance().get(id);
		return result;
	}
	
	/**
	 * 根据参数查询数据
	 * 2016年1月26日
	 * By 李文勇
	 * @param refuse
	 * @return
	 */
	public List<GlRefuse> findByParam(GlRefuse glRefuse){
		List<GlRefuse> result = RefuseCache.getInstance().findByParam(glRefuse);
		return result;
	}
	
}

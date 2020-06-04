package com.creditharmony.approve.management.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditharmony.approve.management.dao.GlRefuseDao;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.cache.redis.util.RedisUtils;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.cache.BaseCache;
import com.creditharmony.core.persistence.BaseEntity;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class RefuseCache implements BaseCache{

	private static RefuseCache cache = new RefuseCache();
	private static String key = RefuseCache.class.getName();
	
	public static RefuseCache getInstance() {
		return cache;
	}
	
	/**
	 * 获取列表
	 * 2016年1月29日
	 * By 李文勇
	 * @return
	 */
	public List<GlRefuse> getList() {
		Map<String,GlRefuse> dataMap = Maps.newLinkedHashMap();
		dataMap = getMap();
		List<GlRefuse> glRefuseList = Lists.newArrayList();
		for (GlRefuse obj : dataMap.values()) {
			glRefuseList.add((GlRefuse)obj);
	    }
		return glRefuseList;
	}
	
	/**
	 * 获取全部数据（整个表的数据存放到缓存中）
	 * 2016年1月29日
	 * By 李文勇
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,GlRefuse> getMap() {
		Map<String,GlRefuse> refuseMap =  (Map<String,GlRefuse>) RedisUtils.getMap(key);
		if (refuseMap == null) {
			GlRefuseDao glRefuseDao = SpringContextHolder.getBean(GlRefuseDao.class);
			List<GlRefuse> refuseList = glRefuseDao.findAllList(new GlRefuse());
			refuseMap = new HashMap<String,GlRefuse>();
			for(GlRefuse refuse:refuseList){
				refuseMap.put(refuse.getId(), refuse);
			}
			RedisUtils.saveMap(key, refuseMap);
		}
		return refuseMap;
	}
	
	/**
	 * 根据ID获取数据
	 * 2016年1月29日
	 * By 李文勇
	 * @param id
	 * @return 
	 */
	@Override
	public GlRefuse get(String id) {
		Map<String,GlRefuse> refuseMap = getMap();
		if ( null != refuseMap && null != refuseMap.get(id) && refuseMap.get(id) instanceof GlRefuse){
			return (GlRefuse)refuseMap.get(id);
		}
		return null;
	}
	
	/**
	 * 保存数据
	 * 2016年1月29日
	 * By 李文勇
	 * @param obj
	 * @return none
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void save(BaseEntity obj) {
		Map<String,GlRefuse> refuseMap = getMap();
		if (null != obj && obj instanceof GlRefuse && null != refuseMap){
			GlRefuse refuse = (GlRefuse)obj;
			refuse.setParent(get(refuse.getParentId()));
			refuseMap.put(obj.getId(),refuse);
			RedisUtils.saveMap(key,refuseMap);
		}
	}
	
	/**
	 * 删除数据
	 * 2016年1月29日
	 * By 李文勇
	 * @param id
	 * @return
	 */
	@Override
	public void remove(String id) {
		Map<String,GlRefuse> refuseMap = getMap();
		if (null != refuseMap && null != refuseMap.get(id) && refuseMap.get(id) instanceof GlRefuse){
			refuseMap.remove(id);
			RedisUtils.saveMap(key,refuseMap);
		}
	}
	
	/**
	 * 检验是否重复
	 * 2016年1月29日
	 * By 李文勇
	 * @param glRefuse
	 * @return
	 */
	public List<GlRefuse> findByParam(GlRefuse glRefuse){
		
		List<GlRefuse> result = new ArrayList<GlRefuse>();
		
		List<GlRefuse> lis = getList();
		if( lis != null){
			for( int i = 0; i < lis.size(); i++ ){
				if(lis.get(i).getRefuseCode().equals(glRefuse.getRefuseCode())){
					result.add(lis.get(i));
				}
			}
		}
		return result;
	}
}

package com.creditharmony.approve.management.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.creditharmony.approve.management.dao.GlGiveupDao;
import com.creditharmony.approve.management.entity.GlGiveup;
import com.creditharmony.cache.redis.util.RedisUtils;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.cache.BaseCache;
import com.creditharmony.core.persistence.BaseEntity;
import com.google.common.collect.Lists;

public class GiveupCache implements BaseCache{

	private static GiveupCache cache = new GiveupCache();
	private static String key = GiveupCache.class.getName();
	
	public static GiveupCache getInstance() {
		return cache;
	}
	
	/**
	 * 获取列表
	 * 2016年1月29日
	 * By 李文勇
	 * @return
	 */
	public List<GlGiveup> getList() {
		Map<String,GlGiveup> giveupMap = getMap();
		List<GlGiveup> giveupList = Lists.newArrayList();
		if(null != giveupMap){
			for (GlGiveup obj : giveupMap.values()) {
				giveupList.add((GlGiveup)obj);
		    }
		}
		return giveupList;
	}
	
	/**
	 * 获取全部数据（整个表的数据存放到缓存中）
	 * 2016年1月29日
	 * By 李文勇
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,GlGiveup> getMap() {
		Map<String,GlGiveup> giveupMap = (Map<String,GlGiveup>) RedisUtils.getMap(key);
		if (giveupMap == null) {
			GlGiveupDao glGiveupDao = SpringContextHolder.getBean(GlGiveupDao.class);
			List<GlGiveup> giveupList = glGiveupDao.findAllList(new GlGiveup());
			giveupMap = new HashMap<String,GlGiveup>();
			for(GlGiveup refuse:giveupList){
				giveupMap.put(refuse.getId(), refuse);
			}
			RedisUtils.saveMap(key, giveupMap);
		}
		return giveupMap;
	}
	
	/**
	 * 根据ID获取数据
	 * 2016年1月29日
	 * By 李文勇
	 * @param id
	 * @return 
	 */
	@Override
	public GlGiveup get(String id) {
		Map<String,GlGiveup> giveupMap = getMap();
		if ( null != giveupMap && null != giveupMap.get(id) && giveupMap.get(id) instanceof GlGiveup){
			return (GlGiveup)giveupMap.get(id);
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
		Map<String,GlGiveup> giveupMap = getMap();
		if (null != obj && obj instanceof GlGiveup && null != giveupMap){
			GlGiveup giveup = (GlGiveup)obj;
			giveup.setParent(get(giveup.getParentId()));
			giveupMap.put(obj.getId(),giveup);
			RedisUtils.saveMap(key,giveupMap);
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
		Map<String,GlGiveup> giveupMap = getMap();
		if (null != giveupMap && null != giveupMap.get(id) && giveupMap.get(id) instanceof GlGiveup){
			giveupMap.remove(id);
			RedisUtils.saveMap(key,giveupMap);
		}
	}
	
	/**
	 * 检验是否重复
	 * 2016年1月29日
	 * By 李文勇
	 * @param glRefuse
	 * @return
	 */
	public List<GlGiveup> findByParam(GlGiveup glGiveup){
		
		List<GlGiveup> result = new ArrayList<GlGiveup>();
		
		List<GlGiveup> lis = getList();
		if( lis != null){
			for( int i = 0; i < lis.size(); i++ ){
				if(lis.get(i).getGiveupCode().equals(glGiveup.getGiveupCode())){
					result.add(lis.get(i));
				}
			}
		}
		return result;
	}
}

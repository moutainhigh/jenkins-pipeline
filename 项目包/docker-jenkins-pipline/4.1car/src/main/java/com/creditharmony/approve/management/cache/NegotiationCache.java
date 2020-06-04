package com.creditharmony.approve.management.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditharmony.approve.management.dao.NegotiationDao;
import com.creditharmony.approve.management.entity.Negotiation;
import com.creditharmony.cache.redis.util.RedisUtils;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.cache.BaseCache;
import com.creditharmony.core.persistence.BaseEntity;
import com.google.common.collect.Lists;

public class NegotiationCache implements BaseCache{

	private static NegotiationCache cache = new NegotiationCache();
	private static String key = NegotiationCache.class.getName();
	
	public static NegotiationCache getInstance() {
		return cache;
	}
	
	/**
	 * 获取列表
	 * 2016年1月29日
	 * By 李文勇
	 * @return
	 */
	public List<Negotiation> getList() {
		Map<String,Negotiation> negotiationMap = getMap();
		List<Negotiation> negotiationList = Lists.newArrayList();
		if(null != negotiationMap){
			for (Negotiation obj : negotiationMap.values()) {
				negotiationList.add((Negotiation)obj);
		    }
		}
		return negotiationList;
	}
	
	/**
	 * 获取全部数据（整个表的数据存放到缓存中）
	 * 2016年1月29日
	 * By 李文勇
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Negotiation> getMap() {
		Map<String,Negotiation> negotiationMap = (Map<String,Negotiation>) RedisUtils.getMap(key);
		if (negotiationMap == null) {
			NegotiationDao negotiationDao = SpringContextHolder.getBean(NegotiationDao.class);
			List<Negotiation> negotiationList = negotiationDao.findAllList(new Negotiation());
			negotiationMap = new HashMap<String,Negotiation>();
			for(Negotiation negotiation:negotiationList){
				negotiationMap.put(negotiation.getId(), negotiation);
			}
			RedisUtils.saveMap(key, negotiationMap);
		}
		return negotiationMap;
	}
	
	/**
	 * 根据ID获取数据
	 * 2016年1月29日
	 * By 李文勇
	 * @param id
	 * @return 
	 */
	@Override
	public Negotiation get(String id) {
		Map<String,Negotiation> negotiationMap = getMap();
		if ( null != negotiationMap && null != negotiationMap.get(id) && negotiationMap.get(id) instanceof Negotiation){
			return (Negotiation)negotiationMap.get(id);
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
		Map<String,Negotiation> negotiationMap = getMap();
		if (null != obj && obj instanceof Negotiation && null != negotiationMap){
			Negotiation negotiation = (Negotiation)obj;
			negotiation.setParent(get(negotiation.getParentId()));
			negotiationMap.put(obj.getId(),negotiation);
			RedisUtils.saveMap(key,negotiationMap);
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
		Map<String,Negotiation> negotiationMap = getMap();
		if (null != negotiationMap && null != negotiationMap.get(id) && negotiationMap.get(id) instanceof Negotiation){
			negotiationMap.remove(id);
			RedisUtils.saveMap(key,negotiationMap);
		}
	}
	
	/**
	 * 检验是否重复
	 * 2016年1月29日
	 * By 李文勇
	 * @param glRefuse
	 * @return
	 */
	public List<Negotiation> findByParam(Negotiation negotiation){
		
		List<Negotiation> result = new ArrayList<Negotiation>();
		
		List<Negotiation> lis = getList();
		if( lis != null){
			for( int i = 0; i < lis.size(); i++ ){
				if(lis.get(i).getNegotiationCode().equals(negotiation.getNegotiationCode())){
					result.add(lis.get(i));
				}
			}
		}
		return result;
	}
}

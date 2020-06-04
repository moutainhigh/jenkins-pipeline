package com.creditharmony.approve.management.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.management.cache.NegotiationCache;
import com.creditharmony.approve.management.dao.NegotiationDao;
import com.creditharmony.approve.management.entity.Negotiation;
import com.creditharmony.approve.management.util.GlNegotiationComparator;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.service.CoreManager;

/**
 * 客户放弃码维护service
 * @Class Name GlGiveupService
 * @author 李文勇
 * @Create In 2015年12月17日
 */
@Service
public class GlNegotiationManagerService extends CoreManager<NegotiationDao, Negotiation> {

	/**
	 * 查询所有数据
	 * 2015年12月16日
	 * By 李文勇
	 * @return 返回协商码list
	 */
	public List<Negotiation> findAll(){
		List<Negotiation> refuseList = NegotiationCache.getInstance().getList();
		// 根据码值排序
		if(ArrayHelper.isNotEmpty(refuseList)){
			Collections.sort(refuseList, new GlNegotiationComparator());
		}
		return refuseList;
	}

	/**
	 * 保存或更新数据
	 * 2015年12月16日
	 * By 李文勇
	 * @param negotiation
	 * @return none
	 */
	@Transactional(value = "loanTransactionManager",readOnly = false)
	public void saveData(Negotiation negotiation) {
		if( negotiation != null && negotiation.getParent() != null 
				&& "".equals(negotiation.getParent().getId())){
			negotiation.getParent().setId(NumberConstants.ZERO_STRING);
		}
		super.save(negotiation);
		NegotiationCache.getInstance().save(negotiation);;
	}
	
	/**
	 * 删除数据
	 * 2015年12月16日
	 * By 李文勇
	 * @param negotiation
	 * @return none
	 */
	@Transactional(value = "loanTransactionManager",readOnly = false)
	public void deleteData(Negotiation negotiation) {
		super.delete(negotiation);
		NegotiationCache.getInstance().remove(negotiation.getId());
	}
	
	/**
	 * 跟据ID查询数据
	 * 2016年1月29日
	 * By 李文勇
	 * @param id
	 * @return
	 */
	public Negotiation getById(String id){
		Negotiation result = NegotiationCache.getInstance().get(id);
		return result;
	}
	
	
	/**
	 * 根据ID获取数据
	 * 2015年12月17日
	 * By 李文勇
	 * @param negotiation
	 * @return 返回协商码对象
	 */
	public Negotiation getById(Negotiation negotiation){
		if(negotiation != null){
			if(negotiation.getParent() != null){
				if(!NumberConstants.ZERO_STRING.equals(negotiation.getParent().getId() ) ){
					Negotiation data = NegotiationCache.getInstance().get(negotiation.getParent().getId());
					negotiation.getParent().setNegotiationName(data.getNegotiationName());
					return negotiation;
				}else{
					Negotiation data = new Negotiation();
					data.setId(NumberConstants.ZERO_STRING);
					negotiation.setParent(data);
					return negotiation;
				}
			}else{
				Negotiation data = new Negotiation();
				negotiation.setParent(data);
				return negotiation;
			}
		}else{
			Negotiation data = new Negotiation();
			return data;
		}
	}
	
	/**
	 * 根据参数（协商码 或 协商名）查询数据
	 * 2016年1月27日
	 * By 李文勇
	 * @param negotiation
	 * @return
	 */
	public List<Negotiation> findByParam(Negotiation negotiation){
		List<Negotiation> result = NegotiationCache.getInstance().findByParam(negotiation);
		return result;
	}
}

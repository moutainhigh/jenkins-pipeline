package com.creditharmony.approve.verify.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.verify.entity.ex.DictEx;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 字典服务
 * @Class Name ApproveDictDao
 * @author 赖敏
 * @Create In 2015年12月25日
 */
@LoanBatisDao
public interface ApproveDictDao extends CrudDao<Dict>{
	
	/**
	 * 获取外访、回退清单
	 * 2015年12月25日
	 * By 赖敏
	 * @param type 一级类型
	 * @return 对应的字典列表
	 */
    public List<DictEx> getDictsByType(String type);
    
    /**
     * 根据父id和类型获取各个节点的状态
     * 2016年12月25日
     * By 赖敏
     * @param params
     * @return 状态字典列表
     */
    public List<Dict> getByParentId(Map<String, Object> params);
	
}

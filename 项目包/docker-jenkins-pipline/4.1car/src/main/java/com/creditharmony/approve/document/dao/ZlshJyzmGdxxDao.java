package com.creditharmony.approve.document.dao;

import java.util.List;



import com.creditharmony.approve.document.entity.ZlshJyzmGdxx;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 经营地址证明
 * @Class Name ZlshJyzmGdxxDao
 * @author 刘燕军
 * @Create In 2015年12月7日
 */
@LoanBatisDao
public interface ZlshJyzmGdxxDao {
	
	 /**
     * 经营证明插入方法
     * 2015年12月28日
     * By 路志友
     * @param record
     * @return int
     */
	public int insertJyzmGdxx(ZlshJyzmGdxx record);
	
     /**
      * 经营证明插入
      * 2015年12月28日
      * By 路志友
      * @param record
      * @return int
      */
    public int insertSelective(ZlshJyzmGdxx record);
    
    /**
     * 经营证明根据rid查找
     * 2015年12月28日
     * By 路志友
     * @param id
     * @return List
     */
    public List<ZlshJyzmGdxx>  selectByRid(String id);
    
    /**
     * 删除股东信息
     * 2015年12月25日
     * By 路志友
     * @param rid
     * @return int
     */
    public int deleteByRid(String rid);
    
    /**
     * 删除股东信息
     * 2015年12月25日
     * By 路志友
     * @param id
     */
    public void deleteById(String id);

	
   


	
}
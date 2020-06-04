package com.creditharmony.approve.document.dao;

import java.util.List;



import com.creditharmony.approve.document.entity.ZlshSbgjj;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 资料审核  社保公积金
 * @Class Name ZlshSbgjjDao
 * @author 黄维
 * @Create In 2015年12月15日
 */
@LoanBatisDao
public interface ZlshSbgjjDao {
    
	/**
	 * 添加社保公积金
	 * 2015年12月15日
	 * By 黄维
	 * @param zlshSbgjj
	 * @return none
	 */
	public void insertSbgjj(ZlshSbgjj zlshSbgjj);
	
	/**
	 * 修改社保公积金
	 * 2015年12月21日
	 * By 黄维
	 * @param zlshSbgjj
	 * @return none
	 */
	public void updateSbgjj(ZlshSbgjj zlshSbgjj);
	
    /**
     * 查找列表
     * 2015年12月23日
     * By 路志友
     * @param loanCode
     * @return List
     */
	public List<ZlshSbgjj> getListByLoanCode(ZlshSbgjj zlshSbgjj);
    
	/**
	 * 删除射吧公积金
	 * 2015年12月24日
	 * By 路志友
	 * @param id
	 * @return none
	 */
	public void deleteById(String id);
	
}
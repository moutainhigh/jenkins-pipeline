package com.creditharmony.approve.document.dao;

import java.util.List;

import com.creditharmony.approve.document.entity.ZlshZczm;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 资料审核  资产证明 
 * @Class Name ZlshZczmDao
 * @author 刘燕军
 * @Create In 2015年12月7日
 */
@LoanBatisDao
public interface ZlshZczmDao {
    
	/**
	 * 保存资产证明
	 * 2015年12月12日
	 * By 黄维
	 * @param zlshZczm 
	 * @return none
	 */
	public void insertZczm(ZlshZczm zlshZczm);

	/**
	 * 修改资产证明
	 * 2015年12月12日
	 * By 黄维
	 * @param zlshZczm 
	 * @return none
	 */
	public void updateZczm(ZlshZczm zlshZczm);
	
    /**
     * 获取房产信息
     * 2016年1月28日
     * By 路志友
     * @param param
     * @return list
     */
	public List<ZlshZczm> findZlshZczms(VerifyParamEx param);
    
    /**
     *  获得资产证明
     * 2016年1月28日
     * By 路志友
     * @param zlshZczm
     * @return list
     */
	public List<ZlshZczm> getListByLoanCode(ZlshZczm zlshZczm);
    
	/**
	 * 删除资产证明
	 * 2015年12月24日
	 * By 路志友
	 * @param id
	 * @return none
	 */
	public void deleteByPrimaryKey(String id); 
	
	/**
	 * 更新资产证明信息
	 * 2016年6月12日
	 * By 刘燕军
	 * @param zlshZczm
	 * @return 更新的行数
	 */
	public  int  updateByPrimaryKeySelective(ZlshZczm zlshZczm);
}
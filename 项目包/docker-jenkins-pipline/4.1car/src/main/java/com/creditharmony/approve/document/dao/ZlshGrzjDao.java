package com.creditharmony.approve.document.dao;

import java.util.List;

import com.creditharmony.approve.document.entity.ZlshGrzj;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 资料审核  个人证件
 * @Class Name ZlshGrzjDao
 * @author 赵春香
 * @Create In 2016年9月13日
 */
@LoanBatisDao
public interface ZlshGrzjDao {
    
	/**
	 * 保存个人证件
	 * 2015年12月12日
	 * By 黄维
	 * @param zlshGrzj 
	 * @return none
	 */
	public void insertGrzj(ZlshGrzj zlshGrzj);

	/**
	 * 修改个人证件
	 * 2015年12月12日
	 * By 黄维
	 * @param zlshGrzj 
	 * @return none
	 */
	public void updateGrzj(ZlshGrzj zlshGrzj);
	
    /**
     * 获取房产信息
     * 2016年1月28日
     * By 路志友
     * @param param
     * @return list
     */
	public List<ZlshGrzj> findZlshGrzjs(VerifyParamEx param);
    
    /**
     *  获得个人证件
     * 2016年1月28日
     * By 路志友
     * @param zlshGrzj
     * @return list
     */
	public List<ZlshGrzj> getListByLoanCode(ZlshGrzj zlshGrzj);
    
	/**
	 * 删除个人证件
	 * 2015年12月24日
	 * By 路志友
	 * @param id
	 * @return none
	 */
	public void deleteByPrimaryKey(String id); 
	
	/**
	 * 更新个人证件信息
	 * 2016年6月12日
	 * By 刘燕军
	 * @param zlshGrzj
	 * @return 更新的行数
	 */
	public  int  updateByPrimaryKeySelective(ZlshGrzj zlshGrzj);
}
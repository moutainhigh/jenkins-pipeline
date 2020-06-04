package com.creditharmony.approve.document.dao;

import java.util.List;

import com.creditharmony.approve.document.entity.ZlshCczm;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 资料审核  车产证明
 * @Class Name ZlshCczmDao
 * @author 刘燕军
 * @Create In 2015年12月7日
 */
@LoanBatisDao
public interface ZlshCczmDao {
	
	/**
	 * 保存车产证明
	 * 2015年12月14日
	 * By 黄维
	 * @param zlshCczm 
	 * @return none
	 */
	public void insertCczm(ZlshCczm zlshCczm);

	/**
	 * 保存车产证明
	 * 2016年3月25日
	 * By 李文勇
	 * @param zlshCczm
	 * @return 返回操作成功数
	 */
	public int insertSelective(ZlshCczm zlshCczm);
	
	/**
	 * 修改车产证明
	 * 2015年12月14日
	 * By 黄维
	 * @param zlshCczm 
	 * @return none
	 */
	public void updateCczm(ZlshCczm zlshCczm);
	
	/**
	 * 修改车产证明信息
	 * 2016年3月25日
	 * By 李文勇
	 * @param zlshCczm
	 * @return 操作成功数
	 */
	public int updateByPrimaryKeySelective(ZlshCczm zlshCczm);
	
	
    /**
     * 获取符合条件的车产证明
     * 2016年1月18日
     * By 路志友
     * @param parm
     * @return List
     */
	public List<ZlshCczm> findZlshCczms(VerifyParamEx parm);
    
   /**
    * 获取车产证明列表
    * 2015年12月23日
    * By 路志友
    * @param loanCode
    * @return List
    */
	public List<ZlshCczm> getListByLoanCode(ZlshCczm zlshCczm);
    
	/**
	 * 删除车产证明
	 * 2015年12月24日
	 * By 路志友
	 * @param id
	 * @return none
	 */
    public void deleteById(String id);
}
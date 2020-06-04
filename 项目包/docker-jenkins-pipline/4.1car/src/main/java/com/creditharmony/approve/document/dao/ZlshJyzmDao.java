package com.creditharmony.approve.document.dao;

import java.util.List;

import com.creditharmony.approve.document.entity.ZlshJyzm;
import com.creditharmony.approve.document.entity.ex.CompanyInfoEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 资料审核 经营证明
 * @Class Name ZlshJyzmDao
 * @author 刘燕军
 * @Create In 2015年12月7日
 */
@LoanBatisDao
public interface ZlshJyzmDao {
    
	/**
	 * 保存经营证明
	 * 2015年12月14日
	 * By 黄维
	 * @param zlshJyzm 
	 * @return int
	 */
	public int insertJyzm(ZlshJyzm zlshJyzm);

	/**
	 * 修改经营证明
	 * 2015年12月14日
	 * By 黄维
	 * @param zlshJyzm 
	 * @return int
	 */
	public int updateJyzm(ZlshJyzm zlshJyzm);
	
    /**
     * 下载意见书中的企业信息
     * 2016年1月28日
     * By 路志友
     * @param param
     * @return list
     */
	public List<CompanyInfoEx> findCompanyInfo(VerifyParamEx param);
    
    /**
     * 获得列表
     * 2016年1月28日
     * By 路志友
     * @param zlshJyzm
     * @return list
     */
	public List<ZlshJyzm> getListByLoanCode(ZlshJyzm zlshJyzm);
	
    /**
     * 删除经营证明
     * 2015年12月24日
     * By 路志友
     * @param id
     * @return none
     */
	public void deleteById(String id);
	
    /**
     * 查询经营证明
     * 2015年12月24日
     * By 路志友
     * @param id
     * @return ZlshJyzm
     */
	public ZlshJyzm getEntityById(String id);
	
	/**
	 * 查找经营证明列表
	 * 2016年1月18日
	 * By 路志友
	 * @param param
	 * @return List
	 */
	public List<ZlshJyzm> getCautioner(VerifyParamEx param);
	
	/**
	 * 查找经营证明列表
	 * 2016年1月18日
	 * By 路志友
	 * @param param
	 * @return List
	 */
	public List<ZlshJyzm> getCautionerNew(VerifyParamEx param);
	
    /**
     * 关联外访表中，查询经营证明
     * 2015年12月24日
     * By 路志友
     * @param id
     * @return ZlshJyzm
     */
	public ZlshJyzm getEntityWithVisit(String id);	
	
}
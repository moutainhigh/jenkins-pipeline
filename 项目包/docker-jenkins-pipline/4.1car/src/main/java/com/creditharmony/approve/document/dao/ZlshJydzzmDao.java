package com.creditharmony.approve.document.dao;

import java.util.List;



import com.creditharmony.approve.document.entity.ZlshJydzzm;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 资料审核 经营地址证明
 * @Class Name ZlshJydzzmDao
 * @author 黄维
 * @Create In 2015年12月15日
 */
@LoanBatisDao
public interface ZlshJydzzmDao {
	
	/**
	 * 保存经营地址证明
	 * 2015年12月15日
	 * By 黄维
	 * @param zlshJydzzm 
	 * @return none
	 */
    public void insertJydzzm(ZlshJydzzm zlshJydzzm);
    
    /**
	 * 修改经营地址证明
	 * 2015年12月15日
	 * By 黄维
	 * @param zlshJydzzm 
	 * @return none
	 */
    public void updateJydzzm(ZlshJydzzm zlshJydzzm);

	/**
	 * 获取所有经营地址证明
	 * 2015年12月21日
	 * By 黄维
	 * @param loanCode
	 * @return List<ZlshJydzzm>
	 */
	public List<ZlshJydzzm> getListByLoanCode(ZlshJydzzm zlshJydzzm);
    
	/**
	 * 删除经营地址证明
	 * 2015年12月24日
	 * By 路志友
	 * @param id
	 * @return none
	 */
	public void deleteById(String id);
	
	/**
	 * 根据rid删除经营地址
	 * 2016年1月15日
	 * By 路志友
	 * @param Rid
	 * @return none
	 */
	public void deleteByRId(String Rid);
	

}
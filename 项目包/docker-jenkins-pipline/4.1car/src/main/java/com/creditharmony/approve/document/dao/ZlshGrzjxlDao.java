package com.creditharmony.approve.document.dao;

import java.util.List;



import com.creditharmony.approve.document.entity.ZlshGrzjxl;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 资料审核 个人证件学历
 * @Class Name ZlshGrzjxlDao
 * @author 赵春香
 * @Create In 2015年12月15日
 */
@LoanBatisDao
public interface ZlshGrzjxlDao {
	
	/**
	 * 保存个人证件学历
	 * 2015年12月15日
	 * By 黄维
	 * @param zlshGrzjxl 
	 * @return none
	 */
    public void insertGrzjxl(ZlshGrzjxl zlshGrzjxl);
    
    /**
	 * 修改个人证件学历
	 * 2015年12月15日
	 * By 黄维
	 * @param zlshGrzjxl 
	 * @return none
	 */
    public void updateGrzjxl(ZlshGrzjxl zlshGrzjxl);

	/**
	 * 获取所有个人证件学历
	 * 2015年12月21日
	 * By 黄维
	 * @param loanCode
	 * @return List<ZlshGrzjxl>
	 */
	public List<ZlshGrzjxl> getListByLoanCode(ZlshGrzjxl zlshGrzjxl);
    
	/**
	 * 删除个人证件学历
	 * 2015年12月24日
	 * By 路志友
	 * @param id
	 * @return none
	 */
	public void deleteById(String id);
	
	/**
	 * 根据rid删除个人证件学历
	 * 2016年1月15日
	 * By 路志友
	 * @param Rid
	 * @return none
	 */
	public void deleteByRId(String Rid);
	

}
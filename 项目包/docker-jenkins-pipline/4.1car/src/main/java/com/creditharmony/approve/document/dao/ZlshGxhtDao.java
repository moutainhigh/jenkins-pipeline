package com.creditharmony.approve.document.dao;

import java.util.List;

import com.creditharmony.approve.document.entity.ZlshGxht;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 资料审核 购销合同
 * @Class Name ZlshGxhtDao
 * @author 刘燕军
 * @Create In 2015年12月7日
 */
@LoanBatisDao
public interface ZlshGxhtDao {
    
	/**
	 * 保存购销合同
	 * 2015年12月15日
	 * By 黄维
	 * @param zlshGxht 
	 * @return none
	 */
	public void insertGxht(ZlshGxht zlshGxht);

	/**
	 * 修改购销合同
	 * 2015年12月15日
	 * By 黄维
	 * @param zlshGxht 
	 * @return none
	 */
	public void updateGxht(ZlshGxht zlshGxht);
	
    /**
     * 通过借款编号，获取对应的所有的购销合同
     * 2016年1月4日
     * By 路志友
     * @param param
     * @return List
     */
	public List<ZlshGxht> findContractExs(VerifyParamEx param);
	
    /**
     * 购销合同资料
     * 2015年12月23日
     * By 路志友
     * @param loanCode
     * @return List
     */
	public List<ZlshGxht> getListByLoanCode(ZlshGxht zlshGxht);
	
    /**
     * 删除合同资料
     * 2015年12月24日
     * By 路志友
     * @param id
     * @return none
     */
	public void deleteById(String id);
}
package com.creditharmony.approve.carloan.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.carloan.entity.CarCustomerConsultation;
import com.creditharmony.approve.carloan.entity.GrossSpread;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 
 * @Class Name GrossSpreadDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface GrossSpreadDao  extends CrudDao<GrossSpread>{
    int deleteByPrimaryKey(Short rateId);

    int insert(GrossSpread record);

    int insertSelective(GrossSpread record);

    GrossSpread selectByPrimaryKey(Short rateId);

    int updateByPrimaryKeySelective(GrossSpread record);

    int updateByPrimaryKey(GrossSpread record);

    /**
     * 根据 产品类型、产品期限和城市 获取总费率
     * @param map
     * @return
     */
	public GrossSpread findByProductTypeAndDeadline(Map<String, String> map); 
	
	/**
     * 根据借款期限、产品类型查询总费率
     * @param carGrossSpread
     * @return
     */
    public List<GrossSpread> selectCarGross(GrossSpread carGrossSpread);
    
    /**
	 * 查询CarCustomerConsultation 
	 * 2016年3月11日
	 * By ganquan
	 * @param String
	 */
    public CarCustomerConsultation selectByLoanCode(String loanCode);

    /**
	 * 查询展期loanCode
	 * @param String
	 */
    public List<String> selectByLoanCodeList(String loanCode);
}
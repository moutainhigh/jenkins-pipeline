package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditGrade;

/**
 * 信用评级dao
 * @Class Name CreditGradeDao
 * @author 王浩
 * @Create In 2016年2月23日
 */
@LoanBatisDao
public interface CreditGradeDao extends CrudDao<CreditGrade>{
	
    /**
     * 根据主键id删除相应记录
     * 2016年2月23日
     * By 王浩
     * @param id
     * @return 
     */
    int deleteByPrimaryKey(String id);

    /**
     * 新增信用评级记录
     * 2016年2月23日
     * By 王浩
     * @param record
     * @return 
     */
    public int insertCreditGrade(CreditGrade record);
    
    /**
     * 根据封装的信息查找信用评级记录
     * 2016年2月23日
     * By 王浩
     * @param record
     * @return 
     */
    public List<CreditGrade> selectByGradeInfo(CreditGrade record);
    
    /**
     * 根据关联id删除相应记录
     * 2016年2月23日
     * By 王浩
     * @param loanCode
     * @return 
     */
    public int deleteGradeByLoanCode(String loanCode);

    int insertSelective(CreditGrade record);

    CreditGrade selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditGrade record);

    int updateByPrimaryKey(CreditGrade record);
}
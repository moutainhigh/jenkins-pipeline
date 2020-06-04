package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditPunish;

/**
 * 企业征信-处罚Dao
 * @Class Name CreditPunishDao
 * @author 王浩
 * @Create In 2016年2月22日
 */
@LoanBatisDao
public interface CreditPunishDao extends CrudDao<CreditPunish>{
	
    /**
     * 根据id删除记录
     * 2016年2月22日
     * By 王浩
     * @param id
     * @return 删除记录条数
     */
    public int deleteByPrimaryKey(String id);

    /**
     * 保存处罚信息
     * 2016年2月22日
     * By 王浩
     * @param record
     * @return 
     */
    public int insertCreditPunish(CreditPunish record);

    int insertSelective(CreditPunish record);

    CreditPunish selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditPunish record);

    int updateByPrimaryKey(CreditPunish record);
    
    /**
     * 根据loanCode查找相应企业征信-处罚信息
     * 2016年2月22日
     * By 王浩
     * @param punishInfo
     * @return 
     */
    public List<CreditPunish> selectByPunishInfo(CreditPunish punishInfo);    
    
    /**
     * 根据loanCode删除对应的企业征信-处罚信息
     * 2016年2月22日
     * By 王浩
     * @param loanCode
     * @return 
     */
    public int deletePunishByLoanCode(String loanCode);
    
}
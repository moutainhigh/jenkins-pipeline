package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditCivilJudgmentRecord;
/**
 * @Class Name CreditCivilJudgmentRecordDao
 * @author 王浩
 * @Create In 2016年2月23日
 */
@LoanBatisDao
public interface CreditCivilJudgmentRecordDao extends CrudDao<CreditCivilJudgmentRecord>{
	
    /**
     * 2016年2月23日
     * By 王浩
     * @param id
     * @return 
     */
    int deleteByPrimaryKey(String id);

    /**
     * 2016年2月23日
     * By 王浩
     * @param record
     * @return 
     */
    int insertCivilJudgment(CreditCivilJudgmentRecord record);

    int insertSelective(CreditCivilJudgmentRecord record);

    CreditCivilJudgmentRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditCivilJudgmentRecord record);

    int updateByPrimaryKey(CreditCivilJudgmentRecord record);
    
    /**
     * 2016年2月23日
     * By 王浩
     * @param civilJudgment
     * @return 
     */
    public List<CreditCivilJudgmentRecord> selectByJudgmentInfo(CreditCivilJudgmentRecord civilJudgment);
    
    /**
     * 2016年2月23日
     * By 王浩
     * @param loanCode
     * @return 
     */
    public int deleteByLoanCode(String loanCode);
    
}
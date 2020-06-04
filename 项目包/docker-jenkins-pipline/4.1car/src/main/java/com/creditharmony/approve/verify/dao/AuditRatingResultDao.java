package com.creditharmony.approve.verify.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.rule.applyengine.client.RatingParam;
import com.creditharmony.approve.rule.auditrating.client.AuditRatingParam;
import com.creditharmony.approve.verify.entity.AuditRatingResult;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 评分结果数据库操作
 * @Class Name AuditRatingResultDao
 * @author 刘燕军
 * @Create In 2015年12月2日
 */
@LoanBatisDao
public interface AuditRatingResultDao extends CrudDao<AuditRatingResult> {
	
	/**
	 * 插入评分结果信息
	 * 2016年1月27日
	 * By 刘燕军
	 * @param record
	 * @return 插入信息的条数
	 */
    public int insertSelective(AuditRatingResult record);

    /**
     * 通过借款编号，获取对应的评分推荐信息
     * 2015年12月2日
     * By 刘燕军
     * @param loanCode
     * @return
     */
    public AuditRatingResult findByLoanCode(String loanCode);
    
    /**
	 * 进件引擎判定所需的评分数据
	 * 2016年3月23日
	 * By 王浩
	 * @param map
	 * @return 
	 */
	public List<RatingParam> findApplyRatingParam(Map<String, Object> map);
    
    /**
     * 查询决策评分时需要参数
     * 2016年3月21日
     * By 王浩
     * @param map
     * @return 
     */
    public List<AuditRatingParam> findAuditRatingParam(Map<String, Object> map);
    
    /**
     * 更新某些字段
     * 2016年3月21日
     * By 王浩
     * @param record
     * @return 
     */
    public int updateSelective(AuditRatingResult record);    
    
    /**
     * 分别获取贷款审批查询次数、本人查询次数、半年内总查询次数
     * 2016年3月21日
     * By 王浩
     * @param map
     * @return 
     */
    public List<Integer> queryRecordCount(Map<String, Object> map);
    
    /**
     * 发生过逾期的贷款个数
     * 2016年3月21日
     * By 王浩
     * @param map
     * @return 
     */
    public int countLoanOverdue(Map<String, Object> map);
    
    /**
     * 半年内是否有新增贷款或信用卡
     * 2016年3月21日
     * By 王浩
     * @param map
     * @return 
     */
    public boolean newCreditLoanExists(Map<String, Object> map);
    
    /**
     * 正常信用卡额度使用百分比
     * 2016年3月21日
     * By 王浩
     * @param map
     * @return 
     */
    public float selectMaxCreditUseCentage(Map<String, Object> map);
    
    /**
     * 贷款最早账龄
     * 2016年3月21日
     * By 王浩
     * @param map
     * @return 
     */
    public int selectLoanEarliestMonth(Map<String, Object> map);
    
    /**
     * 信用卡最早账龄
     * 2016年3月21日
     * By 王浩
     * @param map
     * @return 
     */
    public int selectCreditEarliestMonth(Map<String, Object> map);
    
    /**
     * 信用卡最近账龄
     * 2016年3月21日
     * By 王浩
     * @param map
     * @return 
     */
    public int selectCreditLatestMonth(Map<String, Object> map);	
    
    /**
     * 通过借款编号，获取对应的评分推荐信息
     * 2015年12月2日
     * By 刘燕军
     * @param loanCode
     * @param checkType
     * @return
     */
    public AuditRatingResult findByParam(String loanCode,String checkType);
	
}
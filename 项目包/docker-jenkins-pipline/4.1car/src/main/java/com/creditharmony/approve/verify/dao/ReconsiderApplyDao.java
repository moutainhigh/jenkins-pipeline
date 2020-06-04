package com.creditharmony.approve.verify.dao;

import java.util.Map;

import com.creditharmony.approve.verify.entity.ReconsiderApply;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 复议申请表相关查询
 * @Class Name ReconsiderApplyDao
 * @author 李文勇
 * @Create In 2015年12月9日
 */
@LoanBatisDao
public interface ReconsiderApplyDao {

    /**
     * 获取复议原因
     * 2015年12月9日
     * By 李文勇
     * @param loanCode
     * @return 返回含有复议原因的对象
     */
    public ReconsiderApply getByLoanCode(String loanCode);
   
    /**
     * 更新复议表中的状态
     * 2015年12月25日
     * By 刘燕军
     * @param apply
     * @return 返回操作成功条数
     */
    public int updateReconsiderApply(ReconsiderApply apply);
    
    /**
     * 
     * 2016年1月8日
     * By 赖敏
     * @param param
     * @return 返回对象
     */
    public ReconsiderApply findReconsiderApply(Map<String,Object> param);
    
    /**
     * 获取是否为复议
     * @param applyId
     * @return 返回含有复议对象
     */
    public ReconsiderApply getByApplyId(String applyId);
}
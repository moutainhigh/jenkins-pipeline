package com.creditharmony.approve.common.dao;

import java.util.List;

import com.creditharmony.approve.common.entity.CustomerAbandon;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 保存客户放弃信息
 * @Class Name CustomerAbandonDao
 * @author 刘燕军
 * @Create In 2015年12月5日
 */
@LoanBatisDao
public interface CustomerAbandonDao extends CrudDao<CustomerAbandon>{
   
    /**
     * 把客户放弃信息保存到数据库
     * 2015年12月23日
     * By 刘燕军
     * @param record
     * @return 插入行数
     */
    public int insertSelective(CustomerAbandon record);
    
    /**
     * 客户放弃查看页面
     * 2015年12月31日
     * By 刘燕军
     * @param id
     * @return 放弃信息
     */
    public CustomerAbandon findCustomerAbandon(String id);
    
    /**
     * 查询所有的一级客户放弃码
     * 2016年5月10日
     * By 刘燕军
     * @param flowView
     * @return 客户放弃信息集合
     */
    public List<String> findAbandonList(VerifyBusinessView  flowView);
}
/**
 * @Probject Name: chp-loan
 * @Path: com.creditharmony.loan.borrow.applyinfo.daoChangerInfoDao.java
 * @Create By 张灏
 * @Create In 2016年6月21日 上午11:41:09
 */
package com.creditharmony.approve.common.dao;

import java.util.List;

import com.creditharmony.approve.common.entity.ChangerInfo;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 退回门店，修改信息
 * @Class Name ChangerInfoDao
 * @author 张灏
 * @Create In 2016年6月21日
 */
@LoanBatisDao
public interface ChangerInfoDao extends CrudDao<ChangerInfo> {
  
    /**
     * 新建变更记录
     * 2016年5月18日
     */
    public void insertChangerInfo(ChangerInfo changerInfo);
    
    /**
     * 根据条件查询变更记录
     * 2016年6月25日
     * By 王浩
     * @param changerInfo
     * @return 
     */
    public List<ChangerInfo> getChangerInfoByParam(ChangerInfo changerInfo);
    
}

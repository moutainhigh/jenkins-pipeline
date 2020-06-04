package com.creditharmony.approve.verify.dao;

import java.util.List;

import com.creditharmony.approve.verify.entity.TelAuditInternetInfo;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 网查信息	
 * @Class Name TelAuditInternetInfoDao
 * @author 刘燕军
 * @Create In 2016年1月19日
 */
@LoanBatisDao
public interface TelAuditInternetInfoDao extends CrudDao<TelAuditInternetInfo>{
    
    /**
     * 插入信息
     * 2015年12月11日
     * By 刘燕军
     * @param record
     * @return
     */
    public int insertSelective(TelAuditInternetInfo record);

    /**
     * 通过id更新json
     * 2015年12月14日
     * By 刘燕军
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(TelAuditInternetInfo record);
	  
    /**
	   * 通过指定条件，获取所有的网查信息
	   * 2015年12月11日
	   * By 刘燕军
	   * @param paramEx
	   * @return
	   */
    public List<TelAuditInternetInfo> findTelAuditInternetInfos(VerifyParamEx paramEx);

}
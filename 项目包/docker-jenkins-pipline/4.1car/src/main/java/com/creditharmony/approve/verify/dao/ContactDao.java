package com.creditharmony.approve.verify.dao;

import java.math.BigDecimal;
import java.util.List;

import com.creditharmony.approve.verify.entity.Contact;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 联系人
 * @Class Name ContactDao
 * @author 李文勇
 * @Create In 2015年12月10日
 */
@LoanBatisDao
public interface ContactDao extends CrudDao<Contact>{
	
	/**
	 * 2016年1月18日
	 * By 李文勇
	 * @param id
	 * @return 返回操作成功数
	 */
	public int deleteByPrimaryKey(BigDecimal id);

	/**
	 * 2016年1月18日
	 * By 李文勇
	 * @param record
	 * @return 返回操作成功数
	 */
    public int insertSelective(Contact record);

    /**
     * 
     * 2016年1月18日
     * By 李文勇
     * @param id
     * @return 返回联系人对象
     */
    public Contact selectByPrimaryKey(BigDecimal id);

    /**
     * 2016年1月18日
     * By 李文勇
     * @param record
     * @return 返回操作成功数
     */
    public int updateByPrimaryKeySelective(Contact record);

    /**
     * 
     * 2016年1月18日
     * By 李文勇
     * @param record
     * @return 返回操作成功数
     */
    public int updateByPrimaryKey(Contact record);
    
    /**
     * 查看弹出画面用
     * 2015年12月10日
     * By 李文勇
     * @param contact
     * @return 返回联系人list
     */
    public List<Contact> viewGetByLoanCode(Contact contact);
    
    /**
     * 查看弹出画面用
     * 2016年10月10日
     * @param contact
     * @return 返回联系人list
     */
    public List<Contact> viewGetByLoanCodeNew(Contact contact);
    
}
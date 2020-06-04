package com.creditharmony.approve.carloan.dao;

import com.creditharmony.approve.carloan.entity.Contract;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 
 * @Class Name ContractDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface ContractDao extends CrudDao<Contract> {
    int deleteByPrimaryKey(String loanCode);

    int insert(Contract record);

    int insertSelective(Contract record);

    Contract selectByPrimaryKey(String loanCode);

    int updateByPrimaryKeySelective(Contract record);

    int updateByPrimaryKey(Contract record);
}
package com.creditharmony.approve.carloan.dao;

import com.creditharmony.approve.carloan.entity.ApplicationInterviewInfo;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 车借_面审信息
 * @Class Name ApplicationInterviewInfoDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface ApplicationInterviewInfoDao {
	public int deleteByPrimaryKey(String loanCode);

	public int insert(ApplicationInterviewInfo record);

	public int insertSelective(ApplicationInterviewInfo record);

	public ApplicationInterviewInfo selectByPrimaryKey(String loanCode);

	public int updateByPrimaryKeySelective(ApplicationInterviewInfo record);

	public int updateByPrimaryKey(ApplicationInterviewInfo record);
}
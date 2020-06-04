package com.creditharmony.approve.verify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.verify.dao.LoanCoborrowerDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;

/**
 * 查看 最初初始化
 * @Class Name InitParamService
 * @author 刘燕军
 * @Create In 2015年12月26日
 */
@Service
public class InitParamService {
	
	@Autowired
	private LoanCoborrowerDao coborrowerDao;
	@Autowired
	private LoanCustomerDao customerDao;
	
	/**
	 * 通过借款编号 获取对应查询参数
	 * 2015年12月15日
	 * By 刘燕军
	 * @param id
	 * @return 返回对应的参数信息
	 */
	public VerifyParamEx findIdCode(String id) {
		return coborrowerDao.findJotinly(id);
	}	
	
	/**
	 *  通过借款编号 获取对应查询参数
	 * 2016年1月19日
	 * By 刘燕军
	 * @param loanCode
	 * @return 返回对应的参数信息
	 */
	public VerifyBusinessView findInfo(String loanCode) {
		return customerDao.findIdCode(loanCode);
	}	
	
}

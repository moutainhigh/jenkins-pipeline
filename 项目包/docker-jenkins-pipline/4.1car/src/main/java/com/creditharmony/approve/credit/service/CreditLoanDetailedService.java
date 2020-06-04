package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.creditharmony.approve.verify.dao.ApproveDictDao;
import com.creditharmony.approve.verify.entity.ex.DictEx;


/**
 * 详版贷款征信服务
 * @Class Name CreditLoanDetailedService
 * @author 侯志斌
 * @Create In 2016年02月01日
 */

@Service
@Transactional(value="loanTransactionManager",readOnly=false)
public class CreditLoanDetailedService {
	
	
	@Autowired
	private ApproveDictDao dictDao;


	
	/**
	 * 获取贷款类型
	 * 2016年01月16日
	 * By 侯志斌
	 * @param type 贷款类型code
	 * @return id 
	 */
	public List<DictEx> getLoanType(String type){
    	return dictDao.getDictsByType(type);
    }
	
	
	
}

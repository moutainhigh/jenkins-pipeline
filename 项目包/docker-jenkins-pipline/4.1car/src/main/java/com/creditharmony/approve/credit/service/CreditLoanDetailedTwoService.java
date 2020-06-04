package com.creditharmony.approve.credit.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.credit.dao.CreditLoanDetailedTwoDao;
import com.creditharmony.approve.credit.entity.CreditLoanDetailedTwo;

/**
 * 贷款明细二服务类
 * @Class Name CreditLoanDetailedTwoService
 * @author 侯志斌
 * @Create In 2016年01月11日
 */
@Service
public class CreditLoanDetailedTwoService {
	@Autowired
	CreditLoanDetailedTwoDao creditLoanDetailedTwoDao;
	
	/**
	 * 根据参数查询所有贷款明细
	 * 2016年02月02日
	 * By 侯志斌
	 * @param params 动态参数
	 * @return 数据列表
	 */
	public List<CreditLoanDetailedTwo> query(Map<String, Object> qureryMap) {
		List<CreditLoanDetailedTwo> dataList = creditLoanDetailedTwoDao.findByParams(qureryMap);
		return dataList;
	}

	/**
	 * 保存贷款明细二
	 * 2016年02月02日
	 * By 侯志斌
	 * @param CreditLoanDetailedTwo
	 * @return String
	 */
	public String save(CreditLoanDetailedTwo model) {
		String id = model.getId();
		if(id == null || id.equals("")){
			model.preInsert();
			creditLoanDetailedTwoDao.insert(model);
			id = model.getId();
		}else{
			
			creditLoanDetailedTwoDao.updateByPrimaryKey(model);
		}
		return id;
	}
}

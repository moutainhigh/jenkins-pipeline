package com.creditharmony.approve.outvisit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.outvisit.dao.OutSideCheckListDao;
import com.creditharmony.approve.outvisit.dao.OutsideDatacheckDao;
import com.creditharmony.approve.outvisit.entity.OutsideCheckList;
import com.creditharmony.approve.outvisit.entity.OutsideDatacheck;
import com.creditharmony.approve.outvisit.entity.ex.OutsideDatacheckEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.approve.type.CheckType;
import com.creditharmony.core.approve.type.CustomerType;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.dict.util.DictUtils;

/**
 * 外访核查service
 * @Class Name OutsideDatacheckService
 * @author wanglidong
 * @Create In 2015年12月7日
 */
@Service
public class OutsideDatacheckService {
	@Autowired
	private OutsideDatacheckDao outsideDatacheckDao;
	@Autowired
	private OutSideCheckListDao outSideCheckListDao;
	
	/**
	 * 获取外访核查数据
	 * 2015年12月7日
	 * By wanglidong
	 * @param param 公共参数
	 * @return 外访实体类
	 */
	public OutsideDatacheckEx getVisitCheckForm(VerifyParamEx param) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("loanCode", param.getLoanCode());
		map.put("checkType", param.getCheckType());
		map.put("type", param.getType());
		map.put("coboId", param.getRelId());
		OutsideDatacheckEx visitCheckForm = outsideDatacheckDao.getOutsideDatacheck(map);
		return  visitCheckForm;
	}
	
	/**
	 * 外访，信审出具外访说明，外访时间，外访距离
	 * 2016年5月9日
	 * By wanglidong
	 * @param param
	 * @return
	 */
	public List<OutsideCheckList> getVisitCheckInfo(VerifyParamEx param) {
		List<OutsideCheckList> visitCheckList = outSideCheckListDao.getCheckListByLoanCode(param);
		return  visitCheckList;
	}
	/**
	 * 获取外访核查字典数据
	 * 2016年5月4日
	 * By wanglidong
	 * @param param
	 * @return
	 */
	public Map<String, Object> getOutVisitDictData() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Dict> workingFamly = DictUtils.getDictList(DictionaryConstants.APPROVE_VISIT_WORK_FAMILY);
		List<Dict> workingWork = DictUtils.getDictList(DictionaryConstants.APPROVE_VISIT_WORK_UNIT);
		List<Dict> businessFamly = DictUtils.getDictList(DictionaryConstants.APPROVE_VISIT_BUSINESS_FAMILY);
		List<Dict> businessWork = DictUtils.getDictList(DictionaryConstants.APPROVE_VISIT_BUSINESS_UNIT);
		String business = DictUtils.getDictValue(CustomerType.ENTERPRISE_OPERATION.getName(),DictionaryConstants.CUSTOMER_TYPE, "");
		String working = DictUtils.getDictValue(CustomerType.SALARIED_PEOPLE.getName(),DictionaryConstants.CUSTOMER_TYPE, "");
		String xsname = CheckType.XS_FIRST_CREDIT_AUDIT.getName();
		String fyname = CheckType.FY_FIRST_CREDIT_AUDIT.getName();
		map.put("workingFamly", workingFamly);
		map.put("workingWork", workingWork);
		map.put("businessFamly", businessFamly);
		map.put("businessWork", businessWork);
		map.put("business", business);
		map.put("working", working);
		map.put("xsname", xsname);
		map.put("fyname", fyname);
		return map;
	}

	/**
	 * 添加外访核查
	 * 2015年12月7日
	 * By wanglidong
	 * @param outsideDatacheck 外访实体类
	 * @param param 公共参数
	 * @return
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public String saveVisitCheck(OutsideDatacheck outsideDatacheck,VerifyParamEx param) {
		outsideDatacheck.preInsert();
		outsideDatacheck.setDictCheckType(param.getCheckType());
		outsideDatacheck.setLoanCustomterType(param.getType());
		outsideDatacheck.setrCustomerCoborrowerId(param.getRelId());
		outsideDatacheckDao.insertVisitCheck(outsideDatacheck);
		return outsideDatacheck.getId();
	}

	/**
	 * 修改外访核查
	 * 2016年1月8日
	 * By wanglidong
	 * @param outsideDatacheck 外访实体类
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public void updateVisitCheck(OutsideDatacheck outsideDatacheck) {
		outsideDatacheck.preUpdate();
		outsideDatacheckDao.updateVisitCheck(outsideDatacheck);
	}
}

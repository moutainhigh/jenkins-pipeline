package com.creditharmony.approve.newCar.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.newCar.common.NewCarSystemConfig;
import com.creditharmony.approve.newCar.dao.NewGrossSpreadDao;
import com.creditharmony.approve.newCar.entity.NewCarCustomerConsultation;
import com.creditharmony.approve.newCar.service.NewCarContractVersionService;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.ListUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.dict.entity.Dict;

@Service
@Transactional(readOnly = true, value = "loanTransactionManager")
public class NewCarContractVersionImpl implements NewCarContractVersionService{

	@Autowired
	private NewGrossSpreadDao grossSpreadDao;
	
	public String getFlowContractVersion(String loanCode,String isextensionId){
		if(StringUtils.isNotEmpty(isextensionId) && isextensionId.equals("1")){
			 List<String> l = grossSpreadDao.selectByLoanCodeList(loanCode);
			 if(ListUtils.isNotEmptyList(l)){
				 loanCode = l.get(0);
			 }
		}
		String contractVer = "1.4";
		NewCarCustomerConsultation carCustomerConsultation = grossSpreadDao.selectByLoanCode(loanCode);
		if(carCustomerConsultation!=null){
			Date consultTime = carCustomerConsultation.getCreateTime();
	  	    Date onDate = DateUtils.convertStringToDate(NewCarSystemConfig.CAR_LOAN_DATE);
	  	    Date onLineDate = DateUtils.convertStringToDate(NewCarSystemConfig.CAR_LOAN_ONLINE_DATE);
	  	    List<Dict> dlist = DictCache.getInstance().getListByType("jk_car_contract_version");
	  	    if(null!=dlist&&dlist.size()>0){
	  	    	if(DateUtils.dateAfter(consultTime,onDate)){//根据咨询时间来判断 合同版本号 1.6
	  	    		contractVer = dlist.get(dlist.size()-1).getLabel();
				}else if(DateUtils.dateAfter(consultTime,onLineDate)){//根据咨询时间来判断 合同版本号 
	  	    		contractVer = dlist.get(dlist.size()-2).getLabel();
				}else{//老版本号1.4
					contractVer = dlist.get(dlist.size()-3).getLabel();
				}
	  		}
		}
		return contractVer;
	}
}

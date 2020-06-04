package com.creditharmony.approve.newCar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.carloan.service.GrossSpreadService;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.core.web.BaseController;

/**
 * 车借公共 
 * @Class Name CommonController
 * @author 李高远
 * @Create In 2017年4月7日
 */
@Controller
@RequestMapping(value = "${adminPath}/carloan/common")
public class NewCommonController extends BaseController{
	
	@Autowired
	private GrossSpreadService grossSpreadService;

	/**
	 * 根据产品类型、产品期限、城市(通过借款编码获取机构，再获取机构 城市)获取总费率
	 * 2017年4月7日
	 * By 李高远
	 * @param productTypeName  产品类型
	 * @param productTypeMonths 产品期限
	 * @param loanCode 借款编码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "asynLoadGrossRate", method = RequestMethod.POST)
	public Double asynLoadGrossRate(String productTypeName, String productTypeMonths, String loanCode,String isextensionId){
		Double grossSpread = grossSpreadService.getCarGrossSpread(productTypeMonths, productTypeName,loanCode,isextensionId);
		return ObjectHelper.isEmpty(grossSpread)?null: grossSpread;
	}
}

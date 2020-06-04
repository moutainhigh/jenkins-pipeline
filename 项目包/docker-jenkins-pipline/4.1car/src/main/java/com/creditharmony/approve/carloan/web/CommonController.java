package com.creditharmony.approve.carloan.web;

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
 * @author 李静辉
 * @Create In 2016年2月25日
 */
@Controller
@RequestMapping(value = "${adminPath}/carloanOld/common")
public class CommonController extends BaseController{
	
	@Autowired
	private GrossSpreadService grossSpreadService;

	/**
	 * 根据产品类型、产品期限、城市(通过借款编码获取机构，再获取机构 城市)获取总费率
	 * 2016年2月25日
	 * By 李静辉
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

package com.creditharmony.approve.credit.web;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.entity.CreditReportRisk;
import com.creditharmony.approve.common.service.CityInfoService;
import com.creditharmony.approve.credit.entity.CreditLiveInfo;
import com.creditharmony.approve.credit.entity.CreditOccupationInfo;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
import com.creditharmony.approve.credit.entity.ex.DetailedParamEx;
import com.creditharmony.approve.credit.service.CreditDetailedInfoService;
import com.creditharmony.approve.credit.service.CreditRiskReportService;
import com.creditharmony.approve.credit.service.CreditReportSimpleService;
import com.creditharmony.approve.verify.entity.LoanCoborrower;
import com.creditharmony.approve.verify.entity.LoanCustomer;
import com.creditharmony.approve.verify.service.ReconsiderFinalExamineService;

/**
 * 详版个人信息
 * @Class Name CreditDetailedInfoController
 * @author 李文勇
 * @Create In 2016年2月16日
 */
@Controller
@RequestMapping(value = "${adminPath}/creditdetailed/info")
public class CreditDetailedInfoController extends BaseController{
	
	@Autowired
	private CityInfoService cityInfoService;
	@Autowired
	private CreditDetailedInfoService creditDetailedInfoService;
	@Autowired
	private CreditReportSimpleService creditReportSimpleService;
	@Autowired
	private CreditRiskReportService creditReportService;
	@Autowired
	private ReconsiderFinalExamineService reconsiderService;
	/**
	 * 显示画面
	 * 2016年2月16日
	 * By 李文勇
	 * @return
	 */
	@RequestMapping(value="detail")
	public String detail(Model model, CreditReportRisk creditReportRisk, String customerType, String version) {
		creditReportRisk.setDictCustomerType(customerType);
		creditReportRisk.setRiskCreditVersion(version);
		creditReportRisk.setCustomerName(creditDetailedInfoService.getCustomerName(creditReportRisk));
		List<CreditReportRisk> creditInfoDetailedList = creditDetailedInfoService.getSingleCreditDetailedByCustomer(creditReportRisk);
		
		// 如果为共借人，获取共借人身份证号
		if(LoanManFlag.COBORROWE_LOAN.getCode().equals(customerType)){
			LoanCoborrower result = creditDetailedInfoService.selectCoboNameAndCertNum(
					creditReportRisk.getLoanCode(),creditReportRisk.getrId());
			if(result != null){
				model.addAttribute("applyCertNum", result.getCoboCertNum());
			}
		}
		// 如果为主借人，获取主借人身份证号
		if(LoanManFlag.MAIN_LOAN.getCode().equals(customerType)){
			LoanCustomer result = creditDetailedInfoService.getCustomer(
					creditReportRisk.getLoanCode());
			if(result != null){
				model.addAttribute("applyCertNum", result.getCustomerCertNum());
			}
		}
		
		if (ArrayHelper.isNotEmpty(creditInfoDetailedList)) {
			model.addAttribute("param", creditReportRisk);
			return "credit/detail";
		} else { 
			return "credit/detail_empty";
		}		
		
	}
	
	@ResponseBody
	@RequestMapping(value = "getCreditList")
	public List<CreditReportRisk> getCreditList(String loanCode){
		return creditReportService.getCreditList(loanCode);
	}
	
	/**
	 * 获取征信核查列表
	 * 2016年3月16日
	 * By 李文勇
	 * @param loanCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getRiskList")
	public List<CreditReportRisk> getRiskList(String loanCode){
		
		CreditReportRisk creditReportRisk = new CreditReportRisk();
		creditReportRisk.setLoanCode(loanCode);
		List<CreditReportRisk> riskList = creditDetailedInfoService.getCreditReportDetailedByCode(creditReportRisk);
		
		return riskList;
		
	}
	
	/**
	 * 显示画面
	 * 2016年2月16日
	 * By 李文勇
	 * @return
	 */
	@RequestMapping(value="initPage")
	public String initPage(Model model){
		List<CityInfo> provinceList = cityInfoService.findProvince();
		model.addAttribute("provinceList",provinceList);
		return "credit/personalIdentityInformation";
	}
	
	/**
	 * 显示数据
	 * 2016年2月18日
	 * By 李文勇
	 * @return DetailedParamEx
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@ResponseBody
	@RequestMapping(value="showData")
	public DetailedParamEx showData(CreditReportDetailed param) throws IllegalAccessException, InvocationTargetException{
		DetailedParamEx result = creditDetailedInfoService.showData(param);
		return result;
	}
	
	/**
	 * 保存数据
	 * 2016年2月17日
	 * By 李文勇
	 * @return String
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@ResponseBody
	@RequestMapping(value="saveData")
	public String savaData(DetailedParamEx param) throws IllegalAccessException, InvocationTargetException{
		creditDetailedInfoService.saveData(param);
		return "true";
	}
	
	/**
	 * 删除居住信息
	 * 2016年2月19日
	 * By 李文勇
	 * @param param
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="removeReportHouse")
	public String removeReportHouse(CreditLiveInfo param){
		int result = creditDetailedInfoService.removeReportHouse(param);
		if(result > 0){
			return "true";
		}
		return "false";
	}
	
	/**
	 * 删除职业信息
	 * 2016年2月19日
	 * By 李文勇
	 * @param param
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="removeReportWork")
	public String removeReportWork(CreditOccupationInfo param){
		int result = creditDetailedInfoService.removeReportWork(param);
		if(result > 0){
			return "true";
		}
		return "false";
	}
}

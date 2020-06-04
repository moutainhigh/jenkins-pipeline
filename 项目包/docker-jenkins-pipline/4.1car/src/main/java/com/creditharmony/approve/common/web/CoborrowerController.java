package com.creditharmony.approve.common.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.entity.LoanInfo;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.DiskInfoService;
import com.creditharmony.approve.verify.service.InitParamService;
import com.creditharmony.approve.verify.util.ImagePlatformUtil;
import com.creditharmony.approve.verify.view.DiskInfoView;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.approve.type.CheckType;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.config.Global;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.web.BaseController;

/**
 * 共借人初始
 * @Class Name CheckController
 * @author 刘燕军
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping(value = "${adminPath}/coborrower")
public class CoborrowerController extends  BaseController{
	@Autowired
	private InitParamService initService;
	@Autowired 
	private LoanInfoDao loanInfoDao;
	@Autowired
	private DiskInfoService diskInfoService;

	/**
	 * 共借人初始化
	 * 2015年12月2日
	 * By 刘燕军
	 * @param model
	 * @param id
	 * @param checkTyp
	 * @param outFlag
	 * @return 初始化的页面
	 * @throws Exception 
	 */
	@RequestMapping(value="initPage")
	public String initPage(Model model, String id, String checkTyp, String outFlag, String stepNamea) throws Exception{
		logger.info("进入共借人编辑初始化页面 initPage  id="+id+"checkType="+checkTyp);
		VerifyParamEx param = initService.findIdCode(id);
		param.setCheckType(checkTyp);
		param.setType(LoanManFlag.COBORROWE_LOAN.getCode());
		String stepName = "";
		if(checkTyp.equals(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode())) {
			stepName = CheckType.XS_FIRST_CREDIT_AUDIT.getName();
		}
		if(checkTyp.equals(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode())) {
			stepName = CheckType.FY_FIRST_CREDIT_AUDIT.getName();
		}
		logger.info("loanCode="+param.getLoanCode());
		LoanInfo loanInfo = loanInfoDao.viewGetByLoanCode(param.getLoanCode());
		logger.info("CustomerIntoTime="+loanInfo.getCustomerIntoTime());
		String queryDate = DateUtils.formatDate(loanInfo.getCustomerIntoTime(), "yyyyMMdd");
		DiskInfoView diskInfoView = diskInfoService.findDiskName(queryDate, loanInfo.getDictSourceType());
		
		param.setSunyardUrl(ImagePlatformUtil.appendUrlParm(stepName, Global.getConfig(ApproveConstants.CHP3_0XYD_INFO), param.getLoanCode(), 
				diskInfoView, queryDate, null));
		model.addAttribute("outFlag",outFlag);
		String sex = DictCache.getInstance().getDictLabel(DictionaryConstants.DICT_GENDER,param.getCustomerSex());
		param.setCustomerSex(sex);
		// 历史归户信息概况显示信息  message
		model.addAttribute("jointlyParam", param);
		model.addAttribute("stepName", stepNamea);
		return "/frame/frameFormCoborrower";		
	}
	
	/**
	 * 查看页面共借人
	 * 2015年12月29日
	 * By 刘燕军
	 * @param model
	 * @param id
	 * @param checkTyp
	 * @param outFlag
	 * @return 共借人页面
	 */
	@RequestMapping(value="initCoborrower")
	public String initCoborrower(Model model, String id, String checkTyp, String outFlag, String stepNamea){
		VerifyParamEx param = initService.findIdCode(id);
		param.setCheckType(checkTyp);
		param.setType(LoanManFlag.COBORROWE_LOAN.getCode());
/*		String stepName = "";
		if(checkTyp.equals(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode())) {
			stepName = CheckType.XS_FIRST_CREDIT_AUDIT.getName();
		}
		if(checkTyp.equals(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode())) {
			stepName = CheckType.FY_FIRST_CREDIT_AUDIT.getName();
		}*/		
		LoanInfo loanInfo = loanInfoDao.viewGetByLoanCode(param.getLoanCode());
		String queryDate = DateUtils.formatDate(loanInfo.getCustomerIntoTime(), "yyyyMMdd");
		DiskInfoView diskInfoView = diskInfoService.findDiskName(queryDate, loanInfo.getDictSourceType());
		Map<String, String> stepRead = ImagePlatformUtil.getStepAndReadOnly(stepNamea);
		
		param.setSunyardUrl(ImagePlatformUtil.appendUrlParm(stepRead.get("stepName"), Global.getConfig(ApproveConstants.CHP3_0XYD_INFO), param.getLoanCode(), 
				diskInfoView, queryDate, stepRead.get("readOnly")));
		model.addAttribute("outFlag",outFlag);
		String sex = DictCache.getInstance().getDictLabel(DictionaryConstants.DICT_GENDER,param.getCustomerSex());
		param.setCustomerSex(sex);
		// 历史归户信息概况显示信息  message
		model.addAttribute("jointlyParam", param);
		
		return "/frame/frameViewCoborrower";	
	}
	
}

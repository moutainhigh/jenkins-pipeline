package com.creditharmony.approve.verify.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.creditharmony.approve.common.constants.ApproveConstants;
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
@RequestMapping(value = "${adminPath}/jointly")
public class JointlyCheckController extends  BaseController{
	@Autowired
	private InitParamService initService;
	@Autowired 
	private LoanInfoDao loanInfoDao;
	@Autowired
	private DiskInfoService diskInfoService;
	/**
	 * 
	 * 2015年12月2日
	 * By 刘燕军
	 * @param model
	 * @param loanCode
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="initParam")
	public String initParam(Model model,String id,String checkType) throws Exception{
		VerifyParamEx param = initService.findIdCode(id);
		param.setCheckType(checkType);
		param.setType(LoanManFlag.COBORROWE_LOAN.getCode());
		// 历史归户信息概况显示信息  message
		model.addAttribute("jointlyParam", param);
		return "/approve/verify/verifyJointlyForm";		
	}
	/**
	 * 查看页面共借人
	 * 2015年12月29日
	 * By 刘燕军
	 * @param model
	 * @param id
	 * @param checkType
	 * @return
	 */
	@RequestMapping(value="initJointly")
	public String initJointly(Model model,String id,String checkType){
		VerifyParamEx param = initService.findIdCode(id);
		param.setCheckType(checkType);
		param.setType(LoanManFlag.COBORROWE_LOAN.getCode());
		String stepName = "";
		if(checkType.equals(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode())) stepName = CheckType.XS_FIRST_CREDIT_AUDIT.getName();
		if(checkType.equals(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode())) stepName = CheckType.FY_FIRST_CREDIT_AUDIT.getName();
		LoanInfo loanInfo = loanInfoDao.viewGetByLoanCode(param.getLoanCode());
		String queryDate = DateUtils.formatDate(loanInfo.getCustomerIntoTime(), "yyyyMMdd");
		DiskInfoView diskInfoView = diskInfoService.findDiskName(queryDate, loanInfo.getDictSourceType());
		param.setSunyardUrl(ImagePlatformUtil.appendUrlParm(stepName, Global.getConfig(ApproveConstants.CHP3_0XYD_INFO), param.getLoanCode(),
				diskInfoView, queryDate, null));
		
		// 历史归户信息概况显示信息  message
		model.addAttribute("jointlyParam", param);
		
		return "/frame/frameViewCoborrower";	
	}

}

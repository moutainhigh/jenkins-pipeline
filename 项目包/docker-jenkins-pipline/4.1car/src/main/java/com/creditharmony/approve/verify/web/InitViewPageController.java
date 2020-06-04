package com.creditharmony.approve.verify.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.entity.LoanInfo;
import com.creditharmony.approve.verify.service.DiskInfoService;
import com.creditharmony.approve.verify.service.InitParamService;
import com.creditharmony.approve.verify.util.ImagePlatformUtil;
import com.creditharmony.approve.verify.view.DiskInfoView;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.config.Global;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.web.BaseController;

/**
 * 查看历史页面初始化参数
 * @Class Name InitViewPageController
 * @author 刘燕军
 * @Create In 2015年12月28日
 */
@Controller
@RequestMapping(value = "${adminPath}/verify/view")
public class InitViewPageController extends BaseController{
	
	@Autowired
	private InitParamService initParamService;
	@Autowired 
	private LoanInfoDao loanInfoDao;
	@Autowired
	private DiskInfoService diskInfoService;
	
	@RequestMapping(value="initPage")
	public String initViewParam(Model model,String loanCode){
		if(StringUtils.isNotBlank(loanCode)){
			VerifyBusinessView param = initParamService.findInfo(loanCode);
			param.setType(LoanManFlag.MAIN_LOAN.getCode());
			param.setCheckType(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode());
			WorkItemView work = new WorkItemView();
			String sex = DictCache.getInstance().getDictLabel(DictionaryConstants.DICT_GENDER,param.getCustomerSex());
			param.setCustomerSex(sex);
			work.setBv(param);
			model.addAttribute("workItem", work);	

			LoanInfo loanInfo = loanInfoDao.viewGetByLoanCode(param.getLoanCode()); 
			//為空時  只查看匯金上傳的資料
			String queryDate = DateUtils.formatDate(loanInfo.getCustomerIntoTime(), "yyyyMMdd");
			DiskInfoView diskInfoView = diskInfoService.findDiskName(queryDate, loanInfo.getDictSourceType());
			Map<String, String> stepRead = ImagePlatformUtil.getStepAndReadOnly(null);
			param.setSunyardUrl(ImagePlatformUtil.appendUrlParm(stepRead.get("stepName"), Global.getConfig(ApproveConstants.CHP3_0XYD_INFO), param.getLoanCode(),
					diskInfoView, queryDate, stepRead.get("readOnly")));			
		}
		return "/frame/frameView";	
	}

}

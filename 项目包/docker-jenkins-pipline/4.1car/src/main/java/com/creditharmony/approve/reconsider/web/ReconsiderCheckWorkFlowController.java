/**
 * @Probject Name: chp-approve
 * @Path: com.creditharmony.approve.reconsider.webReconsiderCheckWorkFlowController.java
 * @Create By 黄维
 * @Create In 2015年11月30日 下午5:39:43
 */
package com.creditharmony.approve.reconsider.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.creditharmony.approve.base.web.HandleController;
import com.creditharmony.approve.common.constants.RefuseConstants;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.verify.entity.AuditResult;
import com.creditharmony.approve.verify.entity.ex.FirstCheckEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.CheckService;
import com.creditharmony.approve.verify.service.VerifyCommonService;
import com.creditharmony.approve.verify.util.SurveyUtil;
import com.creditharmony.approve.workflow.constants.ApproveRouteConstants;
import com.creditharmony.approve.workflow.constants.StepNameConstants;
import com.creditharmony.approve.workflow.constants.WorkFlowConstants;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.config.FlowInfoDefinitionConfig;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.approve.type.ChkResult;
import com.creditharmony.core.common.type.BooleanType;

/**
 * 复议初审处理类
 * @Class Name FristAuditWorkFlowController
 * @author 刘燕军
 * @Create In 2015年12月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/reconsider/check")
public class ReconsiderCheckWorkFlowController extends HandleController {
	@Autowired
	private CheckService firstVerifyService;
	@Autowired
	private VerifyCommonService verifyCommonService;

	public ReconsiderCheckWorkFlowController() {
		super(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode(),
				StepNameConstants.RECONSIDER_CHECK, FlowInfoDefinitionConfig
						.getInstance()
						.getFlowInfoConfigBeanByCode(WorkFlowConstants.FLOWNAME_RECONSIDER_KEY)
						.getFlowName());
	}
	
	/**
	 * 初审决策页签初始化
	 * 2015年12月2日
	 * By 刘燕军
	 * @param model
	 * @param loanCode
	 * @return 初始化后的页面
	 * @throws Exception 
	 */
	@RequestMapping(value="initPage")
	public String initPage(Model model,VerifyParamEx param,int result) throws Exception{
		String loanCode = param.getLoanCode();
		List<GlRefuse> glRefuses = null;
		FirstCheckEx  checkView = null;
		param.setRelId(null);
		if(!StringUtils.isBlank(loanCode)){
			param.setRelId(null);
			param.setType(null);
			checkView = firstVerifyService.getCheckInfo(param);
			// 初审不存在审核结果
			if(checkView.getAuditResult()==null){
				// 获取外访发出的实际经营地址
				AuditResult auditResult=firstVerifyService.getVisitAddress(param);
				if(auditResult!=null){
					// 根据省获取市
					if(StringUtils.isNotEmpty(auditResult.getEnsuremanBusinessProvince())){
						checkView.setCityList(cityInfoService.findCity(auditResult.getEnsuremanBusinessProvince()));
					}
					// 根据市获取区
					if(StringUtils.isNotEmpty(auditResult.getEnsuremanBusinessCity())){
						checkView.setDistrictList(cityInfoService.findDistrict(auditResult.getEnsuremanBusinessCity()));
					}
					checkView.setAuditResult(auditResult);
				}
			}
			glRefuses = getRefuseCode(RefuseConstants.ROOT);
		}else{
			throw new Exception("借款编号为空");
		}		
		// 获取新版旧版标识
		String newOrOldFlag = verifyCommonService.getOldornewFlag(loanCode);
		// 历史归户信息概况显示信息  message
		model.addAttribute("glRefuses", glRefuses);
		model.addAttribute("message", SurveyUtil.getMessage(checkView.getCustomerHis()));		
		model.addAttribute("InnerCheck",checkView );	
		model.addAttribute("checkType", param.getCheckType());
		model.addAttribute("result", result);
		model.addAttribute("newOrOldFlag", newOrOldFlag);
		return "/reconsider/checkInclude";		
	}
	
	/**
	 * 复议初审决策
	 * 2015年12月24日
	 * xiaoniu.hu
	 * @param workItem
	 * @param loanApply
	 * @return 决策是否成功
	 */
	@RequestMapping(value="asynCheckOperation")
	@ResponseBody
	public String asynCheckOperation(WorkItemView workItem,VerifyBusinessView loanApply,String auditRulesCode){
		try {
			String result = loanApply.getAuditResultEx().getAuditResult();
			loanApply.getAuditResultEx().setAuditRulesCode(auditRulesCode);
			if(!StringUtils.isBlank(result)){
				if(ChkResult.THROUGH.getCode().equals(result)){ // 通过
					loanApply.setAuditAmount(loanApply.getAuditResultEx().getAuditAmount().doubleValue());
					workItem.setResponse(ApproveRouteConstants.CHECK_TO_RECHECK);
				}else if(ChkResult.REFUSE_TO_BORROW.getCode().equals(result)){ // 拒借
					workItem.setResponse(ApproveRouteConstants.CHECK_TO_END);
				}else if(ChkResult.TO_REFUSE_TO_BORROW.getCode().equals(result)){ // 拟拒借
					workItem.setResponse(ApproveRouteConstants.CHECK_PLAN_REFUSED);
				}else{
					return BooleanType.FALSE;
				}				
				workItem.setBv(loanApply);
				flowService.dispatch(workItem);
			}else{
				return BooleanType.FALSE;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 获取对应的拒绝码
	 * 2016年1月18日
	 * By 刘燕军
	 * @param id
	 * @return 拒绝码信息
	 */
	@RequestMapping(value="getOtherGlRefuses" , method=RequestMethod.POST)
	@ResponseBody
	public String getOtherGlRefuses(String id){	
		List<GlRefuse> glRefuses = null;
		try{
			glRefuses = getRefuseCode(id);
		}catch(Exception e){
			logger.error(e.getMessage());
			return null;
		}
		return jsonMapper.toJson(glRefuses);
	}
	
}


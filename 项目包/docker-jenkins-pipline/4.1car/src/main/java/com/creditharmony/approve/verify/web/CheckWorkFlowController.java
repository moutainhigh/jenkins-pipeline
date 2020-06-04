package com.creditharmony.approve.verify.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.base.web.HandleController;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.common.constants.RefuseConstants;
import com.creditharmony.approve.document.entity.ZlshJyzm;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.rule.auditrating.service.AuditRatingService;
import com.creditharmony.approve.verify.entity.AuditResult;
import com.creditharmony.approve.verify.entity.AuditResultSublist;
import com.creditharmony.approve.verify.entity.ex.AuditResultEx;
import com.creditharmony.approve.verify.entity.ex.FirstCheckEx;
import com.creditharmony.approve.verify.entity.ex.InnerCheckEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.CheckService;
import com.creditharmony.approve.verify.service.VerifyCommonService;
import com.creditharmony.approve.verify.util.SurveyUtil;
import com.creditharmony.approve.workflow.constants.ApproveRouteConstants;
import com.creditharmony.approve.workflow.constants.FieldConstants;
import com.creditharmony.approve.workflow.constants.QueueConstants;
import com.creditharmony.approve.workflow.constants.StepNameConstants;
import com.creditharmony.approve.workflow.constants.WorkFlowConstants;
import com.creditharmony.approve.workflow.entity.VerifyFlowTaskItemEntity;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.config.FlowInfoDefinitionConfig;
import com.creditharmony.bpm.frame.utils.Constant;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.approve.type.ChkResult;
import com.creditharmony.core.common.type.BooleanType;
import com.query.ProcessQueryBuilder;


/**
 * 信审初审处理类
 * @Class Name FristAuditWorkFlowController
 * @author 刘燕军
 * @Create In 2015年12月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/verify/check")
public class CheckWorkFlowController extends HandleController {	
	@Autowired
	private CheckService firstVerifyService;
		
	@Autowired
	private AuditRatingService auditRatingService;	
	@Autowired
	private VerifyCommonService verifyCommonService;
		
	public CheckWorkFlowController() {
		super(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode(),
				StepNameConstants.VERIFY_CHECK, FlowInfoDefinitionConfig
						.getInstance().getFlowInfoConfigBeanByCode(WorkFlowConstants.FLOWNAME_VERIFY_KEY)
						.getFlowName());
	}
	
	/**
	 * 重写初审待办
	 */
	@Override
	protected String fetchTaskItems(Model model) {
		// 通过工具类把查询的实体转换为对应的ProcessQueryBuilder参数
		ProcessQueryBuilder queryParam = new ProcessQueryBuilder();
		queryParam.setFlowName(FlowInfoDefinitionConfig.getInstance().getFlowInfoConfigBeanByCode(WorkFlowConstants.FLOWNAME_VERIFY_KEY).getFlowName());
		queryParam.put(FieldConstants.STEP_NAME, StepNameConstants.VERIFY_CHECK);
		// 获取工作流任务
		FlowPage page=new FlowPage();
		page.setPageSize(30);
		flowService.fetchTaskItems(QueueConstants.VERIFY_CHECK, queryParam,page,
				Constant.FLOW_FRAME_BASKET_FETCH_MODEL_HUICHENG_FIRST_AUDIT,
				VerifyFlowTaskItemEntity.class);
		model.addAttribute("itemList", page.getList());
		return "task/taskList";		
	}



	/**
	 * 初审决策页签初始化
	 * 2015年12月2日
	 * By 刘燕军
	 * @param model
	 * @param param
	 * @return 返回初始化后的页面
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
		return "/verify/checkInclude";		
	}
	
	/**
	 * 决策页面查看
	 * 2015年12月26日
	 * By 刘燕军
	 * @param model
	 * @param param
	 * @return 初始化的页面
	 * @throws Exception
	 */
	@RequestMapping(value="getView")
	public String getView(Model model,VerifyParamEx param) throws Exception{
		String loanCode = param.getLoanCode();
		List<GlRefuse> glRefuses = null;
		InnerCheckEx  checkView = null;
		if(!StringUtils.isBlank(loanCode)){
			checkView = firstVerifyService.getCheckInfo(param);	
			glRefuses = getRefuseCode(NumberConstants.ZERO_STRING);
		}else{
			throw new Exception("借款编号为空");
		}		
		// 历史归户信息概况显示信息  message
		model.addAttribute("glRefuses", glRefuses);
		model.addAttribute("message", SurveyUtil.getMessage(checkView.getCustomerHis()));		
		model.addAttribute("InnerCheck",checkView );
		return "/verify/firstCheckView";		
	}
	
	/**
	 * 初审决策
	 * 2015年12月24日
	 * xiaoniu.hu
	 * @param workItem
	 * @param loanApply
	 * @return 是否保存成功
	 */
	@RequestMapping(value="asynCheckOperation")
	@ResponseBody
	public String asynCheckOperation(WorkItemView workItem,VerifyBusinessView loanApply,String auditRulesCode){
		try {
			String result = loanApply.getAuditResultEx().getAuditResult();
			loanApply.getAuditResultEx().setAuditRulesCode(auditRulesCode);
			//决策评分为F时，此单自动拒借
			String riskLevel= auditRatingService.getScore(loanApply.getLoanCode(),loanApply.getCheckType());
			if ("F".equals(riskLevel)) {
				workItem.setResponse(ApproveRouteConstants.CHECK_TO_END);//拒借
				workItem.setBv(loanApply);
				 AuditResultEx auditResultEx=loanApply.getAuditResultEx();				
				 AuditResultSublist auditResultSub=new AuditResultSublist();
				 auditResultSub.setRefuseFirstCode(splitRefuseCode(ApproveConstants.SCORE_REFUSE_CODE, 2).replaceFirst("0", ""));
				 auditResultSub.setRefuseSecondCode(ApproveConstants.SCORE_REFUSE_CODE);
				 auditResultSub.setRefuseThirdCode(ApproveConstants.SCORE_REFUSE_CODE);
				// 将拒借码保存到审核结果子表				
				if (auditResultEx.getAuditResult().equals(ApproveConstants.PSEUDO_REFUSE)) {	
					List<AuditResultSublist> subResultList=auditResultEx.getSubResult();							
		            subResultList.add(auditResultSub);	
		            auditResultEx.setSubResult(subResultList);
		            auditResultEx.setAuditResult(ApproveConstants.REFUSE);		                     
				}else{
					List<AuditResultSublist> subResultList=new ArrayList<AuditResultSublist>();					
					subResultList.add(auditResultSub);					 
					auditResultEx.setRStatusHisId(null);
					auditResultEx.setProductType(null);
					auditResultEx.setAuditMonths(null);
					auditResultEx.setAuditAmount(null);
					auditResultEx.setAuditContractAmount(null);
					auditResultEx.setAuditRulesCode(null);
					auditResultEx.setAuditResult(ApproveConstants.REFUSE);
					auditResultEx.setAuditEnsureName(null);
					auditResultEx.setAuditLegalMan(null);
					auditResultEx.setEnsuremanBusinessPlace(null);
					auditResultEx.setModifyBy(null);
					auditResultEx.setRate(null);
					auditResultEx.setBusinessProveId(null);
					auditResultEx.setEnsuremanBusinessProvince(null);
					auditResultEx.setEnsuremanBusinessCity(null);
					auditResultEx.setEnsuremanBusinessArea(null);					
					auditResultEx.setSubResult(subResultList);
				}	
				flowService.dispatch(workItem);				
				return "refuse";
			}else{								
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
			}			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 获取保证人的公司名称
	 * 2016年1月5日
	 * By 刘燕军
	 * @param param
	 * @return 获取所有的保证人
	 */
	@ResponseBody
	@RequestMapping(value="getCautioner")
	public List<ZlshJyzm> getCautioner(VerifyParamEx param){
		List<ZlshJyzm> list = null;
		// 获取新版旧版标识
		String newOrOldFlag = verifyCommonService.getOldornewFlag(param.getLoanCode());
		if(null != newOrOldFlag && ("1").equals(newOrOldFlag)){
			list = firstVerifyService.getCautionerNew(param);
		}else{
			list = firstVerifyService.getCautioner(param);
		}
		return list;
		
	}
	
	/**
	 * 获取保证人的详细信息
	 * 2016年1月5日
	 * By 刘燕军
	 * @param id
	 * @return 对应的保证人信息
	 */
	@ResponseBody
	@RequestMapping(value="getCautionerInfo")
	public ZlshJyzm getCautionerInfo(String id){
		ZlshJyzm zlshJyzm = firstVerifyService.getCautionerInfo(id);
		return zlshJyzm;
	}
	
	/**
	 * 通过审核结果获取拒借信息
	 * 2016年1月12日
	 * By 赖敏
	 * @param resultId 审批结果ID
	 * @return 拒借信息
	 */
	@ResponseBody
	@RequestMapping(value="asynGetRefuseInfo")
	public AuditResultEx asynGetRefuseInfo(String resultId){
		return firstVerifyService.getRefuseInfo(resultId);
	}
	
	/**
	 * 通过产品编码获取对应的产品利率
	 * 2016年5月10日
	 * By 刘燕军
	 * @param productCode
	 * @return 利率
	 */
	@ResponseBody
	@RequestMapping(value="getRateByCode")
	public String getRateByCode(String productCode){
		String rate = BooleanType.FALSE;
		if(StringUtils.isNotEmpty(productCode)){
			try{
				rate = firstVerifyService.getRateByCode(productCode);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		return rate;
	}
	/**
	 * 将拒借码切割成一级拒借码或者二级拒借码
	 * 2016年1月21日
	 * By 王浩
	 * @param refuseCode 拒借码
	 * @param gap 一级或者二级拒借码中含有几个数字
	 * @return 一级或者二级拒借码
	 */
	private String splitRefuseCode(String refuseCode, int gap) {
		if (StringUtils.isNotEmpty(refuseCode)) {
			Pattern pattern = Pattern.compile("[0-9]");
			Matcher matcher = pattern.matcher(refuseCode);
			int index = -1;
			if (matcher.find()) {
				index = refuseCode.indexOf(matcher.group());
			}
			if (index >= 0) {
				return refuseCode.substring(0, index + gap > refuseCode
						.length() ? refuseCode.length() : index + gap);
			} else {
				return refuseCode;
			}
		} else {
			return "";
		}
	}
	
}

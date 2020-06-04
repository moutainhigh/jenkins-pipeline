package com.creditharmony.approve.outvisit.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.outvisit.entity.OutsideCheckList;
import com.creditharmony.approve.outvisit.entity.OutsideDatacheck;
import com.creditharmony.approve.outvisit.entity.ex.OutsideDatacheckEx;
import com.creditharmony.approve.outvisit.service.OutsideDatacheckService;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.bpm.frame.view.BaseTaskItemView;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.web.BaseController;
/**
 * 外访核查controller
 * @Class Name OutsideDatacheckController
 * @author wanglidong
 * @Create In 2015年12月7日
 */
@Controller
@RequestMapping(value = "${adminPath}/verify/visitcheck")
public class OutsideDatacheckController  extends BaseController{
	@Autowired
	private OutsideDatacheckService outsideDatacheckService;

	/**
	 * 外访核查【查看】页面
	 * 2015年12月7日
	 * By wanglidong
	 * @param model
	 * @param loanCode 借款编码
	 * @param param 公共参数
	 * @return	到外访核查查看页面
	 */
	@RequestMapping(value="toVisitCheckView")
	public String toVisitCheckView(Model model,VerifyParamEx param,BaseTaskItemView workParam){
		Map<String, Object> outVisitDictData = outsideDatacheckService.getOutVisitDictData();
		OutsideDatacheckEx visitCheckEx = outsideDatacheckService.getVisitCheckForm(param);
		List<OutsideCheckList> visitTaskList = outsideDatacheckService.getVisitCheckInfo(param);
		model.addAttribute("outVisitDictData", outVisitDictData);
		model.addAttribute("visitCheckEx", visitCheckEx);
		model.addAttribute("visitTaskList", visitTaskList);
		return "/outvisit/visitCheckView";	
	}
	
	/**
	 * 外访核查【操作】页面
	 * 2015年12月7日
	 * By wanglidong
	 * @param model
	 * @param loanCode 借款编码
	 * @param param 查询参数
	 * @return 到外访核查操作页面
	 */
	@RequestMapping(value="toVisitCheckForm")
	public String toVisitCheckForm(Model model,VerifyParamEx param,BaseTaskItemView workParam,String editFlag){
		Map<String, Object> outVisitDictData = outsideDatacheckService.getOutVisitDictData();
		OutsideDatacheckEx visitCheckEx = outsideDatacheckService.getVisitCheckForm(param);
		List<OutsideCheckList> visitTaskList = outsideDatacheckService.getVisitCheckInfo(param);
		if(!StringUtils.isEmpty(editFlag)){
			model.addAttribute("outVisitDictData", outVisitDictData);
			model.addAttribute("visitCheckEx", visitCheckEx);
			model.addAttribute("visitTaskList", visitTaskList);
			return "/outvisit/visitCheckView";	
		}else if(outVisitDictData.get("xsname").equals(workParam.getStepName()) || outVisitDictData.get("fyname").equals(workParam.getStepName())){
			model.addAttribute("outVisitDictData", outVisitDictData);
			model.addAttribute("visitCheckEx", visitCheckEx);
			model.addAttribute("visitTaskList", visitTaskList);
			return "/outvisit/visitCheckForm";
		}else{
			model.addAttribute("outVisitDictData", outVisitDictData);
			model.addAttribute("visitCheckEx", visitCheckEx);
			model.addAttribute("visitTaskList", visitTaskList);
			return "/outvisit/visitCheckView";			
		}
	}
	
	/**
	 * 【插入】外访核查结果
	 * 2015年12月15日
	 * By wanglidong
	 * @param model
	 * @param outsideDatacheck 要保存的外访核查对象
	 * @param 公共参数
	 * @return	成功返回插入的id, 失败返回 false
	 */
	@ResponseBody
	@RequestMapping(value="saveVisitCheck")
	public String addVisitCheck(Model model, OutsideDatacheck outsideDatacheck,VerifyParamEx param){
		String saveVisitCheckId = null;
		try {
			OutsideDatacheckEx visitCheckEx = outsideDatacheckService.getVisitCheckForm(param);
			if(visitCheckEx == null){
				saveVisitCheckId = outsideDatacheckService.saveVisitCheck(outsideDatacheck,param);
			}else{
				outsideDatacheckService.updateVisitCheck(outsideDatacheck);
				saveVisitCheckId = outsideDatacheck.getId();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return saveVisitCheckId;		
	}

	
}

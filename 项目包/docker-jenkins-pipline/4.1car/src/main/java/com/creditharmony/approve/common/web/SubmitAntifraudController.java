package com.creditharmony.approve.common.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.antifraud.service.AntiFraudJudgeService;
import com.creditharmony.approve.common.service.SubmitAntifraudService;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
/**
 * 提报反欺诈
 * @Class Name ReportAntifraudController
 * @author 刘燕军
 * @Create In 2015年12月4日
 */
@Controller
@RequestMapping(value = "${adminPath}/common/report")
public class SubmitAntifraudController extends BaseController{	
	@Autowired
	private SubmitAntifraudService  submitAntifraudService;
	@Autowired
	private AntiFraudJudgeService antiFraudJudgeService;

	
	/**
	 * 获取一级拒借码
	 * 2016年4月29日
	 * By wanglidong
	 * @param model
	 * @return
	 */
	@RequestMapping(value="getReportLevelOne", method = RequestMethod.GET)
	public String getReportLevelOne(Model model){
		List<GlRefuse> oneLevel = submitAntifraudService.getOneLevel();
		model.addAttribute("glRefuse", oneLevel);
		model.addAttribute("user", (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO));
		return "common/submitAntifraudForm";
	}
	
	/**
	 * 获取二级拒借码
	 * 2016年4月29日
	 * By wanglidong
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getReportLevelTwo")
	public List<GlRefuse> getReportLevelTwo(Model model,String id){
		List<GlRefuse> twoLevel = submitAntifraudService.getTwoLevel(id);
		return twoLevel;
	}
	
	/**
	 * 2016年4月29日
	 * By 刘燕军
	 * @param model
	 * @param relationId
	 * @return
	 */
	@RequestMapping(value="initView")
	public String initView(Model model,String relationId){
			model.addAttribute("initView", submitAntifraudService.getAntifraudReport(relationId));
	return "common/submitAntifraudView";		
	}
	
}

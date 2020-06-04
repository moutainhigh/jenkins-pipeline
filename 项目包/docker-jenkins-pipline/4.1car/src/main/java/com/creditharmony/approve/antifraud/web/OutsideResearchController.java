package com.creditharmony.approve.antifraud.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.approve.antifraud.entity.AntifraudOutsideSurvey;
import com.creditharmony.approve.antifraud.service.OutsideResearchService;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.web.BaseController;

/**
 * 外部调查
 * @Class Name OutsideResearchController
 * @author 赖敏
 * @Create In 2015年11月27日
 */
@Controller
@RequestMapping(value = "${adminPath}/antifraud/outResearch")
public class OutsideResearchController extends BaseController {
   
	@Autowired
	private OutsideResearchService outsideResearchService;
	
	/**
	 * 添加、修改外部调查
	 * 2015年11月30日
	 * By 赖敏
	 * @param outsideSurvey
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "saveSurvey")
	public String saveSurvey(Model model,AntifraudOutsideSurvey outsideSurvey,RedirectAttributes redirectAttributes){
		try {
			// 如果id为空插入外部调查
			if(StringUtils.isBlank(outsideSurvey.getId())){
				outsideResearchService.insertSurvey(outsideSurvey);
			// 如果id不为空修改外部调查
			}else{
				outsideResearchService.updateSurvey(outsideSurvey);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		List<AntifraudOutsideSurvey> list = outsideResearchService.findList(outsideSurvey);
		model.addAttribute("outList",list);
		model.addAttribute("outsideSurvey", outsideSurvey);
		return "/antifraud/outResearchFormList";
	}
	
	/**
	 * 获取外部调查列表
	 * 2015年11月30日
	 * By 赖敏
	 * @param model
	 * @param outsideSurvey
	 * @return
	 */
	@RequestMapping(value = "goForm")
	public String goForm(Model model,AntifraudOutsideSurvey outsideSurvey){
		List<AntifraudOutsideSurvey> list = outsideResearchService.findList(outsideSurvey);
		model.addAttribute("outList",list);
		model.addAttribute("outsideSurvey", outsideSurvey);
		return "/antifraud/outResearchFormList";
	}
	
	/**
	 * 添加、修改
	 * 2016年1月22日
	 * By 赖敏
	 * @return
	 */
	@RequestMapping(value = "editForm")
	public String editForm(Model model,AntifraudOutsideSurvey outsideSurvey){
		outsideSurvey = outsideResearchService.getSurvey(outsideSurvey);
		model.addAttribute("outsideSurvey",outsideSurvey);
		return "/antifraud/outResearchForm";
	}
	
	/**
	 * 获取外部调查列表
	 * 2015年11月30日
	 * By 赖敏
	 * @param model
	 * @param outsideSurvey
	 * @return
	 */
	@RequestMapping(value = "goView")
	public String goView(Model model,AntifraudOutsideSurvey outsideSurvey){
		List<AntifraudOutsideSurvey> list = outsideResearchService.findList(outsideSurvey);
		model.addAttribute("outList",list);
		return "/antifraud/outResearchView";
	}
	
	/**
	 * 删除外部调查
	 * 2015年11月30日
	 * By 赖敏
	 * @param outsideSurvey
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "deleteSurvey")
	public String deleteSurvey(Model model,AntifraudOutsideSurvey outsideSurvey,RedirectAttributes redirectAttributes){
		try {
			outsideResearchService.delete(outsideSurvey);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		List<AntifraudOutsideSurvey> list = outsideResearchService.findList(outsideSurvey);
		model.addAttribute("outList",list);
		model.addAttribute("outsideSurvey", outsideSurvey);
		return "/antifraud/outResearchFormList";
	}
	
	/**
	 * 获取进件时间
	 * 2016年4月7日
	 * By wanglidong
	 * @param loancode 借款编码
	 * @return 进件时间
	 */
	@ResponseBody
	@RequestMapping(value = "getIntoTime")
	public String getIntoTime(String loanCode){
		String intoTime = outsideResearchService.getIntoTime(loanCode);
		return intoTime;
	}
	
}

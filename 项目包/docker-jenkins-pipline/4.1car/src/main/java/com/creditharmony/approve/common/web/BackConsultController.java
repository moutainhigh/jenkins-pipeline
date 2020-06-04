package com.creditharmony.approve.common.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.common.entity.BackConsult;
import com.creditharmony.approve.common.service.BackConsultService;
import com.creditharmony.approve.management.entity.Negotiation;
import com.creditharmony.core.web.BaseController;
/**
 * 回退协商
 * @Class Name RollbackNegotiation
 * @author wanglidong
 * @Create In 2015年12月8日
 */
@Controller
@RequestMapping(value = "${adminPath}/common/backconsult")
public class BackConsultController extends  BaseController{
	@Autowired
	private BackConsultService backConsultService;
	
	/**
	 * 获取退回协商一级下拉框内容，并跳转到退回协商页面
	 * 2015年12月8日
	 * By wanglidong
	 * @param model
	 * @param loanCode
	 * @return
	 */
	@RequestMapping(value="toBackConsult")
	public String toBackConsult(Model model){
		List<Negotiation> negoListOne = backConsultService.getNegotiationCodeOne("0");
		model.addAttribute("negoList", negoListOne);
		return "/common/backConsult";
	}

	/**
	 * 获取退回协商二级下拉框内容
	 * 2015年12月10日
	 * By wanglidong
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody 
	@RequestMapping(value="asynToBackConsult")
	public List<Negotiation> asynToBackConsult(String id){
		List<Negotiation> backConsultCodeTwo = backConsultService.getNegotiationCodeTwo(id);
		return backConsultCodeTwo;
	}	
	
	/**
	 * 获取退回协商
	 * 2015年12月30日
	 * By wanglidong
	 * @param model
	 * @param loanCode 借款编码
	 * @param loanType	借款类型 主/共
	 * @param loanPeopleId	共借人id
	 * @return
	 */
	@RequestMapping(value="toBackConsultView")
	public String toBackConsultView(Model model,String relationId){
		BackConsult backConsultView = backConsultService.getBackConsultView(relationId);
		model.addAttribute("backConsultView", backConsultView);
		return "/common/backConsultView";
	}

}

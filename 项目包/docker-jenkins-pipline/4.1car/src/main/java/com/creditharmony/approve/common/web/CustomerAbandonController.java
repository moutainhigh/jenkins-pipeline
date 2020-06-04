package com.creditharmony.approve.common.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.common.entity.CustomerAbandon;
import com.creditharmony.approve.management.entity.GlGiveup;
import com.creditharmony.approve.common.service.CustomerAbandonService;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.web.BaseController;
/**
 * 客户放弃controller
 * @Class Name CustomerAbandonController
 * @author 刘燕军
 * @Create In 2015年12月5日
 */
@Controller
@RequestMapping(value = "${adminPath}/common/abandon")
public class CustomerAbandonController extends BaseController{
	@Autowired
	private CustomerAbandonService customerAbandonService;
	/**
	 * 客户放弃页面初始化信息
	 * 2015年12月5日
	 * By 刘燕军
	 * @param model
	 * @param loanCode
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "initCustomerAbandon")
	public String initCustomerAbandon(Model model){
		model.addAttribute("initPage", customerAbandonService.findGlGiveups());
		return "common/customerAbandonForm";
	}
	/**
	 * 通过一级码的id 获取对应的二级码
	 * 2015年12月5日
	 * By 刘燕军
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getSecondCode")
	public String getSecondCode(String id){
		if(id != null){
			List<GlGiveup> glGiveups = customerAbandonService.findGlGiveupsByParentId(id);
			String json=jsonMapper.toJson(glGiveups);
			return json;
		}else{
			return BooleanType.FALSE;
		}

	}
	/**
	 * 客户放弃查看页面初始化信息
	 * 2015年12月31日
	 * By 刘燕军
	 * @param model
	 * @param loanCode
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "initView")
	public String initView(Model model,String relationId){
		CustomerAbandon abandon = customerAbandonService.getList(relationId);
		model.addAttribute("initPage", abandon);
		return "common/customerAbandonView";
	}
}

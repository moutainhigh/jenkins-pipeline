package com.creditharmony.approve.common.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.common.entity.AuditBack;
import com.creditharmony.approve.common.service.BackStoreService;
import com.creditharmony.approve.verify.entity.ex.DictEx;
import com.creditharmony.core.web.BaseController;

/**
 * 审批退回门店
 * @Class Name BackStoreController
 * @author 赖敏
 * @Create In 2015年12月5日
 */
@Controller
@RequestMapping(value = "${adminPath}/common/backStore")
public class BackStoreController extends BaseController {
	
	@Autowired
	private BackStoreService backStoreService;

	
	/**
	 * 转向回退清单页面
	 * 2015年12月8日
	 * By 赖敏
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "goForm")
	public String goForm(Model model){
		List<DictEx> dicts = backStoreService.getBackStoreDicts();
		model.addAttribute("dicts",dicts);
		return "common/backStoreForm";
	}
	
	/**
	 * 转向查看回退清单页面
	 * 2015年12月18日
	 * By 赖敏
	 * @param model
	 * @param relationId 关联ID(变更历史表)
	 * @return 
	 */
	@RequestMapping(value = "goView")
	public String goView(Model model,String relationId){
		List<DictEx> dicts = backStoreService.getBackStoreDicts();
		model.addAttribute("dicts",dicts);
		model.addAttribute("relationId",relationId);
		return "common/backStoreView";
	}
	
	/**
	 * 根据历史ID获取历史回退清单
	 * 2015年12月26日
	 * By 赖敏
	 * @param relationId 关联ID(变更历史表)
	 * @return 历史回退清单
	 */
	@ResponseBody
	@RequestMapping(value = "getById")
	public AuditBack getById(String relationId){
		AuditBack auditBack = backStoreService.getById(relationId);
		return auditBack;
	}
	
}

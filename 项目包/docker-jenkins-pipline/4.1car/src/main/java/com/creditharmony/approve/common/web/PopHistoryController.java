package com.creditharmony.approve.common.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.common.service.PopHistoryService;
import com.creditharmony.approve.verify.entity.StatusChangeRecord;
import com.creditharmony.approve.verify.entity.ex.AuditResultEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.web.BaseController;

/**
 * 历史弹出画面
 * @Class Name PopHistoryController
 * @author 李文勇
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping(value = "${adminPath}/common/pophistory")
public class PopHistoryController extends BaseController {

	@Autowired
	PopHistoryService popHistoryService;
	
	/**
	 * 显示历史列表
	 * 2015年12月2日
	 * By 李文勇
	 * @param model
	 * @param loanCode
	 * @return
	 */
	@RequestMapping(value="showHistory")
	public String showHistory(Model model, VerifyParamEx verifyParamEx){
		List<StatusChangeRecord> history = popHistoryService.showHistory(verifyParamEx);
		model.addAttribute("history", history);
		return "common/popHistory";
	}
	
	/**
	 * 跳转回显画面
	 * 2016年1月19日
	 * By 李文勇
	 * @param model
	 * @param relationId
	 * @return
	 */
	@RequestMapping(value="backShowCheck")
	public String backShowCheck(Model model, String relationId){
		model.addAttribute("relationId", relationId);
		return "common/backShowCheck";
	}
	
	/**
	 * ajax异步显示决策回显数据
	 * 2016年1月19日
	 * By 李文勇
	 * @param relationId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="showCheckData")
	public AuditResultEx showCheckData(String relationId){
		AuditResultEx result = popHistoryService.getCheckInfo(relationId);
		return result;
	}
}

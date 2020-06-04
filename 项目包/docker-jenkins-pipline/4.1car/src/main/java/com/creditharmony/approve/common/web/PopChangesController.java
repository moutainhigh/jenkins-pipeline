package com.creditharmony.approve.common.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.approve.common.entity.ChangerInfo;
import com.creditharmony.approve.common.service.PopChangesService;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.web.BaseController;

/**
 * 汇金修改历史弹出画面
 * @Class Name PopChangesController
 * @author 李文勇
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping(value = "${adminPath}/common/popchanges")
public class PopChangesController extends BaseController {

	@Autowired
	PopChangesService popChangesService;
	
	/**
	 * 显示历史列表
	 * 2015年12月2日
	 * By 李文勇
	 * @param model
	 * @param loanCode
	 * @return
	 */
	@RequestMapping(value="showChangerInfo")
	public String showChangerInfo(Model model, VerifyParamEx verifyParamEx){
		List<ChangerInfo> changerInfos = popChangesService.showChangerInfo(verifyParamEx);
		model.addAttribute("changerInfos", changerInfos);
		return "common/popChangerInfo";
	}
	
}

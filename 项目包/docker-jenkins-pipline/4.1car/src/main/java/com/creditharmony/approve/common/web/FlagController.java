package com.creditharmony.approve.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.creditharmony.approve.base.service.FlagService;
import com.creditharmony.approve.verify.entity.QuotaLimit;
import com.creditharmony.core.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/common/flag/")
public class FlagController extends BaseController {

	@Autowired
	private FlagService flagService;

	@RequestMapping(value = "flag1")
	public ModelAndView flag1() {
		flagService.checkKinnobus();
		ModelAndView mav = new ModelAndView();
		QuotaLimit result = flagService.getResult();
		mav.addObject("result", result);
		mav.setViewName("/flagTest/flagTest");
		return mav;
	}

	@RequestMapping(value = "flag2")
	public ModelAndView flag2() {
		flagService.checkKinnobusXyj();
		ModelAndView mav = new ModelAndView();
		QuotaLimit result = flagService.getResult();
		mav.addObject("result", result);
		mav.setViewName("/flagTest/flagTest");
		return mav;
	}

}

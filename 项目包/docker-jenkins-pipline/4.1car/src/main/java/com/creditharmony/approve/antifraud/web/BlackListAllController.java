package com.creditharmony.approve.antifraud.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.approve.antifraud.entity.ex.BlacklistAllEx;
import com.creditharmony.approve.antifraud.service.BlackListAllService;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.service.CityInfoService;
import com.creditharmony.approve.verify.entity.JkProducts;
import com.creditharmony.approve.verify.service.ReconsiderFinalExamineService;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;

/**
 * 黑灰名单Controller
 * @Class Name BlackListController
 * @author wanglidong
 * @Create In 2015年11月23日
 */
@Controller
@RequestMapping(value = "${adminPath}/antifraud/blacklist")
public class BlackListAllController extends BaseController {
	@Autowired
	private BlackListAllService blackListAllService;
	@Autowired
	private ReconsiderFinalExamineService reconsiderFinalExamineService;	
	@Autowired
	private CityInfoService cityInfoService;
	
	/**
	 * 跳转到黑名单列表页面
	 * 2015年11月28日
	 * By wanglidong
	 * @param antifraudBlacklistEx
	 * @param model
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping(value = {"list", ""})
	public String list(BlacklistAllEx blacklistAllEx,Model model,Page<BlacklistAllEx> page) {
		List<JkProducts> allProducts = reconsiderFinalExamineService.getAllProducts();
		List<CityInfo> provinces = cityInfoService.findProvince();
		List<BlacklistAllEx> blackListType = blackListAllService.getBlackListType();
		model.addAttribute("blackListType", blackListType);
		model.addAttribute("products", allProducts);
		model.addAttribute("provinces", provinces);
		return "/antifraud/blackListAllForm";
	}
	
	/**
	 * 根据条件搜索列表后跳转到黑名单列表页面
	 * 2015年11月27日
	 * By wanglidong
	 * @param antifraudBlacklistEx
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="findBlackListAll")
	public String findBlackListAll(BlacklistAllEx blacklistAllEx, Model model,HttpServletRequest request, HttpServletResponse response) {
		// 获取全部产品
		List<JkProducts> allProducts = reconsiderFinalExamineService.getAllProducts();
		// 查询所有省
		List<CityInfo> provinces = cityInfoService.findProvince();
		// 查黑灰名单类型
		List<BlacklistAllEx> blackListType = blackListAllService.getBlackListType();
		Page<BlacklistAllEx> page = new Page<BlacklistAllEx>(request,response);
		// 将查询条件转换成map
		Map<String, Object> map = jsonMapper.convertValue(blacklistAllEx, Map.class);
		// 反欺诈决策起始时间
		map.put("judgeProcDateFrom", blacklistAllEx.getJudgeProcDateFrom());
		// 反欺诈决策结束时间
		map.put("judgeProcDateTo", blacklistAllEx.getJudgeProcDateTo());
		// 查询起始顾客进件时间
		map.put("customerIntoTimeFrom", blacklistAllEx.getCustomerIntoTimeFrom());
		// 查询结束顾客进件时间
		map.put("customerIntoTimeTo", blacklistAllEx.getCustomerIntoTimeTo());
		// 获取分页数据
		Page<BlacklistAllEx> findByParams = blackListAllService.findByParams(page, map);
		model.addAttribute("blackListType", blackListType);
		model.addAttribute("page", findByParams);
		model.addAttribute("products", allProducts);
		model.addAttribute("provinces", provinces);
		model.addAttribute("blacklistAllEx", blacklistAllEx);
		return "/antifraud/blackListAllForm";
	}
}




















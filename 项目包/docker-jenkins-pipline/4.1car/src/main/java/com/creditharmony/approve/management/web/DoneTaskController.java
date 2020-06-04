package com.creditharmony.approve.management.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.approve.base.web.ApproveController;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.service.CityInfoService;
import com.creditharmony.approve.management.service.VerifyAllService;
import com.creditharmony.approve.verify.entity.JkProducts;
import com.creditharmony.approve.verify.entity.ex.VerifyListEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.ReconsiderFinalExamineService;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;

/**
 * 已办处理类
 * @Class Name DoneTaskController
 * @author xiaoniu.hu
 * @Create In 2016年3月22日
 */
@Controller
@RequestMapping(value = "${adminPath}/management/doneTask")
public class DoneTaskController extends ApproveController{
	
	@Autowired
	private CityInfoService cityInfoService;
	@Autowired
	private VerifyAllService completedService;
	@Autowired
	ReconsiderFinalExamineService reconsiderFinalExamineService;
	
	/**
	 * 已办列表查询
	 * 2016年3月14日
	 * By 刘燕军
	 * @param model
	 * @param verifyListEntity
	 * @param request
	 * @param response
	 * @return 所有的已办列表集合 
	 */
	@RequestMapping(value="getAllDone")
	public String getAllDone(Model model, VerifyListEx verifyListEntity,HttpServletRequest request, HttpServletResponse response){
		List<CityInfo> provinces = cityInfoService.findProvince();
		Page<VerifyListEx> page = completedService.findAllCom(new Page<VerifyListEx>(request, response), verifyListEntity);
		List<JkProducts> allProduct = reconsiderFinalExamineService.getAllProducts();
		model.addAttribute("verifyListEntity", verifyListEntity);
		model.addAttribute("allProduct", allProduct);
		model.addAttribute("page", page);
		model.addAttribute("provinces", provinces);
		model.addAttribute("show", BooleanType.TRUE);
		return "task/completedList";
	}
	
	/**
	 * 获取当前用户下可以查看的所有已办
	 * 2016年3月22日
	 * xiaoniu.hu
	 * @param model
	 * @param verifyListEntity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getDoneByUser")
	public String getDoneByUser(Model model, VerifyListEx verifyListEntity,HttpServletRequest request, HttpServletResponse response){
		User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		verifyListEntity.setOrgId(userInfo.getDepartment().getId());
		List<CityInfo> provinces = cityInfoService.findProvince();
		Page<VerifyListEx> page = completedService.findAll(new Page<VerifyListEx>(request, response), verifyListEntity);
		List<JkProducts> allProduct = reconsiderFinalExamineService.getAllProducts();
		model.addAttribute("verifyListEntity", verifyListEntity);
		model.addAttribute("allProduct", allProduct);
		model.addAttribute("page", page);
		model.addAttribute("provinces", provinces);
		model.addAttribute("show", BooleanType.TRUE);
		return "task/completedList";
	}
	
	/**
	 * 获取【反欺诈经理】用户下可以查看的所有已办
	 * 2016年3月22日
	 * xiaoniu.hu
	 * @param model
	 * @param verifyListEntity
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getAntifraudDoneByUser")
	public String getAntifraudDoneByUser(Model model, VerifyListEx verifyListEntity,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = jsonMapper.convertValue(verifyListEntity, Map.class);
		// 查询最小时间
		map.put("minCustomerIntoTime", verifyListEntity.getMinCustomerIntoTime());
		// 查询最大时间
		map.put("maxCustomerIntoTime", verifyListEntity.getMaxCustomerIntoTime());
		// 获取session里的用户信息
		User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		// 获取用户的部门id
		verifyListEntity.setOrgId(userInfo.getDepartment().getId());
		// 查找分页信息
		Page<VerifyListEx> page = completedService.findAntifraudAll(new Page<VerifyListEx>(request,response), verifyListEntity);
		List<CityInfo> provinces = cityInfoService.findProvince();
		List<JkProducts> products =  reconsiderFinalExamineService.getAllProducts();
		model.addAttribute("page", page);
		model.addAttribute("provinces", provinces);
		model.addAttribute("products", products);
		model.addAttribute("verifyListEx", verifyListEntity);
		model.addAttribute("show", BooleanType.TRUE);
		return "/antifraud/commissionerCompletedList";
	}
	/**
	 * 查询某一个已办
	 * 2016年5月26日
	 * By 刘燕军
	 * @param model
	 * @param param
	 * @return
	 */
	@RequestMapping(value="getDoneByCondition")
	public String getDoneByCondition(Model model, VerifyParamEx param){
		List<VerifyListEx> list = null;
		if(StringUtils.isNotEmpty(param.getLoanCode()) || StringUtils.isNotEmpty(param.getCustomerCertNum()) || StringUtils.isNotEmpty(param.getCustomerName())){
			list = completedService.findOneDone(param);
		}
		model.addAttribute("param", param);
		model.addAttribute("list", list);
		return "management/oneCompletedList";
	}
}

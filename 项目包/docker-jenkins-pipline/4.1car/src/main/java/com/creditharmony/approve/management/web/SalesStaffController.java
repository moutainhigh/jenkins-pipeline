package com.creditharmony.approve.management.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.approve.management.entity.SalesStaff;
import com.creditharmony.approve.management.service.SalesStaffService;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;

/**
 * 销售人员号码维护页面
 * @Class Name SalesStaffController
 * @author xiaoniu.hu
 * @Create In 2016年3月22日
 */
@Controller
@RequestMapping(value = "${adminPath}/management/salesman")
public class SalesStaffController extends BaseController{

	@Autowired
	private SalesStaffService salesStaffService;
	
	/**
	 * 分页  获取反欺诈-销售人员信息列表
	 * @param salesStaff
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value ={"list",""})
	public String findSalesStaffAll(SalesStaff salesStaff, Model model,HttpServletRequest request, HttpServletResponse response) {
		
		List<SalesStaff> salesStaffPage = salesStaffService.getSalesStaff();
		Page<SalesStaff> page = new Page<SalesStaff>(request,response);
		Map<String, Object> map = jsonMapper.convertValue(salesStaff, Map.class);
		map.put("salesCode", salesStaff.getSalesCode());
		map.put("salesName", salesStaff.getSalesName());
		map.put("salesTel", salesStaff.getSalesTel());
		map.put("workFlag", salesStaff.getWorkFlag());
		map.put("dictJobGrade", salesStaff.getDictJobGrade());
		Page<SalesStaff> findByParams = salesStaffService.findByParams(page, map);
		model.addAttribute("salesStaffPage", salesStaffPage);
		model.addAttribute("page", findByParams);
		model.addAttribute("salesStaff", salesStaff);
		return "/management/salesStaffList";
	}
	
	
	/**
	 * 删除salesStaffForm/editForm
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delSalesStaff")
	public String delSalesStaff(String id){
		int del = salesStaffService.delSalesStaff(id);
		if(del > 0){
			return "1";
		}
		return "0";
	}
	/**
	 * to 添加页面
	 * @return
	 */
	@RequestMapping(value = "goSalesStaff")
	public String goSalesStaff(){
		return "/management/salesStaffForm";
	}
	
	/**
	 * to 修改页面
	 * @param model
	 * @param salesStaff
	 * @return
	 */
	@RequestMapping(value = "editForm")
	public String editForm(Model model,SalesStaff salesStaff){
		
		SalesStaff list = salesStaffService.findSalesStaff(salesStaff);
		model.addAttribute("salesStaff",list);
		return "/management/salesStaffEditForm";
	}
	/**
	 * 添加、修改
	 * @param model
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "addSalesStaff")
	public String saveSalesStaff(Model model,SalesStaff salesStaff,RedirectAttributes redirectAttributes) {		
//		String id = "";
		try {
			if(StringUtils.isBlank(salesStaff.getId())) {
				salesStaffService.addSalesStaff(salesStaff);
				addMessage(redirectAttributes, "保存'" + salesStaff.getSalesCode() + "'成功");
			} else {				
				salesStaffService.updateSalesStaff(salesStaff); 
				addMessage(redirectAttributes, "更新'" + salesStaff.getSalesCode() + "'成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return BooleanType.FALSE;
		}
		return "redirect:" + adminPath + "/management/salesman/list";
	}
	
}

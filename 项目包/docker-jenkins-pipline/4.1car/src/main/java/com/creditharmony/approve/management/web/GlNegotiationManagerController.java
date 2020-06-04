package com.creditharmony.approve.management.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.management.service.GlNegotiationManagerService;
import com.creditharmony.approve.management.entity.Negotiation;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.web.BaseController;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 客户协商码维护
 * @Class Name GlNegotiationManagerController
 * @author 李文勇
 * @Create In 2015年12月22日
 */
@Controller
@RequestMapping(value = "${adminPath}/management/negotiation")
public class GlNegotiationManagerController extends BaseController {

	@Autowired
	private GlNegotiationManagerService glNegotiationManagerService;
	
	/**
	 * 画面操作必经方法
	 * 2015年12月22日
	 * By 李文勇
	 * @param id
	 * @return
	 */
	@ModelAttribute("negotiation")
	public Negotiation get(@RequestParam(required=false) String id) {
		if ( StringUtils.isNotBlank(id) ){
			return glNegotiationManagerService.getById(id);
		}else{
			return new Negotiation();
		}
	}

	/**
	 * 获取协商码列表
	 * 2015年12月22日
	 * By 李文勇
	 * @param glGiveup
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Negotiation negotiation, Model model) {
		List<Negotiation> list = glNegotiationManagerService.findAll();
		for( Negotiation item : list ){
			Negotiation pItem = item.getParent();
			if(item.getParent() == null || NumberConstants.ZERO_STRING.equals(pItem.getId())){
				Negotiation parent = new Negotiation();
				parent.setId(NumberConstants.ZERO_STRING);
				item.setParent(parent);
			}
		}
		model.addAttribute("list", list);
		return "management/negotiationManagerList";
	}

	/**
	 * 跳转到form页面
	 * 2015年12月22日
	 * By 李文勇
	 * @param negotiation
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(Negotiation negotiation, Model model) {
		Negotiation data = glNegotiationManagerService.getById( negotiation );
		model.addAttribute("negotiation", data);
		return "management/negotiationManagerForm";
	}
	
	/**
	 * 保存或更新数据
	 * 2015年12月22日
	 * By 李文勇
	 * @param negotiation
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String saveData(Negotiation negotiation, Model model, RedirectAttributes redirectAttributes) {
		if ( !beanValidator(model, negotiation)){
			return form(negotiation, model);
		}
		glNegotiationManagerService.saveData(negotiation);
		addMessage(redirectAttributes, "保存拒借码'" + negotiation.getNegotiationName() + "'成功");
		return "redirect:" + adminPath + "/management/negotiation/";
	}
	
	/**
	 * 逻辑删除数据
	 * 2015年12月22日
	 * By 李文勇
	 * @param negotiation
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String deleteData( Negotiation negotiation, RedirectAttributes redirectAttributes ) {
		glNegotiationManagerService.deleteData(negotiation);
		addMessage( redirectAttributes, "删除拒借码成功" );
		return "redirect:" + adminPath + "/management/negotiation/";
	}

	/**
	 * 树数据
	 * 2015年12月22日
	 * By 李文勇
	 * @param extId
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = false) String extId,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Negotiation> list = glNegotiationManagerService.findAll();
		for ( int i = 0; i < list.size(); i++ ){
			Negotiation e = list.get(i);
			if (StringUtils.isNotEmpty(extId)) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParent().getId());
				map.put("name", e.getNegotiationName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	/**
	 * 检查协商名是否重复
	 * 2016年1月26日
	 * By 李文勇
	 * @param oldName
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String negotiationName) {
		// 判断是否为空
		if(StringUtils.isNotEmpty(negotiationName)){
			return BooleanType.TRUE;
		}else{
			return BooleanType.FALSE;
		}
		
		// 判断是否已经含有相同的拒借名
		/*if (refuseName != null && refuseName.equals(oldName)) {
			return BooleanType.TRUE;
		} else if (refuseName != null) {
			Refuse refuse = new Refuse();
			refuse.setRefuseName(refuseName);
			if(ObjectHelper.isEmpty(refuseManagerService.findByParam(refuse))){
				return BooleanType.TRUE;
			}
		}
		return BooleanType.FALSE;*/
	}
	
	/**
	 * 检查协商码是否重复
	 * 2016年1月26日
	 * By 李文勇
	 * @param oldName
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkCode")
	public String checkCode(String oldCode, String negotiationCode) {
		if (negotiationCode != null && negotiationCode.equals(oldCode)) {
			return BooleanType.TRUE;
		} else if (negotiationCode != null) {
			Negotiation negotiation = new Negotiation();
			negotiation.setNegotiationCode(negotiationCode);
			if(ObjectHelper.isEmpty(glNegotiationManagerService.findByParam(negotiation))){
				return BooleanType.TRUE;
			}
		}
		return BooleanType.FALSE;
	}
}

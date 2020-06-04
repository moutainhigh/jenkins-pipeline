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
import com.creditharmony.approve.management.service.GlGiveupManagerService;
import com.creditharmony.approve.management.entity.GlGiveup;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.web.BaseController;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 客户放弃码表数据维护
 * @Class Name GlGiveupManagerController
 * @author 李文勇
 * @Create In 2015年12月17日
 */
@Controller
@RequestMapping(value = "${adminPath}/management/giveup")
public class GlGiveupManagerController extends BaseController {

	@Autowired
	private GlGiveupManagerService glGiveupManagerService;
	
	/**
	 * 操作画面必经方法
	 * 2015年12月22日
	 * By 李文勇
	 * @param id
	 * @return
	 */
	@ModelAttribute("glGiveup")
	public GlGiveup get(@RequestParam(required=false) String id) {
		if ( StringUtils.isNotBlank(id)){
			return glGiveupManagerService.getById(id);
		}else{
			return new GlGiveup();
		}
	}

	/**
	 * 获取用户放弃码列表
	 * 2015年12月17日
	 * By 李文勇
	 * @param glGiveup
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list", ""})
	public String list( GlGiveup glGiveup, Model model ) {
		List<GlGiveup> list = glGiveupManagerService.findAll();
		for( GlGiveup item : list ){
			GlGiveup pItem = item.getParent();
			if( item.getParent() == null || NumberConstants.ZERO_STRING.equals(pItem.getId() ) ){
				GlGiveup parent = new GlGiveup();
				parent.setId(NumberConstants.ZERO_STRING);
				item.setParent(parent);
			}
		}
		model.addAttribute("list", list);
		return "management/giveupManagerList";
	}

	/**
	 * 跳转到修改，添加页面
	 * 2015年12月17日
	 * By 李文勇
	 * @param model
	 * @param glGiveup
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form( GlGiveup glGiveup, Model model ) {
		GlGiveup data = glGiveupManagerService.getById( glGiveup );
		model.addAttribute("giveup", data);
		return "management/giveupManageForm";
	}
	
	/**
	 * 保存客户放弃码
	 * 2015年12月17日
	 * By 李文勇
	 * @param glGiveup
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String saveData(GlGiveup glGiveup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, glGiveup)){
			return form( glGiveup, model);
		}
		glGiveupManagerService.saveData(glGiveup);
		addMessage( redirectAttributes, "保存拒借码'" + glGiveup.getGiveupName() + "'成功" );
		return "redirect:" + adminPath + "/management/giveup/";
	}
	
	/**
	 * 逻辑删除客户放弃码
	 * 2015年12月17日
	 * By 李文勇
	 * @param glGiveup
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String deleteData(GlGiveup glGiveup, RedirectAttributes redirectAttributes) {
		glGiveupManagerService.deleteData(glGiveup);
		addMessage(redirectAttributes, "删除拒借码成功");
		return "redirect:" + adminPath + "/management/giveup/";
	}

	/**
	 * 树数据
	 * 2015年12月17日
	 * By 李文勇
	 * @param extId
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = false) String extId,
			HttpServletResponse response ) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<GlGiveup> list = glGiveupManagerService.findAll();
		for ( int i = 0; i < list.size(); i++ ){
			GlGiveup e = list.get(i);
			if (StringUtils.isBlank(extId)) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParent());
				map.put("name", e.getGiveupName());
				mapList.add( map );
			}
		}
		return mapList;
	}
	
	
	/**
	 * 检查放弃名是否重复
	 * 2016年1月26日
	 * By 李文勇
	 * @param oldName
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String giveupName) {
		// 判断是否为空
		if(StringUtils.isNotEmpty(giveupName)){
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
	 * 检查放弃码是否重复
	 * 2016年1月26日
	 * By 李文勇
	 * @param oldName
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkCode")
	public String checkCode(String oldCode, String giveupCode) {
		if (giveupCode != null && giveupCode.equals(oldCode)) {
			return BooleanType.TRUE;
		} else if (giveupCode != null) {
			GlGiveup glGiveup = new GlGiveup();
			glGiveup.setGiveupCode(giveupCode);
			if(ObjectHelper.isEmpty(glGiveupManagerService.findByParam(glGiveup))){
				return BooleanType.TRUE;
			}
		}
		return BooleanType.FALSE;
	}
}

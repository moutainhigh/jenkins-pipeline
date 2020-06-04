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
import com.creditharmony.approve.management.service.RefuseManagerService;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.management.entity.Refuse;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.web.BaseController;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 拒借码维护
 * @Class Name RefuseManagerController
 * @author 李文勇
 * @Create In 2015年12月22日
 */
@Controller
@RequestMapping(value = "${adminPath}/management/refuse")
public class RefuseManagerController extends BaseController {

	@Autowired
	private RefuseManagerService refuseManagerService;
	
	/**
	 * 操作画面必经方法
	 * 2015年12月22日
	 * By 李文勇
	 * @param id
	 * @return
	 */
	@ModelAttribute("glRefuse")
	public GlRefuse get(@RequestParam(required=false) String id ) {
		if (StringUtils.isNotBlank(id)){
			return refuseManagerService.getById(id);
		}else{
			return new GlRefuse();
		}
	}

	/**
	 * 获得拒借码列表
	 * 2015年12月22日
	 * By 李文勇
	 * @param refuse
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list", ""})
	public String list( Refuse refuse, Model model ) {
		List<GlRefuse> list = refuseManagerService.findAll();
		for(GlRefuse item : list){
			GlRefuse pItem = item.getParent();
			if(item.getParent() == null || NumberConstants.ZERO_STRING.equals(pItem.getId())){
				GlRefuse parent = new GlRefuse();
				parent.setId(NumberConstants.ZERO_STRING);
				item.setParent(parent);
			}
		}
		model.addAttribute( "list", list );
		return "management/refuseList";
	}

	/**
	 * 添加拒借码
	 * 2015年12月22日
	 * By 李文勇
	 * @param refuse
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(GlRefuse glRefuse, Model model) {
		GlRefuse data = refuseManagerService.getById(glRefuse);
		model.addAttribute("refuse", data);
		return "management/refuseForm";
	}
	
	/**
	 * 保存拒借码
	 * 2015年12月22日
	 * By 李文勇
	 * @param refuse
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save( GlRefuse glRefuse, Model model, RedirectAttributes redirectAttributes ) {
		if ( !beanValidator(model, glRefuse) ){
			return form(glRefuse, model);
		}
		refuseManagerService.saveData(glRefuse);
		addMessage(redirectAttributes, "保存拒借码'" + glRefuse.getRefuseName() + "'成功");
		return "redirect:" + adminPath + "/management/refuse/";
	}
	
	/**
	 * 删除拒接码
	 * 2015年12月22日
	 * By 李文勇
	 * @param refuse
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(GlRefuse glRefuse, RedirectAttributes redirectAttributes) {
		refuseManagerService.deleteData(glRefuse);
		addMessage(redirectAttributes, "删除拒借码成功");
		return "redirect:" + adminPath + "/management/refuse/";
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
		List<GlRefuse> list = refuseManagerService.findAll();
		for ( int i = 0; i < list.size(); i++ ){
			GlRefuse e = list.get(i);
			if (StringUtils.isBlank(extId)) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id" , e.getId());
				map.put("pId", e.getParent());
				map.put("name", e.getRefuseName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	/**
	 * 检查拒借名是否重复
	 * 2016年1月26日
	 * By 李文勇
	 * @param oldName
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String refuseName) {
	
		// 判断是否为空
		if(StringUtils.isNotEmpty(refuseName)){
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
	 * 检查拒借码是否重复
	 * 2016年1月26日
	 * By 李文勇
	 * @param oldName
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkCode")
	public String checkCode(String oldCode, String refuseCode) {
		if (refuseCode != null && refuseCode.equals(oldCode)) {
			return BooleanType.TRUE;
		} else if (refuseCode != null) {
			GlRefuse glRefuse = new GlRefuse();
			glRefuse.setRefuseCode(refuseCode);
			if(ObjectHelper.isEmpty(refuseManagerService.findByParam(glRefuse))){
				return BooleanType.TRUE;
			}
		}
		return BooleanType.FALSE;
	}
}

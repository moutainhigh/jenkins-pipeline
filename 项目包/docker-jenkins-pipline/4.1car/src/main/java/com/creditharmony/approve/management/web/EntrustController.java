
package com.creditharmony.approve.management.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.approve.base.web.ApproveController;
import com.creditharmony.approve.common.type.RoleType;
import com.creditharmony.approve.management.service.JkEntrustService;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.entity.ex.EntrustEx;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.type.DeleteFlagType;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.persistence.BaseEntity;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * 委托管理
 * @Class Name EntrustController
 * @author 王浩
 * @Create In 2015年12月8日
 */
@Controller
@RequestMapping(value = "${adminPath}/management/entrust")
public class EntrustController extends ApproveController {

	@Autowired
	private JkEntrustService entrustService;
	
	/**
	 * 根据id取数据
	 * 2015年12月9日
	 * By 王浩
	 * @param id 数据记录id
	 * @return 返回一个Entrust对象
	 */
	@ModelAttribute
	public EntrustEx getEntrustInfo(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return entrustService.getEntrust(id);
		} else {
			return new EntrustEx();
		}
	}
	
	/**
	 * 显示委托列表
	 * 2015年12月8日
	 * By 王浩
	 * @param jkEntrustEx 委托对象
	 * @param request 
	 * @param response 返回resoponse
	 * @param model
	 * @return 跳转到列表页面的路径
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="list")
	public String showEntrustList(EntrustEx jkEntrustEx, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Map<String, Object> filter = new HashMap<String, Object>();
		filter = jsonMapper.convertValue(jkEntrustEx, Map.class);
		// 如果用户在界面上切换机构，取出session中存放的用户的最新机构
		User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		// 默认只能查询本部门的委托列表
		if (userInfo != null && userInfo.getDepartment() != null) {	
			filter.put("departmentId", userInfo.getDepartment().getId());
		}		
		Page<EntrustEx> entrustList = entrustService.getEntrustList(
				new Page<EntrustEx>(request, response), filter);
		model.addAttribute("page", entrustList);
		return "/management/entrustList";
	}
	
	/**
	 * 进入修改/新增页面前，准备数据
	 * 2015年12月9日
	 * By 王浩
	 * @param model 
	 * @param entrustId 记录的id
	 * @return 数据准备完成，跳转到数据操作页面的路径
	 */
	@RequestMapping(value = "form")
	public String initForm(Model model, EntrustEx entrustEx){		
		String entrustId = entrustEx.getId(); 
		if (StringUtils.isNotEmpty(entrustId)) {
			entrustEx = entrustService.getEntrust(entrustId);
		}		
		model.addAttribute("entrustEx", entrustEx);
		
		return "/management/entrustForm";
	}
	
	/**
	 * 保存数据
	 * 2015年12月9日
	 * By 王浩
	 * @param paramMap 参数集
	 * @param entrustEx 委托记录
	 * @param model 
	 * @param redirectAttributes 重定向参数
	 * @return 保存数据完成后，返回到列表页面
	 */
	@RequestMapping(value = "save")
	public String saveEntrustInfo(@RequestParam Map<String, Object> paramMap, EntrustEx entrustEx, 
			Model model, RedirectAttributes redirectAttributes) {
		Date startTime = DateUtils.parseDate(paramMap.get("entrustStartTime"));
		if (startTime != null){
			entrustEx.setEntrustStartTime(startTime);
		}
		Date endTime = DateUtils.parseDate(paramMap.get("entrustEndtime"));
		if (endTime != null) {
			entrustEx.setEntrustEndTime(endTime);
		}
		entrustService.saveEntrust(entrustEx);
		 
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/management/entrust/list";
	}

	/**
	 * 设置某一委托记录的启用、禁用
	 * 2016年2月17日
	 * By 王浩
	 * @param entrust 要更新的委托记录
	 * @param redirectAttributes 重定向参数
	 * @return 更新记录后，页面重定向
	 */
	@RequestMapping(value="valid")
	public String changeValid(EntrustEx entrust, RedirectAttributes redirectAttributes){
		// 是否启用，如果启用，更新为禁用；如果禁用，更新为启用
		entrust.setValidFlag(entrust.getValidFlag().equals(
				DeleteFlagType.NORMAL) ? DeleteFlagType.DELETE
				: DeleteFlagType.NORMAL);
		entrustService.updateValid(entrust);
		addMessage(redirectAttributes, "更新记录成功");
		return "redirect:" + adminPath + "/management/entrust/list";
	}
	
	/**
	 * 根据id删除某一条委托记录
	 * 2015年12月15日
	 * By 王浩
	 * @param entrustId 记录的id
	 * @param redirectAttributes 重定向参数
	 * @return 删除该条记录后，返回页面重定向的路径
	 */
	@RequestMapping(value="delete")
	public String asyncDelEntrust(EntrustEx entrust, RedirectAttributes redirectAttributes){
		entrustService.delEntrustById(entrust.getId());
		addMessage(redirectAttributes, "删除委托记录成功");
		return "redirect:" + adminPath + "/management/entrust/list";
	}
	
	/**
	 * 查出用户树形结构 
	 * 2015年12月9日
	 * By 王浩
	 * @param type 公司类型
	 * @param response 返回response
	 * @return 将查询数据封装成List<Map>后返回页面
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(String params,
			HttpServletResponse response) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		String[] allParam = params.split("-");
		String exceptUser = null;
		// 判断是否是被委托
		String acceptFlag = "";
		if (allParam.length >= 1) {
			acceptFlag = allParam[0];
		}
		// 如果已经选定了一个用户，取出该用户的id
		if (allParam.length >= 2) {
			exceptUser = allParam[1];
		}
		
		User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		// 当前用户不能给自己设置委托
		param.put("loginUser", userInfo.getId());		
		param.put("delFlag", BaseEntity.DEL_FLAG_NORMAL);
		
		Org currentOrg = userInfo.getDepartment();
		String userDeptId = currentOrg.getId();
		if(StringUtils.isNotEmpty(userDeptId)){		
			// 同时查询同机构、子机构的用户, 具体参见sql			
			param.put("departmentId", userDeptId);
		}		
		
		List<User> list = null;
		// 如果要排除的用户名不为空，那么排除该用户，查询同角色其他用户
		if (StringUtils.isNotEmpty(exceptUser)) {
			param.put("exceptUserId", exceptUser);						
			// 如果是被委托，针对信审初审分为初级、中级、高级的情况, 查询被委托人时等级要高于或等于委托人
			if (acceptFlag.equals(BooleanType.TRUE)) {
				EntrustEx currUserEntrust = entrustService.getCurrentUser(param);
				if (currUserEntrust != null && currUserEntrust.getLevel() != null
						&& RoleType.APPROVE_VERIFY_CHECK.getName().equals(currUserEntrust.getRoleType())) {
					// 如果是信审初审，加入等级判定的内容
					param.put("checkLevel", currUserEntrust.getLevel());
				}				
			}
			// 已经选定了一个用户(委托人或者被委托人)，查询其他用户
			list = entrustService.findOtherByPrevUser(param);
		} else {
			// 没有选定任何用户，查询当前机构所有用户
			list = entrustService.findUserByRole(param);
		}
		List<Map<String, Object>> mapList = Lists.newArrayList();
		for (int i = 0; i < list.size(); i++) {
			User u = list.get(i);
			
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", u.getId());
			map.put("name", u.getName());
			mapList.add(map);
		}
		return mapList;
	}
	
}

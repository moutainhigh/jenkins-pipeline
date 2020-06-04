package com.creditharmony.approve.users.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.config.Global;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.service.UserManager;
import com.creditharmony.core.users.util.PasswordUtil;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;

/**
 * 用户控制器
 * @Class Name UserController
 * @author 张永生
 * @Create In 2015年12月15日
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {

	@Autowired
	private UserManager userManager;
	
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return userManager.getUser(id);
		}else{
			return new User();
		}
	}

	@RequestMapping(value = {"index"})
	public String index(User user, Model model) {
		return "userinfo/userIndex";
	}
	
	/**
	 * 用户信息显示及保存
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "info")
	public String info(User user, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getName())){
			currentUser.setEmail(user.getEmail());
			currentUser.setPhone(user.getPhone());
			currentUser.setMobile(user.getMobile());
			currentUser.setRemarks(user.getRemarks());
			userManager.updateUserInfo(currentUser);
			model.addAttribute("message", "保存用户信息成功");
		}
		String userType=DictCache.getInstance().getDictLabel("t_gl_user_type",currentUser.getUserType());
		currentUser.setUserType(userType);
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "userinfo/userInfo";
	}

	/**
	 * 返回用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "infoData")
	public User infoData() {
		return UserUtils.getUser();
	}
	
	/**
	 * 修改个人用户密码
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)){
			if (PasswordUtil.validPassword(user.getId(), oldPassword)){
				userManager.updatePasswordById(user.getId(), user.getLoginName(), newPassword);
				model.addAttribute("message", "修改密码成功");
			}else{
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", user);
		return "userinfo/userModifyPwd";
	}
	
	
}

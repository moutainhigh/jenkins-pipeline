package com.creditharmony.approve.base.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.creditharmony.bpm.filenet.utils.FileNetContextHolder;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.security.Principal;
import com.creditharmony.core.users.dao.MenuDao;
import com.creditharmony.core.users.entity.Menu;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.service.OrgManager;
import com.creditharmony.core.users.service.UserManager;
import com.creditharmony.core.users.util.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/approveLogin")
public class ApproveLoginController {

	@Autowired
	private UserManager userManager;
	@Autowired
	private OrgManager orgManager;
	@Autowired
	private MenuDao menuDao;
	
	@RequestMapping(value = "changeOrg")
	public String changeOrg(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		//portal直接进入业务系统待办列表地址
		String showmenuid = request.getParameter("showmenuid");
		if(!StringUtils.isEmpty(showmenuid)){
			Menu menu = menuDao.get(showmenuid);
			if(menu != null){
				model.addAttribute("menuUrl", menu.getHref());
				model.addAttribute("menuName", menu.getName());
			}
		}
		model.addAttribute("showmenuid", showmenuid);
		
		Principal principal = UserUtils.getPrincipal();
		User user = userManager.get(principal.getId());

		String orgId = request.getParameter("orgId");
		if(null != orgId && !"".equals(orgId)){
			User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
			userInfo.setDepartment(orgManager.get(orgId));
			UserUtils.getSession().setAttribute(SystemConfigConstant.SESSION_USER_INFO, userInfo);
			FileNetContextHolder.getContext().setUserInfo(userInfo);
		}else{
			orgId = user != null ? user.getDepartment().getId() : "";
		}
		model.addAttribute("orgId", orgId);
		List<Map<String,String>> userOrg = orgManager.selUserOrgByUserId(principal.getId());
		model.addAttribute("userOrgList", userOrg);
		return "modules/sys/sysIndex";
	}

}

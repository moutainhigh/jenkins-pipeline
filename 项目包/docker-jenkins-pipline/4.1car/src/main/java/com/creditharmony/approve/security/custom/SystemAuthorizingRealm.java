package com.creditharmony.approve.security.custom;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.creditharmony.bpm.filenet.FileNetContextHelper;
import com.creditharmony.bpm.filenet.FileNetRequestContext;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.common.service.SystemManager;
import com.creditharmony.core.common.type.BpmConstant;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.common.web.LoginController;
import com.creditharmony.core.log.util.LogUtils;
import com.creditharmony.core.security.Principal;
import com.creditharmony.core.security.custom.CustomUsernamePasswordToken;
import com.creditharmony.core.servlet.Servlets;
import com.creditharmony.core.servlet.ValidateCodeServlet;
import com.creditharmony.core.type.ModuleName;
import com.creditharmony.core.users.entity.Menu;
import com.creditharmony.core.users.entity.Role;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.PasswordUtil;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.users.util.UserValidate;

/**
 * 系统安全认证实现类
 * @Class Name SystemAuthorizingRealm
 * @author zhangyongsheng
 * @Create In 2015年11月16日
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SystemManager systemManager;
	
	public String MODULE_KEY = ModuleName.MODULE_APPROVE.value;;

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		CustomUsernamePasswordToken token = (CustomUsernamePasswordToken) authcToken;
		char[] pwdChar = (char[])token.getCredentials();
		if (logger.isDebugEnabled()){
			logger.debug("login submit, username: {}", token.getUsername());
		}
		
		// 校验登录验证码
		if (LoginController.isValidateCodeLogin(token.getUsername(), false, false)){
			Session session = UserUtils.getSession();
			String code = (String)session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
			if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)){
				throw new AuthenticationException("msg:验证码错误, 请重试.");
			}
		}
		
		// 校验用户名密码
		User user = getSystemService().getUser(token.getUsername());
		if (user != null) {
			UserValidate.validateUser(user);
			Session session = UserUtils.getSession();
			String password = new String(pwdChar);
			FileNetRequestContext context = FileNetContextHelper.login(token.getUsername(), password);
			session.setAttribute(BpmConstant.KEY_FILENET, context);
			context.setUserInfo(user);
			UserUtils.getSession().setAttribute(SystemConfigConstant.SESSION_USER_INFO, user);
			return new SimpleAuthenticationInfo(new Principal(user, token.isMobileLogin()), 
					password, getName());
		} else {
			return null;
		}
	}
	
	@Override
	protected void checkPermission(Permission permission, AuthorizationInfo info) {
		authorizationValidate(permission);
		super.checkPermission(permission, info);
	}
	
	@Override
	protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
        		authorizationValidate(permission);
            }
        }
		return super.isPermitted(permissions, info);
	}
	
	@Override
	public boolean isPermitted(PrincipalCollection principals, Permission permission) {
		authorizationValidate(permission);
		return super.isPermitted(principals, permission);
	}
	
	@Override
	protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
            	authorizationValidate(permission);
            }
        }
		return super.isPermittedAll(permissions, info);
	}
	
	/**
	 * 授权验证方法
	 * @param permission
	 */
	private void authorizationValidate(Permission permission){
		// 模块授权预留接口
	}
	
	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(PasswordUtil.HASH_ALGORITHM);
		matcher.setHashIterations(PasswordUtil.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

	/**
	 * 清空所有关联认证
	 * @Deprecated 不需要清空，授权缓存保存到session中
	 */
	@Deprecated
	public void clearAllCachedAuthorizationInfo() {
//		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
//		if (cache != null) {
//			for (Object key : cache.keys()) {
//				cache.remove(key);
//			}
//		}
	}

	/**
	 * 获取系统业务对象
	 */
	public SystemManager getSystemService() {
		if (systemManager == null){
			systemManager = SpringContextHolder.getBean(SystemManager.class);
		}
		return systemManager;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) getAvailablePrincipal(principals);
		User user = getSystemService().getUserByLoginName(principal.getLoginName());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<Menu> list = UserUtils.getMenuList(MODULE_KEY);
			for (Menu menu : list){
				if (StringUtils.isNotBlank(menu.getPermission())){
					// 添加基于Permission的权限信息
					for (String permission : StringUtils.split(menu.getPermission(),",")){
						info.addStringPermission(permission);
					}
				}
			}
			// 添加用户权限
			info.addStringPermission("user");
			// 添加用户角色信息
			for (Role role : user.getRoleList()){
				info.addRole(role.getEnName());
			}
			// 更新登录IP和时间
			getSystemService().updateUserLoginInfo(user);
			// 记录登录日志
			LogUtils.saveLog(Servlets.getRequest(), "系统登录");
			return info;
		} else {
			return null;
		}
	}
	
	
}

package com.creditharmony.approve.security.custom;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.creditharmony.core.security.custom.CustomCredentialsMatcher;
import com.creditharmony.core.type.ModuleName;

/**
 * 自定义的安全认证实现类
 * @Class Name CustomAuthorizingRealm
 * @author zhangyongsheng
 * @Create In 2015年11月9日
 */
public class CustomAuthorizingRealm extends SystemAuthorizingRealm {

	@Autowired
	private CustomCredentialsMatcher credentialsMatcher;
	
	private CacheManager cacheManager;
	
	private static final String CURRENT_MODULE_KEY = ModuleName.MODULE_APPROVE.value;
	
	/**
	 * 授权查询回调函数
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		super.MODULE_KEY = CURRENT_MODULE_KEY;
		return super.doGetAuthorizationInfo(principals);
	}
	
	public void initCredentialsMatcher() {
		//重写shiro的密码验证，让shiro用自定义的验证
		setCredentialsMatcher(credentialsMatcher);
	}
	
	public CustomCredentialsMatcher getCredentialsMatcher() {
        return credentialsMatcher;
    }

    public void setCredentialsMatcher(CustomCredentialsMatcher credentialsMatcher) {
        this.credentialsMatcher = credentialsMatcher;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
	
}

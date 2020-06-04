package com.creditharmony.approve.users.entity;

import java.util.Date;
import java.util.List;

import com.creditharmony.core.config.Global;
import com.creditharmony.core.persistence.DataEntity;

/**
 * 用户实体
 * @Class Name UserInfo
 * @author 张永生
 * @Create In 2015年12月8日
 */
public class UserInfo extends DataEntity<UserInfo> {

	private static final long serialVersionUID = 1L;
	private String companyId;	    // 归属公司Id
	private String departmentId;	// 归属部门Id
	private String loginName;		// 登录名
	private String password;		// 密码
	private String userCode;        // 工号,对应emp_code
	private String name;			// 姓名
	private String email;			// 邮箱
	private String phone;			// 电话
	private String mobile;			// 手机
	private String userType;		// 用户类型
	private String loginIp;			// 最后登陆IP
	private Date loginDate;			// 最后登陆日期
	private String status;			// 状态:1-允许登录;0-不允许登录
	private String oldLoginName;	// 原登录名
	private String newPassword;		// 新密码
	private String oldLoginIp;		// 上次登陆IP
	private Date oldLoginDate;		// 上次登陆日期
	
	private String hasLogin;    //是否登录用户与员工
	private Date entryTime;   //入职时间
	private String sex;         //用户性别
	private int level;		//用户等级
	private String posPwd;       // pos密码
	private String callEmpCode;       // 外呼员工号,汇诚使用

	//辅助字段
	private List<OrgInfo> orgList;    
	//辅助字段
	private List<RoleInfo> roleList;    
	
	public UserInfo() {
		super();
		this.status = Global.YES;
	}
	
	public UserInfo(String id){
		super(id);
	}

	public UserInfo(String id, String loginName){
		super(id);
		this.loginName = loginName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOldLoginName() {
		return oldLoginName;
	}

	public void setOldLoginName(String oldLoginName) {
		this.oldLoginName = oldLoginName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldLoginIp() {
		return oldLoginIp;
	}

	public void setOldLoginIp(String oldLoginIp) {
		this.oldLoginIp = oldLoginIp;
	}

	public Date getOldLoginDate() {
		return oldLoginDate;
	}

	public void setOldLoginDate(Date oldLoginDate) {
		this.oldLoginDate = oldLoginDate;
	}
	
	public List<OrgInfo> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<OrgInfo> orgList) {
		this.orgList = orgList;
	}
	
	public List<RoleInfo> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleInfo> roleList) {
		this.roleList = roleList;
	}

	public String getHasLogin() {
		return hasLogin;
	}

	public void setHasLogin(String hasLogin) {
		this.hasLogin = hasLogin;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPosPwd() {
		return posPwd;
	}

	public void setPosPwd(String posPwd) {
		this.posPwd = posPwd;
	}

	public String getCallEmpCode() {
		return callEmpCode;
	}

	public void setCallEmpCode(String callEmpCode) {
		this.callEmpCode = callEmpCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result
				+ ((departmentId == null) ? 0 : departmentId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((loginDate == null) ? 0 : loginDate.hashCode());
		result = prime * result + ((loginIp == null) ? 0 : loginIp.hashCode());
		result = prime * result
				+ ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((newPassword == null) ? 0 : newPassword.hashCode());
		result = prime * result
				+ ((oldLoginDate == null) ? 0 : oldLoginDate.hashCode());
		result = prime * result
				+ ((oldLoginIp == null) ? 0 : oldLoginIp.hashCode());
		result = prime * result
				+ ((oldLoginName == null) ? 0 : oldLoginName.hashCode());
		result = prime * result + ((orgList == null) ? 0 : orgList.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((userCode == null) ? 0 : userCode.hashCode());
		result = prime * result
				+ ((userType == null) ? 0 : userType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (departmentId == null) {
			if (other.departmentId != null)
				return false;
		} else if (!departmentId.equals(other.departmentId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (loginDate == null) {
			if (other.loginDate != null)
				return false;
		} else if (!loginDate.equals(other.loginDate))
			return false;
		if (loginIp == null) {
			if (other.loginIp != null)
				return false;
		} else if (!loginIp.equals(other.loginIp))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (newPassword == null) {
			if (other.newPassword != null)
				return false;
		} else if (!newPassword.equals(other.newPassword))
			return false;
		if (oldLoginDate == null) {
			if (other.oldLoginDate != null)
				return false;
		} else if (!oldLoginDate.equals(other.oldLoginDate))
			return false;
		if (oldLoginIp == null) {
			if (other.oldLoginIp != null)
				return false;
		} else if (!oldLoginIp.equals(other.oldLoginIp))
			return false;
		if (oldLoginName == null) {
			if (other.oldLoginName != null)
				return false;
		} else if (!oldLoginName.equals(other.oldLoginName))
			return false;
		if (orgList == null) {
			if (other.orgList != null)
				return false;
		} else if (!orgList.equals(other.orgList))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		if (userType == null) {
			if (other.userType != null)
				return false;
		} else if (!userType.equals(other.userType))
			return false;
		return true;
	}

	
}
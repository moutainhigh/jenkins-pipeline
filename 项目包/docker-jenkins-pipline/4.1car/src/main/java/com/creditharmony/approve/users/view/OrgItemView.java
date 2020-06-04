package com.creditharmony.approve.users.view;

import java.io.Serializable;

/**
 * 组织机构VIEW
 * 用于封装设置机构组时选择的机构数据
 * @Class Name OrgItemView
 * @author 张永生
 * @Create In 2015年12月17日
 */
public class OrgItemView implements Serializable{

	private static final long serialVersionUID = 5366506923423905388L;
	
	private String orgId;
	private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrgItemView other = (OrgItemView) obj;
		if (orgId == null) {
			if (other.orgId != null)
				return false;
		} else if (!orgId.equals(other.orgId))
			return false;
		return true;
	}
	
	

}

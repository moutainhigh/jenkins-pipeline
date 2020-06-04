package com.creditharmony.approve.users.view;

import java.io.Serializable;
import java.util.List;


/**
 * 用户组织机构VIEW
 * 用于接收设置机构组时选择的机构组数据
 * @Class Name UserOrgView
 * @author 张永生
 * @Create In 2015年11月27日
 */
public class UserOrgView implements Serializable{

	private static final long serialVersionUID = -1387671849344524574L;

	private String userId;
	private List<OrgItemView> orgIdList;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<OrgItemView> getOrgIdList() {
		return orgIdList;
	}
	public void setOrgIdList(List<OrgItemView> orgIdList) {
		this.orgIdList = orgIdList;
	}
	
	
}

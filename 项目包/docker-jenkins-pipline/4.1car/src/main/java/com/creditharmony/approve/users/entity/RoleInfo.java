package com.creditharmony.approve.users.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 角色Info实体
 * @Class Name RoleInfo
 * @author 陈伟东
 * @Create In 2015年12月25日
 */
public class RoleInfo extends DataEntity<RoleInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name; 			// 角色名称
	private String enName;			// 英文名称
	private String roleType;		// 角色类型
	private String dataScope;		// 数据范围
	private String oldName; 		// 原角色名称
	private String oldEnName;		// 原英文名称
	private String sysData; 		// 是否是系统数据
	private String useable; 		// 是否是可用
	private String orgType;         // 组织机构类型
	
	public RoleInfo() {
		super();
	}
	
	public RoleInfo(String id){
		super(id);
	}
	

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public String getSysData() {
		return sysData;
	}

	public void setSysData(String sysData) {
		this.sysData = sysData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}
	
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getOldEnName() {
		return oldEnName;
	}

	public void setOldEnName(String oldEnName) {
		this.oldEnName = oldEnName;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
		

}

package com.creditharmony.approve.common.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 省市级实体
 * 
 * @Class Name City
 * @author zhangyongsheng
 * @Create In 2015年11月17日
 */
public class CityInfo extends DataEntity<CityInfo> {

	private static final long serialVersionUID = -7338836374301816767L;

	private String name;		// 区域名称
	private String shortName;	// 区域简称
	private String code;		// 区域编码
	private String type;		// 区域类型(0省级，1市级，区）
	private String parentId;	// 父ID
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getParentId() {
		return parentId;
	}
	
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}

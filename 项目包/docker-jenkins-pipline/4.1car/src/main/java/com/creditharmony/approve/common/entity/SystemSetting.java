/**
 * @Probject Name: chp-loan
 * @Path: com.creditharmony.loan.common.entitySystemSetting.java
 * @Create By 王彬彬
 * @Create In 2016年3月6日 下午2:07:55
 */
package com.creditharmony.approve.common.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 系统设定数据
 * @Class Name SystemSetting
 * @author 王彬彬
 * @Create In 2016年3月6日
 */

@SuppressWarnings("serial")
public class SystemSetting extends DataEntity<SystemSetting>{
	private String id;
	
	//系统设定标记
	private String sysFlag;
	
	//系统设定名称
	private String sysName;
	
	//设定值
	private String sysValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getSysValue() {
		return sysValue;
	}

	public void setSysValue(String sysValue) {
		this.sysValue = sysValue;
	}
	
	
}

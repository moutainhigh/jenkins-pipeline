package com.creditharmony.approve.management.entity;

import com.creditharmony.core.persistence.TreeEntity;

/**
 * 拒绝信息实体
 * @Class Name GlRefuse
 * @author 刘燕军
 * @Create In 2015年12月4日
 */
public class GlRefuse extends TreeEntity<GlRefuse>{
	
	private static final long serialVersionUID = 1L;
	private String refuseCode;		// 拒借编码
	private String refuseName;		// 拒借名称
	private String refuseGrade;		// 分类
	
	public GlRefuse(){
		super();
		this.sort = 30;
	}

	public GlRefuse(String id){
		super(id);
	}
	
	public GlRefuse getParent() {
		return parent;
	}

	public void setParent(GlRefuse parent) {
		this.parent = parent;
	}
	
	
	public String getRefuseCode() {
		return refuseCode;
	}
	public void setRefuseCode(String refuseCode) {
		this.refuseCode = refuseCode;
	}
	public String getRefuseName() {
		return refuseName;
	}
	public void setRefuseName(String refuseName) {
		this.refuseName = refuseName;
	}
	public String getRefuseGrade() {
		return refuseGrade;
	}
	public void setRefuseGrade(String refuseGrade) {
		this.refuseGrade = refuseGrade;
	}
}

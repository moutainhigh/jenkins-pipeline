package com.creditharmony.approve.management.entity;

import java.util.Date;

import com.creditharmony.core.persistence.TreeEntity;

public class Refuse extends TreeEntity<Refuse>{
	
	private static final long serialVersionUID = 1L;
	
    private String refuseCode; 		 // 拒借编码
    private String refuseName;  	 // 拒借名称
    private String refuseGrade;  	 // 拒借类型
    
	public Refuse(){
		super();
		this.sort = 30;
	}

	public Refuse(String id){
		super(id);
	}
	
	public Refuse getParent() {
		return parent;
	}

	public void setParent(Refuse parent) {
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
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}


}
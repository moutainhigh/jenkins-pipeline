package com.creditharmony.approve.management.entity;

import com.creditharmony.core.persistence.TreeEntity;
/**
 * 客户放弃码实体
 * @Class Name GlGiveup
 * @author 刘燕军
 * @Create In 2015年12月5日
 */
public class GlGiveup extends TreeEntity<GlGiveup>{

	private static final long serialVersionUID = 1L;
	private String giveupCode;       //放弃编码
	private String giveupName;       //放弃名称
	private String giveupGrade;      //放弃类型
	//private String parentId;         //父ID
	 private Integer refuseIndex;	 // 排序
	public GlGiveup(){
		super();
		this.sort = 30;
	}
	public GlGiveup(String id){
		super(id);
	}
	public GlGiveup getParent() {
		return parent;
	}
	public void setParent(GlGiveup parent) {
		this.parent = parent;
	}
	public String getGiveupCode() {
		return giveupCode;
	}

	public void setGiveupCode(String giveupCode) {
		this.giveupCode = giveupCode;
	}

	public String getGiveupName() {
		return giveupName;
	}

	public void setGiveupName(String giveupName) {
		this.giveupName = giveupName;
	}

	public String getGiveupGrade() {
		return giveupGrade;
	}

	public void setGiveupGrade(String giveupGrade) {
		this.giveupGrade = giveupGrade;
	}

	/*public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}*/
	public Integer getRefuseIndex() {
		return refuseIndex;
	}
	public void setRefuseIndex(Integer refuseIndex) {
		this.refuseIndex = refuseIndex;
	}
}

package com.creditharmony.approve.document.entity;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 资料审核股东信息
 * @Class Name ZlshJyzmGdxx
 * @author 刘燕军
 * @Create In 2015年12月7日
 */
public class ZlshJyzmGdxx extends DataEntity<ZlshJyzmGdxx>{

    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
    
	 private String id;
	
	private String rJyzmId;

    private String gdxxGdname;

    private String gdxxRelation;

    private String gdxxRatio;

    private String dictCheckType;

	public String getrJyzmId() {
		return rJyzmId;
	}

	public void setrJyzmId(String rJyzmId) {
		this.rJyzmId = rJyzmId;
	}

	public String getGdxxGdname() {
		return gdxxGdname;
	}

	public void setGdxxGdname(String gdxxGdname) {
		this.gdxxGdname = gdxxGdname;
	}

	public String getGdxxRelation() {
		return gdxxRelation;
	}

	public void setGdxxRelation(String gdxxRelation) {
		this.gdxxRelation = gdxxRelation;
	}

	public String getGdxxRatio() {
		return gdxxRatio;
	}

	public void setGdxxRatio(String gdxxRatio) {
		this.gdxxRatio = gdxxRatio;
	}

	public String getDictCheckType() {
		return dictCheckType;
	}

	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
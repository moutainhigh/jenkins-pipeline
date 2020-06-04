package com.creditharmony.approve.management.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name RulesData
 * @author 袁李杰
 * @Create In 2016年03月15日
 * 犯欺诈-销售人员信息实体类
 */
public class SalesStaff extends DataEntity<SalesStaff>{
 
	private static final long serialVersionUID = 1L;

    private String salesCode;       //员工号
    private String salesName;       //姓名
    private String salesTel;        //手机号
    private String workFlag;        //是否在职
    private String dictJobGrade;    //职位等级

	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode == null ? null : salesCode.trim();
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName == null ? null : salesName.trim();
	}

	public String getSalesTel() {
		return salesTel;
	}

	public void setSalesTel(String salesTel) {
		this.salesTel = salesTel == null ? null : salesTel.trim();
	}

	public String getWorkFlag() {
		return workFlag;
	}

	public void setWorkFlag(String workFlag) {
		this.workFlag = workFlag == null ? null : workFlag.trim();
	}

	public String getDictJobGrade() {
		return dictJobGrade;
	}

	public void setDictJobGrade(String dictJobGrade) {
		this.dictJobGrade = dictJobGrade== null ? null : dictJobGrade.trim();
	}

}
package com.creditharmony.approve.common.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 汇金产品类型管理-Entity
 * 
 * @Class Name LoanPrdTypeEntity
 * @author 李静辉
 * @Create In 2015年12月9日
 */
public class LoanPrdTypeEntity extends DataEntity<LoanPrdTypeEntity> {

	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private String no;
	/** 产品类型ID */
    private String id;
    /** 产品类型编号 */
    private String productTypeCode;
    /** 业务类型 */
    private String classType;
    /** 产品类型 */
    private String prductType;
    /** 产品类型名称 */
    private String productTypeName;
    /** 产品类型状态 */
    private String productTypeStatus;
    /** 启动日期 */
    private Date startDay;
    /** 结束日期 */
    private Date closeDay;
    /** 描述 */
    private String remark;
    /** 创建人 */
    private String createBy;
    /** 创建时间 */
    private Date createTime;
    /** 修改人 */
    private String modifyBy;
    /** 修改时间 */
    private Date modifyTime;
    
	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the productTypeCode
	 */
	public String getProductTypeCode() {
		return productTypeCode;
	}
	/**
	 * @param productTypeCode the productTypeCode to set
	 */
	public void setProductTypeCode(String productTypeCode) {
		this.productTypeCode = productTypeCode;
	}
	/**
	 * @return the classType
	 */
	public String getClassType() {
		return classType;
	}
	/**
	 * @param classType the classType to set
	 */
	public void setClassType(String classType) {
		this.classType = classType;
	}
	/**
	 * @return the prductType
	 */
	public String getPrductType() {
		return prductType;
	}
	/**
	 * @param prductType the prductType to set
	 */
	public void setPrductType(String prductType) {
		this.prductType = prductType;
	}
	/**
	 * @return the productTypeName
	 */
	public String getProductTypeName() {
		return productTypeName;
	}
	/**
	 * @param productTypeName the productTypeName to set
	 */
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	/**
	 * @return the productTypeStatus
	 */
	public String getProductTypeStatus() {
		return productTypeStatus;
	}
	/**
	 * @param productTypeStatus the productTypeStatus to set
	 */
	public void setProductTypeStatus(String productTypeStatus) {
		this.productTypeStatus = productTypeStatus;
	}
	/**
	 * @return the startDay
	 */
	public Date getStartDay() {
		return startDay;
	}
	/**
	 * @param startDay the startDay to set
	 */
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
	/**
	 * @return the closeDay
	 */
	public Date getCloseDay() {
		return closeDay;
	}
	/**
	 * @param closeDay the closeDay to set
	 */
	public void setCloseDay(Date closeDay) {
		this.closeDay = closeDay;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the createBy
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * @param createBy the createBy to set
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the modifyBy
	 */
	public String getModifyBy() {
		return modifyBy;
	}
	/**
	 * @param modifyBy the modifyBy to set
	 */
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	/**
	 * @return the modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
	/**
	 * @param modifyTime the modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
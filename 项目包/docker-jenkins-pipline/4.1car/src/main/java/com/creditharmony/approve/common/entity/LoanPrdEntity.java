package com.creditharmony.approve.common.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 汇金产品管理-Entity
 * 
 * @Class Name LoanPrdEntity
 * @author 李静辉
 * @Create In 2015年12月9日
 */
public class LoanPrdEntity extends DataEntity<LoanPrdEntity> {

	private static final long serialVersionUID = 1L;

	/** 序号 */
	private String no;
	/** 产品ID */
    private String id;
    /** 产品类型 */
    private String productType;
    /** 产品编号 */
    private String productCode;
    /** 产品名称 */
    private String productName;
    /** 产品状态 */
    private String productStatus;
    /** 最低限额 */
    private BigDecimal limitLower;
    /** 最高限额 */
    private BigDecimal limitUpper;
    /** 产品利率上限 */
    private BigDecimal productLimitUpperraio;
    /** 产品利率下限 */
    private BigDecimal productLimitLowerraio;
    /** 期数 */
    private String productMonths;
    /** 折扣率 */
    private String discountRate;
    /** 启动日期 */
    private Date startDay;
    /** 结束日期 */
    private Date closeDay;
    /** 协议 */
    private String treaty;
    /** 公式 */
    private String formula;
    /** 业务类型 */
    private String classType;
    /** 利率 */
    private String interestRate;
    /** 所需材料 */
    private String material;
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
    
    List<String> lsMonths;
    
	/**
	 * @return the lsMonths
	 */
	public List<String> getLsMonths() {
		return lsMonths;
	}
	/**
	 * @param lsMonths the lsMonths to set
	 */
	public void setLsMonths(List<String> lsMonths) {
		this.lsMonths = lsMonths;
	}
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
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the productStatus
	 */
	public String getProductStatus() {
		return productStatus;
	}
	/**
	 * @param productStatus the productStatus to set
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	/**
	 * @return the limitLower
	 */
	public BigDecimal getLimitLower() {
		return limitLower;
	}
	/**
	 * @param limitLower the limitLower to set
	 */
	public void setLimitLower(BigDecimal limitLower) {
		this.limitLower = limitLower;
	}
	/**
	 * @return the limitUpper
	 */
	public BigDecimal getLimitUpper() {
		return limitUpper;
	}
	/**
	 * @param limitUpper the limitUpper to set
	 */
	public void setLimitUpper(BigDecimal limitUpper) {
		this.limitUpper = limitUpper;
	}
	/**
	 * @return the productLimitUpperraio
	 */
	public BigDecimal getProductLimitUpperraio() {
		return productLimitUpperraio;
	}
	/**
	 * @param productLimitUpperraio the productLimitUpperraio to set
	 */
	public void setProductLimitUpperraio(BigDecimal productLimitUpperraio) {
		this.productLimitUpperraio = productLimitUpperraio;
	}
	/**
	 * @return the productLimitLowerraio
	 */
	public BigDecimal getProductLimitLowerraio() {
		return productLimitLowerraio;
	}
	/**
	 * @param productLimitLowerraio the productLimitLowerraio to set
	 */
	public void setProductLimitLowerraio(BigDecimal productLimitLowerraio) {
		this.productLimitLowerraio = productLimitLowerraio;
	}
	/**
	 * @return the productMonths
	 */
	public String getProductMonths() {
		return productMonths;
	}
	/**
	 * @param productMonths the productMonths to set
	 */
	public void setProductMonths(String productMonths) {
		this.productMonths = productMonths;
	}
	/**
	 * @return the discountRate
	 */
	public String getDiscountRate() {
		return discountRate;
	}
	/**
	 * @param discountRate the discountRate to set
	 */
	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
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
	 * @return the treaty
	 */
	public String getTreaty() {
		return treaty;
	}
	/**
	 * @param treaty the treaty to set
	 */
	public void setTreaty(String treaty) {
		this.treaty = treaty;
	}
	/**
	 * @return the formula
	 */
	public String getFormula() {
		return formula;
	}
	/**
	 * @param formula the formula to set
	 */
	public void setFormula(String formula) {
		this.formula = formula;
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
	 * @return the interestRate
	 */
	public String getInterestRate() {
		return interestRate;
	}
	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}
	/**
	 * @param material the material to set
	 */
	public void setMaterial(String material) {
		this.material = material;
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
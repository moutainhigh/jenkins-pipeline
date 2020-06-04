package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class JkProducts extends DataEntity<JkProducts> {

    
	private static final long serialVersionUID = 1L;

	private String productName;  //产品名称
    private String productCode;  // 产品编号    
    private String productType;  // 产品类型
    private String productStatus;  // 产品状态
    private BigDecimal limitLower;  // 最低限额
    private BigDecimal limitUpper;  // 最高限额
    private BigDecimal productLimitUpperraio;  //  产品利率上限
    private BigDecimal productLimitLowerraio;  //  产品利率下限
    private String productMonths;  // 期数
    private BigDecimal discountRate;  // 折扣率
    private Date startDay;  // 启动日期
    private Date closeDay;  // 结束日期
    private String treaty;  // 协议
    private String formula;  // 公式
    private String classtype;  // 业务类型
    private String cautionerLimitAmount;// 添加追加保证人的金额
    private String createBy;
    private String interestRate; // 利率
    private Date createTime;

    private String lastmodifyBy;

    private Date modifyTime;

    

    public BigDecimal getLimitLower() {
        return limitLower;
    }

    public void setLimitLower(BigDecimal limitLower) {
        this.limitLower = limitLower;
    }

    public BigDecimal getLimitUpper() {
        return limitUpper;
    }

    public void setLimitUpper(BigDecimal limitUpper) {
        this.limitUpper = limitUpper;
    }

    public String getTreaty() {
        return treaty;
    }

    public void setTreaty(String treaty) {
        this.treaty = treaty == null ? null : treaty.trim();
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula == null ? null : formula.trim();
    }

    public String getClasstype() {
        return classtype;
    }

    public void setClasstype(String classtype) {
        this.classtype = classtype == null ? null : classtype.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastmodifyBy() {
        return lastmodifyBy;
    }

    public void setLastmodifyBy(String lastmodifyBy) {
        this.lastmodifyBy = lastmodifyBy == null ? null : lastmodifyBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getProductLimitUpperraio() {
		return productLimitUpperraio;
	}

	public void setProductLimitUpperraio(BigDecimal productLimitUpperraio) {
		this.productLimitUpperraio = productLimitUpperraio;
	}

	public BigDecimal getProductLimitLowerraio() {
		return productLimitLowerraio;
	}

	public void setProductLimitLowerraio(BigDecimal productLimitLowerraio) {
		this.productLimitLowerraio = productLimitLowerraio;
	}

	public String getProductMonths() {
		return productMonths;
	}

	public void setProductMonths(String productMonths) {
		this.productMonths = productMonths;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getCloseDay() {
		return closeDay;
	}

	public void setCloseDay(Date closeDay) {
		this.closeDay = closeDay;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}


	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public String getCautionerLimitAmount() {
		return cautionerLimitAmount;
	}

	public void setCautionerLimitAmount(String cautionerLimitAmount) {
		this.cautionerLimitAmount = cautionerLimitAmount;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
}
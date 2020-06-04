package com.creditharmony.approve.carloan.entity.ex;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 车借--查询列表、查询条件
 * @Class Name CarVerifyListEx
 * @author 申诗阔
 * @Create In 2016年1月26日
 */
public class CarVerifyListEx extends DataEntity<CarVerifyListEx> {

	private static final long serialVersionUID = 3434129579845298813L;
	
	private String loanCode;			// 借款编码
	private String applyId;				// 流程ID
	private String loanCustomerName;	// 客户姓名
	private String province;			// 门店省
	private String city;				// 门店市
	private String provinceName;		// 门店省名称
	private String cityName;			// 门店市名称
	private String loanTermOrgId;		// 门店
	private String orgName;				// 门店名称
	private Double loanApplyAmount;		// 申请借款金额
	private Double storeAssessAmount;	// 门店评估金额
	private Double loanDivStore;	// 申请 / 门店
	private Integer loanMonths;			// 借款期限
	private String dictProductType;		// 产品类型
	private String plateNumbers;		// 车牌号码
	private Date customerIntoTime;		// 进件时间
	private String dictLoanStatus;		// 借款状态
	private String loanStatusName;		// 借款状态名称
	private Date minCustomerIntoTime;	// 最小进件时间
	private Date maxCustomerIntoTime;	// 最大进件时间
	private String dictSettleRelend;	// 是否循环借
	private String loanAdditionalApplyid;// 是否展期
	private String createBy;			// 审批姓名
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getLoanCustomerName() {
		return loanCustomerName;
	}
	public void setLoanCustomerName(String loanCustomerName) {
		this.loanCustomerName = loanCustomerName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getLoanTermOrgId() {
		return loanTermOrgId;
	}
	public void setLoanTermOrgId(String loanTermOrgId) {
		this.loanTermOrgId = loanTermOrgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Double getLoanApplyAmount() {
		return loanApplyAmount;
	}
	public void setLoanApplyAmount(Double loanApplyAmount) {
		this.loanApplyAmount = loanApplyAmount;
	}
	public Double getStoreAssessAmount() {
		return storeAssessAmount;
	}
	public void setStoreAssessAmount(Double storeAssessAmount) {
		this.storeAssessAmount = storeAssessAmount;
	}
	public Integer getLoanMonths() {
		return loanMonths;
	}
	public void setLoanMonths(Integer loanMonths) {
		this.loanMonths = loanMonths;
	}
	public String getDictProductType() {
		return dictProductType;
	}
	public void setDictProductType(String dictProductType) {
		this.dictProductType = dictProductType;
	}
	public String getPlateNumbers() {
		return plateNumbers;
	}
	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}
	public Date getCustomerIntoTime() {
		return customerIntoTime;
	}
	public void setCustomerIntoTime(Date customerIntoTime) {
		this.customerIntoTime = customerIntoTime;
	}
	public String getDictLoanStatus() {
		return dictLoanStatus;
	}
	public void setDictLoanStatus(String dictLoanStatus) {
		this.dictLoanStatus = dictLoanStatus;
	}
	public String getLoanStatusName() {
		return loanStatusName;
	}
	public void setLoanStatusName(String loanStatusName) {
		this.loanStatusName = loanStatusName;
	}
	public Date getMinCustomerIntoTime() {
		return minCustomerIntoTime;
	}
	public void setMinCustomerIntoTime(Date minCustomerIntoTime) {
		this.minCustomerIntoTime = minCustomerIntoTime;
	}
	public Date getMaxCustomerIntoTime() {
		return maxCustomerIntoTime;
	}
	public void setMaxCustomerIntoTime(Date maxCustomerIntoTime) {
		this.maxCustomerIntoTime = maxCustomerIntoTime;
	}
	public String getDictSettleRelend() {
		return dictSettleRelend;
	}
	public void setDictSettleRelend(String dictSettleRelend) {
		this.dictSettleRelend = dictSettleRelend;
	}
	public String getLoanAdditionalApplyid() {
		return loanAdditionalApplyid;
	}
	public void setLoanAdditionalApplyid(String loanAdditionalApplyid) {
		this.loanAdditionalApplyid = loanAdditionalApplyid;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Double getLoanDivStore() {
		return loanDivStore;
	}
	public void setLoanDivStore(Double loanDivStore) {
		this.loanDivStore = loanDivStore;
	}
	
}

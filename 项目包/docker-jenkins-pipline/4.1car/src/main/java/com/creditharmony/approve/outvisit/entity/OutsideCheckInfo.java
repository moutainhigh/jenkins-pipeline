package com.creditharmony.approve.outvisit.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 外访_外访任务详情
 * @Class Name OutsideCheckInfo
 * @author 赖敏
 * @Create In 2015年12月7日
 */
public class OutsideCheckInfo extends DataEntity<OutsideCheckInfo>{
	
	private static final long serialVersionUID = 53256753985019169L;
	private String taskId; 				// 外访任务ID
	private String dictCustomerType;	// 借款人类型(主借人:0/共借人:1)
	private Object checkJson; 			// JSON
	private String liveProvince; 		// 居住省
	private String liveCity; 			// 居住市
	private String liveArea; 			// 居住区
	private String liveAddress; 		// 居住地址
	private String houseProvince; 		// 房产省
	private String houseCity; 			// 房产市
	private String houseArea; 			// 房产区
	private String houseAddress; 		// 房产地址
	private String workUnitProvince; 	// 工作单位省
	private String workUnitCity; 		// 工作单位市
	private String workUnitArea; 		// 工作单位区
	private String workUnitAddress; 	// 工作单位地址
	private String operationProvince; 	// 经营生产省
	private String operationCity; 		// 经营生产市
	private String operationArea; 		// 经营生产区
	private String operationAddress; 	// 经营生产地址
	private String customerName;		// 主借人/共借人姓名
	private String telephone;			// 联系电话
	private String remark;				// 外访家庭备注
	private String visitWorkRemark;		// 外访单位备注
	private String visitType;			// 外访类型
	
	public String getTaskId() {
		return taskId;
	}
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getDictCustomerType() {
		return dictCustomerType;
	}
	
	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}
	
	public Object getCheckJson() {
		return checkJson;
	}
	
	public void setCheckJson(Object checkJson) {
		this.checkJson = checkJson;
	}
	
	public String getLiveProvince() {
		return liveProvince;
	}
	
	public void setLiveProvince(String liveProvince) {
		this.liveProvince = liveProvince;
	}
	
	public String getLiveCity() {
		return liveCity;
	}
	
	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}
	
	public String getLiveArea() {
		return liveArea;
	}
	
	public void setLiveArea(String liveArea) {
		this.liveArea = liveArea;
	}
	
	public String getLiveAddress() {
		return liveAddress;
	}
	
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
	
	public String getHouseProvince() {
		return houseProvince;
	}
	
	public void setHouseProvince(String houseProvince) {
		this.houseProvince = houseProvince;
	}
	
	public String getHouseCity() {
		return houseCity;
	}
	
	public void setHouseCity(String houseCity) {
		this.houseCity = houseCity;
	}
	
	public String getHouseArea() {
		return houseArea;
	}
	
	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}
	
	public String getHouseAddress() {
		return houseAddress;
	}
	
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	
	public String getWorkUnitProvince() {
		return workUnitProvince;
	}
	
	public void setWorkUnitProvince(String workUnitProvince) {
		this.workUnitProvince = workUnitProvince;
	}
	
	public String getWorkUnitCity() {
		return workUnitCity;
	}
	
	public void setWorkUnitCity(String workUnitCity) {
		this.workUnitCity = workUnitCity;
	}
	
	public String getWorkUnitArea() {
		return workUnitArea;
	}
	
	public void setWorkUnitArea(String workUnitArea) {
		this.workUnitArea = workUnitArea;
	}
	
	public String getWorkUnitAddress() {
		return workUnitAddress;
	}
	
	public void setWorkUnitAddress(String workUnitAddress) {
		this.workUnitAddress = workUnitAddress;
	}
	
	public String getOperationProvince() {
		return operationProvince;
	}
	
	public void setOperationProvince(String operationProvince) {
		this.operationProvince = operationProvince;
	}
	
	public String getOperationCity() {
		return operationCity;
	}
	
	public void setOperationCity(String operationCity) {
		this.operationCity = operationCity;
	}
	
	public String getOperationArea() {
		return operationArea;
	}
	
	public void setOperationArea(String operationArea) {
		this.operationArea = operationArea;
	}
	
	public String getOperationAddress() {
		return operationAddress;
	}
	
	public void setOperationAddress(String operationAddress) {
		this.operationAddress = operationAddress;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVisitWorkRemark() {
		return visitWorkRemark;
	}

	public void setVisitWorkRemark(String visitWorkRemark) {
		this.visitWorkRemark = visitWorkRemark;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}    
	
}
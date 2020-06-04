package com.creditharmony.approve.internet.entity.ex;

import java.io.Serializable;
import java.util.List;
/**
 * 网查信息参数
 * @Class Name OutsideNetInfoParam
 * @author 刘燕军
 * @Create In 2015年12月11日
 */
public class OutsideNetInfoParam implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String customerType;
	private List<WorkNameEx> workName; // 公司名称
	private String certNum; // 身份证号
	private List<OutsideNetInfoEx> certCheck; // 专网查询身份證號
	public List<WorkNameEx> getWorkName() {
		return workName;
	}
	public void setWorkName(List<WorkNameEx> workName) {
		this.workName = workName;
	}
	public String getCertNum() {
		return certNum;
	}
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
	public List<OutsideNetInfoEx> getCertCheck() {
		return certCheck;
	}
	public void setCertCheck(List<OutsideNetInfoEx> certCheck) {
		this.certCheck = certCheck;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
}

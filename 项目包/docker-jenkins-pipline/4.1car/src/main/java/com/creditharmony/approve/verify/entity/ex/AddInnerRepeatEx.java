package com.creditharmony.approve.verify.entity.ex;

import java.io.Serializable;
/**
 * 需要在决策时查重的信息的实体
 * @Class Name AddInnerRepeatEx
 * @author 刘燕军
 * @Create In 2016年1月5日
 */
public class AddInnerRepeatEx implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String phone; // 手机号
	private String tel; // 固定电话
	private String name; // 公司名称
	private String province; // 公司地址省
	private String city; // 公司地址市
	private String area; // 公司地址区
	private String address; // 公司地址
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvice() {
		return province;
	}
	public void setProvice(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}

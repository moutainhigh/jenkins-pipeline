package com.creditharmony.approve.phone.entity.ex;

import java.util.List;

import com.creditharmony.approve.phone.entity.DhzhDhgxsh;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;

/**
 * 电话照会_联系人审核 及相应的电话录音
 * @Class Name TelCheckContactInfo
 * @author 王浩
 * @Create In 2015年12月1日
 */
public class TelCheckContactPersonEx extends DhzhDhgxsh{
	
	private static final long serialVersionUID = -1187021982020223127L;
	private List<DhzhDhlyxx> dhlyxx;	// 电话录音列表
	private String dhgxshTel;			// 联系人电话
	
	// 新版申请表追加
	private List<TelCheckContactNumEx> contactNumList;// 家庭固话
	private List<TelCheckContactNumEx> phoneNumList;// 手机号
	
	public List<DhzhDhlyxx> getDhlyxx() {
		return dhlyxx;
	}

	public void setDhlyxx(List<DhzhDhlyxx> dhlyxx) {
		this.dhlyxx = dhlyxx;
	}

	public String getDhgxshTel() {
		return dhgxshTel;
	}

	public void setDhgxshTel(String dhgxshTel) {
		this.dhgxshTel = dhgxshTel;
	}

	public List<TelCheckContactNumEx> getContactNumList() {
		return contactNumList;
	}

	public void setContactNumList(List<TelCheckContactNumEx> contactNumList) {
		this.contactNumList = contactNumList;
	}

	public List<TelCheckContactNumEx> getPhoneNumList() {
		return phoneNumList;
	}

	public void setPhoneNumList(List<TelCheckContactNumEx> phoneNumList) {
		this.phoneNumList = phoneNumList;
	}

	
}

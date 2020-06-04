package com.creditharmony.approve.antifraud.entity.ex;

import java.util.List;

/**
 *  单位电话，电话录音，联系人 ,本人核实 集合
 * @Class Name PhoneWorkEx
 * @author wanglidong
 * @Create In 2016年3月2日
 */
public class PhoneListEx {
	private List<PhoneWorkEx> telWorkList;						//单位电话
	private List<PhoneRecordingEx> phoneRecordList;				//电话录音
	private List<PhoneWorkProvesEx> phoneWorkProvesList;		//联系人
	private List<PhoneConfirmEx> phoneConfirmList;				//本人核实

	public List<PhoneConfirmEx> getPhoneConfirmList() {
		return phoneConfirmList;
	}
	public void setPhoneConfirmList(List<PhoneConfirmEx> phoneConfirmList) {
		this.phoneConfirmList = phoneConfirmList;
	}
	public List<PhoneWorkProvesEx> getPhoneWorkProvesList() {
		return phoneWorkProvesList;
	}
	public void setPhoneWorkProvesList(List<PhoneWorkProvesEx> phoneWorkProvesList) {
		this.phoneWorkProvesList = phoneWorkProvesList;
	}

	public List<PhoneWorkEx> getTelWorkList() {
		return telWorkList;
	}

	public void setTelWorkList(List<PhoneWorkEx> telWorkList) {
		this.telWorkList = telWorkList;
	}

	public List<PhoneRecordingEx> getPhoneRecordList() {
		return phoneRecordList;
	}

	public void setPhoneRecordList(List<PhoneRecordingEx> phoneRecordList) {
		this.phoneRecordList = phoneRecordList;
	}
	
}

package com.creditharmony.approve.phone.entity.ex;

import java.util.List;

import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;
import com.creditharmony.approve.phone.entity.TelAuditWork;

/**
 * 电话核查_单位信息，包含电话录音
 * @Class Name TelChcekCompany
 * @author 王浩
 * @Create In 2015年12月1日
 */
public class TelCheckCompanyEx extends TelAuditWork {

	private static final long serialVersionUID = 3851377334986582204L;
	private List<WorkTelNumEx> telNumList; 	// 单位电话列表
	private List<DhzhDhlyxx> dhlyxx; 		// 电话录音列表
	private String telNum;					// 单位电话
	private List<CityInfo> cityList;
	private List<CityInfo> districtList;

	public List<WorkTelNumEx> getTelNumList() {
		return telNumList;
	}

	public void setTelNumList(List<WorkTelNumEx> telNumList) {
		this.telNumList = telNumList;
	}
	
	public String getTelNum() {
		return telNum;
	}
	
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public List<CityInfo> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityInfo> cityList) {
		this.cityList = cityList;
	}

	public List<CityInfo> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<CityInfo> districtList) {
		this.districtList = districtList;
	}

	public List<DhzhDhlyxx> getDhlyxx() {
		return dhlyxx;
	}

	public void setDhlyxx(List<DhzhDhlyxx> dhlyxx) {
		this.dhlyxx = dhlyxx;
	}
	
}

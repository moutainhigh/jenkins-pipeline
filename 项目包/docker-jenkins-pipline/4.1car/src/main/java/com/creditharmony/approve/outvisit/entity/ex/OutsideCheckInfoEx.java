package com.creditharmony.approve.outvisit.entity.ex;

import java.util.List;

import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.outvisit.entity.OutsideCheckInfo;

/**
 * 外访_外访任务详情扩展
 * @Class Name OutsideCheckInfoEx
 * @author 赖敏
 * @Create In 2016年1月5日
 */
public class OutsideCheckInfoEx extends OutsideCheckInfo{
	
	private static final long serialVersionUID = 7228273496258384238L;
	private String customerTypeName;		    // 借款人类型(主借人/共借人)
	private List<CityInfo> liveCitys;		    // 居住市列表
	private List<CityInfo> liveAreas;		    // 居住区列表
	private List<CityInfo> houseCitys;		    // 房产市列表
	private List<CityInfo> houseAreas;		    // 房产区列表
	private List<CityInfo> workUnitProvinces;	// 工作单位市列表
	private List<CityInfo> workUnitCitys;	    // 工作单位市列表
	private List<CityInfo> workUnitAreas;	    // 工作单位区列表
	private String liveProvinceName;		    // 居住地省名字
	private String liveCityName;			    // 居住地市名字
	private String liveAreaName;			    // 居住地区名字
	private String workProvinceName;		    // 工作单位省名字
	private String workCityName;			    // 工作单位市名字
	private String workAreaName;			    // 工作单位区名字
	
	public String getCustomerTypeName() {
		return customerTypeName;
	}
	
	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}
	
	public List<CityInfo> getWorkUnitProvinces() {
		return workUnitProvinces;
	}

	public void setWorkUnitProvinces(List<CityInfo> workUnitProvinces) {
		this.workUnitProvinces = workUnitProvinces;
	}

	public List<CityInfo> getLiveCitys() {
		return liveCitys;
	}
	
	public void setLiveCitys(List<CityInfo> liveCitys) {
		this.liveCitys = liveCitys;
	}
	
	public List<CityInfo> getLiveAreas() {
		return liveAreas;
	}
	
	public void setLiveAreas(List<CityInfo> liveAreas) {
		this.liveAreas = liveAreas;
	}
	
	public List<CityInfo> getHouseCitys() {
		return houseCitys;
	}
	
	public void setHouseCitys(List<CityInfo> houseCitys) {
		this.houseCitys = houseCitys;
	}
	
	public List<CityInfo> getHouseAreas() {
		return houseAreas;
	}
	
	public void setHouseAreas(List<CityInfo> houseAreas) {
		this.houseAreas = houseAreas;
	}
	
	public List<CityInfo> getWorkUnitCitys() {
		return workUnitCitys;
	}
	
	public void setWorkUnitCitys(List<CityInfo> workUnitCitys) {
		this.workUnitCitys = workUnitCitys;
	}
	
	public List<CityInfo> getWorkUnitAreas() {
		return workUnitAreas;
	}
	
	public void setWorkUnitAreas(List<CityInfo> workUnitAreas) {
		this.workUnitAreas = workUnitAreas;
	}

	public String getLiveProvinceName() {
		return liveProvinceName;
	}

	public void setLiveProvinceName(String liveProvinceName) {
		this.liveProvinceName = liveProvinceName;
	}

	public String getLiveCityName() {
		return liveCityName;
	}

	public void setLiveCityName(String liveCityName) {
		this.liveCityName = liveCityName;
	}

	public String getLiveAreaName() {
		return liveAreaName;
	}

	public void setLiveAreaName(String liveAreaName) {
		this.liveAreaName = liveAreaName;
	}

	public String getWorkProvinceName() {
		return workProvinceName;
	}

	public void setWorkProvinceName(String workProvinceName) {
		this.workProvinceName = workProvinceName;
	}

	public String getWorkCityName() {
		return workCityName;
	}

	public void setWorkCityName(String workCityName) {
		this.workCityName = workCityName;
	}

	public String getWorkAreaName() {
		return workAreaName;
	}

	public void setWorkAreaName(String workAreaName) {
		this.workAreaName = workAreaName;
	}
	
}

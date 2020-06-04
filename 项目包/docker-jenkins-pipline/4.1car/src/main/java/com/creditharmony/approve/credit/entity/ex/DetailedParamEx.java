package com.creditharmony.approve.credit.entity.ex;

import java.util.List;

import com.creditharmony.approve.credit.entity.CreditLiveInfo;
import com.creditharmony.approve.credit.entity.CreditOccupationInfo;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;

public class DetailedParamEx extends CreditReportDetailed{

	private static final long serialVersionUID = 1L;
	private List<CreditLiveInfo> liveList;					// 居住信息list
	private List<CreditOccupationInfo> occupationList;		// 单位信息list
	public List<CreditLiveInfo> getLiveList() {
		return liveList;
	}
	public void setLiveList(List<CreditLiveInfo> liveList) {
		this.liveList = liveList;
	}
	public List<CreditOccupationInfo> getOccupationList() {
		return occupationList;
	}
	public void setOccupationList(List<CreditOccupationInfo> occupationList) {
		this.occupationList = occupationList;
	}
}

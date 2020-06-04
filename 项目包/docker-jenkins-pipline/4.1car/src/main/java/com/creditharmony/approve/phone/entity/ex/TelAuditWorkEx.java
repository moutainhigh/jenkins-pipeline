package com.creditharmony.approve.phone.entity.ex;

import java.util.List;

import com.creditharmony.approve.phone.entity.TelAuditWork;
import com.creditharmony.approve.phone.entity.WorkTelNum;

public class TelAuditWorkEx extends TelAuditWork{
	private static final long serialVersionUID = 1L;
	private List<WorkTelNum> workTelNums;
	
	public List<WorkTelNum> getWorkTelNums() {
		return workTelNums;
	}
	public void setWorkTelNums(List<WorkTelNum> workTelNums) {
		this.workTelNums = workTelNums;
	}
}

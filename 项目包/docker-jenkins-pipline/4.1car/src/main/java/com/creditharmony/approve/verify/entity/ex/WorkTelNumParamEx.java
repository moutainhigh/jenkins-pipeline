package com.creditharmony.approve.verify.entity.ex;

import java.io.Serializable;
import java.util.List;

import com.creditharmony.approve.phone.entity.WorkTelNum;

/**
 *  外网审核 电话信息更新参数实体
 * @Class Name WorkTelNumParamEx
 * @author 刘燕军
 * @Create In 2015年12月21日
 */
public class WorkTelNumParamEx implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<WorkTelNum> workTelNums;
	public List<WorkTelNum> getWorkTelNums() {
		return workTelNums;
	}
	public void setWorkTelNums(List<WorkTelNum> workTelNums) {
		this.workTelNums = workTelNums;
	}
}

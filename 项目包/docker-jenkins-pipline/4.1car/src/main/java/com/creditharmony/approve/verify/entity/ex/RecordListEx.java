package com.creditharmony.approve.verify.entity.ex;

import java.io.Serializable;
import java.util.List;

import com.creditharmony.approve.phone.entity.DhzhDhlyxx;

/**
 * 电话录音参数实体
 * @Class Name RecordListEx
 * @author 刘燕军
 * @Create In 2016年3月29日
 */
public class RecordListEx implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<DhzhDhlyxx> recordData;
	
	public List<DhzhDhlyxx> getRecordData() {
		return recordData;
	}
	public void setRecordData(List<DhzhDhlyxx> recordData) {
		this.recordData = recordData;
	}
}

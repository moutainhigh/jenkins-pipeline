package com.creditharmony.approve.phone.entity.ex;

import java.util.List;

import com.creditharmony.approve.phone.entity.DhzhDhlyxx;
import com.creditharmony.approve.phone.entity.WorkTelNum;

/**
 * 单位 电话信息
 * 
 * @Class Name WorkTelNumEx
 * @author 王浩
 * @Create In 2015年12月18日
 */
public class WorkTelNumEx extends WorkTelNum {
	private static final long serialVersionUID = 1L;

	private List<DhzhDhlyxx> dhlyxx; // 电话录音列表

	public List<DhzhDhlyxx> getDhlyxx() {
		return dhlyxx;
	}

	public void setDhlyxx(List<DhzhDhlyxx> dhlyxx) {
		this.dhlyxx = dhlyxx;
	}

}

package com.creditharmony.approve.phone.entity.ex;

import java.util.List;

import com.creditharmony.approve.phone.entity.DhzhDhgxshDhxx;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;

/**
 * 电话照会_关系证明人新增的电话号码及录音
 * 
 * @Class Name TelCheckBorrowerRecord
 * @author 王浩
 * @Create In 2015年12月1日
 */
public class TelCheckContactNumEx extends DhzhDhgxshDhxx {

	private static final long serialVersionUID = -2445821069063366276L;
	private List<DhzhDhlyxx> dhlyxx;

	public List<DhzhDhlyxx> getDhlyxx() {
		return dhlyxx;
	}

	public void setDhlyxx(List<DhzhDhlyxx> dhlyxx) {
		this.dhlyxx = dhlyxx;
	}
}

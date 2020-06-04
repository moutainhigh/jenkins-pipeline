package com.creditharmony.approve.phone.entity.ex;

import java.util.List;

import com.creditharmony.approve.phone.entity.DhzhDhlyxx;
import com.creditharmony.approve.phone.entity.DhzhGxhtDhxx;

/**
 * 电话照会_购销合同核查及录音
 * 
 * @Class Name TelCheckContractsInfo
 * @author 王浩
 * @Create In 2015年12月1日
 */
public class TelCheckBusiContractEx extends DhzhGxhtDhxx {

	private static final long serialVersionUID = 3949684285335160676L;

	private List<DhzhDhlyxx> dhlyxx;

	public List<DhzhDhlyxx> getDhlyxx() {
		return dhlyxx;
	}

	public void setDhlyxx(List<DhzhDhlyxx> dhlyxx) {
		this.dhlyxx = dhlyxx;
	}

}

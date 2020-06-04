package com.creditharmony.approve.document.entity.ex;

import java.util.List;

import com.creditharmony.approve.document.entity.ZlshGxht;
import com.creditharmony.approve.phone.entity.DhzhGxhtDhxx;

public class ContractEx extends ZlshGxht{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<DhzhGxhtDhxx> dhzhGxhtDhxx; // 所有的电话信息

	public List<DhzhGxhtDhxx> getDhzhGxhtDhxx() {
		return dhzhGxhtDhxx;
	}

	public void setDhzhGxhtDhxx(List<DhzhGxhtDhxx> dhzhGxhtDhxx) {
		this.dhzhGxhtDhxx = dhzhGxhtDhxx;
	}
	
	
}

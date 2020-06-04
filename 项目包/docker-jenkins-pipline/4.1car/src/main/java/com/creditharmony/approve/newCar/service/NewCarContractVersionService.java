package com.creditharmony.approve.newCar.service;

public interface NewCarContractVersionService{

	/** 
	 * 
	 * @param loanCode	借款编码
	 * @param isextensionId是否展期Id
	 * @return
	 */
	public String getFlowContractVersion(String loanCode,String isextensionId);
}

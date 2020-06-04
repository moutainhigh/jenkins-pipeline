package com.creditharmony.approve.newCar.service;

public interface NewCarImageService{
	
	/** 
	 * 
	 * 标签方法，获取图片的路径
	 * @param stepName
	 * @param loanCode
	 * @param contractVersion
	 * @return
	 */
	public String getImageUrl(String stepName, String loanCode,String contractVersion) ;
}

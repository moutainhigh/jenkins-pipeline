package com.creditharmony.approve.antifraud.entity.ex;

import java.util.List;



/**
 * 黑灰名单扩展Entity
 * @Class Name BlackList
 * @author wanglidong
 * @Create In 2015年11月23日
 */
public class BlackGraylistOptionEx {
	
	private List<ExternalBlackListEx> externalBlackListEx; 
	private String name;           // 姓名
	private String type;           // 主借人共借人

	/**
	 * @return the externalBlackListEx
	 */
	public List<ExternalBlackListEx> getExternalBlackListEx() {
		return externalBlackListEx;
	}
	/**
	 * @param externalBlackListEx the List<ExternalBlackListEx> externalBlackListEx to set
	 */
	public void setExternalBlackListEx(List<ExternalBlackListEx> externalBlackListEx) {
		this.externalBlackListEx = externalBlackListEx;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the String name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the String type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


}


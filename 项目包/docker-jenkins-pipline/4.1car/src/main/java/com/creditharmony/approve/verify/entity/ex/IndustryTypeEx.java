package com.creditharmony.approve.verify.entity.ex;

import java.util.List;


/**
 * 行业类别字典数据
 * @Class Name compIndustryDictListEx
 * @author wanglidong
 * @Create In 2016年4月14日
 */
public class IndustryTypeEx {
	
	private List<IndustryTypeOneEx> oneLevelList;

	/**
	 * @return the oneLevelList
	 */
	public List<IndustryTypeOneEx> getOneLevelList() {
		return oneLevelList;
	}

	/**
	 * @param oneLevelList the List<IndustryTypeOneEx> oneLevelList to set
	 */
	public void setOneLevelList(List<IndustryTypeOneEx> oneLevelList) {
		this.oneLevelList = oneLevelList;
	}


}

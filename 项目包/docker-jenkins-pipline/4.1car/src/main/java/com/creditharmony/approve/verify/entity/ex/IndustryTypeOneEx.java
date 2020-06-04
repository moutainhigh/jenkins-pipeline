package com.creditharmony.approve.verify.entity.ex;

import java.util.List;

import com.creditharmony.core.dict.entity.Dict;

/**
 * 行业类别字典数据
 * @Class Name compIndustryDictListEx
 * @author wanglidong
 * @Create In 2016年4月14日
 */
public class IndustryTypeOneEx {
	
	private Dict oneLevelDict;
	private List<IndustryTypeTwoEx> towLevelList;
	/**
	 * @return the oneLevelDict
	 */
	public Dict getOneLevelDict() {
		return oneLevelDict;
	}
	/**
	 * @param oneLevelDict the Dict oneLevelDict to set
	 */
	public void setOneLevelDict(Dict oneLevelDict) {
		this.oneLevelDict = oneLevelDict;
	}
	/**
	 * @return the towLevelList
	 */
	public List<IndustryTypeTwoEx> getTowLevelList() {
		return towLevelList;
	}
	/**
	 * @param towLevelList the List<IndustryTypeTwoEx> towLevelList to set
	 */
	public void setTowLevelList(List<IndustryTypeTwoEx> towLevelList) {
		this.towLevelList = towLevelList;
	}

	
}

package com.creditharmony.approve.verify.entity.ex;

import java.util.List;

import com.creditharmony.core.dict.entity.Dict;

/**
 * 行业类别字典数据
 * @Class Name compIndustryDictListEx
 * @author wanglidong
 * @Create In 2016年4月14日
 */
public class IndustryTypeTwoEx {
	private Dict twoLevelDict;
	private List<IndustryTypeThreeEx> threeLevelList;
	

	/**
	 * @return the twoLevelDict
	 */
	public Dict getTwoLevelDict() {
		return twoLevelDict;
	}
	/**
	 * @param twoLevelDict the Dict twoLevelDict to set
	 */
	public void setTwoLevelDict(Dict twoLevelDict) {
		this.twoLevelDict = twoLevelDict;
	}
	/**
	 * @return the threeLevelList
	 */
	public List<IndustryTypeThreeEx> getThreeLevelList() {
		return threeLevelList;
	}
	/**
	 * @param threeLevelList the List<IndustryTypeThreeEx> threeLevelList to set
	 */
	public void setThreeLevelList(List<IndustryTypeThreeEx> threeLevelList) {
		this.threeLevelList = threeLevelList;
	}

}

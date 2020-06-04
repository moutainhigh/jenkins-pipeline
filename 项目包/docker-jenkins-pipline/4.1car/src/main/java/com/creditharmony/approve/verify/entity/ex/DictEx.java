package com.creditharmony.approve.verify.entity.ex;

import java.util.List;

import com.creditharmony.core.dict.entity.Dict;

/**
 * 字典扩展类
 * @Class Name DictEx
 * @author 赖敏
 * @Create In 2015年12月25日
 */
public class DictEx extends Dict{
	
	private static final long serialVersionUID = 7511847176682123591L;
	private List<DictEx> dictExs;
	
	public List<DictEx> getDictExs() {
		return dictExs;
	}
	
	public void setDictExs(List<DictEx> dictExs) {
		this.dictExs = dictExs;
	}

}

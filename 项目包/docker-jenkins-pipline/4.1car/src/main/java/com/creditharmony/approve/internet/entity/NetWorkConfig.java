package com.creditharmony.approve.internet.entity;

import com.creditharmony.core.persistence.DataEntity;

public class NetWorkConfig extends DataEntity<NetWorkConfig> {
	private static final long serialVersionUID = 1L;
	private String keyword; // 借款编码
	private String url; // 网站
	private String netname; // 网站名字
	private String dictNetworkType; // 检查类型（身份证、单位名称）
	private String sort; // 排序
	private String useFlag; // 是否有效

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNetname() {
		return netname;
	}

	public void setNetname(String netname) {
		this.netname = netname;
	}

	public String getDictNetworkType() {
		return dictNetworkType;
	}

	public void setDictNetworkType(String dictNetworkType) {
		this.dictNetworkType = dictNetworkType;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

}

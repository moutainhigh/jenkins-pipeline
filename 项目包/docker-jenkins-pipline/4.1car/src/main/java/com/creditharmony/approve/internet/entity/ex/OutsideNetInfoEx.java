package com.creditharmony.approve.internet.entity.ex;

import java.io.Serializable;
import java.util.List;

import com.creditharmony.approve.internet.entity.PrivateNetworkCheck;
/**
 * 网查信息保存实体参数
 * @Class Name OutsideNetInfoEx
 * @author 刘燕军
 * @Create In 2015年12月11日
 */
public class OutsideNetInfoEx implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<PrivateNetworkCheck> netWorks;

	public List<PrivateNetworkCheck> getNetWorks() {
		return netWorks;
	}

	public void setNetWorks(List<PrivateNetworkCheck> netWorks) {
		this.netWorks = netWorks;
	}
}

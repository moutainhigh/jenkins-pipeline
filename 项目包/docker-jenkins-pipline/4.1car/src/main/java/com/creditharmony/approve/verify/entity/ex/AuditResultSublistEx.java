package com.creditharmony.approve.verify.entity.ex;

import java.util.List;

import com.creditharmony.approve.verify.entity.AuditResultSublist;
import com.creditharmony.approve.management.entity.GlRefuse;

/**
 * 拒绝信息扩展类-用于决策页面的拒借码回显
 * @Class Name AuditResultSublistEx
 * @author 赖敏
 * @Create In 2016年1月12日
 */
public class AuditResultSublistEx extends AuditResultSublist{

	private static final long serialVersionUID = 7962914294665888786L;
	private List<GlRefuse> secondList;	// 二级拒借码
	private List<GlRefuse> thirdList;	// 三级拒借码
	
	public List<GlRefuse> getSecondList() {
		return secondList;
	}
	public void setSecondList(List<GlRefuse> secondList) {
		this.secondList = secondList;
	}
	public List<GlRefuse> getThirdList() {
		return thirdList;
	}
	public void setThirdList(List<GlRefuse> thirdList) {
		this.thirdList = thirdList;
	}
	
}

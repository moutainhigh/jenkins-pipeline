package com.creditharmony.approve.outvisit.entity.ex;

import com.creditharmony.approve.outvisit.entity.OutsideCheckInfo;
import com.creditharmony.approve.outvisit.entity.OutsideCheckList;

/**
 * 外访_外访任务清单扩展类
 * @Class Name OutsideCheckListEx
 * @author 赖敏
 * @Create In 2015年12月25日
 */
public class OutsideCheckListEx extends OutsideCheckList {

	private static final long serialVersionUID = 260141378428037778L;
	private OutsideCheckInfo checkInfos;	  // 外访_外访任务详情
	public OutsideCheckInfo getCheckInfos() {
		return checkInfos;
	}
	public void setCheckInfos(OutsideCheckInfo checkInfos) {
		this.checkInfos = checkInfos;
	}
}

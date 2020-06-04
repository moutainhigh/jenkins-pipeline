package com.creditharmony.approve.common.view;

import com.creditharmony.approve.common.entity.BackConsult;
import com.creditharmony.bpm.frame.view.BaseBusinessView;

/**
 * 
 * @Class Name BackConsultView
 * @author wanglidong
 * @Create In 2015年12月21日
 */
public class BackConsultView extends BaseBusinessView{

	private BackConsult backConsult;	// 退回清单

	public BackConsult getBackConsult() {
		return backConsult;
	}

	public void setBackConsult(BackConsult backConsult) {
		this.backConsult = backConsult;
	}

}

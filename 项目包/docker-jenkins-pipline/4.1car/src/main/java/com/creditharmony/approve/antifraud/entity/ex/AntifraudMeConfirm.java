package com.creditharmony.approve.antifraud.entity.ex;

import java.util.List;

import com.creditharmony.approve.antifraud.entity.FqzdhzhBrhsDhxx;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;

/**
 * 反欺诈-电话照会-本人核实扩展类
 * @Class Name AntifraudMeConfirm
 * @author 赖敏
 * @Create In 2015年12月14日
 */
public class AntifraudMeConfirm extends FqzdhzhBrhsDhxx{

	private static final long serialVersionUID = 6880315867778173606L;
	private List<DhzhDhlyxx> dhlyxx; //电话录音信息
	
	public List<DhzhDhlyxx> getDhlyxx() {
		return dhlyxx;
	}
	
	public void setDhlyxx(List<DhzhDhlyxx> dhlyxx) {
		this.dhlyxx = dhlyxx;
	}
	
}

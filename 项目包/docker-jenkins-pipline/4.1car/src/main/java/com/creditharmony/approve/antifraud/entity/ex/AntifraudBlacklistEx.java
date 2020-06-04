package com.creditharmony.approve.antifraud.entity.ex;


import com.creditharmony.approve.antifraud.entity.AntifraudBlacklist;
/**
 * 反欺诈黑名单匹配信息  
 * @Class Name AntifraudBlacklistEx
 * @author wanglidong
 * @Create In 2015年12月2日
 */
public class AntifraudBlacklistEx extends AntifraudBlacklist{

	private static final long serialVersionUID = 1L;
    private String rId;
    private String toAfraudFlag;
    private String refuseResion;

    public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public String getToAfraudFlag() {
		return toAfraudFlag;
	}
	public void setToAfraudFlag(String toAfraudFlag) {
		this.toAfraudFlag = toAfraudFlag;
	}
	public String getRefuseResion() {
		return refuseResion;
	}
	public void setRefuseResion(String refuseResion) {
		this.refuseResion = refuseResion;
	}
}
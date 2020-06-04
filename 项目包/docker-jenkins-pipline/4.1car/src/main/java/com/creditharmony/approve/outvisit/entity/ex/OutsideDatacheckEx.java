package com.creditharmony.approve.outvisit.entity.ex;


import java.util.Date;

import com.creditharmony.approve.outvisit.entity.OutsideDatacheck;
/**
 * 
 * @Class Name OutsideDatacheckEx
 * @author wanglidong
 * @Create In 2015年12月7日
 */
public class OutsideDatacheckEx  extends OutsideDatacheck{

	private static final long serialVersionUID = 1L;
	private String remark;
	private String itemDistance;
    private Date createTime;

	public String getItemDistance() {
		return itemDistance;
	}

	public void setItemDistance(String itemDistance) {
		this.itemDistance = itemDistance;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



}
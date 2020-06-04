package com.creditharmony.approve.document.entity.ex;

import java.util.Date;
import java.util.List;
import com.creditharmony.approve.document.entity.ZlshJyzm;
import com.creditharmony.approve.document.entity.ZlshJyzmGdxx;
import com.creditharmony.approve.outvisit.entity.OutsideCheckInfo;

/**
 * 企业信息实体ex
 * 
 * @Class Name CompanyInfo
 * @author 刘燕军
 * @Create In 2015年12月7日
 */
public class CompanyInfoEx extends ZlshJyzm {
	private static final long serialVersionUID = 1L;
	private List<OutsideCheckInfo> jyzmAddress; // 经营地址
	private Date jydzzmExpireDay; // 合同到期时间
	private List<ZlshJyzmGdxx> gdxx; // 占股比例
	private String brhsMainBusiness; // 主营业务
	
	public List<OutsideCheckInfo> getJyzmAddress() {
		return jyzmAddress;
	}
	public void setJyzmAddress(List<OutsideCheckInfo> jyzmAddress) {
		this.jyzmAddress = jyzmAddress;
	}
	public Date getJydzzmExpireDay() {
		return jydzzmExpireDay;
	}
	public void setJydzzmExpireDay(Date jydzzmExpireDay) {
		this.jydzzmExpireDay = jydzzmExpireDay;
	}
	public List<ZlshJyzmGdxx> getGdxx() {
		return gdxx;
	}
	public void setGdxx(List<ZlshJyzmGdxx> gdxx) {
		this.gdxx = gdxx;
	}
	public String getBrhsMainBusiness() {
		return brhsMainBusiness;
	}
	public void setBrhsMainBusiness(String brhsMainBusiness) {
		this.brhsMainBusiness = brhsMainBusiness;
	}

}

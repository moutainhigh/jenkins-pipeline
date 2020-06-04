package com.creditharmony.approve.verify.entity.ex;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.localnet.entity.InnerApplyContrast;
import com.creditharmony.approve.localnet.entity.InnerCustomerHis;
import com.creditharmony.approve.localnet.entity.InnerRepeat;
import com.creditharmony.approve.localnet.entity.LoanRemark;
import com.creditharmony.approve.localnet.entity.ex.LoanInfoEx;
import com.creditharmony.approve.verify.entity.ReconsiderApply;

/**
 * 内网审核整体的Ex
 * 
 * @Class Name InnerCheckEx
 * @author 刘燕军
 * @Create In 2015年12月4日
 */
public class InnerCheckEx implements Serializable {

	private static final long serialVersionUID = 1L;
	private LoanInfoEx loanInfoEx;							// 申请信息
	private List<LoanRemark> loanRemarks;					// 门店备注
	private List<InnerApplyContrast> innerApplyContrasts;	// 申请信息历史对比异常点
	private List<InnerCustomerHis> customerHis;				// 历史归户信息
	private List<AntiFraudResultEx> antiFraudResultViews;	// 反欺诈反馈信息
	private List<GreyListEx> greyListViews;					// 查灰信息
	private List<GreyListEx> blackListViews;				// 查黑信息
	private List<InnerRepeat> innerRepeats;					// 查重信息
	private ReconsiderApply reconsiderApply; 				// 复议原因
	private Map<String, String> checkResults;			// 核查结果字典map
	private Map<String, String> approveResult;			// 核查结果字典map
	private List<CityInfo> provinceList; 				// 所有省列表 
	private List<CityInfo> cityList; 					// 所有市列表 
	private List<CityInfo> districtList; 					// 所有区列表 
	
	
	public List<LoanRemark> getLoanRemarks() {
		return loanRemarks;
	}

	public void setLoanRemarks(List<LoanRemark> loanRemarks) {
		this.loanRemarks = loanRemarks;
	}

	public List<InnerApplyContrast> getInnerApplyContrasts() {
		return innerApplyContrasts;
	}

	public void setInnerApplyContrasts(
			List<InnerApplyContrast> innerApplyContrasts) {
		this.innerApplyContrasts = innerApplyContrasts;
	}

	public List<InnerCustomerHis> getCustomerHis() {
		return customerHis;
	}

	public void setCustomerHis(List<InnerCustomerHis> customerHis) {
		this.customerHis = customerHis;
	}

	public List<AntiFraudResultEx> getAntiFraudResultViews() {
		return antiFraudResultViews;
	}

	public void setAntiFraudResultViews(
			List<AntiFraudResultEx> antiFraudResultViews) {
		this.antiFraudResultViews = antiFraudResultViews;
	}

	public List<GreyListEx> getGreyListViews() {
		return greyListViews;
	}

	public void setGreyListViews(List<GreyListEx> greyListViews) {
		this.greyListViews = greyListViews;
	}

	public List<GreyListEx> getBlackListViews() {
		return blackListViews;
	}

	public void setBlackListViews(List<GreyListEx> blackListViews) {
		this.blackListViews = blackListViews;
	}

	public List<InnerRepeat> getInnerRepeats() {
		return innerRepeats;
	}

	public void setInnerRepeats(List<InnerRepeat> innerRepeats) {
		this.innerRepeats = innerRepeats;
	}

	public LoanInfoEx getLoanInfoEx() {
		return loanInfoEx;
	}

	public void setLoanInfoEx(LoanInfoEx loanInfoEx) {
		this.loanInfoEx = loanInfoEx;
	}

	public ReconsiderApply getReconsiderApply() {
		return reconsiderApply;
	}

	public void setReconsiderApply(ReconsiderApply reconsiderApply) {
		this.reconsiderApply = reconsiderApply;
	}

	public Map<String, String> getCheckResults() {
		return checkResults;
	}

	public void setCheckResults(Map<String, String> checkResults) {
		this.checkResults = checkResults;
	}

	public Map<String, String> getApproveResult() {
		return approveResult;
	}

	public void setApproveResult(Map<String, String> approveResult) {
		this.approveResult = approveResult;
	}

	public List<CityInfo> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<CityInfo> provinceList) {
		this.provinceList = provinceList;
	}

	public List<CityInfo> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityInfo> cityList) {
		this.cityList = cityList;
	}

	public List<CityInfo> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<CityInfo> districtList) {
		this.districtList = districtList;
	}
	
}

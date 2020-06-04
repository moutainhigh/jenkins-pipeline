package com.creditharmony.approve.outvisit.entity;


import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 
 * @Class Name OutsideDatacheck
 * @author wanglidong
 * @Create In 2015年12月7日
 */
public class OutsideDatacheck  extends DataEntity<OutsideDatacheck>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String loanCode;

    private String dictSurveyType;

    private String surveyFamly;

    private String surveyWork;

    private String datacheckDescription;

    private String dictCheckType;
    
    private String loanCustomterType;      // 关联类型(主借人/共借人)
    
    private String rCustomerCoborrowerId;    // 关联ID(主借人，共借人)

	public String getrCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}

	public void setrCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
	}

	public String getLoanCustomterType() {
		return loanCustomterType;
	}

	public void setLoanCustomterType(String loanCustomterType) {
		this.loanCustomterType = loanCustomterType;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getDictSurveyType() {
        return dictSurveyType;
    }

    public void setDictSurveyType(String dictSurveyType) {
        this.dictSurveyType = dictSurveyType == null ? null : dictSurveyType.trim();
    }

    public String getSurveyFamly() {
        return surveyFamly;
    }

    public void setSurveyFamly(String surveyFamly) {
        this.surveyFamly = surveyFamly == null ? null : surveyFamly.trim();
    }

    public String getSurveyWork() {
        return surveyWork;
    }

    public void setSurveyWork(String surveyWork) {
        this.surveyWork = surveyWork == null ? null : surveyWork.trim();
    }

    public String getDatacheckDescription() {
        return datacheckDescription;
    }

    public void setDatacheckDescription(String datacheckDescription) {
        this.datacheckDescription = datacheckDescription == null ? null : datacheckDescription.trim();
    }

    public String getDictCheckType() {
        return dictCheckType;
    }

    public void setDictCheckType(String dictCheckType) {
        this.dictCheckType = dictCheckType == null ? null : dictCheckType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }


}
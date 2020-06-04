package com.creditharmony.approve.antifraud.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 反欺诈-外部调查
 * @Class Name AntifraudOutsideSurvey
 * @author 赖敏
 * @Create In 2015年11月30日
 */
public class AntifraudOutsideSurvey extends DataEntity<AntifraudOutsideSurvey>{

	private static final long serialVersionUID = -4340530907365575261L;
	private String loanCode;			// 借款编码
    private String surveyDepartment;	// 调查部门
    private Date surveyTime;			// 调查时间
    private String surveyUserName;		// 调查处理人
    private String surveyProject;		// 调查项目
    private String surveyMsg;			// 调查情况说明

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public Date getSurveyTime() {
        return surveyTime;
    }

    public void setSurveyTime(Date surveyTime) {
        this.surveyTime = surveyTime;
    }

    public String getSurveyProject() {
        return surveyProject;
    }

    public void setSurveyProject(String surveyProject) {
        this.surveyProject = surveyProject == null ? null : surveyProject.trim();
    }

    public String getSurveyMsg() {
        return surveyMsg;
    }

    public void setSurveyMsg(String surveyMsg) {
        this.surveyMsg = surveyMsg == null ? null : surveyMsg.trim();
    }

	public String getSurveyDepartment() {
		return surveyDepartment;
	}

	public void setSurveyDepartment(String surveyDepartment) {
		this.surveyDepartment = surveyDepartment;
	}

	public String getSurveyUserName() {
		return surveyUserName;
	}

	public void setSurveyUserName(String surveyUserName) {
		this.surveyUserName = surveyUserName;
	}
    
}
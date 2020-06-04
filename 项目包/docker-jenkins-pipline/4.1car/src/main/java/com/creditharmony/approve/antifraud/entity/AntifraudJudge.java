package com.creditharmony.approve.antifraud.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class AntifraudJudge extends DataEntity<AntifraudJudge>{

	private static final long serialVersionUID = 1L;
    private String rReportId;// 关联ID(提报ID)
    private String loanCode;// 借款编号
    private String judgeCaseCode;//欺诈案件编号
    private Date judgeProcDate;//反欺诈处理日期
    private String judgeProcBy;//反欺诈处理人编号
    private String dictCaseResult; //处理结果
    private String dictJudgeSecondCode; //二级决策项
    private String dictJudgeThredCode; //三级决策项
    private String judgeRiskMsg; //风险说明
    private String judgeReportEmpCode; //提报人员
    private String dictIntoSource; //进件来源
    private String judgeObjectId; //外部拉黑每行数据标识
    private String relationId; //关联ID(关联历史表)
    private String detailJudgeRiskMsg; //详细风险说明
    private String loanCustomerName; //借款人姓名
    
	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getDetailJudgeRiskMsg() {
		return detailJudgeRiskMsg;
	}

	public void setDetailJudgeRiskMsg(String detailJudgeRiskMsg) {
		this.detailJudgeRiskMsg = detailJudgeRiskMsg;
	}

	public String getLoanCustomerName() {
		return loanCustomerName;
	}

	public void setLoanCustomerName(String loanCustomerName) {
		this.loanCustomerName = loanCustomerName;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getrReportId() {
        return rReportId;
    }

    public void setrReportId(String rReportId) {
        this.rReportId = rReportId == null ? null : rReportId.trim();
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getJudgeCaseCode() {
        return judgeCaseCode;
    }

    public void setJudgeCaseCode(String judgeCaseCode) {
        this.judgeCaseCode = judgeCaseCode == null ? null : judgeCaseCode.trim();
    }

    public Date getJudgeProcDate() {
        return judgeProcDate;
    }

    public void setJudgeProcDate(Date judgeProcDate) {
        this.judgeProcDate = judgeProcDate;
    }

    public String getJudgeProcBy() {
        return judgeProcBy;
    }

    public void setJudgeProcBy(String judgeProcBy) {
        this.judgeProcBy = judgeProcBy == null ? null : judgeProcBy.trim();
    }

    public String getDictCaseResult() {
        return dictCaseResult;
    }

    public void setDictCaseResult(String dictCaseResult) {
        this.dictCaseResult = dictCaseResult == null ? null : dictCaseResult.trim();
    }

    public String getDictJudgeSecondCode() {
        return dictJudgeSecondCode;
    }

    public void setDictJudgeSecondCode(String dictJudgeSecondCode) {
        this.dictJudgeSecondCode = dictJudgeSecondCode == null ? null : dictJudgeSecondCode.trim();
    }

    public String getDictJudgeThredCode() {
        return dictJudgeThredCode;
    }

    public void setDictJudgeThredCode(String dictJudgeThredCode) {
        this.dictJudgeThredCode = dictJudgeThredCode == null ? null : dictJudgeThredCode.trim();
    }

    public String getJudgeRiskMsg() {
        return judgeRiskMsg;
    }

    public void setJudgeRiskMsg(String judgeRiskMsg) {
        this.judgeRiskMsg = judgeRiskMsg == null ? null : judgeRiskMsg.trim();
    }

    public String getJudgeReportEmpCode() {
        return judgeReportEmpCode;
    }

    public void setJudgeReportEmpCode(String judgeReportEmpCode) {
        this.judgeReportEmpCode = judgeReportEmpCode == null ? null : judgeReportEmpCode.trim();
    }

    public String getDictIntoSource() {
        return dictIntoSource;
    }

    public void setDictIntoSource(String dictIntoSource) {
        this.dictIntoSource = dictIntoSource == null ? null : dictIntoSource.trim();
    }

    public String getJudgeObjectId() {
        return judgeObjectId;
    }

    public void setJudgeObjectId(String judgeObjectId) {
        this.judgeObjectId = judgeObjectId == null ? null : judgeObjectId.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
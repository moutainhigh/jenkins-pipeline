package com.creditharmony.approve.antifraud.entity;


import com.creditharmony.core.persistence.DataEntity;

public class AntifraudReport  extends DataEntity<AntifraudReport>{
	private static final long serialVersionUID = 1L;
    private String rStatusHisId;        // 关联ID(变更历史表)
    private String loanCode;			// 借款编码
    private String dictBlackGrayType;	// 一级拒借码提报类型
    private String dictReportType;		// 提报类型
	private String dictCheckType;		// 类型(初审，信审初审，复议初审)
    private String mentionGroupId;		// 提报组别
	private String antifraudMsg;		// 欺诈说明
    private String mentionUserId;		// 提报人

	public String getDictBlackGrayType() {
		return dictBlackGrayType;
	}
	public void setDictBlackGrayType(String dictBlackGrayType) {
		this.dictBlackGrayType = dictBlackGrayType;
	}
	public String getMentionGroupId() {
		return mentionGroupId;
	}
	public void setMentionGroupId(String mentionGroupId) {
		this.mentionGroupId = mentionGroupId;
	}
    public String getMentionUserId() {
		return mentionUserId;
	}
	public void setMentionUserId(String mentionUserId) {
		this.mentionUserId = mentionUserId;
	}
    public String getrStatusHisId() {
		return rStatusHisId;
	}
	public void setrStatusHisId(String rStatusHisId) {
		this.rStatusHisId = rStatusHisId;
	}
	public String getLoanCode() {
        return loanCode;
    }
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }
    public String getDictReportType() {
        return dictReportType;
    }
    public void setDictReportType(String dictReportType) {
        this.dictReportType = dictReportType == null ? null : dictReportType.trim();
    }
    public String getAntifraudMsg() {
        return antifraudMsg;
    }
    public void setAntifraudMsg(String antifraudMsg) {
        this.antifraudMsg = antifraudMsg == null ? null : antifraudMsg.trim();
    }
    public String getDictCheckType() {
        return dictCheckType;
    }
    public void setDictCheckType(String dictCheckType) {
        this.dictCheckType = dictCheckType == null ? null : dictCheckType.trim();
    }
}
package com.creditharmony.approve.antifraud.entity;


import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 反欺诈黑名单匹配信息
 * @Class Name AntifraudBlacklist
 * @author wanglidong
 * @Create In 2015年12月2日
 */
public class AntifraudBlacklist extends DataEntity<AntifraudBlacklist>{

	private static final long serialVersionUID = 1L;
	private String id;
    private String loanCode;
    private String dictMarkType;
    private String dictBlacklistType;
    private String blacklistMsg;
    private String blacklistRiskMsg;
    private String blacklistRelation;
    private String addBlackType;
    private String loanCodeNow;
    private String rulesCode;
    private String rOffendId;
    
	public String getLoanCodeNow() {
		return loanCodeNow;
	}

	public void setLoanCodeNow(String loanCodeNow) {
		this.loanCodeNow = loanCodeNow;
	}

	public String getrOffendId() {
		return rOffendId;
	}

	public void setrOffendId(String rOffendId) {
		this.rOffendId = rOffendId;
	}

	public String getAddBlackType() {
		return addBlackType;
	}

	public void setAddBlackType(String addBlackType) {
		this.addBlackType = addBlackType;
	}

	public String getRulesCode() {
		return rulesCode;
	}

	public void setRulesCode(String rulesCode) {
		this.rulesCode = rulesCode;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }
    
	public String getDictMarkType() {
		return dictMarkType;
	}
	
	public void setDictMarkType(String dictMarkType) {
		this.dictMarkType = dictMarkType;
	}
	
	public String getDictBlacklistType() {
		return dictBlacklistType;
	}
	
	public void setDictBlacklistType(String dictBlacklistType) {
		this.dictBlacklistType = dictBlacklistType;
	}

	public String getBlacklistMsg() {
        return blacklistMsg;
    }

    public void setBlacklistMsg(String blacklistMsg) {
        this.blacklistMsg = blacklistMsg == null ? null : blacklistMsg.trim();
    }

    public String getBlacklistRiskMsg() {
        return blacklistRiskMsg;
    }

    public void setBlacklistRiskMsg(String blacklistRiskMsg) {
        this.blacklistRiskMsg = blacklistRiskMsg == null ? null : blacklistRiskMsg.trim();
    }

    public String getBlacklistRelation() {
        return blacklistRelation;
    }

    public void setBlacklistRelation(String blacklistRelation) {
        this.blacklistRelation = blacklistRelation == null ? null : blacklistRelation.trim();
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
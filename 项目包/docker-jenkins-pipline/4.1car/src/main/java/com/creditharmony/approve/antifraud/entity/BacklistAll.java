package com.creditharmony.approve.antifraud.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 黑灰名单
 * @Class Name BacklistAll
 * @author wanglidong
 * @Create In 2015年12月2日
 */
/**
 * @Class Name BacklistAll
 * @author wanglidong
 * @Create In 2016年4月26日
 */
public class BacklistAll extends DataEntity<BacklistAll> {
	private static final long serialVersionUID = 1L;
    private String loanCode;//借款编码
    private String dictBlackType;//加黑类型code
    private String dictBlackTypeName;//加黑类型name
    private String blackMsg;//加黑内容
    private String dictSource;//来源
    private String dictMarkType;//标记类型
    private String dictResult;//创建人
    private String loanCustomterType;//  关联类型(主借人/共借人)
    private String rCustomerCoborrowerId;//关联ID(主借人，共借人)
    private String createBy;
    private Date createTime;
    private String modifyBy;
    private Date modifyTime;
    

	/**
	 * @return the dictBlackTypeName
	 */
	public String getDictBlackTypeName() {
		return dictBlackTypeName;
	}
	/**
	 * @param dictBlackTypeName the String dictBlackTypeName to set
	 */
	public void setDictBlackTypeName(String dictBlackTypeName) {
		this.dictBlackTypeName = dictBlackTypeName;
	}
	public String getDictMarkType() {
		return dictMarkType;
	}
	/**
	 * @param dictMarkType the String dictMarkType to set
	 */
	public void setDictMarkType(String dictMarkType) {
		this.dictMarkType = dictMarkType;
	}
	public String getLoanCustomterType() {
		return loanCustomterType;
	}
	/**
	 * @param loanCustomterType the String loanCustomterType to set
	 */
	public void setLoanCustomterType(String loanCustomterType) {
		this.loanCustomterType = loanCustomterType;
	}
	/**
	 * @return the rCustomerCoborrowerId
	 */
	public String getrCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}
	/**
	 * @param rCustomerCoborrowerId the String rCustomerCoborrowerId to set
	 */
	public void setrCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
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

    public String getDictBlackType() {
        return dictBlackType;
    }

    public void setDictBlackType(String dictBlackType) {
        this.dictBlackType = dictBlackType == null ? null : dictBlackType.trim();
    }

    public String getBlackMsg() {
        return blackMsg;
    }

    public void setBlackMsg(String blackMsg) {
        this.blackMsg = blackMsg == null ? null : blackMsg.trim();
    }

    public String getDictSource() {
        return dictSource;
    }

    public void setDictSource(String dictSource) {
        this.dictSource = dictSource == null ? null : dictSource.trim();
    }

    public String getDictResult() {
        return dictResult;
    }

    public void setDictResult(String dictResult) {
        this.dictResult = dictResult == null ? null : dictResult.trim();
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
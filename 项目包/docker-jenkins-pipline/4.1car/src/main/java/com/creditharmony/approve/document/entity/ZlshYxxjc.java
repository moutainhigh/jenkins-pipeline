package com.creditharmony.approve.document.entity;

import java.util.List;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 资料审核有效性检查实体
 * @Class Name ZlshYxxjc
 * @author 刘燕军
 * @Create In 2015年12月2日
 */
public class ZlshYxxjc extends DataEntity<ZlshYxxjc>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

    private String loanCode;

    private String rId;

    private String dictCustomerType;

  //  private CheckJson checkJson;
    private List<YxxjcJson> yxxjcJson; // 

    private String dictCheckType;
    
    private String remark;
    
    private String dictSourceType;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getDictCustomerType() {
        return dictCustomerType;
    }

    public void setDictCustomerType(String dictCustomerType) {
        this.dictCustomerType = dictCustomerType == null ? null : dictCustomerType.trim();
    }

	public List<YxxjcJson> getYxxjcJson() {
		return yxxjcJson;
	}

	public void setYxxjcJson(List<YxxjcJson> yxxjcJson) {
		this.yxxjcJson = yxxjcJson;
	}

	public String getDictCheckType() {
        return dictCheckType;
    }

    public void setDictCheckType(String dictCheckType) {
        this.dictCheckType = dictCheckType == null ? null : dictCheckType.trim();
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDictSourceType() {
		return dictSourceType;
	}

	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}    
	
}
package com.creditharmony.approve.localnet.entity;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class InnerRepeat  extends  DataEntity<InnerRepeat>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String loanCode;
    private String rId;
    private String repeatName;
    private String loanCustomterCode;
    private String dictCustomerType;
    private String repeatRelation;
    private Date repeatIntoTime;
    private String repeatMainLoaner;
    private String repeatUnitname;
    private String repeatUnitAddress;
    private String exceptionFlag;
    private String productType;
    private String repeatViewType;
    private String repeatViewContent;
    private String dictCheckStatus;
    private String repeatRefuseReson;
    private String repeatAfterSituation;
    private String remark;    
    private String message;
    private String resource;
    private String exceptionReconsiderFlag; // 复议判断是否异常
    private String reconsiderRemark; // 复议备注

    public String getExceptionFlag() {
		return exceptionFlag;
	}

	public void setExceptionFlag(String exceptionFlag) {
		this.exceptionFlag = exceptionFlag;
	}


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

    public String getRepeatName() {
        return repeatName;
    }

    public void setRepeatName(String repeatName) {
        this.repeatName = repeatName == null ? null : repeatName.trim();
    }

    public String getLoanCustomterCode() {
        return loanCustomterCode;
    }

    public void setLoanCustomterCode(String loanCustomterCode) {
        this.loanCustomterCode = loanCustomterCode == null ? null : loanCustomterCode.trim();
    }

    public String getDictCustomerType() {
        return dictCustomerType;
    }

    public void setDictCustomerType(String dictCustomerType) {
        this.dictCustomerType = dictCustomerType == null ? null : dictCustomerType.trim();
    }

    public String getRepeatRelation() {
        return repeatRelation;
    }

    public void setRepeatRelation(String repeatRelation) {
        this.repeatRelation = repeatRelation == null ? null : repeatRelation.trim();
    }

    public Date getRepeatIntoTime() {
        return repeatIntoTime;
    }

    public void setRepeatIntoTime(Date repeatIntoTime) {
        this.repeatIntoTime = repeatIntoTime;
    }

    public String getRepeatMainLoaner() {
        return repeatMainLoaner;
    }

    public void setRepeatMainLoaner(String repeatMainLoaner) {
        this.repeatMainLoaner = repeatMainLoaner == null ? null : repeatMainLoaner.trim();
    }

    public String getRepeatUnitname() {
        return repeatUnitname;
    }

    public void setRepeatUnitname(String repeatUnitname) {
        this.repeatUnitname = repeatUnitname == null ? null : repeatUnitname.trim();
    }

    public String getRepeatUnitAddress() {
        return repeatUnitAddress;
    }

    public void setRepeatUnitAddress(String repeatUnitAddress) {
        this.repeatUnitAddress = repeatUnitAddress == null ? null : repeatUnitAddress.trim();
    }

   
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getRepeatViewType() {
        return repeatViewType;
    }

    public void setRepeatViewType(String repeatViewType) {
        this.repeatViewType = repeatViewType == null ? null : repeatViewType.trim();
    }

    public String getRepeatViewContent() {
        return repeatViewContent;
    }

    public void setRepeatViewContent(String repeatViewContent) {
        this.repeatViewContent = repeatViewContent == null ? null : repeatViewContent.trim();
    }

    public String getDictCheckStatus() {
        return dictCheckStatus;
    }

    public void setDictCheckStatus(String dictCheckStatus) {
        this.dictCheckStatus = dictCheckStatus == null ? null : dictCheckStatus.trim();
    }

    public String getRepeatRefuseReson() {
        return repeatRefuseReson;
    }

    public void setRepeatRefuseReson(String repeatRefuseReson) {
        this.repeatRefuseReson = repeatRefuseReson == null ? null : repeatRefuseReson.trim();
    }

    public String getRepeatAfterSituation() {
        return repeatAfterSituation;
    }

    public void setRepeatAfterSituation(String repeatAfterSituation) {
        this.repeatAfterSituation = repeatAfterSituation == null ? null : repeatAfterSituation.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getExceptionReconsiderFlag() {
		return exceptionReconsiderFlag;
	}

	public void setExceptionReconsiderFlag(String exceptionReconsiderFlag) {
		this.exceptionReconsiderFlag = exceptionReconsiderFlag;
	}

	public String getReconsiderRemark() {
		return reconsiderRemark;
	}

	public void setReconsiderRemark(String reconsiderRemark) {
		this.reconsiderRemark = reconsiderRemark;
	}

	
	
}
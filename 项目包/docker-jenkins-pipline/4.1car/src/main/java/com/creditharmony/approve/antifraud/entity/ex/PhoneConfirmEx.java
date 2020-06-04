package com.creditharmony.approve.antifraud.entity.ex;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 反欺诈-电话照会-本人核实
 * @Class Name FqzdhzhBrhsDhxx
 * @author 赖敏
 * @Create In 2015年12月14日
 */
public class PhoneConfirmEx extends DataEntity<PhoneConfirmEx>{
	
	private static final long serialVersionUID = 3752947529125187291L;
	private String loanCode;			// 借款编码
    private String brhsPhone;			// 手机号
    private String brhsAssessResult;	// 评估结果
    private String brhsRemark;			// 备注
    private String editRemark;			// 编辑标识(默认0可编辑1不可编辑)
    private String telRemark;			// 电话备注
    
    private String homeTel;					// 新版申请表add宅电
	private String homeTelAssessResult;		// 新版申请表add宅电评估结果
	private String homeTelRemark;			//新版申请表add宅电备注
    
    

	public String getTelRemark() {
		return telRemark;
	}

	public void setTelRemark(String telRemark) {
		this.telRemark = telRemark;
	}

	public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getBrhsPhone() {
        return brhsPhone;
    }

    public void setBrhsPhone(String brhsPhone) {
        this.brhsPhone = brhsPhone == null ? null : brhsPhone.trim();
    }

    public String getBrhsAssessResult() {
        return brhsAssessResult;
    }

    public void setBrhsAssessResult(String brhsAssessResult) {
        this.brhsAssessResult = brhsAssessResult == null ? null : brhsAssessResult.trim();
    }

    public String getBrhsRemark() {
        return brhsRemark;
    }

    public void setBrhsRemark(String brhsRemark) {
        this.brhsRemark = brhsRemark == null ? null : brhsRemark.trim();
    }

    public String getEditRemark() {
		return editRemark;
	}
    
    public void setEditRemark(String editRemark) {
		this.editRemark = editRemark;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getHomeTelAssessResult() {
		return homeTelAssessResult;
	}

	public void setHomeTelAssessResult(String homeTelAssessResult) {
		this.homeTelAssessResult = homeTelAssessResult;
	}

	public String getHomeTelRemark() {
		return homeTelRemark;
	}

	public void setHomeTelRemark(String homeTelRemark) {
		this.homeTelRemark = homeTelRemark;
	}
    
    
    
}
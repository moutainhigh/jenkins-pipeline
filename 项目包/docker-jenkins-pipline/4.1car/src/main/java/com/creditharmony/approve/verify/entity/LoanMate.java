package com.creditharmony.approve.verify.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class LoanMate extends DataEntity<LoanMate> {

	private static final long serialVersionUID = 1L;

	private String loanCode;

    private String mateName;

    private int mateAge;

    private String loanCustomterType;

    private Date mateBirthday;

    private String dictCertType;

    private String mateCertNum;

    private String mateTel;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;


    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getMateName() {
        return mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName == null ? null : mateName.trim();
    }


    public int getMateAge() {
		return mateAge;
	}

	public void setMateAge(int mateAge) {
		this.mateAge = mateAge;
	}

	public String getLoanCustomterType() {
        return loanCustomterType;
    }

    public void setLoanCustomterType(String loanCustomterType) {
        this.loanCustomterType = loanCustomterType == null ? null : loanCustomterType.trim();
    }

    public Date getMateBirthday() {
        return mateBirthday;
    }

    public void setMateBirthday(Date mateBirthday) {
        this.mateBirthday = mateBirthday;
    }

    public String getDictCertType() {
        return dictCertType;
    }

    public void setDictCertType(String dictCertType) {
        this.dictCertType = dictCertType == null ? null : dictCertType.trim();
    }

    public String getMateCertNum() {
        return mateCertNum;
    }

    public void setMateCertNum(String mateCertNum) {
        this.mateCertNum = mateCertNum == null ? null : mateCertNum.trim();
    }

    public String getMateTel() {
        return mateTel;
    }

    public void setMateTel(String mateTel) {
        this.mateTel = mateTel == null ? null : mateTel.trim();
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
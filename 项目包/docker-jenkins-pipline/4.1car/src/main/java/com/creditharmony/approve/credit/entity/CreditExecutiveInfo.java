package com.creditharmony.approve.credit.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 企业征信_高管人信息
 * @Class Name CreditExecutiveInfo
 * @author 张虎
 * @Create In 2015年12月31日
 */
public class CreditExecutiveInfo extends DataEntity<CreditExecutiveInfo> {

	private static final long serialVersionUID = 1L;
	
    private String loanCode;//借款编码

    private String name;//姓名

    private String dictCertType;//证件类型

    private String customerCertNum;//证件号码

    private String dictCompPost;//职务

    private String operation;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDictCertType() {
        return dictCertType;
    }

    public void setDictCertType(String dictCertType) {
        this.dictCertType = dictCertType == null ? null : dictCertType.trim();
    }

    public String getCustomerCertNum() {
        return customerCertNum;
    }

    public void setCustomerCertNum(String customerCertNum) {
        this.customerCertNum = customerCertNum == null ? null : customerCertNum.trim();
    }

    public String getDictCompPost() {
        return dictCompPost;
    }

    public void setDictCompPost(String dictCompPost) {
        this.dictCompPost = dictCompPost == null ? null : dictCompPost.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
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
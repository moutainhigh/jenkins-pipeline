package com.creditharmony.approve.document.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 资料审核 个人证件学历信息
 * @Class Name ZlshGrzjxl
 * @author 赵春香
 * @Create In 2016年9月9日
 */
public class ZlshGrzjxl extends DataEntity<ZlshGrzjxl>{
	
	private static final long serialVersionUID = 1L;
	private String loanCode;// 借款编码
	private String rGrzjId;// 关联ID(个人证件)
	private String dictCustomerType;// 借款人类型(主借人/共借人)
	private String educationalCertifcateType;         //学历证书类型
	private String educationSchool;                   //毕业学校
	private String educationalCertifcateNum;          //学历证书编号
	private Date   educationalCertifcateTime;           //学历证书取得时间
	private String dictCheckType;// 类型(初审，信审初审，复议初审)
	private String dictSourceType;
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getrGrzjId() {
		return rGrzjId;
	}
	public void setrGrzjId(String rGrzjId) {
		this.rGrzjId = rGrzjId;
	}
	public String getDictCustomerType() {
		return dictCustomerType;
	}
	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}
	public String getDictCheckType() {
		return dictCheckType;
	}
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
	public String getEducationalCertifcateType() {
		return educationalCertifcateType;
	}
	public void setEducationalCertifcateType(String educationalCertifcateType) {
		this.educationalCertifcateType = educationalCertifcateType;
	}
	public String getEducationSchool() {
		return educationSchool;
	}
	public void setEducationSchool(String educationSchool) {
		this.educationSchool = educationSchool;
	}
	public String getEducationalCertifcateNum() {
		return educationalCertifcateNum;
	}
	public void setEducationalCertifcateNum(String educationalCertifcateNum) {
		this.educationalCertifcateNum = educationalCertifcateNum;
	}
	public Date getEducationalCertifcateTime() {
		return educationalCertifcateTime;
	}
	public void setEducationalCertifcateTime(Date educationalCertifcateTime) {
		this.educationalCertifcateTime = educationalCertifcateTime;
	}
	public String getDictSourceType() {
		return dictSourceType;
	}
	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}
	
}
package com.creditharmony.approve.internet.entity.ex;

import java.util.List;

import com.creditharmony.approve.document.entity.ZlshZczm;
import com.creditharmony.approve.phone.entity.DhzhBrhs;
import com.creditharmony.approve.phone.entity.DhzhBrhsDhxx;
import com.creditharmony.approve.phone.entity.DhzhDhgxbrshd;
import com.creditharmony.approve.phone.entity.DhzhDhgxsh;
import com.creditharmony.approve.phone.entity.ex.TelAuditWorkEx;

/**
 * CertificateType 从数据库查询出的信息 网查信息
 * 
 * @Class Name OutsideNetCheckEx
 * @author 刘燕军
 * @Create In 2015年12月11日
 * @update in 2016-09-27
 */
public class OutsideNetCheckEx extends DhzhBrhs {

	private static final long serialVersionUID = 1L;

	private List<DhzhBrhsDhxx> phoneInfo; // 本人核实电话信息
	private List<TelAuditWorkEx> workInof; // 公司信息
	private String queryCity; // 网查时的城市信息
	private List<DhzhDhgxsh> contacts; // 家庭联系人
	private List<DhzhDhgxbrshd> liveAddress; // 居住地址
	private List<ZlshZczm> houseAdderss; // 房产地址
	private String relId; // 关联id
	private String cerNum; // 配偶身份证号
	private String result; // 配偶身份证网查结果
	private String remark; // 配偶身份证网查备注
	private String cardType; // 配偶证件类型
	private String mateName; // 配偶姓名
	private String nameResult; // 配型姓名网查结果
	private String nameRemark; // 配型姓名网查备注
	private String compLegalId;

	public String getCompLegalId() {
		return compLegalId;
	}

	public void setCompLegalId(String compLegalId) {
		this.compLegalId = compLegalId;
	}

	public List<DhzhBrhsDhxx> getPhoneInfo() {
		return phoneInfo;
	}

	public void setPhoneInfo(List<DhzhBrhsDhxx> phoneInfo) {
		this.phoneInfo = phoneInfo;
	}

	public List<TelAuditWorkEx> getWorkInof() {
		return workInof;
	}

	public void setWorkInof(List<TelAuditWorkEx> workInof) {
		this.workInof = workInof;
	}

	public List<DhzhDhgxsh> getContacts() {
		return contacts;
	}

	public void setContacts(List<DhzhDhgxsh> contacts) {
		this.contacts = contacts;
	}

	public String getRelId() {
		return relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

	public String getCerNum() {
		return cerNum;
	}

	public void setCerNum(String cerNum) {
		this.cerNum = cerNum;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<DhzhDhgxbrshd> getLiveAddress() {
		return liveAddress;
	}

	public void setLiveAddress(List<DhzhDhgxbrshd> liveAddress) {
		this.liveAddress = liveAddress;
	}

	public String getQueryCity() {
		return queryCity;
	}

	public void setQueryCity(String queryCity) {
		this.queryCity = queryCity;
	}

	public List<ZlshZczm> getHouseAdderss() {
		return houseAdderss;
	}

	public void setHouseAdderss(List<ZlshZczm> houseAdderss) {
		this.houseAdderss = houseAdderss;
	}

	public String getMateName() {
		return mateName;
	}

	public void setMateName(String mateName) {
		this.mateName = mateName;
	}

	public String getNameResult() {
		return nameResult;
	}

	public void setNameResult(String nameResult) {
		this.nameResult = nameResult;
	}

	public String getNameRemark() {
		return nameRemark;
	}

	public void setNameRemark(String nameRemark) {
		this.nameRemark = nameRemark;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * 配偶单位名称
	 */
	private String unitName;
	/**
	 * 网查结果（配偶的“单位名称”）
	 */
	private String mateUnitnameNetResult;
	/**
	 * 网查备注（配偶的“单位名称”）
	 */
	private String mateUnitnameNetRemark;
	/**
	 * 法人代表人姓名
	 */
	private String comLegalMan;
	/**
	 * 法人代表人身份证件号
	 */
	private String comLegalManNum;
	/**
	 * 法人代表人手机号
	 */
	private String comLegalManMoblie;
	/**
	 * 企业邮箱
	 */
	private String comEmail;
	/**
	 * 网查结果（法人代表人姓名）
	 */
	private String comLegalManResult;
	/**
	 * 网查备注（法人代表人姓名）
	 */
	private String comLegalManRemark;
	/**
	 * 网查结果（法人代表人身份证件号）
	 */
	private String comLegalManNumResult;
	/**
	 * 网查备注（法人代表人身份证件号）
	 */
	private String comLegalManNumRemark;
	/**
	 * 网查结果（法人代表人手机号）
	 */
	private String comLegalManMoblieResult;
	/**
	 * 网查备注（法人代表人手机号）
	 */
	private String comLegalManMoblieRemark;
	/**
	 * 网查结果（企业邮箱）
	 */
	private String comEmailNetResult;
	/**
	 * 网查备注（企业邮箱）
	 */
	private String comEmailNetRemark;
	/**
	 * 网查结果（配偶的“住址”）
	 */
	private String mateAddressNetResult;
	/**
	 * 网查备注（配偶的“住址”）
	 */
	private String mateAddressNetRemark;
	/**
	 * 住址省
	 */
	private String province;
	/**
	 * 住址市
	 */
	private String city;
	/**
	 * 住址区
	 */
	private String arer;
	/**
	 * 住址地址
	 */
	private String address;

	/**
	 * 配偶单位名称
	 * 
	 * @return
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 配偶单位名称
	 * 
	 * @param unitName
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 网查结果（配偶的“单位名称”）
	 */
	public String getMateUnitnameNetResult() {
		return mateUnitnameNetResult;
	}

	/**
	 * 网查结果（配偶的“单位名称”）
	 */
	public void setMateUnitnameNetResult(String mateUnitnameNetResult) {
		this.mateUnitnameNetResult = mateUnitnameNetResult;
	}

	/**
	 * 网查备注（配偶的“单位名称”）
	 */
	public String getMateUnitnameNetRemark() {
		return mateUnitnameNetRemark;
	}

	/**
	 * 网查备注（配偶的“单位名称”）
	 */
	public void setMateUnitnameNetRemark(String mateUnitnameNetRemark) {
		this.mateUnitnameNetRemark = mateUnitnameNetRemark;
	}

	/**
	 * 法人代表人姓名
	 */
	public String getComLegalMan() {
		return comLegalMan;
	}

	/**
	 * 法人代表人姓名
	 */
	public void setComLegalMan(String comLegalMan) {
		this.comLegalMan = comLegalMan;
	}

	/**
	 * 法人代表人身份证件号
	 */
	public String getComLegalManNum() {
		return comLegalManNum;
	}

	/**
	 * 法人代表人身份证件号
	 */
	public void setComLegalManNum(String comLegalManNum) {
		this.comLegalManNum = comLegalManNum;
	}

	/**
	 * 法人代表人手机号
	 */
	public String getComLegalManMoblie() {
		return comLegalManMoblie;
	}

	/**
	 * 法人代表人手机号
	 */
	public void setComLegalManMoblie(String comLegalManMoblie) {
		this.comLegalManMoblie = comLegalManMoblie;
	}

	/**
	 * 企业邮箱
	 */
	public String getComEmail() {
		return comEmail;
	}

	/**
	 * 企业邮箱
	 */
	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}

	/**
	 * 网查结果（法人代表人姓名）
	 */
	public String getComLegalManResult() {
		return comLegalManResult;
	}

	/**
	 * 网查结果（法人代表人姓名）
	 */
	public void setComLegalManResult(String comLegalManResult) {
		this.comLegalManResult = comLegalManResult;
	}

	/**
	 * 网查备注（法人代表人姓名）
	 */
	public String getComLegalManRemark() {
		return comLegalManRemark;
	}

	/**
	 * 网查备注（法人代表人姓名）
	 */
	public void setComLegalManRemark(String comLegalManRemark) {
		this.comLegalManRemark = comLegalManRemark;
	}

	/**
	 * 网查结果（法人代表人身份证件号）
	 */
	public String getComLegalManNumResult() {
		return comLegalManNumResult;
	}

	/**
	 * 网查结果（法人代表人身份证件号）
	 */
	public void setComLegalManNumResult(String comLegalManNumResult) {
		this.comLegalManNumResult = comLegalManNumResult;
	}

	/**
	 * 网查备注（法人代表人身份证件号）
	 */
	public String getComLegalManNumRemark() {
		return comLegalManNumRemark;
	}

	/**
	 * 网查备注（法人代表人身份证件号）
	 */
	public void setComLegalManNumRemark(String comLegalManNumRemark) {
		this.comLegalManNumRemark = comLegalManNumRemark;
	}

	/**
	 * 网查结果（法人代表人手机号）
	 */
	public String getComLegalManMoblieResult() {
		return comLegalManMoblieResult;
	}

	/**
	 * 网查结果（法人代表人手机号）
	 */
	public void setComLegalManMoblieResult(String comLegalManMoblieResult) {
		this.comLegalManMoblieResult = comLegalManMoblieResult;
	}

	/**
	 * 网查备注（法人代表人手机号）
	 */
	public String getComLegalManMoblieRemark() {
		return comLegalManMoblieRemark;
	}

	/**
	 * 网查备注（法人代表人手机号）
	 */
	public void setComLegalManMoblieRemark(String comLegalManMoblieRemark) {
		this.comLegalManMoblieRemark = comLegalManMoblieRemark;
	}

	/**
	 * 网查结果（企业邮箱）
	 */
	public String getComEmailNetResult() {
		return comEmailNetResult;
	}

	/**
	 * 网查结果（企业邮箱）
	 */
	public void setComEmailNetResult(String comEmailNetResult) {
		this.comEmailNetResult = comEmailNetResult;
	}

	/**
	 * 网查备注（企业邮箱）
	 */
	public String getComEmailNetRemark() {
		return comEmailNetRemark;
	}

	/**
	 * 网查备注（企业邮箱）
	 */
	public void setComEmailNetRemark(String comEmailNetRemark) {
		this.comEmailNetRemark = comEmailNetRemark;
	}

	/**
	 * 网查结果（配偶的“住址”）
	 */
	public String getMateAddressNetResult() {
		return mateAddressNetResult;
	}

	/**
	 * 网查结果（配偶的“住址”）
	 */
	public void setMateAddressNetResult(String mateAddressNetResult) {
		this.mateAddressNetResult = mateAddressNetResult;
	}

	/**
	 * 网查备注（配偶的“住址”）
	 */
	public String getMateAddressNetRemark() {
		return mateAddressNetRemark;
	}

	/**
	 * 网查备注（配偶的“住址”）
	 */
	public void setMateAddressNetRemark(String mateAddressNetRemark) {
		this.mateAddressNetRemark = mateAddressNetRemark;
	}

	/**
	 * 住址省
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 住址省
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 住址市
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 住址市
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 住址区
	 */
	public String getArer() {
		return arer;
	}

	/**
	 * 住址区
	 */
	public void setArer(String arer) {
		this.arer = arer;
	}

	/**
	 * 住址地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 住址地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}

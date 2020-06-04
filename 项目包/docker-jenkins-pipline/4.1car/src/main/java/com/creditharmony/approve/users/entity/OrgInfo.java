package com.creditharmony.approve.users.entity;

import com.creditharmony.core.persistence.TreeEntity;

/**
 * 组织机构实体
 * @Class Name OrgInfo
 * @author 张永生
 * @Create In 2015年12月8日
 */
public class OrgInfo extends TreeEntity<OrgInfo> {

	private static final long serialVersionUID = 5320844093921571664L;
	private String areaId;			     // 归属区域
	private String code; 				 // 机构编码
	private String type; 				 // 机构类型（1：公司；2：部门；3：小组）
	private String grade; 				 // 机构等级（1：一级；2：二级；3：三级；4：四级）
	private String address; 			 // 联系地址
	private String zipCode; 			 // 邮政编码
	private String master; 				 // 负责人
	private String phone; 				 // 电话
	private String fax; 				 // 传真
	private String email; 				 // 邮箱
	private String useable;              // 是否可用
	private String provinceId;           // 省份Id
	private String cityId;               // 城市Id
	private String districtId;           // 区县Id 
	private String partyType;			 // 主体类型
	private String storeCode;            // 门店编码
	private String carLoanCode;          // 门店的车借编码
	private Integer systemFlag;          // 系统标识
	private String parentId;             // 父节点Id,为了从系统管理模块同步数据时方便保存parentId而添加
	private String creditPaperless;      // 信借是否无纸化, 1-是,0-否
	private String carPaperless;         // 车借是否无纸化, 1-是,0-否
	
	public OrgInfo(){
		super();
	}

	public OrgInfo(String id){
		super(id);
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public OrgInfo getParent() {
		return parent;
	}

	public void setParent(OrgInfo parent) {
		this.parent = parent;
	}

	public String getPartyType() {
		return partyType;
	}

	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public 	Integer getSystemFlag() {
		return systemFlag;
	}

	public void setSystemFlag(Integer systemFlag) {
		this.systemFlag = systemFlag;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCarLoanCode() {
		return carLoanCode;
	}

	public void setCarLoanCode(String carLoanCode) {
		this.carLoanCode = carLoanCode;
	}

	public String getCreditPaperless() {
		return creditPaperless;
	}

	public void setCreditPaperless(String creditPaperless) {
		this.creditPaperless = creditPaperless;
	}

	public String getCarPaperless() {
		return carPaperless;
	}

	public void setCarPaperless(String carPaperless) {
		this.carPaperless = carPaperless;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
		result = prime * result
				+ ((carLoanCode == null) ? 0 : carLoanCode.hashCode());
		result = prime * result
				+ ((carPaperless == null) ? 0 : carPaperless.hashCode());
		result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((creditPaperless == null) ? 0 : creditPaperless.hashCode());
		result = prime * result
				+ ((districtId == null) ? 0 : districtId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((master == null) ? 0 : master.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result
				+ ((partyType == null) ? 0 : partyType.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result
				+ ((provinceId == null) ? 0 : provinceId.hashCode());
		result = prime * result
				+ ((storeCode == null) ? 0 : storeCode.hashCode());
		result = prime * result
				+ ((systemFlag == null) ? 0 : systemFlag.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((useable == null) ? 0 : useable.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrgInfo other = (OrgInfo) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (areaId == null) {
			if (other.areaId != null)
				return false;
		} else if (!areaId.equals(other.areaId))
			return false;
		if (carLoanCode == null) {
			if (other.carLoanCode != null)
				return false;
		} else if (!carLoanCode.equals(other.carLoanCode))
			return false;
		if (carPaperless == null) {
			if (other.carPaperless != null)
				return false;
		} else if (!carPaperless.equals(other.carPaperless))
			return false;
		if (cityId == null) {
			if (other.cityId != null)
				return false;
		} else if (!cityId.equals(other.cityId))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (creditPaperless == null) {
			if (other.creditPaperless != null)
				return false;
		} else if (!creditPaperless.equals(other.creditPaperless))
			return false;
		if (districtId == null) {
			if (other.districtId != null)
				return false;
		} else if (!districtId.equals(other.districtId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (master == null) {
			if (other.master != null)
				return false;
		} else if (!master.equals(other.master))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (partyType == null) {
			if (other.partyType != null)
				return false;
		} else if (!partyType.equals(other.partyType))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (provinceId == null) {
			if (other.provinceId != null)
				return false;
		} else if (!provinceId.equals(other.provinceId))
			return false;
		if (storeCode == null) {
			if (other.storeCode != null)
				return false;
		} else if (!storeCode.equals(other.storeCode))
			return false;
		if (systemFlag == null) {
			if (other.systemFlag != null)
				return false;
		} else if (!systemFlag.equals(other.systemFlag))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (useable == null) {
			if (other.useable != null)
				return false;
		} else if (!useable.equals(other.useable))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	
	
}
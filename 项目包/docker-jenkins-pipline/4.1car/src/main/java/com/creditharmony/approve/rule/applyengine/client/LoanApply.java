
package com.creditharmony.approve.rule.applyengine.client;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>loanApply complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="loanApply">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="applyMaterialEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="bankSavingsEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="birthPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coborrowAge" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="coborrowBirthPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coborrowIdentityNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coborrowerExist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="companyBankAccountEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="companyHisLoanUnpaid" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="coupleBirthPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coupleCurrentLoanOverdue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coupleHisLoanUnpaid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coupleHisLoanUnpaidAsCoborrow" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coupleIdentityNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coupleJobType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coupleWorkCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditEnterpriseEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="creditReportDetailEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="creditReportSimpleEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fromLocal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hisLoanUnpaid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hisLoanUnpaidAsCoborrow" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identityDocEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="identityNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jobType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="livingMaterialEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="loanAmount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="manageDocEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="manageMonths" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="marriageDocEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="noCreditReport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noCreditHistory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notUnMarried" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otherDocEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="parentsContactExists" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="realEastateEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="salaryDocEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="workCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coborrowWorkCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="liveCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coupleLiveCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coborrowLiveCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="coborrowDocEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="workDocEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="workMonths" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loanApply", propOrder = {
    "age",
    "applyMaterialEmpty",
    "bankSavingsEmpty",
    "birthPlace",
    "coborrowAge",
    "coborrowBirthPlace",
    "coborrowIdentityNo",
    "coborrowerExist",
    "companyBankAccountEmpty",
    "companyHisLoanUnpaid",
    "coupleCurrentLoanOverdue",
    "coupleHisLoanUnpaid",
    "coupleHisLoanUnpaidAsCoborrow",
    "coupleIdentityNo",
    "coupleWorkCity",
    "creditEnterpriseEmpty",
    "creditReportDetailEmpty",
    "creditReportSimpleEmpty",
    "fromLocal",
    "hisLoanUnpaid",
    "hisLoanUnpaidAsCoborrow",
    "identityDocEmpty",
    "identityNo",
    "livingMaterialEmpty",
    "loanAmount",
    "manageDocEmpty",
    "manageMonths",
    "marriageDocEmpty",    
    "noCreditReport",
    "noCreditHistory",
    "notUnMarried",
    "otherDocEmpty",
    "parentsContactExists",
    "productType",
    "realEastateEmpty",
    "salaryDocEmpty",
    "workCity",
    "workDocEmpty",
    "coborrowDocEmpty",
    "coborrowWorkCity",
    "liveCity",
    "coupleLiveCity",
    "coborrowLiveCity",
    "workMonths",
    "legalPersonExist",
    "legalPersonDocEmpty",
    "naturalPersonExist",
    "naturalPersonDocEmpty"
})
public class LoanApply {

    protected int age;
    protected boolean applyMaterialEmpty;
    protected boolean bankSavingsEmpty;
    protected String birthPlace;
    protected int coborrowAge;
    protected String coborrowBirthPlace;
    protected String coborrowIdentityNo;
    protected String coborrowerExist;
    protected boolean companyBankAccountEmpty;
    protected boolean companyHisLoanUnpaid;
    protected String coupleCurrentLoanOverdue;
    protected String coupleHisLoanUnpaid;
    protected String coupleHisLoanUnpaidAsCoborrow;
    protected String coupleIdentityNo;
    protected String coupleWorkCity;
    protected boolean creditEnterpriseEmpty;
    protected boolean creditReportDetailEmpty;
    protected boolean creditReportSimpleEmpty;
    protected String fromLocal;
    protected String hisLoanUnpaid;
    protected String hisLoanUnpaidAsCoborrow;
    protected boolean identityDocEmpty;
    protected String identityNo;
    protected boolean livingMaterialEmpty;
    protected int loanAmount;
    protected boolean manageDocEmpty;
    protected int manageMonths;
    protected boolean marriageDocEmpty;
    protected String noCreditReport;    
    protected String noCreditHistory;
    protected String notUnMarried;
    protected boolean otherDocEmpty;
    protected String parentsContactExists;
    protected String productType;
    protected boolean realEastateEmpty;
    protected boolean salaryDocEmpty;
    protected String workCity;
    protected boolean workDocEmpty;
    protected int workMonths;
    protected boolean coborrowDocEmpty;
    protected String coborrowWorkCity;
    protected String liveCity;
    protected String coupleLiveCity;
    protected String coborrowLiveCity;
    // 申请人有法人保证人
 	private String legalPersonExist;
 	// 法人保证人文件夹为空
 	private boolean legalPersonDocEmpty;
 	// 申请人有自然人保证人
 	private String naturalPersonExist;
 	// 自然人保证人文件夹为空
 	private boolean naturalPersonDocEmpty;
 	

    public String getLegalPersonExist() {
		return legalPersonExist;
	}

	public void setLegalPersonExist(String legalPersonExist) {
		this.legalPersonExist = legalPersonExist;
	}

	public boolean isLegalPersonDocEmpty() {
		return legalPersonDocEmpty;
	}

	public void setLegalPersonDocEmpty(boolean legalPersonDocEmpty) {
		this.legalPersonDocEmpty = legalPersonDocEmpty;
	}

	public String getNaturalPersonExist() {
		return naturalPersonExist;
	}

	public void setNaturalPersonExist(String naturalPersonExist) {
		this.naturalPersonExist = naturalPersonExist;
	}

	public boolean isNaturalPersonDocEmpty() {
		return naturalPersonDocEmpty;
	}

	public void setNaturalPersonDocEmpty(boolean naturalPersonDocEmpty) {
		this.naturalPersonDocEmpty = naturalPersonDocEmpty;
	}

	/**
     * 获取age属性的值。
     * 
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置age属性的值。
     * 
     */
    public void setAge(int value) {
        this.age = value;
    }
    
    public boolean isCoborrowDocEmpty() {
		return coborrowDocEmpty;
	}

	public void setCoborrowDocEmpty(boolean coborrowDocEmpty) {
		this.coborrowDocEmpty = coborrowDocEmpty;
	}

	public String getCoborrowWorkCity() {
		return coborrowWorkCity;
	}

	public void setCoborrowWorkCity(String coborrowWorkCity) {
		this.coborrowWorkCity = coborrowWorkCity;
	}

	public String getLiveCity() {
		return liveCity;
	}

	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}

	public String getCoupleLiveCity() {
		return coupleLiveCity;
	}

	public void setCoupleLiveCity(String coupleLiveCity) {
		this.coupleLiveCity = coupleLiveCity;
	}

	public String getCoborrowLiveCity() {
		return coborrowLiveCity;
	}

	public void setCoborrowLiveCity(String coborrowLiveCity) {
		this.coborrowLiveCity = coborrowLiveCity;
	}

    /**
     * 获取applyMaterialEmpty属性的值。
     * 
     */
    public boolean isApplyMaterialEmpty() {
        return applyMaterialEmpty;
    }

    /**
     * 设置applyMaterialEmpty属性的值。
     * 
     */
    public void setApplyMaterialEmpty(boolean value) {
        this.applyMaterialEmpty = value;
    }

    /**
     * 获取bankSavingsEmpty属性的值。
     * 
     */
    public boolean isBankSavingsEmpty() {
        return bankSavingsEmpty;
    }

    /**
     * 设置bankSavingsEmpty属性的值。
     * 
     */
    public void setBankSavingsEmpty(boolean value) {
        this.bankSavingsEmpty = value;
    }

    /**
     * 获取birthPlace属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthPlace() {
        return birthPlace;
    }

    /**
     * 设置birthPlace属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthPlace(String value) {
        this.birthPlace = value;
    }

    /**
     * 获取coborrowAge属性的值。
     * 
     */
    public int getCoborrowAge() {
        return coborrowAge;
    }

    /**
     * 设置coborrowAge属性的值。
     * 
     */
    public void setCoborrowAge(int value) {
        this.coborrowAge = value;
    }

    /**
     * 获取coborrowBirthPlace属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoborrowBirthPlace() {
        return coborrowBirthPlace;
    }

    /**
     * 设置coborrowBirthPlace属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoborrowBirthPlace(String value) {
        this.coborrowBirthPlace = value;
    }

    /**
     * 获取coborrowIdentityNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoborrowIdentityNo() {
        return coborrowIdentityNo;
    }

    /**
     * 设置coborrowIdentityNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoborrowIdentityNo(String value) {
        this.coborrowIdentityNo = value;
    }

    /**
     * 获取coborrowerExist属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoborrowerExist() {
        return coborrowerExist;
    }

    /**
     * 设置coborrowerExist属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoborrowerExist(String value) {
        this.coborrowerExist = value;
    }

    /**
     * 获取companyBankAccountEmpty属性的值。
     * 
     */
    public boolean isCompanyBankAccountEmpty() {
        return companyBankAccountEmpty;
    }

    /**
     * 设置companyBankAccountEmpty属性的值。
     * 
     */
    public void setCompanyBankAccountEmpty(boolean value) {
        this.companyBankAccountEmpty = value;
    }

    /**
     * 获取companyHisLoanUnpaid属性的值。
     * 
     */
    public boolean isCompanyHisLoanUnpaid() {
        return companyHisLoanUnpaid;
    }

    /**
     * 设置companyHisLoanUnpaid属性的值。
     * 
     */
    public void setCompanyHisLoanUnpaid(boolean value) {
        this.companyHisLoanUnpaid = value;
    }

    /**
     * 获取coupleCurrentLoanOverdue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoupleCurrentLoanOverdue() {
        return coupleCurrentLoanOverdue;
    }

    /**
     * 设置coupleCurrentLoanOverdue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoupleCurrentLoanOverdue(String value) {
        this.coupleCurrentLoanOverdue = value;
    }

    /**
     * 获取coupleHisLoanUnpaid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoupleHisLoanUnpaid() {
        return coupleHisLoanUnpaid;
    }

    /**
     * 设置coupleHisLoanUnpaid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoupleHisLoanUnpaid(String value) {
        this.coupleHisLoanUnpaid = value;
    }

    /**
     * 获取coupleHisLoanUnpaidAsCoborrow属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoupleHisLoanUnpaidAsCoborrow() {
        return coupleHisLoanUnpaidAsCoborrow;
    }

    /**
     * 设置coupleHisLoanUnpaidAsCoborrow属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoupleHisLoanUnpaidAsCoborrow(String value) {
        this.coupleHisLoanUnpaidAsCoborrow = value;
    }

    /**
     * 获取coupleIdentityNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoupleIdentityNo() {
        return coupleIdentityNo;
    }

    /**
     * 设置coupleIdentityNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoupleIdentityNo(String value) {
        this.coupleIdentityNo = value;
    }

    /**
     * 获取coupleWorkCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoupleWorkCity() {
        return coupleWorkCity;
    }

    /**
     * 设置coupleWorkCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoupleWorkCity(String value) {
        this.coupleWorkCity = value;
    }

    /**
     * 获取creditEnterpriseEmpty属性的值。
     * 
     */
    public boolean isCreditEnterpriseEmpty() {
        return creditEnterpriseEmpty;
    }

    /**
     * 设置creditEnterpriseEmpty属性的值。
     * 
     */
    public void setCreditEnterpriseEmpty(boolean value) {
        this.creditEnterpriseEmpty = value;
    }

    /**
     * 获取creditReportDetailEmpty属性的值。
     * 
     */
    public boolean isCreditReportDetailEmpty() {
        return creditReportDetailEmpty;
    }

    /**
     * 设置creditReportDetailEmpty属性的值。
     * 
     */
    public void setCreditReportDetailEmpty(boolean value) {
        this.creditReportDetailEmpty = value;
    }

    /**
     * 获取creditReportSimpleEmpty属性的值。
     * 
     */
    public boolean isCreditReportSimpleEmpty() {
        return creditReportSimpleEmpty;
    }

    /**
     * 设置creditReportSimpleEmpty属性的值。
     * 
     */
    public void setCreditReportSimpleEmpty(boolean value) {
        this.creditReportSimpleEmpty = value;
    }

    /**
     * 获取fromLocal属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromLocal() {
        return fromLocal;
    }

    /**
     * 设置fromLocal属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromLocal(String value) {
        this.fromLocal = value;
    }

    /**
     * 获取hisLoanUnpaid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHisLoanUnpaid() {
        return hisLoanUnpaid;
    }

    /**
     * 设置hisLoanUnpaid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHisLoanUnpaid(String value) {
        this.hisLoanUnpaid = value;
    }

    /**
     * 获取hisLoanUnpaidAsCoborrow属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHisLoanUnpaidAsCoborrow() {
        return hisLoanUnpaidAsCoborrow;
    }

    /**
     * 设置hisLoanUnpaidAsCoborrow属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHisLoanUnpaidAsCoborrow(String value) {
        this.hisLoanUnpaidAsCoborrow = value;
    }

    /**
     * 获取identityDocEmpty属性的值。
     * 
     */
    public boolean isIdentityDocEmpty() {
        return identityDocEmpty;
    }

    /**
     * 设置identityDocEmpty属性的值。
     * 
     */
    public void setIdentityDocEmpty(boolean value) {
        this.identityDocEmpty = value;
    }

    /**
     * 获取identityNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentityNo() {
        return identityNo;
    }

    /**
     * 设置identityNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentityNo(String value) {
        this.identityNo = value;
    }

    /**
     * 获取livingMaterialEmpty属性的值。
     * 
     */
    public boolean isLivingMaterialEmpty() {
        return livingMaterialEmpty;
    }

    /**
     * 设置livingMaterialEmpty属性的值。
     * 
     */
    public void setLivingMaterialEmpty(boolean value) {
        this.livingMaterialEmpty = value;
    }

    /**
     * 获取loanAmount属性的值。
     * 
     */
    public int getLoanAmount() {
        return loanAmount;
    }

    /**
     * 设置loanAmount属性的值。
     * 
     */
    public void setLoanAmount(int value) {
        this.loanAmount = value;
    }

    /**
     * 获取manageDocEmpty属性的值。
     * 
     */
    public boolean isManageDocEmpty() {
        return manageDocEmpty;
    }

    /**
     * 设置manageDocEmpty属性的值。
     * 
     */
    public void setManageDocEmpty(boolean value) {
        this.manageDocEmpty = value;
    }

    /**
     * 获取manageMonths属性的值。
     * 
     */
    public int getManageMonths() {
        return manageMonths;
    }

    /**
     * 设置manageMonths属性的值。
     * 
     */
    public void setManageMonths(int value) {
        this.manageMonths = value;
    }

    /**
     * 获取marriageDocEmpty属性的值。
     * 
     */
    public boolean isMarriageDocEmpty() {
        return marriageDocEmpty;
    }

    /**
     * 设置marriageDocEmpty属性的值。
     * 
     */
    public void setMarriageDocEmpty(boolean value) {
        this.marriageDocEmpty = value;
    }
    
    /**
     * 获取noCreditReport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoCreditReport() {
        return noCreditReport;
    }

    /**
     * 设置noCreditReport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoCreditReport(String value) {
        this.noCreditReport = value;
    }
    
    /**
     * 获取noCreditHistory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoCreditHistory() {
        return noCreditHistory;
    }

    /**
     * 设置noCreditHistory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoCreditHistory(String value) {
        this.noCreditHistory = value;
    }

    /**
     * 获取notUnMarried属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotUnMarried() {
        return notUnMarried;
    }

    /**
     * 设置notUnMarried属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotUnMarried(String value) {
        this.notUnMarried = value;
    }

    /**
     * 获取otherDocEmpty属性的值。
     * 
     */
    public boolean isOtherDocEmpty() {
        return otherDocEmpty;
    }

    /**
     * 设置otherDocEmpty属性的值。
     * 
     */
    public void setOtherDocEmpty(boolean value) {
        this.otherDocEmpty = value;
    }

    /**
     * 获取parentsContactExists属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentsContactExists() {
        return parentsContactExists;
    }

    /**
     * 设置parentsContactExists属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentsContactExists(String value) {
        this.parentsContactExists = value;
    }

    /**
     * 获取productType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductType() {
        return productType;
    }

    /**
     * 设置productType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductType(String value) {
        this.productType = value;
    }

    /**
     * 获取realEastateEmpty属性的值。
     * 
     */
    public boolean isRealEastateEmpty() {
        return realEastateEmpty;
    }

    /**
     * 设置realEastateEmpty属性的值。
     * 
     */
    public void setRealEastateEmpty(boolean value) {
        this.realEastateEmpty = value;
    }

    /**
     * 获取salaryDocEmpty属性的值。
     * 
     */
    public boolean isSalaryDocEmpty() {
        return salaryDocEmpty;
    }

    /**
     * 设置salaryDocEmpty属性的值。
     * 
     */
    public void setSalaryDocEmpty(boolean value) {
        this.salaryDocEmpty = value;
    }

    /**
     * 获取workCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkCity() {
        return workCity;
    }

    /**
     * 设置workCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkCity(String value) {
        this.workCity = value;
    }

    /**
     * 获取workDocEmpty属性的值。
     * 
     */
    public boolean isWorkDocEmpty() {
        return workDocEmpty;
    }

    /**
     * 设置workDocEmpty属性的值。
     * 
     */
    public void setWorkDocEmpty(boolean value) {
        this.workDocEmpty = value;
    }

    /**
     * 获取workMonths属性的值。
     * 
     */
    public int getWorkMonths() {
        return workMonths;
    }

    /**
     * 设置workMonths属性的值。
     * 
     */
    public void setWorkMonths(int value) {
        this.workMonths = value;
    }
    
    @Override
	public String toString() {
    	StringBuffer strBuff = new StringBuffer("");
		Field[] field = getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				String name = field[j].getName(); // 获取属性的名字
				if (!(name.equals("identityNo") || name.equals("coupleIdentityNo") || name.equals("coborrowIdentityNo"))) {
					// 将属性的首字符大写，方便构造get，set方法
					String upperName = name.substring(0, 1).toUpperCase() + name.substring(1);
					String type = field[j].getGenericType().toString(); // 获取属性的类型
					if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
						Method m = getClass().getMethod("get" + upperName);
						String value = (String) m.invoke(this); // 调用getter方法获取属性值
						if (value != null) {
							strBuff.append("{" + name + ":" + value + "}");
						}
					}
					if (type.equals("class java.lang.Integer")) {
						Method m = getClass().getMethod("get" + upperName);
						Integer value = (Integer) m.invoke(this);
						if (value != null) {
							strBuff.append("{" + name + ":" + value + "}");
						}
					}
				}
			}
		} catch (SecurityException e1) {			
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
		}
		return strBuff.toString();
	}
    
}

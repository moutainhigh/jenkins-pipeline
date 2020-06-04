
package com.creditharmony.approve.rule.auditrating.client;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>auditRatingParam complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="auditRatingParam">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="companyWorkYear" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="earliestCreditMonth" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="earliestLoanMonth" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="education" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="finalScore" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="housingSituation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latestCreditMonth" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="loanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marriage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="normalCreditUseRate" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="numLoanOverdue" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="personalQueryNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="queriesLoanSp" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="rateCheckResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nSixNewLoanCredit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="nSixQueriesNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "auditRatingParam", propOrder = {
    "age",
    "companyWorkYear",
    "earliestCreditMonth",
    "earliestLoanMonth",
    "education",
    "finalScore",
    "housingSituation",
    "latestCreditMonth",
    "loanCode",
    "marriage",
    "normalCreditUseRate",
    "numLoanOverdue",
    "personalQueryNum",
    "queriesLoanSp",
    "rateCheckResult",
    "sex",
    "nSixNewLoanCredit",
    "nSixQueriesNum"
})
public class AuditRatingParam {

    protected Integer age;
    protected Integer companyWorkYear;
    protected Integer earliestCreditMonth;
    protected Integer earliestLoanMonth;
    protected String education;
    protected Integer finalScore;
    protected String housingSituation;
    protected Integer latestCreditMonth;
    protected String loanCode;
    protected String marriage;
    protected float normalCreditUseRate;
    protected Integer numLoanOverdue;
    protected Integer personalQueryNum;
    protected Integer queriesLoanSp;
    protected String rateCheckResult;
    protected String sex;
    protected boolean nSixNewLoanCredit;
    protected Integer nSixQueriesNum;

    /**
     * 获取age属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置age属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAge(Integer value) {
        this.age = value;
    }

    /**
     * 获取companyWorkYear属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCompanyWorkYear() {
        return companyWorkYear;
    }

    /**
     * 设置companyWorkYear属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCompanyWorkYear(Integer value) {
        this.companyWorkYear = value;
    }

    /**
     * 获取earliestCreditMonth属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEarliestCreditMonth() {
        return earliestCreditMonth;
    }

    /**
     * 设置earliestCreditMonth属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEarliestCreditMonth(Integer value) {
        this.earliestCreditMonth = value;
    }

    /**
     * 获取earliestLoanMonth属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEarliestLoanMonth() {
        return earliestLoanMonth;
    }

    /**
     * 设置earliestLoanMonth属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEarliestLoanMonth(Integer value) {
        this.earliestLoanMonth = value;
    }

    /**
     * 获取education属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置education属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEducation(String value) {
        this.education = value;
    }

    /**
     * 获取finalScore属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFinalScore() {
        return finalScore;
    }

    /**
     * 设置finalScore属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFinalScore(Integer value) {
        this.finalScore = value;
    }

    /**
     * 获取housingSituation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHousingSituation() {
        return housingSituation;
    }

    /**
     * 设置housingSituation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHousingSituation(String value) {
        this.housingSituation = value;
    }

    /**
     * 获取latestCreditMonth属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLatestCreditMonth() {
        return latestCreditMonth;
    }

    /**
     * 设置latestCreditMonth属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLatestCreditMonth(Integer value) {
        this.latestCreditMonth = value;
    }

    /**
     * 获取loanCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanCode() {
        return loanCode;
    }

    /**
     * 设置loanCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanCode(String value) {
        this.loanCode = value;
    }

    /**
     * 获取marriage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarriage() {
        return marriage;
    }

    /**
     * 设置marriage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarriage(String value) {
        this.marriage = value;
    }

    /**
     * 获取normalCreditUseRate属性的值。
     * 
     */
    public float getNormalCreditUseRate() {
        return normalCreditUseRate;
    }

    /**
     * 设置normalCreditUseRate属性的值。
     * 
     */
    public void setNormalCreditUseRate(float value) {
        this.normalCreditUseRate = value;
    }

    /**
     * 获取numLoanOverdue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumLoanOverdue() {
        return numLoanOverdue;
    }

    /**
     * 设置numLoanOverdue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumLoanOverdue(Integer value) {
        this.numLoanOverdue = value;
    }

    /**
     * 获取personalQueryNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPersonalQueryNum() {
        return personalQueryNum;
    }

    /**
     * 设置personalQueryNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPersonalQueryNum(Integer value) {
        this.personalQueryNum = value;
    }

    /**
     * 获取queriesLoanSp属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQueriesLoanSp() {
        return queriesLoanSp;
    }

    /**
     * 设置queriesLoanSp属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQueriesLoanSp(Integer value) {
        this.queriesLoanSp = value;
    }

    /**
     * 获取rateCheckResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCheckResult() {
        return rateCheckResult;
    }

    /**
     * 设置rateCheckResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCheckResult(String value) {
        this.rateCheckResult = value;
    }

    /**
     * 获取sex属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置sex属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSex(String value) {
        this.sex = value;
    }

    /**
     * 获取nSixNewLoanCredit属性的值。
     * 
     */
    public boolean isNSixNewLoanCredit() {
        return nSixNewLoanCredit;
    }

    /**
     * 设置nSixNewLoanCredit属性的值。
     * 
     */
    public void setNSixNewLoanCredit(boolean value) {
        this.nSixNewLoanCredit = value;
    }

    /**
     * 获取nSixQueriesNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNSixQueriesNum() {
        return nSixQueriesNum;
    }

    /**
     * 设置nSixQueriesNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNSixQueriesNum(Integer value) {
        this.nSixQueriesNum = value;
    }
    
    @Override
	public String toString() {
    	StringBuffer strBuff = new StringBuffer("");
		Field[] field = getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				String name = field[j].getName(); // 获取属性的名字
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
				if (type.equals("float")) {
					Method m = getClass().getMethod("get" + upperName);
					Float value = (Float) m.invoke(this);
					strBuff.append("{" + name + ":" + value + "}");					
				}
				if (type.equals("boolean")) {
					Method m = getClass().getMethod("is" + upperName);
					Boolean value = (Boolean) m.invoke(this);
					strBuff.append("{" + name + ":" + value + "}");					
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

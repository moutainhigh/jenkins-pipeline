
package com.creditharmony.approve.rule.applyengine.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DecisionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element ref="{http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules/param}credit"/>
 *         &lt;element ref="{http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules/param}loan"/>
 *         &lt;element ref="{http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules/param}rating"/>
 *         &lt;element ref="{http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules/param}result"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "decisionID",
    "credit",
    "loan",
    "rating",
    "result"
})
@XmlRootElement(name = "Applyengine_rulesRequest")
public class ApplyengineRulesRequest {

    @XmlElement(name = "DecisionID", namespace = "http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules")
    protected String decisionID;
    @XmlElement(namespace = "http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules/param", required = true)
    protected Credit credit;
    @XmlElement(namespace = "http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules/param", required = true)
    protected Loan loan;
    @XmlElement(namespace = "http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules/param", required = true)
    protected Rating rating;
    @XmlElement(namespace = "http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules/param", required = true)
    protected Result result;

    /**
     * 获取decisionID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecisionID() {
        return decisionID;
    }

    /**
     * 设置decisionID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecisionID(String value) {
        this.decisionID = value;
    }

    /**
     * 获取credit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Credit }
     *     
     */
    public Credit getCredit() {
        return credit;
    }

    /**
     * 设置credit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Credit }
     *     
     */
    public void setCredit(Credit value) {
        this.credit = value;
    }

    /**
     * 获取loan属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Loan }
     *     
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * 设置loan属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Loan }
     *     
     */
    public void setLoan(Loan value) {
        this.loan = value;
    }

    /**
     * 获取rating属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Rating }
     *     
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * 设置rating属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Rating }
     *     
     */
    public void setRating(Rating value) {
        this.rating = value;
    }

    /**
     * 获取result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setResult(Result value) {
        this.result = value;
    }

}

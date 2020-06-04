
package com.creditharmony.approve.rule.auditrating.client;

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
 *         &lt;element ref="{http://www.ibm.com/rules/decisionservice/AuditRating_ruleapp/AuditRating_rules/param}param"/>
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
    "param"
})
@XmlRootElement(name = "AuditRating_rulesRequest")
public class AuditRatingRulesRequest {

    @XmlElement(name = "DecisionID", namespace = "http://www.ibm.com/rules/decisionservice/AuditRating_ruleapp/AuditRating_rules")
    protected String decisionID;
    @XmlElement(namespace = "http://www.ibm.com/rules/decisionservice/AuditRating_ruleapp/AuditRating_rules/param", required = true)
    protected Param param;

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
     * 获取param属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Param }
     *     
     */
    public Param getParam() {
        return param;
    }

    /**
     * 设置param属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Param }
     *     
     */
    public void setParam(Param value) {
        this.param = value;
    }

}


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
 *         &lt;element name="param" type="{http://www.ibm.com/rules/decisionservice/AuditRating_ruleapp/AuditRating_rules}auditRatingParam"/>
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
    "param"
})
@XmlRootElement(name = "param", namespace = "http://www.ibm.com/rules/decisionservice/AuditRating_ruleapp/AuditRating_rules/param")
public class Param {

    @XmlElement(required = true)
    protected AuditRatingParam param;

    /**
     * 获取param属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AuditRatingParam }
     *     
     */
    public AuditRatingParam getParam() {
        return param;
    }

    /**
     * 设置param属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AuditRatingParam }
     *     
     */
    public void setParam(AuditRatingParam value) {
        this.param = value;
    }

}

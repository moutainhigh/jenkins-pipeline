
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
 *         &lt;element name="credit" type="{http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules}creditInfo"/>
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
    "credit"
})
@XmlRootElement(name = "credit", namespace = "http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules/param")
public class Credit {

    @XmlElement(required = true)
    protected CreditInfo credit;

    /**
     * 获取credit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CreditInfo }
     *     
     */
    public CreditInfo getCredit() {
        return credit;
    }

    /**
     * 设置credit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CreditInfo }
     *     
     */
    public void setCredit(CreditInfo value) {
        this.credit = value;
    }

}

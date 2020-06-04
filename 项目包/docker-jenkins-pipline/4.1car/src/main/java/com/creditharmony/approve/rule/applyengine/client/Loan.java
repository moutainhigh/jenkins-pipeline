
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
 *         &lt;element name="loan" type="{http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules}loanApply"/>
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
    "loan"
})
@XmlRootElement(name = "loan", namespace = "http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules/param")
public class Loan {

    @XmlElement(required = true)
    protected LoanApply loan;

    /**
     * 获取loan属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LoanApply }
     *     
     */
    public LoanApply getLoan() {
        return loan;
    }

    /**
     * 设置loan属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LoanApply }
     *     
     */
    public void setLoan(LoanApply value) {
        this.loan = value;
    }

}

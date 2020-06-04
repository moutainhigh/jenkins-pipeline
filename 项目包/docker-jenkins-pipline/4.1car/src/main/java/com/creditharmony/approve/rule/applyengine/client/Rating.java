
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
 *         &lt;element name="rating" type="{http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules}ratingParam"/>
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
    "rating"
})
@XmlRootElement(name = "rating", namespace = "http://www.ibm.com/rules/decisionservice/Applyengine_ruleapp/Applyengine_rules/param")
public class Rating {

    @XmlElement(required = true)
    protected RatingParam rating;

    /**
     * 获取rating属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RatingParam }
     *     
     */
    public RatingParam getRating() {
        return rating;
    }

    /**
     * 设置rating属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RatingParam }
     *     
     */
    public void setRating(RatingParam value) {
        this.rating = value;
    }

}

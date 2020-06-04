
package com.creditharmony.approve.rule.applyengine.client;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>creditInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="creditInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cardHasBadDebtBrief" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="closedCardHasMaxOverdueBrief" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="closedCardHasNoNewCreditBrief" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="creditCycleHasDDZGDetail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="creditLevelHasCKSDetail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="currentOverdueGTTwoCardDetail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="currentOverdueGTTwoLoanDetail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="externalVouchHasBadRating" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lawQueryWithinThreeMonths" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="loanCycleHasDGZDetail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="loanHasBadDebtBrief" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="loanHasBadDebtsDetail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="loanLevelHasCKSDetail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="maxOverdueCardBrief" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxOverdueCardToYearDetail" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxOverdueLoanBrief" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxOverdueLoanToYearDetail" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="monthsFromCardCloseDateBrief" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="monthsFromLoanPayDateBrief" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="normalCardHasMaxOverdueBrief" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="normalLoanHasMaxOverdueBrief" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="overdueGTThreeCardToYearDetail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="overdueGTThreeLoanToYearDetail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="personalCreditHasNoNewCredit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="settleCompanyLoanHasDDZ" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="settleCompanyLoanHasNoNewCredit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="settleCompanyLoanLevelHasGCKS" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="settledLoanHasMaxOverdueBrief" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="settledLoanHasNoNewCreditBrief" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="totalOverdueCardBrief" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalOverdueCardToYearDetail" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalOverdueLoanBrief" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalOverdueLoanToYearDetail" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unSettleCompanyLoanHasAdvances" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="unSettleCompanyLoanHasDDZ" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="unSettleCompanyLoanLevelHasCKS" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditInfo", propOrder = {
    "cardHasBadDebtBrief",
    "closedCardHasMaxOverdueBrief",
    "closedCardHasNoNewCreditBrief",
    "creditCycleHasDDZGDetail",
    "creditLevelHasCKSDetail",
    "currentOverdueGTTwoCardDetail",
    "currentOverdueGTTwoLoanDetail",
    "externalVouchHasBadRating",
    "lawQueryWithinThreeMonths",
    "loanCycleHasDGZDetail",
    "loanHasBadDebtBrief",
    "loanHasBadDebtsDetail",
    "loanLevelHasCKSDetail",
    "maxOverdueCardBrief",
    "maxOverdueCardToYearDetail",
    "maxOverdueLoanBrief",
    "maxOverdueLoanToYearDetail",
    "monthsFromCardCloseDateBrief",
    "monthsFromLoanPayDateBrief",
    "normalCardHasMaxOverdueBrief",
    "normalLoanHasMaxOverdueBrief",
    "overdueGTThreeCardToYearDetail",
    "overdueGTThreeLoanToYearDetail",
    "personalCreditHasNoNewCredit",
    "settleCompanyLoanHasDDZ",
    "settleCompanyLoanHasNoNewCredit",
    "settleCompanyLoanLevelHasGCKS",
    "settledLoanHasMaxOverdueBrief",
    "settledLoanHasNoNewCreditBrief",
    "totalOverdueCardBrief",
    "totalOverdueCardToYearDetail",
    "totalOverdueLoanBrief",
    "totalOverdueLoanToYearDetail",
    "unSettleCompanyLoanHasAdvances",
    "unSettleCompanyLoanHasDDZ",
    "unSettleCompanyLoanLevelHasCKS"
})
public class CreditInfo {

    protected boolean cardHasBadDebtBrief;
    protected boolean closedCardHasMaxOverdueBrief;
    protected boolean closedCardHasNoNewCreditBrief;
    protected boolean creditCycleHasDDZGDetail;
    protected boolean creditLevelHasCKSDetail;
    protected boolean currentOverdueGTTwoCardDetail;
    protected boolean currentOverdueGTTwoLoanDetail;
    protected boolean externalVouchHasBadRating;
    protected boolean lawQueryWithinThreeMonths;
    protected boolean loanCycleHasDGZDetail;
    protected boolean loanHasBadDebtBrief;
    protected boolean loanHasBadDebtsDetail;
    protected boolean loanLevelHasCKSDetail;
    protected int maxOverdueCardBrief;
    protected int maxOverdueCardToYearDetail;
    protected int maxOverdueLoanBrief;
    protected int maxOverdueLoanToYearDetail;
    protected int monthsFromCardCloseDateBrief;
    protected int monthsFromLoanPayDateBrief;
    protected boolean normalCardHasMaxOverdueBrief;
    protected boolean normalLoanHasMaxOverdueBrief;
    protected boolean overdueGTThreeCardToYearDetail;
    protected boolean overdueGTThreeLoanToYearDetail;
    protected boolean personalCreditHasNoNewCredit;
    protected boolean settleCompanyLoanHasDDZ;
    protected boolean settleCompanyLoanHasNoNewCredit;
    protected boolean settleCompanyLoanLevelHasGCKS;
    protected boolean settledLoanHasMaxOverdueBrief;
    protected boolean settledLoanHasNoNewCreditBrief;
    protected int totalOverdueCardBrief;
    protected int totalOverdueCardToYearDetail;
    protected int totalOverdueLoanBrief;
    protected int totalOverdueLoanToYearDetail;
    protected boolean unSettleCompanyLoanHasAdvances;
    protected boolean unSettleCompanyLoanHasDDZ;
    protected boolean unSettleCompanyLoanLevelHasCKS;

    /**
     * 获取cardHasBadDebtBrief属性的值。
     * 
     */
    public boolean isCardHasBadDebtBrief() {
        return cardHasBadDebtBrief;
    }

    /**
     * 设置cardHasBadDebtBrief属性的值。
     * 
     */
    public void setCardHasBadDebtBrief(boolean value) {
        this.cardHasBadDebtBrief = value;
    }

    /**
     * 获取closedCardHasMaxOverdueBrief属性的值。
     * 
     */
    public boolean isClosedCardHasMaxOverdueBrief() {
        return closedCardHasMaxOverdueBrief;
    }

    /**
     * 设置closedCardHasMaxOverdueBrief属性的值。
     * 
     */
    public void setClosedCardHasMaxOverdueBrief(boolean value) {
        this.closedCardHasMaxOverdueBrief = value;
    }

    /**
     * 获取closedCardHasNoNewCreditBrief属性的值。
     * 
     */
    public boolean isClosedCardHasNoNewCreditBrief() {
        return closedCardHasNoNewCreditBrief;
    }

    /**
     * 设置closedCardHasNoNewCreditBrief属性的值。
     * 
     */
    public void setClosedCardHasNoNewCreditBrief(boolean value) {
        this.closedCardHasNoNewCreditBrief = value;
    }

    /**
     * 获取creditCycleHasDDZGDetail属性的值。
     * 
     */
    public boolean isCreditCycleHasDDZGDetail() {
        return creditCycleHasDDZGDetail;
    }

    /**
     * 设置creditCycleHasDDZGDetail属性的值。
     * 
     */
    public void setCreditCycleHasDDZGDetail(boolean value) {
        this.creditCycleHasDDZGDetail = value;
    }

    /**
     * 获取creditLevelHasCKSDetail属性的值。
     * 
     */
    public boolean isCreditLevelHasCKSDetail() {
        return creditLevelHasCKSDetail;
    }

    /**
     * 设置creditLevelHasCKSDetail属性的值。
     * 
     */
    public void setCreditLevelHasCKSDetail(boolean value) {
        this.creditLevelHasCKSDetail = value;
    }

    /**
     * 获取currentOverdueGTTwoCardDetail属性的值。
     * 
     */
    public boolean isCurrentOverdueGTTwoCardDetail() {
        return currentOverdueGTTwoCardDetail;
    }

    /**
     * 设置currentOverdueGTTwoCardDetail属性的值。
     * 
     */
    public void setCurrentOverdueGTTwoCardDetail(boolean value) {
        this.currentOverdueGTTwoCardDetail = value;
    }

    /**
     * 获取currentOverdueGTTwoLoanDetail属性的值。
     * 
     */
    public boolean isCurrentOverdueGTTwoLoanDetail() {
        return currentOverdueGTTwoLoanDetail;
    }

    /**
     * 设置currentOverdueGTTwoLoanDetail属性的值。
     * 
     */
    public void setCurrentOverdueGTTwoLoanDetail(boolean value) {
        this.currentOverdueGTTwoLoanDetail = value;
    }

    /**
     * 获取externalVouchHasBadRating属性的值。
     * 
     */
    public boolean isExternalVouchHasBadRating() {
        return externalVouchHasBadRating;
    }

    /**
     * 设置externalVouchHasBadRating属性的值。
     * 
     */
    public void setExternalVouchHasBadRating(boolean value) {
        this.externalVouchHasBadRating = value;
    }

    /**
     * 获取lawQueryWithinThreeMonths属性的值。
     * 
     */
    public boolean isLawQueryWithinThreeMonths() {
        return lawQueryWithinThreeMonths;
    }

    /**
     * 设置lawQueryWithinThreeMonths属性的值。
     * 
     */
    public void setLawQueryWithinThreeMonths(boolean value) {
        this.lawQueryWithinThreeMonths = value;
    }

    /**
     * 获取loanCycleHasDGZDetail属性的值。
     * 
     */
    public boolean isLoanCycleHasDGZDetail() {
        return loanCycleHasDGZDetail;
    }

    /**
     * 设置loanCycleHasDGZDetail属性的值。
     * 
     */
    public void setLoanCycleHasDGZDetail(boolean value) {
        this.loanCycleHasDGZDetail = value;
    }

    /**
     * 获取loanHasBadDebtBrief属性的值。
     * 
     */
    public boolean isLoanHasBadDebtBrief() {
        return loanHasBadDebtBrief;
    }

    /**
     * 设置loanHasBadDebtBrief属性的值。
     * 
     */
    public void setLoanHasBadDebtBrief(boolean value) {
        this.loanHasBadDebtBrief = value;
    }

    /**
     * 获取loanHasBadDebtsDetail属性的值。
     * 
     */
    public boolean isLoanHasBadDebtsDetail() {
        return loanHasBadDebtsDetail;
    }

    /**
     * 设置loanHasBadDebtsDetail属性的值。
     * 
     */
    public void setLoanHasBadDebtsDetail(boolean value) {
        this.loanHasBadDebtsDetail = value;
    }

    /**
     * 获取loanLevelHasCKSDetail属性的值。
     * 
     */
    public boolean isLoanLevelHasCKSDetail() {
        return loanLevelHasCKSDetail;
    }

    /**
     * 设置loanLevelHasCKSDetail属性的值。
     * 
     */
    public void setLoanLevelHasCKSDetail(boolean value) {
        this.loanLevelHasCKSDetail = value;
    }

    /**
     * 获取maxOverdueCardBrief属性的值。
     * 
     */
    public int getMaxOverdueCardBrief() {
        return maxOverdueCardBrief;
    }

    /**
     * 设置maxOverdueCardBrief属性的值。
     * 
     */
    public void setMaxOverdueCardBrief(int value) {
        this.maxOverdueCardBrief = value;
    }

    /**
     * 获取maxOverdueCardToYearDetail属性的值。
     * 
     */
    public int getMaxOverdueCardToYearDetail() {
        return maxOverdueCardToYearDetail;
    }

    /**
     * 设置maxOverdueCardToYearDetail属性的值。
     * 
     */
    public void setMaxOverdueCardToYearDetail(int value) {
        this.maxOverdueCardToYearDetail = value;
    }

    /**
     * 获取maxOverdueLoanBrief属性的值。
     * 
     */
    public int getMaxOverdueLoanBrief() {
        return maxOverdueLoanBrief;
    }

    /**
     * 设置maxOverdueLoanBrief属性的值。
     * 
     */
    public void setMaxOverdueLoanBrief(int value) {
        this.maxOverdueLoanBrief = value;
    }

    /**
     * 获取maxOverdueLoanToYearDetail属性的值。
     * 
     */
    public int getMaxOverdueLoanToYearDetail() {
        return maxOverdueLoanToYearDetail;
    }

    /**
     * 设置maxOverdueLoanToYearDetail属性的值。
     * 
     */
    public void setMaxOverdueLoanToYearDetail(int value) {
        this.maxOverdueLoanToYearDetail = value;
    }

    /**
     * 获取monthsFromCardCloseDateBrief属性的值。
     * 
     */
    public int getMonthsFromCardCloseDateBrief() {
        return monthsFromCardCloseDateBrief;
    }

    /**
     * 设置monthsFromCardCloseDateBrief属性的值。
     * 
     */
    public void setMonthsFromCardCloseDateBrief(int value) {
        this.monthsFromCardCloseDateBrief = value;
    }

    /**
     * 获取monthsFromLoanPayDateBrief属性的值。
     * 
     */
    public int getMonthsFromLoanPayDateBrief() {
        return monthsFromLoanPayDateBrief;
    }

    /**
     * 设置monthsFromLoanPayDateBrief属性的值。
     * 
     */
    public void setMonthsFromLoanPayDateBrief(int value) {
        this.monthsFromLoanPayDateBrief = value;
    }

    /**
     * 获取normalCardHasMaxOverdueBrief属性的值。
     * 
     */
    public boolean isNormalCardHasMaxOverdueBrief() {
        return normalCardHasMaxOverdueBrief;
    }

    /**
     * 设置normalCardHasMaxOverdueBrief属性的值。
     * 
     */
    public void setNormalCardHasMaxOverdueBrief(boolean value) {
        this.normalCardHasMaxOverdueBrief = value;
    }

    /**
     * 获取normalLoanHasMaxOverdueBrief属性的值。
     * 
     */
    public boolean isNormalLoanHasMaxOverdueBrief() {
        return normalLoanHasMaxOverdueBrief;
    }

    /**
     * 设置normalLoanHasMaxOverdueBrief属性的值。
     * 
     */
    public void setNormalLoanHasMaxOverdueBrief(boolean value) {
        this.normalLoanHasMaxOverdueBrief = value;
    }

    /**
     * 获取overdueGTThreeCardToYearDetail属性的值。
     * 
     */
    public boolean isOverdueGTThreeCardToYearDetail() {
        return overdueGTThreeCardToYearDetail;
    }

    /**
     * 设置overdueGTThreeCardToYearDetail属性的值。
     * 
     */
    public void setOverdueGTThreeCardToYearDetail(boolean value) {
        this.overdueGTThreeCardToYearDetail = value;
    }

    /**
     * 获取overdueGTThreeLoanToYearDetail属性的值。
     * 
     */
    public boolean isOverdueGTThreeLoanToYearDetail() {
        return overdueGTThreeLoanToYearDetail;
    }

    /**
     * 设置overdueGTThreeLoanToYearDetail属性的值。
     * 
     */
    public void setOverdueGTThreeLoanToYearDetail(boolean value) {
        this.overdueGTThreeLoanToYearDetail = value;
    }

    /**
     * 获取personalCreditHasNoNewCredit属性的值。
     * 
     */
    public boolean isPersonalCreditHasNoNewCredit() {
        return personalCreditHasNoNewCredit;
    }

    /**
     * 设置personalCreditHasNoNewCredit属性的值。
     * 
     */
    public void setPersonalCreditHasNoNewCredit(boolean value) {
        this.personalCreditHasNoNewCredit = value;
    }

    /**
     * 获取settleCompanyLoanHasDDZ属性的值。
     * 
     */
    public boolean isSettleCompanyLoanHasDDZ() {
        return settleCompanyLoanHasDDZ;
    }

    /**
     * 设置settleCompanyLoanHasDDZ属性的值。
     * 
     */
    public void setSettleCompanyLoanHasDDZ(boolean value) {
        this.settleCompanyLoanHasDDZ = value;
    }

    /**
     * 获取settleCompanyLoanHasNoNewCredit属性的值。
     * 
     */
    public boolean isSettleCompanyLoanHasNoNewCredit() {
        return settleCompanyLoanHasNoNewCredit;
    }

    /**
     * 设置settleCompanyLoanHasNoNewCredit属性的值。
     * 
     */
    public void setSettleCompanyLoanHasNoNewCredit(boolean value) {
        this.settleCompanyLoanHasNoNewCredit = value;
    }

    /**
     * 获取settleCompanyLoanLevelHasGCKS属性的值。
     * 
     */
    public boolean isSettleCompanyLoanLevelHasGCKS() {
        return settleCompanyLoanLevelHasGCKS;
    }

    /**
     * 设置settleCompanyLoanLevelHasGCKS属性的值。
     * 
     */
    public void setSettleCompanyLoanLevelHasGCKS(boolean value) {
        this.settleCompanyLoanLevelHasGCKS = value;
    }

    /**
     * 获取settledLoanHasMaxOverdueBrief属性的值。
     * 
     */
    public boolean isSettledLoanHasMaxOverdueBrief() {
        return settledLoanHasMaxOverdueBrief;
    }

    /**
     * 设置settledLoanHasMaxOverdueBrief属性的值。
     * 
     */
    public void setSettledLoanHasMaxOverdueBrief(boolean value) {
        this.settledLoanHasMaxOverdueBrief = value;
    }

    /**
     * 获取settledLoanHasNoNewCreditBrief属性的值。
     * 
     */
    public boolean isSettledLoanHasNoNewCreditBrief() {
        return settledLoanHasNoNewCreditBrief;
    }

    /**
     * 设置settledLoanHasNoNewCreditBrief属性的值。
     * 
     */
    public void setSettledLoanHasNoNewCreditBrief(boolean value) {
        this.settledLoanHasNoNewCreditBrief = value;
    }

    /**
     * 获取totalOverdueCardBrief属性的值。
     * 
     */
    public int getTotalOverdueCardBrief() {
        return totalOverdueCardBrief;
    }

    /**
     * 设置totalOverdueCardBrief属性的值。
     * 
     */
    public void setTotalOverdueCardBrief(int value) {
        this.totalOverdueCardBrief = value;
    }

    /**
     * 获取totalOverdueCardToYearDetail属性的值。
     * 
     */
    public int getTotalOverdueCardToYearDetail() {
        return totalOverdueCardToYearDetail;
    }

    /**
     * 设置totalOverdueCardToYearDetail属性的值。
     * 
     */
    public void setTotalOverdueCardToYearDetail(int value) {
        this.totalOverdueCardToYearDetail = value;
    }

    /**
     * 获取totalOverdueLoanBrief属性的值。
     * 
     */
    public int getTotalOverdueLoanBrief() {
        return totalOverdueLoanBrief;
    }

    /**
     * 设置totalOverdueLoanBrief属性的值。
     * 
     */
    public void setTotalOverdueLoanBrief(int value) {
        this.totalOverdueLoanBrief = value;
    }

    /**
     * 获取totalOverdueLoanToYearDetail属性的值。
     * 
     */
    public int getTotalOverdueLoanToYearDetail() {
        return totalOverdueLoanToYearDetail;
    }

    /**
     * 设置totalOverdueLoanToYearDetail属性的值。
     * 
     */
    public void setTotalOverdueLoanToYearDetail(int value) {
        this.totalOverdueLoanToYearDetail = value;
    }

    /**
     * 获取unSettleCompanyLoanHasAdvances属性的值。
     * 
     */
    public boolean isUnSettleCompanyLoanHasAdvances() {
        return unSettleCompanyLoanHasAdvances;
    }

    /**
     * 设置unSettleCompanyLoanHasAdvances属性的值。
     * 
     */
    public void setUnSettleCompanyLoanHasAdvances(boolean value) {
        this.unSettleCompanyLoanHasAdvances = value;
    }

    /**
     * 获取unSettleCompanyLoanHasDDZ属性的值。
     * 
     */
    public boolean isUnSettleCompanyLoanHasDDZ() {
        return unSettleCompanyLoanHasDDZ;
    }

    /**
     * 设置unSettleCompanyLoanHasDDZ属性的值。
     * 
     */
    public void setUnSettleCompanyLoanHasDDZ(boolean value) {
        this.unSettleCompanyLoanHasDDZ = value;
    }

    /**
     * 获取unSettleCompanyLoanLevelHasCKS属性的值。
     * 
     */
    public boolean isUnSettleCompanyLoanLevelHasCKS() {
        return unSettleCompanyLoanLevelHasCKS;
    }

    /**
     * 设置unSettleCompanyLoanLevelHasCKS属性的值。
     * 
     */
    public void setUnSettleCompanyLoanLevelHasCKS(boolean value) {
        this.unSettleCompanyLoanLevelHasCKS = value;
    }
    
    @Override
	public String toString() {
    	StringBuffer strBuff = new StringBuffer("");
		Field[] field = getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				String name = field[j].getName(); // 获取属性的名字
				String upperName =  name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
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

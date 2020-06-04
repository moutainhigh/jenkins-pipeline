package com.creditharmony.approve.verify.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @author 罗俊平
 * @update in 2016-10-25
 */
public class QuotaLimit extends DataEntity<QuotaLimit> {

	private static final long serialVersionUID = 1L;

	private Integer chp1; // CHP上限数量1
	private Integer chp2; // CHP上限数量2
	private Integer chp3; // CHP上限数量3
	private Integer chp4; // CHP上限数量4
	private Integer chp5; // CHP上限数量5
	private Integer chpResidual; // CHP剩余数量
	private Integer goldCredit1; // 金信上限数量1
	private Integer goldCredit2; // 金信上限数量2
	private Integer goldCredit3; // 金信上限数量3
	private Integer goldCredit4; // 金信上限数量4
	private Integer goldCredit5; // 金信上限数量5
	private Integer goldCreditResidual; // 金信剩余数量
	/**
	 * P2P上限数量1
	 */
	private Integer p2p1;
	/**
	 * P2P上限数量2
	 */
	private Integer p2p2;
	/**
	 * P2P上限数量3
	 */
	private Integer p2p3;
	/**
	 * P2P上限数量4
	 */
	private Integer p2p4;
	/**
	 * P2P上限数量5
	 */
	private Integer p2p5;
	/**
	 * P2P剩余数量
	 */
	private Integer p2pResidual;
	/**
	 * 资产家上限数量1
	 */
	private Integer zcj1;
	/**
	 * 资产家上限数量2
	 */
	private Integer zcj2;
	/**
	 * 资产家上限数量3
	 */
	private Integer zcj3;
	/**
	 * 资产家上限数量4
	 */
	private Integer zcj4;
	/**
	 * 资产家上限数量5
	 */
	private Integer zcj5;
	/**
	 * 资产家剩余数量
	 */
	private Integer zcjResidual;
	private int usingFlag; // 启用标识
	private int version; // 版本
	private Integer posit; // 位置
	private Integer positNum; // 位置数量
	private Integer positXyj; // 信易借位置
	private Integer positNumXyj; // 信易借位置数量

	public Integer getChp1() {
		return chp1;
	}

	public void setChp1(Integer chp1) {
		this.chp1 = chp1;
	}

	public Integer getChp2() {
		return chp2;
	}

	public void setChp2(Integer chp2) {
		this.chp2 = chp2;
	}

	public Integer getChp3() {
		return chp3;
	}

	public void setChp3(Integer chp3) {
		this.chp3 = chp3;
	}

	public Integer getChp4() {
		return chp4;
	}

	public void setChp4(Integer chp4) {
		this.chp4 = chp4;
	}

	public Integer getChp5() {
		return chp5;
	}

	public void setChp5(Integer chp5) {
		this.chp5 = chp5;
	}

	public Integer getChpResidual() {
		return chpResidual;
	}

	public void setChpResidual(Integer chpResidual) {
		this.chpResidual = chpResidual;
	}

	public Integer getGoldCredit1() {
		return goldCredit1;
	}

	public void setGoldCredit1(Integer goldCredit1) {
		this.goldCredit1 = goldCredit1;
	}

	public Integer getGoldCredit2() {
		return goldCredit2;
	}

	public void setGoldCredit2(Integer goldCredit2) {
		this.goldCredit2 = goldCredit2;
	}

	public Integer getGoldCredit3() {
		return goldCredit3;
	}

	public void setGoldCredit3(Integer goldCredit3) {
		this.goldCredit3 = goldCredit3;
	}

	public Integer getGoldCredit4() {
		return goldCredit4;
	}

	public void setGoldCredit4(Integer goldCredit4) {
		this.goldCredit4 = goldCredit4;
	}

	public Integer getGoldCredit5() {
		return goldCredit5;
	}

	public void setGoldCredit5(Integer goldCredit5) {
		this.goldCredit5 = goldCredit5;
	}

	public Integer getGoldCreditResidual() {
		return goldCreditResidual;
	}

	public void setGoldCreditResidual(Integer goldCreditResidual) {
		this.goldCreditResidual = goldCreditResidual;
	}

	/**
	 * P2P上限数量1
	 */
	public Integer getP2p1() {
		return p2p1;
	}

	/**
	 * P2P上限数量1
	 */
	public void setP2p1(Integer p2p1) {
		this.p2p1 = p2p1;
	}

	/**
	 * P2P上限数量2
	 */
	public Integer getP2p2() {
		return p2p2;
	}

	/**
	 * P2P上限数量2
	 */
	public void setP2p2(Integer p2p2) {
		this.p2p2 = p2p2;
	}

	/**
	 * P2P上限数量3
	 */
	public Integer getP2p3() {
		return p2p3;
	}

	/**
	 * P2P上限数量3
	 */
	public void setP2p3(Integer p2p3) {
		this.p2p3 = p2p3;
	}

	/**
	 * P2P上限数量4
	 */
	public Integer getP2p4() {
		return p2p4;
	}

	/**
	 * P2P上限数量4
	 */
	public void setP2p4(Integer p2p4) {
		this.p2p4 = p2p4;
	}

	/**
	 * P2P上限数量5
	 */
	public Integer getP2p5() {
		return p2p5;
	}

	/**
	 * P2P上限数量5
	 */
	public void setP2p5(Integer p2p5) {
		this.p2p5 = p2p5;
	}

	/**
	 * P2P剩余数量
	 */
	public Integer getP2pResidual() {
		return p2pResidual;
	}

	/**
	 * P2P剩余数量
	 */
	public void setP2pResidual(Integer p2pResidual) {
		this.p2pResidual = p2pResidual;
	}

	/**
	 * 资产家上限数量1
	 */
	public Integer getZcj1() {
		return zcj1;
	}

	/**
	 * 资产家上限数量1
	 */
	public void setZcj1(Integer zcj1) {
		this.zcj1 = zcj1;
	}

	/**
	 * 资产家上限数量2
	 */
	public Integer getZcj2() {
		return zcj2;
	}

	/**
	 * 资产家上限数量2
	 */
	public void setZcj2(Integer zcj2) {
		this.zcj2 = zcj2;
	}

	/**
	 * 资产家上限数量3
	 */
	public Integer getZcj3() {
		return zcj3;
	}

	/**
	 * 资产家上限数量3
	 */
	public void setZcj3(Integer zcj3) {
		this.zcj3 = zcj3;
	}

	/**
	 * 资产家上限数量4
	 */
	public Integer getZcj4() {
		return zcj4;
	}

	/**
	 * 资产家上限数量4
	 */
	public void setZcj4(Integer zcj4) {
		this.zcj4 = zcj4;
	}

	/**
	 * 资产家上限数量5
	 */
	public Integer getZcj5() {
		return zcj5;
	}

	/**
	 * 资产家上限数量5
	 */
	public void setZcj5(Integer zcj5) {
		this.zcj5 = zcj5;
	}

	/**
	 * 资产家剩余数量
	 */
	public Integer getZcjResidual() {
		return zcjResidual;
	}

	/**
	 * 资产家剩余数量
	 */
	public void setZcjResidual(Integer zcjResidual) {
		this.zcjResidual = zcjResidual;
	}

	public int getUsingFlag() {
		return usingFlag;
	}

	public void setUsingFlag(int usingFlag) {
		this.usingFlag = usingFlag;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Integer getPosit() {
		return posit;
	}

	public void setPosit(Integer posit) {
		this.posit = posit;
	}

	public Integer getPositNum() {
		return positNum;
	}

	public void setPositNum(Integer positNum) {
		this.positNum = positNum;
	}

	public Integer getPositXyj() {
		return positXyj;
	}

	public void setPositXyj(Integer positXyj) {
		this.positXyj = positXyj;
	}

	public Integer getPositNumXyj() {
		return positNumXyj;
	}

	public void setPositNumXyj(Integer positNumXyj) {
		this.positNumXyj = positNumXyj;
	}

}
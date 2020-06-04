package com.creditharmony.approve.management.util;

import java.util.Comparator;

import com.creditharmony.approve.management.entity.Negotiation;

/**
 * 类比较器(协商码对比)
 * @Class Name RefuseComparator
 * @author 李文勇
 * @Create In 2016年5月10日
 */
public class GlNegotiationComparator implements Comparator<Negotiation> {

	/**
	 * 按照协商码码值对比
	 */
	@Override
	public int compare(Negotiation d1, Negotiation d2) {
		String code1 = d1.getNegotiationCode();
		String code2 = d2.getNegotiationCode();
		return code1.compareTo(code2);
	}
	
}

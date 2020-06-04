package com.creditharmony.approve.management.util;

import java.util.Comparator;

import com.creditharmony.approve.management.entity.GlGiveup;

/**
 * 类比较器（放弃码对比）
 * @Class Name RefuseComparator
 * @author 李文勇
 * @Create In 2016年5月10日
 */
public class GlGiveupComparator implements Comparator<GlGiveup> {
	
	/**
	 * 按照放弃码码值对比
	 */
	@Override
	public int compare(GlGiveup d1, GlGiveup d2) {
		String code1 = d1.getGiveupCode();
		String code2 = d2.getGiveupCode();
		return code1.compareTo(code2);
	}
	
}

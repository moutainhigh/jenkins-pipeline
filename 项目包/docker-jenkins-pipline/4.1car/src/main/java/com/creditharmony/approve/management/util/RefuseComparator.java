package com.creditharmony.approve.management.util;

import java.util.Comparator;

import com.creditharmony.approve.management.entity.GlRefuse;

/**
 * 类比较器(比较拒借码)
 * @Class Name RefuseComparator
 * @author 李文勇
 * @Create In 2016年5月10日
 */
public class RefuseComparator implements Comparator<GlRefuse> {

	/**
	 * 按照拒绝码码值对比
	 */
	@Override
	public int compare(GlRefuse d1, GlRefuse d2) {
		String code1 = d1.getRefuseCode();
		String code2 = d2.getRefuseCode();
		return code1.compareTo(code2);
	}
	
}

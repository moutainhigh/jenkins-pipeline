package com.creditharmony.approve.common.constants;
/**
 * 标记表的来源
 * @Class Name TableType
 * @author 刘燕军
 * @Create In 2016年5月20日
 */
public interface TableType {
	
	/**
	 * 本人核实表
	 */
	public static final String MYSELF="0";
	
	/**
	 * 联系人表
	 */
	public static final String CONTACT="1";
	
	/**
	 * 单位名称表
	 */
	public static final String WORK_NAME="2";
	
	/**
	 * 单位电话表
	 */
	public static final String WORK_TEL="3";
	
	/**
	 * 4联系人电话信息表（家庭、固话）
	 */
	public static final String CONTACT_TEL="4";
	
	
	
	/**
	 * 表类型对应的key值
	 */
	public static final String TABLE_TYPE="tableType";
	
	/**
	 * 需要查询的字段对应的key值
	 */
	public static final String REPEATE_CONTENT="repeateContent";
	
	/**
	 * 需要查询的字段对应ID的key值
	 */
	public static final String ID="id";
}

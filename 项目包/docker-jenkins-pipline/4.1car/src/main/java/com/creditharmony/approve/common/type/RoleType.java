package com.creditharmony.approve.common.type;

import java.util.HashMap;
import java.util.Map;

/**
 * 汇诚角色枚举
 * @Class Name RoleType
 * @author 王浩
 * @Create In 2016年5月6日
 */
public enum RoleType {

	APPROVE_VERIFY_MANAGER("6310000001","信审经理"),
	APPROVE_VERIFY_TEAM_LEADER("6310000002","信审组长"),
	APPROVE_VERIFY_CHECK("6310000003","信审初审"),
	APPROVE_VERIFY_RECHECK("6310000004","信审复审"),
	APPROVE_VERIFY_GROUP_CHECK("6310000005","信审终审"),
	APPROVE_VERIFY_FINAL_CHECK("6310000006","信审高级终审"),
	APPROVE_RECONSIDER_MANAGER("6310000007","复议经理"),
	APPROVE_RECONSIDER_TEAM_LEADER("6310000008","复议组长"),
	APPROVE_RECONSIDER_CHECK("6310000009","复议初审"),
	APPROVE_RECONSIDER_RECHECK("6310000010","复议复审"),
	APPROVE_RECONSIDER_FINAL_CHECK("6310000011","复议终审"),
	APPROVE_ANTIFRAUD_MANAGER("6310000012","反欺诈经理"),
	APPROVE_ANTIFRAUD_MEMBER("6310000013","反欺诈专员"),
	APPROVE_CODE_ADMIN("6310000014","码值管理员"),
	APPROVE_RULE_ADMIN("6310000015","规则管理员"),
	APPROVE_SYNTHETICAL_MEMBER("6310000016","综合内勤"),
	APPROVE_TRAIN_MANAGER("6310000017","培训经理"),
	APPROVE_DATA_VIEW_MEMBER("6310000018","数据查看专员"),
	APPROVE_CAR_RECHECK("6310000019","车借复审"),
	APPROVE_CAR_FINAL_CHECK("6310000020","车借终审");

	public String id;    //对应角色表中的id字段
	public String name;  //对应角色表中的enname字段

	private RoleType(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	private static Map<String, RoleType> idMap = new HashMap<String, RoleType>(
			100);
	private static Map<String, RoleType> nameMap = new HashMap<String, RoleType>(
			100);
	
	static {
		RoleType[] allValues = RoleType.values();
		for (RoleType obj : allValues) {
			idMap.put(obj.getId(), obj);
			nameMap.put(obj.getName(), obj);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static RoleType parseById(String id) {
		return idMap.get(id);
	}

	public static RoleType parseByName(String name) {
		return nameMap.get(name);
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}

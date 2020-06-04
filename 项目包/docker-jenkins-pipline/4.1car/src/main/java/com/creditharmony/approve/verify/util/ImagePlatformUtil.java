package com.creditharmony.approve.verify.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.type.RoleType;
import com.creditharmony.approve.verify.view.DiskInfoView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.approve.type.CheckType;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.config.Global;
import com.creditharmony.core.users.dao.RoleDao;
import com.creditharmony.core.users.dao.UserRoleOrgDao;
import com.creditharmony.core.users.entity.Role;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.entity.UserRoleOrg;
import com.creditharmony.core.users.util.UserUtils;
import com.google.common.collect.Maps;

/**
 * 
 * @Class Name ImagePlatformUtil  根据不同角色的信审用户  对影像平台插件进行查看 下载  上传不同的权限
 * @author 李静辉
 * @Create In 2016年1月4日
 */
@Service
public class ImagePlatformUtil {
	
	// 信雅达插件校验的标识
	private static String VERIFY_PARAM_MY = "ZXYGXFZIYEEX";
	
	/**
	 * 2016年1月4日
	 * By 李静辉
	 * @param userType 用户角色  初审  复审   终审  复议用户角色
	 * @param chp3_0XYD_info 配置文件中的chp3.0XYD.info信息
	 * @param loanApplyId 借款编号
	 * @param hjDiskName 汇金影像地址
	 * @param hcDiskName 信审影像地址
	 * @param fyDiskName 复议影像地址
	 * @param queryTime 借款进件时间
	 * @param readOnly
	 * @return 
	 */
	public static String appendUrlParm(String userType, String chp3_0XYD_info, String loanApplyId, DiskInfoView diskInfoView, String queryTime, String readOnly) {	
		StringBuffer url = new StringBuffer("&");		
		
		if (StringUtils.isNotEmpty(diskInfoView.getLoanIndex()) && StringUtils.isNotEmpty(diskInfoView.getVerifyIndex())) {
			if(CheckType.XS_FIRST_CREDIT_AUDIT.getName().equals(userType)) {
				// 信审初审对汇金文件夹具有打印权限
				url.append(replaceUrlPram(chp3_0XYD_info,"info1",loanApplyId,diskInfoView.getLoanIndex(),queryTime,diskInfoView.getVerifyFilelevel(),"0100100")).append("&");
				// 信审初审文件夹，读写删
				if (StringUtils.isNotEmpty(readOnly) && readOnly.equals(BooleanType.TRUE)) {
					url.append(replaceUrlPram(chp3_0XYD_info,"info2",loanApplyId,diskInfoView.getVerifyIndex(),queryTime,"1,2","0100000"));
				}else{
					url.append(replaceUrlPram(chp3_0XYD_info,"info2",loanApplyId,diskInfoView.getVerifyIndex(),queryTime,"1,2","1110000"));
				}
			} else if(CheckType.FY_FIRST_CREDIT_AUDIT.getName().equals(userType)) {
				// 复议能查看复议补充资料
				url.append(replaceUrlPram(chp3_0XYD_info,"info1",loanApplyId,diskInfoView.getLoanIndex(),queryTime,diskInfoView.getReconsiderFilelevel(),"0100000")).append("&");		
				url.append(replaceUrlPram(chp3_0XYD_info,"info2",loanApplyId,diskInfoView.getVerifyIndex(),queryTime,"1,2","0100000")).append("&");
				// 复议初审文件夹，读写删
				if(StringUtils.isNotEmpty(diskInfoView.getReconsiderIndex())) {
					if (StringUtils.isNotEmpty(readOnly) && readOnly.equals(BooleanType.TRUE)) {
						url.append(replaceUrlPram(chp3_0XYD_info,"info3",loanApplyId,diskInfoView.getReconsiderIndex(),queryTime,"1","0100000"));
					}else{
						url.append(replaceUrlPram(chp3_0XYD_info,"info3",loanApplyId,diskInfoView.getReconsiderIndex(),queryTime,"1","1110000"));
					}
				}				
			} else if (CheckType.FY_SECOND_CREDIT_AUDIT.getName().equals(userType)
				|| CheckType.FY_FINAL_CREDIT_AUDIT.getName().equals(userType)) { 
				// 复议角色，只读权限
				url.append(replaceUrlPram(chp3_0XYD_info,"info1",loanApplyId,diskInfoView.getLoanIndex(),queryTime,diskInfoView.getReconsiderFilelevel(),"0100000")).append("&");			
				url.append(replaceUrlPram(chp3_0XYD_info,"info2",loanApplyId,diskInfoView.getVerifyIndex(),queryTime,"1,2","0100000")).append("&");	
				if(StringUtils.isNotEmpty(diskInfoView.getReconsiderIndex())) {
					url.append(replaceUrlPram(chp3_0XYD_info,"info3",loanApplyId,diskInfoView.getReconsiderIndex(),queryTime,"1","0100000"));
				}				
			} else {
				// stepName为信审复审、终审、高级终审、组长、经理；反欺诈专员、反欺诈经理、null，文件夹权限与信审复审相同，只读权限
				url.append(replaceUrlPram(chp3_0XYD_info, "info1", loanApplyId, diskInfoView.getLoanIndex(), queryTime, diskInfoView.getVerifyFilelevel(), "0100000")).append("&");
				url.append(replaceUrlPram(chp3_0XYD_info, "info2", loanApplyId, diskInfoView.getVerifyIndex(), queryTime, "1,2", "0100000"));
			}
		} 
		String finalUrl = url.toString();
		// 如果只读，将读写删权限替换为读权限
//		if (StringUtils.isNotEmpty(readOnly) && readOnly.equals(BooleanType.TRUE)) {
//			finalUrl = finalUrl.replaceAll("1110000", "0100000");			
//		}
		
		return Global.getConfig(ApproveConstants.CHP3_0XYD) + finalUrl + "&MY=" + VERIFY_PARAM_MY;
	}
	
	/**
	 * 
	 * 2016年1月4日
	 * By 李静辉
	 * @param url
	 * @param infoNum
	 * @param busiSerialNo
	 * @param objectName
	 * @param queryTime
	 * @param filelevel
	 * @param right
	 * @return
	 */
	public static  String replaceUrlPram(String url,String infoNum,String busiSerialNo,String diskName,
			String queryTime,String filelevel,String right){
		Object []fmtargs = {infoNum, busiSerialNo,diskName, queryTime ,filelevel,right}; 		
		url =  MessageFormat.format(url, fmtargs);		
		return url;
	}
	
	/**
	 * 其他人员的待办，已办，更新只读标识与stepName以控制信雅达插件权限
	 * 2016年5月6日
	 * By 王浩
	 * @param stepName 步骤名如信审初审等
	 * @param user
	 * @return 
	 */
	public static Map<String, String> getStepAndReadOnly(String stepName) {
		// 当前登录用户
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		
		UserRoleOrgDao userRoleOrgDao = SpringContextHolder.getBean(UserRoleOrgDao.class);
		RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", user.getId());
		params.put("orgId", user.getDepartment().getId());
		// 当前用户在当前部门下的角色列表
		List<UserRoleOrg> userRoleList = userRoleOrgDao.query(params);
		
		Map<String, String> stepRead = Maps.newHashMap();
		// stepName为空，限定只读
		String readOnly = StringUtils.isNotEmpty(stepName) ? null : BooleanType.TRUE;
		
		if (ArrayHelper.isNotEmpty(userRoleList)) {
			Role role = roleDao.get(userRoleList.get(0).getRoleId());
			String roleName = null;
			if (role != null) {
				roleName = role.getName();
			} else {
				roleName = user.getRoleList().get(0).getName();
			}
			
			if (!roleName.equals(stepName)) {
				// 如果用户角色与stepName不一致，设置为只读;
				readOnly = BooleanType.TRUE;
				// 如果用户角色为复议组长或者复议经理，设置为复议复审								
				if (roleName.equals(RoleType.APPROVE_RECONSIDER_MANAGER.getName())
						|| roleName.equals(RoleType.APPROVE_RECONSIDER_TEAM_LEADER.getName())) {
					stepName = RoleType.APPROVE_RECONSIDER_RECHECK.getName();
				}			
			} 

			if (!StringUtils.isNotEmpty(stepName) && CheckType.parseByName(roleName) != null) {
				// stepName为空，如果用户为审批角色，为stepName赋值
				stepName = roleName;				
			}
		}		
		stepRead.put("stepName", stepName);
		stepRead.put("readOnly", readOnly);
		return stepRead;
	}
	
}

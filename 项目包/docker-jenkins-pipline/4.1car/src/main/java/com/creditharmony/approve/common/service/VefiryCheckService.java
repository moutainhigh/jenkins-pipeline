package com.creditharmony.approve.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.common.constants.FilterCompanyNameConstant;
import com.creditharmony.approve.common.constants.TableType;
import com.creditharmony.approve.common.entity.RepeateParam;
import com.creditharmony.approve.localnet.dao.VerifyRepeateDao;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.approve.type.TelSrc;
import com.creditharmony.core.users.util.UserUtils;

/**
 * 添加电话等信息查重
 * 删除对应的查重信息
 * @Class Name VefiryCheckService
 * @author 刘燕军
 * @Create In 2016年5月21日
 */

@Service
public class VefiryCheckService extends BaseService{
	
	@Autowired
	private VerifyRepeateDao verifyRepeateDao;
	
	/**
	 * 单位名称或号码查重
	 * 2016年5月20日
	 * By 刘燕军
	 * @param map
	 * @return 是否有查重
	 */
	public boolean isRepeate(Map<String, String> map){
		int flag = 0;
		int returnFlag = 0;
		RepeateParam paramMap = verifyRepeateDao.getParam(map); // 获取查重需要的所有的参数
		if(!TableType.WORK_NAME.equals(map.get(TableType.TABLE_TYPE))){ // 如果是电话号码。则需要判断是否为无效号码
			flag = verifyRepeateDao.getPhoneUse(paramMap.getRepeateContent());
		}
		paramMap.setUser(UserUtils.getUser().getId());
		// 非无效号码，则开始其他操作
		if(!TableType.WORK_NAME.equals(map.get(TableType.TABLE_TYPE)) && flag==0 ){
			if(paramMap != null){
				returnFlag = verifyRepeateDao.repeateCheck(paramMap);
			}
		}else if(TableType.WORK_NAME.equals(map.get(TableType.TABLE_TYPE)) ){ // 如果是单位名称，查重
			// 把单位名称过滤下
			paramMap.setRepeateContent(filterCompanyName(paramMap.getRepeateContent()));
			returnFlag = verifyRepeateDao.repeateCheckName(paramMap);
		}
		if(returnFlag >0){ // 如果大于0，则有查重信息
			return true;
		}
		return false;
	}
	
	private String filterCompanyName(String compantName){
		for(String str : FilterCompanyNameConstant.NAME_FILTER){
			compantName = compantName.replaceAll(str, "");
		}
		return compantName;
	}
	
	/**
	 * 根据参数删除对应的查重信息
	 * 2016年5月21日
	 * By 刘燕军
	 * @param map
	 */
	public boolean deleteRepeate(Map<String, String> map){
		int result = 0;
		RepeateParam paramMap = verifyRepeateDao.getParam(map); // 获取查重需要的所有的参数
		if(paramMap != null){ // 如果参数不为空，则删除对应的查重信息
			// 号码来源申请人提供的,执行删除查重
			if(TelSrc.TEL_SOURCE_APPLICATION_FORM.getCode().equals(paramMap.getSrc())){
				result = verifyRepeateDao.deleteRepeate(paramMap);
			}
		}
		if(result>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据参数删除对应的所有查重信息
	 * 2016年5月21日
	 * By 刘燕军
	 * @param map
	 */
	public boolean deleteRepeates(Map<String, String> map){
		int result = 0;
		List<RepeateParam> paramMap = verifyRepeateDao.getParamList(map); // 获取查重需要的所有的参数
		if(ArrayHelper.isNotEmpty(paramMap)){// 如果list不为空
			for (RepeateParam map2 : paramMap) {
				if(map2 != null){ // 如果参数不为空，则删除对应的查重信息
					// 如果是单位名称或者单位电话
					if(TableType.WORK_NAME.equals(map.get(TableType.TABLE_TYPE)) || TableType.WORK_TEL.equals(map.get(TableType.TABLE_TYPE))){
						// 此时需要判断 来源
						if(TelSrc.TEL_SOURCE_APPLICATION_FORM.getCode().equals(map2.getSrc())){
							int i = verifyRepeateDao.deleteRepeate(map2);
							result += i;
						}
					}else{// 其他的不需要判断来源
						int i = verifyRepeateDao.deleteRepeate(map2);
						result += i;
					}
				}
			}
		}
		if(result>0){
			return true;
		}
		return false;
	}
}

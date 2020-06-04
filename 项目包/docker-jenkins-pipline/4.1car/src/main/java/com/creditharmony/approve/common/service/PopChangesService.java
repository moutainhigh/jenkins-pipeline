package com.creditharmony.approve.common.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.common.dao.ChangerInfoDao;
import com.creditharmony.approve.common.entity.ChangerInfo;
import com.creditharmony.approve.verify.constants.ApplicationConstants;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.VerifyCommonService;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.cache.ProvinceCityCache;
import com.creditharmony.core.loan.type.ReturnChangeResult;

/**
 * 汇金修改历史弹出画面
 * @Class Name PopChangesService
 * @author 李文勇
 * @Create In 2015年12月2日
 */
@Service
public class PopChangesService {

	@Autowired
	private ChangerInfoDao changerInfoDao;
	@Autowired
	private VerifyCommonService verifyCommonService;
	
	/**
	 * 获取历史列表信息
	 * 2015年12月2日
	 * By 李文勇
	 * @param loanCode
	 * @return
	 */
	public List<ChangerInfo> showChangerInfo(VerifyParamEx verifyParamEx){
		ChangerInfo changerInfo = new ChangerInfo();
		if (verifyParamEx != null) {
			changerInfo.setChangeCode(verifyParamEx.getLoanCode());
			changerInfo.setApplyId(verifyParamEx.getApplyId());
		}
		List<ChangerInfo> infoList = changerInfoDao.getChangerInfoByParam(changerInfo);
//		String bigRelation = "";
		if (ArrayHelper.isNotEmpty(infoList)) {
			DictCache dictCache = DictCache.getInstance();
			for (ChangerInfo info : infoList) {
				// 修改数据类型
				String returnChangeCode = info.getChangeResult();
				// 将修改数据类型代码转换为汉字
				info.setChangeResult(ReturnChangeResult.parseByCode(returnChangeCode).getName());
				// 家庭地址、单位地址
				if (returnChangeCode.equals(ReturnChangeResult.HOME_ADDRESS.getCode())
						||returnChangeCode.equals(ReturnChangeResult.WORK_ADDRESS.getCode())) {
					info.setChangeBegin(getAddress(info.getChangeBegin()));
					info.setChangeAfter(getAddress(info.getChangeAfter()));
				}
				// 学历
				if (returnChangeCode.equals(ReturnChangeResult.DEGREE.getCode())) {
					info.setChangeBegin(dictCache.getDictLabel(DictionaryConstants.DEGREE, info.getChangeBegin()));
					info.setChangeAfter(dictCache.getDictLabel(DictionaryConstants.DEGREE, info.getChangeAfter()));
				}
				// 婚姻状况
				if (returnChangeCode.equals(ReturnChangeResult.MARRIED.getCode())) {
					info.setChangeBegin(dictCache.getDictLabel(DictionaryConstants.MARRIAGE, info.getChangeBegin()));
					info.setChangeAfter(dictCache.getDictLabel(DictionaryConstants.MARRIAGE, info.getChangeAfter()));
				}
				// 住房性质
				if (returnChangeCode.equals(ReturnChangeResult.HOUSE_PORI.getCode())) {
					info.setChangeBegin(dictCache.getDictLabel(DictionaryConstants.HOUSE_NATURE, info.getChangeBegin()));
					info.setChangeAfter(dictCache.getDictLabel(DictionaryConstants.HOUSE_NATURE, info.getChangeAfter()));
				}
				// 大关系类型
				if (returnChangeCode.equals(ReturnChangeResult.CONTACT_TYPE_MAIN.getCode())) {
					// 大关系类型
//					bigRelation = returnChangeCode;
					info.setChangeBegin(dictCache.getDictLabel(DictionaryConstants.RELATION_TYPE, info.getChangeBegin()));
					info.setChangeAfter(dictCache.getDictLabel(DictionaryConstants.RELATION_TYPE, info.getChangeAfter()));					
				}
				// 删除共借人类型 新版显示删除保证人，旧版显示删除共借人
				if (returnChangeCode.equals(ReturnChangeResult.DEL_COBORROWER.getCode())) {
					// 获取新版旧版标识 新版删除共借人显示为删除保证人，旧版不变
					if (info.getChangeCode() !=null) {
						String newOrOldFlag = verifyCommonService.getOldornewFlag(info.getChangeCode());
						if (newOrOldFlag.equals(ApplicationConstants.LOANINFO_NEW_FLAG)) {
							info.setChangeResult("删除保证人");
						}
					}					
				}				
				// 与本人关系变更，暂时不作处理
//				if (returnChangeCode.equals(ReturnChangeResult.CONTACT_TRELATION_MAIN.getCode())) {
//					// 与本人关系
//					String relation = "";
//					// 大关系是家庭联系人
//					if (bigRelation.equals(ApproveConstants.RELATION_TYPE_FAMILY)) {
//						relation = DictionaryConstants.LOAN_FAMILY_RELATION;
//					} else if (bigRelation.equals(ApproveConstants.RELATION_TYPE_WORKMATE)) {
//						// 大关系是工作证明人
//						relation = DictionaryConstants.LOAN_WORKMATE_RELATION;
//					} else if (bigRelation.equals(ApproveConstants.RELATION_TYPE_OTHER)) {
//						// 大关系是其他联系人
//						relation = DictionaryConstants.LOAN_OTHER_RELATION_TYPE;
//					}
//					if (StringUtils.isNotEmpty(relation)) {
//						info.setChangeBegin(dictCache.getDictLabel(relation, info.getChangeBegin()));
//						info.setChangeAfter(dictCache.getDictLabel(relation, info.getChangeAfter()));
//					}					
//				}				
			}
		}
		return infoList;
	}	
    
    /**
     * 根据省市区代码及详细地址，转换为省市区汉字+详细地址
     * 2016年6月25日
     * By 王浩
     * @param str
     * @return 
     */
    private String getAddress(String str) {
    	// 空直接返回
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		String[] strs = str.split(",");
		if (strs.length == 4) {
			return ProvinceCityCache.getInstance().getProvinceCity(strs[0], strs[1], strs[2]) + strs[3];
		}
    	return "";
    }

}

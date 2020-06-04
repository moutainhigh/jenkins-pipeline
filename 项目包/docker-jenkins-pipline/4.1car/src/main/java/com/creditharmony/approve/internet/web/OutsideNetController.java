package com.creditharmony.approve.internet.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.common.util.CallPhoneUtil;
import com.creditharmony.approve.internet.entity.ex.OutSideCheckConParamEx;
import com.creditharmony.approve.internet.entity.ex.OutSideCheckEx;
import com.creditharmony.approve.internet.entity.ex.OutsideNetCheckEx;
import com.creditharmony.approve.internet.entity.ex.OutsideNetInfoEx;
import com.creditharmony.approve.internet.service.OutsideNetService;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;
import com.creditharmony.approve.phone.entity.TelAuditWork;
import com.creditharmony.approve.phone.entity.WorkTelNum;
import com.creditharmony.approve.phone.entity.ex.TelAuditWorkEx;
import com.creditharmony.approve.verify.entity.ex.RecordListEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.entity.ex.WorkTelNumParamEx;
import com.creditharmony.approve.verify.service.VerifyCommonService;
import com.creditharmony.approve.verify.util.DictToMapUtil;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.lend.type.LendConstants;
import com.creditharmony.core.loan.type.YESNO;
import com.creditharmony.core.web.BaseController;
import com.google.common.collect.Maps;

/**
 * 外网审核
 * 
 * @Class Name OutsideNetController
 * @author 刘燕军
 * @Create In 2016年1月6日
 * @update in 2016-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/verify/outside")
public class OutsideNetController extends BaseController {

	@Autowired
	private OutsideNetService outsideNetService;
	@Autowired
	private VerifyCommonService verifyCommonService;

	/**
	 * 外网审核初始化(单位名称、电话信息) 2016年1月6日 By 刘燕军
	 * 
	 * @param model
	 * @param param
	 * @return String
	 */
	@RequestMapping(value = "getList")
	public String getList(Model model, VerifyParamEx param) {
		model.addAttribute("param", param);
		// 新版申请表与旧版申请表区分标识
		String flag = verifyCommonService.getOldornewFlag(param.getLoanCode().trim());
		if ("0".equals(flag)) {
			OutSideCheckEx checkView = outsideNetService.getOutsideCheckView(param);
			// 把查询结果放入model中去
			model.addAttribute("out", checkView);
			Map<String, String> map = DictToMapUtil.getMap(DictionaryConstants.TEL_SOURCE);
			Map<String, String> cardType = DictToMapUtil.getMap(DictionaryConstants.CERTIFICATE_TYPE);
			model.addAttribute("cardType", cardType);
			model.addAttribute("telSource", map);
			model.addAttribute("applyNewOrOld", flag);
			return "/internet/internetForm";
		} else {
			OutSideCheckEx checkView = outsideNetService.getOutsideCheckViewNew(param);
			// 把查询结果放入model中去
			model.addAttribute("out", checkView);
			Map<String, String> map = DictToMapUtil.getMap(DictionaryConstants.TEL_SOURCE);
			model.addAttribute("telSource", map);
			model.addAttribute("applyNewOrOld", flag);
			return "/internet/internetForm_new";
		}
	}

	/**
	 * 获取替换文本 2016年4月16日 xiaoniu.hu
	 * 
	 * @param model
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "getReplaceInfo")
	public String getReplaceInfo(Model model, VerifyParamEx param) {
		// 新版申请表与旧版申请表区分标识
		String flag = verifyCommonService.getOldornewFlag(param.getLoanCode().trim());
		model.addAttribute("param", param);
		if ("0".equals(flag)) {
			OutSideCheckEx checkView = outsideNetService.getOutsideCheckView(param);
			// 把查询结果放入model中去
			model.addAttribute("out", checkView);
			Map<String, String> map = DictToMapUtil.getMap(DictionaryConstants.TEL_SOURCE);
			Map<String, String> cardType = DictToMapUtil.getMap(DictionaryConstants.CERTIFICATE_TYPE);
			model.addAttribute("cardType", cardType);
			model.addAttribute("telSource", map);
			model.addAttribute("applyNewOrOld", flag);
			return "/internet/internetReplaceForm";
		} else {
			OutSideCheckEx checkView = outsideNetService.getOutsideCheckView(param);
			// 把查询结果放入model中去
			model.addAttribute("out", checkView);
			Map<String, String> map = DictToMapUtil.getMap(DictionaryConstants.TEL_SOURCE);
			model.addAttribute("telSource", map);
			model.addAttribute("applyNewOrOld", flag);
			return "/internet/internetReplaceForm_new";
		}
	}

	/**
	 * 外网审核查看初始化 2016年1月6日 By 刘燕军
	 * 
	 * @param model
	 * @param param
	 * @return String
	 */
	@RequestMapping(value = "getView")
	public String getView(Model model, VerifyParamEx param) {
		// 新版申请表与旧版申请表区分标识
		String flag = verifyCommonService.getOldornewFlag(param.getLoanCode().trim());
		if ("0".equals(flag)) {
			OutSideCheckEx checkView = outsideNetService.getOutsideCheckView(param);
			// 把查询结果放入model中去
			model.addAttribute("out", checkView);
			// 核查结果对应字典
			model.addAttribute("map", DictToMapUtil.getMap(LendConstants.EVAL_RESULT));
			Map<String, String> cardType = DictToMapUtil.getMap(DictionaryConstants.CERTIFICATE_TYPE);
			model.addAttribute("cardType", cardType);
			return "/internet/internetView";
		} else {
			OutSideCheckEx checkView = outsideNetService.getOutsideCheckViewNew(param);
			// 把查询结果放入model中去
			model.addAttribute("out", checkView);
			// 核查结果对应字典
			model.addAttribute("map", DictToMapUtil.getMap(LendConstants.EVAL_RESULT));
			return "/internet/internetView_new";
		}
	}

	/**
	 * 网查信息保存 2016年2月4日 By 刘燕军
	 * 
	 * @param model
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "asynSaveNet")
	public String asynSaveNet(OutsideNetInfoEx netInfo, OutsideNetCheckEx outside, WorkTelNumParamEx workNum,
			RecordListEx records, OutSideCheckConParamEx outSideCheckConParamEx, String applyNewOrOld) {
		if (ArrayHelper.isNotEmpty(netInfo.getNetWorks()) || StringUtils.isNotEmpty(outside.getId())) {
			try {
				if ("0".equals(applyNewOrOld)) {
					outsideNetService.saveNet(netInfo, outside, workNum, records);
				} else {
					outsideNetService.saveNetNew(netInfo, outside, workNum, records, outSideCheckConParamEx);
				}
			} catch (Exception e) {
				logger.error(e.getMessage() + "网查信息保存异常");
				return BooleanType.FALSE;
			}
			return BooleanType.TRUE;
		} else {
			return BooleanType.FALSE;
		}
	}

	/**
	 * 单位信息删除 2015年12月21日 By 刘燕军
	 * 
	 * @param id
	 *            单位id
	 * @return String 是否删除成功
	 */
	@ResponseBody
	@RequestMapping(value = "asynDelete")
	public String asynDelete(String id, String workName) {
		try {
			if (StringUtils.isNotEmpty(id)) {
				boolean flag = outsideNetService.asynDelete(id, workName);
				if (flag) {
					return "repeate";
				}
				return BooleanType.TRUE;
			} else {
				return BooleanType.FALSE;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
	}

	/**
	 * 单位信息 电话信息保存 2015年12月21日 By 刘燕军
	 * 
	 * @param work
	 * @param workNum
	 * @param param
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "asynSaveWorkInfo")
	public String asynSaveWorkInfo(TelAuditWork work, WorkTelNum workNum, VerifyParamEx param) {
		try {
			if (work != null && param != null && !StringUtils.isBlank(work.getWorkUnitname())) {
				String repeate = outsideNetService.asynSaveWorkInfo(work, workNum, param);
				if (BooleanType.TRUE.equals(repeate)) {
					return "repeate";
				}
				return BooleanType.TRUE;
			} else {
				return BooleanType.FALSE;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
	}

	/**
	 * 电话信息保存 2015年12月21日 By 刘燕军
	 * 
	 * @param workNum
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "asynSavePhoneInfo")
	public String asynSavePhoneInfo(WorkTelNum workNum) {
		try {
			if (!StringUtils.isBlank(workNum.getWorkId())) {
				WorkTelNum workNumThis = outsideNetService.asynSavePhoneInfo(workNum);
				return jsonMapper.toJson(workNumThis);
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 所有的电话信息更新 2015年12月21日 By 刘燕军
	 * 
	 * @param workNu
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "asynUpdatePhoneInfo")
	public String asynUpdatePhoneInfo(WorkTelNum workNu) {
		try {
			if (!StringUtils.isBlank(workNu.getId())) {
				outsideNetService.asynUpdatePhoneInfo(workNu);
				return BooleanType.TRUE;
			} else {
				return BooleanType.FALSE;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
	}

	/**
	 * 电话信息删除 2015年12月21日 By 刘燕军
	 * 
	 * @param workNum
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "asynDelPhoneInfo")
	public String asynDelPhoneInfo(String id) {
		try {
			if (!StringUtils.isBlank(id)) {
				boolean flag = outsideNetService.asynDelPhoneInfo(id);
				if (flag) {
					return "repeate";
				}
				return BooleanType.TRUE;
			} else {
				return BooleanType.FALSE;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
	}

	/**
	 * 单位名称更新 2015年12月21日 By 刘燕军
	 * 
	 * @param workNum
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "asynUpdateWork")
	public String asynUpdateWork(TelAuditWorkEx telAuditWork) {
		try {
			if (!StringUtils.isBlank(telAuditWork.getId())) {
				telAuditWork.setIsRepeat(YESNO.NO.getCode());
				boolean falg = outsideNetService.asynUpdateWork(telAuditWork);
				if (falg) {
					return "repeate";
				}
			} else {
				return BooleanType.FALSE;
			}
			return BooleanType.TRUE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
	}

	/**
	 * 电话录音信息保存 2016年3月28日 By 刘燕军
	 * 
	 * @param dhly
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "asynSaveThis")
	public String asynSaveThis(DhzhDhlyxx dhly, HttpServletRequest request, String localTel) {
		try {
			String ip = getIpAddress(request);
			logger.error("ip=" + ip);
			outsideNetService.asynSaveThis(dhly, ip, localTel);
			return jsonMapper.toJson(dhly);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
	}

	/**
	 * 获取录音信息 2016年4月27日 By 刘燕军
	 * 
	 * @param callId
	 * @return 录音实体
	 */
	@ResponseBody
	@RequestMapping(value = "getRecord")
	public Map<String, String> getRecord(String callId) {
		logger.debug("开始获取电话录音");
		Map<String, String> resultMap = Maps.newHashMap();
		try {
			resultMap.put("url", CallPhoneUtil.getRecord(callId));
		} catch (Exception e) {
			logger.error("获取电话录音失败" + callId + e.getMessage());
		}
		return resultMap;
	}

}

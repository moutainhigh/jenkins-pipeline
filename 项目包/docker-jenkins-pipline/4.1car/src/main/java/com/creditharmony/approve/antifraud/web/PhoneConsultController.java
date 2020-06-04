package com.creditharmony.approve.antifraud.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.approve.antifraud.entity.ex.AntifraudTelEx;
import com.creditharmony.approve.antifraud.entity.ex.PhoneConfirmEx;
import com.creditharmony.approve.antifraud.entity.ex.PhoneListEx;
import com.creditharmony.approve.antifraud.entity.ex.PhoneWorkEx;
import com.creditharmony.approve.antifraud.entity.ex.PhoneWorkProvesEx;
import com.creditharmony.approve.antifraud.service.PhoneConsultService;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;
import com.creditharmony.approve.verify.constants.ApplicationConstants;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.VerifyCommonService;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.web.BaseController;

/**
 * 反欺诈-电话照会
 * @Class Name PhoneConsultController
 * @author 赖敏
 * @Create In 2015年11月25日
 */
@Controller
@RequestMapping(value = "${adminPath}/antifraud/phoneConsult")
public class PhoneConsultController extends BaseController {

	@Autowired
	private PhoneConsultService consultService;
	@Autowired
	private VerifyCommonService verifyCommonService;
	/**
	 * 到反欺诈电话照会【操作】页面
	 * 2016年3月26日
	 * By wanglidong
	 * @param model
	 * @param loanCode 借款编码
	 * @return 反欺诈电话照会办理页面
	 */
	@RequestMapping(value = "goForm")
	public String goForm(Model model, VerifyParamEx verifyParamEx,String loanCode,String editFlag) throws Exception{
		
		// 获取新版旧版标识
		String newOrOldFlag = verifyCommonService.getOldornewFlag(loanCode);
				
		// 反欺诈电话照会内容预处理，如果是第一次打开电话照会页签则初始化数据
		try {
			consultService.initTelCheck(loanCode);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		// 根据借款编号获取反欺诈电话照会内容
		// 判断为旧版
		if (newOrOldFlag.equals(ApplicationConstants.LOANINFO_OLD_FLAG)) {
			AntifraudTelEx checkView = consultService.getDhzhInfos(loanCode);
			model.addAttribute("checkView",checkView);
		} else{
			AntifraudTelEx checkView = consultService.getDhzhInfosNew(loanCode);
			model.addAttribute("checkView",checkView);
		}
		// 判断为旧版
		if (newOrOldFlag.equals(ApplicationConstants.LOANINFO_OLD_FLAG)) {
			// 如果editFlag不为空，则跳转到只读页面
			if (!StringUtils.isEmpty(editFlag)) {
				return "/antifraud/phoneConsultView";
			}
			return "/antifraud/phoneConsultForm";
		}
		// 判断为新版
		// 如果editFlag不为空，则跳转到只读页面
		if (!StringUtils.isEmpty(editFlag)) {
			return "/antifraud/phoneConsultView_new";
		}
		return "/antifraud/phoneConsultForm_new";
	}
	
	/**
	 * 【保存】反欺诈电话照会内容
	 * 2016年3月26日
	 * By wanglidong
	 * @param phoneWorkListEx 单位电话，电话录音，联系人 ,本人核实 集合
	 * @return 单位电话，电话录音，联系人 ,本人核实 集合
	 */
	@ResponseBody
	@RequestMapping(value = "saveWorkAll")
	public String saveWorkAll(PhoneListEx phoneWorkListEx){
		PhoneListEx updataTelAll;
		try {
			updataTelAll = consultService.updataTelAll(phoneWorkListEx);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return jsonMapper.toJson(updataTelAll);
	}
	
	/**
	 * 到反欺诈电话照会【查看】页面
	 * @param model
	 * @param loanCode 借款编码
	 * @return 查看页面
	 */
	@RequestMapping(value = "goView")
	public String goView(Model model,String loanCode,String editFlag){
		// 获取新版旧版标识
		String newOrOldFlag = verifyCommonService.getOldornewFlag(loanCode);
		// 判断为旧版
		if (newOrOldFlag.equals(ApplicationConstants.LOANINFO_OLD_FLAG)) {
			AntifraudTelEx checkView = consultService.getDhzhInfos(loanCode);
			model.addAttribute("checkView",checkView);
			return "/antifraud/phoneConsultView";
		}
			
			AntifraudTelEx checkView = consultService.getDhzhInfosNew(loanCode);
			model.addAttribute("checkView",checkView);
			return "/antifraud/phoneConsultView_new";
	
	}

	/**
	 * 删除【单位】电话 	
	 * 2016年3月1日
	 * By wanglidong
	 * @param telNumEx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delWorkTel")
	public String delWorkTel(String id){
		try {
			consultService.delWorkTel(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}

	/**
	 * 增加【单位】电话
	 * 2016年3月1日
	 * By wanglidong
	 * @param telNumEx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addWorkTel")
	public String addWorkTel(PhoneWorkEx phoneWorkEx){
		String addId = null;
		try {
			addId = consultService.addWorkTel(phoneWorkEx);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return addId;
	}

	/**
	 * 新增【工作证明人】
	 * 2016年3月3日
	 * By wanglidong
	 * @param personEx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addContacts")
	public String addContacts(PhoneWorkProvesEx phoneWorkProvesEx){
		String addContactsId = null;
		try {
			addContactsId = consultService.addContacts(phoneWorkProvesEx);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return addContactsId;
	}
	
	/**
	 * 新增【其他联系人】
	 * 2016年3月3日
	 * By wanglidong
	 * @param personEx 
	 * @return id
	 */
	@ResponseBody
	@RequestMapping(value = "addOhters")
	public String addOhters(PhoneWorkProvesEx phoneWorkProvesEx){
		String addContactsId = null;
		try {
			addContactsId = consultService.addOhters(phoneWorkProvesEx);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return addContactsId;
	}
	
	/**
	 * 新增家庭联系人
	 * 2016年3月3日
	 * By wanglidong
	 * @param personEx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addfamily")
	public String addfamily(PhoneWorkProvesEx phoneWorkProvesEx){
		String addContactsId = null;
		try {
			addContactsId = consultService.addfamily(phoneWorkProvesEx);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return addContactsId;
	}
	
	/**
	 * 删除联系人
	 * 2016年3月1日
	 * By wanglidong
	 * @param telNumEx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delProves")
	public String delProves(String id){
		try {
			consultService.delProves(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}

	/**
	 * 新增本人核实
	 * 2016年3月4日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return id
	 */
	@ResponseBody
	@RequestMapping(value = "addConfirm")
	public String addConfirm(PhoneConfirmEx phoneConfirmEx){
		String id = null;
		try {
			id = consultService.addConfirm(phoneConfirmEx);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return id;
	}
	
	/**
	 * 删除本人核实
	 * 2016年3月4日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return id
	 */
	@ResponseBody
	@RequestMapping(value = "delConfirm")
	public String delConfirm(String id){
		try {
			consultService.delConfirm(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}

	/**
	 * 调用打电话接口
	 * 2016年4月28日
	 * By wanglidong
	 * @param request
	 * @param dhlyxx
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "saveRecord")
	public String saveRecord(HttpServletRequest request,DhzhDhlyxx dhlyxx){
		DhzhDhlyxx saveRecord = null;
		try {
			String ip = super.getIpAddress(request);
			saveRecord = consultService.saveRecord(dhlyxx,ip);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// 返回接口错误原因
			if(e.getMessage().equals(ReturnConstant.ERROR) || e.getMessage().equals(ReturnConstant.ERROR_NET) ||
					e.getMessage().equals(ReturnConstant.ERROR_EXT_NET)){
				return e.getMessage();
			// 如果不是调用接口报错，则返回fasle
			}else{
				return BooleanType.FALSE;
			}
		}
		return saveRecord.getId();
	}
	
	/**
	 * 删除电话录音
	 * 2015年11月29日
	 * By 赖敏
	 * @param id 录音id
	 * @return 成功返回true,失败返回false
	 */
	@ResponseBody
	@RequestMapping(value = "delRecord")
	public String delRecord(String id){
		String delRecord = null;
		try {
			delRecord = consultService.delRecord(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return delRecord;
	}

}

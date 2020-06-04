package com.creditharmony.approve.localnet.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.localnet.entity.InnerRepeat;
import com.creditharmony.approve.localnet.service.InsideNetService;
import com.creditharmony.approve.verify.entity.ex.InnerCheckEx;
import com.creditharmony.approve.verify.entity.ex.PayBackMonthEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.util.SurveyUtil;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.web.BaseController;

/**
 * 内网审核的controller
 * 
 * @Class Name InsideNetController
 * @author 刘燕军
 * @Create In 2015年11月27日
 */
@Controller
@RequestMapping(value = "${adminPath}/verify/InsideNet")
public class InsideNetController extends BaseController {
	@Autowired
	private InsideNetService innerService;

	/**
	 * 初始化内网审核页面 2015年12月4日 By 刘燕军
	 * @param model
	 * @param loanCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list")
	public String getList(Model model, VerifyParamEx param) throws Exception {
		String loanCode = param.getLoanCode();
		if (!StringUtils.isBlank(loanCode)) {
			InnerCheckEx checkView = innerService.getInnerCheckView(param);
			// 历史归户信息概况显示信息 message
			model.addAttribute("message",
					SurveyUtil.getMessage(checkView.getCustomerHis()));
			model.addAttribute("InnerCheck", checkView);
			model.addAttribute("checkType", param.getCheckType());
		} else {
			throw new Exception("借款编号为空！！！");
		}
		return "/localnet/localnetForm";
	}
	/**
	 * 内网审核 查看页面
	 * 2015年12月26日
	 * By 刘燕军
	 * @param model
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getView")
	public String getView(Model model, VerifyParamEx param) throws Exception {
		String loanCode = param.getLoanCode();
		if (!StringUtils.isBlank(loanCode)) {
			InnerCheckEx checkView = innerService.getInnerCheckView(param);
			// 历史归户信息概况显示信息 message
			model.addAttribute("message",
					SurveyUtil.getMessage(checkView.getCustomerHis()));
			model.addAttribute("InnerCheck", checkView);
			model.addAttribute("checkType", param.getCheckType());
		} else {
			throw new Exception("借款编号为空！！！");
		}
		return "/localnet/localnetView";
	}

	/**
	 * 内网审核，查重数据更新 2015年11月27日 By 刘燕军
	 * 
	 * @param model
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "updateInnerCheck")
	public String updateInnerCheck(Model model, InnerRepeat repeat,VerifyParamEx param)
			throws Exception {
		try {
			// 首先判断参数是不是为空
			String id = repeat.getId();
			if (!StringUtils.isBlank(id)) {
				try {
					innerService.updateInnerCheck(repeat,param);
				} catch (Exception e) {// 如果更新失败，返回false
					logger.error(e.getMessage());
					return BooleanType.FALSE;
				}
			} else {
				return BooleanType.FALSE;
			}
			return BooleanType.TRUE;
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(repeat + "---------");
			throw new Exception("保存失败");
		}

	}

	/**
	 * 内网审核近12期逾期次数
	 * 2015年12月19日
	 * By wanglidong
	 * @param model
	 * @param loanCode 借款编码
	 * @return 跳转到逾期次数页面
	 */
	@RequestMapping(value = "getLateTime")
	public String getLateTime(Model model, String loanCode){
		List<PayBackMonthEx> lateTime = innerService.getLateTime(loanCode);
		model.addAttribute("lateTime", lateTime);
		// 跳转到逾期次数页面
		return "/localnet/payBackMonth";
	}

	/**
	 * 内网审核逾期记录
	 * 2015年12月19日
	 * By wanglidong
	 * @param model
	 * @param loanCode 借款编码
	 * @return 跳转到逾期记录页面
	 */
	@RequestMapping(value = "getLateTimeRecord")
	public String getLateTimeRecord(Model model, String loanCode) {
		List<PayBackMonthEx> recordList = innerService.getLateTimeRecord(loanCode);
		model.addAttribute("recordList", recordList);
		// 跳转到逾期记录页面
		return "/localnet/lateTimeRecord";
	}
	
	/**
	 * 获取查重内容更新
	 * 2016年6月22日
	 * By 刘燕军
	 * @param model
	 * @param param
	 * @return 查重信息
	 */
	@RequestMapping(value = "getInnerRepeat")
	public String getInnerRepeat(Model model,VerifyParamEx param){
		InnerCheckEx checkView = innerService.getInnerRepeats(param);
		model.addAttribute("InnerCheck", checkView);
		return "/localnet/localnetReplaceForm";
	}
}

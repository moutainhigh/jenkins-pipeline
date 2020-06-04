package com.creditharmony.approve.common.web;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.approve.common.service.PopViewNewService;
import com.creditharmony.approve.common.service.PopViewService;
import com.creditharmony.approve.verify.entity.Contact;
import com.creditharmony.approve.verify.entity.LoanBank;
import com.creditharmony.approve.verify.entity.LoanHouse;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.VerifyCommonService;
import com.creditharmony.core.web.BaseController;

/**
 * 查看弹出页面
 * 
 * @Class Name PopViewController
 * @author 李文勇
 * @Create In 2015年11月27日
 * @update in 2016-10-10
 */
@Controller
@RequestMapping(value = "${adminPath}/common/popview")
public class PopViewController extends BaseController {

	@Autowired
	private PopViewService popViewService;
	@Autowired
	private PopViewNewService popViewNewService;
	@Autowired
	private VerifyCommonService verifyCommonService;

	/**
	 * 
	 * 2015年11月27日 By 李文勇
	 * 
	 * @param model
	 * @param loanCode
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ParseException
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "showDetail")
	public String showDetail(Model model, VerifyParamEx verifyParamEx)
			throws IllegalAccessException, InvocationTargetException, ParseException {
		// 新版申请表与旧版申请表区分标识
		String flag = verifyCommonService.getOldornewFlag(verifyParamEx.getLoanCode().trim());
		if ("0".equals(flag)) {
			// 申请信息，单位信息，房产信息，居住信息，客户个人信息
			Map<String, Object> resutlMap = popViewService.showDetail(verifyParamEx);
			model.addAttribute("ApplyInformation", resutlMap.get("ApplyInformation"));
			// 房产信息
			List<LoanHouse> loanHouseInfo = (List<LoanHouse>) resutlMap.get("loanHouseInfo");
			model.addAttribute("loanHouseInfo", loanHouseInfo);
			// 联系人信息列表
			List<Contact> contact = (List<Contact>) resutlMap.get("contact");
			model.addAttribute("contact", contact);
			// 银行卡信息列表
			List<LoanBank> loanBank = (List<LoanBank>) resutlMap.get("loanBank");
			model.addAttribute("loanBank", loanBank);
			model.addAttribute("customerType", verifyParamEx.getType());
			return "/common/popView";
		} else {
			// 进件信息、借款意愿、个人基本信息、工作信息、征信账户信息、法人保证人信息
			Map<String, Object> resutlMap = popViewNewService.showDetail(verifyParamEx);
			model.addAttribute("ApplyInformation", resutlMap.get("ApplyInformation"));
			// 联系人信息
			List<Contact> contact = (List<Contact>) resutlMap.get("contact");
			model.addAttribute("contact", contact);
			// 银行卡信息
			List<LoanBank> loanBank = (List<LoanBank>) resutlMap.get("loanBank");
			model.addAttribute("loanBank", loanBank);
			model.addAttribute("customerType", verifyParamEx.getType());
			return "/common/popView_new";
		}
	}

}

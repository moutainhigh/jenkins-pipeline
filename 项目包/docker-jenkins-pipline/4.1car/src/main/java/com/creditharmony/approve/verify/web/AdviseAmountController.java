package com.creditharmony.approve.verify.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.verify.entity.AdviseAmount;
import com.creditharmony.approve.verify.entity.AuditRatingResult;
import com.creditharmony.approve.verify.service.AdviseAmountService;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.web.BaseController;
	
/**
	 * 推荐额度控制类
	 * @Class Name AdviseAmountController
	 * @author 张虎
	 * @Create In 2016年8月18日
	 */
@Controller
@RequestMapping(value = "${adminPath}/verify/adviseAmount")
public class AdviseAmountController extends  BaseController{
	
	@Autowired
	private AdviseAmountService adviseAmountService;
	
	/**
	 * 
	 * 2016年8月18日
	 * By 张虎
	 * @param model
	 * @param param
	 * @return 推荐额度
	 */
	@ResponseBody
	@RequestMapping(value="getAdviseAmount")
	public BigDecimal getAdviseAmount(Model model, AdviseAmount param){
		BigDecimal remark = null;
		try{
			remark = adviseAmountService.getAdviseAmount(param);
		}catch(Exception e){
			logger.error(e.getMessage());
			remark = null;
		}
				
		return remark;
	}
	
	/**
	 * 
	 * 2016年8月18日
	 * By 张虎
	 * @param model
	 * @param param
	 * @return 推荐额度
	 */
	@ResponseBody
	@RequestMapping(value="saveAdviseAmount")
	public String saveAdviseAmount(Model model, AuditRatingResult auditRatingResult){
		String result = BooleanType.FALSE;
		try{
			result = adviseAmountService.saveRatingAdviseAmount(auditRatingResult);
		}catch(Exception e){
			logger.error(e.getMessage());
			result = BooleanType.FALSE;
		}
				
		return result;
	}
	
	
}

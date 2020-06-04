package com.creditharmony.approve.verify.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.AddInnerRepeatService;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.web.BaseController;
	
/**
	 * 初审决策时 查重
	 * @Class Name AddInnerRepeatController
	 * @author 刘燕军
	 * @Create In 2016年1月6日
	 */
@Controller
@RequestMapping(value = "${adminPath}/verify/innerRepeat")
public class AddInnerRepeatController extends  BaseController{
	
	@Autowired
	private AddInnerRepeatService addInnerRepeatService;
	
	/**
	 * 
	 * 2016年1月6日
	 * By 刘燕军
	 * @param model
	 * @param param
	 * @return 查重的结果返回
	 */
	@ResponseBody
	@RequestMapping(value="getResult")
	public String getResult(Model model,VerifyParamEx param){
		String remark = "";
		try{
			remark = addInnerRepeatService.checkInfo(param);
		}catch(Exception e){
			logger.error(e.getMessage());
			remark = null;
		}
				
		return remark;
	}
	
	@ResponseBody
	@RequestMapping(value="getResultRefuse")
	public String getResultRefuse(Model model,VerifyParamEx param){
		String remark = "";
		try{
			remark = addInnerRepeatService.checkForRefuse(param);
		}catch(Exception e){
			logger.error(e.getMessage());
			remark = null;
		}
				
		return remark;
	}
	/**
	 * 获取是否发起过外访
	 * 2016年7月21日
	 * By 刘燕军
	 * @param model
	 * @param param
	 * @return 是否发起过外访
	 */
	@ResponseBody
	@RequestMapping("getOutVisit")
	public String getOutVisit(Model model,VerifyParamEx param){
		String result = BooleanType.FALSE;
		try{
			result = addInnerRepeatService.checkOutVisit(param);
		}catch(Exception e ){
			logger.error(e.getMessage());
		}
		return result;
	}
}

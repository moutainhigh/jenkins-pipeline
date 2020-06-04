package com.creditharmony.approve.credit.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.web.BaseController;
import com.creditharmony.approve.credit.entity.CreditQueryRecord;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
import com.creditharmony.approve.credit.entity.ex.CreditQueryRecordEx;
import com.creditharmony.approve.credit.service.CreditQueryRecordService;

/**
 * 查询记录相关操作
 * @Class Name CreditQueryRecordController
 * @author 李文勇
 * @Create In 2016年2月3日
 */
@Controller
@RequestMapping(value = "${adminPath}/creditdetailed/queryrecord")
public class CreditQueryRecordController extends BaseController{

	@Autowired
	private CreditQueryRecordService creditQueryRecordService;
	
	/**
	 * 查询画面
	 * 2016年2月1日
	 * By 李文勇
	 * @return String
	 */
	@RequestMapping(value="initPage")
	public String initPage(){
		return "credit/queryInformation";
	}
	
	/**
	 * 显示数据
	 * 2016年2月15日
	 * By 李文勇
	 * @param creditReportDetailed
	 * @return 结果集list
	 */
	@ResponseBody
	@RequestMapping(value="showData")
	public List<CreditQueryRecord> showData(CreditReportDetailed creditReportDetailed){
		List<CreditQueryRecord> result = creditQueryRecordService.showData(creditReportDetailed);
		return result;
	}
	
	/**
	 * 保存数据
	 * 2016年2月15日
	 * By 李文勇
	 * @param param
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="saveData")
	public String saveData(CreditQueryRecordEx param){
		int result = creditQueryRecordService.saveData(param);
		if( result > 0 ){
			return "true";
		}
		return "false";
	}
	
	/**
	 * 删除数据
	 * 2016年2月16日
	 * By 李文勇
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="deleteData")
	public String deleteData(CreditQueryRecord param){
		int result = creditQueryRecordService.deleteData(param);
		if(result > 0){
			return "true";
		}
		return "false";
	}
}

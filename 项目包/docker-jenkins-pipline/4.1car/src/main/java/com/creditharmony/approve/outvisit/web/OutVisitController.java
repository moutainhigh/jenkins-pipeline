package com.creditharmony.approve.outvisit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.creditharmony.approve.common.service.CityInfoService;
import com.creditharmony.approve.outvisit.entity.OutsideCheckInfo;
import com.creditharmony.approve.outvisit.entity.OutsideCheckList;
import com.creditharmony.approve.outvisit.entity.ex.OutsideCheckInfoEx;
import com.creditharmony.approve.outvisit.service.OutVisitService;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.web.BaseController;

/**
 * 外访
 * @Class Name OutVisitController
 * @author 赖敏
 * @Create In 2015年12月3日
 */
@Controller
@RequestMapping(value = "${adminPath}/check/outVisit")
public class OutVisitController extends BaseController {
	
	@Autowired
	private CityInfoService cityInfoService;
	@Autowired
	private OutVisitService outVisitService;
	
	/**
	 * 发起外访初始化页面
	 * 2015年12月7日
	 * By 赖敏
	 * @param model
	 * @param loanCode 借款编码
	 * @return 发起外访页面
	 */
	@RequestMapping(value = "goForm")
	public String goForm(Model model,String loanCode){
		//获取外访信息
		OutsideCheckInfoEx checkInfo = outVisitService.getAddressSimple(loanCode);
		//绑定默认省份列表
		checkInfo.setWorkUnitProvinces(cityInfoService.findProvince());
    	//根据1级码绑定默认城市列表
		if(StringUtils.isNotEmpty(checkInfo.getWorkUnitProvince())){
			checkInfo.setWorkUnitCitys(cityInfoService.findCity(checkInfo.getWorkUnitProvince()));
		}
    	//根据2级码绑定默认地区列表
		if(StringUtils.isNotEmpty(checkInfo.getWorkUnitCity())){
			checkInfo.setWorkUnitAreas(cityInfoService.findDistrict(checkInfo.getWorkUnitCity()));
		}
		model.addAttribute("checkInfo", checkInfo);
		return "outvisit/outVisitForm";
	}	
	
	/**
	 * 跳转至查看外访清单页面
	 * 2015年12月16日
	 * By 赖敏
	 * @param model
	 * @param relationId 关联ID(变更历史表)
	 * @return 查看历史外访清单页面
	 */
	@RequestMapping(value = "goView")
	public String goView(Model model,String relationId,String loanCode){
		OutsideCheckList outsideCheckList=new OutsideCheckList();
		outsideCheckList.setLoanCode(loanCode);
		outsideCheckList.setrId(relationId);
		OutsideCheckInfo checkInfo = outVisitService.getInfosByRid(outsideCheckList);
		model.addAttribute("checkInfo",checkInfo);		
		return "outvisit/outVisitView";		
	}
	
	@ResponseBody
	@RequestMapping(value = "isShow")
	public String isShow(Model model,String loanCode,String checkType){
		if(StringUtils.isNotEmpty(loanCode)){
			try{
				return outVisitService.isShow(loanCode,checkType);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		return BooleanType.FALSE;
	}
}

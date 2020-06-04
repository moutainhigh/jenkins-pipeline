package com.creditharmony.approve.common.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.entity.LoanPrdEntity;
import com.creditharmony.approve.common.service.CityInfoService;
import com.creditharmony.approve.common.service.CompletedListService;
import com.creditharmony.approve.common.service.LoanPrdService;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.verify.service.CheckService;
import com.creditharmony.approve.verify.service.ReconsiderFinalExamineService;
import com.creditharmony.core.users.entity.Area;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.web.BaseController;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 下拉框加载
 * @Class Name SelectInitController
 * @author xiaoniu.hu
 * @Create In 2015年12月30日
 */
@Controller
@RequestMapping(value = "${adminPath}/common/selectInit")
public class SelectInitController extends BaseController {
	
	@Autowired
	private CheckService firstVerifyService;
	@Autowired
	ReconsiderFinalExamineService reconsiderFinalExamineService;
	@Autowired
	private CityInfoService cityInfoService;
	@Autowired
	private CompletedListService completedListService;
	/**
	 * 获取对应的拒绝码
	 * 2016年1月19日
	 * By 刘燕军
	 * @param id
	 * @return 拒绝码实体
	 */
	@RequestMapping(value="getOtherGlRefuses" , method=RequestMethod.POST)
	@ResponseBody
	public String getOtherGlRefuses(String id){	
		List<GlRefuse> glRefuses = null;
		try{
			glRefuses = reconsiderFinalExamineService.getRefuseCode(id);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug(e.getMessage());
			return null;
		}
		return jsonMapper.toJson(glRefuses);
	}
	/**
	 * 获取门店名称
	 * 2016年3月18日
	 * By 刘燕军
	 * @param org
	 * @param request
	 * @param response
	 * @param model
	 * @return 门店名称集合
	 */
	@ResponseBody
	@RequestMapping(value = "selectOrgList")
	public List<Map<String, Object>> selectStorePage(Org org,HttpServletRequest request,HttpServletResponse response, Model model) {
		if(null==org.getArea()){
			Area area = new Area();
			org.setArea(area);
		}
		List<Org> orgs = firstVerifyService.findStoresPage(org);
		List<Map<String, Object>> mapList = Lists.newArrayList();
		for (Org or :  orgs) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", or.getId());
			map.put("name", or.getName());
			mapList.add(map);
		}
		return mapList;
	}
	/**
	 * 获取汇成信审员工集合
	 * 2016年6月22日
	 * By wanglidong
	 * @param org
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getApproveUserList")
	public List<Map<String, String>> selectApproveUserList(Model model) {
		List<String> orgIds = completedListService.getCheckAfraudRecheckOrgIds();
		List<Map<String,String>> approveUserList = completedListService.getApproveUserList(orgIds);
		return approveUserList;
	}

	/**
	 * 异步加载城市
	 * 2015年12月31日
	 * By 赖敏
	 * @param provinceId 省id
	 * @return 城市json
	 */
	@ResponseBody
	@RequestMapping(value = "asynLoadCity", method = RequestMethod.POST)
	public String asynLoadCity(String provinceId) {
		List<CityInfo> cityList = cityInfoService.findCity(provinceId);
		return jsonMapper.toJson(cityList);
	}

	/**
	 * 异步加载区县
	 * 2015年12月31日
	 * By 赖敏
	 * @param cityId 城市id
	 * @return 区县json
	 */
	@ResponseBody
	@RequestMapping(value = "asynLoadDistrict", method = RequestMethod.POST)
	public String asynLoadDistrict(String cityId) {
		List<CityInfo> districtList = cityInfoService.findDistrict(cityId);
		return jsonMapper.toJson(districtList);
	}

	/**
	 * 获取产品分期
	 * 2015年12月31日
	 * By By 赖敏
	 * @param productCode
	 * @param productType
	 * @return 分期
	 */
	@ResponseBody
	@RequestMapping(value = "asynLoadProductMonths", method = RequestMethod.POST)
	public String asynLoadProductMonths(String productCode, String productType) {
		LoanPrdEntity l = LoanPrdService.getPrdByTypeAndCode(productType,productCode);
		return l.getProductMonths();
	}

}

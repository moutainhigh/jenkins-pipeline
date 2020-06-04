package com.creditharmony.approve.phone.web;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.service.CityInfoService;
import com.creditharmony.approve.common.util.CallPhoneUtil;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;
import com.creditharmony.approve.phone.entity.WorkTelNum;
import com.creditharmony.approve.phone.entity.ex.TelCheckBorrowerInfoEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckBorrowerNumEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckCompanyEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckContactNumEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckContactPersonEx;
import com.creditharmony.approve.phone.service.TelCheckService;
import com.creditharmony.approve.phone.view.TelCheckView;
import com.creditharmony.approve.rule.applyengine.client.ApplyengineRulesSoapFault;
import com.creditharmony.approve.verify.constants.ApplicationConstants;
import com.creditharmony.approve.verify.entity.CompManage;
import com.creditharmony.approve.verify.entity.ex.IndustryTypeEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.CheckService;
import com.creditharmony.approve.verify.service.VerifyCommonService;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.loan.type.FamilyRelation;
import com.creditharmony.core.web.BaseController;
import com.google.common.collect.Maps;

/**
 * 电话照会controller类 
 * @Class Name TelCheckController
 * @author 王浩
 * @Create In 2015年12月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/verify/telcheck")
public class TelCheckController extends BaseController {

	@Autowired
	private TelCheckService telCheckService;	
	@Autowired
	private CityInfoService cityInfoService;	
	@Autowired
	private CheckService checkService;
	
	@Autowired
	private VerifyCommonService verifyCommonService;
	
	/**
	 * 获取页面加载时所需信息 
	 * 2015年12月1日 
	 * By 王浩
	 * @param model
	 * @param verifyParamEx 参数集
	 * @param editFlag 编辑还是查看
	 * @return 返回页面跳转的路径
	 */
	@RequestMapping(value = "form")
	public String getTelChceckList(Model model, VerifyParamEx verifyParamEx,
			String editFlag) {
		
		// 借款人类型
		String type = verifyParamEx.getType().trim();
		
		// 借款编码
		String loanCode = verifyParamEx.getLoanCode().trim();
		// 获取新版旧版标识
		String newOrOldFlag = verifyCommonService.getOldornewFlag(loanCode);
		
		TelCheckView telView = telCheckService.getTelCheckView(verifyParamEx, newOrOldFlag);
		model.addAttribute("telView", telView);
		List<CityInfo> provinceList = cityInfoService.findProvince();
		model.addAttribute("province", provinceList);
		IndustryTypeEx compIndustry = telCheckService.getCompIndustry();
		model.addAttribute("compIndustry", compIndustry);
		
		//本人- 自述分期字典内容
		List<Integer> newAllMonth = checkService.getAllMonths(checkService.getConsultFlag(verifyParamEx.getLoanCode()));
		model.addAttribute("newAllMonth", newAllMonth);
		
		// 取出家庭联系人的配偶数据，放到页面上用
		List<TelCheckContactPersonEx> familyList = telView.getContactFamilyList();
		if(ArrayHelper.isNotEmpty(familyList)){
			for(TelCheckContactPersonEx family : familyList){
				// 如果关系为配偶
				if( FamilyRelation.MATES.getCode().equals(family.getLoanManRelation()) ){
					model.addAttribute("mateName", family.getName());
					model.addAttribute("mateCertType", family.getDictCertType());
					model.addAttribute("mateCertNum", family.getCustomerCertNum());
					model.addAttribute("hasMate", NumberConstants.ONE_STRING);
					// 初始化市、区列表
					if(StringUtils.isNotEmpty(family.getProvince())){
						List<CityInfo> cityList = cityInfoService.findCity(family.getProvince());
						family.setCityList(cityList);
					}
					if(StringUtils.isNotEmpty(family.getCity())){
						List<CityInfo> districtList = cityInfoService.findDistrict(family.getCity());
						family.setDistrictList(districtList);
					}
					break;
				}
			}
		}

		//通过借款编号获取主借人、法人保证人、配偶的身份证号
		if(type.equals("0")){
			Map<String,String>  cerNumInfo = verifyCommonService.getCertNumByLoanCode(loanCode);
			if(StringUtils.isNotEmpty(cerNumInfo.get("corporateId"))){
				String rGxId = cerNumInfo.get("corporateId");
				List<DhzhDhlyxx> corporateList = telCheckService.getCorporateTelByRid(rGxId);
				model.addAttribute("corporateList", corporateList);
			}
			
			model.addAttribute("cerNumInfo",cerNumInfo);
		}
		
		// 判断为旧版
		if (newOrOldFlag.equals(ApplicationConstants.LOANINFO_OLD_FLAG)) {
			// 如果editFlag不为空，则跳转到只读页面
			if (!StringUtils.isEmpty(editFlag)) {
				return "phone/telCheckView";
			}
			return "phone/telCheckForm";
		}
		// 判断为新版
		// 如果editFlag不为空，则跳转到只读页面
		if (!StringUtils.isEmpty(editFlag)) {
			return "phone/telCheckView_new";
		}
		//return "phone/telCheckView_new";
		return "phone/telCheckForm_new";
		
		
		
		
	}

	/**
	 * 异步删除电话录音 
	 * 2015年12月4日 
	 * By 王浩
	 * @param recordId 录音id
	 * @return 返回是否删除成功
	 */
	@ResponseBody
	@RequestMapping(value = "delCallRecord", method = RequestMethod.POST)
	public String asyncDelCallRecord(String recordId) {
		return telCheckService.asyncDeleteCallRecord(recordId);
	}

	/**
	 * 异步保存电话录音 
	 * 2015年12月10日 
	 * By 王浩
	 * @param callRecord 电话录音信息
	 * @return 返回是否保存成功的标识
	 */
	@ResponseBody
	@RequestMapping(value = "saveCallRecord", method = RequestMethod.POST)
	public String asyncSaveCallRecord(DhzhDhlyxx callRecord) {
		return telCheckService.asyncSaveCallRecord(callRecord);
	}

	/**
	 * 保存电话审核关系联系人 
	 * 2015年12月14日 
	 * By 王浩
	 * @param contactPerson 联系人信息
	 * @param verifyParamEx 参数集
	 * @return 返回是否保存成功的标识
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "saveContactInfo")
	public String saveContactInfo(TelCheckContactPersonEx contactPerson,
			VerifyParamEx verifyParamEx) throws Exception {
		String result = telCheckService.saveContactPersonInfo(contactPerson,
				verifyParamEx);
		return result;
	}
	
	/**
	 * 保存电话审核关系联系人 
	 * 2015年12月14日 
	 * By 王浩
	 * @param contactPerson 联系人信息
	 * @param verifyParamEx 参数集
	 * @return 返回是否保存成功的标识
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "saveContactInfoNew")
	public TelCheckContactPersonEx saveContactInfoNew(TelCheckContactPersonEx contactPerson,
			VerifyParamEx verifyParamEx) throws Exception {
		TelCheckContactPersonEx result = telCheckService.saveContactPersonInfoNew(contactPerson,
				verifyParamEx);
		return result;
	}
	
	
	/**
	 * 保存电话审核关系联系人 
	 * 2015年12月14日 
	 * By 王浩
	 * @param contactPerson 联系人信息
	 * @param verifyParamEx 参数集
	 * @return 返回是否保存成功的标识
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "zcContact")
	public String zcContact(TelCheckContactPersonEx contactPerson,
			VerifyParamEx verifyParamEx) throws Exception {
		String result = telCheckService.zcSaveContactPersonInfo(contactPerson,
				verifyParamEx);
		return result;
	}
	
	/**
	 * 新版申请表add
	 * 保存电话审核关系联系人 
	 * 2015年12月14日 
	 * By 王浩
	 * @param contactPerson 联系人信息
	 * @param verifyParamEx 参数集
	 * @return 返回是否保存成功的标识
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "zcContactNew")
	public TelCheckContactPersonEx zcContactNew(TelCheckContactPersonEx contactPerson,
			VerifyParamEx verifyParamEx) throws Exception {
		TelCheckContactPersonEx result = telCheckService.zcSaveContactPersonInfoNew(contactPerson,
				verifyParamEx);
		return result;
	}
	
	/**
	 * 新版申请表
	 * 保存电话审核关系联系人电话信息 
	 * 2015年12月14日 
	 * By 王浩
	 * @param contactPerson 联系人信息
	 * @param verifyParamEx 参数集
	 * @param telCheckContactNumEx 电话信息
	 * @return 返回是否保存成功的标识
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "zcContactPhone")
	public String zcContactPhone(TelCheckContactPersonEx contactPerson,
			VerifyParamEx verifyParamEx, TelCheckContactNumEx telCheckContactNumEx) throws Exception {
		String result = telCheckService.zcSaveContactPersonInfoPhone(contactPerson,
				verifyParamEx, telCheckContactNumEx);
		return result;
	}
	
	/**
	 * 打电话图标按下-保存电话审核关系联系人 
	 * 2015年12月14日 
	 * By 王浩
	 * @param contactPerson 联系人信息
	 * @param verifyParamEx 参数集
	 * @return 返回是否保存成功的标识
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "saveCallContactInfo")
	public DhzhDhlyxx saveCallContactInfo(TelCheckContactPersonEx contactPerson,String localTel,
			VerifyParamEx verifyParamEx,HttpServletRequest request) throws Exception {
		String ip = getIpAddress(request);
		DhzhDhlyxx result = new DhzhDhlyxx();
		try{
			result = telCheckService.saveCallContactInfo(contactPerson,localTel,verifyParamEx,ip);
		}catch(Exception e){
			result.setId("");
			result.setrGxId("");
		}
		return result;
	}
	
	/**
	 * 新版申请表add
	 * 打电话图标按下-保存电话审核关系联系人 
	 * 2015年12月14日 
	 * By 王浩
	 * @param contactPerson 联系人信息
	 * @param verifyParamEx 参数集
	 * @return 返回是否保存成功的标识
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "saveCallContactInfoNew")
	public DhzhDhlyxx saveCallContactInfoNew(TelCheckContactPersonEx contactPerson,String localTel,
			VerifyParamEx verifyParamEx,HttpServletRequest request, TelCheckContactNumEx telCheckContactNumEx) throws Exception {
		String ip = getIpAddress(request);
		DhzhDhlyxx result = new DhzhDhlyxx();
		try{
			result = telCheckService.saveCallContactInfoNew(contactPerson,localTel,verifyParamEx,ip,telCheckContactNumEx);
		}catch(Exception e){
			result.setId("");
			result.setrGxId("");
		}
		return result;
	}
	
	/**
	 * 删除本人核实中的同业借款信息 
	 * 2015年12月16日 
	 * By 王浩 
	 * @param otherLoanId 同业借款信息
	 * @return 返回是否删除成功的标识
	 */
	@ResponseBody
	@RequestMapping(value = "delOtherLoan")
	public String asyncDelOtherLoan(String otherLoanId) {
		return telCheckService.deleteOtherLoan(otherLoanId);
	}
	
	/**
	 * 删除本人核实中的电话号码
	 * 2016年3月4日 
	 * By 王浩 
	 * @param id 
	 * @return 返回是否删除成功的标识
	 */
	@ResponseBody
	@RequestMapping(value = "delPersonalNums")
	public Map<String, String> delPersonalNums(String id) {
		return telCheckService.delPersonalNums(id);
	}

	/**
	 * 保存本人核实信息 
	 * 2015年12月18日 
	 * By 王浩
	 * @param borrowerInfo 本人核实信息
	 * @return 返回是否保存成功的标识
	 */
	@ResponseBody
	@RequestMapping(value = "saveBorrowerInfo")
	public String saveBorrowerInfo(TelCheckBorrowerInfoEx borrowerInfo) {
		return telCheckService.saveBorrowerInfo(borrowerInfo);
	}

	/**
	 * 保存本人核实的电话号码信息 
	 * 2015年12月18日 
	 * By 王浩
	 * @param borrowerNum 本人号码信息
	 * @return 返回是否保存成功的标识
	 */
	@ResponseBody
	@RequestMapping(value = "saveBorrowerTelNum")
	public String saveBorrowerTelNum(TelCheckBorrowerNumEx borrowerNum) {
		return telCheckService.saveBorrowerTelNum(borrowerNum);
	}
	
	/**
	 * 保存本人核实的电话号码信息 
	 * 2015年12月18日 
	 * By 王浩
	 * @param borrowerNum 本人号码信息
	 * @return 返回是否保存成功的标识
	 */
	@ResponseBody
	@RequestMapping(value = "zcBrTelNum")
	public String zcBrTelNum(TelCheckBorrowerNumEx borrowerNum) {
		return telCheckService.zcSaveBorrowerTelNum(borrowerNum);
	}
	
	/**
	 * 本人页签拨号图标按下
	 * 2016年4月15日
	 * By 李文勇
	 * @param workTelNum
	 * @param verifyParamEx
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveOnePersonNum")
	public DhzhDhlyxx saveOnePersonNum(TelCheckBorrowerNumEx borrowerNum,String localTel,
			VerifyParamEx verifyParamEx,HttpServletRequest request){
		String ip = getIpAddress(request);
		DhzhDhlyxx result = new DhzhDhlyxx();
		try{
			result = telCheckService.saveOnePersonNum(borrowerNum,localTel,verifyParamEx,ip);
		}catch(Exception e){
			result.setId("");
			result.setrGxId("");
		}
		return result;
	}
	
	/**
	 * 拨号图片按下 保存新增的单位电话号码信息 
	 * 2015年12月18日 
	 * By 王浩
	 * @param workTelNum 单位电话号码信息
	 * @return 返回是否保存成功的标识
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@ResponseBody
	@RequestMapping(value = "saveOneCompanyNum")
	public DhzhDhlyxx saveOneCompanyNum(WorkTelNum workTelNum,String localTel,
			VerifyParamEx verifyParamEx,HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		String ip = getIpAddress(request);
		DhzhDhlyxx result = new DhzhDhlyxx();
		try{
			result = telCheckService.saveOneCompanyNum(workTelNum,localTel,verifyParamEx,ip);
		}catch(Exception e){
			result.setId("");
			result.setrGxId("");
		}
		return result;
	}
	
	
	/**
	 * 法人代表手机号录音信息 
	 * 2016年10月26日 
	 * By Wangyanna
	 * @param compManage 法人代表信息
	 * @return 返回法人代表录音信息
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@ResponseBody
	@RequestMapping(value = "saveCorporateTel")
	public DhzhDhlyxx saveCorporateTel(CompManage compManage,String localTel,
			VerifyParamEx verifyParamEx,HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		String ip = getIpAddress(request);
		DhzhDhlyxx result = new DhzhDhlyxx();
		try{
			result = telCheckService.saveCorporateTel(compManage,localTel,verifyParamEx,ip);
		}catch(Exception e){
			result.setId("");
			result.setrGxId("");
		}
		return result;
	}

	/**
	 * 保存单位信息 
	 * 2015年12月21日 
	 * By 王浩	  
	 * @param companyInfo 单位信息
	 * @return 返回是否保存成功的标识
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@ResponseBody
	@RequestMapping(value = "saveCompanyInfo")
	public TelCheckCompanyEx asyncSaveCompanyInfo(TelCheckCompanyEx companyInfo) throws IllegalAccessException, InvocationTargetException {
		TelCheckCompanyEx result = telCheckService.saveCompanyInfo(companyInfo);
		return result;
	}

	/**
	 * 删除电话号码 
	 * 2015年12月31日
	 * By 路志友
	 * @param workTelNum 电话号码信息
	 * @return 返回是否删除成功的标识
	 */
	@ResponseBody
	@RequestMapping(value = "deleteCompanyTelNum")
	public Map<String, String> asyncDeleteCompanyTel(WorkTelNum workTelNum) {
		return telCheckService.deleteWorkTelNum(workTelNum);
	}
	
	/**
	 * 新版申请表add
	 * 删除关系人电话号码 
	 * 2015年12月31日
	 * By 路志友
	 * @param workTelNum 电话号码信息
	 * @return 返回是否删除成功的标识
	 */
	@ResponseBody
	@RequestMapping(value = "deleteContactTelNum")
	public Map<String, String> deleteContactTelNum(TelCheckContactNumEx telCheckContactNumEx) {
		return telCheckService.deleteContactTelNum(telCheckContactNumEx);
	}
	
	/**
	 * 刷新单位信息
	 * 2016年2月22日
	 * By 董超
	 * @param verifyParamEx 参数集
	 * @param model
	 * @return 返回单位信息
	 */
	@ResponseBody
	@RequestMapping(value = "refreshDwxx")
	public TelCheckView refreshDwxx(VerifyParamEx verifyParamEx,
			Model model) throws ApplyengineRulesSoapFault {
		TelCheckView telCheckView  = telCheckService.getDwxx(verifyParamEx);
		return telCheckView;
	}		

	/**
	 * 删除本人核实中的居住地址信息 
	 * 2015年12月16日 
	 * By 王浩 
	 * @param otherLoanId 同业借款信息
	 * @return 返回是否删除成功的标识
	 */
	@ResponseBody
	@RequestMapping(value = "delbrshd")
	public String delbrshd(String id) {
		return telCheckService.delbrshd(id);
	}
	
	/**
	 * 删除家庭联系人
	 * 2016年3月7日
	 * By 李文勇
	 * @param id
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "delContact")
	public Map<String, String> delContact(String id) {
		return telCheckService.delContact(id);
	}
	
	/**
	 * 新版申请表add
	 * 删除家庭联系人
	 * 2016年3月7日
	 * By 李文勇
	 * @param id
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "delContactNew")
	public Map<String, String> delContactNew(String id) {
		return telCheckService.delContactNew(id);
	}
	
	/**
	 * 获取行业字典数据
	 * 2016年4月14日
	 * By wanglidong
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getCompIndustry")
	public String getCompIndustry(Model model) {
		IndustryTypeEx compIndustry = telCheckService.getCompIndustry();
		model.addAttribute("compIndustry", compIndustry);
		return "/approve/verify/compIndustry";
	}
	
	/**
	 * 根据parentid和type获取职业类别
	 * 2016年4月19日
	 * By 李文勇
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getOccupationType")
	public List<Dict> getOccupationType(String parentId){
		List<Dict> result = telCheckService.getOccupationType(parentId);
		return result;
	}
	
	/**
	 * 获取职业分类
	 * 2016年4月27日
	 * By 李文勇
	 * @param value
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getZYFL")
	public List<Dict> getZYFL(String value){
		List<Dict> result = telCheckService.getZYFL(value);
		return result;
	}
	
	/**
	 * 暂存单位电话
	 * 2016年5月3日
	 * By 李文勇
	 * @param workTelNum
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@ResponseBody
	@RequestMapping(value = "zcCompany")
	public String zcCompany(WorkTelNum workTelNum) 
			throws IllegalAccessException, InvocationTargetException{
		String result = telCheckService.zcSaveWorkTelNum(workTelNum);
		return result;
	}
	/**
	 * 获取录音信息
	 * 2016年4月27日
	 * By 刘燕军
	 * @param callId
	 * @return 录音实体
	 */
	@ResponseBody
	@RequestMapping(value = "getRecord")
	public Map<String, String> getRecord(String callId){
		logger.debug("开始获取电话录音");
		Map<String, String> resultMap = Maps.newHashMap();
		try {
			resultMap.put("url",CallPhoneUtil.getRecord(callId));
		} catch (Exception e) {
			logger.error("获取电话录音失败" + callId + e.getMessage());
		}
		return resultMap;
	}
}

package com.creditharmony.approve.document.web;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.service.CityInfoService;
import com.creditharmony.approve.document.entity.ZlshCczm;
import com.creditharmony.approve.document.entity.ZlshGrzj;
import com.creditharmony.approve.document.entity.ZlshGrzjxl;
import com.creditharmony.approve.document.entity.ZlshGxht;
import com.creditharmony.approve.document.entity.ZlshJydzzm;
import com.creditharmony.approve.document.entity.ZlshJyzm;
import com.creditharmony.approve.document.entity.ZlshJyzmGdxx;
import com.creditharmony.approve.document.entity.ZlshLoanAccount;
import com.creditharmony.approve.document.entity.ZlshSbgjj;
import com.creditharmony.approve.document.entity.ZlshYxxjc;
import com.creditharmony.approve.document.entity.ZlshZczm;
import com.creditharmony.approve.document.service.ZlshCczmService;
import com.creditharmony.approve.document.service.ZlshGrzjService;
import com.creditharmony.approve.document.service.ZlshGrzjxlService;
import com.creditharmony.approve.document.service.ZlshGxhtService;
import com.creditharmony.approve.document.service.ZlshJydzzmService;
import com.creditharmony.approve.document.service.ZlshJyzmService;
import com.creditharmony.approve.document.service.ZlshLoanAccountService;
import com.creditharmony.approve.document.service.ZlshSbgjjService;
import com.creditharmony.approve.document.service.ZlshYxxjcService;
import com.creditharmony.approve.document.service.ZlshZczmService;
import com.creditharmony.approve.localnet.entity.ex.LoanInfoEx;
import com.creditharmony.approve.verify.constants.ApplicationConstants;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.VerifyCommonService;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.AccountTraceType;
import com.creditharmony.core.web.BaseController;

/**
 * 资料审核
 * @Class Name DataCheckController
 * @author 路志友
 * @Create In 2015年12月24日
 */
@Controller
@RequestMapping(value = "${adminPath}/document/datacheck")
public class DataCheckController extends BaseController {

	@Autowired
	private ZlshLoanAccountService zlshLoanAccountService;
	@Autowired
	private ZlshZczmService zlshZczmService;
	@Autowired
	private ZlshCczmService zlshCczmService;
	@Autowired
	private ZlshJyzmService zlshJyzmService;
	@Autowired
	private ZlshJydzzmService zlshJydzzmService;
	@Autowired
	private ZlshGxhtService zlshGxhtService;
	@Autowired
	private ZlshSbgjjService zlshSbgjjService;
	@Autowired
	private ZlshYxxjcService zlshYxxjcService;
	@Autowired
	private CityInfoService cityInfoService;
	@Autowired
	private VerifyCommonService verifyCommonService;
	@Autowired
	private ZlshGrzjService zlshGrzjService;
	@Autowired
	private ZlshGrzjxlService zlshGrzjxlService;
	
	/**
	 * 初始化资料审核页面
	 * 2016年1月28日
	 * By 路志友
	 * @param model
	 * @param verifyParamEx
	 * @param editFlag
	 * @return list
	 */
	@RequestMapping(value="loadDataCheckPage")
	public String loadDataCheckPage(Model model, VerifyParamEx verifyParamEx,String editFlag) {
		// 有效性检查
		String loanCode =  verifyParamEx.getLoanCode();
		String dictCustomerType = verifyParamEx.getType();
		String vEx = verifyParamEx.getRelId();
		String checkType = verifyParamEx.getCheckType();
		ZlshYxxjc zlshYxxjc = zlshYxxjcService.findZlshYxxjc(verifyParamEx);
		model.addAttribute("zlshYxxjc", zlshYxxjc);
		// 个人卡流水
		ZlshLoanAccount za = new ZlshLoanAccount();
		za.setLoanCode(loanCode);//借款编号
		za.setDictCustomerType(dictCustomerType);//借款人类型
		za.setRCustomerCoborrowerId(vEx);//借款人id
		za.setDictCheckType(checkType);//信审类型
		za.setDictAccountType(AccountTraceType.PERSONAL_WATER_BILLS.getCode());
		List<ZlshLoanAccount> pLoanAccount = zlshLoanAccountService.getListByLoanCode(za);
		model.addAttribute("pLoanAccount", pLoanAccount);
		// 对公卡流水
		za.setDictAccountType(AccountTraceType.PUBLIC_WATER_BILLS.getCode());
		List<ZlshLoanAccount> gLoanAccount = zlshLoanAccountService.getListByLoanCode(za);
		model.addAttribute("gLoanAccount", gLoanAccount);
		// 资产证明
		ZlshZczm zczm = new ZlshZczm();
		zczm.setLoanCode(loanCode);// 借款编号
		zczm.setDictCustomerType(dictCustomerType);// 借款人类型
		zczm.setrCustomerCoborrowerId(vEx);// 借款人id
		zczm.setDictCheckType(checkType);// 信审类型
		List<ZlshZczm> zlshZczm = zlshZczmService.getListByLoanCode(zczm);
		List<CityInfo> provinceList = cityInfoService.findProvince();
		if( ArrayHelper.isNotEmpty(zlshZczm)){
			for(int i = 0; i < zlshZczm.size(); i++){
				String confirmStr = zlshZczm.get(i).getConfirmStr();
				String[] confirm={};
				Map<String,String> map = new HashMap<String,String>();
				// 如果 确认方式不为null(,拼接的字符串)
				if(confirmStr != null){
					confirm = confirmStr.split(",");
				}
				//则把确认方式封装到map里面，页面展示用
				for(int w = 0; w < confirm.length; w++){
					map.put(confirm[w], confirm[w]);
				}
				zlshZczm.get(i).setConfirmMap(map);
				zlshZczm.get(i).setProvinceList(provinceList);
				// 根据省获取市
				if(zlshZczm.get(i)!=null && StringUtils.isNotEmpty(zlshZczm.get(i).getDictProvince())){
					List<CityInfo> cityList = cityInfoService.findCity(zlshZczm.get(i).getDictProvince());
					zlshZczm.get(i).setCityList(cityList);
				}
				// 根据市获取区
				if(zlshZczm.get(i)!=null && StringUtils.isNotEmpty(zlshZczm.get(i).getDictCity())){
					List<CityInfo> districtList = cityInfoService.findDistrict(zlshZczm.get(i).getDictCity());
					zlshZczm.get(i).setDistrictList(districtList);
				}				
			}
		}
		
		model.addAttribute("zlshZczm", zlshZczm);
		// 车产证明
		ZlshCczm cczm = new ZlshCczm();
		cczm.setLoanCode(loanCode);// 借款编号
		cczm.setDictCustomerType(dictCustomerType);// 借款人类型
		cczm.setrId(vEx);// 借款人id
		cczm.setDictCheckType(checkType);// 信审类型
		List<ZlshCczm> zlshCczm = zlshCczmService.getListByLoanCode(cczm);
		model.addAttribute("zlshCczm", zlshCczm);
		// 经营证明
		ZlshJyzm jyzm = new ZlshJyzm();
		jyzm.setLoanCode(loanCode);// 借款编号
		jyzm.setDictCustomerType(dictCustomerType);// 借款人类型
		jyzm.setrCustomerCoborrowerId(vEx);// 借款人ID
		jyzm.setDictCheckType(checkType);// 信审类型
		List<ZlshJyzm> zlshJyzmList = zlshJyzmService.getListByLoanCode(jyzm);	
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("manageProvinceList", provinceList);
		for(int i = 0; i < zlshJyzmList.size(); i++){
			List<ZlshJyzmGdxx> Gdxx =zlshJyzmService.getListByRid(zlshJyzmList.get(i).getId());
			zlshJyzmList.get(i).setJyzmGdxxList(Gdxx);
			
			ZlshJydzzm zm = new ZlshJydzzm();
			zm.setrJyzmId(zlshJyzmList.get(i).getId());
			List<ZlshJydzzm> zt = zlshJydzzmService.getListByLoanCode(zm);
			zlshJyzmList.get(i).setJydzzmList(zt);
			
			zlshJyzmList.get(i).setProvinceList(provinceList);
			// 根据省获取市
			if(zlshJyzmList.get(i)!=null && StringUtils.isNotEmpty(zlshJyzmList.get(i).getJyzmRegisteredProvince())){
				List<CityInfo> cityList = cityInfoService.findCity(zlshJyzmList.get(i).getJyzmRegisteredProvince());
				zlshJyzmList.get(i).setCityList(cityList);
			}
			// 根据市获取区
			if(zlshJyzmList.get(i)!=null && StringUtils.isNotEmpty(zlshJyzmList.get(i).getJyzmRegisteredCity())){
				List<CityInfo> districtList = cityInfoService.findDistrict(zlshJyzmList.get(i).getJyzmRegisteredCity());
				zlshJyzmList.get(i).setDistrictList(districtList);
			}
			//新增经营地址省市区
			zlshJyzmList.get(i).setManageProvinceList(provinceList);
			// 根据省获取市
			if(zlshJyzmList.get(i)!=null && StringUtils.isNotEmpty(zlshJyzmList.get(i).getManageProvince())){
				List<CityInfo> manageCityList = cityInfoService.findCity(zlshJyzmList.get(i).getManageProvince());
				zlshJyzmList.get(i).setManageCityList(manageCityList);
			}			
			// 根据市获取区
			if(zlshJyzmList.get(i)!=null && StringUtils.isNotEmpty(zlshJyzmList.get(i).getManageCity())){
				List<CityInfo> manageDistrictList = cityInfoService.findDistrict(zlshJyzmList.get(i).getManageCity());
				zlshJyzmList.get(i).setManageDistrictList(manageDistrictList);
		   }
		}
		model.addAttribute("zlshJyzmList", zlshJyzmList);
		// 购销合同资料录入项
		ZlshGxht  zt = new ZlshGxht();
		zt.setLoanCode(loanCode);//借款编号
		zt.setDictCustomerType(dictCustomerType);//借款人类型
		zt.setrCustomerCoborrowerId(vEx);// 借款人ID
		zt.setDictCheckType(checkType);// 信审类型
		List<ZlshGxht> zlshGxht = zlshGxhtService.getListByLoanCode(zt);
		model.addAttribute("zlshGxht", zlshGxht);
		
		// 社保公积金录入项
		ZlshSbgjj Sbgjj = new ZlshSbgjj();
		Sbgjj.setLoanCode(loanCode);// 借款编号
		Sbgjj.setDictCustomerType(dictCustomerType);//借款人类型
		Sbgjj.setrId(vEx);// 借款人id
		Sbgjj.setDictCheckType(checkType);// 信审类型
		List<ZlshSbgjj> zlshSbgjj = zlshSbgjjService.getListByLoanCode(Sbgjj);
		model.addAttribute("zlshSbgjj", zlshSbgjj);
		model.addAttribute("loanCode", loanCode);		
		//个人证件
		ZlshGrzj grzj = new ZlshGrzj();
		grzj.setLoanCode(loanCode);// 借款编号
		grzj.setDictCustomerType(dictCustomerType);// 借款人类型
		grzj.setrCustomerCoborrowerId(vEx);// 借款人ID
		grzj.setDictCheckType(checkType);// 信审类型
		List<ZlshGrzj> grzjList = zlshGrzjService.getListByLoanCode(grzj);		
		for(int i = 0; i < grzjList.size(); i++){
			//查询个人学历信息
			ZlshGrzjxl grxl = new ZlshGrzjxl();
			grxl.setrGrzjId(grzjList.get(i).getId());
			List<ZlshGrzjxl> grxlList = zlshGrzjxlService.getListByLoanCode(grxl);
			grzjList.get(i).setGrxlList(grxlList);
			
			grzjList.get(i).setProvinceList(provinceList);
			// 根据省获取市
			if(grzjList.get(i)!=null && StringUtils.isNotEmpty(grzjList.get(i).getMasterProvince())){
				List<CityInfo> cityList = cityInfoService.findCity(grzjList.get(i).getMasterProvince());
				grzjList.get(i).setCityList(cityList);
			}
			// 根据市获取区
			if(grzjList.get(i)!=null && StringUtils.isNotEmpty(grzjList.get(i).getMasterCity())){
				List<CityInfo> districtList = cityInfoService.findDistrict(grzjList.get(i).getMasterCity());
				grzjList.get(i).setDistrictList(districtList);
			}				
			
		}
		model.addAttribute("grzjList", grzjList);
		//获取借款信息
		VerifyParamEx param = new VerifyParamEx();
		param.setType(dictCustomerType);
		param.setLoanCode(loanCode);
		LoanInfoEx loanInfo = verifyCommonService.getLoanInfoEx(param);
		model.addAttribute("loanInfo",loanInfo);
		// 获取新版旧版标识
		String newOrOldFlag = verifyCommonService.getOldornewFlag(loanCode);
		// 判断为旧版
		if (newOrOldFlag.equals(ApplicationConstants.LOANINFO_OLD_FLAG)) {
			// 如果editFlag不为空，则跳转到只读页面
			if (!StringUtils.isEmpty(editFlag)) {
				return "document/dataCheckView";
			}
			return "document/dataCheckForm";
		}
		// 判断为新版
		// 如果editFlag不为空，则跳转到只读页面
		if (!StringUtils.isEmpty(editFlag)) {
			return "document/dataCheckView_new";
		}
		return "document/dataCheckForm_new";
	}
	
	/**
	 * ajax保存有效性检查信息
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshYxxjc
	 * @param vex
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncSaveJxxjc")
	public String asyncSaveJxxjc(ZlshYxxjc zlshYxxjc,VerifyParamEx vex) {
		return zlshYxxjcService.saveYxxjc(zlshYxxjc,vex);
	}
	
	/**
	 * ajax保存/修改银行卡流水
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshLoanAccount
	 * @param vex
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncSaveBankCard")
	public String asyncSaveBankCard(ZlshLoanAccount zlshLoanAccount,VerifyParamEx vex){
		return zlshLoanAccountService.saveLoanAccount(zlshLoanAccount,vex);
	}
	
	/**
	 * ajax删除银行卡流水
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshLoanAccount
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncDeleteBankCard")
	public String asyncDeleteBankCard(ZlshLoanAccount zlshLoanAccount){
		return zlshLoanAccountService.deleteBankCard(zlshLoanAccount);
	}
	
	/**
	 * ajax保存/修改资产证明
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshZczm
	 * @param vex
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncSavePropertyProve")
	public String asyncSavePropertyProve(ZlshZczm zlshZczm,VerifyParamEx vex){
		return zlshZczmService.saveZczm(zlshZczm,vex);
	}
	
	/**
	 * 删除资产证明
	 * 2015年12月24日
	 * By 路志友
	 * @param zlshZczm
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncDeletePropertyProve")
	public String asyncDeletePropertyProve(ZlshZczm zlshZczm){
		return zlshZczmService.deletePropertyProve(zlshZczm);
	}
	
	/**
	 * ajax保存/修改车产证明
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshCczm
	 * @param vex
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncSaveCarProve")
	public String asyncSaveCarProve(ZlshCczm zlshCczm,VerifyParamEx vex){
		return zlshCczmService.saveCczm(zlshCczm,vex);
	}
	
	/**
	 * 删除车产证明
	 * 2015年12月24日
	 * By 路志友
	 * @param zlshCczm
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncDeleteCarProve")
	public String asyncDeleteCarProve(ZlshCczm zlshCczm){
		return zlshCczmService.deleteCarProve(zlshCczm);
	}
	
	/**
	 * ajax保存/修改经营证明
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshJyzm
	 * @param vex
	 * @return String
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value="asyncSaveManageProve")
	public ZlshJyzm asyncSaveManageProve(ZlshJyzm zlshJyzm,VerifyParamEx vex) throws ParseException{
		return zlshJyzmService.saveJyzm(zlshJyzm,vex);
	}
	
	/**
	 * 删除经营证明
	 * 2015年12月24日
	 * By 路志友
	 * @param zlshJyzm
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncDeleteManageProve")
	public String asyncDeleteManageProve(ZlshJyzm zlshJyzm){
		return zlshJyzmService.deleteManageProve(zlshJyzm);
	}
	
	/**
	 * 删除股东信息
	 * 2015年12月25日
	 * By 路志友
	 * @param zlshJyzmGdxx
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncDeleteGdxx")
	public String asyncDeleteGdxx(ZlshJyzmGdxx zlshJyzmGdxx){
		return zlshJyzmService.deleteGdxx(zlshJyzmGdxx);
	}
	
	/**
	 * 删除经营地址证明
	 * 2016年2月2日
	 * By 路志友
	 * @param zlshJyzmGdxx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="asyncDeleteJydz")
	public String asyncDeleteJydz(ZlshJydzzm zlshJydzzm){
		return zlshJydzzmService.deleteManageAddrProve(zlshJydzzm);
	}
	
	/**
	 * ajax保存/修改经营地址证明
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshJydzzm
	 * @param vex
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncSaveManageAddrProve")
	public List<ZlshJydzzm> asyncSaveManageAddrProve(ZlshJyzm zlshJyzm,VerifyParamEx vex){
		List<ZlshJydzzm> result = zlshJydzzmService.saveJydzzm(zlshJyzm,vex);
		return result;
	}
	
	/**
	 * 删除经营地址证明
	 * 2015年12月24日
	 * By 路志友
	 * @param zlshJydzzm
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncDeleteManageAddrProve")
	public String asyncDeleteManageAddrProve(ZlshJydzzm zlshJydzzm){
		return zlshJydzzmService.deleteManageAddrProve(zlshJydzzm);
	}
	
	/**
	 * ajax保存/修改购销合同
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshGxht
	 * @param vex
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncSaveTradeContract")
	public String asyncSaveTradeContract(ZlshGxht zlshGxht,VerifyParamEx vex){
		return zlshGxhtService.saveGxht(zlshGxht,vex);
	}
	
	/**
	 * 删除购销合同
	 * 2015年12月24日
	 * By 路志友
	 * @param zlshGxht
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncDeleteTradeContract")
	public String asyncDeleteTradeContract(ZlshGxht zlshGxht){
		return zlshGxhtService.deleteTradeContract(zlshGxht);
	}
	
	/**
	 * ajax保存/修改社保公积金
	 * 2016年1月28日
	 * By 路志友
	 * @param zlshSbgjj
	 * @param vex
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncSaveSocialFund")
	public String asyncSaveSocialFund(ZlshSbgjj zlshSbgjj,VerifyParamEx vex){
		return zlshSbgjjService.saveSbgjj(zlshSbgjj,vex);
	}
	
	/**
	 * 删除社保公积金
	 * 2015年12月24日
	 * By 路志友
	 * @param zlshSbgjj
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="asyncDeleteSocialFund")
	public String asyncDeleteSocialFund(ZlshSbgjj zlshSbgjj){
		return zlshSbgjjService.deleteSocialFund(zlshSbgjj);
	}
	/**
	 * 删除个人证明学历
	 * 2016年9月21日
	 * By 赵春香
	 * @param ZlshGrzjxl
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="asyncDeleteGrxl")
	public String asyncDeleteGrxl(ZlshGrzjxl zlshGrzjxl){
		return zlshGrzjxlService.deleteGrzjxl(zlshGrzjxl);
	}
	/**
	 * ajax保存/修改个人证件
	 * 2016年9月21日
	 * By 路志友
	 * @param zlshJyzm
	 * @param vex
	 * @return String
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value="asyncSavePersonCredential")
	public ZlshGrzj asyncSavePersonCredential(ZlshGrzj zlshGrzj,VerifyParamEx vex) throws ParseException{
		return zlshGrzjService.saveGrzj(zlshGrzj, vex);
	}
	
}

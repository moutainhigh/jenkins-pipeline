package com.creditharmony.approve.antifraud.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.antifraud.constants.AntifraudJudgeConstants;
import com.creditharmony.approve.antifraud.dao.AntifraudJudgeDao;
import com.creditharmony.approve.antifraud.entity.AntifraudJudge;
import com.creditharmony.approve.antifraud.entity.AntifraudReport;
import com.creditharmony.approve.antifraud.entity.BacklistAll;
import com.creditharmony.approve.antifraud.entity.Refuse;
import com.creditharmony.approve.antifraud.entity.ex.AntiFraudJudgeEx;
import com.creditharmony.approve.antifraud.entity.ex.AntiFraudJudgeOptionEx;
import com.creditharmony.approve.antifraud.entity.ex.AntifraudDictListEx;
import com.creditharmony.approve.antifraud.entity.ex.BlackGraylistOptionEx;
import com.creditharmony.approve.antifraud.entity.ex.CoborrowerOptionEx;
import com.creditharmony.approve.antifraud.entity.ex.ExternalBlackListEx;
import com.creditharmony.approve.antifraud.entity.ex.OutBlackEx;
import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.dao.StatusChangeRecordDao;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.PinyingUtil;
import com.creditharmony.core.approve.type.AfraudListSource;
import com.creditharmony.core.approve.type.AfraudListType;
import com.creditharmony.core.approve.type.AfraudReportType;
import com.creditharmony.core.approve.type.BlackGreyList;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.users.util.UserUtils;

/**
 * 反欺诈判定Service
 * @Class Name AntiFraudJudgeService
 * @author wanglidong
 * @Create In 2015年11月23日
 */
@Service
public class AntiFraudJudgeService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AntifraudJudgeDao antifraudJudgeDao;
	@Autowired
	private LoanInfoDao loanInfoDao;
	@Autowired
	private StatusChangeRecordDao statusChangeRecordDao;

	/**
	 * 获取提报反欺诈信息
	 * 2015年12月24日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 返回提报反欺诈信息
	 */
	public AntifraudReport getAntifraudReport(String loanCode) {
		AntifraudReport antifraudReport = antifraudJudgeDao.getAntifraudReportTypeView(loanCode);
		return antifraudReport;
	}	

	/**
	 * 生成欺诈案件编号
	 * 2016年1月20日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 欺诈案件编号
	 */
	public String getfraudCode(String loanCode) {
		// 获取当前时间
	    Calendar c1 = new GregorianCalendar();
	    c1.set(Calendar.HOUR_OF_DAY, 0);
	    c1.set(Calendar.MINUTE, 0);
	    c1.set(Calendar.SECOND, 0);
	    // 当天起始时间
	    Date timeFrom = c1.getTime();
	    // 当前时间
		Date dateTo = new Date();
		// 用户工号
		String userCode = UserUtils.getUser().getUserCode();
		String name = UserUtils.getUser().getName();
		Map<String, Object> map = new HashMap<String, Object>();
		// 用户工号
		map.put("userCode", name);
		// 当前起始时间
		map.put("dateFrom", timeFrom);
		// 当前时间
		map.put("dateTo", dateTo);
		// 专员当天决策次数
		Integer judgeTime = antifraudJudgeDao.getJudgeTime(map);
		// 反欺诈专员自己的编号
		String afraudCode = antifraudJudgeDao.getAfraudCode(userCode);
		if(StringUtils.isBlank(afraudCode)){
			afraudCode = AntifraudJudgeConstants.ANTIFRAUD_USER_TEST_CODE;
		}
		// 门店区域名称
		String str = antifraudJudgeDao.getArea(loanCode);
		String piny="";
		if(str != null){
			// 区域文字拼音
			String pinyin = PinyingUtil.hanziToPinyin(str, ",");
			String[] pinyinArray = pinyin.split(",");
			for (int i = 0; i < 2; i++) {
				piny=piny+pinyinArray[i].subSequence(0, 1);
			}
		}else{
			logger.error("*******************门店无省份，需要手动编写门店的拼音首字母，“省份代码”表示门店拼音需要手动编写*******************");
			piny = "省份代码";
		}
		// 格式化日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
		return piny+afraudCode+df.format(timeFrom)+(judgeTime+1);
	}
	
	/**
	 * 获取外部加黑选项
	 * 2016年1月18日
	 * By wanglidong
	 * @param param 公用实体类
	 * @return 外部加黑选项
	 */
	public AntiFraudJudgeOptionEx getAntifraudOption(VerifyParamEx param) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("loanCode", param.getLoanCode());		
		// 客户类型
		map.put("customterType", param.getType());		
		AntiFraudJudgeOptionEx antifraudOption = antifraudJudgeDao.getAntifraudOption(map);
		// 联系人手机号
		List<String> contactMobile = antifraudJudgeDao.getContactMobile(map);
		// 如果有联系人
		if(ArrayHelper.isNotEmpty(contactMobile)){
			antifraudOption.setContactMobile(contactMobile);
		}
		// 新版申请表add 宅电 
		// 联系人宅电
		List<String> contactHomeTel = antifraudJudgeDao.getContactHomeTel(map);
		// 如果有宅电
		if(ArrayHelper.isNotEmpty(contactHomeTel)){
			antifraudOption.setContactHomeTel(contactHomeTel);
		}
		
		return antifraudOption;
	}

	/**
	 * 获取反欺诈加灰项
	 * 2016年1月11日
	 * By wanglidong
	 * @param param 公用实体类
	 * @return 反欺诈加灰项
	 */
	public AntiFraudJudgeOptionEx getGrayListOption(VerifyParamEx param) {		
		return antifraudJudgeDao.getGrayListOption(param.getLoanCode());
	}
	
	/**
	 * 查看是否全部解除
	 * 2015年12月17日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 解除状态集合
	 */
	public List<String> getRelieveStatus(String loanCode) {
		return antifraudJudgeDao.getRelieveStatus(loanCode);
	}

	/**
	 * 获取反欺诈决策历史信息
	 * 2016年1月18日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @param checkType 提报类型
	 * @return 决策信息
	 */
	public AntiFraudJudgeEx getAntiFraudJudgeView(String loanCode ) {
		Map<String, String> map = new HashMap<String, String>();
		// 借款编号
		map.put("loanCode", loanCode);
		// 反欺诈办理状态
		map.put("caseResult",BlackGreyList.UNPROCESSED.getCode());
		return antifraudJudgeDao.getAntiFraudJudgeView(map);
	}
	
	/**
	 * 获取外部黑名单
	 * 2015年12月28日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 外部黑名单
	 */
	public List<BacklistAll> getBlackListAll(String loanCode) {
		Map<String, String> map = new HashMap<String, String>();
		// 借款编码
		map.put("loanCode", loanCode);
		// 进件来源
		map.put("source",AfraudListSource.OUTSIDE_PLUS_BLACK.getCode());		
		List<BacklistAll> blackListAll = antifraudJudgeDao.getBlackListAll(map);
		return blackListAll;
	}
	
	/**
	 * 保存外部拉黑信息
	 * 2015年12月30日
	 * By wanglidong
	 * @param outBlackEx 反欺诈决策扩展类
	 * @return 外部拉黑信息
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public List<BacklistAll> saveOutBlackList(OutBlackEx outBlackEx) {
		// 声明外部拉黑集合对象
		List<BacklistAll> blackList = new ArrayList<BacklistAll>();
		// 声明外部拉黑对象
		BacklistAll backlistAll = null;
		if(outBlackEx == null){
			logger.error("*************************saveOutBlackList方法的参数outBlackEx 为空*******************************");
		}
		// 借款编码
		String loanCode = outBlackEx.getLoanCode();
		if(StringUtils.isNotBlank(outBlackEx.getCertNum())){// 判断身份证号不为空
			backlistAll = new BacklistAll();
			backlistAll.preInsert();
			backlistAll.setLoanCode(loanCode);
			backlistAll.setDictSource(AfraudListSource.OUTSIDE_PLUS_BLACK.getCode());
			backlistAll.setDictMarkType(BlackGreyList.BLACK_LIST.getCode());
			backlistAll.setBlackMsg(outBlackEx.getCertNum());
			backlistAll.setDictBlackType(AfraudListType.APPLICANT_ID_NUMBER.getCode());
			backlistAll.setDictBlackTypeName(AfraudListType.APPLICANT_ID_NUMBER.getName());
			blackList.add(backlistAll);
		}
		if(StringUtils.isNotBlank(outBlackEx.getFamilyPhone()) ){// 判断固定电话号不为空
			backlistAll = new BacklistAll();
			backlistAll.preInsert();
			backlistAll.setLoanCode(loanCode);
			backlistAll.setDictSource(AfraudListSource.OUTSIDE_PLUS_BLACK.getCode());
			backlistAll.setDictMarkType(BlackGreyList.BLACK_LIST.getCode());
			backlistAll.setBlackMsg(outBlackEx.getFamilyPhone());
			backlistAll.setDictBlackType(AfraudListType.FIXED_TELEPHONE_NUMBER_OF_THE_APPLICANT.getCode());
			backlistAll.setDictBlackTypeName(AfraudListType.FIXED_TELEPHONE_NUMBER_OF_THE_APPLICANT.getName());
			blackList.add(backlistAll);
		}
		if(StringUtils.isNotBlank(outBlackEx.getMobilePhone()) ){// 判断手机号不为空
			backlistAll = new BacklistAll();
			backlistAll.preInsert();
			backlistAll.setLoanCode(loanCode);
			backlistAll.setDictSource(AfraudListSource.OUTSIDE_PLUS_BLACK.getCode());
			backlistAll.setDictMarkType(BlackGreyList.BLACK_LIST.getCode());
			backlistAll.setBlackMsg(outBlackEx.getMobilePhone());
			backlistAll.setDictBlackType(AfraudListType.CONTACT_PHONE_NUMBER.getCode());
			backlistAll.setDictBlackTypeName(AfraudListType.CONTACT_PHONE_NUMBER.getName());
			blackList.add(backlistAll);
		}
		if(StringUtils.isNotBlank(outBlackEx.getUnitName()) ){// 判断单位名称不为空
			backlistAll = new BacklistAll();
			backlistAll.preInsert();
			backlistAll.setLoanCode(loanCode);
			backlistAll.setDictSource(AfraudListSource.OUTSIDE_PLUS_BLACK.getCode());
			backlistAll.setDictMarkType(BlackGreyList.BLACK_LIST.getCode());
			backlistAll.setBlackMsg(outBlackEx.getUnitName());
			backlistAll.setDictBlackType(AfraudListType.UNIT_NAME.getCode());
			backlistAll.setDictBlackTypeName(AfraudListType.UNIT_NAME.getName());
			blackList.add(backlistAll);
		}
		// 保存外部拉黑信息	
		antifraudJudgeDao.addBlackList(blackList);
		return blackList;		
	}
	
	/**
	 * 删除外部拉黑
	 * 2015年12月29日
	 * By wanglidong
	 * @param id 外部拉黑信息id
	 * @return void
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public void delOutBlack(String id) {
		antifraudJudgeDao.delOutBlack(id);
	}

	/**
	 * 查看黑灰名单在数据库中是否存在
	 * 2016年1月15日
	 * By wanglidong
	 * @param value 加黑灰内容
	 * @return 存在个数
	 */
	public int getCheckExists(String value) {
		return antifraudJudgeDao.getCheckExists(value);
	}

	/**
	 * 点击查看按钮，打开历史决策信息
	 * 2016年1月19日
	 * By wanglidong
	 * @param id 变更历史id
	 * @return 决策信息
	 */
	public AntifraudJudge getJudgeHistory(String id) {
		Map<String,String> map = new HashMap<String, String>();
		// 历史纪录表中的id
		map.put("id", id);
		return antifraudJudgeDao.getJudgeHistory(map);
	}
	
	/**
	 * 获取借款信息表中的状态
	 * 2016年1月21日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 借款状态
	 */
	public String getLoanStatus(String loanCode) {
		return antifraudJudgeDao.getLoanStatus(loanCode);
	}

	/**
	 * 获取提报类型
	 * 2016年2月1日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 提报信息
	 */
	public AntifraudReport getAntifraudReportType(String loanCode) {
		return antifraudJudgeDao.getAntifraudReportType(loanCode, BlackGreyList.UNPROCESSED.getCode());
	}
	


	/**
	 * 获取历史提报类型
	 * 2016年2月1日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 提报信息
	 */
	public AntifraudReport getAntifraudReportTypeView(String loanCode) {
		return antifraudJudgeDao.getAntifraudReportTypeView(loanCode);
	}

	/**
	 * 获取共借人的内部加黑项
	 * 2016年2月19日
	 * By wanglidong
	 * @param param
	 */
	public List<CoborrowerOptionEx> getCoborrowerOption(VerifyParamEx param) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("loanCode", param.getLoanCode());		
		// 共借人内部加灰项
		List<CoborrowerOptionEx> coborrowerOption = antifraudJudgeDao.getCoborrowerOption(map);
		// 判断是否用共借人
		if(coborrowerOption.size() > 0 ){
			for (int i = 0; i < coborrowerOption.size(); i++) {
				// 共借人id
				String id = coborrowerOption.get(i).getId();
				// 共借人联系人手机号
				List<String> contactMobile = antifraudJudgeDao.getCoborrowerContactMobile(id);
				coborrowerOption.get(i).setContactMobile(contactMobile);
			
				// 新版申请表add 共借人联系人宅电
				List<String> contactHomeTel = antifraudJudgeDao.getCoborrowerContactHomeTel(id);
				coborrowerOption.get(i).setContactHomeTel(contactHomeTel);
			}
		}
		return coborrowerOption;
	}
	
	/**
	 * 获取共借人的内部加灰项
	 * 2016年2月20日
	 * By wanglidong
	 * @param param
	 * @return
	 */
	public List<CoborrowerOptionEx> getCoborrowerGrayOption(VerifyParamEx param) {
		Map<String, String> map = new HashMap<String, String>();
		// 借款编码
		map.put("loanCode", param.getLoanCode());		
		List<CoborrowerOptionEx> coborrowerOption = antifraudJudgeDao.getCoborrowerGrayOption(map);
		return coborrowerOption;
	}

	/**
	 * 将反欺诈内部加黑项回显，获取所有加黑项区分主借人，共借人
	 * 2016年3月30日
	 * By wanglidong
	 * @param param
	 * @return 返回内部加黑项
	 */
	public  List<BlackGraylistOptionEx> getCoborrower(VerifyParamEx param) {
		Map<String, String> map =new HashMap<String, String>();
		// 借款编码
		map.put("loanCode", param.getLoanCode());
		// 内部
		map.put("inside", AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
		// 类型
		map.put("type", BlackGreyList.BLACK_LIST.getCode());
		// 获取共借人
		List<CoborrowerOptionEx> coborrowerOption = antifraudJudgeDao.getCoborrowerOption(map);
		// 外部加黑项
		List<BacklistAll> oldBlackListOption = antifraudJudgeDao.getOldBlackGrayListOption(map);
		List<BlackGraylistOptionEx> blackGrayList = new ArrayList<BlackGraylistOptionEx>();
		BlackGraylistOptionEx blackGraylistOptionEx = null;
		List<ExternalBlackListEx> externalBlackList = null;
		ExternalBlackListEx ExternalBlackListEx = null;
		// 对共借人进行循环操作
		for (int i = 0; i < coborrowerOption.size(); i++) {
			externalBlackList = new ArrayList<ExternalBlackListEx>();
			blackGraylistOptionEx = new BlackGraylistOptionEx();
			// 共借人id
			String id = coborrowerOption.get(i).getId();
			// 共借人姓名
			String coboName = coborrowerOption.get(i).getCoboName();
			// 共借人姓名
			blackGraylistOptionEx.setName(coboName);
			// 借款人类型
			blackGraylistOptionEx.setType(LoanManFlag.COBORROWE_LOAN.getCode());
			for (int j = 0; j < oldBlackListOption.size(); j++) {
				// 判定共借人id是否相等
				if(id.equals(oldBlackListOption.get(j).getrCustomerCoborrowerId())){
					ExternalBlackListEx = new ExternalBlackListEx();
					// 黑名单内容
					ExternalBlackListEx.setBlackListMsg(oldBlackListOption.get(j).getBlackMsg());
					// 黑名单类型
					ExternalBlackListEx.setBlackListType(oldBlackListOption.get(j).getDictBlackType());
					externalBlackList.add(ExternalBlackListEx);
				}
			}
			blackGraylistOptionEx.setExternalBlackListEx(externalBlackList);
			blackGrayList.add(blackGraylistOptionEx);
		}
		return blackGrayList;
	}

	/**
	 * 根据 借款编码 获取 反欺诈 主借人内部 加黑项 回显
	 * 2016年4月27日
	 * By wanglidong
	 * @param param
	 * @return
	 */
	public List<BacklistAll> getOldBlackListOption(VerifyParamEx param) {
		Map<String, String> map =new HashMap<String, String>();
		// 借款编码
		map.put("loanCode", param.getLoanCode());
		// 内部外部
		map.put("inside", AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
		// 类型
		map.put("type", BlackGreyList.BLACK_LIST.getCode());
		List<BacklistAll> oldBlackListOption = antifraudJudgeDao.getOldBlackGrayListOption(map);
		return oldBlackListOption;
	}
	
	
	/**
	 * 从黑灰名单库中 获取反欺诈 内部 加灰项 并区分主借人 与 共借人 回显
	 * 2016年3月30日
	 * By wanglidong
	 * @param param
	 * @return
	 */
	public List<BlackGraylistOptionEx> getOldGrayListOption(VerifyParamEx param) {
		Map<String, String> map =new HashMap<String, String>();
		// 借款编码
		map.put("loanCode", param.getLoanCode());
		// 内部
		map.put("inside", AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
		// 类型
		map.put("type", BlackGreyList.GREY_LIST.getCode());
		// 共借人
		List<CoborrowerOptionEx> coborrowerOption = antifraudJudgeDao.getCoborrowerOption(map);
		// 内部加灰项
		List<BacklistAll> oldGrayListOption = antifraudJudgeDao.getOldBlackGrayListOption(map);
		List<BlackGraylistOptionEx> blackGrayList = new ArrayList<BlackGraylistOptionEx>();
		BlackGraylistOptionEx blackGraylistOptionEx = null;
		List<ExternalBlackListEx> externalBlackList = new ArrayList<ExternalBlackListEx>();
		ExternalBlackListEx ExternalBlackListEx = null;
		// 对共借人进行循环操作，将加灰项放入对应的共借人内
		for (int i = 0; i < coborrowerOption.size(); i++) {
			blackGraylistOptionEx = new BlackGraylistOptionEx();
			String id = coborrowerOption.get(i).getId();
			String coboName = coborrowerOption.get(i).getCoboName();
			blackGraylistOptionEx.setName(coboName);
			blackGraylistOptionEx.setType(LoanManFlag.COBORROWE_LOAN.getCode());
			for (int j = 0; j < oldGrayListOption.size(); j++) {
				// 判断共借人id相等
				if(id.equals(oldGrayListOption.get(j).getrCustomerCoborrowerId())){
					ExternalBlackListEx = new ExternalBlackListEx();
					ExternalBlackListEx.setBlackListMsg(oldGrayListOption.get(j).getBlackMsg());
					ExternalBlackListEx.setBlackListType(oldGrayListOption.get(j).getDictBlackType());
					externalBlackList.add(ExternalBlackListEx);
				}
			}
			blackGraylistOptionEx.setExternalBlackListEx(externalBlackList);
			blackGrayList.add(blackGraylistOptionEx);
		}
		return blackGrayList;
	}
	
	/**
	 * 从黑灰名单库中 获取反欺诈 内部 加灰项
	 * 2016年3月30日
	 * By wanglidong
	 * @param param
	 * @return
	 */
	public List<BacklistAll> getOldGrayList(VerifyParamEx param) {
		Map<String, String> map =new HashMap<String, String>();
		// 借款编码
		map.put("loanCode", param.getLoanCode());
		// 内部
		map.put("inside", AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
		// 类型
		map.put("type", BlackGreyList.GREY_LIST.getCode());
		List<BacklistAll> oldGrayListOption = antifraudJudgeDao.getOldBlackGrayListOption(map);
		return oldGrayListOption;
	}

	
	/**
	 * 获取反欺诈判定所需的字典数据
	 * 2016年2月1日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 提报信息
	 */
	public AntifraudDictListEx getAntifraudDictList() {
		// 0：黑名单，1：灰名单，2：清白件，3：退回
		List<Dict> blackGreyEList = DictUtils.getDictList(DictionaryConstants.BLACK_GREY_LIST_TYPE);
		// 黑名单
		String black = BlackGreyList.BLACK_LIST.getCode();
		// 灰名单
		String gary = BlackGreyList.GREY_LIST.getCode();
		// 清白件
		String white = BlackGreyList.INNOCENT_CASE.getCode();
		// 退回
		String back = BlackGreyList.FALL_BACK.getCode();
		// 系统提报类型
		String reportType = AfraudReportType.SYS_REPORT.getCode();
		AntifraudDictListEx antifraudDictList = new AntifraudDictListEx();
		antifraudDictList.setBack(back);
		antifraudDictList.setBlackGreyEList(blackGreyEList);
		antifraudDictList.setGary(gary);
		antifraudDictList.setBlack(black);
		antifraudDictList.setReportType(reportType);
		antifraudDictList.setWhite(white);
		return antifraudDictList;
	}

	/**
	 * 获取反欺诈决策页面初始化数据
	 * 2016年4月26日
	 * By wanglidong
	 * @param loanCode
	 * @return 
	 */
	public AntiFraudJudgeEx getAfraudJudgeInitData(String loanCode) {
		AntiFraudJudgeEx antiFraudJudgeEx = new AntiFraudJudgeEx();
		// 字典数据
		AntifraudDictListEx antifraudDict = getAntifraudDictList();
		// 获取提报类型
		AntifraudReport antifraudReport = getAntifraudReportType(loanCode);
		// 欺诈案件编号
		String afraudCode = getfraudCode(loanCode);
		// 获取外部拉黑项
		List<BacklistAll> blackListAll = getBlackListAll(loanCode);
		// 灰名单二级拒接码
		List<Refuse> grayTwoLevel = antifraudJudgeDao.getGrayTwoLevel();
		// 黑名单三级拒接码
		List<Refuse> blackThreeLevel = antifraudJudgeDao.getBlackThreeLevel();
		// 黑名单二级拒接码
		List<Refuse> blackTwoLevel = antifraudJudgeDao.getBlackTwoLevel();
		antiFraudJudgeEx.setThreeBlackRefuseCode(blackThreeLevel);
		antiFraudJudgeEx.setTwoBlackRefuseCode(blackTwoLevel);
		antiFraudJudgeEx.setTwoGrayRefuseCode(grayTwoLevel);
		antiFraudJudgeEx.setAntifraudDict(antifraudDict);
		antiFraudJudgeEx.setAntifraudReport(antifraudReport);
		antiFraudJudgeEx.setJudgeCaseCode(afraudCode);
		antiFraudJudgeEx.setBlackListAll(blackListAll);
		return antiFraudJudgeEx;
	}

	/**
	 * 反欺诈决策页面【回显】
	 * 2016年4月26日
	 * By wanglidong
	 * @param loanCode
	 * @return
	 */
	public AntiFraudJudgeEx getAfraudJudgeViewData(String loanCode) {
		AntiFraudJudgeEx antiFraudJudgeEx =getAntiFraudJudgeView(loanCode);
		if(antiFraudJudgeEx == null){
			antiFraudJudgeEx = new AntiFraudJudgeEx();
		}
		// 字典数据
		AntifraudDictListEx antifraudDict = getAntifraudDictList();
		// 获取外部拉黑信息
		List<BacklistAll> blackListAll = getBlackListAll(loanCode);
		// 黑名单三级拒接码
		List<Refuse> grayTwoLevel = antifraudJudgeDao.getGrayTwoLevel();
		// 黑名单二级拒接码
		List<Refuse> blackThreeLevel = antifraudJudgeDao.getBlackThreeLevel();
		// 灰名单二级拒接码
		List<Refuse> blackTwoLevel = antifraudJudgeDao.getBlackTwoLevel();
		antiFraudJudgeEx.setThreeBlackRefuseCode(blackThreeLevel);
		antiFraudJudgeEx.setTwoBlackRefuseCode(blackTwoLevel);
		antiFraudJudgeEx.setTwoGrayRefuseCode(grayTwoLevel);
		antiFraudJudgeEx.setAntifraudDict(antifraudDict);
		antiFraudJudgeEx.setBlackListAll(blackListAll);
		return antiFraudJudgeEx;
	}

}
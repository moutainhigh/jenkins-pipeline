package com.creditharmony.approve.credit.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.common.dao.CreditReportDao;
import com.creditharmony.approve.common.entity.CreditReportRisk;
import com.creditharmony.approve.credit.dao.CreditOccupationInfoDao;
/*import com.creditharmony.approve.credit.dao.CreditReportDao;*/
import com.creditharmony.approve.credit.dao.CreditReportDetailedDao;
import com.creditharmony.approve.credit.dao.CreditliveInfoDao;
import com.creditharmony.approve.credit.entity.CreditLiveInfo;
import com.creditharmony.approve.credit.entity.CreditOccupationInfo;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
import com.creditharmony.approve.credit.entity.ex.DetailedParamEx;
import com.creditharmony.approve.verify.dao.LoanCoborrowerDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.entity.LoanCoborrower;
import com.creditharmony.approve.verify.entity.LoanCustomer;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.service.CoreManager;

/**
 * 个人征信详版
 * @Class Name CreditDetailedInfoService
 * @author 李文勇
 * @Create In 2016年2月18日
 */
@Service
public class CreditDetailedInfoService extends CoreManager<CreditReportDetailedDao,CreditReportDetailed>{
	
	@Autowired
	private LoanCoborrowerDao loanCoborrowerDao;	
	@Autowired
	private LoanCustomerDao loanCustomerDao;
	@Autowired
	private CreditReportDetailedDao creditReportDetailedDao;
	@Autowired
	private CreditliveInfoDao creditliveInfoDao;
	@Autowired
	private CreditOccupationInfoDao creditOccupationInfoDao;
	@Autowired
	private CreditReportDao creditReportDao;
	
	
	/**
	 * 保存数据
	 * 2016年2月18日
	 * By 李文勇
	 * @param param
	 * @return none
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public void saveData(DetailedParamEx param) throws IllegalAccessException, InvocationTargetException{
		if(param != null){
			String detailID = "";
			// 根据id判断是否该数据已存在数据库
			if(StringUtils.isNotEmpty(param.getId())){// 更新数据
				detailID = param.getId();
				CreditReportDetailed creditReportDetailed = new CreditReportDetailed();
				BeanUtils.copyProperties(creditReportDetailed,param);
				creditReportDetailed.preUpdate();
				creditReportDetailedDao.updataById(creditReportDetailed);
			}else{// ID为空，说明为第一次保存用insert
				CreditReportDetailed creditReportDetailed = new CreditReportDetailed();
				BeanUtils.copyProperties(creditReportDetailed,param);
				creditReportDetailed.setIsNewRecord(false);
				creditReportDetailed.preInsert();
				detailID = creditReportDetailed.getId();
				creditReportDetailedDao.saveData(creditReportDetailed);
			}
			// 居住信息
			if(param.getLiveList() != null && param.getLiveList().size() > 0 && StringUtils.isNotEmpty(detailID)){
				List<CreditLiveInfo> liveList = param.getLiveList();
				for( int i = 0; i < liveList.size(); i++ ){
					if( StringUtils.isNotEmpty(liveList.get(i).getId()) ){// 该数据已存在数据库，进行更新
						CreditLiveInfo creditLiveInfo = liveList.get(i);
						creditLiveInfo.preUpdate();
						creditliveInfoDao.updataById(creditLiveInfo);
					}else{// 该数据未存在数据库，进行添加保存
						CreditLiveInfo creditLiveInfo = liveList.get(i);
						creditLiveInfo.setIsNewRecord(false);
						creditLiveInfo.setRelationId(detailID);// 居住信息关联ID
						creditLiveInfo.preInsert();
						creditliveInfoDao.saveData(creditLiveInfo);
					}
				}
			}
			// 职业信息
			if(param.getOccupationList() != null && param.getOccupationList().size() > 0 && StringUtils.isNotEmpty(detailID)){
				List<CreditOccupationInfo> occupationList = param.getOccupationList();
				for( int w = 0; w < occupationList.size(); w++ ){
					if(StringUtils.isNotEmpty(occupationList.get(w).getId())){// 该数据已存在数据库，进行更新
						CreditOccupationInfo creditOccupationInfo = occupationList.get(w);
						creditOccupationInfo.preUpdate();
						creditOccupationInfoDao.updataById(creditOccupationInfo);
					}else{// 该数据未存在数据库，进行添加保存
						CreditOccupationInfo creditOccupationInfo = occupationList.get(w);
						creditOccupationInfo.setIsNewRecord(false);
						creditOccupationInfo.setRelationId(detailID);
						creditOccupationInfo.preInsert();
						creditOccupationInfoDao.saveData(creditOccupationInfo);
					}
				}
			}
		}
	}
	
	/**
	 * 查询征信报告客户姓名
	 * 2016年5月3日
	 * By 王浩
	 * @param creditReportSimple
	 * @return 
	 */
	public String getCustomerName(CreditReportRisk creditReportRisk) {
		if (LoanManFlag.MAIN_LOAN.getCode().equals(creditReportRisk.getDictCustomerType())) {
			LoanCustomer loanCustomer = this.loanCustomerDao.getLoanCustomer(creditReportRisk.getrId());
			return loanCustomer.getCustomerName();
		} else if(LoanManFlag.COBORROWE_LOAN.getCode().equals(creditReportRisk.getDictCustomerType())) {
			LoanCoborrower coborrower = this.loanCoborrowerDao.getCoborrower(creditReportRisk.getrId());
			return coborrower.getCoboName();
		}
		return null;
	}
	
	/**
	 * 查询数据（个人征信详版，居住信息，职业信息）
	 * 2016年2月18日
	 * By 李文勇
	 * @param param
	 * @return DetailedParamEx
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public DetailedParamEx showData(CreditReportDetailed param) throws IllegalAccessException, InvocationTargetException{
		
		DetailedParamEx detailedParamEx = new DetailedParamEx();
		CreditReportDetailed detailInfo = creditReportDetailedDao.getIdByParam(param);
		if (detailInfo != null) {
			detailInfo.setSex(DictCache.getInstance().getDictLabel(DictionaryConstants.DICT_GENDER, detailInfo.getSex()));
			detailInfo.setCertType(DictCache.getInstance().getDictLabel("jk_certificate_type", detailInfo.getCertType()));
			detailInfo.setHighestEducation(DictCache.getInstance().getDictLabel("jk_degree", detailInfo.getHighestEducation()));
			detailInfo.setMarryStatus(DictCache.getInstance().getDictLabel("jk_marriage", detailInfo.getMarryStatus()));
			detailInfo.setMateCertType(DictCache.getInstance().getDictLabel("jk_certificate_type", detailInfo.getMateCertType()));
		}
		if(detailInfo != null && StringUtils.isNotEmpty(detailInfo.getId())){
			BeanUtils.copyProperties(detailedParamEx,detailInfo);// 个人基本信息
			// 居住信息
			List<CreditLiveInfo> resultLiveList = creditliveInfoDao.getByParam(detailInfo.getId());
			if(resultLiveList != null && resultLiveList.size() >0){
				detailedParamEx.setLiveList(resultLiveList);
			}
			// 职位信息
			List<CreditOccupationInfo> resultOccList = creditOccupationInfoDao.getByParam(detailInfo.getId());
			if(resultOccList != null && resultOccList.size() >0){
				detailedParamEx.setOccupationList(resultOccList);
			}
		}
		return detailedParamEx;
	}
	
	/**
	 * 删除居住信息
	 * 2016年2月19日
	 * By 李文勇
	 * @param param
	 * @return 操作成功数
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public int removeReportHouse(CreditLiveInfo param){
		int result = creditliveInfoDao.deleteData(param);
		return result;
	}
	
	/**
	 * 删除职位信息
	 * 2016年2月19日
	 * By 李文勇
	 * @param param
	 * @return 操作成功数
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public int removeReportWork(CreditOccupationInfo param){
		int result = creditOccupationInfoDao.deleteData(param);
		return result;
	}
	
	/**
	 * 根据借款编号获取全部征信核查数据
	 * 2016年3月15日
	 * By 李文勇
	 * @param param
	 * @return
	 */
	public List<CreditReportRisk> getCreditReportDetailedByCode(CreditReportRisk param){		
		List<CreditReportRisk> result = creditReportDao.getPersonCreditReportDetailedByCode(param);
		return result;
	}
	
	/**
	 * 根据借款编号和客户编号，查询同一个客户唯一一条记录
	 * 2016年3月15日
	 * By 李文勇
	 * @param param
	 * @return
	 */
	public List<CreditReportRisk> getSingleCreditDetailedByCustomer(CreditReportRisk param){		
		List<CreditReportRisk> result = creditReportDao.getSingleCreditDetailedByCustomer(param);
		return result;
	}
	
	/**
	 * 获取主借人身份证
	 * 2016年5月26日
	 * By 李文勇
	 * @param loanCode
	 * @return
	 */
	public LoanCustomer getCustomer(String loanCode){
		LoanCustomer bean = new LoanCustomer();
		bean.setLoanCode(loanCode);
		LoanCustomer loanCustomer = loanCustomerDao.viewGetByLoanCode(bean);
		
		return loanCustomer;
	}
	
	
	public LoanCoborrower selectCoboNameAndCertNum(String loanCode, String rId){
		
		LoanCoborrower bean = new LoanCoborrower();
		bean.setLoanCode(loanCode);
		bean.setId(rId);
		LoanCoborrower loanCoborrower =loanCoborrowerDao.viewGetByLoanCode(bean);
		
		return loanCoborrower;
	}
	
}

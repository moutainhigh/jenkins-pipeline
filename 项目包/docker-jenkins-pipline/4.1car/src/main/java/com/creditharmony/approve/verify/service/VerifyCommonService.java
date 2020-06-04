package com.creditharmony.approve.verify.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.antifraud.dao.AntifraudOffendInfoDao;
import com.creditharmony.approve.antifraud.dao.AntifraudReportDao;
import com.creditharmony.approve.localnet.dao.InnerApplyContrastDao;
import com.creditharmony.approve.localnet.dao.InnerCustomerHisDao;
import com.creditharmony.approve.localnet.dao.InnerRepeatDao;
import com.creditharmony.approve.localnet.dao.LoanRemarkDao;
import com.creditharmony.approve.localnet.entity.InnerApplyContrast;
import com.creditharmony.approve.localnet.entity.InnerCustomerHis;
import com.creditharmony.approve.localnet.entity.InnerRepeat;
import com.creditharmony.approve.localnet.entity.LoanRemark;
import com.creditharmony.approve.localnet.entity.ex.LoanInfoEx;
import com.creditharmony.approve.verify.constants.ApplicationConstants;
import com.creditharmony.approve.verify.dao.AuditResultDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.dao.ReconsiderApplyDao;
import com.creditharmony.approve.verify.entity.AuditResult;
import com.creditharmony.approve.verify.entity.LoanCustomer;
import com.creditharmony.approve.verify.entity.ReconsiderApply;
import com.creditharmony.approve.verify.entity.ex.AntiFraudResultEx;
import com.creditharmony.approve.verify.entity.ex.GreyListEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.util.GetAgeUtil;
import com.creditharmony.core.approve.type.BlackGreyList;
import com.creditharmony.core.common.type.CertificateType;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.users.service.OrgManager;
/**
 * 抽出内网审核 决策页面中共同的部分
 * @Class Name VerifyCommonService
 * @author 刘燕军
 * @Create In 2015年12月4日
 */
@Service
public class VerifyCommonService extends OrgManager{
	@Autowired
	private LoanInfoDao infoDao;
	@Autowired	
	private LoanRemarkDao loanRemarkDao;
	@Autowired
	private InnerApplyContrastDao constrastDao;
	@Autowired
	private InnerCustomerHisDao  hisDao;
	@Autowired
	private InnerRepeatDao repeatDao;
	@Autowired
	private AntifraudReportDao antifraudReportDao;
	@Autowired
	private AntifraudOffendInfoDao antifraudOffendInfoDao;
	@Autowired
	private ReconsiderApplyDao reconsiderApplyDao;
	@Autowired
	private AuditResultDao auditResultDao;
	@Autowired
	private LoanCustomerDao loanCustomerDao;
	
	/**
	 * 通过借款编号获取复议原因
	 * 2015年12月25日
	 * By 刘燕军
	 * @param loanCode
	 * @return 复议原因
	 */
	public ReconsiderApply getReconsiderApply(String loanCode){
		return reconsiderApplyDao.getByLoanCode(loanCode);
	}
	
	/**
	 * 通过借款编号查询申请信息的实体Ex
	 * 2015年12月4日
	 * By 刘燕军
	 * @param loanCode
	 * @return 申请信息
	 */
	public LoanInfoEx getLoanInfoEx(VerifyParamEx param){
		
		// 借款编码
		String loanCode = param.getLoanCode().trim();
		// 获取新版旧版标识
		String newOrOldFlag = this.getOldornewFlag(loanCode);
		
		// 判断为新版申请表
		if (newOrOldFlag.equals(ApplicationConstants.LOANINFO_NEW_FLAG)) {
			// 共借人类型
			if (null != param.getType() && param.getType().equals(LoanManFlag.COBORROWE_LOAN.getCode())) {
				return infoDao.findCoborroweLoanInfoEx(param);
			}
		}
		
		return infoDao.findLoanInfoEx(param);
	}
	
	/**
	 * 通过借款编号查询备注信息
	 * 2015年12月4日
	 * By 刘燕军
	 * @param loanCode
	 * @return 备注信息
	 */
	public List<LoanRemark> getLoanRemark(String loanCode){		
		return loanRemarkDao.findListByLoanCode(loanCode);
	}
	
	/**
	 * 申请信息历史对比异常点
	 * 2015年12月4日
	 * By 刘燕军
	 * @param loanCode
	 * @return 申请信息历史对比异常点
	 */
	public List<InnerApplyContrast> getInnerApplyContrast(VerifyParamEx param){		
		return constrastDao.findListByLoanCode(param);
	}
	
	/**
	 * 历史归户信息
	 * 2015年12月4日
	 * By 刘燕军
	 * @param loanCode
	 * @return 历史归户信息
	 */
	public List<InnerCustomerHis> getCustomerHis(VerifyParamEx param){		
		return hisDao.findListByLoanCode(param);
	}
	
	/**
	 * 反欺诈反馈信息
	 * 2015年12月4日
	 * By 刘燕军
	 * @param loanCode
	 * @return 反欺诈反馈信息
	 */
	public List<AntiFraudResultEx> getAntiFraudResultEx(VerifyParamEx param){		
		return antifraudReportDao.findListByLoanCode(param);
	}
	
	/**
	 * 	查重信息
	 * 2015年12月4日
	 * By 刘燕军
	 * @param loanCode
	 * @return 查重信息
	 */
	public List<InnerRepeat> getInnerRepeat(VerifyParamEx param){		
		return repeatDao.findListByLoanCode(param);
	}
	
	/**
	 * 	查重信息（只获取异常信息）
	 * 2015年12月4日
	 * By 刘燕军
	 * @param loanCode
	 * @return 异常的查重信息
	 */
	public List<InnerRepeat> getExceptionInnerRepeat(VerifyParamEx param){		
		return repeatDao.findExceptions(param);
	}
	
	/**
	 * 查黑信息
	 * 2015年12月4日
	 * By 刘燕军
	 * @param loanCode
	 * @return 黑名单集合
	 */
	public List<GreyListEx> getBlackList(String loanCode){
		
		return antifraudOffendInfoDao.selectByLoanCode(loanCode,BlackGreyList.BLACK_LIST.getCode());
	}
	
	/**
	 * 查灰信息
	 * 2015年12月4日
	 * By 刘燕军
	 * @param param
	 * @return 灰名单集合
	 */
	public List<GreyListEx> getGreyListEx(String loanCode){
		
		return antifraudOffendInfoDao.selectByLoanCode(loanCode,BlackGreyList.GREY_LIST.getCode());
	}
	/**
	 * 获取判定结果
	 * 2016年1月27日
	 * By 刘燕军
	 * @param param
	 * @return 对应的判定结果
	 */
	public AuditResult getAuditResult(VerifyParamEx param){
		return auditResultDao.getAuditResult(param);
	}
	
    /**
     * 通过借款编号获取跳转到新旧版申请表页面的标志
     * 2016年9月8日
     * By 张虎
     * @param loanCode
     * @return 跳转到新旧版申请表页面的标志
     */
    public String getOldornewFlag(String loanCode) {
    	String oldornewFlag = this.infoDao.selectOldornewFlag(loanCode);
    	// 为空或1表示跳到新版
    	if (!StringUtils.isNotEmpty(oldornewFlag)) {
    		// 新版
    		return ApplicationConstants.LOANINFO_NEW_FLAG;
    		
    	} else if (oldornewFlag.equals(ApplicationConstants.LOANINFO_NEW_FLAG)) {
    		// 新版
    		return ApplicationConstants.LOANINFO_NEW_FLAG;
    	} else if (oldornewFlag.equals(ApplicationConstants.LOANINFO_OLD_FLAG)) {
    		// 旧版
    		return ApplicationConstants.LOANINFO_OLD_FLAG;
    	} 

		// 新版
		return ApplicationConstants.LOANINFO_NEW_FLAG; 
    	
    }
    
    /**
     * 通过借款编号获取主借人、法人保证人、配偶的身份证号
     * 2016年10月22日
     * By Wangyanna
     * @param loanCode
     * @return 
     */
    public Map<String,String> getCertNumByLoanCode(String loanCode){
    	return loanCustomerDao.getCertNumByLoanCode(loanCode);
    }
    
    //获取客户年龄,主借人
    public String getCustomerAge(String loanCode) throws ParseException{   	
		String customerAge=null;
		LoanCustomer loanCustomer = new LoanCustomer();
		loanCustomer.setLoanCode(loanCode);
		loanCustomer = loanCustomerDao.viewGetByLoanCode(loanCustomer);
		if(loanCustomer != null ){
			if(CertificateType.SFZ.getCode().equals(loanCustomer.getDictCertType())
					|| CertificateType.HKB.getCode().equals(loanCustomer.getDictCertType()) &&
					StringUtils.isNotEmpty(loanCustomer.getCustomerCertNum())){
				String birth = loanCustomer.getCustomerCertNum().substring(6,14);// 年
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(birth));
				Date birthday = c.getTime();
				// 调用util根据出生日期计算年龄
				customerAge = GetAgeUtil.countAge(birthday);		
			}
		}		
    	return customerAge;
    }
}

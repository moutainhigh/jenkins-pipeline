package com.creditharmony.approve.common.service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.verify.dao.ContactDao;
import com.creditharmony.approve.verify.dao.LoanBankDao;
import com.creditharmony.approve.verify.dao.LoanCoborrowerDao;
import com.creditharmony.approve.verify.dao.LoanCompanyDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.dao.LoanMateDao;
import com.creditharmony.approve.verify.entity.Contact;
import com.creditharmony.approve.verify.entity.LoanBank;
import com.creditharmony.approve.verify.entity.LoanCoborrower;
import com.creditharmony.approve.verify.entity.LoanCompany;
import com.creditharmony.approve.verify.entity.LoanCustomer;
import com.creditharmony.approve.verify.entity.LoanInfo;
import com.creditharmony.approve.verify.entity.LoanMate;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.view.ViewDetailView;
import com.creditharmony.core.lend.type.LoanManFlag;

/**
 * 获取查看弹出画面信息
 * 
 * @Class Name PopViewNewService
 * @author luojunping
 * @Create In 2016年10月10日
 * @update in 2016-10-10
 */
@Service
public class PopViewNewService {

	@Autowired
	private LoanInfoDao loanInfoDao;
	@Autowired
	private LoanCompanyDao loanCompanyDao;
	@Autowired
	private LoanCustomerDao loanCustomerDao;
	@Autowired
	private ContactDao contactDao;
	@Autowired
	private LoanBankDao loanBankDao;
	@Autowired
	private LoanCoborrowerDao loanCoborrowerDao;
	@Autowired
	private LoanMateDao loanMateDao;

	/**
	 * 获取查看弹出画面信息 2015年10月10日
	 * 
	 * @param loanCode
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ParseException
	 */
	public Map<String, Object> showDetail(VerifyParamEx verifyParamEx)
			throws IllegalAccessException, InvocationTargetException, ParseException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ViewDetailView viewDetailView = new ViewDetailView();
		// 新版申请表-进件信息和借款意愿（区分主借人和自然人保证人）
		LoanInfo loanInfo = new LoanInfo();
		if (LoanManFlag.MAIN_LOAN.getCode().equals(verifyParamEx.getType())) {
			loanInfo = loanInfoDao.getLoanInfoByLoanCodeNew(verifyParamEx.getLoanCode());
		} else {
			Map<String, String> mapp = new HashMap<String, String>();
			mapp.put("loanCode", verifyParamEx.getLoanCode());
			mapp.put("rid", verifyParamEx.getRelId());
			loanInfo = loanInfoDao.getLoanInfoCobByLoanCodeNew(mapp);
		}
		if (loanInfo != null) {
			BeanUtils.copyProperties(viewDetailView, loanInfo);
		}
		// 新版申请表-个人基本信息（区分主借人和自然人保证人）
		LoanCustomer loanCustomer = this.getCustomerInfo(verifyParamEx);
		if (loanCustomer != null) {
			BeanUtils.copyProperties(viewDetailView, loanCustomer);
		}
		// 新版申请表-工作信息和法人保证人信息
		LoanCompany companyInfo = this.getCompanyInfo(verifyParamEx);
		if (companyInfo != null) {
			BeanUtils.copyProperties(viewDetailView, companyInfo);
		}
		// 新版申请表-联系人信息
		List<Contact> contactInfo = this.getContactInfo(verifyParamEx);
		if (contactInfo != null && contactInfo.size() > 0) {
			map.put("contact", contactInfo);
		}
		// 新版申请表-银行卡信息
		if (LoanManFlag.MAIN_LOAN.getCode().equals(verifyParamEx.getType())) {
			List<LoanBank> loanBank = loanBankDao.viewGetByLoanCode(verifyParamEx.getLoanCode());
			if (loanBank != null && loanBank.size() > 0) {
				map.put("loanBank", loanBank);
			}
		}
		// 新版申请表-配偶信息
		LoanMate loanMate = new LoanMate();
		loanMate.setLoanCode(verifyParamEx.getLoanCode());
		loanMate.setLoanCustomterType(verifyParamEx.getType());
		LoanMate loanMateResult = loanMateDao.viewGetByLoanCode(loanMate);
		if (loanMateResult != null) {
			BeanUtils.copyProperties(viewDetailView, loanMateResult);
			viewDetailView.setMateCertType(loanMateResult.getDictCertType());
		}
		map.put("ApplyInformation", viewDetailView);
		return map;
	}

	/**
	 * 职位信息 2016年10月10日
	 * 
	 * @param verifyParamEx
	 * @return
	 */
	private LoanCompany getCompanyInfo(VerifyParamEx verifyParamEx) {
		LoanCompany loanCompany = new LoanCompany();
		// 借款编号
		loanCompany.setLoanCode(verifyParamEx.getLoanCode());
		// 关联类型
		loanCompany.setCompCustomterType(verifyParamEx.getType());
		// 关联ID
		loanCompany.setrId(verifyParamEx.getRelId());
		LoanCompany companyInfo = loanCompanyDao.viewGetByLoanCodeNew(loanCompany);
		return companyInfo;
	}

	/**
	 * 获取联系人信息 2015年10月10日
	 * 
	 * @param verifyParamEx
	 * @return
	 */
	private List<Contact> getContactInfo(VerifyParamEx verifyParamEx) {
		Contact contact = new Contact();
		// 借款编号
		contact.setLoanCode(verifyParamEx.getLoanCode());
		// 关联类型
		contact.setLoanCustomterType(verifyParamEx.getType());
		// 关联ID
		contact.setrId(verifyParamEx.getRelId());
		List<Contact> contactInfo = contactDao.viewGetByLoanCodeNew(contact);
		return contactInfo;
	}

	/**
	 * 获取客户信息 2016年10月10日
	 * 
	 * @param verifyParamEx
	 */
	private LoanCustomer getCustomerInfo(VerifyParamEx verifyParamEx) {
		LoanCustomer loanCustomer = new LoanCustomer();
		if (LoanManFlag.MAIN_LOAN.getCode().equals(verifyParamEx.getType())) {
			LoanCustomer bean = new LoanCustomer();
			bean.setLoanCode(verifyParamEx.getLoanCode());
			loanCustomer = loanCustomerDao.viewGetByLoanCodeNew(bean);
		} else if (LoanManFlag.COBORROWE_LOAN.getCode().equals(verifyParamEx.getType())) {
			LoanCoborrower bean = new LoanCoborrower();
			bean.setLoanCode(verifyParamEx.getLoanCode());
			bean.setId(verifyParamEx.getRelId());
			LoanCoborrower loanCoborrower = loanCoborrowerDao.viewGetByLoanCodeNew(bean);
			// 数据从共借人实体转移到主借人实体中
			loanCustomer.setCustomerName(loanCoborrower.getCoboName());
			loanCustomer.setDictCertType(loanCoborrower.getDictCertType());
			loanCustomer.setCustomerCertNum(loanCoborrower.getCoboCertNum());
			loanCustomer.setCustomerSex(loanCoborrower.getCoboSex());
			loanCustomer.setCustomerHaveChildren(loanCoborrower.getCoboHaveChildFlag());
			loanCustomer.setDictEducation(loanCoborrower.getDictEducation());
			loanCustomer.setCustomerTel(loanCoborrower.getCoboFamilyTel());
			loanCustomer.setDictMarryStatus(loanCoborrower.getDictMarryStatus());
			loanCustomer.setCustomerPhoneFirst(loanCoborrower.getCoboMobile());
			loanCustomer.setCustomerPhoneSecond(loanCoborrower.getCoboMobile2());
			loanCustomer.setCustomerFirtArriveYear(loanCoborrower.getCustomerFirtArriveYear());
			loanCustomer.setCustomerHouseHoldProperty(loanCoborrower.getCustomerHouseHoldProperty());
			loanCustomer.setCustomerEmail(loanCoborrower.getCoboEmail());
			// 现居 省，市，区，详细地址
			loanCustomer.setCustomerLiveProvince(loanCoborrower.getCoboLiveingProvince());
			loanCustomer.setCustomerLiveCity(loanCoborrower.getCoboLiveingCity());
			loanCustomer.setCustomerLiveArea(loanCoborrower.getCoboLiveingArea());
			loanCustomer.setCustomerAddress(loanCoborrower.getCoboNowAddress());
			// 户籍 省，市，区，详细地址
			loanCustomer.setCustomerRegisterProvince(loanCoborrower.getCoboHouseholdProvince());
			loanCustomer.setCustomerRegisterCity(loanCoborrower.getCoboHouseholdCity());
			loanCustomer.setCustomerRegisterArea(loanCoborrower.getCoboHouseholdArea());
			loanCustomer.setCustomerRegisterAddress(loanCoborrower.getCoboHouseholdAddress());
			// 新版申请表新增字段
			loanCustomer.setCustomerFamilySupport(loanCoborrower.getSupportNum());
			loanCustomer.setCustomerChildrenCount(String.valueOf(loanCoborrower.getChildrenNum()));
			loanCustomer.setPersonalYearIncome(loanCoborrower.getPersonalYearIncome());
			loanCustomer.setHomeMonthIncome(loanCoborrower.getHomeMonthIncome());
			loanCustomer.setHomeMonthPay(loanCoborrower.getHomeMonthPay());
			loanCustomer.setHomeTotalDebt(loanCoborrower.getHomeTotalDebt());
			loanCustomer.setCreditUsername(loanCoborrower.getCreditUserName());
			loanCustomer.setCreditPassword(loanCoborrower.getCreditPassword());
			loanCustomer.setCreditAuthCode(loanCoborrower.getCreditAuthCode());
			loanCustomer.setCustomerQq(loanCoborrower.getCoboQq());
			loanCustomer.setCustomerWeibo(loanCoborrower.getCoboWeibo());
			loanCustomer.setCustomerFirstLivingDay(loanCoborrower.getCustomerFirstLivingDay());
			loanCustomer.setSocialSecurityNumber(loanCoborrower.getSocialSecurityNumber());
		}
		return loanCustomer;
	}

}

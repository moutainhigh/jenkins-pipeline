package com.creditharmony.approve.common.service;


import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.verify.dao.ContactDao;
import com.creditharmony.approve.verify.dao.CustomerLivingsDao;
import com.creditharmony.approve.verify.dao.LoanBankDao;
import com.creditharmony.approve.verify.dao.LoanCoborrowerDao;
import com.creditharmony.approve.verify.dao.LoanCompanyDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.dao.LoanHouseDao;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.dao.LoanMateDao;
import com.creditharmony.approve.verify.entity.Contact;
import com.creditharmony.approve.verify.entity.CustomerLivings;
import com.creditharmony.approve.verify.entity.LoanBank;
import com.creditharmony.approve.verify.entity.LoanCoborrower;
import com.creditharmony.approve.verify.entity.LoanCompany;
import com.creditharmony.approve.verify.entity.LoanCustomer;
import com.creditharmony.approve.verify.entity.LoanHouse;
import com.creditharmony.approve.verify.entity.LoanInfo;
import com.creditharmony.approve.verify.entity.LoanMate;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.util.GetAgeUtil;
import com.creditharmony.approve.verify.view.ViewDetailView;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.CertificateType;
import com.creditharmony.core.lend.type.LoanManFlag;

/**
 * 获取查看弹出画面信息
 * @Class Name ViewDetailService
 * @author liwenyong
 * @Create In 2015年11月28日
 */
@Service
public class PopViewService {

	@Autowired
	private LoanInfoDao loanInfoDao;
	@Autowired
	private LoanCompanyDao loanCompanyDao;
	@Autowired
	private LoanCustomerDao loanCustomerDao;
	@Autowired
	private CustomerLivingsDao customerLivingsDao;
	@Autowired
	private ContactDao contactDao;
	@Autowired
	private LoanBankDao loanBankDao;
	@Autowired
	private LoanHouseDao loanHouseDao;
	@Autowired
	private LoanCoborrowerDao loanCoborrowerDao;
	@Autowired
	private LoanMateDao loanMateDao;
	
	/**
	 * 获取查看弹出画面信息
	 * 2015年11月28日
	 * By 李文勇
	 * @param loanCode
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ParseException 
	 */
	public Map<String,Object> showDetail(VerifyParamEx verifyParamEx)throws IllegalAccessException, InvocationTargetException, ParseException{
		HashMap<String,Object> map = new HashMap<String,Object>();
		ViewDetailView viewDetailView = new ViewDetailView();
		// 借款信息
		LoanInfo loanInfo = loanInfoDao.getLoanInfoByLoanCode(verifyParamEx.getLoanCode());
		if( loanInfo != null ){
			BeanUtils.copyProperties(viewDetailView,loanInfo);
		}
		
		// 职位信息
		LoanCompany companyInfo = this.getCompanyInfo(verifyParamEx);
		if( companyInfo != null ){
			BeanUtils.copyProperties(viewDetailView,companyInfo);
		}
		// 客户信息
		LoanCustomer loanCustomer = this.getCustomerInfo(verifyParamEx);
		if( loanCustomer != null ){
			if(CertificateType.SFZ.getCode().equals(loanCustomer.getDictCertType())
					|| CertificateType.HKB.getCode().equals(loanCustomer.getDictCertType()) &&
					StringUtils.isNotEmpty(loanCustomer.getCustomerCertNum())){
				String birth = loanCustomer.getCustomerCertNum().substring(6,14);// 年
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(birth));
				Date birthday = c.getTime();
				// 调用util根据出生日期计算年龄
				String age = GetAgeUtil.countAge(birthday);
				viewDetailView.setCustomerAge(age);
			}
			BeanUtils.copyProperties(viewDetailView,loanCustomer);
		}
		// 居住信息
		CustomerLivings customerLivingsInfo = this.getLivingsInfo(verifyParamEx);
		if( customerLivingsInfo != null ){
			BeanUtils.copyProperties(viewDetailView,customerLivingsInfo);
			if(StringUtils.isNotEmpty(customerLivingsInfo.getCustomerFirtArriveYear())){
				viewDetailView.setTomerFirtArriveYear(customerLivingsInfo.getCustomerFirtArriveYear());
			}else{
				viewDetailView.setTomerFirtArriveYear(loanCustomer.getCustomerFirtArriveYear());
			}
			if(StringUtils.isNotEmpty(customerLivingsInfo.getCustomerHouseHoldProperty())){
				viewDetailView.setCustomerHouseHoldProperty(customerLivingsInfo.getCustomerHouseHoldProperty());
			}else{
				viewDetailView.setCustomerHouseHoldProperty(loanCustomer.getCustomerHouseHoldProperty());
			}
		}else{
			viewDetailView.setTomerFirtArriveYear(loanCustomer.getCustomerFirtArriveYear());
			
			viewDetailView.setCustomerHouseHoldProperty(loanCustomer.getCustomerHouseHoldProperty());
		}
		// 房产信息
		List<LoanHouse> loanHouseInfo = this.getHouseInfo(verifyParamEx);
		if( loanHouseInfo != null ){
			map.put("loanHouseInfo", loanHouseInfo);
		}
		// 联系人信息
		List<Contact> contactInfo = this.getContactInfo(verifyParamEx);
		if( contactInfo != null && contactInfo.size() > 0 ){
			map.put("contact", contactInfo);
		}
		
		if(LoanManFlag.MAIN_LOAN.getCode().equals(verifyParamEx.getType())){
			// 银行卡信息
			List<LoanBank> loanBank = loanBankDao.viewGetByLoanCode(verifyParamEx.getLoanCode());
			if( loanBank != null && loanBank.size() >0 ){
				map.put("loanBank", loanBank);
			}
		}
		
		// 配偶信息
		LoanMate loanMate  = new LoanMate();
		loanMate.setLoanCode(verifyParamEx.getLoanCode());
		loanMate.setLoanCustomterType(verifyParamEx.getType());
		LoanMate loanMateResult = loanMateDao.viewGetByLoanCode(loanMate);
		if(loanMateResult != null){
			BeanUtils.copyProperties(viewDetailView,loanMateResult);
			viewDetailView.setMateCertType(loanMateResult.getDictCertType());
		}
		map.put("ApplyInformation", viewDetailView);
		return map;
	}
	
	/**
	 * 职位信息
	 * 2015年12月28日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	private LoanCompany getCompanyInfo(VerifyParamEx verifyParamEx){
		LoanCompany loanCompany = new LoanCompany();
		// 借款编号
		loanCompany.setLoanCode(verifyParamEx.getLoanCode());
		// 关联类型
		loanCompany.setCompCustomterType(verifyParamEx.getType());
		// 关联ID
		loanCompany.setrId(verifyParamEx.getRelId());
		LoanCompany companyInfo = loanCompanyDao.viewGetByLoanCode(loanCompany);
		return companyInfo;
	}
	
	/**
	 * 获取居住情况
	 * 2015年12月28日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	private CustomerLivings getLivingsInfo(VerifyParamEx verifyParamEx){
		CustomerLivings CustomerLivings = new CustomerLivings();
		// 借款编号
		CustomerLivings.setLoanCode(verifyParamEx.getLoanCode());
		// 关联类型
		CustomerLivings.setLoanCustomterType(verifyParamEx.getType());
		// 关联ID
		CustomerLivings.setrId(verifyParamEx.getRelId());
		CustomerLivings customerLivingsInfo = customerLivingsDao.viewByLoanCode(CustomerLivings);
		return customerLivingsInfo;
	}
	
	/**
	 * 获取房产信息
	 * 2015年12月28日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	private List<LoanHouse> getHouseInfo(VerifyParamEx verifyParamEx){
		LoanHouse LoanHouse = new LoanHouse();
		// 借款编号
		LoanHouse.setLoanCode(verifyParamEx.getLoanCode());
		// 关联类型
		LoanHouse.setLoanCustomterType(verifyParamEx.getType());
		// 关联ID
		LoanHouse.setrId(verifyParamEx.getRelId());
		List<LoanHouse> loanHouseInfo = loanHouseDao.viewGetByLoanCode(LoanHouse);
		return loanHouseInfo;
	}
	
	/**
	 * 获取联系人信息
	 * 2015年12月28日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	private List<Contact> getContactInfo(VerifyParamEx verifyParamEx){
		Contact contact = new Contact();
		// 借款编号
		contact.setLoanCode(verifyParamEx.getLoanCode());
		// 关联类型
		contact.setLoanCustomterType(verifyParamEx.getType());
		// 关联ID
		contact.setrId(verifyParamEx.getRelId());
		List<Contact> contactInfo = contactDao.viewGetByLoanCode(contact);
		return contactInfo;
	}
	
	/**
	 * 获取客户信息
	 * 2015年12月30日
	 * By 李文勇
	 * @param verifyParamEx
	 */
	private LoanCustomer getCustomerInfo(VerifyParamEx verifyParamEx){
		LoanCustomer loanCustomer = new LoanCustomer();
		if( LoanManFlag.MAIN_LOAN.getCode().equals(verifyParamEx.getType())  ){
			LoanCustomer bean = new LoanCustomer();
			bean.setLoanCode(verifyParamEx.getLoanCode());
			loanCustomer = loanCustomerDao.viewGetByLoanCode(bean);
		}else if(LoanManFlag.COBORROWE_LOAN.getCode().equals(verifyParamEx.getType())){
			LoanCoborrower bean = new LoanCoborrower();
			bean.setLoanCode(verifyParamEx.getLoanCode());
			bean.setId(verifyParamEx.getRelId());
			LoanCoborrower loanCoborrower =loanCoborrowerDao.viewGetByLoanCode(bean);
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
			
			// 现居	 省，市，区，详细地址
			loanCustomer.setCustomerLiveProvince(loanCoborrower.getCoboLiveingProvince());
			loanCustomer.setCustomerLiveCity(loanCoborrower.getCoboLiveingCity());
			loanCustomer.setCustomerLiveArea(loanCoborrower.getCoboLiveingArea());
			loanCustomer.setCustomerAddress(loanCoborrower.getCoboNowAddress());
			// 户籍	省，市，区，详细地址
			loanCustomer.setCustomerRegisterProvince(loanCoborrower.getCoboHouseholdProvince());
			loanCustomer.setCustomerRegisterCity(loanCoborrower.getCoboHouseholdCity());
			loanCustomer.setCustomerRegisterArea(loanCoborrower.getCoboHouseholdArea());
			loanCustomer.setCustomerRegisterAddress(loanCoborrower.getCoboHouseholdAddress());
		}
		return loanCustomer;
	}
}

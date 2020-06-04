package com.creditharmony.approve.verify.dao;



import java.util.List;
import java.util.Map;

import com.creditharmony.approve.antifraud.entity.ex.AntifraudMeConfirm;
import com.creditharmony.approve.phone.entity.ex.TelCheckBorrowerInfoEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckContactPersonEx;
import com.creditharmony.approve.verify.entity.LoanCustomer;
import com.creditharmony.approve.verify.entity.ex.ApplicantInfoEx;
import com.creditharmony.approve.verify.entity.ex.LoanCustomerEx;
import com.creditharmony.approve.verify.entity.ex.MainLoanInfoEx;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 主借人dao
 * @Class Name LoanCustomerDao
 * @author 刘燕军
 * @Create In 2015年12月5日
 */
@LoanBatisDao
public interface LoanCustomerDao extends CrudDao<LoanCustomer>{
	
	/**
    * 查看弹出画面用
    * 2015年12月30日
    * By 李文勇
    * @param loanCustomer
    * @return 主借人信息
    */
    public LoanCustomer viewGetByLoanCode(LoanCustomer loanCustomer);
    
    /**
     * 查看弹出画面用
     * 2015年10月10日
     * @param loanCustomer
     * @return 主借人信息
     */
     public LoanCustomer viewGetByLoanCodeNew(LoanCustomer loanCustomer);
   
    /**
     * 
     * 2015年12月5日
     * By 刘燕军
     * @param loanCode
     * @return 主借人信息
     */
    public MainLoanInfoEx findMainLoanInfoEx(String loanCode);
    
    /**
     * （决策画面）申请人资格数据
     * 2015年12月8日
     * By 李文勇
     * @param loanCode
     * @return  资格信息
     */
    public ApplicantInfoEx getByLoanCode(String loanCode);
    
    /**
     * 通用 通过借款编号获取对应借款人的id和编号
     * 2015年12月15日
     * By 刘燕军
     * @param loanCode
     * @return 主借人信息
     */
    public VerifyBusinessView  findIdCode(String loanCode);
   
    /**
     * 根据借款编码获取客户手机号码
     * 2015年12月14日
     * By 赖敏
     * @param loanCode
     * @return 主借人信息
     */
    public List<AntifraudMeConfirm> getPhonesByLoanCode(String loanCode);
    
    /**
     * 根据流程的申请编号，获取办理打开后需要展示的用户信息(信借流程)
     * 2015年12月19日
     * xiaoniu.hu
     * @param applyId
     * @return 主借人信息
     */
    public VerifyBusinessView getInfoForVerify(String applyId);
    
    /**
     * 根据流程的申请编号，获取办理打开后需要展示的用户信息(复议流程)
     * 2015年12月19日
     * xiaoniu.hu
     * @param applyId
     * @return 主借人信息
     */
    public VerifyBusinessView getInfoForReconsider(String applyId);
    
    /**
     * 在汇金数据中，查找主借人的配偶 
     * 2015年12月4日
     * By 王浩
     * @param params
     * @return 主借人信息
     */
    public TelCheckContactPersonEx selectMateByLoanCode(Map<String,Object> params);
    
    /**
     * 在汇金数据中，查找主借人的联系人
     * 2015年12月4日
     * By 王浩
     * @param params
     * @return 主借人信息
     */
    List<TelCheckContactPersonEx> selectMainContact(Map<String,Object> params);

    /**
     * 在汇金数据中,查找主借人的联系人、配偶、共借人的联系人
     * 2016年1月19日
     * By 王浩
     * @param params
     * @return 主借人信息
     */
    public List<TelCheckContactPersonEx> getAllContact(Map<String,Object> params);
        
    /**
     * 从汇金查询主借人以及所有共借人的资料
     * 2015年12月4日
     * By 王浩
     * @param params
     * @return 主借人信息
     */
    public List<TelCheckBorrowerInfoEx> getPersonalConfirmInfo(Map<String,Object> params);
    
    /**
     * 查询借借款人居住地址
     * 2015年12月30日
     * By 赖敏
     * @param loanCode
     * @return 主借人信息
     */
    public String getLiveAddress(String loanCode);
    
	/**
	 * 查询借款人信息（征信对比用）
	 * 2016年1月11日
	 * By 李文勇
	 * @param loanCode
	 * @return 主借人信息
	 */
    public LoanCustomerEx getCustomerInfo( String loanCode );
    
    /**
     * 通过借款编号获取借款客户信息，包含其工作信息
     * 2016年1月26日
     * By 申诗阔
     * @param loanCode
     * @return 借款客户信息，包含其工作信息
     */
    public LoanCustomerEx findCustomerByLoanCode(String loanCode);

    /**
     * 获取借款客户信息
     * 2016年3月29日
     * By 侯志斌
     * @param applyId
     * @return LoanCustomer
     */
	public LoanCustomer selectByApplyId(String applyId);
	
	/**
	 * 根据客户编号，查询借款编号
	 * 2016年4月7日
	 * By 王浩
	 * @param customerCode
	 * @return 
	 */
	public String getLoanCodeByCustomer(String customerCode);
	
	/**
	 * 根据loanCode获取客户信息（车借）
	 * 2016年4月8日
	 * By 申诗阔
	 * @param loanCode
	 * @return
	 */
	public LoanCustomerEx findCustomerInfo(String loanCode);
	
	/**
	 * 根据id查找customer 相关信息
	 * 2016年5月3日
	 * By 王浩
	 * @param id
	 * @return 
	 */
	public LoanCustomer getLoanCustomer(String id);

	/**
	 * 根据借款编号查询客户咨询时间是否在上线时间之后
	 * 2016年7月5日
	 * By 王浩
	 * @param loanCode
	 * @param riskRateOnlineTime 上线时间
	 * @return 
	 */
	public String getConsultTimeFlag(String loanCode, String riskRateOnlineTime);
	
	/**
	 * 根据loan_code查找customer 相关信息
	 * 2016年9月28日
	 * By 赵春香
	 * @param id
	 * @return 
	 */
	public LoanCustomer getCustomerByLoanCode(String loanCode);
	
	/**
	 * 根据loan_code查找主借人、配偶、法人代表人身份证号 
	 * 2016年10月22日
	 * By Wangyanna
	 * @param loanCode
	 * @return 
	 */
	public Map<String,String> getCertNumByLoanCode(String loanCode);

    /**
     * 新版申请表
     * 从汇金查询主借人以及所有共借人的资料
     * 2015年12月4日
     * By 王浩
     * @param params
     * @return 主借人信息
     */
	public List<TelCheckBorrowerInfoEx> getPersonalConfirmInfoNew(
			Map<String, Object> params);
}
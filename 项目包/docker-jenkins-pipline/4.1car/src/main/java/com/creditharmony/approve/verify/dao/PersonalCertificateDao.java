package com.creditharmony.approve.verify.dao;

import java.util.List;

import com.creditharmony.approve.verify.entity.PersonalCertificate;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 资料审核  个人证件
 * @Class Name PersonalCertificateDao
 * @author 赵春香
 * @Create In 2016年9月18日
 */
@LoanBatisDao
public interface PersonalCertificateDao {

    
    /**
     *  获得个人证件
     * 2016年9月18日
     * By 赵春香
     * @param zlshGrzj
     * @return list
     */
	public List<PersonalCertificate> getListByLoanCode(String loanCode);
    

}
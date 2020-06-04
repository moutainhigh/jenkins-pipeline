package com.creditharmony.approve.credit.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.credit.dao.CreditPaybackInfoDao;
import com.creditharmony.approve.credit.dao.CreditReportDetailedDao;
import com.creditharmony.approve.credit.entity.CreditPaybackInfo;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
import com.creditharmony.approve.credit.entity.ex.CreditQueryRecordEx;
import com.creditharmony.core.service.CoreManager;

/**
 * 征信保证人代偿信息
 * @Class Name CreditPaybackInfoService
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Service
@Transactional(value="loanTransactionManager",readOnly=true)
public class CreditPaybackInfoService extends  CoreManager<CreditPaybackInfoDao,CreditPaybackInfo> {

	@Autowired
	private CreditReportDetailedDao creditReportDetailedDao;
	@Autowired
	private CreditPaybackInfoDao creditPaybackInfoDao;
	
	/**
     * 根据个人征信简版id检索保证人代偿信息List
     * 2016年2月2日
     * By zhanghu
     * @param creditReportSimpleId
     * @return 查询信息List
     */
	public List<CreditPaybackInfo> selectByCreditPaybackInfo(String creditReportSimpleId) {
		CreditPaybackInfo creditPaybackInfo = new CreditPaybackInfo();
		creditPaybackInfo.setRelationId(creditReportSimpleId);
		return this.dao.selectByCreditPaybackInfo(creditPaybackInfo);
	}

	
	/**
	 * 根据个人征信简版id删除保证人代偿信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param relationId 个人征信简版id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteByRelationId(String relationId) {
		return this.dao.deleteByRelationId(relationId);
	}
	
	/**
	 * 根据个人征信简版id删除保证人代偿信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param relationId 个人征信简版id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int insertCreditPaybackInfo(CreditPaybackInfo creditPaybackInfo) {
		// 初始化默认数据
		creditPaybackInfo.preInsert();
		return this.dao.insertCreditPaybackInfo(creditPaybackInfo);
	}

	/**
	 * 根据id删除保证人代偿信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteCreditPaybackInfoById(String id) {
		return this.dao.deleteByPrimaryKey(id);
	}

	/**
	 * 保存数据
	 * 2016年2月15日
	 * By 李文勇
	 * @param param
	 * @return
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int saveData(CreditQueryRecordEx param){
		int flg = 0;
		if(param != null){
			CreditReportDetailed creditReportDetailed = new CreditReportDetailed();
			creditReportDetailed.setLoanCode(param.getLoanCode());
			creditReportDetailed.setDictCustomerType(param.getType());
			creditReportDetailed.setrCustomerCoborrowerId(param.getRelId());
			CreditReportDetailed detail = creditReportDetailedDao.getIdByParam(creditReportDetailed);
			List<CreditPaybackInfo> queryList = param.getCreditPaybackInfoList();
			if(queryList != null && queryList.size() > 0 && detail != null){
				for(int i = 0; i < queryList.size(); i++){
					if(StringUtils.isNotEmpty(queryList.get(i).getId())){
						CreditPaybackInfo creditPaybackInfo = queryList.get(i);
						creditPaybackInfo.setIsNewRecord(false);
						creditPaybackInfo.preUpdate();
						flg=this.dao.updataDataById(creditPaybackInfo);
					}else{
						CreditPaybackInfo creditPaybackInfo = queryList.get(i);
						creditPaybackInfo.setIsNewRecord(false);
						creditPaybackInfo.setRelationId(detail.getId());
						creditPaybackInfo.preInsert();
						flg = this.dao.insertData(creditPaybackInfo);
					}
				}
				return flg;
			}else{
				return flg;
			}
		}else{
			return flg;
		}
	}
	
	/**
	 * 
	 * 2016年2月15日
	 * By 李文勇
	 * @return
	 */
	public List<CreditPaybackInfo> showData(CreditReportDetailed creditReportDetailed){
		return creditPaybackInfoDao.getAllByParam(creditReportDetailed);
	}
	
	/**
	 * 删除数据
	 * 2016年2月16日
	 * By 李文勇
	 * @return
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteData(CreditPaybackInfo param){
		int result = creditPaybackInfoDao.deleteData(param);
		return result;
	}
	
}

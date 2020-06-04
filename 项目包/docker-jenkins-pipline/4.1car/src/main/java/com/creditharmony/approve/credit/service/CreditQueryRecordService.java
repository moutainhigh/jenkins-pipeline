package com.creditharmony.approve.credit.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.credit.dao.CreditQueryRecordDao;
import com.creditharmony.approve.credit.dao.CreditReportDetailedDao;
import com.creditharmony.approve.credit.entity.CreditQueryRecord;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
import com.creditharmony.approve.credit.entity.ex.CreditQueryRecordEx;

/**
 * 征信查询记录
 * @Class Name CreditQueryRecordService
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Service
public class CreditQueryRecordService extends  CoreManager<CreditQueryRecordDao,CreditQueryRecord> {

	@Autowired
	private CreditReportDetailedDao creditReportDetailedDao;
	@Autowired
	private CreditQueryRecordDao creditQueryRecordDao;
	
	/**
     * 根据个人征信简版id检索查询信息List
     * 2016年2月2日
     * By zhanghu
     * @param creditReportSimpleId
     * @return 查询信息List
     */
	public List<CreditQueryRecord> selectByCreditQueryRecord(String creditReportSimpleId) {
		CreditQueryRecord creditQueryRecord = new CreditQueryRecord();
		creditQueryRecord.setRelationId(creditReportSimpleId);
		return this.dao.selectByCreditQueryRecord(creditQueryRecord);
	}

	
	/**
	 * 根据个人征信简版id删除查询信息
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
	 * 根据个人征信简版id删除查询信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param relationId 个人征信简版id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int insertCreditQueryRecord(CreditQueryRecord creditQueryRecord) {
		// 初始化默认数据
		creditQueryRecord.preInsert();
		return this.dao.insertCreditQueryRecord(creditQueryRecord);
	}

	/**
	 * 根据id删除查询信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param id
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteQueryInfoById(String id) {
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
			List<CreditQueryRecord> queryList = param.getCreditQueryList();
			if(queryList != null && queryList.size() > 0 && detail != null){
				for(int i = 0; i < queryList.size(); i++){
					if(StringUtils.isNotEmpty(queryList.get(i).getId())){
						CreditQueryRecord creditQueryRecord = queryList.get(i);
						creditQueryRecord.setIsNewRecord(false);
						creditQueryRecord.preUpdate();
						flg=this.dao.updataDataById(creditQueryRecord);
					}else{
						CreditQueryRecord creditQueryRecord = queryList.get(i);
						creditQueryRecord.setIsNewRecord(false);
						creditQueryRecord.setRelationId(detail.getId());
						creditQueryRecord.preInsert();
						flg = this.dao.insertData(creditQueryRecord);
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
	public List<CreditQueryRecord> showData(CreditReportDetailed creditReportDetailed){
		List<CreditQueryRecord> cqrList = creditQueryRecordDao.getAllByParam(creditReportDetailed);
		for (CreditQueryRecord creditQueryRecord : cqrList) {
			if (creditQueryRecord != null)
				creditQueryRecord.setQueryType(DictCache.getInstance().getDictLabel(DictionaryConstants.CREDIT_QUERY_TYPE, creditQueryRecord.getQueryType()));
		}
		return cqrList;
	}
	
	/**
	 * 删除数据
	 * 2016年2月16日
	 * By 李文勇
	 * @return
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int deleteData(CreditQueryRecord param){
		int result = creditQueryRecordDao.deleteData(param);
		return result;
	}
	
}

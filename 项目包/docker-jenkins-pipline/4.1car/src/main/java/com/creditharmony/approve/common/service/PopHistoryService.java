package com.creditharmony.approve.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.verify.dao.AuditResultDao;
import com.creditharmony.approve.verify.dao.ReconsiderApplyDao;
import com.creditharmony.approve.verify.dao.StatusChangeRecordDao;
import com.creditharmony.approve.verify.entity.AuditResult;
import com.creditharmony.approve.verify.entity.AuditResultSublist;
import com.creditharmony.approve.verify.entity.ReconsiderApply;
import com.creditharmony.approve.verify.entity.StatusChangeRecord;
import com.creditharmony.approve.verify.entity.ex.AuditResultEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.ChkResult;
import com.creditharmony.core.cache.ProvinceCityCache;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.loan.type.LoanApplyStatus;

/**
 * 显示历史弹出画面
 * @Class Name PopHistoryService
 * @author 李文勇
 * @Create In 2015年12月2日
 */
@Service
public class PopHistoryService {

	@Autowired
	private StatusChangeRecordDao statusChangeRecordDao;
	@Autowired
	private AuditResultDao auditResultDao;
	@Autowired
	private ReconsiderApplyDao reconsiderApplyDao;
	
	
	/**
	 * 获取历史列表信息
	 * 2015年12月2日
	 * By 李文勇
	 * @param loanCode
	 * @return
	 */
	public List<StatusChangeRecord> showHistory(VerifyParamEx verifyParamEx){
		StatusChangeRecord statusChangeRecord = new StatusChangeRecord();
		if(verifyParamEx != null){
			statusChangeRecord.setLoanCode(verifyParamEx.getLoanCode());
			statusChangeRecord.setApplyId(verifyParamEx.getApplyId());
		}
		List<StatusChangeRecord> result = statusChangeRecordDao.historyGetByLoanCode(statusChangeRecord);
		if(ArrayHelper.isNotEmpty(result)){
			for(StatusChangeRecord record : result){
				// 如果状态为901（发起复议）则去复议申请表查询复议类型
				if(LoanApplyStatus.LANUCH_RE.getCode().equals(record.getDictLoanStatus())){
					ReconsiderApply apply = reconsiderApplyDao.getByLoanCode(verifyParamEx.getLoanCode());
					if(apply != null && StringUtils.isNotEmpty(apply.getDictReconsiderType())){
						// 字典数据
						String applyType = DictUtils.getDictLabel(apply.getDictReconsiderType(),DictionaryConstants.RECONSIDE_REASON,""); 
						record.setRemark("复议类型:" + applyType + "; 复议原因:" + apply.getSecondReconsiderMsg());
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 查询决策回显
	 * 2016年1月19日
	 * By 李文勇
	 * @param relationId
	 * @return
	 */
	public AuditResultEx getCheckInfo(String relationId){
		AuditResult auditResult = new AuditResult();
		auditResult.setRStatusHisId(relationId);
		AuditResultEx result = auditResultDao.getCheckInfo(auditResult);
		if(result != null && StringUtils.isNotEmpty(result.getAuditResult())  && StringUtils.isNotEmpty(result.getId())){
			if(ChkResult.REFUSE_TO_BORROW.getCode().equals(result.getAuditResult()) 
					|| ChkResult.TO_REFUSE_TO_BORROW.getCode().equals(result.getAuditResult())){
				List<AuditResultSublist> allRefuse = auditResultDao.getAllRefuseByRid(result.getId());
				result.setSubResult(allRefuse);
			}
			// 如果保证人省市区不为空，将省市区名字填写到地址字段，显示在页面上
			if(result != null && StringUtils.isNotEmpty(result.getEnsuremanBusinessArea())){
				String provice = ProvinceCityCache.getInstance().getProvinceCity(result.getEnsuremanBusinessProvince(), 
						result.getEnsuremanBusinessCity(), result.getEnsuremanBusinessArea());
				result.setEnsuremanBusinessPlace(provice + result.getEnsuremanBusinessPlace());
			}
		}
		return result;
	}
}

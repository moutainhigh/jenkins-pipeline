package com.creditharmony.approve.workflow.event;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.localnet.dao.InnerRepeatDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.service.DiskInfoService;
import com.creditharmony.approve.verify.service.InitParamService;
import com.creditharmony.approve.verify.util.ImagePlatformUtil;
import com.creditharmony.approve.verify.view.DiskInfoView;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.face.BusinessLoadCallBack;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.config.Global;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;

/**
 * 信借流程加载业务数据
 * @Class Name LoadFlowData
 * @author xiaoniu.hu
 * @Create In 2015年12月18日
 */
@Service("Reconsider_Flow_Data")
public class ReconsiderFlowData extends BaseService implements BusinessLoadCallBack {
	@Autowired 
	private LoanCustomerDao loanCustomerDao;
	@Autowired 
	private LoanInfoDao loanInfoDao;
	@Autowired
	private InnerRepeatDao innerRepeatDao;
	@Autowired
	private DiskInfoService diskInfoService;
	@Autowired
	private InitParamService initParamService;
	@Override
	public BaseBusinessView load(String applyId, String stepName) {
		User user = UserUtils.getUser(); // 获取当前登录用户
		VerifyBusinessView res = new VerifyBusinessView();
		res=loanCustomerDao.getInfoForReconsider(applyId);
		res.setCheckType(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode());
		res.setType(LoanManFlag.MAIN_LOAN.getCode());
		res.setUserCode(user.getUserCode());//设置用户工号
		res.setCustomerSex(DictCache.getInstance().getDictLabel(DictionaryConstants.DICT_GENDER,res.getCustomerSex()));
		// 使用TimeOutPoint字段记录当前借款的进件时间
		String queryTime =DateUtils.formatDate(res.getTimeOutPoint(), "yyyyMMdd");
		DiskInfoView diskInfoView = diskInfoService.findDiskName(queryTime, res.getDictSourceType());
		Map<String, String> stepRead = ImagePlatformUtil.getStepAndReadOnly(stepName);
		res.setSunyardUrl(ImagePlatformUtil.appendUrlParm(stepRead.get("stepName"), Global.getConfig(ApproveConstants.CHP3_0XYD_INFO), res.getLoanCode(),
				diskInfoView, queryTime, stepRead.get("readOnly")));
		return res;
	}
	
}

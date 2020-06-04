package com.creditharmony.approve.common.util;

import org.apache.commons.lang.StringUtils;
import com.creditharmony.adapter.bean.in.outbound.OutboundCallInBean;
import com.creditharmony.adapter.bean.in.outbound.OutboundGetSoundRecordInBean;
import com.creditharmony.adapter.bean.out.outbound.OutboundCallOutBean;
import com.creditharmony.adapter.bean.out.outbound.OutboundGetSoundRecordOutBean;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.core.cache.DictCache;

public class CallPhoneUtil {
	/**
	 * 发起外呼,调用商路通webservice
	 * 2016年3月30日
	 * xiaoniu.hu
	 * @param phoneNo 拨打的号码
	 * @param clientIp 浏览器端IP地址
	 * @param UUID 录音KEY
	 * @return
	 */
	public static final String callPhone(String phoneNo,String clientIp,String UUID) {
		ClientPoxy service = new ClientPoxy(ServiceType.Type.OUT_BOUND_CALL);
		OutboundCallInBean param = new OutboundCallInBean();
		param.setCallNum(ApproveConstants.SYNROUTE_CALLNUMBER + phoneNo+","+
			   DictCache.getInstance().getDictLabel(DictionaryConstants.SYNROUTE_NUMBER,NumberConstants.ONE_STRING));
        param.setClientIp(clientIp);
        param.setStrUUI(UUID);
        //调用外呼
        OutboundCallOutBean outInfo = (OutboundCallOutBean) service.callService(param);
        return outInfo.getRetCode();
	}
	
	/**
	 * 通过唯一主叫号码，获取录音信息
	 * 2016年4月27日
	 * By 刘燕军
	 * @param callId
	 * @return 录音信息的实体
	 */
	public static final String getRecord(String callId) {
		ClientPoxy service = new ClientPoxy(ServiceType.Type.OUT_BOUND_GET_SOUND_RECORD);
		OutboundGetSoundRecordInBean in = new OutboundGetSoundRecordInBean();
		in.setCustomerCallID(callId);
		OutboundGetSoundRecordOutBean out = (OutboundGetSoundRecordOutBean) service.callService(in);
		if (out != null && StringUtils.isNotEmpty(out.getLocalPath())
				&& StringUtils.isNotEmpty(out.getLocalFile())) {
			return "http://" + out.getRecServerIP()
					+ out.getLocalPath().substring(out.getLocalPath().indexOf("\\")).replace("\\", "/")
					+ out.getLocalFile();
		} else {
			return null;
		}

	}
}

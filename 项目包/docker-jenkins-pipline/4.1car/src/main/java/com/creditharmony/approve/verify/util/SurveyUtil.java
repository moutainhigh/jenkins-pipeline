package com.creditharmony.approve.verify.util;

import java.util.List;

import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.localnet.entity.InnerCustomerHis;

/**
 * 
 * @项目名称：chp-approve  
 * @类名称：SurveyUtil   
 * @类描述： 获取内网审核中的历史归户信息的概况内容 
 * @创建人：liuyanjun   
 * @创建时间：2015年11月26日 上午9:01:36  
 *
 * @version V1.0
 */
public class SurveyUtil {
	/**
	 * 通过获取到的List<InnerCustomerHis>
	 * 遍历获取对应需要的信息
	 * @param list
	 * @return
	 */
	public static String getMessage(List<InnerCustomerHis> list){
		
		int loan=0;
		int overdue=0;
		if(list != null){
			for (InnerCustomerHis innerCustomerHis : list) {
				if(ApproveConstants.PASS.equals(innerCustomerHis.getDictCheckStatus())){
					loan++;
				}
				if(innerCustomerHis.getMaxOverdueDays()!=null){
					overdue++;
				}
			}
			return "客户在本机构共申请"+list.size()+" 笔，放款"+ loan +"笔，逾期 "+ overdue  +" 笔";
		}else{
			return "";
		}
		
		
		
	}
}

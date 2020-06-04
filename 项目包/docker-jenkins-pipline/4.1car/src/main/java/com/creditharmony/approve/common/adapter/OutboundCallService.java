package com.creditharmony.approve.common.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.service.outbound.Outbound_CallBaseService;
import com.creditharmony.adapter.service.outbound.bean.OutboundCallStateInBean;
import com.creditharmony.adapter.service.outbound.bean.OutboundCallStateOutBean;
import com.creditharmony.approve.phone.dao.DhzhDhlyxxDao;

/**
 * 外呼回调函数
 * 
 * @Class Name
 * @author wzq
 * @Create In 2016年2月23日
 */
@Service
public class OutboundCallService extends Outbound_CallBaseService {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private DhzhDhlyxxDao lyDao;
    @Override
    public OutboundCallStateOutBean doExec(OutboundCallStateInBean inParam) {
        
        log.debug("回调函数开始---------------------");
        log.info(inParam.getParam());

        OutboundCallStateOutBean outParam = new OutboundCallStateOutBean();
        try {
            log.debug("回调开始更新数据---------------------");
            lyDao.updateFromRecord(inParam);
            log.debug("回调更新数据结束---------------------");
            outParam.setRetCode(ReturnConstant.SUCCESS);
        } catch (Exception e) {
            log.error("汇诚处理失败.");
            outParam.setRetCode(ReturnConstant.ERROR);
            outParam.setRetMsg("汇诚处理失败.");
        }
        log.info(outParam.getParam());
        
        log.debug("回调函数结束---------------------");
    	return outParam;
    }

}

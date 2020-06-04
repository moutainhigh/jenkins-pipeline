package com.creditharmony.approve.document.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.document.dao.ZlshYxxjcDao;
import com.creditharmony.approve.document.entity.ZlshYxxjc;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.service.CoreManager;

/**
 * 资料审核 有效性检查
 * @Class Name ZlshYxxjcService
 * @author 路志友
 * @Create In 2016年1月28日
 */
@Service
public class ZlshYxxjcService extends CoreManager<ZlshYxxjcDao, ZlshYxxjc> {

	/**
	 * 保存/修改资产证明
	 * 2016年1月28日
	 * By 路志友
	 * @param lshYxxjc
	 * @param vex
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String saveYxxjc(ZlshYxxjc lshYxxjc,VerifyParamEx vex) {
		String id = lshYxxjc.getId();
		lshYxxjc.setLoanCode(vex.getLoanCode());// 借款编号
		lshYxxjc.setDictCheckType(vex.getCheckType());// 信审类型
		lshYxxjc.setDictCustomerType(vex.getType());// 借款人类型
		lshYxxjc.setrId(vex.getRelId());// 借款人id
		if(StringUtils.isEmpty(id)){
			lshYxxjc.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
			lshYxxjc.preInsert();
			dao.insert(lshYxxjc);
			id = lshYxxjc.getId();
		}else{
			lshYxxjc.preUpdate();
			dao.update(lshYxxjc);
		}
		return id;
	}

	/**
	 * 删除有效性检查
	 * 2016年1月4日
	 * By 路志友
	 * @param lshYxxjc
	 * @return String
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String deleteYxxjc(ZlshYxxjc lshYxxjc) {
		String id = "";
		if(!StringUtils.isEmpty(lshYxxjc.getId())){
			 id = lshYxxjc.getId();
			// dao.deleteByPrimaryKey(id);
		}
		return id;
	}
	
	/**
	 * 查询有效性检查
	 * 2016年3月11日
	 * By 李文勇
	 * @param vex
	 * @return
	 */
	public ZlshYxxjc findZlshYxxjc(VerifyParamEx vex){
		List<ZlshYxxjc> result = dao.findYxx(vex);
		ZlshYxxjc zlshYxxjc = new ZlshYxxjc();
		if(ArrayHelper.isNotEmpty(result)){
			zlshYxxjc = result.get(0);
		}
		return zlshYxxjc;
	}
	
}

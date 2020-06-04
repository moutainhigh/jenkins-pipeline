package com.creditharmony.approve.carloan.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.carloan.dao.GrossSpreadDao;
import com.creditharmony.approve.carloan.entity.GrossSpread;
import com.creditharmony.core.service.CoreManager;

	/**
	 * 费率服务类
	 * @Class Name GrossSpreadService
	 * @author 李静辉
	 * @Create In 2016年2月25日
	 */
	@Service
	public class GrossSpreadService extends CoreManager<GrossSpreadDao, GrossSpread> {

		@Autowired
		private GrossSpreadDao grossSpreadDao;
		@Autowired
		private CarContractVersionService contractVers;
		
		/**
		 * 根据产品类型、产品期限、城市(通过借款编码获取机构，再获取机构 城市)获取总费率
		 * 2016年2月25日
		 * By 李静辉
		 * @param map
		 * @return
		 */
		public GrossSpread findByProductTypeAndDeadline(Map<String, String> map) {
			return grossSpreadDao.findByProductTypeAndDeadline(map);
		}
		
		/**
		 * 根据借款期限、产品类型获取总费率
		 * 2016年2月25日
		 * By 陈伟东
		 * @param deadlineCode
		 * @param productTypeCode
		 * @return
		 */
		public Double getCarGrossSpread(String deadlineCode,String productTypeCode,String loanCode,String isextensionId) {
			GrossSpread carGrossSpread = new GrossSpread();
			carGrossSpread.setDictDeadline(deadlineCode);
			carGrossSpread.setDictProductType(productTypeCode);
			//根据loanCode来确定使用哪个类型的费率
			String contractVer = contractVers.getFlowContractVersion(loanCode,isextensionId);
	  	    //end
			carGrossSpread.setRateType(contractVer);
			List<GrossSpread> selectCarGross = grossSpreadDao.selectCarGross(carGrossSpread);
			if(selectCarGross != null && selectCarGross.size() > 0){
				return selectCarGross.get(0).getGrossRate().doubleValue();
			}
			return null;
		}
		
	}

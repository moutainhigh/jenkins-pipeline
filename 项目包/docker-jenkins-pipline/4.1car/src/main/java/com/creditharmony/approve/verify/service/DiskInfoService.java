package com.creditharmony.approve.verify.service;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.verify.dao.FileDiskInfoDao;
import com.creditharmony.approve.verify.entity.FileDiskInfo;
import com.creditharmony.approve.verify.view.DiskInfoView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.approve.type.ApproveCheckType;

/** 查询文件存放的磁盘信息
 * @author 张建雄
 * @Class Name DiskInfoService
 * @create 2016年 3月29日
 */
@Service 
public class DiskInfoService{
	
	@Autowired
	private FileDiskInfoDao diskInfoDao;
	
	/** 通过进件时间查询文件存放的磁盘名
	 * @param queryTime 进件时间
	 * @return 磁盘名
	 */
	private List<FileDiskInfo> getDiskListByTime(String queryTime){
		if (StringUtils.isEmpty(queryTime)) {
			return null;
		}	
		
		return diskInfoDao.getDiskInfoListByQueryTime(queryTime);
	}
	
	/**
	 * 获取汇诚、信审、复议的信雅达插件
	 * 2016年5月5日
	 * By 王浩
	 * @param queryTime
	 * @param sourceType chp2.0的数据还是3.0的数据
	 * @return 
	 */
	public DiskInfoView findDiskName(String queryTime, String sourceType){		
		DiskInfoView diskInfoView = new DiskInfoView();
		
		List<FileDiskInfo> diskInfoList = this.getDiskListByTime(queryTime);
		if (ArrayHelper.isNotEmpty(diskInfoList)) {
			for (FileDiskInfo diskInfo : diskInfoList) {
				// 数据是chp3.0的数据
				if (StringUtils.isNotEmpty(sourceType) && ApproveConstants.DATA_SOURCE_CHP3_0.equals(sourceType)) {
					// 系统标识为3.0信审
					if (ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode()
							.equals(diskInfo.getSysFlag())) {
						// 信审						
						diskInfoView.setVerifyIndex(diskInfo.getFlagHc());
						// 汇金
						diskInfoView.setLoanIndex(diskInfo.getFlagHj());
						diskInfoView.setVerifyFilelevel(diskInfo.getLoanDirLevel());
					}
					// 系统标识为3.0 复议
					if (ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode()
							.equals(diskInfo.getSysFlag())) {
						// 复议
						diskInfoView.setReconsiderIndex(diskInfo.getFlagHc());
						diskInfoView.setReconsiderFilelevel(diskInfo.getLoanDirLevel());
					}
				} else {
					// 数据是2.0的数据
					// 系统标识为2.0信审
					if (ApproveConstants.XS_CHP2_0_SYS_FLAG.equals(diskInfo.getSysFlag())) {
						// 信审
						diskInfoView.setVerifyIndex(diskInfo.getFlagHc());
						diskInfoView.setLoanIndex(diskInfo.getFlagHj());
						diskInfoView.setVerifyFilelevel(diskInfo.getLoanDirLevel());
					}
					// 系统标识为2.0 复议
					if (ApproveConstants.FY_CHP2_0_SYS_FLAG.equals(diskInfo.getSysFlag())) {
						// 复议
						diskInfoView.setReconsiderIndex(diskInfo.getFlagHc());
						diskInfoView.setReconsiderFilelevel(diskInfo.getLoanDirLevel());
					}
				}
			}
		}
		return diskInfoView;		
	}
}

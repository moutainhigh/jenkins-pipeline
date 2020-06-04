package com.creditharmony.approve.antifraud.dao;
import java.util.List;
import java.util.Map;

import com.creditharmony.approve.antifraud.entity.AntifraudJudge;
import com.creditharmony.approve.antifraud.entity.AntifraudReport;
import com.creditharmony.approve.antifraud.entity.BacklistAll;
import com.creditharmony.approve.antifraud.entity.Refuse;
import com.creditharmony.approve.antifraud.entity.ex.AntiFraudJudgeEx;
import com.creditharmony.approve.antifraud.entity.ex.AntiFraudJudgeOptionEx;
import com.creditharmony.approve.antifraud.entity.ex.CoborrowerOptionEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 反欺诈判定dao
 * @Class Name AntifraudJudgeDao
 * @author wanglidong
 * @Create In 2015年12月1日
 */
@LoanBatisDao
public interface AntifraudJudgeDao extends CrudDao<AntiFraudJudgeEx>{

    /**
     * 添加内/外部黑名单列表
     * 2015年12月1日
     * By wanglidong
     * @param externalBlackList 外部黑名单
     * @return 完成标识
     */
    public int addBlackList(List<BacklistAll> externalBlackList);
	
    /**
     * 黑名单二级拒借码
     * 2015年12月14日
     * By wanglidong
     * @param refuseGrade 拒绝码
     * @return 二级拒绝码集合
     */
	public List<Refuse> getBlackTwoLevel();
	
	/**
	 * 黑名单三级拒借码
	 * 2015年12月14日
	 * By wanglidong
	 * @return 三级决绝码集合
	 */
	public List<Refuse> getBlackThreeLevel();
	
	/**
	 * 灰名单二级拒借码
	 * 2016年5月20日
	 * By wanglidong
	 * @return 灰名单二级决绝码集合
	 */
	public List<Refuse> getGrayTwoLevel();
	
	/**
	 * 获取加黑灰项
	 * 2016年1月28日
	 * By wanglidong
	 * @param map 查询参数
	 * @return 返回加黑项
	 */
	public AntiFraudJudgeOptionEx getAntifraudOption(Map<String, String> map);
	
	/**
	 * 保存反欺诈决策
	 * 2015年12月16日
	 * By wanglidong
	 * @param antiFraudJudgeEx 反欺诈决策扩展实体类
	 * @return void
	 */
	public void updateJudge(AntiFraudJudgeEx antiFraudJudgeEx);
	
	/**
	 * 获取解除状态
	 * 2015年12月17日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 解除状态集合
	 */
	public List<String> getRelieveStatus(String loanCode);
	
	/**
	 * 获取欺诈案件编号
	 * 2016年1月28日
	 * By wanglidong
	 * @param map 查询参数
	 * @return 当日反欺诈决策次数
	 */
	public Integer getJudgeTime(Map<String, Object> map);

	/**
	 * 插入判定
	 * 2015年12月23日
	 * By 刘燕军
	 * @param antifraudJudge 反欺诈决策实体类
	 * @return 成功标识
	 */
	public int insertJudge(AntifraudJudge antifraudJudge);
    
	/**
	 * 根据loanCode获取提报反欺诈信息
	 * 2016年1月28日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @param status 提报状态
	 * @return 报反欺诈信息
	 */
    public AntifraudReport getAntifraudReport(String loanCode,String status);

    /**
     * 获取决策信息
     * 2016年1月28日
     * By wanglidong
     * @param map 查询参数
     * @return 决策信息
     */
	public AntiFraudJudgeEx getAntiFraudJudgeView(Map<String, String> map);	
	
	/**
	 * 获取外部拉黑信息
	 * 2015年12月28日
	 * By wanglidong
	 * @param map 查询参数
	 * @return 外部拉黑集合
	 */
	public List<BacklistAll> getBlackListAll(Map<String, String> map);

	/**
	 * 删除外部拉黑
	 * 2015年12月29日
	 * By wanglidong
	 * @param id 删除id
	 * @return void
	 */
	public void delOutBlack(String id);

	/**
	 * 获取联系人手机号
	 * 2016年1月9日
	 * By wanglidong
	 * @param map 查询参数
	 * @return 联系人手机号
	 */
	public List<String> getContactMobile(Map<String, String> map);
	
	/**
	 * 新版申请表add
	 * 获取联系人手机号
	 * 2016年1月9日
	 * By 张虎
	 * @param map 查询参数
	 * @return 联系人手机号
	 */
	public List<String> getContactHomeTel(Map<String, String> map);

	/**
	 * 获取反欺诈加灰项
	 * 2016年1月28日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 加灰项
	 */
	public AntiFraudJudgeOptionEx getGrayListOption(String loanCode);

	/**
	 * 查看黑灰名单在数据库中是否存在
	 * 2016年1月15日
	 * By wanglidong
	 * @param value 加黑灰内容
	 * @return 黑名单个数
	 */
	public int getCheckExists(String value);

	/**
	 * 获取用户所在区域
	 * 2016年1月18日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 黑名单个数
	 */
	public String getArea(String loanCode);

	/**
	 * 查看决策历史纪录
	 * 2016年1月19日
	 * By wanglidong
	 * @param map 查询参数
	 * @return 决策信息
	 */
	public AntifraudJudge getJudgeHistory(Map<String,String> map);

	/**
	 * 获取借款状态
	 * 2016年1月21日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 借款状态
	 */
	public String getLoanStatus(String loanCode);

	/**
	 * 获取提报类型
	 * 2016年1月26日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @param status 状态 
	 * @return 提报反欺诈信息
	 */
	public AntifraudReport getAntifraudReportType(String loanCode, String status);

	/**
	 * 获取系统提报说明
	 * 2016年1月29日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 提报说明
	 */
	public List<String> getAntifraudMsg(String loanCode);

	/**
	 * 获取历史提报类型
	 * 2016年2月1日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 提报信息
	 */
	public AntifraudReport getAntifraudReportTypeView(String loanCode);

	/**
	 * 获取共借人的内部加黑项
	 * 2016年2月19日
	 * By wanglidong
	 * @param map
	 */
	public List<CoborrowerOptionEx> getCoborrowerOption(Map<String, String> map);

	/**
	 * 获取共借人联系人手机号
	 * 2016年2月20日
	 * By wanglidong
	 * @param id
	 */
	public List<String> getCoborrowerContactMobile(String id);
	
	/**
	 * 新版申请表add
	 * 获取共借人联系人宅电
	 * 2016年2月20日
	 * By 张虎
	 * @param id
	 */
	public List<String> getCoborrowerContactHomeTel(String id);

	/**
	 * 获取共借人的内部加灰项
	 * 2016年2月20日
	 * By wanglidong
	 * @param map
	 * @return
	 */
	public List<CoborrowerOptionEx> getCoborrowerGrayOption(Map<String, String> map);

	/**
	 * 获取反欺诈员工编号
	 * 2016年2月22日
	 * By wanglidong
	 * @param userCode 用户工号
	 */
	public String getAfraudCode(String userCode);

	/**
	 * 获取内部加黑灰项回显
	 * 2016年3月30日
	 * By wanglidong
	 * @param map
	 */
	public List<BacklistAll> getOldBlackGrayListOption(Map<String,String> map);

	/**
	 * 更新借款信息表中的审核结果ID
	 * 2016年4月13日
	 * By wanglidong
	 * @param mapp 审核记录表的id和loancode借款编码
	 */
	public void updateLoanInfo(Map<String,Object> map);



}






















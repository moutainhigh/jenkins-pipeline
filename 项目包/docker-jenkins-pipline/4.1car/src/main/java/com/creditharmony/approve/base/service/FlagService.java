package com.creditharmony.approve.base.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.common.constants.KinnobuQuotaLConstant;
import com.creditharmony.approve.verify.dao.QuotaLimitDao;
import com.creditharmony.approve.verify.entity.QuotaLimit;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.core.exception.ServiceException;

@Service
public class FlagService extends BaseService {

	@Autowired
	private QuotaLimitDao quotaLimitDao;

	/**
	 * ************************************** 按照上限1，2，3，4，5，6，7，8，9，10 的顺序进行标识 *
	 * 财富 1 5 9 13 17 * 金信 2 6 10 14 18 * P2P 3 7 11 15 19 * ZCJ 4 8 12 16 20 *
	 * ************************************** 对比财富、金信、P2P和ZCJ剩余次数 2016年10月31日
	 * 
	 * @param flowView
	 */
	public String checkKinnobus() {
		String resultFlg = KinnobuQuotaLConstant.NONE;// 设置标识为：剩余数量全部为0
		QuotaLimit quotaLimitResult = quotaLimitDao.getDataNew(99);
		if (quotaLimitResult != null) {
			int ch1 = quotaLimitResult.getChp1(); // ch1上限
			int ch2 = quotaLimitResult.getChp2(); // ch2上限
			int ch3 = quotaLimitResult.getChp3(); // ch3上限
			int ch4 = quotaLimitResult.getChp4(); // ch4上限
			int ch5 = quotaLimitResult.getChp5(); // ch5上限
			int chpResidual = quotaLimitResult.getChpResidual(); // chp剩余数量
			int kin1 = quotaLimitResult.getGoldCredit1(); // 金信上限数量1
			int kin2 = quotaLimitResult.getGoldCredit2(); // 金信上限数量2
			int kin3 = quotaLimitResult.getGoldCredit3(); // 金信上限数量3
			int kin4 = quotaLimitResult.getGoldCredit4(); // 金信上限数量4
			int kin5 = quotaLimitResult.getGoldCredit5(); // 金信上限数量5
			int goldCreditResidual = quotaLimitResult.getGoldCreditResidual(); // 金信剩余数量
			int p2p1 = quotaLimitResult.getP2p1(); // P2P上限数量1
			int p2p2 = quotaLimitResult.getP2p2(); // P2P上限数量2
			int p2p3 = quotaLimitResult.getP2p3(); // P2P上限数量3
			int p2p4 = quotaLimitResult.getP2p4(); // P2P上限数量4
			int p2p5 = quotaLimitResult.getP2p5(); // P2P上限数量5
			int p2pResidual = quotaLimitResult.getP2pResidual(); // P2P剩余数量
			int zcj1 = quotaLimitResult.getZcj1(); // 资产家上限数量1
			int zcj2 = quotaLimitResult.getZcj2(); // 资产家上限数量2
			int zcj3 = quotaLimitResult.getZcj3(); // 资产家上限数量3
			int zcj4 = quotaLimitResult.getZcj4(); // 资产家上限数量4
			int zcj5 = quotaLimitResult.getZcj5(); // 资产家上限数量5
			int zcjResidual = quotaLimitResult.getZcjResidual(); // 资产家剩余数量
			int posit = quotaLimitResult.getPosit();// 当前位置
			int positNum = quotaLimitResult.getPositNum();// 当前位置数量
			int positXyj = quotaLimitResult.getPositXyj();// 信易借当前位置
			int positNumXyj = 0;// 信易借当前位置剩余上限数量
			Integer y = quotaLimitResult.getPositNumXyj();// 信易借当前位置剩余上限数量
			if (y != null) {
				positNumXyj = y;
			}
			int[] slsxArray = new int[] { ch1, kin1, p2p1, zcj1, ch2, kin2, p2p2, zcj2, ch3, kin3, p2p3, zcj3, ch4,
					kin4, p2p4, zcj4, ch5, kin5, p2p5, zcj5 };
			if (posit > 20) {
				resultFlg = KinnobuQuotaLConstant.NONE;// 数量为零
			} else {
				QuotaLimit param = new QuotaLimit();
				QuotaLimit paramCopy = new QuotaLimit();
				param.setId(quotaLimitResult.getId());// 设置ID
				param.setVersion(quotaLimitResult.getVersion());// 设置version
				paramCopy.setId(quotaLimitResult.getId());// 设置ID
				paramCopy.setVersion(quotaLimitResult.getVersion());// 设置version
				for (int i = posit; i < 21; i++) {
					if (positNum > 0) {
						if (i % 4 == 1) {
							resultFlg = KinnobuQuotaLConstant.CHPLAG;// chp标识
							param.setChpResidual(chpResidual - 1);// chp剩余数量减一
							param.setGoldCreditResidual(goldCreditResidual);// 金信剩余数量不变
							param.setP2pResidual(p2pResidual);// P2P剩余数量不变
							param.setZcjResidual(zcjResidual);// 资产家剩余数量不变
							paramCopy.setChpResidual(chpResidual - 1);// chp剩余数量减一
							paramCopy.setGoldCreditResidual(goldCreditResidual);// 金信剩余数量不变
							paramCopy.setP2pResidual(p2pResidual);// P2P剩余数量不变
							paramCopy.setZcjResidual(zcjResidual);// 资产家剩余数量不变
						} else if (i % 4 == 2) {
							resultFlg = KinnobuQuotaLConstant.KINFLAG;// 金信标识
							param.setChpResidual(chpResidual);// chp剩余数量不变
							param.setGoldCreditResidual(goldCreditResidual - 1);// 金信剩余数量减一
							param.setP2pResidual(p2pResidual);// P2P剩余数量不变
							param.setZcjResidual(zcjResidual);// 资产家剩余数量不变
							paramCopy.setChpResidual(chpResidual);// chp剩余数量不变
							paramCopy.setGoldCreditResidual(goldCreditResidual - 1);// 金信剩余数量减一
							paramCopy.setP2pResidual(p2pResidual);// P2P剩余数量不变
							paramCopy.setZcjResidual(zcjResidual);// 资产家剩余数量不变
						} else if (i % 4 == 3) {
							resultFlg = KinnobuQuotaLConstant.P2PFLAG;// P2P标识
							param.setChpResidual(chpResidual);// chp剩余数量不变
							param.setGoldCreditResidual(goldCreditResidual);// 金信剩余数量不变
							param.setP2pResidual(p2pResidual - 1);// P2P剩余数量减一
							param.setZcjResidual(zcjResidual);// 资产家剩余数量不变
							paramCopy.setChpResidual(chpResidual);// chp剩余数量不变
							paramCopy.setGoldCreditResidual(goldCreditResidual);// 金信剩余数量不变
							paramCopy.setP2pResidual(p2pResidual - 1);// P2P剩余数量减一
							paramCopy.setZcjResidual(zcjResidual);// 资产家剩余数量不变
							if (i == 3) {
								paramCopy.setP2p1(p2p1 - 1);
								if (positXyj == 1) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 7) {
								paramCopy.setP2p2(p2p2 - 1);
								if (positXyj == 3) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 11) {
								paramCopy.setP2p3(p2p3 - 1);
								if (positXyj == 5) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 15) {
								paramCopy.setP2p4(p2p4 - 1);
								if (positXyj == 7) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 19) {
								paramCopy.setP2p5(p2p5 - 1);
								if (positXyj == 9) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							}
						} else {
							resultFlg = KinnobuQuotaLConstant.ZCJFLAG;// 资产家标识
							param.setChpResidual(chpResidual);// chp剩余数量不变
							param.setGoldCreditResidual(goldCreditResidual);// 金信剩余数量不变
							param.setP2pResidual(p2pResidual);// P2P剩余数量不变
							param.setZcjResidual(zcjResidual - 1);// 资产家剩余数量减一
							paramCopy.setChpResidual(chpResidual);// chp剩余数量不变
							paramCopy.setGoldCreditResidual(goldCreditResidual);// 金信剩余数量不变
							paramCopy.setP2pResidual(p2pResidual);// P2P剩余数量不变
							paramCopy.setZcjResidual(zcjResidual - 1);// 资产家剩余数量减一
							if (i == 4) {
								paramCopy.setZcj1(zcj1 - 1);
								if (positXyj == 2) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 8) {
								paramCopy.setZcj2(zcj2 - 1);
								if (positXyj == 4) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 12) {
								paramCopy.setZcj3(zcj3 - 1);
								if (positXyj == 6) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 16) {
								paramCopy.setZcj4(zcj4 - 1);
								if (positXyj == 8) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 20) {
								paramCopy.setZcj5(zcj5 - 1);
								if (positXyj == 10) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							}
						}
						param.setPosit(i);// 当前位置坐标
						param.setPositNum(positNum - 1);// 当前位置剩余数量减一
						paramCopy.setPosit(i);// 当前位置坐标
						paramCopy.setPositNum(positNum - 1);// 当前位置剩余数量减一
						break;
					} else {
						if (i < 20) {
							positNum = slsxArray[i];// 取下一位置的剩余数量
							continue;
						} else {
							resultFlg = KinnobuQuotaLConstant.NONE;// 数量为零
							param.setPosit(i + 1);// 当前位置坐标
							param.setChpResidual(null);// chp剩余数量为零
							param.setGoldCreditResidual(null);// 金信剩余数量为零
							param.setP2pResidual(null);// P2P剩余数量为零
							param.setZcjResidual(null);// 资产家剩余数量为零
							paramCopy.setPosit(i + 1);// 当前位置坐标
							paramCopy.setChpResidual(null);// chp剩余数量为零
							paramCopy.setGoldCreditResidual(null);// 金信剩余数量为零
							paramCopy.setP2pResidual(null);// P2P剩余数量为零
							paramCopy.setZcjResidual(null);// 资产家剩余数量为零
							break;
						}
					}
				}
				param.preUpdate();
				paramCopy.preUpdate();
				int updateNum = quotaLimitDao.updateData(param);
				int updateNumCopy = quotaLimitDao.updateDataNew(paramCopy);
				if (updateNum == 0 || updateNumCopy == 0) {
					throw new ServiceException("更新渠道标识上限标识表失败！");
				}
			}
		} else {
			resultFlg = KinnobuQuotaLConstant.RESULT_UNUSE_FLG;// 未启用渠道标识
		}
		return resultFlg;
	}

	/**
	 * ************************************** 按照上限1，2，3，4，5，6，7，8，9，10 的顺序进行标识 *
	 * P2P 1 3 5 7 9 * ZCJ 2 4 6 8 10 * **************************************
	 * 对比P2P和资产家剩余次数 2016年10月25日
	 * 
	 * @param flowView
	 */
	public String checkKinnobusXyj() {
		String resultFlg = KinnobuQuotaLConstant.NONE;// 设置标识为：剩余数量全部为0
		QuotaLimit quotaLimitResult = quotaLimitDao.getDataNew(99);
		if (quotaLimitResult != null) {
			int p2p1 = quotaLimitResult.getP2p1(); // P2P上限数量1
			int p2p2 = quotaLimitResult.getP2p2(); // P2P上限数量2
			int p2p3 = quotaLimitResult.getP2p3(); // P2P上限数量3
			int p2p4 = quotaLimitResult.getP2p4(); // P2P上限数量4
			int p2p5 = quotaLimitResult.getP2p5(); // P2P上限数量5
			int p2pResidual = quotaLimitResult.getP2pResidual(); // P2P剩余数量
			int zcj1 = quotaLimitResult.getZcj1(); // 资产家上限数量1
			int zcj2 = quotaLimitResult.getZcj2(); // 资产家上限数量2
			int zcj3 = quotaLimitResult.getZcj3(); // 资产家上限数量3
			int zcj4 = quotaLimitResult.getZcj4(); // 资产家上限数量4
			int zcj5 = quotaLimitResult.getZcj5(); // 资产家上限数量5
			int zcjResidual = quotaLimitResult.getZcjResidual(); // 资产家剩余数量
			int positXyj = quotaLimitResult.getPositXyj();// 信易借当前位置
			int positNumXyj = 0;// 信易借当前位置剩余上限数量
			Integer x = quotaLimitResult.getPositNumXyj();// 信易借当前位置剩余上限数量
			if (x != null) {
				positNumXyj = x;
			}
			int posit = quotaLimitResult.getPosit();// 当前位置
			int positNum = quotaLimitResult.getPositNum();// 当前位置数量
			int[] slsxArray = new int[] { p2p1, zcj1, p2p2, zcj2, p2p3, zcj3, p2p4, zcj4, p2p5, zcj5 };
			if (positXyj > 10) {
				resultFlg = KinnobuQuotaLConstant.NONE;// 数量为零
			} else {
				QuotaLimit param = new QuotaLimit();
				QuotaLimit paramCopy = new QuotaLimit();
				param.setId(quotaLimitResult.getId());// 设置ID
				param.setVersion(quotaLimitResult.getVersion());// 设置version
				paramCopy.setId(quotaLimitResult.getId());// 设置ID
				paramCopy.setVersion(quotaLimitResult.getVersion());// 设置version
				for (int i = positXyj; i < 11; i++) {
					if (positNumXyj > 0) {
						if (i % 2 == 1) {
							resultFlg = KinnobuQuotaLConstant.P2PFLAG;// P2P标识
							param.setP2pResidual(p2pResidual - 1);// P2P剩余数量减一
							param.setZcjResidual(zcjResidual);// 资产家剩余数量不变
							paramCopy.setP2pResidual(p2pResidual - 1);// P2P剩余数量减一
							paramCopy.setZcjResidual(zcjResidual);// 资产家剩余数量不变
							if (i == 1) {
								paramCopy.setP2p1(p2p1 - 1);
								if (posit == 3) {
									param.setPosit(3);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(3);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 3) {
								paramCopy.setP2p2(p2p2 - 1);
								if (posit == 7) {
									param.setPosit(7);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(7);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 5) {
								paramCopy.setP2p3(p2p3 - 1);
								if (posit == 11) {
									param.setPosit(11);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(11);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 7) {
								paramCopy.setP2p4(p2p4 - 1);
								if (posit == 15) {
									param.setPosit(15);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(15);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 9) {
								paramCopy.setP2p5(p2p5 - 1);
								if (posit == 19) {
									param.setPosit(19);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(19);
									paramCopy.setPositNum(positNum - 1);
								}
							}
						} else {
							resultFlg = KinnobuQuotaLConstant.ZCJFLAG;// 资产家标识
							param.setP2pResidual(p2pResidual);// P2P剩余数量不变
							param.setZcjResidual(zcjResidual - 1);// 资产家剩余数量减一
							paramCopy.setP2pResidual(p2pResidual);// P2P剩余数量不变
							paramCopy.setZcjResidual(zcjResidual - 1);// 资产家剩余数量减一
							if (i == 2) {
								paramCopy.setZcj1(zcj1 - 1);
								if (posit == 4) {
									param.setPosit(4);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(4);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 4) {
								paramCopy.setZcj2(zcj2 - 1);
								if (posit == 8) {
									param.setPosit(8);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(8);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 6) {
								paramCopy.setZcj3(zcj3 - 1);
								if (posit == 12) {
									param.setPosit(12);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(12);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 8) {
								paramCopy.setZcj4(zcj4 - 1);
								if (posit == 16) {
									param.setPosit(16);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(16);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 10) {
								paramCopy.setZcj5(zcj5 - 1);
								if (posit == 20) {
									param.setPosit(20);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(20);
									paramCopy.setPositNum(positNum - 1);
								}
							}
						}
						param.setPositXyj(i);// 当前位置坐标
						param.setPositNumXyj(positNumXyj - 1);// 当前位置剩余数量减一
						paramCopy.setPositXyj(i);// 当前位置坐标
						paramCopy.setPositNumXyj(positNumXyj - 1);// 当前位置剩余数量减一
						break;
					} else {
						if (i < 10) {
							positNumXyj = slsxArray[i];// 取下一位置的剩余数量
							continue;
						} else {
							resultFlg = KinnobuQuotaLConstant.NONE;// 数量为零
							param.setPositXyj(i + 1);// 当前位置坐标
							param.setP2pResidual(null);// P2P剩余数量为零
							param.setZcjResidual(null);// 资产家剩余数量为零
							paramCopy.setPositXyj(i + 1);// 当前位置坐标
							paramCopy.setP2pResidual(null);// P2P剩余数量为零
							paramCopy.setZcjResidual(null);// 资产家剩余数量为零
							break;
						}
					}
				}
				param.preUpdate();
				paramCopy.preUpdate();
				int updateNum = quotaLimitDao.updateData(param);
				int updateNumCopy = quotaLimitDao.updateDataNew(paramCopy);
				if (updateNum == 0 || updateNumCopy == 0) {
					throw new ServiceException("更新信易借产品渠道标识上限标识表失败！");
				}
			}
		} else {
			resultFlg = KinnobuQuotaLConstant.RESULT_UNUSE_FLG;// 未启用渠道标识
		}
		return resultFlg;
	}

	public QuotaLimit getResult() {
		return quotaLimitDao.getData(new BigDecimal(99));
	}

}
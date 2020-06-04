package com.creditharmony.approve.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.creditharmony.encdecShell.clinet.TcpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * 敏感信息加密解密
 * Created by IntelliJ IDEA.
 * User: wangpeng
 * Date: 2016/12/9
 * Time: 17:14
 */
public class CryptoUtils {

    // 日志
    private static final Logger log = LoggerFactory.getLogger(CryptoUtils.class);

    public final static String SYS_CAR = "carLoan";
    public final static String DBNAME_CAR = "loancj";

    public final static String SYS = "srdbloan";
    public final static String DBNAME = "loanxj";

    private static long now = System.nanoTime();

    /**
     * 加密手机号
     * @param mphones
     * @return
     */
    public static String encryptPhones(String mphones, String tabname, String enccols){
        JSONObject json = encrypt("", "", "", "", now + "",
                "", mphones, "", "", tabname, enccols);
        return (String) json.get("MPHONES");
    }

    /**
     * 解密手机号
     * @param mphones
     * @return
     */
    public static String decryptPhones(String mphones, String tabname, String enccols){
        JSONObject json = decrypt("", "", "", "", now + "",
                "", mphones, "", "", tabname, enccols);
        String result = (String) json.get("MPHONES");
        if(result != null){
            return result.substring(0, result.length()-1);
        }
        return "";
    }

    public static String getEncryptStr(String name, String flag, String ids, String userCode, String createby,
                                 String objSig, String mphones, String psCode,
                                 String tphones, String tabname, String enccols){
        JSONObject json = encrypt("", "", "", "", now + "",
                "", mphones, "", "", tabname, enccols);
        return (String) json.get(name);
    }

    public static String getDecryptStr(String name, String flag, String ids, String userCode, String createby,
                                       String objSig, String mphones, String psCode,
                                       String tphones, String tabname, String enccols){
        JSONObject json = decrypt("", "", "", "", now + "",
                "", mphones, "", "", tabname, enccols);
        return (String) json.get(name);
    }

    /**
     * 加密手机号
     * @param mphones
     * @return
     */
    public static JSONObject encrypt(String flag, String ids, String userCode, String createby,
                                String objSig, String mphones, String psCode,
                                String tphones, String tabname, String enccols){
        return encrypt(flag, ids, userCode, createby, now + "",
                objSig, mphones, psCode, tphones, tabname, enccols);
    }

    /**
     *
     * @param mphones
     * @return
     */
    public static JSONObject decrypt(String flag, String ids, String userCode, String createby,
                                String objSig, String mphones, String psCode,
                                String tphones, String tabname, String enccols){
        return decrypt(flag, ids, userCode, createby, now + "",
                objSig, mphones, psCode, tphones, tabname, enccols);
    }

    /**
     *
     * @param flag
     * @param ids
     * @param userCode
     * @param createby
     * @param createTime
     * @param objSig
     * @param mphones
     * @param psCode
     * @param tphones
     * @param tabname
     * @param enccols
     * @return
     */
    private static JSONObject encrypt(String flag, String ids, String userCode, String createby,
                                 String createTime, String objSig, String mphones, String psCode,
                                 String tphones, String tabname, String enccols){
        try {
            return crypto(0, flag, ids, userCode, createby, createTime, objSig, mphones, psCode, tphones, tabname, enccols);
        } catch (Exception ex) {
            log.error("车借敏感信息加密出错了。。。", ex);
            log.error("参数：flag = [" + flag + "], ids = [" + ids + "], userCode = [" + userCode + "], createby = ["
                    + createby + "], createTime = [" + createTime + "], objSig = [" + objSig + "], mphones = [" + mphones + "], psCode = ["
                    + psCode + "], tphones = [" + tphones + "], tabname = [" + tabname + "], enccols = [" + enccols + "]");
        }
        return null;
    }

    /**
     *
     * @param flag
     * @param ids
     * @param userCode
     * @param createby
     * @param createTime
     * @param objSig
     * @param mphones
     * @param psCode
     * @param tphones
     * @param tabname
     * @param enccols
     * @return
     */
    private static JSONObject decrypt(String flag, String ids, String userCode, String createby,
                                  String createTime, String objSig, String mphones, String psCode,
                                  String tphones, String tabname, String enccols){
        try {
            return crypto(1, flag, ids, userCode, createby, createTime, objSig, mphones, psCode, tphones, tabname, enccols);
        } catch (Exception ex) {
            log.error("车借敏感信息解密出错了。。。", ex);
            log.error("参数：flag = [" + flag + "], ids = [" + ids + "], userCode = [" + userCode + "], createby = ["
                    + createby + "], createTime = [" + createTime + "], objSig = [" + objSig + "], mphones = [" + mphones + "], psCode = ["
                    + psCode + "], tphones = [" + tphones + "], tabname = [" + tabname + "], enccols = [" + enccols + "]");

        }
        return null;
    }


    private static JSONObject crypto(int fg, String flag, String ids, String userCode, String createby,
                                     String createTime, String objSig, String mphones, String psCode, String tphones,
                                     String tabname, String enccols) throws Exception{
        try {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("flg", flag); //暂空
            map.put("SYS", tabname.toUpperCase().contains("JK") ? SYS : SYS_CAR);//请求加密的系统
            map.put("IDS", ids);//用户ID
            map.put("USERCODE", userCode);//用户标记、用户编码
            map.put("CREATEUSER", createby); //创建人
            map.put("CREATTIME", createTime);//创建时间 必须long类型
            map.put("OBJSIG", objSig);//用户附加字段
            map.put("MPHONES", mphones+",");//手机号 原文 13716396384,13716396385,
            map.put("PSLCODE", psCode);//证件号
            map.put("TPHONES", tphones);//固定电话号
            map.put("DBNAME", tabname.toUpperCase().contains("JK") ? DBNAME : DBNAME_CAR); //所属库
            map.put("TABNAME", tabname);//所属表
            map.put("ENCCOLS", enccols);//加密字段 （单表多字段必须使用半角逗号分隔）
            String str = JSON.toJSONString(map);
            //加密对象
            TcpClient client = new TcpClient();
            //加密结果
            String strRe = "";
            if(fg == 0){
                strRe = client.disEncrypt(str);
            }
            if(fg == 1){
                strRe = client.disDecrypt(str);
            }
            return JSON.parseObject(strRe);
        } catch (Exception ex) {
            throw ex;
        }
    }

}

DROP FUNCTION jk.verifyRepeate(character varying);
/**
 * 决策时查重
 * @修改履历  V1.0 2016/2/1 创建
 *        V2.0 
 */
CREATE OR REPLACE FUNCTION jk.verifyRepeate(loancode character varying, OUT outvalue INT)
RETURNS INT AS
$BODY$
DECLARE BACKVALUE INT:=0;--返回值
DECLARE T_NUM INT :=0;--当前行数
BEGIN

/*****************************信息入池开始******************************/

--单位名称入池
INSERT INTO JK.T_JK_WORK_NAME_POOL(ID,LOAN_CODE,R_ID,R_NAME,RELATION,DICT_CUSTOMER_TYPE,WORK_NAME,RESOURCE,INCREMENT_ID,REPEAT_VIEW_TYPE)
SELECT GETUUID32(),LOAN_CODE,CUSTOMER_CODE,N_NAME,N_RELATION,DICT_CUSTOMER_TYPE,COMP_NAME,RESOURCE,O_ID,REPEAT_VIEW_TYPE
FROM (		-- 单位名称
			SELECT A.R_CUSTOMER_COBORROWER_ID AS CUSTOMER_CODE,A.LOAN_CODE,A.DICT_CUSTOMER_TYPE AS DICT_CUSTOMER_TYPE,JK.FILTERCOMPANYNAME(A.WORK_UNITNAME) AS COMP_NAME,
			       CASE WHEN A.DICT_CUSTOMER_TYPE='0' THEN B.CUSTOMER_NAME ELSE C.COBO_NAME END AS N_NAME,
			       CASE WHEN A.DICT_CUSTOMER_TYPE='0' THEN '本人' ELSE '共借人' END AS N_RELATION,
			       A.ID AS O_ID,'JK.T_JK_TEL_AUDIT_WORK' AS RESOURCE,'单位名称' AS REPEAT_VIEW_TYPE
			FROM JK.T_JK_TEL_AUDIT_WORK A 
			LEFT JOIN JK.T_JK_LOAN_CUSTOMER B ON A.LOAN_CODE=B.LOAN_CODE AND A.R_CUSTOMER_COBORROWER_ID=B.ID
			LEFT JOIN JK.T_JK_LOAN_COBORROWER C ON A.LOAN_CODE=C.LOAN_CODE AND A.R_CUSTOMER_COBORROWER_ID=C.ID
			WHERE COALESCE(A.WORK_UNITNAME,'')<>''  AND A.LOAN_CODE=loancode AND A.EDIT_REMARK='1'
				AND (A.IS_IN_POOL='0' OR COALESCE(A.IS_IN_POOL,'')='') AND A.DICT_CHECK_TYPE <> '2'	
	);
--电话入池
INSERT INTO JK.T_JK_PHONE_POOL(ID,LOAN_CODE,R_ID,R_NAME,RELATION,DICT_CUSTOMER_TYPE,PHONE,RESOURCE,INCREMENT_ID,REPEAT_VIEW_TYPE)
SELECT GETUUID32(),LOAN_CODE,CUSTOMER_CODE,N_NAME,N_RELATION,DICT_CUSTOMER_TYPE,PHONE_NUMBER,RESOURCE,O_ID,REPEAT_VIEW_TYPE
FROM (
		SELECT A.R_CUSTOMER_COBORROWER_ID AS CUSTOMER_CODE,A.LOAN_CODE,A.DICT_CUSTOMER_TYPE,A.DHGXSH_TEL AS PHONE_NUMBER,
		CONCAT(B.LABEL,'-',C.LABEL,'(',A.NAME,')手机号码') AS REPEAT_VIEW_TYPE,A.NAME AS N_NAME,
		CONCAT(B.LABEL,'(',C.LABEL,')') AS N_RELATION, A.ID AS O_ID,'JK.T_JK_DHZH_DHGXSH' AS RESOURCE
		FROM  JK.T_JK_DHZH_DHGXSH A 
		LEFT JOIN JK.T_GL_DICT B ON B.TYPE='jk_relation_type' AND A.DICT_RELATION_TYPE = B.VALUE
		LEFT JOIN JK.T_GL_DICT C ON C.PARENT_ID=B.ID AND A.LOAN_MAN_RELATION = C.VALUE 
		WHERE A.LOAN_CODE=loancode AND COALESCE(A.DHGXSH_TEL,'')<>'' AND A.DICT_TEL_SOURCE='01' AND A.EDIT_REMARK='1'
			AND (A.IS_IN_POOL='0' OR COALESCE(A.IS_IN_POOL,'')='') AND A.DICT_CHECK_TYPE <> '2'	
		UNION ALL
		SELECT A.R_CUSTOMER_COBORROWER_ID AS CUSTOMER_CODE,A.LOAN_CODE,A.DICT_CUSTOMER_TYPE,B.BRHS_PHONE AS PHONE_NUMBER,
		CASE WHEN B.TYPE='0' THEN '家庭固话' ELSE '手机号码' END AS REPEAT_VIEW_TYPE,CASE WHEN A.DICT_CUSTOMER_TYPE='0' THEN C.CUSTOMER_NAME ELSE D.COBO_NAME END AS N_NAME,
		CASE WHEN A.DICT_CUSTOMER_TYPE='0' THEN '本人' ELSE '共借人' END AS N_RELATION,
		B.ID AS O_ID,'JK.T_JK_DHZH_BRHS_DHXX' AS RESOURCE
		FROM  JK.T_JK_DHZH_BRHS A 
		INNER JOIN JK.T_JK_DHZH_BRHS_DHXX B ON A.ID=B.R_BRHS_ID
		LEFT JOIN JK.T_JK_LOAN_CUSTOMER C ON A.LOAN_CODE=C.LOAN_CODE
		LEFT JOIN JK.T_JK_LOAN_COBORROWER D ON A.LOAN_CODE=D.LOAN_CODE
		WHERE A.LOAN_CODE=loancode AND COALESCE(B.BRHS_PHONE,'')<>'' AND B.SOURCE='01' AND B.EDIT_REMARK='1'
			AND (B.IS_IN_POOL='0' OR COALESCE(B.IS_IN_POOL,'')='')  AND A.DICT_CHECK_TYPE <> '2'	
		UNION ALL
		SELECT A.R_CUSTOMER_COBORROWER_ID AS CUSTOMER_CODE,A.LOAN_CODE,A.DICT_CUSTOMER_TYPE,B.WORK_UNIT_TEL AS PHONE_NUMBER,
		'单位固话' AS REPEAT_VIEW_TYPE,CASE WHEN A.DICT_CUSTOMER_TYPE='0' THEN C.CUSTOMER_NAME ELSE D.COBO_NAME END AS N_NAME,
		CASE WHEN A.DICT_CUSTOMER_TYPE='0' THEN '本人' ELSE '共借人' END AS N_RELATION,
		B.ID AS O_ID,'JK.T_JK_DHZH_WORK_TELNUM' AS RESOURCE
		FROM  JK.T_JK_TEL_AUDIT_WORK A 
		INNER JOIN JK.T_JK_DHZH_WORK_TELNUM B ON A.ID=B.WORK_ID
		LEFT JOIN JK.T_JK_LOAN_CUSTOMER C ON A.LOAN_CODE=C.LOAN_CODE
		LEFT JOIN JK.T_JK_LOAN_COBORROWER D ON A.LOAN_CODE=D.LOAN_CODE
		WHERE A.LOAN_CODE=loancode AND COALESCE(B.WORK_UNIT_TEL,'')<>'' AND B.WORK_TEL_SOURCE='01' AND B.EDIT_REMARK='1'
			AND (B.IS_IN_POOL='0' OR COALESCE(B.IS_IN_POOL,'')='') AND A.DICT_CHECK_TYPE <> '2'	
	);
/*****************************信息入池结束******************************/
/*************************更新查重入池字段开始**************************/
--单位固话
UPDATE JK.T_JK_DHZH_WORK_TELNUM B SET IS_IN_POOL='1'
FROM JK.T_JK_TEL_AUDIT_WORK A
WHERE A.LOAN_CODE=loancode AND A.ID=B.WORK_ID AND B.WORK_TEL_SOURCE='01' AND B.EDIT_REMARK='1' 
	AND (B.IS_IN_POOL='0' OR COALESCE(B.IS_IN_POOL,'')='');
--本人手机号
UPDATE JK.T_JK_DHZH_BRHS_DHXX B SET IS_IN_POOL='1'
FROM JK.T_JK_DHZH_BRHS A 
WHERE A.LOAN_CODE=loancode AND A.ID=B.R_BRHS_ID AND B.SOURCE='01' AND B.EDIT_REMARK='1'
	AND (B.IS_IN_POOL='0' OR COALESCE(B.IS_IN_POOL,'')='');
--联系人手机号
UPDATE JK.T_JK_DHZH_DHGXSH SET IS_IN_POOL='1'
WHERE LOAN_CODE=loancode AND DICT_TEL_SOURCE='01' AND EDIT_REMARK='1'
	AND (IS_IN_POOL='0' OR COALESCE(IS_IN_POOL,'')='');
--单位信息
UPDATE JK.T_JK_TEL_AUDIT_WORK SET IS_IN_POOL='1'
WHERE LOAN_CODE=loancode AND EDIT_REMARK='1'
	AND (IS_IN_POOL='0' OR COALESCE(IS_IN_POOL,'')='');
/*************************更新查重入池字段结束**************************/
--返回结果
outvalue:=BACKVALUE;
END;
$BODY$
LANGUAGE plsrsql VOLATILE
COST 100;

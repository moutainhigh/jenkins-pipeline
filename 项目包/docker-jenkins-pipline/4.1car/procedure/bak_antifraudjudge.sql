-- Function: antifraudjudge(character varying)

DROP FUNCTION antifraudjudge(character varying);


CREATE OR REPLACE FUNCTION antifraudjudge(IN loancode character varying, OUT outvalue character varying)
  RETURNS character varying AS
$BODY$

	declare stmt record;                                            -- 声明被执行SQL变量
	        _flag varchar (50);                                     -- 用于区分被插入表的变更
	        exet record;                                            -- 声明被执行SQL返回结果变更
	        message text;                                           -- 处理结果信息
                sqltxt varchar(5000);                                            -- sql
                idstr varchar(36);                                      -- 生成表中的ID
                opuser varchar (50);                                    -- 操作员
                v_count int;
                r_offend_id varchar(32);                                --每执行一次生成一个ID，弃用规则ID
	begin
                message:='0';
                
		for stmt in select sql_auto, rules_tables,rules_type,id,rules_content,rules_relation from jk.t_jk_rules_config
		loop 
                  r_offend_id:=getuuid32();
		  _flag := stmt.rules_type;
                  opuser:='sql';
                  sqltxt:=replace(stmt.sql_auto,'&lt;','<');
                  sqltxt:=replace(sqltxt,'&gt;','>');
                  sqltxt:=replace(sqltxt,'{loan_code}',''||loancode||'');
                  sqltxt:=replace(sqltxt,'NULL AS idstr','getuuid32()');
                  sqltxt:=replace(sqltxt,'NULL AS stmtid',''''||r_offend_id||'''');
                  sqltxt:=replace(sqltxt,'NULL as rules_content',''''||stmt.rules_content||'''');
                  sqltxt:=replace(sqltxt,'NULL as rules_relation',''''||stmt.rules_relation||'''');
                  sqltxt:=replace(sqltxt,'NULL as CREATE_BY',''''||opuser||'''');
                  sqltxt:=replace(sqltxt,'NULL as CREATE_TIME',''''||sysdate||'''');
                  sqltxt:=replace(sqltxt,'NULL as MODIFY_BY',''''||opuser||'''');
                  sqltxt:=replace(sqltxt,'NULL as MODIFY_TIME',''''||sysdate||'''');
     
                   if _flag = '1' then         -- 反欺诈_销售人员信息匹配
                    
		    execute 'insert into jk.t_jk_antifraud_offend_sales(id, r_offend_id, loan_code, offend_sales_name, offend_tel, offend_name, work_flag, dict_job_grade, create_by, create_time, modify_by, modify_time)'||sqltxt;
                   elseif _flag = '2' then     -- 反欺诈_黑名单数据库信息匹配
	            execute 'insert into jk.t_jk_antifraud_blacklist(id, r_offend_id, loan_code, dict_mark_type, dict_blacklist_type, blacklist_msg, blacklist_risk_msg, blacklist_relation,add_black_time, add_black_type, create_by, create_time, modify_by, modify_time) '||sqltxt;    
                   elseif _flag = '3' then     -- 反欺诈_查重内容
                    execute  'insert into jk.t_jk_antifraud_repeat(ID,R_OFFEND_ID,LOAN_CODE,CUSTOMER_NAME,REPEAT_INTO_TIME,REPEAT_RESULT,REPEAT_RESULT_MSG,REPEAT_RELATION,CREATE_BY,CREATE_TIME,MODIFY_BY,MODIFY_TIME) '||sqltxt;     
                   
		   elseif _flag = '4' then     -- 反欺诈_案件信息表
		    execute 'insert into jk.t_jk_antifraud_case(id, r_judge_id, case_code, loan_code, case_handle_day, case_handle_by, loan_customer_name, dict_antifraud_type, case_risk_msg, create_by, create_time, modify_by, modify_time) '||sqltxt; 
		   end if;
                    GET DIAGNOSTICS v_count = ROW_COUNT;
                   
                    if v_count>0 then
                       INSERT INTO t_jk_antifraud_offend_info(id, loan_code, rules_code, dict_offend_type, offend_msg, offend_status, 
                       offend_relieve_status, offend_remark, create_by, create_time,  modify_by, modify_time)
                       VALUES (r_offend_id, loancode, stmt.rules_type, stmt.rules_type, stmt.rules_content, '0', '0', '', opuser, sysdate, opuser, sysdate);
                       message := '1';
                    end if;
                  

		end loop;
		
                OutValue:=message;
	end;
    $BODY$
  LANGUAGE plsrsql VOLATILE
  COST 100;

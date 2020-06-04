DROP FUNCTION filtercompanyname(character varying);
/**
 * 查重单位名称过滤关键字
 * @修改履历  V1.0 2016/2/1 创建
 *        V2.0 
 */
CREATE OR REPLACE FUNCTION filtercompanyname(companyname character varying)
RETURNS character varying AS
$BODY$
DECLARE
p_result character varying := companyName;
BEGIN
	p_result:=replace(p_result,'股份','');
	p_result:=replace(p_result,'有限','');
	p_result:=replace(p_result,'公司','');
	p_result:=replace(p_result,'责任','');
	p_result:=replace(p_result,'省','');
	p_result:=replace(p_result,'市','');
	p_result:=replace(p_result,'经营部','');
	p_result:=replace(p_result,'店','');
	p_result:=replace(p_result,'中心','');
	p_result:=replace(p_result,'厂','');
	p_result:=replace(p_result,'超市','');
	p_result:=replace(p_result,'个体','');
	return p_result;
END;
$BODY$
LANGUAGE plsrsql VOLATILE
COST 100;
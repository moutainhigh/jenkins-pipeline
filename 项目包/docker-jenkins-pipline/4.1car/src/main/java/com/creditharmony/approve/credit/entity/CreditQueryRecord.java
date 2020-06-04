package com.creditharmony.approve.credit.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 征信查询记录
 * @Class Name CreditQueryRecord
 * @author zhanghu
 * @Create In 2016年1月29日
 */
public class CreditQueryRecord extends DataEntity<CreditQueryRecord>{

	private static final long serialVersionUID = 1L;
	
	
    /**
     * 主键id 
     */
    private String id;

    /**
     * 关联ID
     */
    private String relationId;

    /**
     * 区分
     */
    private String distinguish;

    /**
     * 查询日期
     */
    private Date queryDay;

    /**
     * 查询原因 
     */
    private String queryType;

    /**
     * 爬虫HTML
     */
    private String htmlUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId == null ? null : relationId.trim();
    }

    public String getDistinguish() {
        return distinguish;
    }

    public void setDistinguish(String distinguish) {
        this.distinguish = distinguish == null ? null : distinguish.trim();
    }

    public Date getQueryDay() {
        return queryDay;
    }

    public void setQueryDay(Date queryDay) {
        this.queryDay = queryDay;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType == null ? null : queryType.trim();
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl == null ? null : htmlUrl.trim();
    }
}
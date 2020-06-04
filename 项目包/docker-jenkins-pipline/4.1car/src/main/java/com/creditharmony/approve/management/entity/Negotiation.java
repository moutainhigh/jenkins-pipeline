package com.creditharmony.approve.management.entity;


import com.creditharmony.core.persistence.TreeEntity;

public class Negotiation extends TreeEntity<Negotiation>{
	private static final long serialVersionUID = 1L;

	private String id;                  // id
    private String negotiationCode;     // 协商编码
    private String negotiationName;     // 协商名称
    private String negotiationGrade;    // 协商类型
    //private String parentId;
    //private String createBy;
    //private Date createTime;
    //private String modifyBy;
    //private Date modifyTime;
    
    public Negotiation(){
		super();
		this.sort = 30;
	}
	public Negotiation(String id){
		super(id);
	}
	public Negotiation getParent() {
		return parent;
	}
	public void setParent(Negotiation parent) {
		this.parent = parent;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNegotiationCode() {
        return negotiationCode;
    }

    public void setNegotiationCode(String negotiationCode) {
        this.negotiationCode = negotiationCode == null ? null : negotiationCode.trim();
    }

    public String getNegotiationName() {
        return negotiationName;
    }

    public void setNegotiationName(String negotiationName) {
        this.negotiationName = negotiationName == null ? null : negotiationName.trim();
    }

    public String getNegotiationGrade() {
        return negotiationGrade;
    }

    public void setNegotiationGrade(String negotiationGrade) {
        this.negotiationGrade = negotiationGrade == null ? null : negotiationGrade.trim();
    }

    /*public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }*/
    
}
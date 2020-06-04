
package com.creditharmony.approve.newCar.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;


/**
 * 插件磁盘信息文件类
 * @Class Name FileDiskInfo
 * @author 王浩
 * @Create In 2016年5月5日
 */
public class NewFileDiskInfo extends DataEntity<NewFileDiskInfo>{      

    private static final long serialVersionUID = 1L;

    private Date startDate;
    
    private Date endDate;
    
    private String flagHj;
    
    private String flagHc;
    
    private String sysFlag;
    private String loanDirLevel;

    public String getLoanDirLevel() {
		return loanDirLevel;
	}

	public void setLoanDirLevel(String loanDirLevel) {
		this.loanDirLevel = loanDirLevel;
	}

	public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFlagHj() {
        return flagHj;
    }

    public void setFlagHj(String flagHj) {
        this.flagHj = flagHj;
    }

    public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

	public String getFlagHc() {
        return flagHc;
    }

    public void setFlagHc(String flagHc) {
        this.flagHc = flagHc;
    }    
    
}

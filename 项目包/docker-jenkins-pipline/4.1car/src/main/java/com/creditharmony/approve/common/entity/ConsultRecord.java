package com.creditharmony.approve.common.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 客户沟通日志信息
 * @Class Name ConsultRecord
 * @author zhangping
 * @Create In 2015年11月9日
 */
public class ConsultRecord extends DataEntity<ConsultRecord> {

	private static final long serialVersionUID = -4826593454690408310L;
	
    private Consult consult;               //客户咨询
    private String consLoanRecord;			//沟通记录							
	private String consOperStatus;			//下一步操作状态							
	private String consCommunicateDate;		//沟通时间	
	public Consult getConsult() {
		return consult;
	}
	public void setConsult(Consult consult) {
		this.consult = consult;
	}
	public String getConsLoanRecord() {
		return consLoanRecord;
	}
	public void setConsLoanRecord(String consLoanRecord) {
		this.consLoanRecord = consLoanRecord;
	}
	public String getConsOperStatus() {
		return consOperStatus;
	}
	public void setConsOperStatus(String consOperStatus) {
		this.consOperStatus = consOperStatus;
	}
	public String getConsCommunicateDate() {
		return consCommunicateDate;
	}
	public void setConsCommunicateDate(String consCommunicateDate) {
		this.consCommunicateDate = consCommunicateDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((consCommunicateDate == null) ? 0 : consCommunicateDate
						.hashCode());
		result = prime * result
				+ ((consLoanRecord == null) ? 0 : consLoanRecord.hashCode());
		result = prime * result
				+ ((consOperStatus == null) ? 0 : consOperStatus.hashCode());
		result = prime * result + ((consult == null) ? 0 : consult.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultRecord other = (ConsultRecord) obj;
		if (consCommunicateDate == null) {
			if (other.consCommunicateDate != null)
				return false;
		} else if (!consCommunicateDate.equals(other.consCommunicateDate))
			return false;
		if (consLoanRecord == null) {
			if (other.consLoanRecord != null)
				return false;
		} else if (!consLoanRecord.equals(other.consLoanRecord))
			return false;
		if (consOperStatus == null) {
			if (other.consOperStatus != null)
				return false;
		} else if (!consOperStatus.equals(other.consOperStatus))
			return false;
		if (consult == null) {
			if (other.consult != null)
				return false;
		} else if (!consult.equals(other.consult))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ConsultRecord [consult=" + consult + ", consLoanRecord="
				+ consLoanRecord + ", consOperStatus=" + consOperStatus
				+ ", consCommunicateDate=" + consCommunicateDate + "]";
	}
	
     
}

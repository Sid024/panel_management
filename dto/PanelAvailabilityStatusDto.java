package com.zensar.panelmanagement.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




public class PanelAvailabilityStatusDto {
	
	private String email;
	private Date fromDate;
	private Date toDate;
	private int panelId;
	private String panelName;
	private String panalRole;
	private String interviewType;
	private String availabilityStatus;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public int getPanelId() {
		return panelId;
	}
	public void setPanelId(int panelId) {
		this.panelId = panelId;
	}
	public String getPanelName() {
		return panelName;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
	public String getPanalRole() {
		return panalRole;
	}
	public void setPanalRole(String panalRole) {
		this.panalRole = panalRole;
	}
	public String getInterviewType() {
		return interviewType;
	}
	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}
	public String getAvailabilityStatus() {
		return availabilityStatus;
	}
	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	public PanelAvailabilityStatusDto(String email, Date fromDate, Date toDate, int panelId, String panelName,
			String panalRole, String interviewType, String availabilityStatus) {
		super();
		this.email = email;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.panelId = panelId;
		this.panelName = panelName;
		this.panalRole = panalRole;
		this.interviewType = interviewType;
		this.availabilityStatus = availabilityStatus;
		
		
	}
	
	
	public PanelAvailabilityStatusDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PanalAvailabilityStatusDto [email=" + email + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", panelId=" + panelId + ", panelName=" + panelName + ", panalRole=" + panalRole + ", interviewType="
				+ interviewType + ", availabilityStatus=" + availabilityStatus + "]";
	}
	
	
	
	
}

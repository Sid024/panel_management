package com.zensar.pm.panel.dto;

import java.time.LocalDate;

public class PanelListDTO {
	private String panelId;
	private String panelName;
	private String panelEmail;
	private String panelContact;
	private String panelRole;
	private String interviewType;
	private LocalDate date;
	private String slotTime;
	private String availabilityStatus;

	public PanelListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPanelId() {
		return panelId;
	}

	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	public String getPanelEmail() {
		return panelEmail;
	}

	public void setPanelEmail(String panelEmail) {
		this.panelEmail = panelEmail;
	}

	public String getPanelContact() {
		return panelContact;
	}

	public void setPanelContact(String panelContact) {
		this.panelContact = panelContact;
	}

	public String getPanelRole() {
		return panelRole;
	}

	public void setPanelRole(String panelRole) {
		this.panelRole = panelRole;
	}

	public String getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getSlotTime() {
		return slotTime;
	}

	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
	}

	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public PanelListDTO(String panelId, String panelName, String panelEmail, String panelContact, String panelRole,
			String interviewType, LocalDate date, String slotTime, String availabilityStatus) {
		super();
		this.panelId = panelId;
		this.panelName = panelName;
		this.panelEmail = panelEmail;
		this.panelContact = panelContact;
		this.panelRole = panelRole;
		this.interviewType = interviewType;
		this.date = date;
		this.slotTime = slotTime;
		this.availabilityStatus = availabilityStatus;
	}

	@Override
	public String toString() {
		return "PanelListDTO [panelId=" + panelId + ", panelName=" + panelName + ", panelEmail=" + panelEmail
				+ ", panelContact=" + panelContact + ", panelRole=" + panelRole + ", interviewType=" + interviewType
				+ ", date=" + date + ", slotTime=" + slotTime + ", availabilityStatus=" + availabilityStatus + "]";
	}

}

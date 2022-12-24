package com.zensar.pm.panel.dto;

import java.time.LocalDate;
import java.util.Date;

public class PanelAvailabilityListDto {

	private String email;
	private LocalDate date;
	private String panelId;
	private String contact;
	private String slotTime;
	private String panelName;
	private String role;
	private String interviewType;
	private String availabilityStatus;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getPanelId() {
		return panelId;
	}

	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getSlotTime() {
		return slotTime;
	}

	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public PanelAvailabilityListDto(String email, LocalDate date, String panelId, String contact, String slotTime,
			String panelName, String role, String interviewType, String availabilityStatus) {
		super();
		this.email = email;
		this.date = date;
		this.panelId = panelId;
		this.contact = contact;
		this.slotTime = slotTime;
		this.panelName = panelName;
		this.role = role;
		this.interviewType = interviewType;
		this.availabilityStatus = availabilityStatus;
	}

	public PanelAvailabilityListDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}

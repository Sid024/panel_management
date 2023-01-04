package com.zensar.pm.panel.dto;

import java.time.LocalDate;
import java.util.Date;

public class PanelAvailabilityListDTO {

	private String email;
	private LocalDate date;
	private int panelId;
	private String contact;
	private String slotTime;
	private String panelName;
	private String role;
	private String interviewType;
	private String availabilityStatus;
	private int panelAvailabilityId;
	private String gradeId;
	private String fromTime;
	private String toTime;
	
	
	


	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public int getPanelAvailabilityId() {
		return panelAvailabilityId;
	}

	public void setPanelAvailabilityId(int panelAvailabilityId) {
		this.panelAvailabilityId = panelAvailabilityId;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

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

	public int getPanelId() {
		return panelId;
	}

	public void setPanelId(int panelId) {
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

	public PanelAvailabilityListDTO(String email, LocalDate date, int panelId, String contact, String slotTime,
			String panelName, String role, String interviewType, String availabilityStatus, int panelAvailabilityId,
			String gradeId) {
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
		this.panelAvailabilityId = panelAvailabilityId;
		this.gradeId = gradeId;
	}

	public PanelAvailabilityListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



}

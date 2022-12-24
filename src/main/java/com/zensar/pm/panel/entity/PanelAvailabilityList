package com.zensar.pm.panel.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;

@Entity
@Table(name = "panel_management")
public class PanelAvailabilityList {

	@Column(name = "email")
	private String email;

	private LocalDate date;

	@Id
	@Column(name = "Panel Id")
	private String panelId;

	@Column(name = "Panal name")
	private String panelName;

	@Column(name = "role")
	private String role;

	@Column(name = "Slot Time")
	private String slotTime;

	@Column(name = "Contact")
	private String contact;

	@Column(name = "Interview Type")
	private String interviewType;

	@Column(name = "Availability Status")
	private String availabilityStatus;

	public PanelAvailabilityList() {
		super();
	}

	public PanelAvailabilityList(String email, LocalDate date, String panelId, String panelName, String panalRole,
			String slotTime, String contact, String interviewType, String availabilityStatus) {
		super();
		this.email = email;
		this.date = date;
		this.panelId = panelId;
		this.panelName = panelName;
		this.role = panalRole;
		this.slotTime = slotTime;
		this.contact = contact;
		this.interviewType = interviewType;
		this.availabilityStatus = availabilityStatus;
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

	public String getPanalRole() {
		return role;
	}

	public void setPanalRole(String panalRole) {
		this.role = panalRole;
	}

	public String getSlotTime() {
		return slotTime;
	}

	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	@Override
	public String toString() {
		return "PanelAvailabilityList [email=" + email + ", date=" + date + ", panelId=" + panelId + ", panelName="
				+ panelName + ", panalRole=" + role + ", slotTime=" + slotTime + ", contact=" + contact
				+ ", interviewType=" + interviewType + ", availabilityStatus=" + availabilityStatus + "]";
	}

}

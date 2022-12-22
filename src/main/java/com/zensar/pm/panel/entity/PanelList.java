package com.zensar.pm.panel.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "panel_management")
public class PanelList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String panelId;
	private String panelName;
	private String panelEmail;
	private String panelContact;
	private String panelRole;
	private String interviewType;
	private LocalDate date;
	private String slotTime;
	private String availabilityStatus;

	/*
	 * PRIMARY KEY (panels_availability_id), FOREIGN KEY (panel_id) REFERENCES
	 * panels(id), FOREIGN KEY (availability_status_id) REFERENCES
	 * panels_availability_status(availability_status_id)
	 */
	public String getPanelId() {
		return panelId;
	}

	public void setPanelId(String panel_id) {
		this.panelId = panel_id;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panel_name) {
		this.panelName = panel_name;
	}

	public String getPanelEmail() {
		return panelEmail;
	}

	public void setPanelEmail(String panel_email) {
		this.panelEmail = panel_email;
	}

	public String getPanelContact() {
		return panelContact;
	}

	public void setPanelContact(String panel_contact) {
		this.panelContact = panel_contact;
	}

	public String getPanelRole() {
		return panelRole;
	}

	public void setPanelRole(String panel_role) {
		this.panelRole = panel_role;
	}

	public String getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(String interview_type) {
		this.interviewType = interview_type;
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

	public PanelList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PanelList(String panel_id, String panel_name, String panel_email, String panel_contact, String panel_role,
			String interview_type, LocalDate date, String slot_time, String availability_status) {
		super();
		this.panelId = panel_id;
		this.panelName = panel_name;
		this.panelEmail = panel_email;
		this.panelContact = panel_contact;
		this.panelRole = panel_role;
		this.interviewType = interview_type;
		this.date = date;
		this.slotTime = slot_time;
		this.availabilityStatus = availability_status;
	}

	@Override
	public String toString() {
		return "PannelAvailabilityList [panel_id=" + panelId + ", panel_name=" + panelName + ", panel_email="
				+ panelEmail + ", panel_contact=" + panelContact + ", panel_role=" + panelRole + ", interview_type="
				+ interviewType + ", date=" + date + ", Slot_time=" + slotTime + ", Availability_status="
				+ availabilityStatus + "]";
	}

	public void setSlotTime(String slot_time) {
		this.slotTime = slot_time;
	}

	public String getAvailabilityStatus() {
		return this.availabilityStatus;
	}

	public void setAvailabilityStatus(String availability_status) {
		this.availabilityStatus = availability_status;
	}

}

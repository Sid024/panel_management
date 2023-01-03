package com.zensar.pm.panel.dto;

import java.util.List;
import java.util.Objects;

public class PanelDTO {

	private int panelId;
	private String panelName;
	private String contact;
	private String email;
	private boolean isActive;
	private String grade;
	private String interviewType;
	public  String panelRoles;
	public List<Integer> listPanelId;
	public List<String> listPanelNames;
	
	public PanelDTO() {
		super();
	}

	public PanelDTO(int panelId, String panelName, String contact, String email, boolean isActive, String grade,
			String interviewType, String panelRoles, List<Integer> listPanelId, List<String> listPanelNames) {
		super();
		this.panelId = panelId;
		this.panelName = panelName;
		this.contact = contact;
		this.email = email;
		this.isActive = isActive;
		this.grade = grade;
		this.interviewType = interviewType;
		this.panelRoles = panelRoles;
		this.listPanelId = listPanelId;
		this.listPanelNames = listPanelNames;
	}

	public PanelDTO(int panelId, String panelName, String email, String contact, String grade, String panelRoles,
			String interviewType, boolean isActive) {
		this.panelId = panelId;
		this.panelName = panelName;
		this.contact = contact;
		this.email = email;
		this.isActive = isActive;
		this.grade = grade;
		this.panelRoles=panelRoles;
		this.interviewType=interviewType;
		
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}

	public String getPanelRoles() {
		return panelRoles;
	}

	public void setPanelRoles(String panelRoles) {
		this.panelRoles = panelRoles;
	}

	public List<Integer> getListPanelId() {
		return listPanelId;
	}

	public void setListPanelId(List<Integer> listPanelId) {
		this.listPanelId = listPanelId;
	}

	public List<String> getListPanelNames() {
		return listPanelNames;
	}

	public void setListPanelNames(List<String> listPanelNames) {
		this.listPanelNames = listPanelNames;
	}

	@Override
	public String toString() {
		return "PanelDTO [panelId=" + panelId + ", panelName=" + panelName + ", contact=" + contact + ", email=" + email
				+ ", isActive=" + isActive + ", grade=" + grade + ", interviewType=" + interviewType + ", panelRoles="
				+ panelRoles + ", listPanelId=" + listPanelId + ", listPanelNames=" + listPanelNames + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(contact, email, grade, interviewType, isActive, listPanelId, listPanelNames, panelId,
				panelName, panelRoles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PanelDTO other = (PanelDTO) obj;
		return Objects.equals(contact, other.contact) && Objects.equals(email, other.email)
				&& Objects.equals(grade, other.grade) && Objects.equals(interviewType, other.interviewType)
				&& isActive == other.isActive && Objects.equals(listPanelId, other.listPanelId)
				&& Objects.equals(listPanelNames, other.listPanelNames) && panelId == other.panelId
				&& Objects.equals(panelName, other.panelName) && Objects.equals(panelRoles, other.panelRoles);
	}
	
	
}

package com.zensar.pm.panel.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class PanelCandidateRolesEntity {
	@Id
	@GeneratedValue
	@Column(name = "panel_candidate_roles_id")
	private int id;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id") // FK column
	private InterviewType interviewType;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id") // FK column
	private PanelEntity PanelEntity;
	@Column(name = "role", nullable = false)
	private String role;
	@Column(name = "deleted_on")
	private LocalDateTime deletedOn;
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
	public PanelCandidateRolesEntity() {
		super();
	}
	public PanelCandidateRolesEntity(int id, InterviewType interviewType, PanelEntity PanelEntity, String role,
			LocalDateTime deletedOn, LocalDateTime createdOn, LocalDateTime updatedOn) {
		super();
		this.id = id;
		this.interviewType = interviewType;
		this.PanelEntity = PanelEntity;
		this.role = role;
		this.deletedOn = deletedOn;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public InterviewType getInterviewType() {
		return interviewType;
	}
	public void setInterviewType(InterviewType interviewType) {
		this.interviewType = interviewType;
	}
	public PanelEntity getPanelEntity() {
		return PanelEntity;
	}
	public void setPanelEntity(PanelEntity PanelEntity) {
		this.PanelEntity = PanelEntity;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public LocalDateTime getDeletedOn() {
		return deletedOn;
	}
	public void setDeletedOn(LocalDateTime deletedOn) {
		this.deletedOn = deletedOn;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	@Override
	public String toString() {
		return "PanelCandidateRolesEntity [id=" + id + ", interviewType=" + interviewType + ", PanelEntity=" + PanelEntity
				+ ", role=" + role + ", deletedOn=" + deletedOn + ", createdOn=" + createdOn + ", updatedOn="
				+ updatedOn + "]";
	}
	
}

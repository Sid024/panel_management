package com.zensar.pm.panel.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "panels")
public class PanelEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id") // FK column
	private UserEntity userEntity;
	@Column(name = "contact")
	private String contact;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "grade_id") // FK column
	private GradeEntity gradeEntity;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "panel_level_id") // FK column
	private PanelLevelEntity panelLevelEntity;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "panel_candidate_role_id") // FK column
	private PanelCandidateRolesEntity panelCandidateRolesEntity;
	@Column(name = "remark")
	private String remark;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	@Column(name = "deleted_by")
	private String deletedBy;
	@Column(name = "deleted_on")
	private LocalDateTime deletedOn;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id") // FK column
	private InterviewTypesEntity interviewType;

	@JsonIgnore
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "panelEntity")
	private List<PanelAvailabilityEntity> panelsAvailability;

	public PanelEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public GradeEntity getGradeEntity() {
		return gradeEntity;
	}

	public void setGradeEntity(GradeEntity gradeEntity) {
		this.gradeEntity = gradeEntity;
	}

	public PanelLevelEntity getPanelLevelEntity() {
		return panelLevelEntity;
	}

	public void setPanelLevelEntity(PanelLevelEntity panelLevelEntity) {
		this.panelLevelEntity = panelLevelEntity;
	}

	public PanelCandidateRolesEntity getPanelCandidateRolesEntity() {
		return panelCandidateRolesEntity;
	}

	public void setPanelCandidateRolesEntity(PanelCandidateRolesEntity panelCandidateRolesEntity) {
		this.panelCandidateRolesEntity = panelCandidateRolesEntity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public LocalDateTime getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(LocalDateTime deletedOn) {
		this.deletedOn = deletedOn;
	}

	public InterviewTypesEntity getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(InterviewTypesEntity interviewType) {
		this.interviewType = interviewType;
	}

	public PanelEntity(int id, UserEntity userEntity, String contact, GradeEntity gradeEntity,
			PanelLevelEntity panelLevelEntity, PanelCandidateRolesEntity panelCandidateRolesEntity, String remark,
			String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isDeleted,
			String deletedBy, LocalDateTime deletedOn, InterviewTypesEntity interviewType) {
		super();
		this.id = id;
		this.userEntity = userEntity;
		this.contact = contact;
		this.gradeEntity = gradeEntity;
		this.panelLevelEntity = panelLevelEntity;
		this.panelCandidateRolesEntity = panelCandidateRolesEntity;
		this.remark = remark;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.isDeleted = isDeleted;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
		this.interviewType = interviewType;
	}

}

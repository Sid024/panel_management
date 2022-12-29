package com.zensar.pm.panel.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity(name = "panel-management-db")
@Table(name = "panels")
public class PanelEnitity {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id") // FK column
	private UserEntity userEntity;
	@Column(name="contact")
	private String contact;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "grade_id") // FK column
	private GradeEntity gradeEntity;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "panel_level_id") // FK column
	private PanelLevelEntity panelLevelEntity;
	@Column(name="remark")
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
	private boolean isDeleted;
	@Column(name = "deleted_by")
	private String deletedBy;
	@Column(name = "deleted_on")
	private LocalDateTime deletedOn;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id") // FK column
	private InterviewType interviewType;
	
	public PanelEnitity() {
		super();
	}

	public PanelEnitity(int id, UserEntity userEntity, String contact, GradeEntity gradeEntity,
			PanelLevelEntity panelLevelEntity, String remark, String createdBy, LocalDateTime createdOn,
			String updatedBy, LocalDateTime updatedOn, boolean isDeleted, String deletedBy, LocalDateTime deletedOn,
			InterviewType interviewType) {
		super();
		this.id = id;
		this.userEntity = userEntity;
		this.contact = contact;
		this.gradeEntity = gradeEntity;
		this.panelLevelEntity = panelLevelEntity;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
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

	public InterviewType getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(InterviewType interviewType) {
		this.interviewType = interviewType;
	}

	@Override
	public String toString() {
		return "Panels [id=" + id + ", userEntity=" + userEntity + ", contact=" + contact + ", gradeEntity="
				+ gradeEntity + ", panelLevelEntity=" + panelLevelEntity + ", remark=" + remark + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn
				+ ", isDeleted=" + isDeleted + ", deletedBy=" + deletedBy + ", deletedOn=" + deletedOn
				+ ", interviewType=" + interviewType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(contact, createdBy, createdOn, deletedBy, deletedOn, gradeEntity, id, interviewType,
				isDeleted, panelLevelEntity, remark, updatedBy, updatedOn, userEntity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PanelEnitity other = (PanelEnitity) obj;
		return Objects.equals(contact, other.contact) && Objects.equals(createdBy, other.createdBy)
				&& Objects.equals(createdOn, other.createdOn) && Objects.equals(deletedBy, other.deletedBy)
				&& Objects.equals(deletedOn, other.deletedOn) && Objects.equals(gradeEntity, other.gradeEntity)
				&& id == other.id && Objects.equals(interviewType, other.interviewType) && isDeleted == other.isDeleted
				&& Objects.equals(panelLevelEntity, other.panelLevelEntity) && Objects.equals(remark, other.remark)
				&& Objects.equals(updatedBy, other.updatedBy) && Objects.equals(updatedOn, other.updatedOn)
				&& Objects.equals(userEntity, other.userEntity);
	}
	
	
	
}

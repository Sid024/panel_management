package com.zensar.pm.panel.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InterviewType {
	@Id
	@Column(name="type_id")
	private int typeId;
	@Column(name="type")
	private String type;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_on")
	private LocalDateTime createdOn;
	@Column(name="updated_by")
	private String updatedBy;
	@Column(name="updated_on")
	private LocalDateTime updatedOn;
	@Column(name="is_deleted")
	private boolean isDeleted;
	@Column(name="deleted_by")
	private String deletedBy;
	@Column(name="deleted_on")
	private LocalDateTime deletedOn;
	public InterviewType() {
		super();
	}
	public InterviewType(int typeId, String type, String createdBy, LocalDateTime createdOn, String updatedBy,
			LocalDateTime updatedOn, boolean isDeleted, String deletedBy, LocalDateTime deletedOn) {
		super();
		this.typeId = typeId;
		this.type = type;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.isDeleted = isDeleted;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	@Override
	public String toString() {
		return "InterviewType [typeId=" + typeId + ", type=" + type + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + ", isDeleted=" + isDeleted
				+ ", deletedBy=" + deletedBy + ", deletedOn=" + deletedOn + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(createdBy, createdOn, deletedBy, deletedOn, isDeleted, type, typeId, updatedBy, updatedOn);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterviewType other = (InterviewType) obj;
		return Objects.equals(createdBy, other.createdBy) && Objects.equals(createdOn, other.createdOn)
				&& Objects.equals(deletedBy, other.deletedBy) && Objects.equals(deletedOn, other.deletedOn)
				&& isDeleted == other.isDeleted && Objects.equals(type, other.type) && typeId == other.typeId
				&& Objects.equals(updatedBy, other.updatedBy) && Objects.equals(updatedOn, other.updatedOn);
	}
	
	

}

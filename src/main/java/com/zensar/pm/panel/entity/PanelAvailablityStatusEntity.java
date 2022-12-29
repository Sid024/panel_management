package com.zensar.pm.panel.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="panels_availability_status")
@Table(name="panels_availability_status")
public class PanelAvailablityStatusEntity {
	
	@Id
	@GeneratedValue
	@Column(name="availability_status_id")
	private String availablityStatusId;
	
	@Column(name="availability_status")
	private String availablityStatus;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_on")
	private LocalDateTime createdOn;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_on")
	private LocalDateTime updatedOn;
	
	@Column(name="deleted_by")
	private String deletedBy;
	
	@Column(name="deleted_on")
	private LocalDateTime deletedOn;
	
	@Column(name="is_deleted")
	private boolean isDeleted;

	public PanelAvailablityStatusEntity() {
		super();
	}

	public PanelAvailablityStatusEntity(String availablityStatusId, String availablityStatus, String createdBy,
			LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, String deletedBy,
			LocalDateTime deletedOn, boolean isDeleted) {
		super();
		this.availablityStatusId = availablityStatusId;
		this.availablityStatus = availablityStatus;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
		this.isDeleted = isDeleted;
	}

	public String getAvailablityStatusId() {
		return availablityStatusId;
	}

	public void setAvailablityStatusId(String availablityStatusId) {
		this.availablityStatusId = availablityStatusId;
	}

	public String getAvailablityStatus() {
		return availablityStatus;
	}

	public void setAvailablityStatus(String availablityStatus) {
		this.availablityStatus = availablityStatus;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "PanelAvailablityStatusEntity [availablityStatusId=" + availablityStatusId + ", availablityStatus="
				+ availablityStatus + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + ", deletedBy=" + deletedBy + ", deletedOn=" + deletedOn
				+ ", isDeleted=" + isDeleted + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(availablityStatus, availablityStatusId, createdBy, createdOn, deletedBy, deletedOn,
				isDeleted, updatedBy, updatedOn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PanelAvailablityStatusEntity other = (PanelAvailablityStatusEntity) obj;
		return Objects.equals(availablityStatus, other.availablityStatus)
				&& Objects.equals(availablityStatusId, other.availablityStatusId)
				&& Objects.equals(createdBy, other.createdBy) && Objects.equals(createdOn, other.createdOn)
				&& Objects.equals(deletedBy, other.deletedBy) && Objects.equals(deletedOn, other.deletedOn)
				&& isDeleted == other.isDeleted && Objects.equals(updatedBy, other.updatedBy)
				&& Objects.equals(updatedOn, other.updatedOn);
	}
	
	

}

package com.zensar.pm.panel.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="panels_availability_status")
public class PanelAvailabilityStatusEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
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

	public PanelAvailabilityStatusEntity() {
		super();
	}

	public PanelAvailabilityStatusEntity(int id, String availablityStatus, String createdBy,
			LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, String deletedBy,
			LocalDateTime deletedOn, boolean isDeleted) {
		super();
		this.id = id;
		this.availablityStatus = availablityStatus;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
		this.isDeleted = isDeleted;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "PanelAvailablityStatusEntity [availablityStatusId=" + id + ", availablityStatus="
				+ availablityStatus + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + ", deletedBy=" + deletedBy + ", deletedOn=" + deletedOn
				+ ", isDeleted=" + isDeleted + "]";
	}

	
	
	

}

package com.zensar.pm.panel.dto;

import java.time.LocalDateTime;

public class PanelAvailablityStatusDTO {


	private int availablityStatusId;
	
	private String availablityStatus;
	
	private String createdBy;
	
	private LocalDateTime createdOn;
	
	private String updatedBy;
	
	private LocalDateTime updatedOn;
	
	private String deletedBy;
	
	private LocalDateTime deletedOn;
	
	private boolean isDeleted;

	public PanelAvailablityStatusDTO(int availablityStatusId, String availablityStatus, String createdBy,
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

	public PanelAvailablityStatusDTO() {
		super();
	}

	public int getAvailablityStatusId() {
		return availablityStatusId;
	}

	public void setAvailablityStatusId(int availablityStatusId) {
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
		return "PanelAvailablityStatusDTO [availablityStatusId=" + availablityStatusId + ", availablityStatus="
				+ availablityStatus + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + ", deletedBy=" + deletedBy + ", deletedOn=" + deletedOn
				+ ", isDeleted=" + isDeleted + "]";
	}
	
	

}

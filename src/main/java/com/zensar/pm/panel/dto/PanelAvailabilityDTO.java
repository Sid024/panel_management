package com.zensar.pm.panel.dto;

import java.time.LocalDate;

public class PanelAvailabilityDTO {

	private int panelAvailablityId;

	private PanelEntityDTO panelId; // FK to PanelEntity

	private LocalDate date;

	private String startTime;

	private String endTime;

	private PanelAvailablityStatusDTO panelAvailablityStatus; // FK to PanelAvailablityStatusEntity

	private String createdBy;

	private LocalDate createdOn;

	private String updatedBy;

	private LocalDate updatedOn;

	private String deletedBy;

	private LocalDate deletedOn;

	private boolean isDeleted;

	public PanelAvailabilityDTO(int panelAvailablityId, PanelEntityDTO panelId, LocalDate date, String startTime,
			String endTime, PanelAvailablityStatusDTO panelAvailablityStatus, String createdBy, LocalDate createdOn,
			String updatedBy, LocalDate updatedOn, String deletedBy, LocalDate deletedOn, boolean isDeleted) {
		super();
		this.panelAvailablityId = panelAvailablityId;
		this.panelId = panelId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.panelAvailablityStatus = panelAvailablityStatus;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
		this.isDeleted = isDeleted;
	}

	public PanelAvailabilityDTO() {
		super();
	}

	
	public PanelAvailabilityDTO(PanelEntityDTO panelId, LocalDate date, String startTime, String endTime,
			PanelAvailablityStatusDTO panelAvailablityStatus) {
		super();
		this.panelId = panelId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.panelAvailablityStatus = panelAvailablityStatus;
	}

	public int getPanelAvailablityId() {
		return panelAvailablityId;
	}

	public void setPanelAvailablityId(int panelAvailablityId) {
		this.panelAvailablityId = panelAvailablityId;
	}

	public PanelEntityDTO getPanelId() {
		return panelId;
	}

	public void setPanelId(PanelEntityDTO panelId) {
		this.panelId = panelId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public PanelAvailablityStatusDTO getPanelAvailablityStatus() {
		return panelAvailablityStatus;
	}

	public void setPanelAvailablityStatus(PanelAvailablityStatusDTO panelAvailablityStatus) {
		this.panelAvailablityStatus = panelAvailablityStatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDate getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public LocalDate getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(LocalDate deletedOn) {
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
		return "PanelAvailablityDTO [panelAvailablityId=" + panelAvailablityId + ", panelId=" + panelId + ", date="
				+ date + ", startTime=" + startTime + ", endTime=" + endTime + ", panelAvailablityStatus="
				+ panelAvailablityStatus + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + ", deletedBy=" + deletedBy + ", deletedOn=" + deletedOn
				+ ", isDeleted=" + isDeleted + "]";
	}

	
}

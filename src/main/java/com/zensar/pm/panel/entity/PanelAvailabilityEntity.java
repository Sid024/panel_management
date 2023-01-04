package com.zensar.pm.panel.entity;

import java.time.LocalDate;
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
import javax.persistence.Table;



@Entity

@Table(name="panels_availability")
public class PanelAvailabilityEntity {

	@Id
	
	@Column(name="panels_availability_id")
	private int panelAvailablityId;
	
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private PanelEntity panelId;  //FK to PanelEntity
	
	@Column(name="available_date")
	private LocalDate date;
	
	@Column(name="start_time")
	private String startTime;
	
	@Column(name="end_time")
	private String endTime;
	
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "availability_status_id")
	private PanelAvailabilityStatusEntity availablityStatusId; //FK to PanelAvailablityStatusEntity

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

	public PanelAvailabilityEntity() {
		super();
	}

	public PanelAvailabilityEntity(int panelAvailablityId, PanelEntity panelId, LocalDate date, String startTime,
			String endTime, PanelAvailabilityStatusEntity panelAvailabilityStatusEntity, String createdBy,
			LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, String deletedBy,
			LocalDateTime deletedOn, boolean isDeleted) {
		super();
		this.panelAvailablityId = panelAvailablityId;
		this.panelId = panelId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.availablityStatusId = panelAvailabilityStatusEntity;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
		this.isDeleted = isDeleted;
	}

	public PanelAvailabilityEntity(String startTime, String endTime, LocalDate date, PanelEntity panelEntity) {

		super();
		this.panelId = panelEntity;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getPanelAvailablityId() {
		return panelAvailablityId;
	}

	public void setPanelAvailablityId(int panelAvailablityId) {
		this.panelAvailablityId = panelAvailablityId;
	}

	public PanelEntity getPanelId() {
		return panelId;
	}

	public void setPanelId(PanelEntity panelId) {
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

	public PanelAvailabilityStatusEntity getPanelAvailablityStatusEntity() {
		return availablityStatusId;
	}

	public void setPanelAvailablityStatusEntity(PanelAvailabilityStatusEntity panelAvailabilityStatusEntity) {
		this.availablityStatusId = panelAvailabilityStatusEntity;
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
		return "PanelAvailablityEntity [panelAvailablityId=" + panelAvailablityId + ", panelId=" + panelId + ", date="
				+ date + ", startTime=" + startTime + ", endTime=" + endTime + ", panelAvailablityStatusEntity="
				+ availablityStatusId + ", createdBy=" + createdBy + ", createdOn=" + createdOn
				+ ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + ", deletedBy=" + deletedBy + ", deletedOn="
				+ deletedOn + ", isDeleted=" + isDeleted + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdBy, createdOn, date, deletedBy, deletedOn, endTime, isDeleted, panelAvailablityId,
				availablityStatusId, panelId, startTime, updatedBy, updatedOn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PanelAvailabilityEntity other = (PanelAvailabilityEntity) obj;
		return Objects.equals(createdBy, other.createdBy) && Objects.equals(createdOn, other.createdOn)
				&& Objects.equals(date, other.date) && Objects.equals(deletedBy, other.deletedBy)
				&& Objects.equals(deletedOn, other.deletedOn) && Objects.equals(endTime, other.endTime)
				&& isDeleted == other.isDeleted && panelAvailablityId == other.panelAvailablityId
				&& Objects.equals(availablityStatusId, other.availablityStatusId)
				&& Objects.equals(panelId, other.panelId) && Objects.equals(startTime, other.startTime)
				&& Objects.equals(updatedBy, other.updatedBy) && Objects.equals(updatedOn, other.updatedOn);
	}
	
	
}

package com.zensar.pm.panel.entity;

import java.time.LocalDate;
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



@Entity
@Table(name="panels_availability")
public class PanelAvailabilityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "panel_id")
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
	
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;

	public PanelAvailabilityEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public PanelAvailabilityStatusEntity getAvailablityStatusId() {
		return availablityStatusId;
	}

	public void setAvailablityStatusId(PanelAvailabilityStatusEntity availablityStatusId) {
		this.availablityStatusId = availablityStatusId;
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

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public PanelAvailabilityEntity(int id, PanelEntity panelId, LocalDate date, String startTime, String endTime,
			PanelAvailabilityStatusEntity availablityStatusId, String createdBy, LocalDateTime createdOn,
			String updatedBy, LocalDateTime updatedOn, String deletedBy, LocalDateTime deletedOn, boolean isDeleted,
			UserEntity userEntity) {
		super();
		this.id = id;
		this.panelId = panelId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.availablityStatusId = availablityStatusId;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
		this.isDeleted = isDeleted;
		this.userEntity = userEntity;
	}


}

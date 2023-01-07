package com.zensar.pm.panel.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="panels_availability")
public class PanelAvailabilityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@JsonIgnore
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "panel_id")
	private PanelEntity panelEntity;  //FK to PanelEntity
	
	@Column(name="available_date")
	private LocalDate date;
	
	@Column(name="start_time")
	private LocalTime startTime;
	
	@Column(name="end_time")
	private LocalTime endTime;
	
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "availability_status_id")
	private PanelAvailabilityStatusEntity panelAvailabilityStatusEntity; //FK to PanelAvailablityStatusEntity

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
		return panelEntity;
	}

	public void setPanelId(PanelEntity panelId) {
		this.panelEntity = panelId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
		
		
	}

	public PanelAvailabilityStatusEntity getAvailablityStatusId() {
		return panelAvailabilityStatusEntity;
	}

	public void setAvailablityStatusId(PanelAvailabilityStatusEntity availablityStatusId) {
		this.panelAvailabilityStatusEntity = availablityStatusId;
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

	public PanelAvailabilityEntity(int id, PanelEntity panelId, LocalDate date, LocalTime startTime, LocalTime endTime,
			PanelAvailabilityStatusEntity availablityStatusId, String createdBy, LocalDateTime createdOn,
			String updatedBy, LocalDateTime updatedOn, String deletedBy, LocalDateTime deletedOn, boolean isDeleted,
			UserEntity userEntity) {
		super();
		this.id = id;
		this.panelEntity = panelId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.panelAvailabilityStatusEntity = availablityStatusId;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
		this.isDeleted = isDeleted;
		this.userEntity = userEntity;
	}
	
	public PanelAvailabilityEntity(LocalTime startTime, LocalTime endTime, LocalDate date,PanelEntity panelEntity) {
	      super();
	      this.startTime = startTime;
	      this.endTime = endTime;
	      this.date = date;
	      this.panelEntity = panelEntity;
	  }
	public PanelAvailabilityEntity(PanelEntity panelEntity, LocalDate date,
			PanelAvailabilityStatusEntity panelAvailabilityStatusEntity, String createdBy, LocalDateTime createdOn,
			LocalDateTime updatedOn, String updatedBy, boolean isDeleted, String deletedBy, LocalDateTime deletedOn,
			@NotNull LocalTime startTime, @NotNull LocalTime endTime) {
		super();
		this.panelEntity = panelEntity;
		this.date = date;
		this.panelAvailabilityStatusEntity = panelAvailabilityStatusEntity;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.isDeleted = isDeleted;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public PanelAvailabilityEntity(int id, PanelEntity panelEntity, LocalDate date, LocalTime startTime,
			LocalTime endTime, PanelAvailabilityStatusEntity panelAvailabilityStatusEntity, String createdBy,
			LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, String deletedBy,
			LocalDateTime deletedOn, boolean isDeleted) {
		super();
		this.id = id;
		this.panelEntity = panelEntity;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.panelAvailabilityStatusEntity = panelAvailabilityStatusEntity;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
		this.isDeleted = isDeleted;
	}

	

	

}

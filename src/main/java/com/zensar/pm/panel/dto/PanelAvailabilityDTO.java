package com.zensar.pm.panel.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.zensar.pm.panel.entity.PanelAvailabilityStatusEntity;
import com.zensar.pm.panel.entity.PanelEntity;
import com.zensar.pm.panel.entity.UserEntity;

public class PanelAvailabilityDTO {

 private int panelAvailablityId;
 private PanelEntity panelId; // FK to PanelEntity
 private LocalDate date;
 private LocalTime startTime;
 private LocalTime endTime;
 private PanelAvailabilityStatusEntity panelAvailablityStatusEntity; // FK to PanelAvailabilityStatusEntity
 private int availablityStatusId;
 private String createdBy;
 private LocalDateTime createdOn;
 private String updatedBy;
 private LocalDateTime updatedOn;
 private String deletedBy;
 private LocalDateTime deletedOn;
 private boolean isDeleted;
 private UserEntity userEntity;
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
public PanelAvailabilityStatusEntity getPanelAvailablityStatusEntity() {
	return panelAvailablityStatusEntity;
}
public void setPanelAvailablityStatusEntity(PanelAvailabilityStatusEntity panelAvailablityStatusEntity) {
	this.panelAvailablityStatusEntity = panelAvailablityStatusEntity;
}
public int getAvailablityStatusId() {
	return availablityStatusId;
}
public void setAvailablityStatusId(int availablityStatusId) {
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

public UserEntity getUserEntity() {
	return userEntity;
}
public void setUserEntity(UserEntity userEntity) {
	this.userEntity = userEntity;
}
public boolean isDeleted() {
	return isDeleted;
}
public void setDeleted(boolean isDeleted) {
	this.isDeleted = isDeleted;
}
public PanelAvailabilityDTO(int panelAvailablityId, PanelEntity panelId, LocalDate date, LocalTime startTime,
		LocalTime endTime, PanelAvailabilityStatusEntity panelAvailablityStatusEntity, int availablityStatusId,
		String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, String deletedBy,
		LocalDateTime deletedOn, boolean isDeleted) {
	super();
	this.panelAvailablityId = panelAvailablityId;
	this.panelId = panelId;
	this.date = date;
	this.startTime = startTime;
	this.endTime = endTime;
	this.panelAvailablityStatusEntity = panelAvailablityStatusEntity;
	this.availablityStatusId = availablityStatusId;
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
	// TODO Auto-generated constructor stub
}

public PanelAvailabilityDTO(int panelAvailablityId, LocalTime startTime, LocalTime endTime, int availablityStatusId) {
	super();
	this.panelAvailablityId = panelAvailablityId;
	this.startTime = startTime;
	this.endTime = endTime;
	this.availablityStatusId = availablityStatusId;
}

}


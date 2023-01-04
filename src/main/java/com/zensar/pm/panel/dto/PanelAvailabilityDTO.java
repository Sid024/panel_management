package com.zensar.pm.panel.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.zensar.pm.panel.entity.PanelAvailabilityEntity;
import com.zensar.pm.panel.entity.PanelAvailabilityStatusEntity;
import com.zensar.pm.panel.entity.PanelEntity;

public class PanelAvailabilityDTO {

 private int panelAvailablityId;
 private PanelEntity panelId; // FK to PanelEntity
 private LocalDate date;
 private String startTime;
 private String endTime;
 private PanelAvailabilityStatusEntity panelAvailablityStatusEntity; // FK to PanelAvailabilityStatusEntity
 private int availablityStatusId;
 private String createdBy;
 private LocalDateTime createdOn;
 private String updatedBy;
 private LocalDateTime updatedOn;
 private String deletedBy;
 private LocalDateTime deletedOn;
 private boolean isDeleted;
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
public boolean isDeleted() {
	return isDeleted;
}
public void setDeleted(boolean isDeleted) {
	this.isDeleted = isDeleted;
}
public PanelAvailabilityDTO(int panelAvailablityId, PanelEntity panelId, LocalDate date, String startTime,
		String endTime, PanelAvailabilityStatusEntity panelAvailablityStatusEntity, int availablityStatusId,
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

public PanelAvailabilityDTO(int panelAvailablityId, String startTime, String endTime, int availablityStatusId) {
	super();
	this.panelAvailablityId = panelAvailablityId;
	this.startTime = startTime;
	this.endTime = endTime;
	this.availablityStatusId = availablityStatusId;
}

}


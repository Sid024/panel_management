package com.zensar.pm.panel.dto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class PanelAvailDTO {
	
    private int panelsAvailabilityId;
    private String panelName; 
    private int panelId;
    private String grade;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private int availabilityStatusId;
    private String createdBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdOn;
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedOn;
    private String updatedBy;
    private boolean isDeleted;
    private String deletedBy;
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deletedOn;
    @NotNull
    @DateTimeFormat(pattern="HH:mm")
    private LocalTime startTime;
    @NotNull
    @DateTimeFormat(pattern="HH:mm")
    private LocalTime endTime;
   @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate fromDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate toDate;
    private List<String> listOfDays;



    public int getPanelsAvailabilityId() {
		return panelsAvailabilityId;
	}
	public void setPanelsAvailabilityId(int panelsAvailabilityId) {
		this.panelsAvailabilityId = panelsAvailabilityId;
	}
	public String getPanelName() {
		return panelName;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
	public int getPanelId() {
		return panelId;
	}
	public void setPanelId(int panelId) {
		this.panelId = panelId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getAvailabilityStatusId() {
		return availabilityStatusId;
	}
	public void setAvailabilityStatusId(int availabilityStatusId) {
		this.availabilityStatusId = availabilityStatusId;
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
	public LocalDate getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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
	public LocalDate getDeletedOn() {
		return deletedOn;
	}
	public void setDeletedOn(LocalDate deletedOn) {
		this.deletedOn = deletedOn;
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
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public List<String> getListOfDays() {
		return listOfDays;
	}
	public void setListOfDays(List<String> listOfDays) {
		this.listOfDays = listOfDays;
	}
	
	public PanelAvailDTO(int panelsAvailabilityId, String panelName, int panelId, String grade, LocalDate date,
			int availabilityStatusId, String createdBy, LocalDate createdOn, LocalDate updatedOn,
			String updatedBy, boolean isDeleted, String deletedBy, LocalDate deletedOn,
			@NotNull LocalTime startTime, @NotNull LocalTime endTime, LocalDate fromDate, LocalDate toDate,
			List<String> listOfDays) {
		super();
		this.panelsAvailabilityId = panelsAvailabilityId;
		this.panelName = panelName;
		this.panelId = panelId;
		this.grade = grade;
		this.date = date;
		this.availabilityStatusId = availabilityStatusId;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.isDeleted = isDeleted;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
		this.startTime = startTime;
		this.endTime = endTime;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.listOfDays = listOfDays;
	}
	public PanelAvailDTO() {
        super();
    }
    public PanelAvailDTO(int panelsAvailabilityId, String panelName, int panelId, String grade,
            LocalDate date, int availabilityStatusId, String createdBy, LocalDate createdOn,
            LocalDate updatedOn, String updatedBy, boolean isDeleted, String deletedBy, LocalDate deletedOn,
            @NotNull LocalTime startTime, @NotNull LocalTime endTime) {
        super();
        this.panelsAvailabilityId = panelsAvailabilityId;
        this.panelName = panelName;
        this.panelId = panelId;
        this.grade = grade;
        this.date = date;
        this.availabilityStatusId = availabilityStatusId;
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

}

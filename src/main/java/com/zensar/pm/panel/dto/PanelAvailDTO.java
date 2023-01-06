package com.zensar.pm.panel.dto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


public class PanelAvailDTO {
	
    private int panelsAvailabilityId;
    private String panelName; 
    private int panelId;
    private String grade;
    private LocalDate date;
    private int availabilityStatusId;
    private String createdBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private String updatedBy;
    private boolean isDeleted;
    private String deletedBy;
    private LocalDateTime deletedOn;
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
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(LocalDateTime updatedOn) {
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
	public LocalDateTime getDeletedOn() {
		return deletedOn;
	}
	public void setDeletedOn(LocalDateTime deletedOn) {
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
			int availabilityStatusId, String createdBy, LocalDateTime createdOn, LocalDateTime updatedOn,
			String updatedBy, boolean isDeleted, String deletedBy, LocalDateTime deletedOn,
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
            LocalDate date, int availabilityStatusId, String createdBy, LocalDateTime createdOn,
            LocalDateTime updatedOn, String updatedBy, boolean isDeleted, String deletedBy, LocalDateTime deletedOn,
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

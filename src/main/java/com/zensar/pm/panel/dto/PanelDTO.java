package com.zensar.pm.panel.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.aspectj.apache.bcel.ExceptionConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class PanelDTO {

	
	private String associateId;
	private String associateName;
	private String associateGrade;
	
	@NotNull
	private LocalDate Date;
	@NotNull
	private LocalTime fromTime;
	@NotNull
	private LocalTime toTime;
	@NotNull
	private String status;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PanelDTO other = (PanelDTO) obj;
		return Objects.equals(Date, other.Date) && Objects.equals(associateGrade, other.associateGrade)
				&& Objects.equals(associateId, other.associateId) && Objects.equals(associateName, other.associateName)
				&& Objects.equals(fromTime, other.fromTime) && Objects.equals(status, other.status)
				&& Objects.equals(toTime, other.toTime);
	}
	@Override
	public int hashCode() {
		return Objects.hash(Date, associateGrade, associateId, associateName, fromTime, status, toTime);
	}
	public PanelDTO(String associateId, String associateName, String associateGrade) {
		super();
		this.associateId = associateId;
		this.associateName = associateName;
		this.associateGrade = associateGrade;
	}
	public String getAssociateId() {
		return associateId;
	}
	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}
	public String getAssociateName() {
		return associateName;
	}
	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}
	public String getAssociateGrade() {
		return associateGrade;
	}
	public void setAssociateGrade(String associateGrade) {
		this.associateGrade = associateGrade;
	}
	public LocalDate getDate() {
		return Date;
	}
	public void setDate(LocalDate date) {
		Date = date;
	}
	public LocalTime getFromTime() {
		return fromTime;
	}
	public void setFromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}
	public LocalTime getToTime() {
		return toTime;
	}
	public void setToTime(LocalTime toTime) {
		this.toTime = toTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public PanelDTO(String associateId, String associateName, String associateGrade, @NotNull LocalDate date,
			@NotNull LocalTime fromTime, @NotNull LocalTime toTime, @NotNull String status) {
		super();
		this.associateId = associateId;
		this.associateName = associateName;
		this.associateGrade = associateGrade;
		Date = date;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.status = status;
	}
	
	
	public PanelDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

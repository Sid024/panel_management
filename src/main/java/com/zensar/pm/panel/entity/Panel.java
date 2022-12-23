package com.zensar.pm.panel.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "panel-management-db")
@Table(name = "panel")
public class Panel {


	
	@Id
	private String associateId;
	@Column(name = "panel_availability_id")
	private int panelsAvailabilityId;
	@Column(name = "panel_id")
	private int panelId;
	private String associateName;
	private String associateGrade;
	@Column(name = "available_date")
	private LocalDate Date;
	@Column(name = "start_time")
	private LocalTime fromTime;
	@Column(name = "end_time")
	private LocalTime toTime;
	private String status;
	@Column(name = "updated_on")
	private LocalDate updatedOn ;
	@Column(name = "updated_by")
    private String updatedBy;
	@Column(name = "avaialability_status_id")
    private int availabilityStatusId;
    
	public Panel(String associateId, String associateName, String associateGrade) {
		super();
		this.associateId = associateId;
		this.associateName = associateName;
		this.associateGrade = associateGrade;
	}

	public Panel(String associateId, String associateName, String associateGrade, LocalDate date, LocalTime fromTime,
			LocalTime toTime, String status) {
		super();
		this.associateId = associateId;
		this.associateName = associateName;
		this.associateGrade = associateGrade;
		Date = date;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Panel other = (Panel) obj;
		return Objects.equals(Date, other.Date) && Objects.equals(associateGrade, other.associateGrade)
				&& Objects.equals(associateId, other.associateId) && Objects.equals(associateName, other.associateName)
				&& availabilityStatusId == other.availabilityStatusId && Objects.equals(fromTime, other.fromTime)
				&& panelId == other.panelId && panelsAvailabilityId == other.panelsAvailabilityId
				&& Objects.equals(status, other.status) && Objects.equals(toTime, other.toTime)
				&& Objects.equals(updatedBy, other.updatedBy) && Objects.equals(updatedOn, other.updatedOn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(Date, associateGrade, associateId, associateName, availabilityStatusId, fromTime, panelId,
				panelsAvailabilityId, status, toTime, updatedBy, updatedOn);
	}

	
	
	

}

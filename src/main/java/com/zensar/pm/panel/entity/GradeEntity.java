package com.zensar.pm.panel.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grades_master")
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class GradeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	private String grade;
	private String createdBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdOn;
	private String updatedBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate updatedOn;
    private boolean isDeleted;
    private String deletedBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deletedOn;
	public GradeEntity(Integer id, String grade) {
		super();
		this.id = id;
		this.grade = grade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
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
	public GradeEntity(int id, String grade, String createdBy, LocalDate createdOn, String updatedBy,
			LocalDate updatedOn, boolean isDeleted, String deletedBy, LocalDate deletedOn) {
		super();
		this.id = id;
		this.grade = grade;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.isDeleted = isDeleted;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
	}
	public GradeEntity() {
		super();
	}

	
	
}

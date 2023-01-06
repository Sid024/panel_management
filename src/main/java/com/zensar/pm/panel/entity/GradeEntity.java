package com.zensar.pm.panel.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Grade")
@Data

public class GradeEntity {
	@GeneratedValue
	@Id
	@Column(name="id")
	private Integer gradeId;
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
    
	public GradeEntity() {
		super();
	}
	public GradeEntity(Integer gradeId, String grade) {
		super();
		this.gradeId = gradeId;
		this.grade = grade;
	}
	public GradeEntity(Integer gradeId, String grade, String createdBy, LocalDate createdOn, String updatedBy,
			LocalDate updatedOn, boolean isDeleted, String deletedBy, LocalDate deletedOn) {
		super();
		this.gradeId = gradeId;
		this.grade = grade;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.isDeleted = isDeleted;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
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
	@Override
	public String toString() {
		return "GradeEntity [gradeId=" + gradeId + ", grade=" + grade + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + ", isDeleted=" + isDeleted
				+ ", deletedBy=" + deletedBy + ", deletedOn=" + deletedOn + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(createdBy, createdOn, deletedBy, deletedOn, grade, gradeId, isDeleted, updatedBy,
				updatedOn);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GradeEntity other = (GradeEntity) obj;
		return Objects.equals(createdBy, other.createdBy) && Objects.equals(createdOn, other.createdOn)
				&& Objects.equals(deletedBy, other.deletedBy) && Objects.equals(deletedOn, other.deletedOn)
				&& Objects.equals(grade, other.grade) && Objects.equals(gradeId, other.gradeId)
				&& isDeleted == other.isDeleted && Objects.equals(updatedBy, other.updatedBy)
				&& Objects.equals(updatedOn, other.updatedOn);
	}
	
	
}

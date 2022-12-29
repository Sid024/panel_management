package com.zensar.pm.panel.entity;

import java.time.LocalDate;
import java.util.Date;

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
@NoArgsConstructor
@AllArgsConstructor
public class GradeEntity {
	@GeneratedValue
	@Id
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
	public GradeEntity(Integer gradeId, String grade) {
		super();
		this.gradeId = gradeId;
		this.grade = grade;
	}
	
}

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
@NoArgsConstructor
@AllArgsConstructor
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
	
}

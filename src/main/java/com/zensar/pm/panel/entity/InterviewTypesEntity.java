package com.zensar.pm.panel.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="interview_types")
public class InterviewTypesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	private String type;
	private String createdBy;
	private LocalDateTime createdOn;
	private String updatedBy;
	private LocalDateTime updatedOn;
	private boolean isDeleted;
	private String deletedBy;
	private LocalDateTime deletedOn;
}

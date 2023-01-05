package com.zensar.pm.panel.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InterviewTypes {
	@Id
	private String typeId;
	private String type;
	private String created_by;
	private LocalDateTime created_on;
	private String updated_by;
	private LocalDateTime updated_on;
	private boolean is_Deleted;
	private String deleted_by;
	private LocalDateTime deleted_on;
}

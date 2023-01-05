package com.zensar.pm.panel.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "panel_candidate_roles")
public class PanelCandidateRolesEntity {
	@Id
	@GeneratedValue
	@Column(name = "panel_candidate_role_id")
	private int id;
	@Column(name = "role", nullable = false)
	private String role;
	@Column(name = "deleted_on")
	private LocalDateTime deletedOn;
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	public PanelCandidateRolesEntity(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
}

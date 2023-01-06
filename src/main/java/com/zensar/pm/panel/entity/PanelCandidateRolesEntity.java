package com.zensar.pm.panel.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "id")
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDateTime getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(LocalDateTime deletedOn) {
		this.deletedOn = deletedOn;
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

	public PanelCandidateRolesEntity(int id, String role, LocalDateTime deletedOn, LocalDateTime createdOn,
			LocalDateTime updatedOn) {
		super();
		this.id = id;
		this.role = role;
		this.deletedOn = deletedOn;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	public PanelCandidateRolesEntity() {
		super();
	}
	
	
}

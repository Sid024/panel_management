package com.zensar.pm.panel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name="panel_level_master")
public class PanelLevelEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@NotEmpty
	private String panelLevel;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private boolean isDeleted;
	private String deletedBy;
	private Date deletedOn;
	public PanelLevelEntity(@NotNull int id, @NotEmpty String panelLevel) {
		super();
		this.id = id;
		this.panelLevel = panelLevel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPanelLevel() {
		return panelLevel;
	}
	public void setPanelLevel(String panelLevel) {
		this.panelLevel = panelLevel;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
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
	public Date getDeletedOn() {
		return deletedOn;
	}
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}
	public PanelLevelEntity(int id, @NotEmpty String panelLevel, String createdBy, Date createdOn, String updatedBy,
			Date updatedOn, boolean isDeleted, String deletedBy, Date deletedOn) {
		super();
		this.id = id;
		this.panelLevel = panelLevel;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.isDeleted = isDeleted;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
	}
	
}

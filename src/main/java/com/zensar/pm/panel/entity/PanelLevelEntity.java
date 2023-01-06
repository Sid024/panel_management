package com.zensar.pm.panel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="panel_level")
public class PanelLevelEntity {
	
	
	@NotNull
	@Id
	@GeneratedValue
	@Column(name="id")
	private int panelLevelId;
	@NotEmpty
	private String panelLevel;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private boolean isDeleted;
	private String deletedBy;
	private Date deletedOn;
	public PanelLevelEntity(@NotNull int panelLevelId, @NotEmpty String panelLevel) {
		super();
		this.panelLevelId = panelLevelId;
		this.panelLevel = panelLevel;
	}
}

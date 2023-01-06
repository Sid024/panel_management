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
@AllArgsConstructor
@NoArgsConstructor
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
}

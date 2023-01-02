package com.zensar.pm.panel.dto;

import com.zensar.pm.panel.entity.RoleEntity;

public class PanelsGetAllDTO {
	
	private int panelId;
	
	private String panelName;
	
	private String panelRole;

	public PanelsGetAllDTO(int panelId, String panelName, String panelRole) {
		super();
		this.panelId = panelId;
		this.panelName = panelName;
		this.panelRole = panelRole;
	}

	public PanelsGetAllDTO() {
		super();
	}

	public int getPanelId() {
		return panelId;
	}

	public void setPanelId(int panelId) {
		this.panelId = panelId;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	public String getPanelRole() {
		return panelRole;
	}

	public void setPanelRole(String panelRole) {
		this.panelRole = panelRole;
	}

	@Override
	public String toString() {
		return "PanelsGetAllDTO [panelId=" + panelId + ", panelName=" + panelName + ", panelRole=" + panelRole + "]";
	}
	
	
}

package com.zensar.pm.panel.dto;

public class PanelsGetAllDTO {
	
	private int panelId;
	
	private String panelName;
	
	private String panelGrade;

	public PanelsGetAllDTO(int panelId, String panelName, String panelGrade) {
		super();
		this.panelId = panelId;
		this.panelName = panelName;
		this.panelGrade = panelGrade;
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

	public String getPanelGrade() {
		return panelGrade;
	}

	public void setPanelGrade(String panelGrade) {
		this.panelGrade = panelGrade;
	}

	@Override
	public String toString() {
		return "PanelsGetAllDTO [panelId=" + panelId + ", panelName=" + panelName + ", panelGrade=" + panelGrade + "]";
	}
	
	
}

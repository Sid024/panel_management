package com.zensar.pm.panel.dto;
public class PanelAvailabilitySelfDTO {
	
	private int panelId;
    private String panelName;
    private String grade;
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((panelName == null) ? 0 : panelName.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + panelId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PanelAvailabilitySelfDTO other = (PanelAvailabilitySelfDTO) obj;
		if (panelName == null) {
			if (other.panelName != null)
				return false;
		} else if (!panelName.equals(other.panelName))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (panelId != other.panelId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PanelAvailabilitySelfDTO [panelId=" + panelId + ", PanelName=" + panelName + ", grade=" + grade + "]";
	}
	public PanelAvailabilitySelfDTO(int panelId, String panelName, String grade) {
		super();
		this.panelId = panelId;
		this.panelName = panelName;
		this.grade = grade;
	}
	public PanelAvailabilitySelfDTO() {
		super();
	}
    
	
}

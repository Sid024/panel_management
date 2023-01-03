package com.zensar.pm.panel.dto;

import java.util.List;
import java.util.Objects;



public class SearchByFilterDTO {
	private List<PanelDTO> panelDtoList;
	private int totalNumberOfRecords;
	public SearchByFilterDTO() {
		super();
	}
	public SearchByFilterDTO(List<PanelDTO> panelDtoList, int totalNumberOfRecords) {
		super();
		this.panelDtoList = panelDtoList;
		this.totalNumberOfRecords = totalNumberOfRecords;
	}
	public List<PanelDTO> getPanelDtoList() {
		return panelDtoList;
	}
	public void setPanelDtoList(List<PanelDTO> panelDtoList) {
		this.panelDtoList = panelDtoList;
	}
	public int getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}
	public void setTotalNumberOfRecords(int totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}
	@Override
	public String toString() {
		return "SearchByFilterDTO [panelDtoList=" + panelDtoList + ", totalNumberOfRecords=" + totalNumberOfRecords
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(panelDtoList, totalNumberOfRecords);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchByFilterDTO other = (SearchByFilterDTO) obj;
		return Objects.equals(panelDtoList, other.panelDtoList) && totalNumberOfRecords == other.totalNumberOfRecords;
	}
	
	
}

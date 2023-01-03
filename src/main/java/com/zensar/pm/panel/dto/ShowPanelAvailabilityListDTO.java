package com.zensar.pm.panel.dto;

import java.util.List;

public class ShowPanelAvailabilityListDTO {

	private List<PanelAvailabilityListDTO> showDTOList;
	private int totalNoOfPanels;
	
	public List<PanelAvailabilityListDTO> getShowDTOList() {
		return showDTOList;
	}
	public void setShowDTOList(List<PanelAvailabilityListDTO> showDTOList) {
		this.showDTOList = showDTOList;
	}
	public int getSize() {
		return totalNoOfPanels;
	}
	public void setSize(int showSize) {
		this.totalNoOfPanels = showSize;
	}
	public ShowPanelAvailabilityListDTO(List<PanelAvailabilityListDTO> showDTOList, int showSize) {
		super();
		this.showDTOList = showDTOList;
		this.totalNoOfPanels = showSize;
	}
	public ShowPanelAvailabilityListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



}

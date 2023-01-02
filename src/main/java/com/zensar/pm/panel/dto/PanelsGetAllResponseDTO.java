package com.zensar.pm.panel.dto;

import java.util.List;

public class PanelsGetAllResponseDTO {

	private List<PanelsGetAllDTO> list;

	public PanelsGetAllResponseDTO(List<PanelsGetAllDTO> list) {
		super();
		this.list = list;
	}

	public PanelsGetAllResponseDTO() {
		super();
	}

	public List<PanelsGetAllDTO> getList() {
		return list;
	}

	public void setList(List<PanelsGetAllDTO> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PanelsGetAllResponseDTO [list=" + list + "]";
	}
	
	
}

package com.zensar.pm.panel.dto;

import java.util.List;

public class PanelsGetAllResponseDTO {

	private List<PanelsGetAllDTO> list;
	private String roleName;
	
	

	public PanelsGetAllResponseDTO(List<PanelsGetAllDTO> list, String roleName) {
		super();
		this.list = list;
		this.roleName = roleName;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "PanelsGetAllResponseDTO [list=" + list + ", roleName=" + roleName + "]";
	}

	
	
	
}

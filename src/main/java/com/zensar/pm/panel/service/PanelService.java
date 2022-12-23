package com.zensar.pm.panel.service;

import java.util.List;

import com.zensar.pm.panel.dto.PanelDTO;

public interface PanelService {

	public PanelDTO updatePanel(String associateId, PanelDTO panelDTO,String jwtToken);
	public List<PanelDTO> getByAssociateName(String name,String jwtToken);

}

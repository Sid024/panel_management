package com.zensar.pm.panel.service;

import org.springframework.stereotype.Service;

import com.zensar.pm.panel.dto.PanelAvailabilityDTO;
import com.zensar.pm.panel.dto.PanelsGetAllResponseDTO;

@Service
public interface PanelAvailabilityService {

	public PanelAvailabilityDTO addPanelAvailablitySingle( PanelAvailabilityDTO dto);

	public PanelsGetAllResponseDTO getLoginRole(String token);

	public PanelsGetAllResponseDTO filterByPanelName(String panelName);

	/* public PanelsGetAllResponseDTO getAllPanels(); */
}

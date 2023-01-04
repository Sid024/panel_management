package com.zensar.pm.panel.service;

import org.springframework.stereotype.Service;

import com.zensar.pm.panel.dto.PanelAvailabilityDTO;
import com.zensar.pm.panel.dto.PanelsGetAllResponseDTO;
import com.zensar.pm.panel.entity.PanelAvailabilityEntity;

@Service
public interface PanelAvailabilityService {

	public PanelAvailabilityDTO addPanelAvailablitySingle( PanelAvailabilityDTO dto);

	public PanelsGetAllResponseDTO getAllPanels();
}

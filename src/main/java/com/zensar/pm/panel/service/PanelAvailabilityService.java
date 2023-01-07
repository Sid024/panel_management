package com.zensar.pm.panel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zensar.pm.panel.dto.PanelAvailDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityPHDTO;
import com.zensar.pm.panel.dto.PanelAvailabilitySelfDTO;
import com.zensar.pm.panel.dto.PanelsGetAllResponseDTO;

@Service
public interface PanelAvailabilityService {

	public PanelAvailabilityDTO addPanelAvailablitySingle( PanelAvailabilityDTO dto);

	public PanelsGetAllResponseDTO getLoginRole(String token);

	public PanelsGetAllResponseDTO filterByPanelName(String panelName);

	/* public PanelsGetAllResponseDTO getAllPanels(); */
	
	String post(PanelAvailDTO pa); //1
    List<PanelAvailDTO> getPanelByFilterCriteria(String panelName,String token); //2
    PanelAvailDTO create(PanelAvailDTO pa,String token); //3
    PanelAvailabilityPHDTO getPanelDetails(String PanelName,String token); //4
    PanelAvailabilitySelfDTO getLoginRoleByPanelAvail(String token); //5
}

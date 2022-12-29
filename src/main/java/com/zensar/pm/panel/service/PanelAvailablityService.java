package com.zensar.pm.panel.service;

import org.springframework.stereotype.Service;

import com.zensar.pm.panel.dto.PanelAvailablityDTO;
import com.zensar.pm.panel.entity.PanelAvailablityEntity;

@Service
public interface PanelAvailablityService {

	public PanelAvailablityDTO addPanelAvailablitySingle(int panelId, PanelAvailablityDTO dto);
}

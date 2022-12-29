package com.zensar.pm.panel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.pm.panel.dto.PanelAvailablityDTO;
import com.zensar.pm.panel.entity.PanelAvailablityEntity;
import com.zensar.pm.panel.entity.PanelEnitity;
import com.zensar.pm.panel.repository.PanelAvailablityRepository;

@Service
public class PanelAvailablityServiceImpl implements PanelAvailablityService {

	@Autowired
	PanelAvailablityRepository repository;
	
	@Override
	public PanelAvailablityDTO addPanelAvailablitySingle(int panelId, PanelAvailablityDTO dto) {

		PanelAvailablityEntity entity=new PanelAvailablityEntity();
		
		entity.getPanelId().getId();
		return null;
	}

}

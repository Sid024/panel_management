package com.zensar.pm.panel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.pm.panel.dto.PanelAvailablityDTO;
import com.zensar.pm.panel.service.PanelAvailablityService;

@RestController
public class PanelAvailablityController {
	
	@Autowired
	PanelAvailablityService service;
	
	private 

	@PostMapping(value = "/panel/availablity", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
     ResponseEntity<PanelAvailablityDTO> addPanelAvailablity(int panelId, PanelAvailablityDTO dto) {
		return new ResponseEntity<PanelAvailablityDTO>(service.addPanelAvailablitySingle(panelId, dto),HttpStatus.OK);
	}
}

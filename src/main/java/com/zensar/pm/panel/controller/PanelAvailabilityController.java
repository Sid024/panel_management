package com.zensar.pm.panel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.pm.panel.dto.PanelAvailabilityDTO;
import com.zensar.pm.panel.dto.PanelsGetAllResponseDTO;
import com.zensar.pm.panel.service.PanelAvailabilityService;

@RestController
@RequestMapping("/pm/panel-management")
@Validated
@CrossOrigin("*")
public class PanelAvailabilityController {
	
	@Autowired
	PanelAvailabilityService service;
	
	

	@PostMapping(value = "/panel/availablity", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<PanelAvailabilityDTO> addPanelAvailablity(@RequestBody PanelAvailabilityDTO dto) {
		return new ResponseEntity<PanelAvailabilityDTO>(service.addPanelAvailablitySingle(dto),HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/panels/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public PanelsGetAllResponseDTO getAllPanels() {
		PanelsGetAllResponseDTO panelsDTO=service.getAllPanels();
		return panelsDTO;
	}
}

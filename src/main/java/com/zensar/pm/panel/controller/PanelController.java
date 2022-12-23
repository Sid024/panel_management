package com.zensar.pm.panel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.pm.panel.dto.PanelDTO;
import com.zensar.pm.panel.service.PanelService;
import com.zensar.pm.panel.service.PanelServiceImplementation;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@OpenAPIDefinition(info = @Info(description = "<h2>Panel Management</h2>", version = "1.0.0"))
@RequestMapping("/pm/panel-management")
@Validated
@CrossOrigin("*")
public class PanelController {
	@Autowired
	private PanelService impl;

	@PutMapping(value = "/panel/{associateId}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PanelDTO> updatePanel(@PathVariable("associateId") String AssociateId, @RequestBody PanelDTO panelDTO,@RequestHeader("Authorization") String token) {
		PanelDTO panel = impl.updatePanel(AssociateId, panelDTO,token);
		return new ResponseEntity<PanelDTO>(panel, HttpStatus.CREATED);
	}
	@GetMapping(value = "/associate/name/{associateName}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<PanelDTO>> getAssociateByName(@PathVariable("associateName") String associateName,@RequestHeader("Authorization") String token) {

 

        return new ResponseEntity<List<PanelDTO>>(impl.getByAssociateName(associateName,token), HttpStatus.FOUND);
    }
}
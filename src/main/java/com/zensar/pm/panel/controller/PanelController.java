package com.zensar.pm.panel.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.pm.panel.dto.PanelAvailabilityListDto;
import com.zensar.pm.panel.dto.PanelDTO;
import com.zensar.pm.panel.export.FileExporter;
import com.zensar.pm.panel.service.PanelAvailabilityService;
import com.zensar.pm.panel.service.PanelService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@OpenAPIDefinition(info = @Info(description = "<h2>Panel Management</h2>", version = "1.0.0"))
@RequestMapping("/pm/panel-management")
@Validated
@CrossOrigin("*")
public class PanelController {

	@Autowired
	private PanelAvailabilityService panelAvailabilityService;

	////////////////////////////////////////////////////////////////

	@Autowired
	private PanelService impl;

	@PutMapping(value = "/panel/{associateId}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PanelDTO> updatePanel(@PathVariable("associateId") String AssociateId,
			@RequestBody PanelDTO panelDTO, @RequestHeader("Authorization") String token) {
		PanelDTO panel = impl.updatePanel(AssociateId, panelDTO, token);
		return new ResponseEntity<PanelDTO>(panel, HttpStatus.CREATED);
	}

	@GetMapping(value = "/associate/name/{associateName}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<PanelDTO>> getAssociateByName(@PathVariable("associateName") String associateName,
			@RequestHeader("Authorization") String token) {

		return new ResponseEntity<List<PanelDTO>>(impl.getByAssociateName(associateName, token), HttpStatus.FOUND);
	}

	/// Our Project

	@Autowired
	private FileExporter exporter;

	@GetMapping(value = "/export/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportByFilter(HttpServletResponse response, @RequestParam(required = false) String panelId,
			@RequestParam(required = false) String role, @RequestParam(required = false) String email,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fromDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate toDate,
			@RequestParam(required = false) String interviewType, @RequestParam(required = false) String panelName,
			@RequestParam(required = false) String availabilityStatus, @RequestHeader("Authorization") String token)
			throws IOException {
		List<PanelAvailabilityListDto> exportPanelList = impl.ExportPanelBYFilter(panelId, role, email, fromDate,
				toDate, interviewType, panelName, availabilityStatus, token);
		exporter.exportToCSV(exportPanelList, response, "Panel_List");

	}

	// @RequestMapping(method = RequestMethod.GET, value = "/panelPage")
	@GetMapping(value = "/search/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PanelAvailabilityListDto>> searchAdvertisesByFilterCriteria(
			@RequestParam(value = "panelId", required = false) String panelId,
			@RequestParam(value = "panelName", required = false) String panelName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "availabilityStatus", required = false) String availabilityStatus,
			@RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fromDate,
			@RequestParam(name = "toDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate toDate,
			@RequestParam(value = "role", required = false) String role,
			@RequestParam(value = "interviewType", required = false) String interviewType,
			// @RequestParam(name="dateCondition", required = false)String dateCondition,
			@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
			@RequestHeader("Authorization") String token) {
		// try {
		// if (impl.isTokenValidate(token)) {
		return new ResponseEntity<List<PanelAvailabilityListDto>>(impl.SearchPanelBYFilter(panelId, panelName, email,
				availabilityStatus, fromDate, toDate, role, interviewType, pageNo, pageSize, token), HttpStatus.FOUND);

		// } catch(InvalidUserException e)
		// {
		// return new
		// ResponseEntity<List<PanelAvailabilityListDto>>(HttpStatus.UNAUTHORIZED);
		// }
		// catch (EmptyListException e) {
		// throw new EmptyListException("List is Empty");
		// }
		// return null;

	}

}
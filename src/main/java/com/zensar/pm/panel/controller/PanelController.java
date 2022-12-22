package com.zensar.pm.panel.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.pm.panel.dto.PanelListDTO;
import com.zensar.pm.panel.export.FileExporter;
import com.zensar.pm.panel.service.ServiceInterface;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@CrossOrigin(origins = "*")
@Validated
@RequestMapping("/pm/panel-management")
@OpenAPIDefinition(info = @Info(description = "<h2>Master Data Management</h2>", version = "1.0.0"))
public class PanelController {

	@Autowired
	private ServiceInterface serviceImpl;

	@Autowired
	private FileExporter exporter;

	@GetMapping(value = "/export/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportByFilter(HttpServletResponse response, @RequestParam(required = false) String panelId,
			@RequestParam(required = false) String role, @RequestParam(required = false) String panelEmail,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fromDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate toDate,
			@RequestParam(required = false) String interviewType, @RequestParam(required = false) String panelName,
			@RequestParam(required = false) String availabilityStatus, @RequestHeader("Authorization") String token)
			throws IOException {
		List<PanelListDTO> exportPanelList = serviceImpl.filter(panelId, role, panelEmail, fromDate, toDate,
				interviewType, panelName, availabilityStatus, token);
		exporter.exportToCSV(exportPanelList, response, "Panel_List");

	}

	@GetMapping(value = "/search/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PanelListDTO> searchByFilter(HttpServletResponse response,
			@RequestParam(required = false) String panelId, @RequestParam(required = false) String role,
			@RequestParam(required = false) String panelEmail,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fromDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate toDate,
			@RequestParam(required = false) String interviewType, @RequestParam(required = false) String panelName,
			@RequestParam(required = false) String availabilityStatus,
			@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
			@RequestParam(value = "noOfRecords", defaultValue = "5") int pageSize,
			@RequestHeader("Authorization") String token) throws IOException {
		List<PanelListDTO> searchPanelList = serviceImpl.searchFilter(panelId, role, panelEmail, fromDate, toDate,
				interviewType, panelName, availabilityStatus, pageNo, pageSize, token);

		return searchPanelList;

	}

}

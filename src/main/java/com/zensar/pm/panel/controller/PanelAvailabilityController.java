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
	
	
	//team 10
	
	@Autowired
	PanelService impl;


    /// update
	
	
	@PutMapping(value = "/panelAvailability/{panelAvailabilityId}", consumes = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<PanelAvailabilityDTO> updatePanelAvailability(
            @PathVariable("panelAvailabilityId") Integer PanelAvailabilityId,
            @RequestBody PanelAvailabilityDTO panelAvailabilityDTO, @RequestHeader("Authorization") String token) {
        PanelAvailabilityDTO panelAvailability = impl.updatePanelAvailability(PanelAvailabilityId, panelAvailabilityDTO,
                token);
        return new ResponseEntity<PanelAvailabilityDTO>(panelAvailability, HttpStatus.CREATED);
    }
	
	



	/// Search & Export

	@Autowired
	private FileExporter exporter;

	@GetMapping(value = "/export/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportByFilter(HttpServletResponse response, @RequestParam(required = false) String panelId,
			@RequestParam(required = false) String role, @RequestParam(required = false) String email,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
			@RequestParam(required = false) String interviewType, 
			@RequestParam(required = false) String panelName,
			@RequestParam(required = false) String availabilityStatus,
			@RequestHeader("Authorization") String token)
			throws IOException {
		
		int panelIdint=0;
		

		
		if(panelId!=null && !panelId.isEmpty())
		{panelIdint=Integer.parseInt(panelId);}	
		
		List<PanelAvailabilityListDTO> exportPanelList = impl.ExportPanelBYFilter(panelIdint, role, email, fromDate,
				toDate, interviewType, panelName, availabilityStatus,token);
		exporter.exportToCSV(exportPanelList, response, "Panel_List");

	}

	@GetMapping(value = "/search/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ShowPanelAvailabilityListDTO searchAdvertisesByFilterCriteria(
			@RequestParam(value = "panelId", required = false) String panelId,
			@RequestParam(value = "panelName", required = false) String panelName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "availabilityStatus", required = false) String availabilityStatus,
			@RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@RequestParam(name = "toDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
			@RequestParam(value = "role", required = false) String role,
			@RequestParam(value = "interviewType", required = false) String interviewType,
			@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
			@RequestHeader("Authorization") String token) 
			{
	
		int panelIdint=0;
		
	
		
		if(panelId!=null && !panelId.isEmpty())
		{try{panelIdint=Integer.parseInt(panelId);} catch(Exception e) {throw new EmptyListException("Panel Id must Be integer");}}	
		
		
		return impl.SearchPanelBYFilter(panelIdint, panelName, email,
				availabilityStatus, fromDate, toDate, role, interviewType, pageNo, pageSize,token);


	}
	
	@GetMapping(value = "/getAllStatus",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PanelAvailabilityStatusDTO>AvailabilityStatus()
	{
	return impl.getByAvailabilityStatus();	
	}
		
	
	
	
    /// dynamicdropdown
    @GetMapping(value = "/Interview/Type",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InterviewTypeDTO> InterviewType()
    {
    return impl.DropDownConvertorInterviewType();    
    }

    @GetMapping(value = "/Availability/Status",produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<String> DropDownAvailabilityStatus()
    {
    return impl.DropDownConvertorStatus();    
    }

    @GetMapping(value = "/role",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoleDto> DropDownRole()
    {
    return impl.DropDownConvertorRole();
    }
///team 10
}

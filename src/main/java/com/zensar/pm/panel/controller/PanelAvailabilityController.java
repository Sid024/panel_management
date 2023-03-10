package com.zensar.pm.panel.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.pm.panel.dto.InterviewTypeDTO;
import com.zensar.pm.panel.dto.PanelAvailDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityListDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityPHDTO;
import com.zensar.pm.panel.dto.PanelAvailabilitySelfDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityStatusDTO;
import com.zensar.pm.panel.dto.PanelDTO;
import com.zensar.pm.panel.dto.PanelsGetAllResponseDTO;
import com.zensar.pm.panel.dto.RoleDto;
import com.zensar.pm.panel.dto.SearchByFilterDTO;
import com.zensar.pm.panel.dto.ShowPanelAvailabilityListDTO;
import com.zensar.pm.panel.entity.PanelEntity;
import com.zensar.pm.panel.exceptions.EmptyListException;
import com.zensar.pm.panel.exceptions.InvalidPanelException;
import com.zensar.pm.panel.export.FileExporter;
import com.zensar.pm.panel.service.PanelAvailabilityService;
import com.zensar.pm.panel.service.PanelService;

@RestController
@RequestMapping("/pm/panel-management")
@Validated
@CrossOrigin("*")
public class PanelAvailabilityController {
	
	@Autowired
	PanelAvailabilityService service;
	
	

	@PostMapping(value = "/panel/availability", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<PanelAvailabilityDTO> addPanelAvailablity(@RequestBody PanelAvailabilityDTO dto) {
		return new ResponseEntity<PanelAvailabilityDTO>(service.addPanelAvailablitySingle(dto),HttpStatus.OK);
	}
	
	@GetMapping(value="/panel/availability/getRole",produces = MediaType.APPLICATION_JSON_VALUE)
	public PanelsGetAllResponseDTO searchRole(@RequestHeader("Authorization") String token) {
		
		PanelsGetAllResponseDTO dto=service.getLoginRole(token);
		return dto;
	}
	
	@GetMapping(value="panel/availability/filter",produces = MediaType.APPLICATION_JSON_VALUE)
	public PanelsGetAllResponseDTO filterByPanelName(@RequestParam(required = true) String panelName ){
		PanelsGetAllResponseDTO panelNameList=service.filterByPanelName(panelName);
		return panelNameList;
	}
	
	//team 10
	
	@Autowired
	PanelService panelService;


    /// update
	
	
	@PutMapping(value = "/panelAvailability/{panelAvailabilityId}", consumes = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<PanelAvailabilityDTO> updatePanelAvailability(
            @PathVariable("panelAvailabilityId") Integer PanelAvailabilityId,
            @RequestBody PanelAvailabilityDTO panelAvailabilityDTO, @RequestHeader("Authorization") String token) {
        PanelAvailabilityDTO panelAvailability = panelService.updatePanelAvailability(PanelAvailabilityId, panelAvailabilityDTO,
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
		
		List<PanelAvailabilityListDTO> exportPanelList = panelService.ExportPanelBYFilter(panelIdint, role, email, fromDate,
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
		
		    if(panelName!=null && !panelName.isEmpty())
        {
            int charcter = panelName.charAt(0);
            if(charcter>=65 && charcter<=95 || charcter>=97 && charcter<=122)
            {

            }
            else
                throw new EmptyListException("Please write a proper Name");
        }
		
		return panelService.SearchPanelBYFilter(panelIdint, panelName, email,
				availabilityStatus, fromDate, toDate, role, interviewType, pageNo, pageSize,token);


	}
	
	
	/// search for panel
	@GetMapping(value = "/search/filter/panel", produces = MediaType.APPLICATION_JSON_VALUE)
	public ShowPanelAvailabilityListDTO searchAdvertisesByFilterCriteriaPanel(

			@RequestParam(value = "availabilityStatus", required = false) String availabilityStatus,
			@RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@RequestParam(name = "toDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
			@RequestParam(value = "interviewType", required = false) String interviewType,
			@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
			@RequestHeader("Authorization") String token) 
	
	
			{
	
		
		return panelService.SearchByPanel(availabilityStatus, fromDate, toDate,interviewType, pageNo, pageSize,token);


	}
	
	
	
	
	
	
	
	@GetMapping(value = "/getAllStatus",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PanelAvailabilityStatusDTO>AvailabilityStatus()
	{
	return panelService.getByAvailabilityStatus();	
	}
//	@GetMapping(value = "/panelList")
//	public ResponseEntity<List<PanelDTO>> getAllPanel(){
//		List<PanelDTO> allPanel = panelService.getAllPanel();
//		return new ResponseEntity<List<PanelDTO>>(allPanel, HttpStatus.OK);
//	}
	
	@GetMapping(value="/panel/list")
	public ResponseEntity<SearchByFilterDTO> searchUserByFilterCriteria(
            @RequestHeader(value = "Authorization") String token,
            @Valid @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(value = "panelId", defaultValue = "0") int panelId,
            @RequestParam(value = "panelName", required = false) String panelName,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "grade", required = false) String grade,
            @RequestParam(value = "role", required = false) String role,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "isActive", required = false, defaultValue = "true") boolean isActive)
                    throws InvalidPanelException {

        SearchByFilterDTO searchPanelByFilter = panelService.searchPanelByFilter(panelId, panelName, email, grade, role, type, isActive, token, pageNumber, pageSize);
        if (searchPanelByFilter != null) {
            if (searchPanelByFilter.getTotalNumberOfRecords() > 0) {
                return new ResponseEntity<SearchByFilterDTO>(searchPanelByFilter, HttpStatus.OK);
            } else {
                throw new InvalidPanelException("Panel Not Found");
            }
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

 

    }
	
	@PutMapping(value = "/panel/toggle/{panelId}")
    public ResponseEntity<String> updateIsActive(@PathVariable("panelId") int panelId,@RequestHeader(value = "Authorization") String token) {
        String updateIsActive = panelService.updateIsActive(panelId, token);
        if(updateIsActive!=null) {
            return new ResponseEntity<String>("Status Updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("Status not updated",HttpStatus.BAD_REQUEST);



    }
                   
	
		
	
	
	
    /// dynamicdropdown
    @GetMapping(value = "/Interview/Type",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InterviewTypeDTO> InterviewType()
    {
    return panelService.DropDownConvertorInterviewType();    
    }

  

    @GetMapping(value = "/role",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoleDto> DropDownRole()
    {
    return panelService.DropDownConvertorRole();
    }
///team 10
    
    @PostMapping(value="/panel",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<String> createPanel(@RequestHeader(value = "Authorization") String token,@RequestBody @Valid PanelDTO panelDTO) {
		boolean createPanel = panelService.createPanel(panelDTO,token);
		if(createPanel){
			return new ResponseEntity("Created Successfully", HttpStatus.CREATED);
		}else {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);

		}	
	}
	
	@PutMapping(value="/panel",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<String> updatePanel(@RequestHeader(value = "Authorization") String token,@RequestBody @Valid PanelDTO panelDTO) {
		boolean updatePanel = panelService.updatePanel(panelDTO,token);
		if(updatePanel){
			return new ResponseEntity("Updated Successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);

		}	
	}
	
	
	//team8
	@GetMapping(value="/panel",produces= {MediaType.APPLICATION_JSON_VALUE})  
    public ResponseEntity<List<PanelAvailDTO>> panelGetAll(
            @RequestParam(value = "panelName", required = false) String panelName,@RequestHeader("Authorization") String token){
        return new ResponseEntity<List<PanelAvailDTO>>(service.getPanelByFilterCriteria(panelName,token), HttpStatus.OK);

    }
	
	@GetMapping(value="/panelName",produces= {MediaType.APPLICATION_JSON_VALUE})  //listofUsernames
    public ResponseEntity<List<String>> listPanelNames() {
        return new ResponseEntity<List<String>>(panelService.getAllPanelNames(),HttpStatus.OK);
    }

    @GetMapping(value="/panelName/{panelName}",produces= {MediaType.APPLICATION_JSON_VALUE})  //searchByName
    public ResponseEntity<PanelAvailabilityPHDTO> getPanelDetails(@PathVariable ("panelName") String panelName,@RequestHeader("Authorization") String token) {
        return new ResponseEntity<PanelAvailabilityPHDTO>(service.getPanelDetails(panelName,token),HttpStatus.OK);
    }
    
    
    @GetMapping(value="/panel/self")
	public ResponseEntity<PanelAvailabilitySelfDTO> searchUserByFilterCriteria(   //selfDetails
            @RequestHeader(value = "Authorization") String token){
    	return new ResponseEntity<PanelAvailabilitySelfDTO>(service.getLoginRoleByPanelAvail(token),HttpStatus.OK);
    }
    
    @PostMapping(value="/panel/multipleSlots",produces= {MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> postMultiple(@RequestBody PanelAvailDTO pa)
    {
    	return new ResponseEntity<String>(service.post(pa),HttpStatus.CREATED);
    }
}

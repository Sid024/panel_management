package com.zensar.pm.panel.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.zensar.pm.panel.dto.PanelAvailabilityListDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityStatusDTO;
import com.zensar.pm.panel.dto.PanelDTO;
import com.zensar.pm.panel.dto.RoleDto;
import com.zensar.pm.panel.dto.SearchByFilterDTO;
import com.zensar.pm.panel.dto.InterviewTypeDTO;
import com.zensar.pm.panel.dto.PanelAvailabilityDTO;
import com.zensar.pm.panel.dto.ShowPanelAvailabilityListDTO;
import com.zensar.pm.panel.entity.PanelAvailabilityEntity;

public interface PanelService {


	       	
	
	public List<PanelAvailabilityListDTO> ExportPanelBYFilter(int panelId, String role,
            String email,LocalDate fromDate,LocalDate toDate, String interviewType, String panelName,
            String availabilityStatus,String jwtToken);
	public ShowPanelAvailabilityListDTO  SearchPanelBYFilter(int panelId, String panelName, String email,
			String availabilityStatus, LocalDate fromDate, LocalDate toDate, String role, String interviewType,
			int pageNo, int pageSize, String jwtToken);
	
	public PanelAvailabilityDTO updatePanelAvailability(Integer panelAvailablityId, PanelAvailabilityDTO panelAvailablityDTO,String jwtToken);
	
	public PanelDTO getAllPanel();

	public SearchByFilterDTO searchPanelByFilter(int panelId, String panelName, String email, String grade, String role,
			String type, boolean isActive, String token);



	public List<PanelAvailabilityStatusDTO> getByAvailabilityStatus();
	
	
	
	//dropdown

	public Set<String> DropDownConvertorStatus();
	public List<InterviewTypeDTO> DropDownConvertorInterviewType();
	public List<RoleDto> DropDownConvertorRole();


}

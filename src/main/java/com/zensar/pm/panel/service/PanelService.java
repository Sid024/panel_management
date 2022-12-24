package com.zensar.pm.panel.service;

import java.time.LocalDate;
import java.util.List;

import com.zensar.pm.panel.dto.PanelAvailabilityListDto;
import com.zensar.pm.panel.dto.PanelDTO;

public interface PanelService {

	public PanelDTO updatePanel(String associateId, PanelDTO panelDTO,String jwtToken);
	public List<PanelDTO> getByAssociateName(String name,String jwtToken);
	
	
	
	public List<PanelAvailabilityListDto> SearchPanelBYFilter(String panelId, String panelName, String email,
            String availabilityStatus, LocalDate fromDate, LocalDate toDate,
            String role, String interviewType,int pageNo, int pageSize, String jwtToken);
	
    public List<PanelAvailabilityListDto> ExportPanelBYFilter(String panelId, String role,
            String email,LocalDate fromDate,LocalDate toDate, String interviewType, String panelName,
            String availabilityStatus,String jwtToken);

}

package com.zensar.pm.panel.service;

import java.time.LocalDate;
import java.util.List;

import com.zensar.pm.panel.dto.PanelListDTO;



public interface ServiceInterface {
   
	public List<PanelListDTO> filter(String panel_id, String role,
			String panel_email,LocalDate start_date,LocalDate enddate,String interview_type, String panel_name,
			String availability_status,String jwtToken);	

	public List<PanelListDTO> searchFilter(String panel_id, String panel_role,
			String panel_email,LocalDate start_date,LocalDate end_date, String interview_type, String panel_name,
			String Availability_status,int pageNo, int pageSize,String jwtToken);
}
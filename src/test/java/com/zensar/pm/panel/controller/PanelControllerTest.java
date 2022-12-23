package com.zensar.pm.panel.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.pm.panel.dto.PanelDTO;
import com.zensar.pm.panel.exceptions.CustomNullPointerException;
import com.zensar.pm.panel.exceptions.InvalidAssociateNameException;
import com.zensar.pm.panel.exceptions.InvalidPanelException;
import com.zensar.pm.panel.service.PanelService;

@WebMvcTest(PanelController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PanelControllerTest {
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	MockMvc mockMvc;
	@MockBean
	PanelService panelService;

	@Test
	public void testUpdatePanel() throws Exception {
		PanelDTO panel = new PanelDTO( "vn75213", "Venkata Subhash", "G0", LocalDate.now() , LocalTime.now(), LocalTime.now(),
				"Not Available");
		
		when(this.panelService.updatePanel(panel.getAssociateId(), panel,"AAA")).thenReturn(panel);
		MvcResult mvcResult = this.mockMvc.perform(put("http://localhost:8003/pm/panel-management/panel/vn75213")
				.header("Authorization", "AAA").contentType("application/json")
				.content(objectMapper.writeValueAsString(panel))

		).andExpect(status().isCreated())
				// .andExpect(content().string(containsString("HTML")))
				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Not Available"), true);
	}

	@Test
	public void testUpdatePanelFail() throws Exception {
		PanelDTO panel = new PanelDTO( "vn75213", "Venkata Subhash", "G0", LocalDate.now(), LocalTime.now(), LocalTime.now(),
				"Not Available");

		when(this.panelService.updatePanel("2", panel,"AAA")).thenThrow(new InvalidPanelException("Panel not found"));
		MvcResult mvcResult = this.mockMvc.perform(put("http://localhost:8003/pm/panel-management/panel/2")
				.header("Authorization", "AAA").contentType("application/json")
				.content(objectMapper.writeValueAsString(panel))

		).andExpect(status().isBadRequest())

				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Panel not found"), true);
	}

	@Test
	public void testUpdatePanelFail1() throws Exception {
		PanelDTO panel = new PanelDTO( "vn75213", "Venkata Subhash", "G0", LocalDate.now(), LocalTime.now(), LocalTime.now(),
				null);

		when(this.panelService.updatePanel(panel.getAssociateId(), panel,"AAA"))
				.thenThrow(new CustomNullPointerException("Empty value! enter the value"));
		MvcResult mvcResult = this.mockMvc.perform(put("http://localhost:8003/pm/panel-management/panel/vn75213")
				.header("Authorization", "AAA").contentType("application/json")
				.content(objectMapper.writeValueAsString(panel))

		).andExpect(status().isNotFound())

				.andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Empty value! enter the value"), true);
	}

	
	@Test
	public void testGetAssociateByName() throws Exception {
		List<PanelDTO> associates = new ArrayList<>();
		PanelDTO associate = new PanelDTO("pavan23", "pavanreddy", "g0");
		associates.add(associate);

		when(panelService.getByAssociateName("pavanreddy", "AAA")).thenReturn(associates);
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/associate/name/pavanreddy")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(associates)))
				.andExpect(status().isFound()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("pavanreddy"),true);
	}
	 
    

	@Test
    public void testGetAssociateByNameFailure() throws Exception {
        List<PanelDTO> associates = new ArrayList<>();
        PanelDTO associate = new PanelDTO();
        associates.add(associate);

 

        when(this.panelService.getByAssociateName("subash","AAA")).thenThrow(InvalidAssociateNameException.class);
        MvcResult mvcResult = this.mockMvc
                .perform(get("http://localhost:8003/pm/panel-management/associate/name/subash").header("Authorization","AAA")
                        .contentType("application/json").content(objectMapper.writeValueAsString(associates)))
                .andExpect(status().isNotFound()).andReturn();
    }

}

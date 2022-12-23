package com.zensar.pm.panel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.zensar.pm.panel.dto.PanelDTO;
import com.zensar.pm.panel.dto.UserDTO;
import com.zensar.pm.panel.entity.Panel;
import com.zensar.pm.panel.exceptions.UnauthorizedUserException;
import com.zensar.pm.panel.repository.PanelRepository;

@WebMvcTest(PanelServiceImplementation.class)
@AutoConfigureMockMvc(addFilters = false)
public class PanelServiceTest {
	@MockBean
	ModelMapper modelMapper;
	@MockBean
	PanelRepository panelRepository;
	@MockBean
	LoginDelegate loginDelegate;

	@Test
	public void testUpdatePanel() throws Exception {
		String token = "sample token";

		PanelServiceImplementation panelService = new PanelServiceImplementation();
		panelService.modelMapper = this.modelMapper;
		panelService.panelRepository = this.panelRepository;
		panelService.loginDelegate = this.loginDelegate;

		PanelDTO panel = new PanelDTO("vn75213", "Venkata Subhash", "G0", LocalDate.now(), LocalTime.now(),
				LocalTime.now(), "Not Available");
		Panel panelEntity = new Panel("vn75213", "Venkata Subhash", "G0", LocalDate.now(), LocalTime.now(),
				LocalTime.now(), "Not Available" );
		Optional<Panel> opPanelEntity = Optional.of(panelEntity);
		UserDTO user = new UserDTO(1, "abc", "Ram", "Practice Head", 5);

		when(panelRepository.findById("vn75213")).thenReturn(opPanelEntity);
		when(loginDelegate.isTokenValid(token)).thenReturn(user);
		when(modelMapper.map(any(PanelDTO.class), eq(Panel.class))).thenReturn(panelEntity);
		when(panelRepository.save(panelEntity)).thenReturn(panelEntity);
		when(modelMapper.map(any(Panel.class), eq(PanelDTO.class))).thenReturn(panel);
		PanelDTO expected = panelService.updatePanel("vn75213", panel, token);
		assertEquals(panel, expected);
	}

	@Test
	public void testInvalidUser() {
		String token = "sample token";

		PanelServiceImplementation panelService = new PanelServiceImplementation();
		panelService.modelMapper = this.modelMapper;
		panelService.panelRepository = this.panelRepository;
		panelService.loginDelegate = this.loginDelegate;

		PanelDTO panel = new PanelDTO("vn75213", "Venkata Subhash", "G0", LocalDate.now(), LocalTime.now(),
				LocalTime.now(), "Not Available");
		Panel panelEntity = new Panel();
		panelEntity.setAssociateGrade("D1");
		panelEntity.setAssociateId("VN75213");
		UserDTO user = new UserDTO(1, "abc", "Ram", "panel", 1233);

		when(loginDelegate.isTokenValid(token)).thenReturn(user);
		when(modelMapper.map(any(PanelDTO.class), eq(Panel.class))).thenReturn(panelEntity);
		when(panelRepository.save(panelEntity)).thenReturn(panelEntity);
		when(modelMapper.map(any(Panel.class), eq(PanelDTO.class))).thenReturn(panel);

		assertThrows(UnauthorizedUserException.class, () -> panelService.updatePanel("vn75213", panel, token));
	}

	@Test
	public void testInvalidUser2() {
		String token = "sample token";

		PanelServiceImplementation panelService = new PanelServiceImplementation();
		panelService.modelMapper = this.modelMapper;
		panelService.panelRepository = this.panelRepository;
		panelService.loginDelegate = this.loginDelegate;

		PanelDTO panel = new PanelDTO("vn75213", "Venkata Subhash", "G0", LocalDate.now(), LocalTime.now(),
				LocalTime.now(), "Not Available");
		Panel panelEntity = new Panel();
		panelEntity.setAssociateGrade("D1");
		panelEntity.setAssociateId("VN75213");
		UserDTO user = new UserDTO(1, "abc", "Ram", "Talent Acquisition", 1233);

		when(loginDelegate.isTokenValid(token)).thenReturn(user);
		when(modelMapper.map(any(PanelDTO.class), eq(Panel.class))).thenReturn(panelEntity);
		when(panelRepository.save(panelEntity)).thenReturn(panelEntity);
		when(modelMapper.map(any(Panel.class), eq(PanelDTO.class))).thenReturn(panel);

		assertThrows(UnauthorizedUserException.class, () -> panelService.updatePanel("vn75213", panel, token));
	}
}

	

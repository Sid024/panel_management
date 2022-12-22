package com.zensar.pm.panel.controller;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.pm.panel.dto.PanelListDTO;
import com.zensar.pm.panel.entity.PanelList;
import com.zensar.pm.panel.exception.InvalidDataException;
import com.zensar.pm.panel.exception.WrongDateException;
import com.zensar.pm.panel.service.ServiceInterface;

@WebMvcTest(PanelController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PanelControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	ServiceInterface stockService;
	@Autowired
	ObjectMapper objectMapper;

	@DisplayName("Test By ID")
	@Test
	public void testFilterById() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("PS123400", null, null, null, null, null, null, null, null);

		students.add(student);
		when(stockService.searchFilter("PS12346", null, null, null, null, null, null, null, 0, 0, "AAA"))
				.thenReturn(students);
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/export/filter?Panel_ID=1")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))

				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);
	}

	// (Panel_Id,Role,Panel_Email,From_Date,To_Date,Interview_Type,Panel_Name,Availability_Status);
	@DisplayName("Test By Role")
	@Test
	public void testFilterByRole() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("PS123", null, null, null, null, null, null, null, null);

		students.add(student);
		when(stockService.searchFilter(null, "TA", null, null, null, null, null, null, 0, 0, "AAA"))
				.thenReturn(students);
		MvcResult mvcResult = this.mockMvc.perform(
				get("http://localhost:8003/pm/panel-management/export/filter?Role=TA").header("Authorization", "AAA")
						.contentType("application/json").content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);
	}

	@DisplayName("Test By Name")
	@Test
	public void testFilterByName() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("PS12", null, null, null, null, null, null, null, null);

		students.add(student);
		when(stockService.searchFilter(null, null, null, null, null, null, "Nirbhay", null, 0, 0, "AAA"))
				.thenReturn(students);
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/export/filter?Panel_Name=Nirbhay")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);
	}

	@DisplayName("Test By Date")
	@Test
	public void testFilterByDate() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("PS1", null, null, null, null, null, null, null, null);

		students.add(student);
		when(stockService.searchFilter(null, null, null, LocalDate.of(2000,12,02),LocalDate.of(2023,11,19), null,
				null, null, 0, 0, "AAA")).thenReturn(students);
		MvcResult mvcResult = this.mockMvc.perform(get(
				"http://localhost:8003/pm/panel-management/export/filter?Role=TA&From_Date=02/12/2000&To_Date=19/11/2023&Availability_Status=yes")
				.header("Authorization", "AAA").contentType("application/json")
				.content(objectMapper.writeValueAsString(students))).andExpect(status().isBadRequest()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);
	}

	@DisplayName("Test by all")
	@Test
	public void testFilterByAll() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("PS123400", "Nirbhay", "nirbhay12.com", "123456789", "TA", "Entry",
				LocalDate.of(2023, 11, 11), "10-55", "no");

		students.add(student);
		when(stockService.searchFilter("PS12346", "TA", "nirbhay12.com", LocalDate.of(2000, 12, 11),
				LocalDate.of(2023, 11, 11), "Entry", "Nirbhay", "no", 0, 0, "AAA")).thenReturn(students);
		MvcResult mvcResult = this.mockMvc.perform(get(
				"http://localhost:8003/pm/panel-management/export/filter?Panel_Id=PS12346&Role=TA&Panel_Email=nirbhay12.com&From_Date=11/12/2000&To_Date=11/11/2023&Interview_Type=Entry&Panel_Name=Nirbhay&Availability_Status=no")
				.header("Authorization", "AAA").contentType("application/json")
				.content(objectMapper.writeValueAsString(students))).andExpect(status().isBadRequest()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);

	}

	@DisplayName("Test by status")
	@Test
	public void testFilterByStatus() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("PS12", null, null, null, null, null, null, null, "AAA");

		students.add(student);
		when(stockService.searchFilter(null, null, null, null, null, null, null, "yes", 0, 0, null))
				.thenReturn(students);
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/export/filter?Availability_Status=yes")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);
	}

	@DisplayName("Test By Email")
	@Test
	public void testFilterByEmail() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("PS123401", null, null, null, null, null, null, null, null);

		students.add(student);
		when(stockService.searchFilter(null, null, "nirbhay12.com", null, null, null, null, null, 0, 0, "AAA"))
				.thenReturn(students);
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/export/filter?Panel_Email=nirbhay12.com")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);
	}

	@DisplayName("Test By failed Email")
	@Test
	public void testFilterByEmailFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO(null, null, "nirbhay12.com", null, null, null, null, null, null);

		students.add(student);
		when(stockService.searchFilter(null, null, "nirbhay1234.com", null, null, null, null, null, 0, 0, "AAA")
				.isEmpty()).thenThrow(new InvalidDataException("Invalid Email"));
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/export/filter?Panel_Email=nirbhay1234.com")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isNotFound()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Invalid Email"), true);
	}

	@DisplayName("Test By failed id")
	@Test
	public void testFilterByIdFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("1234", null, null, null, null, null, null, null, null);

		students.add(student);
		when(stockService.searchFilter("1234", null, null, null, null, null, null, null, 0, 0, "AAA").isEmpty())
				.thenThrow(new InvalidDataException("Invalid ID"));
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/export/filter?Panel_Id=1234")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Invalid ID"), false);
	}

	@DisplayName("Test By failed role")
	@Test
	public void testFilterByRoleFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO(null, "Fresher", null, null, null, null, null, null, null);

		students.add(student);
		when(stockService.searchFilter(null, "Fresher", null, null, null, null, null, null, 0, 0, "AAA").isEmpty())
				.thenThrow(new InvalidDataException("Invalid Role"));
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/export/filter?Role=Fresher")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isNotFound()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Invalid Role"), true);
	}

	@DisplayName("Test By failed Name")
	@Test
	public void testFilterByNameFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO(null, null, null, null, null, null, null, "Xyz", null);

		students.add(student);
		when(stockService.searchFilter(null, null, null, null, null, null, "Xyz", null, 0, 0, "AAA").isEmpty())
				.thenThrow(new InvalidDataException("Invalid Name"));
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/export/filter?Panel_Name=Xyz")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isNotFound()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Invalid Name"), true);
	}

	@DisplayName("Test By failed Date")
	@Test
	public void testFilterByDateFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO(null, null, null, null, null, null, LocalDate.of(2022, 11, 11), null,
				null);

		students.add(student);
		when(stockService.searchFilter(null, null, null, LocalDate.of(2024, 11, 11), LocalDate.of(2023, 11, 11), null,
				null, null, 0, 0, "AAA").isEmpty())
				.thenThrow(new WrongDateException("From Date should be before To Date"));
		MvcResult mvcResult = this.mockMvc.perform(
				get("http://localhost:8003/pm/panel-management/export/filter?From_Date=11/11/2024&To_Date=11/11/2023")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isBadRequest()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("From Date should be before To Date"), false);
	}

	@DisplayName("Test By failed status")
	@Test
	public void testFilterByStatusFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO(null, null, null, null, null, null, null, null, "NA");

		students.add(student);
		when(stockService.searchFilter(null, null, null, null, null, null, null, "NA", 0, 0, "AAA").isEmpty())
				.thenThrow(new InvalidDataException("Invalid Data"));
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/export/filter?Availability_Status=NA")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isNotFound()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Invalid Data"), true);
	}

	@DisplayName("Failed test by multiple incorrect value")
	@Test
	public void testFailedByMultipleValueFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("123", null, null, null, null, null, null, null, "NA");

		students.add(student);
		when(stockService.searchFilter("123", null, null, null, null, null, null, "NA", 0, 0, "AAA").isEmpty())
				.thenThrow(new InvalidDataException("Invalid Data"));
		MvcResult mvcResult = this.mockMvc.perform(
				get("http://localhost:8003/pm/panel-management/export/filter?Panel_Id=123&Availability_Status=NA")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Invalid Data"), false);
	}

	/// search test
	@DisplayName("Test By ID")
	@Test
	public void testSearchFilterById() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("PS123400", null, null, null, null, null, null, null, null);
		students.add(student);
		when(stockService.searchFilter("PS123400", null, null, null, null, null, null, null, 0, 0, "AAA"))
				.thenReturn(students);
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/search/filter?Panel_Id=PS12346")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);
	}

	@DisplayName("Test By Role")
	@Test
	public void testSearchFilterByRole() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("PS123", null, null, null, null, null, null, null, null);
		students.add(student);
		when(stockService.searchFilter(null, "TA", null, null, null, null, null, null, 0, 0, "AAA"))
				.thenReturn(students);
		MvcResult mvcResult = this.mockMvc.perform(
				get("http://localhost:8003/pm/panel-management/search/filter?Role=TA").header("Authorization", "AAA")
						.contentType("application/json").content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);
	}

	@DisplayName("Test By Name")
	@Test
	public void testSearchFilterByName() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("PS12", null, null, null, null, null, null, null, null);
		students.add(student);
		when(stockService.searchFilter(null, null, null, null, null, null, "Nirbhay", null, 0, 0, "AAA"))
				.thenReturn(students);
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/search/filter?Panel_Name=Nirbhay")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);
	}

	@DisplayName("Test By Date")
	@Test
	public void testSearchFilterByDate() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("PS1", null, null, null, null, null, null, null, null);
		students.add(student);
		when(stockService.searchFilter(null, null, null, LocalDate.of(1001,12,02), LocalDate.of(3001, 11, 19), null,null, null, 0, 0, "AAA"))
		.thenReturn(students);
		MvcResult mvcResult = this.mockMvc.perform(get(
				"http://localhost:8003/pm/panel-management/search/filter?Role=TA&From_Date=02/12/1001&To_Date=19/11/3001")
				.header("Authorization", "AAA").contentType("application/json")
				.content(objectMapper.writeValueAsString(students))).andExpect(status().isBadRequest()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);
	}

	@DisplayName("Test by all")
	@Test
	public void testSearchFilterByAll() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("PS123400", "Nirbhay", "nirbhay12.com", "123456789", "TA", "Entry",
				LocalDate.of(2023, 11, 11), "10-55", "no");
		students.add(student);
		when(stockService.searchFilter("PS12346", "TA", "nirbhay12.com", LocalDate.of(2000, 12, 11),
				LocalDate.of(2023, 11, 11), "Entry", "Nirbhay", "no", 0, 0, "AAA")).thenReturn(students);
		MvcResult mvcResult = this.mockMvc.perform(get(
				"http://localhost:8003/pm/panel-management/search/filter?Panel_Id=PS12346&Role=TA&Panel_Email=nirbhay12.com&From_Date=11/12/2000&To_Date=11/11/2023&Interview_Type=Entry&Panel_Name=Nirbhay&Availability_Status=no")
				.header("Authorization", "AAA").contentType("application/json")
				.content(objectMapper.writeValueAsString(students))).andExpect(status().isBadRequest()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);

	}

	@DisplayName("Test by status")
	@Test
	public void testSearchFilterByStatus() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("PS12", null, null, null, null, null, null, null, null);
		students.add(student);
		when(stockService.searchFilter(null, null, null, null, null, null, null, "yes", 0, 0, "AAA"))
				.thenReturn(students);
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/search/filter?Availability_Status=yes")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);
	}

	@DisplayName("Test by status")
	@Test
	public void testSearchFilterByEmail() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO(null, null, null, null, null, null, null, null, null);
		students.add(student);
		when(stockService.searchFilter(null, null, "as@.com", null, null, null, null, "yes", 0, 0, "AAA"))
				.thenReturn(students);
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/search/filter?Panel_Email=as@.com")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Zensar"), false);
	}

	@DisplayName("Test By failed Email")
	@Test
	public void testSearchFilterByEmailFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO(null, null, "nirbhay12.com", null, null, null, null, null, null);
		students.add(student);
		when(stockService.searchFilter(null, null, "nirbhay1234.com", null, null, null, null, null, 0, 0, "AAA")
				.isEmpty()).thenThrow(new InvalidDataException("Invalid Email"));
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/search/filter?Panel_Email=nirbhay1234.com")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Invalid Email"), false);
	}

	@DisplayName("Test By failed id")
	@Test
	public void testSearchFilterByIdFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("1234", null, null, null, null, null, null, null, null);
		students.add(student);
		when(stockService.searchFilter("1234", null, null, null, null, null, null, null, 0, 0, "AAA").isEmpty())
				.thenThrow(new InvalidDataException("Invalid Email"));
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/search/filter?Panel_Email=nirbhay1234.com")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Invalid ID"), false);
	}

	@DisplayName("Test By failed role")
	@Test
	public void testSearchFilterByRoleFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO(null, "Fresher", null, null, null, null, null, null, null);
		students.add(student);
		when(stockService.searchFilter(null, "Fresher", null, null, null, null, null, null, 0, 0, "AAA").isEmpty())
				.thenThrow(new InvalidDataException("Invalid Role"));
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/search/filter?Role=Fresher")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Invalid Role"), false);
	}

	@DisplayName("Test By failed Name")
	@Test
	public void testSearchFilterByNameFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO(null, null, null, null, null, null, null, "Xyz", null);
		students.add(student);
		when(stockService.searchFilter(null, null, null, null, null, null, "Xyz", null, 0, 0, "AAA").isEmpty())
				.thenThrow(new InvalidDataException("Invalid Name"));
		MvcResult mvcResult = this.mockMvc
				.perform(get("http://localhost:8003/pm/panel-management/search/filter?Panel_Name=Xyz")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Invalid Name"), false);
	}

	@DisplayName("Test By failed Date")
	@Test
	public void testSearchFilterByDateFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO(null, null, null, null, null, null, LocalDate.of(2022, 11, 11), null,
				null);
		students.add(student);
		when(stockService.searchFilter(null, null, null, LocalDate.of(2024, 11, 11), LocalDate.of(2023, 11, 11), null,
				null, null, 0, 0,"AAA").isEmpty())
				.thenThrow(new WrongDateException("From Date should be before To Date"));
		MvcResult mvcResult = this.mockMvc.perform(
				get("http://localhost:8003/pm/panel-management/search/filter?From_Date=11/11/2024&To_Date=11/11/2023")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isBadRequest()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("From Date should be before To Date"), false);
	}
	
	
	@DisplayName("Test By failed status")
	@Test
	public void testSearchFilterByStatusFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO(null,null,null,null,null,null,null,null,"NA");
		students.add(student);
		when(stockService.searchFilter(null,null,null,null,null,null,null,"NA",0,0,"AAA").isEmpty())
				.thenThrow(new WrongDateException("From Date should be before To Date"));
		MvcResult mvcResult = this.mockMvc.perform(
				get("http://localhost:8003/pm/panel-management/search/filter?Availability_Status=NA")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isBadRequest()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Invaild Data"), false);
	}

	

	@DisplayName("Failed test by multiple incorrect value")
	@Test
	public void testSearchFailedByMultipleValueFailed() throws Exception {
		List<PanelListDTO> students = new ArrayList<>();
		PanelListDTO student = new PanelListDTO("123", null, null, null, null, null, null, null, "NA");
		students.add(student);
		when(stockService.searchFilter("123", null, null, null, null, null, null, "NA", 0, 0, "AAA").isEmpty())
				.thenThrow(new InvalidDataException("Invalid Data"));
		MvcResult mvcResult = this.mockMvc.perform(
				get("http://localhost:8003/pm/panel-management/search/filter?Panel_Id=123&Availability_Status=NA")
						.header("Authorization", "AAA").contentType("application/json")
						.content(objectMapper.writeValueAsString(students)))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		assertEquals(response.contains("Invalid Data"), false);
	}
}

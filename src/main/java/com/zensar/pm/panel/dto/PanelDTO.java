package com.zensar.pm.panel.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PanelDTO {

	private String email;
	private LocalDate fromDate;
	private LocalDate toDate;
	private int panelId;
	private String panelName;
	private String panalRole;
	private String interviewType;
	private String availabilityStatus;

	public PanelDTO(String email, LocalDate fromDate, LocalDate toDate, int panelId, String panelName, String panalRole,
			String interviewType, String availabilityStatus) {
		super();
		this.email = email;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.panelId = panelId;
		this.panelName = panelName;
		this.panalRole = panalRole;
		this.interviewType = interviewType;
		this.availabilityStatus = availabilityStatus;

	}

	public PanelDTO() {
		super();

	}

}

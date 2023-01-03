package com.zensar.pm.panel.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class PanelAvailabilityStatusDTO {
	
private int availabilityStatusId;
	

	private String availabilityStatus;


	public int getAvailabilityStatusId() {
		return availabilityStatusId;
	}


	public void setAvailabilityStatusId(int availablityStatusId) {
		this.availabilityStatusId = availablityStatusId;
	}


	public String getAvailabilityStatus() {
		return availabilityStatus;
	}


	public void setAvailabilityStatus(String availablityStatus) {
		this.availabilityStatus = availablityStatus;
	}
	
	
	
	
	

}

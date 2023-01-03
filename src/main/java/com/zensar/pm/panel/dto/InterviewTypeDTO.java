package com.zensar.pm.panel.dto;

public class InterviewTypeDTO {

	private String interviewType;
	private int interviewID;
	public String getInterviewType() {
		return interviewType;
	}
	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}
	public int getInterviewID() {
		return interviewID;
	}
	public void setInterviewID(int interviewID) {
		this.interviewID = interviewID;
	}
	public InterviewTypeDTO(String interviewType, int interviewID) {
		super();
		this.interviewType = interviewType;
		this.interviewID = interviewID;
	}
	public InterviewTypeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

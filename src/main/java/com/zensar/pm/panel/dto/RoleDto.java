package com.zensar.pm.panel.dto;
import java.util.Set;

public class RoleDto {

	private String roleString;
	private int roleId=0;
	public String getRoleString() {
		return roleString;
	}
	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public RoleDto(String roleString, int roleId) {
		super();
		this.roleString = roleString;
		this.roleId = roleId;
	}
	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}

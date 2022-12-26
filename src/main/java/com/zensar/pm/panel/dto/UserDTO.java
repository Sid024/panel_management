package com.zensar.pm.panel.dto;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class UserDTO {
	
	private int userId;
	
//	@NotNull(message = "username cannot be blank")
	private String userName;
	private String userPassword;
	
	@Email
//	@NotNull
	private String email;
//	private String roleEntity;
	private String token;
	private String confirmPassword;
	private String roleName;
	
//	@NotNull
	private int roleId;
	
	private boolean isActive;

	public UserDTO() {
		super();
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String userEmail) {
		this.email = userEmail;
	}
//
//	public String getRoleEntity() {
//		return roleEntity;
//	}
//
//	public void setRoleEntity(String roleEntity) {
//		this.roleEntity = roleEntity;
//	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	

	public UserDTO(int userId, @NotBlank(message = "username cannot be blank") String userName, String userPassword,
			@Email  String userEmail,
			 String token, String confirmPassword, String roleName, @NotNull int roleId,
			boolean isActive) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.email = userEmail;
		this.token = token;
		this.confirmPassword = confirmPassword;
		this.roleName = roleName;
		this.roleId = roleId;
		this.isActive = isActive;
	}

	public UserDTO(String email,int userId) {
		super();
		this.userId = userId;
		this.email = email;
	}
	public UserDTO(String userName, String userPassword) {
		super();
		this.userPassword = userPassword;
		this.userName = userName;
	}

	public UserDTO(int id, @NotEmpty String userName, @NotEmpty String email, @NotEmpty String role) {
		super();
		this.userId = id;
		this.userName = userName;
		this.email = email;
		this.roleName = role;

	}

	public UserDTO(@NotEmpty String email, @NotEmpty String userPassword, @NotEmpty String confirmPassword) {
		super();
		this.email = email;
		this.userPassword = userPassword;
		this.confirmPassword = confirmPassword;
	}

	public UserDTO(int userId, String userName, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
	}

	public UserDTO(String email) {
		super();
		this.email = email;
	}

	public UserDTO(int userId, String userPassword, String userName, String roleName, int roleId) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.roleName = roleName;
		this.roleId = roleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", email="
				+ email + ", token=" + token + ", confirmPassword=" + confirmPassword + ", roleName=" + roleName
				+ ", roleId=" + roleId + ", isActive=" + isActive + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(confirmPassword, email, isActive, roleId, roleName, token, userId, userName, userPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(confirmPassword, other.confirmPassword) && Objects.equals(email, other.email)
				&& isActive == other.isActive && roleId == other.roleId && Objects.equals(roleName, other.roleName)
				&& Objects.equals(token, other.token) && userId == other.userId
				&& Objects.equals(userName, other.userName) && Objects.equals(userPassword, other.userPassword);
	}

	

}

//(regexp = ".*@.*\\..*", message = "invalid email address")
//@NotNull

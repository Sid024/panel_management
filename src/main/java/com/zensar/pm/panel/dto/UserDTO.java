package com.zensar.pm.panel.dto;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor

public class UserDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String roleEntity;
	private String token;
	private String confirmPassword;
	private String roleName;
	private int roleId;
	
	

	public UserDTO() {
		super();
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(String roleEntity) {
		this.roleEntity = roleEntity;
	}

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

	public UserDTO(String userName, String userPassword) {
		super();
		this.userPassword = userPassword;
		this.userName = userName;
	}

	public UserDTO(int id, @NotEmpty String userName, @NotEmpty String email, @NotEmpty String role) {
		super();
		this.userId = id;
		this.userName = userName;
		this.userEmail = email;
		this.roleEntity = role;

	}

	public UserDTO(@NotEmpty String userEmail, @NotEmpty String userPassword, @NotEmpty String confirmPassword) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.confirmPassword = confirmPassword;
	}
	
	
	
	

	public UserDTO(String userEmail) {
		super();
		this.userEmail = userEmail;
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
		return "UserDTO [userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName + ", roleName="
				+ roleName + ", roleId=" + roleId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(confirmPassword, userPassword, roleEntity, roleId, roleName, token, userEmail, userId, userName,
				userPassword);
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
		return Objects.equals(confirmPassword, other.confirmPassword) && Objects.equals(userPassword, other.userPassword)
				&& Objects.equals(roleEntity, other.roleEntity) && roleId == other.roleId
				&& Objects.equals(roleName, other.roleName) && Objects.equals(token, other.token)
				&& Objects.equals(userEmail, other.userEmail) && userId == other.userId
				&& Objects.equals(userName, other.userName) && Objects.equals(userPassword, other.userPassword);
	}

}

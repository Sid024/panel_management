package com.zensar.pm.panel.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserEntity {

	@Id
	@Column(name = "id")
	private int userId;
	@Column(name = "name")
	private String userName;
	@Column(name = "password")
	private String userPassword;
	@Column(name = "email")
	private String email;
	@Column(name = "is_active")
	private boolean isActive;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Column(name = "deleted_by")
	private String deletedBy;
	@Column(name = "deleted_on")
	private LocalDateTime deletedOn;

	public UserEntity() {
		super();
	}

	public UserEntity(String userName, String userPassword) {
		super();
		this.userPassword = userPassword;
		this.userName = userName;
	}

	public UserEntity(int userId, String userPassword) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
	}

	public UserEntity(String userEmail, int userId) {
		super();
		this.userId = userId;
		this.email = userEmail;
	}

//		public UserEntity(String userName, UserRolesEntity userRolesEntity) {
//			super();
//			this.userName = userName;
//			this.userRolesEntity = userRolesEntity;
	//
//		}

//		public UserEntity(int userId, String userName, String email, UserRolesEntity userRolesEntity) {
//			super();
//			this.userId = userId;
//			this.userName = userName;
//			this.email = email;
//			this.userRolesEntity = userRolesEntity;
//		}

	public UserEntity(int userId, String userName, String userPassword, String email, boolean isActive,
			String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isDeleted,
			String deletedBy, LocalDateTime deletedOn) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.email = email;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.isDeleted = isDeleted;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;

	}

//		public UserEntity(UserRolesEntity userRolesEntity) {
//			super();
//			this.userRolesEntity = userRolesEntity;
//		}

	public UserEntity(int userId, String userName, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
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

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public LocalDateTime getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(LocalDateTime deletedOn) {
		this.deletedOn = deletedOn;
	}

//		public UserRolesEntity getUserRolesEntity() {
//			return userRolesEntity;
//		}

//		public void setUserRolesEntity(UserRolesEntity userRolesEntity) {
//			this.userRolesEntity = userRolesEntity;
//		}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", email="
				+ email + ", isActive=" + isActive + ", createdBy=" + createdBy + ", createdOn=" + createdOn
				+ ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + ", isDeleted=" + isDeleted + ", deletedBy="
				+ deletedBy + ", deletedOn=" + deletedOn + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdBy, createdOn, deletedBy, deletedOn, email, isActive, isDeleted, updatedBy,
				updatedOn, userId, userName, userPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(createdBy, other.createdBy) && Objects.equals(createdOn, other.createdOn)
				&& Objects.equals(deletedBy, other.deletedBy) && Objects.equals(deletedOn, other.deletedOn)
				&& Objects.equals(email, other.email) && isActive == other.isActive && isDeleted == other.isDeleted
				&& Objects.equals(updatedBy, other.updatedBy) && Objects.equals(updatedOn, other.updatedOn)
				&& userId == other.userId && Objects.equals(userName, other.userName)
				&& Objects.equals(userPassword, other.userPassword);
	}

}

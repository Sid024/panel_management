package com.zensar.pm.panel.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class UserRolesEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id") // FK column
	private RolesEntity rolesEntity;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id") // FK column
	private UserEntity userEntity;
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

	public UserRolesEntity() {
		super();
	}
	

	public UserRolesEntity(int id, RolesEntity rolesEntity, UserEntity userEntity) {
		super();
		this.id = id;
		this.rolesEntity = rolesEntity;
		this.userEntity = userEntity;
	}
	


	public UserRolesEntity(RolesEntity rolesEntity, UserEntity userEntity) {
		super();
		this.rolesEntity = rolesEntity;
		this.userEntity = userEntity;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
 
//	public int getUserId() {
//		return userId;
//	}
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
//	public int getRoleId() {
//		return roleId;
//	}
//	public void setRoleId(int roleId) {
//		this.roleId = roleId;
//	}
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

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public boolean isDeleted() {
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

	public RolesEntity getRolesEntity() {
		return rolesEntity;
	}

	public void setRolesEntity(RolesEntity rolesEntity) {
		this.rolesEntity = rolesEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public String toString() {
		return "UserRolesEntity [id=" + id + ", rolesEntity=" + rolesEntity + ", userEntity=" + userEntity
				+ ", isActive=" + isActive + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + ", isDeleted=" + isDeleted + ", deletedBy=" + deletedBy
				+ ", deletedOn=" + deletedOn + "]";
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(createdBy, createdOn, deletedBy, deletedOn, id, isActive, isDeleted, roleId, rolesEntity,
//				updatedBy, updatedOn, userEntity, userId);
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		UserRolesEntity other = (UserRolesEntity) obj;
//		return Objects.equals(createdBy, other.createdBy) && Objects.equals(createdOn, other.createdOn)
//				&& Objects.equals(deletedBy, other.deletedBy) && Objects.equals(deletedOn, other.deletedOn)
//				&& id == other.id && isActive == other.isActive && isDeleted == other.isDeleted
//				&& roleId == other.roleId && Objects.equals(rolesEntity, other.rolesEntity)
//				&& Objects.equals(updatedBy, other.updatedBy) && Objects.equals(updatedOn, other.updatedOn)
//				&& Objects.equals(userEntity, other.userEntity) && userId == other.userId;
//	}
//	

}

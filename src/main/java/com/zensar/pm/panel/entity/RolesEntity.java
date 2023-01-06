package com.zensar.pm.panel.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import lombok.AllArgsConstructor;
import lombok.Data;

//import lombok.NoArgsConstructor;
@Entity
@Table(name = "roles_master")
@Data
public class RolesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "role_name")
	private String roleName;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "updated_on")
	private LocalDateTime upatedOn;
	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Column(name = "deleted_by")
	private String deletedBy;
	@Column(name = "deleted_on")
	private LocalDateTime deletedOn;

	

	public RolesEntity() {
		super();
	}

	public RolesEntity(String roleName) {
		super();
		this.roleName = roleName;
	}

	public RolesEntity(int roleId, String roleName, String createdBy, LocalDateTime createdOn, String updatedBy,
			LocalDateTime upatedOn, boolean isDeleted, String deletedBy, LocalDateTime deletedOn) {
		super();
		this.id = roleId;
		this.roleName = roleName;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.upatedOn = upatedOn;
		this.isDeleted = isDeleted;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
	}

	public int getRoleId() {
		return id;
	}

	public void setRoleId(int roleId) {
		this.id = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public LocalDateTime getUpatedOn() {
		return upatedOn;
	}

	public void setUpatedOn(LocalDateTime upatedOn) {
		this.upatedOn = upatedOn;
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

	@Override
	public String toString() {
		return "RolesEntity [roleId=" + id + ", roleName=" + roleName + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", updatedBy=" + updatedBy + ", upatedOn=" + upatedOn + ", isDeleted=" + isDeleted
				+ ", deletedBy=" + deletedBy + ", deletedOn=" + deletedOn + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdBy, createdOn, deletedBy, deletedOn, isDeleted, id, roleName, upatedOn,
				updatedBy);
	}
   
	public RolesEntity(int roleId, String roleName) {
		super();
		this.id = roleId;
		this.roleName = roleName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolesEntity other = (RolesEntity) obj;
		return Objects.equals(createdBy, other.createdBy) && Objects.equals(createdOn, other.createdOn)
				&& Objects.equals(deletedBy, other.deletedBy) && Objects.equals(deletedOn, other.deletedOn)
				&& isDeleted == other.isDeleted && id == other.id && Objects.equals(roleName, other.roleName)
				&& Objects.equals(upatedOn, other.upatedOn) && Objects.equals(updatedBy, other.updatedBy);
	}

}

package com.zensar.pm.panel.dto;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.zensar.pm.panel.entity.GradeEntity;
import com.zensar.pm.panel.entity.InterviewType;
import com.zensar.pm.panel.entity.PanelLevelEntity;
import com.zensar.pm.panel.entity.RoleEntity;
import com.zensar.pm.panel.entity.UserEntity;

public class PanelEntityDTO {
	
	private int id;
	private UserEntity userEntity;
	private String contact;
	private GradeEntity gradeEntity;
	private PanelLevelEntity panelLevelEntity;
	private String remark;
	private String createdBy;
	private LocalDateTime createdOn;
	private String updatedBy;
	private LocalDateTime updatedOn;
	private boolean isDeleted;
	private String deletedBy;
	private LocalDateTime deletedOn;
	private InterviewType interviewType;
	private RoleEntity roleType;
	
	public PanelEntityDTO(int id, UserEntity userEntity, String contact, GradeEntity gradeEntity,
			PanelLevelEntity panelLevelEntity, String remark, String createdBy, LocalDateTime createdOn,
			String updatedBy, LocalDateTime updatedOn, boolean isDeleted, String deletedBy, LocalDateTime deletedOn,
			InterviewType interviewType, RoleEntity roleType) {
		super();
		this.id = id;
		this.userEntity = userEntity;
		this.contact = contact;
		this.gradeEntity = gradeEntity;
		this.panelLevelEntity = panelLevelEntity;
		this.remark = remark;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.isDeleted = isDeleted;
		this.deletedBy = deletedBy;
		this.deletedOn = deletedOn;
		this.interviewType = interviewType;
		this.roleType = roleType;
	}
	public PanelEntityDTO() {
		super();
	}
	public PanelEntityDTO(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public GradeEntity getGradeEntity() {
		return gradeEntity;
	}
	public void setGradeEntity(GradeEntity gradeEntity) {
		this.gradeEntity = gradeEntity;
	}
	public PanelLevelEntity getPanelLevelEntity() {
		return panelLevelEntity;
	}
	public void setPanelLevelEntity(PanelLevelEntity panelLevelEntity) {
		this.panelLevelEntity = panelLevelEntity;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public InterviewType getInterviewType() {
		return interviewType;
	}
	public void setInterviewType(InterviewType interviewType) {
		this.interviewType = interviewType;
	}
	
	
	public RoleEntity getRoleType() {
		return roleType;
	}
	public void setRoleType(RoleEntity roleType) {
		this.roleType = roleType;
	}
	@Override
	public String toString() {
		return "PanelEntityDTO [id=" + id + ", userEntity=" + userEntity + ", contact=" + contact + ", gradeEntity="
				+ gradeEntity + ", panelLevelEntity=" + panelLevelEntity + ", remark=" + remark + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn
				+ ", isDeleted=" + isDeleted + ", deletedBy=" + deletedBy + ", deletedOn=" + deletedOn
				+ ", interviewType=" + interviewType + ", roleType=" + roleType + "]";
	}
	
	

}

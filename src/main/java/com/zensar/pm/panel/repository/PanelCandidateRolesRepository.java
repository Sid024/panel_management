package com.zensar.pm.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.pm.panel.entity.PanelCandidateRolesEntity;

public interface PanelCandidateRolesRepository extends JpaRepository<PanelCandidateRolesEntity, Integer> {
	PanelCandidateRolesEntity findById(int id);
}

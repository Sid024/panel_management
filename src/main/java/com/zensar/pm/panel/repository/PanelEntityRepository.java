package com.zensar.pm.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.PanelEntity;

@Repository
public interface PanelEntityRepository extends JpaRepository<PanelEntity, Integer>{
	@Query("Select p from PanelEntity p where p.userEntity.userId=?1")
	PanelEntity findByUserId(int userId);

}

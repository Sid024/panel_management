package com.zensar.pm.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.PanelAvailabilityStatusEntity;

@Repository
public interface PanelAvailablityStatusRepository extends JpaRepository<PanelAvailabilityStatusEntity, Integer>{

      PanelAvailabilityStatusEntity findByAvailablityStatusId(int availablityStatusId);

}

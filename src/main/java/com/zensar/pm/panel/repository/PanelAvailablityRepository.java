package com.zensar.pm.panel.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.PanelAvailabilityEntity;
import com.zensar.pm.panel.entity.PanelEntity;


@Repository
public interface PanelAvailablityRepository extends JpaRepository<PanelAvailabilityEntity, Integer>{

public boolean existsByStartTime(String startTime);

public boolean existsByPanelId(PanelEntity panelEntity);

public boolean existsByDate(LocalDate date);

}

package com.zensar.pm.panel.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.PanelAvailabilityEntity;
import com.zensar.pm.panel.entity.PanelEntity;
import com.zensar.pm.panel.entity.UserEntity;


@Repository
public interface PanelAvailabilityRepository extends JpaRepository<PanelAvailabilityEntity, Integer>{

public boolean existsByStartTime(LocalTime localTime);

public boolean existsByPanelEntity(PanelEntity panelEntity);

public boolean existsByDate(LocalDate date);

public boolean existsByUserEntity(UserEntity userEntity);


}

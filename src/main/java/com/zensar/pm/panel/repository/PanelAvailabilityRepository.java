package com.zensar.pm.panel.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

@Query(value="select available_date from panels_availability where panel_id=?1",nativeQuery = true)
List<Date> getDatesWithId(int panelId);


}

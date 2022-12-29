package com.zensar.pm.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.PanelAvailablityEntity;


@Repository
public interface PanelAvailablityRepository extends JpaRepository<PanelAvailablityEntity, Integer>{

}

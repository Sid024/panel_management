package com.zensar.pm.panel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.PanelEntity;
import com.zensar.pm.panel.entity.UserEntity;

@Repository
public interface PanelEntityRepository extends JpaRepository<PanelEntity, Integer>{

	PanelEntity findByUserEntity(UserEntity userEntity);



}

package com.zensar.pm.panel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.PanelEntity;
import com.zensar.pm.panel.entity.UserEntity;

@Repository
public interface PanelEntityRepository extends JpaRepository<PanelEntity, Integer>{
	@Query("Select p from PanelEntity p where p.userEntity.id=?1")
	PanelEntity findByUserId(int userId);

	PanelEntity findByUserEntity(UserEntity userEntity);
	
	@Query(value="select * from panels where user_id=?1",nativeQuery=true)
    PanelEntity getPanelsDetails(int id);



}

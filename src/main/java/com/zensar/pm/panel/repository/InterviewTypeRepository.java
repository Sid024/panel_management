package com.zensar.pm.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.pm.panel.entity.InterviewTypesEntity;

public interface InterviewTypeRepository  extends JpaRepository<InterviewTypesEntity, Integer>{
	
	

}

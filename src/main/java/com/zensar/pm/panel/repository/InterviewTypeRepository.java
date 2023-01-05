package com.zensar.pm.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.pm.panel.entity.InterviewTypes;

public interface InterviewTypeRepository  extends JpaRepository<InterviewTypes, String>{
	public InterviewTypes findByTypeId(String typeId);

	public boolean existsByTypeId(String typeId);
	

}

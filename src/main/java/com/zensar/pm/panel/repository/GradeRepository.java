package com.zensar.pm.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.pm.panel.entity.GradeEntity;

public interface GradeRepository extends JpaRepository<GradeEntity, Integer>{
	
	GradeEntity findByGradeId(int gradeId);
	
	boolean existsByGradeId(int gradeId);

}

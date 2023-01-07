package com.zensar.pm.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zensar.pm.panel.entity.GradeEntity;

public interface GradeRepository extends JpaRepository<GradeEntity, Integer>{
	@Query(name = "select * from grade where grade=?1",nativeQuery = true)
    public GradeEntity findByGrade(String grade);
}

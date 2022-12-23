package com.zensar.pm.panel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.Panel;

@Repository
public interface PanelRepository extends JpaRepository<Panel, String> {
	//Panel findByAssociateId(String name);
	@Query("SELECT text FROM panel-management-db text WHERE " +
            "text.associateName LIKE CONCAT('%',:searchText, '%')")
    List<Panel> findByTextIgnoreAssociates(String searchText);
}
package com.zensar.pm.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.zensar.panelmanagement.entity.PanelAvailabilityStatus;
import com.zensar.pm.panel.entity.PanelAvailabilityList;

@Repository
public interface PanelAvailabilityListRepository extends JpaRepository<PanelAvailabilityList,String> {

}

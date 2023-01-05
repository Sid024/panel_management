package com.zensar.pm.panel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.RolesEntity;



@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {

	public List<RolesEntity> findByRoleId(int roleId);
}

package com.zensar.pm.panel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.RoleEntity;



@Repository
public interface RolesRepository extends JpaRepository<RoleEntity, Integer> {

	public List<RoleEntity> findByRoleId(int roleId);
}

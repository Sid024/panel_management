package com.zensar.pm.panel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.RolesEntity;



@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {

<<<<<<< HEAD
	public List<RolesEntity> findByRoleId(int roleId);
=======
	public List<RoleEntity> findByRoleId(int roleId);

	public List<RoleEntity> findByRoleName(String roleName);
>>>>>>> branch 'main' of https://github.com/anand-zensar/panel_management_rest_api_pm_dec_2022
}

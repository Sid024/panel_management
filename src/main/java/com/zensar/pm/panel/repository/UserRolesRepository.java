package com.zensar.pm.panel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.UserRolesEntity;


@Repository
public interface UserRolesRepository extends JpaRepository<UserRolesEntity, Integer> {
	@Query("Select u from UserRolesEntity u where u.userEntity.userId=?1")
	List<UserRolesEntity> findByUserId(int userId);
<<<<<<< HEAD
	@Query("Select u from UserRolesEntity u where u.rolesEntity.roleId=?1")
	List<UserRolesEntity> findByRoleId(int roleId);
=======
	@Query("Select u from UserRolesEntity u where u.roleEntity.roleId=?1")
	List<UserRolesEntity> findByRoleId(int roleId);
	
>>>>>>> branch 'main' of https://github.com/anand-zensar/panel_management_rest_api_pm_dec_2022
}

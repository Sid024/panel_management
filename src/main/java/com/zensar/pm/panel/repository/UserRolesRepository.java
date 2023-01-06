package com.zensar.pm.panel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.UserRolesEntity;


@Repository
public interface UserRolesRepository extends JpaRepository<UserRolesEntity, Integer> {
	@Query("Select u from UserRolesEntity u where u.userEntity.id=?1")
	List<UserRolesEntity> findByUserId(int userId);
	@Query("Select u from UserRolesEntity u where u.rolesEntity.id=?1")
	List<UserRolesEntity> findByRoleId(int roleId);
	
}

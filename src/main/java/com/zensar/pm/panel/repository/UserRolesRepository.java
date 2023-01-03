package com.zensar.pm.panel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.UserRoleEntity;


@Repository
public interface UserRolesRepository extends JpaRepository<UserRoleEntity, Integer> {
	@Query("Select u from UserRoleEntity u where u.userEntity.userId=?1")
	List<UserRoleEntity> findByUserId(int userId);
	@Query("Select u from UserRoleEntity u where u.roleEntity.roleId=?1")
	List<UserRoleEntity> findByRoleId(int roleId);
}

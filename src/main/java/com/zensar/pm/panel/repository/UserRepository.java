package com.zensar.pm.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	UserEntity findByUserName(String userName);

}

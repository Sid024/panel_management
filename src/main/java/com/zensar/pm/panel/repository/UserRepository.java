package com.zensar.pm.panel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.pm.panel.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}

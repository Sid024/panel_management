package com.zensar.pm.panel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	//UserEntity findByUserName(String userName);
	
	
	public List<UserEntity> findByUserName(String userName);

	Optional<UserEntity> findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
	public boolean existsByUserName(String userName);
	

	public List<UserEntity> findByUserIdNotAndEmail(int userId, String email);
	
	public List<UserEntity> findByUserIdNotAndUserName(int userId, String userName);

	boolean existsByUserId(int panelId);

	boolean existsByUserIdNotAndEmail(int userId, String email);
}

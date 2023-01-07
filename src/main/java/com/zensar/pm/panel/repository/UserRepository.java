package com.zensar.pm.panel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zensar.pm.panel.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	//UserEntity findByUserName(String userName);
	
	
	public List<UserEntity> findByUserName(String userName);

	Optional<UserEntity> findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
	public boolean existsByUserName(String userName);
	

	public List<UserEntity> findByIdNotAndEmail(int userId, String email);
	
	public List<UserEntity> findByIdNotAndUserName(int userId, String userName);

	boolean existsByIdNotAndEmail(int userId, String email);
	
	@Query(value="select * from users where name=?1",nativeQuery=true)
    UserEntity getUserDetails(String UserName);
}

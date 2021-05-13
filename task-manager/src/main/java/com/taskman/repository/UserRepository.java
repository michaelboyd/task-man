package com.taskman.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.taskman.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query("select case when count(u) > 0 then true else false end from User u where u.email = ?1")
	Boolean selectExistsEmail(String email);
	
	@Query("select case when count(u) > 0 then true else false end from User u where u.id = ?1")
	Boolean selectExistsId(Long id);
	
	User findByName(String name);
	

}

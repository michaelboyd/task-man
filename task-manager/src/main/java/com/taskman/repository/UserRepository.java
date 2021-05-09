package com.taskman.repository;

import org.springframework.data.repository.CrudRepository;

import com.taskman.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}

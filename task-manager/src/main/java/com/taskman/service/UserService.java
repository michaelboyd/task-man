package com.taskman.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.taskman.entity.User;

@Validated
public interface UserService {

	@NotNull
	Iterable<User> getAllUsers();

	User create(@NotNull(message = "The user cannot be null.") @Valid User user);
	
	User getUser(Long id);


}

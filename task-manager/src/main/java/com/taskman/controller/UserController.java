package com.taskman.controller;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskman.entity.User;
import com.taskman.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = { "", "/users" })
	public @NotNull Iterable<User> getUsers() {
		return userService.getAllUsers();
	}

	@PostMapping("/users")
	public ResponseEntity <User> newUser(@RequestBody User user) {
		HttpStatus status = HttpStatus.CREATED;
		User created = userService.create(user);
		return new ResponseEntity<>(created, status);
	}

}

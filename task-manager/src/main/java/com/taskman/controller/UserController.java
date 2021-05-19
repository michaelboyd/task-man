package com.taskman.controller;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskman.entity.Hero;
import com.taskman.entity.Task;
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
	
	@GetMapping(value = { "", "/users/{id}" })
	public @NotNull ResponseEntity<User> findById(@PathVariable("id") Long id) {
		Optional<User> user = userService.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = { "", "/users/{id}" })
	public ResponseEntity<User> updateTask(@PathVariable("id") long id, @RequestBody User user) {
		Optional<User> userData = userService.findById(id);
		if (userData.isPresent()) {
			User _user = userData.get();
			_user.setName(user.getName());
			_user.setUpdateDate(LocalDate.now());
			return new ResponseEntity<>(userService.update(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	

}

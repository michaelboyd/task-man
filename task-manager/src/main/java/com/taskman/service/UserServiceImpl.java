package com.taskman.service;

import java.time.LocalDate;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.taskman.entity.User;
import com.taskman.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public @NotNull Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User create(@NotNull(message = "The user cannot be null.") @Valid User user) {
		user.setCreateDate(LocalDate.now());
		user.setUpdateDate(LocalDate.now());
		return userRepository.save(user);
	}

	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}

}

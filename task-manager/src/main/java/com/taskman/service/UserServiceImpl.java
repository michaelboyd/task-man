package com.taskman.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.taskman.entity.User;
import com.taskman.exception.BadRequestException;
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
		Boolean emailExists = userRepository.selectExistsEmail(user.getEmail());
		if(emailExists ) {
			throw new BadRequestException("Email " + user.getEmail() + " is not available");
		}
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void delete(@NotNull(message = "The user cannot be null.") @Valid User user) {
		userRepository.delete(user);
	}

	@Override
	public User update(@NotNull(message = "The user cannot be null.") @Valid User user) {
		return userRepository.save(user);
	}
	
}

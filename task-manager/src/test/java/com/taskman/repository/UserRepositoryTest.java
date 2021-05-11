package com.taskman.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.taskman.entity.User;

@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@AfterEach
	void tearDown() {
		userRepository.deleteAll();
	}
	
	@Test
	void userWithEmailExists() {
		//given a certain email
		User user = new User("test", "test@email.com");
		userRepository.save(user);
		//when get if user exists
		String email = "test@email.com";
		boolean expected = userRepository.selectExistsEmail(email);
		//then assert user is not null
		assertThat(expected).isTrue();
	}
	
	@Test
	void userWithEmailNotExists() {
		//given a certain email
		String email = "not-exists@email.com";
		boolean expected = userRepository.selectExistsEmail(email);
		//then assert user is not null
		assertThat(expected).isFalse();
	}	

}

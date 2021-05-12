package com.taskman.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.taskman.entity.User;


@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	void setUp() {
		userRepository.deleteAll();
	}
	
	@Test
	void userWithEmailExists() {
		//given
		User user = new User("test", "test@email.com");
		userRepository.save(user);
		
		//when
		String email = "test@email.com";
		boolean expected = userRepository.selectExistsEmail(email);
		
		//then
		assertThat(expected).isTrue();
	}
	
	@Test
	void userWithEmailNotExists() {
		//given
		String email = "not-exists@email.com";
		boolean expected = userRepository.selectExistsEmail(email);
		
		//then
		assertThat(expected).isFalse();
	}
	
	@Test
	void userWithIdExists() {
		//given
		User user = new User("test", "test@email.com");
		User saved = userRepository.save(user);
		//when get if user exists
		boolean expected = userRepository.selectExistsId(saved.getId());
		//then
		assertThat(expected).isTrue();
	}
	
	@Test
	void userWithIdNotExists() {
		//given
		boolean expected = userRepository.selectExistsId(10L);
		
		//then assert user is null
		assertThat(expected).isFalse();
	}	

}

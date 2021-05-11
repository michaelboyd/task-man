package com.taskman.services;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taskman.entity.User;
import com.taskman.repository.UserRepository;
import com.taskman.service.UserService;
import com.taskman.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;

	
	private UserService underTest;

	@BeforeEach
	void setUp() {
		underTest = new UserServiceImpl(userRepository);
	}
	
	@Test
	void testGetAllUsers() {
		//when
		underTest.getAllUsers();
		//then
		verify(userRepository).findAll();
	}
	
	@Test
	void testCreateUser() {
		//given
		User user = new User("test", "test@email.com");
		//when
		underTest.create(user);
		//then
		ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
		verify(userRepository).save(userArgumentCaptor.capture());
		User capturedUser = userArgumentCaptor.getValue();
		assertThat(capturedUser).isSameAs(user);
		assertThat(capturedUser).isEqualTo(user);
	}
	
	@Test
	void testFindUserById() {
		//given
		User user = new User("test", "test@email.com");
        doReturn(Optional.of(user)).when(userRepository).findById(Mockito.anyLong());
        //when
        Optional<User> returnedUser = underTest.findById(Mockito.anyLong());
		//then
        assertThat(returnedUser.isPresent()).isTrue();
        assertThat(returnedUser.get()).isSameAs(user);
		
	}

}

package com.taskman.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.taskman.entity.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testGetAllUsersNotEmpty() throws Exception {
		
		ResponseEntity <User[]> response =
				  this.restTemplate.getForEntity(
						  "http://localhost:" + port + "/api/users",
				  User[].class);	
		
		User[] rows = response.getBody();
		assertThat(rows.length > 0);
		
		
	}	

}

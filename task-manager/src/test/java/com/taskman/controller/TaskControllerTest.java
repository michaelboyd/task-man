package com.taskman.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RequestCallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskman.entity.Task;
import com.taskman.entity.TaskStatus;
import com.taskman.entity.User;
import com.taskman.service.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TaskControllerTest {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testGetAllTasksNotEmpty() throws Exception {
		ResponseEntity <Task[]> response =
				  this.restTemplate.getForEntity(
						  "http://localhost:" + port + "/api/tasks",
				  Task[].class);	
		Task[] rows = response.getBody();
		assertThat(rows.length > 0);
	}
	
	public void testUpdateStatus() throws Exception {
		
	}
	
	@Test 
	void testGetTaskById() throws Exception {
		ResponseEntity <Task> response =
				  this.restTemplate.getForEntity(
						  "http://localhost:" + port + "/api/task/1",
				  Task.class);	
		Task task = response.getBody();
		assertThat(task != null);
	}
	
	@Test 
	void testTasksByStatus() throws Exception {
		ResponseEntity <Task[]> response =
				  this.restTemplate.getForEntity(
						  "http://localhost:" + port + "/api/tasks/NEW",
				  Task[].class);	
		Task[] tasks = response.getBody();
		assertThat(tasks.length == 1);
	}	
	
	@Test 
	void testUpdateTaskStatusAndUser() throws Exception {
		
		ResponseEntity <Task> response =
				  this.restTemplate.getForEntity(
						  "http://localhost:" + port + "/api/task/1",
				  Task.class);	
		Task task = response.getBody();
		assertThat(task != null);
		
		User user = userService.getUser(1L);
		//update the status and user
		task.setStatus(TaskStatus.IN_PROGRESS.name());
		task.setUser(user);

		restTemplate.execute(
				"http://localhost:" + port + "/api/task/1", 
		  HttpMethod.PUT, 
		  requestCallback(task), 
		  clientHttpResponse -> null);
		
		response =
				  this.restTemplate.getForEntity(
						  "http://localhost:" + port + "/api/task/1",
				  Task.class);	
		task = response.getBody();
		assertThat(task.getStatus().toString() == "IN_PROGRESS");		

	}
	
	RequestCallback requestCallback(final Task updatedInstance) {
	    return clientHttpRequest -> {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.writeValue(clientHttpRequest.getBody(), updatedInstance);
	        clientHttpRequest.getHeaders().add(
	          HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//	        clientHttpRequest.getHeaders().add(
//	          HttpHeaders.AUTHORIZATION, "Basic " + getBase64EncodedLogPass());
	    };
	}	
	
	

}

package com.taskman.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.taskman.entity.Task;
import com.taskman.entity.TaskStatus;

@DataJpaTest
public class TaskRepositoryTest {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@BeforeEach
	void setUp() {
		taskRepository.deleteAll();
	}
	
	@Test
	void testFindTasksByStatus() {
		// given 
		Task task = new Task("Test Something", TaskStatus.NEW.name());
		taskRepository.save(task);
		
		// when 
		List <Task> tasks = taskRepository.findByStatus(TaskStatus.NEW.name());
		
		// then
		assertThat(tasks).isNotEmpty();
		
	}
	
	@Test
	void testFindTasksByName() {
		// given 
		String name = "Test Something";
		Task task = new Task(name, TaskStatus.NEW.name());
		taskRepository.save(task);
		
		// when 
		List <Task> tasks = taskRepository.findByName(name);
		
		// then
		assertThat(tasks).isNotEmpty();
	}	

}

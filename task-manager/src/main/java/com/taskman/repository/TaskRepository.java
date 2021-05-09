package com.taskman.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.taskman.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
	
	List <Task> findByName(String name);
	
	List <Task> findByStatus(String status);

}

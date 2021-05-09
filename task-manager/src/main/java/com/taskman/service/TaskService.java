package com.taskman.service;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.taskman.entity.Task;

@Validated
public interface TaskService {
	
	
	Task create(@NotNull(message = "The task cannot be null.") @Valid Task task);
	
	@NotNull
	Iterable<Task> getAllTasks();
	
	Optional <Task> getTask(Long id);
	
	Iterable<Task> getTasksByStatus(String status);

    Task update(@NotNull(message = "The task cannot be null.") @Valid Task task);
    
}

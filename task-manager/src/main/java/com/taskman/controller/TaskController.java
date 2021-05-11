package com.taskman.controller;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskman.entity.Task;
import com.taskman.service.TaskService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

	private TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping(value = { "", "/tasks" })
	public @NotNull Iterable<Task> getTasks() {
		return taskService.getAllTasks();
	}
	
	@GetMapping(value = { "", "/tasks/{status}" })
	public @NotNull Iterable<Task> getTasksByStatus(@PathVariable("status") String status) {
		return taskService.getTasksByStatus(status.toUpperCase());
	}	

	@GetMapping(value = { "", "/task/{id}" })
	public @NotNull ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
		Optional<Task> task = taskService.getTask(id);
		if (task.isPresent()) {
			return new ResponseEntity<>(task.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = { "", "/task/{id}" })
	public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task task) {
		Optional<Task> taskData = taskService.getTask(id);

		if (taskData.isPresent()) {
			Task _task = taskData.get();
			_task.setName(task.getName());
			_task.setStatus(task.getStatus());
			_task.setUser(task.getUser());
			_task.setUpdateDate(LocalDate.now());
			return new ResponseEntity<>(taskService.update(_task), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}

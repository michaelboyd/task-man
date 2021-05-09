package com.taskman.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.taskman.entity.Task;
import com.taskman.repository.TaskRepository;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{
	
	private TaskRepository taskRepository;

	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public @NotNull Iterable<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task create(@NotNull(message = "The task cannot be null.") @Valid Task task) {
		task.setCreateDate(LocalDate.now());
		task.setUpdateDate(LocalDate.now());
		return taskRepository.save(task);
	}

	@Override
	public Task update(@NotNull(message = "The task cannot be null.") @Valid Task task) {
		task.setUpdateDate(LocalDate.now());
		return taskRepository.save(task);
	}

	@Override
	public Optional <Task> getTask(Long id) {
		return taskRepository.findById(id);
	}
	
	

}
